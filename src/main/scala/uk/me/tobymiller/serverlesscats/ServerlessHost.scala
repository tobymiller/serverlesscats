package uk.me.tobymiller.serverlesscats

import cats._
import cats.data._
import cats.implicits._
import java.io.OutputStream
import java.io.InputStream
import com.amazonaws.services.lambda.runtime.Context
import io.circe._, io.circe.parser._
import java.io.BufferedReader
import java.io.InputStreamReader
import scala.language.postfixOps
import cats.effect._
import cats.effect.implicits._
import java.io.IOException
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import com.amazonaws.services.lambda.runtime.RequestStreamHandler

class ServerlessHost(handler: Handler) extends RequestStreamHandler {
  private def catchAll[E](in: => E) = EitherT.fromEither[IO](Either.catchNonFatal[E](in))
  
  def handleRequest(is: InputStream, os: OutputStream, context: Context): Unit = {
    val result = (for {
      reader <- catchAll(new BufferedReader(new InputStreamReader(is)))
      writer <- catchAll(new BufferedWriter(new OutputStreamWriter(os)))
      inString <- catchAll[String](Iterator continually reader.readLine takeWhile (_ != null) mkString)
      json <- EitherT.fromEither[IO](parse(inString))
      outString <- handler.handle(IO(json))
      _ <- catchAll(writer.write(outString.toString()))
      _ <- catchAll(writer.flush())
      written <- catchAll(writer.close())
    } yield written).value.unsafeRunSync
    result.left.foreach(e => throw e)
  }
}
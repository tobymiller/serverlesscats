package me.tobymiller.http4stest

import cats.effect._
import cats.implicits._
import io.circe.Json, io.circe.parser._
import io.circe.syntax.EncoderOps
import org.specs2._
import uk.me.tobymiller.serverlesscats._
import cats.data.EitherT
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets


class ServerlessHostSpec extends org.specs2.mutable.Specification {
  "Handler should run on input and produce output" >> {
    val host = new ServerlessHost(new Handler {
      def handle(input: IO[Json]): EitherT[IO, Throwable, Json] = 
        EitherT(input.map(json => Either.right[Throwable, Json](Map("input" -> json).asJson)))
    })
    val inJson = Map("key" -> "value").asJson
    val outStream = new ByteArrayOutputStream
    host.handleRequest(new ByteArrayInputStream(inJson.toString().getBytes(StandardCharsets.UTF_8)), outStream, null)
    var json = parse(new String(outStream.toByteArray(), StandardCharsets.UTF_8))
    json.right.toOption must_== Some(Map("input" -> Map("key" -> "value")).asJson)
  }
}

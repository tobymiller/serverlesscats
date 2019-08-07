package uk.me.tobymiller.serverlesscats

import cats.effect.{ContextShift, IO}
import io.circe.Json
import cats.data.EitherT

trait Handler {
  def handle(input: IO[Json])(implicit cs: ContextShift[IO]): EitherT[IO, Throwable, Json]
}
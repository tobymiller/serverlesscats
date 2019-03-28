package uk.me.tobymiller.serverlesscats

import cats.effect.IO
import io.circe.Json
import cats.data.EitherT

trait Handler {
  def handle(input: IO[Json]): EitherT[IO, Throwable, Json]
}
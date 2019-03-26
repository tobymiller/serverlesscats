package uk.me.tobymiller.serverlesscats

import cats.effect.IO
import io.circe.Json

trait Handler {
  def handle(input: IO[Json]): IO[Json]
}
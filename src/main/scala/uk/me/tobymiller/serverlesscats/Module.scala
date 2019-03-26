package uk.me.tobymiller.serverlesscats

import cats._
import cats.data._
import cats.implicits._

// Custom DI module
class Module extends App {
  def a: String = "hi" |+| "s"
  def b = a |+| "a"
}

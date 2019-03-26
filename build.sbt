lazy val CatsEffectVersion = "1.2.0"
lazy val CirceVersion      = "0.11.1"
lazy val ScalaTestVersion  = "3.0.5"
lazy val ScalaCheckVersion = "1.13.4"

lazy val root = (project in file("."))
  .settings(
    organization := "uk.me.tobymiller",
    name := "serverlesscats",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.4",
    scalacOptions := Seq(
      "-deprecation",
      "-encoding",
      "UTF-8",
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-Ypartial-unification"
    ),
    libraryDependencies ++= Seq(
      "org.typelevel"   %% "cats-effect"         % CatsEffectVersion,

      "io.circe"        %% "circe-core"          % CirceVersion,
      "io.circe"        %% "circe-generic"       % CirceVersion,

      "org.scalatest"   %% "scalatest"           % ScalaTestVersion  % Test,
      "org.scalacheck"  %% "scalacheck"          % ScalaCheckVersion % Test
    )
  )


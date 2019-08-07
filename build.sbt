lazy val CatsEffectVersion = "2.0.0-M5"
lazy val CirceVersion      = "0.12.0-M4"
lazy val ScalaTestVersion  = "3.0.8"
lazy val ScalaCheckVersion = "1.14.0"

lazy val root = (project in file("."))
  .settings(
    organization := "uk.me.tobymiller",
    name := "serverlesscats",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.0",
    scalacOptions := Seq(
      "-deprecation",
      "-encoding",
      "UTF-8",
      "-feature",
      "-language:existentials",
      "-language:higherKinds"
    ),
    scalacOptions in Test ++= Seq("-Yrangepos"),
    libraryDependencies ++= Seq(
      "org.typelevel"   %% "cats-effect"         % CatsEffectVersion,

      "io.circe"        %% "circe-core"          % CirceVersion,
      "io.circe"        %% "circe-generic"       % CirceVersion,
      "io.circe"        %% "circe-parser"       % CirceVersion,

      "org.scalatest"   %% "scalatest"           % ScalaTestVersion  % Test,
      "org.scalacheck"  %% "scalacheck"          % ScalaCheckVersion % Test,
      "org.specs2" %% "specs2-core" % "4.7.0" % "test",

      "com.amazonaws" % "aws-lambda-java-events" % "2.2.5",
      "com.amazonaws" % "aws-lambda-java-core" % "1.2.0"
    )
  )


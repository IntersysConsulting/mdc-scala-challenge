import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.intersys",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "mdc-scala-challenge",
    libraryDependencies ++= {
      val akkaVersion = "2.4.14"
      val akkaHttpVersion = "10.0.1"
      Seq(
        "com.typesafe.akka" %% "akka-actor" % akkaVersion,
        "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
        "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
        "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
        scalaTest % Test
      )
    }
  )

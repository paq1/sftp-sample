ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "sftp",
    libraryDependencies ++= Seq(
      "com.hierynomus" % "sshj" % "0.35.0",
      "ch.qos.logback" % "logback-classic" % "1.4.6" % Runtime,
      "com.typesafe.akka" %% "akka-actor" % "2.8.0"
    )
  )

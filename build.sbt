import sbt.Keys.libraryDependencies

lazy val root = (project in file("."))
  .settings(
    name := "test-testcontainers",
    version := "1.0",
    scalaVersion := "2.13.8",
    libraryDependencies ++= Seq(
      "com.dimafeng" %% "testcontainers-scala-elasticsearch" % TestContainersVersion % Test,
      "com.dimafeng" %% "testcontainers-scala-scalatest" % TestContainersVersion % Test,
      "org.scalatest" %% "scalatest-wordspec" % ScalaTestVersion % Test,
      "org.scalatest" %% "scalatest-shouldmatchers" % ScalaTestVersion % Test,
      "io.circe" %% "circe-generic" % CirceVersion,
      "io.circe" %% "circe-generic-extras" % CirceVersion,
      "io.circe" %% "circe-literal" % CirceVersion,
      "io.circe" %% "circe-parser" % CirceVersion,
    )
  )
val ScalaTestVersion = "3.2.12"
val TestContainersVersion = "0.40.7"
val CirceVersion = "0.14.1"





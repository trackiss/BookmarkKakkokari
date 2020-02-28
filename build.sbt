name := "BookmarkKakkokari"
version := "0.1"

scalaVersion := "2.13.1"
scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")

val baseSettings = Seq(
  scalaVersion := "2.13.1",
  scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")
)

lazy val infrastructure = (project in file("modules/infrastructure"))
  .settings(baseSettings)
  .settings(name := "infrastructure", libraryDependencies += {
    lazy val uuidV = "0.3.1"
    "io.jvm.uuid" %% "scala-uuid" % uuidV
  })

lazy val domain = (project in file("modules/domain"))
  .settings(baseSettings)
  .settings(name := "domain", libraryDependencies += {
    lazy val bcryptV = "4.1"
    "com.github.t3hnar" %% "scala-bcrypt" % bcryptV
  })
  .dependsOn(infrastructure)

lazy val use_case = (project in file("modules/use_case"))
  .settings(baseSettings)
  .settings(name := "use_case")
  .dependsOn(domain, infrastructure)

lazy val interface_adapter =
  (project in file("modules/interface_adapter"))
    .settings(baseSettings)
    .settings(
      name := "interface_adapter",
      libraryDependencies ++= {
        lazy val accordV = "0.7.5"
        lazy val akkaV = "2.6.3"
        lazy val circeV = "1.30.0"
        lazy val httpV = "10.1.11"
        lazy val logV = "1.2.3"
        lazy val psqlV = "42.2.10"
        lazy val skinnyV = "3.1.0"

        Seq(
          "com.wix" %% "accord-core" % accordV,
          "com.typesafe.akka" %% "akka-stream" % akkaV,
          "de.heikoseeberger" %% "akka-http-circe" % circeV,
          "com.typesafe.akka" %% "akka-http-core" % httpV,
          "ch.qos.logback" % "logback-classic" % logV,
          "org.postgresql" % "postgresql" % psqlV,
          "org.skinny-framework" %% "skinny-orm" % skinnyV
        )
      }
    )
    .dependsOn(use_case, infrastructure)

lazy val dbDriver = "org.postgresql.Driver"
lazy val dbUrl = "jdbc:postgresql://localhost/bookmark"
lazy val dbUser = "postgres"
lazy val dbPassword = "P@ssw0rd"

lazy val accordV = "0.7.5"
lazy val akkaV = "2.6.3"
lazy val bcryptV = "4.1"
lazy val circeV = "1.30.0"
lazy val httpV = "10.1.11"
lazy val logV = "1.2.3"
lazy val psqlV = "42.2.10"
lazy val slickV = "3.3.2"
lazy val uuidV = "0.3.1"

val baseSettings = Seq(
  scalaVersion := "2.13.1",
  scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")
)

lazy val infrastructure = (project in file("modules/infrastructure"))
  .settings(baseSettings)
  .settings(name := "infrastructure", libraryDependencies += {
    "io.jvm.uuid" %% "scala-uuid" % uuidV
  })

lazy val domain = (project in file("modules/domain"))
  .settings(baseSettings)
  .settings(name := "domain", libraryDependencies += {
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
      libraryDependencies ++=
        Seq(
          "com.wix" %% "accord-core" % accordV,
          "com.typesafe.akka" %% "akka-stream" % akkaV,
          "de.heikoseeberger" %% "akka-http-circe" % circeV,
          "com.typesafe.akka" %% "akka-http-core" % httpV,
          "ch.qos.logback" % "logback-classic" % logV,
          "org.postgresql" % "postgresql" % psqlV,
          "com.typesafe.slick" %% "slick" % slickV,
          "com.typesafe.slick" %% "slick-hikaricp" % slickV,
          "com.typesafe.slick" %% "slick-codegen" % slickV
        )
    )
    .dependsOn(use_case, infrastructure)

lazy val interface = (project in file("modules/interface"))
  .settings(baseSettings)
  .settings(name := "interface")
  .dependsOn(interface_adapter, infrastructure)

lazy val root = (project in file("."))
  .enablePlugins(FlywayPlugin)
  .settings(baseSettings)
  .settings(
    name := "BookmarkKakkokari",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % logV,
      "org.postgresql" % "postgresql" % psqlV,
      "com.typesafe.slick" %% "slick" % slickV,
      "com.typesafe.slick" %% "slick-codegen" % slickV
    ),
    flywayUrl := dbUrl,
    flywayUser := dbUser,
    flywayPassword := dbPassword,
    flywayLocations := Seq("filesystem:src/main/resources/db")
  )
  .aggregate(infrastructure, domain, use_case, interface_adapter, interface)

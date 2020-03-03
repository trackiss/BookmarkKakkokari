lazy val flywayV = "6.2.3"
lazy val slickCodegenV = "1.4.0"

addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % flywayV)

addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % slickCodegenV)

lazy val psqlV = "42.2.10"
lazy val slickV = "3.3.2"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % psqlV,
  "com.typesafe.slick" %% "slick" % slickV
)

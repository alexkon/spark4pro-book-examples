name := "spark-book-demo"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= sparkLocal

lazy val sparkVersion = "2.0.2"

lazy val sparkLocal = Seq(
  "org.apache.spark" %% "spark-sql" % "2.1.1"
    exclude("org.slf4j", "slf4j-log4j12"),
  "ch.qos.logback" % "logback-classic" % "1.1.7"
)
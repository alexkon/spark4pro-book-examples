package com.example.chapter2

import org.apache.spark.sql.{Row, SparkSession}
import org.slf4j.{Logger, LoggerFactory}

object MatchPersons extends App {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  // setup spark

  val spark = SparkSession
    .builder()
    .appName("Spark SQL basic example")
    .master("local[*]")
    .getOrCreate()

  import spark.implicits._

  val parsed = spark
    .read
    .option("header", "true")
    .option("nullValue", "?")
    .option("inferSchema", "true")
    .csv("linkage")


  logger.info("Show CSV schema:")
  parsed.show()
  parsed.printSchema()


  logger.info("Show first 10 records without header:")
  val head = parsed.take(10)
  head.foreach(println)

  logger.info("Count true and false records")
  parsed.groupBy("is_match").count().orderBy($"count".desc).show()
}

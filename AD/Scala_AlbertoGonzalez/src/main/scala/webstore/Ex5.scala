package webstore

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{array_contains, col, explode}

object Ex5 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  val items = spark.read.option("header", "true").option("encoding", "windows-1252").option("multiline", "true").json("datos/Items.json")

  items
    .withColumn("Customer", explode(col("reviews.dni_customer")))
    .select(col("reviews.rate"), col("Customer"))
    .where(!(array_contains(col("reviews.rate"), 1)) && !(array_contains(col("reviews.rate"), 2)))
    .show(false)
}

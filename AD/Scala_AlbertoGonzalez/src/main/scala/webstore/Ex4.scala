package webstore

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, explode}

object Ex4 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  val items = spark.read.option("header", "true").option("encoding", "windows-1252").option("multiline", "true").json("datos/Items.json")

  items.withColumn("rate", explode(col("reviews.rate")))
    .select(col("name"), col("rate"))
    .filter(col("rate").gt(4))
    .show(false)
}

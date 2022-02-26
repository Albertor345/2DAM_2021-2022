package webstore

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.functions.{col, explode}
import org.apache.spark.sql.{SparkSession, functions}

object Ex2 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  val items = spark.read.option("header", "true").option("encoding", "windows-1252").option("multiline", "true").json("datos/Items.json")

  items
    .withColumn("Dni_Customer", explode(col("reviews.dni_customer")))
    .select(col("Dni_Customer"), functions.size(col("reviews")).as("numReviews"))
    .groupBy("Dni_Customer")
    .agg(functions.count("Dni_Customer").as("Number of Reviews"))
    .show(false)

}

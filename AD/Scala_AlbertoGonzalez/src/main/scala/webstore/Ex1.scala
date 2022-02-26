package webstore

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.functions.col

object Ex1 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  val customers = spark.read.option("header", "true").option("encoding", "windows-1252").option("multiline", "true").json("datos/Customers.json")

  customers.select(col("_id"), functions.size(col("purchases")).as("numPurchases"))
    .show(false)

}

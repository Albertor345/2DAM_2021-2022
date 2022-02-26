package webstore

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.functions.{col, sum}
import org.apache.spark.sql.{SparkSession, functions}

object Ex7 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  val customers = spark.read.option("header", "true").option("encoding", "windows-1252").option("multiline", "true").json("datos/Items.json")
/*
  customers.agg(withColumn("Purchase", functions.explode(col("purchases")))
    .withColumn("Suma", sum(col("price"))))
    .show(false)*/

}

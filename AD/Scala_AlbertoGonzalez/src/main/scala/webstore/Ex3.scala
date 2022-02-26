package webstore

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.functions.{col, desc, exists}


object Ex3 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  import spark.implicits._

  spark.sparkContext.setLogLevel("ERROR")

  val customers = spark.read.option("header", "true").option("encoding", "windows-1252").option("multiline", "true").json("datos/Customers.json")

  customers.withColumn("numPurchases", functions.size(col("purchases")))
    .select(col("_id"),col("numPurchases"))
    .sort(desc("numPurchases"))
    .limit(1)
    .show(false)


}

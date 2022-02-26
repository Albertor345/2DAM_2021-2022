package madrid

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{asc, col, count, desc, when}

object Ex11 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  import spark.implicits._

  spark.sparkContext.setLogLevel("ERROR")

  val madrid = spark.read.option("header", "true").option("encoding", "windows-1252").csv("datos/206974-0-agenda-eventos-culturales-100.csv")

  madrid.groupBy($"DISTRITO-INSTALACION")
    .agg(
      count(when($"GRATUITO" === "1", 1)).as("Free"),
      count(when($"GRATUITO" === "0", 1)).as("PAID"),
      count("GRATUITO").as("Total"))
    .withColumn("Percentage Of free Events", (col("Free") / col("Total")) * 100 )
    .select(col("DISTRITO-INSTALACION"), col("Percentage Of free Events"))
    .sort(asc("Percentage Of free Events"))
    .show(false)

}

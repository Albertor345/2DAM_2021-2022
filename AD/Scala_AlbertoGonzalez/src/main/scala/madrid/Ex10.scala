package madrid

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{asc, count, when}

object Ex10 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  import spark.implicits._

  spark.sparkContext.setLogLevel("ERROR")

  val madrid = spark.read.option("header", "true").option("encoding", "windows-1252").csv("datos/206974-0-agenda-eventos-culturales-100.csv")

  madrid.groupBy($"DISTRITO-INSTALACION")
    .agg(
      count(when($"GRATUITO"==="1", 1)).as("Free"),
      count(when($"GRATUITO"==="0", 1)).as("Non Free"))
    .na.drop()
    .show(false)

}

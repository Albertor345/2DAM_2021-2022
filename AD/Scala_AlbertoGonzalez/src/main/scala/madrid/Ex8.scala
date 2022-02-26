package madrid

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{count, desc}

import scala.xml.Utility.sort

object Ex8 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  import spark.implicits._

  spark.sparkContext.setLogLevel("ERROR")

  val madrid = spark.read.option("header", "true").option("encoding", "windows-1252").csv("datos/206974-0-agenda-eventos-culturales-100.csv")

  madrid.filter($"GRATUITO".equalTo("1"))
    .groupBy($"NOMBRE-INSTALACION")
    .agg(count("NOMBRE-INSTALACION").as("count"))
    .sort(desc("count"))
    .limit(1)
    .foreach(rows => println(rows(0) + " with: " + rows(1) + " free events"))

}

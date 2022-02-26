package madrid

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, substring_index}

object Ex3 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  import spark.implicits._

  spark.sparkContext.setLogLevel("ERROR")

  val madrid = spark.read.option("header", "true").option("encoding", "windows-1252").csv("datos/206974-0-agenda-eventos-culturales-100.csv")


  madrid.filter($"DISTRITO-INSTALACION".equalTo("LATINA"))
    .withColumn("TYPE", substring_index($"TIPO", "/", -1))
    .select(col("TYPE"), col("TITULO")).show(false)

}

package madrid

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.month

import scala.language.postfixOps

object Ex5 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  import spark.implicits._

  spark.sparkContext.setLogLevel("ERROR")

  val madrid = spark.read.option("header", "true").option("encoding", "windows-1252").csv("datos/206974-0-agenda-eventos-culturales-100.csv")

  println(madrid.filter(
    $"DISTRITO-INSTALACION".equalTo("LATINA") &&
      month($"FECHA-FIN").geq(4))
    .count())

}

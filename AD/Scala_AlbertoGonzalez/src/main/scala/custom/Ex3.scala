package custom

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

//------------------------------------pokemons legendarios-----------------------------

object Ex3 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  import spark.implicits._

  spark.sparkContext.setLogLevel("ERROR")

  val pokemons = spark.read.option("header", "true").option("encoding", "windows-1252").csv("datos/pokemon.csv")

  pokemons.filter(
    $"Legendary".equalTo("True"))
    .show(false)

}

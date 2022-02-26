package custom

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, count, grouping}

//------------------------------ataque medio de todos los pokemons de fuego de la primera generacion---------------------

object Ex4 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  import spark.implicits._

  spark.sparkContext.setLogLevel("ERROR")

  val pokemons = spark.read.option("header", "true").option("encoding", "windows-1252").csv("datos/pokemon.csv")

  pokemons.filter($"Generation".equalTo("1"))
    .filter($"Type 1".equalTo("Fire"))
    .select(count("Name").as("Number of pokemons measured"), avg($"Attack").as("Attack average"))
    .show(false)

}

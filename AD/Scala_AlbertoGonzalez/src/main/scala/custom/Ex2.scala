package custom

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

//--------------------------pokemons que sean de tipo X & tipo Y----------------------------

object Ex2 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  import spark.implicits._

  spark.sparkContext.setLogLevel("ERROR")

  val pokemons = spark.read.option("header", "true").option("encoding", "windows-1252").csv("datos/pokemon.csv")

  pokemons.filter(
    $"TYPE 1".equalTo("Fire") &&
      $"TYPE 2".equalTo("Flying"))
    .show(false)

}

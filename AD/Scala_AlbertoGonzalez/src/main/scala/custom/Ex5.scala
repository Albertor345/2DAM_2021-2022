package custom

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

//-------------------------------nombre de los pokemons cuya vida sea menor a 120 y ataque mayor a 75, de agua, legendarios y de segunda generacion-------------------------------------

object Ex5 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  import spark.implicits._

  spark.sparkContext.setLogLevel("ERROR")

  val pokemons = spark.read.option("header", "true").option("encoding", "windows-1252").csv("datos/pokemon.csv")

  pokemons.filter($"HP".leq(120))
    .filter($"Attack".geq(75) &&
      ($"Type 1".equalTo("Water") ||
        $"Type 2".equalTo("Water")))
    .filter($"Legendary".equalTo("True") && $"Generation".like("2"))
    .select(col("Name"))
    .show(false)

}

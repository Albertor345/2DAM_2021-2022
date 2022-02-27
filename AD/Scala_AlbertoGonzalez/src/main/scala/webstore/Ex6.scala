package webstore

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

import scala.collection.Map

object Ex6 extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().
    master("local").getOrCreate()

  import spark.implicits._

  spark.sparkContext.setLogLevel("ERROR")

  val customers = spark.read.option("header", "true").option("encoding", "windows-1252").csv("datos/Customers.csv")
  val users = spark.read.option("header", "true").option("encoding", "windows-1252").csv("datos/Users.csv")


  val lookupMap = customers.rdd.map { row =>
    (row.getString(0), (row.getString(1), row.getString(2),row.getString(3)))
  }.collectAsMap()


  def lookup(lookupMap: Map[String, (String, String, String)]) =
    udf((_id: String) => lookupMap.get(_id))


  //This shows those USERS who are not a CUSTOMER, because in my model there cant be any customer who's not an user

  users
    .withColumn("lookup", lookup(lookupMap)($"_id"))
    .withColumn("CustomerName", $"lookup._1")
    .withColumn("phone", $"lookup._2")
    .withColumn("address", $"lookup._3")
    .drop($"lookup")
    .filter($"user_type".equalTo("admin"))
    .show(false)

}

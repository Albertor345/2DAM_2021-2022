name := "Scala_AlbertoGonzalez"

version := "0.1"

scalaVersion := "2.12.11"


val sparkVersion = "3.1.2"
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "com.coxautodata" %% "vegalite4s" % "0.4",
  "org.scalanlp" %% "breeze-viz" % "1.3"
)
// https://mvnrepository.com/artifact/net.liftweb/lift-json
libraryDependencies += "net.liftweb" %% "lift-json" % "3.5.0"


// https://mvnrepository.com/artifact/org.openjfx/javafx-web
libraryDependencies += "org.openjfx" % "javafx-web" % "11"
libraryDependencies += "org.openjfx" % "javafx-controls" % "11"
libraryDependencies += "org.openjfx" % "javafx-fxml" % "11"
libraryDependencies += "org.openjfx" % "javafx-base" % "11"
libraryDependencies += "org.openjfx" % "javafx-swing" % "11"
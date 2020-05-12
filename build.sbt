name := "play-with-zio-demo"
 
version := "0.0.1"
      
lazy val `play-with-zio-demo` = (project in file("."))
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)

scalaVersion := "2.12.11"

libraryDependencies ++= Akka.dependencies

libraryDependencies ++= Logback.dependencies

libraryDependencies ++= Zio.dependencies

libraryDependencies += ws

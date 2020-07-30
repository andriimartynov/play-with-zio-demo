name := "play-with-zio-demo"
 
version := "0.0.2"
      
lazy val `play-with-zio-demo` = (project in file("."))
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)

scalaVersion := "2.12.12"

libraryDependencies ++= Akka.dependencies

libraryDependencies ++= Logback.dependencies

libraryDependencies ++= Zio.dependencies

libraryDependencies += ws

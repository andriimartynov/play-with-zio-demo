import sbt._

object Akka {
  lazy val AKKA_VERSION: String = "2.5.26"

  lazy val dependencies: Seq[ModuleID] = Seq(
    "com.typesafe.akka" %% "akka-slf4j" % AKKA_VERSION
  )
}

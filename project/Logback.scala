import sbt._

object Logback {
  lazy val dependencies: Seq[ModuleID] = Seq(
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
  
}

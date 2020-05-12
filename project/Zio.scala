import sbt._

object Zio {
  lazy val ZIO_VERSION = "1.0.0-RC18-2"

  lazy val dependencies: Seq[ModuleID] = Seq(
    "dev.zio" %% "zio" % ZIO_VERSION
  )

}

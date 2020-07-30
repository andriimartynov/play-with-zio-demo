logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.7.4")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.0")
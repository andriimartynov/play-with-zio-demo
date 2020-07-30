package com.github.andriimartynov.play_with_zio_demo.configuration

import play.api.Configuration
import zio.Has

object AppConfiguration {
  type HasAppConfiguration = Has[AppConfiguration]
}

class AppConfiguration(
  config: Configuration
) extends SearchConfiguration {

  val apiKey: String =
    config.get[String](SearchConfiguration.apiKey)

  val searchEngineId: String =
    config.get[String](SearchConfiguration.searchEngineId)

}

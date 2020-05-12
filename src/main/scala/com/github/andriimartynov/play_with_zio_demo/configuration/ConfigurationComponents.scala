package com.github.andriimartynov.play_with_zio_demo.configuration

import com.github.andriimartynov.play_with_zio_demo.configuration.AppConfiguration.HasAppConfiguration
import play.api.Configuration
import zio.{Layer, ZLayer}

trait ConfigurationComponents {
  def configuration: Configuration

  private lazy val appConfiguration: AppConfiguration = new AppConfiguration(configuration)

  lazy val configurationLayer: Layer[Nothing, HasAppConfiguration] =
    ZLayer.fromFunction(_ => appConfiguration)

}

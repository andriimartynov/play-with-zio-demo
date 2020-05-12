package com.github.andriimartynov.play_with_zio_demo.configuration

object SearchConfiguration {
  val apiKey: String = "play-with-zio-demo.search.api-key"

  val searchEngineId: String = "play-with-zio-demo.search.search-engine-id"

}

trait SearchConfiguration {
  def apiKey: String

  def searchEngineId: String

}

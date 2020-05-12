package com.github.andriimartynov.play_with_zio_demo.search

import com.github.andriimartynov.play_with_zio_demo.configuration.AppConfiguration.HasAppConfiguration
import com.github.andriimartynov.play_with_zio_demo.http.HttpClientService.HttpClientService
import com.github.andriimartynov.play_with_zio_demo.search.SearchService.SearchService
import zio.Layer

trait SearchComponents {

  def configurationLayer: Layer[Nothing, HasAppConfiguration]

  def httpClientLayer: Layer[Nothing, HttpClientService]

  lazy val searchLayer: Layer[Nothing, SearchService] =
    (configurationLayer ++ httpClientLayer) >>> SearchService.http

}

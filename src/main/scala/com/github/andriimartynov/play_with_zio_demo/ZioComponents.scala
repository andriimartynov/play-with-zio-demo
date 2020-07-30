package com.github.andriimartynov.play_with_zio_demo

import com.github.andriimartynov.play_with_zio_demo.ZioComponents.ZEnv
import com.github.andriimartynov.play_with_zio_demo.http.HttpClientService.HttpClientService
import com.github.andriimartynov.play_with_zio_demo.search.SearchService.SearchService
import zio.internal.Platform
import zio.{ Layer, Runtime }

import scala.concurrent.ExecutionContext

object ZioComponents {
  type ZEnv = HttpClientService with SearchService
}

trait ZioComponents {

  def ec: ExecutionContext

  def httpClientLayer: Layer[Nothing, HttpClientService]

  def searchLayer: Layer[Nothing, SearchService]

  private lazy val layer: Layer[Nothing, ZEnv] =
    httpClientLayer ++ searchLayer

  implicit lazy val runtime: Runtime[ZEnv] =
    Runtime.unsafeFromLayer(layer, Platform.fromExecutionContext(ec))
}

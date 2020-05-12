package com.github.andriimartynov.play_with_zio_demo.http

import com.github.andriimartynov.play_with_zio_demo.http.HttpClientService.HttpClientService
import play.api.libs.ws.WSClient
import play.api.libs.ws.ahc.AhcWSComponents
import zio.{Has, Layer, ZLayer}

trait HttpComponents { this: AhcWSComponents =>

  private lazy val wsClientLayer: Layer[Nothing, Has[WSClient]] =
    ZLayer.fromFunction(_ => wsClient)

  lazy val httpClientLayer: Layer[Nothing, HttpClientService] =
    wsClientLayer >>> HttpClientService.ws

}

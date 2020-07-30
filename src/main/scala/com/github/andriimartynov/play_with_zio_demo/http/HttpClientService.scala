package com.github.andriimartynov.play_with_zio_demo.http

import play.api.libs.ws.WSClient
import zio._

object HttpClientService {

  type HttpClientService = Has[HttpClientService.Service]

  trait Service extends Serializable {
    def get(url: String): Task[String]
  }

  object Service {
    def ws(client: WSClient): Service =
      new Service {
        def get(url: String): Task[String] =
          Task.fromFuture { ec =>
            client.url(url).get().map(_.body)(ec)
          }
      }
  }

  lazy val ws: ZLayer[Has[WSClient], Nothing, HttpClientService] =
    ZLayer.fromFunction(hasClient => Service.ws(hasClient.get[WSClient]))

  def get(url: String): RIO[HttpClientService, String] =
    ZIO.accessM(_.get get url)

}

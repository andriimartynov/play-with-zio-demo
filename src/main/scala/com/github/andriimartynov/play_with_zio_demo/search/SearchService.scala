package com.github.andriimartynov.play_with_zio_demo.search

import com.github.andriimartynov.play_with_zio_demo.configuration.AppConfiguration.HasAppConfiguration
import com.github.andriimartynov.play_with_zio_demo.configuration.{AppConfiguration, SearchConfiguration}
import com.github.andriimartynov.play_with_zio_demo.http.HttpClientService
import com.github.andriimartynov.play_with_zio_demo.http.HttpClientService.HttpClientService
import zio._

object SearchService {

  type SearchService = Has[SearchService.Service]

  trait Service extends Serializable {
    def search(q: String): Task[String]
  }

  object Service {
    def http(config: SearchConfiguration, service: HttpClientService.Service): Service = new Service {
      def search(q: String): Task[String] =
        service
          .get(s"""https://www.googleapis.com/customsearch/v1?key=${config.apiKey}&cx=${config.searchEngineId}:omuauf_lfve&q=$q""")
    }
  }

  lazy val http: ZLayer[HasAppConfiguration with HttpClientService, Nothing, SearchService] =
    ZLayer.fromFunction(hasClient =>
      Service.http(
        hasClient.get[AppConfiguration],
        hasClient.get[HttpClientService.Service]))

  def search(q: String): RIO[SearchService, String] =
    ZIO.accessM(_.get search q)

}

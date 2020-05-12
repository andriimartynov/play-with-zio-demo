package com.github.andriimartynov.play_with_zio_demo

import com.github.andriimartynov.play_with_zio_demo.configuration.ConfigurationComponents
import com.github.andriimartynov.play_with_zio_demo.http.HttpComponents
import com.github.andriimartynov.play_with_zio_demo.search.SearchComponents
import play.api.ApplicationLoader.Context
import play.api.i18n.I18nComponents
import play.api.libs.ws.ahc.AhcWSComponents
import play.api.routing.Router
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext, LoggerConfigurator}
import play.filters.HttpFiltersComponents
import router.Routes
import zio.Runtime
import zio.internal.Platform

import scala.concurrent.ExecutionContext

class AppLoader extends ApplicationLoader {
  def load(context: Context): Application = {
    new ApplicationComponents(context).application
  }
}

class ApplicationComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
    with AhcWSComponents
    with ConfigurationComponents
    with HttpComponents
    with I18nComponents
    with HttpFiltersComponents
    with SearchComponents {

  LoggerConfigurator(context.environment.classLoader).foreach {
    _.configure(context.environment, context.initialConfiguration, Map.empty)
  }

  implicit val ec: ExecutionContext = actorSystem.dispatcher

  implicit val runtime: Runtime[Null] = Runtime(null, Platform.fromExecutionContext(ec))

  implicit val layer = httpClientLayer ++ searchLayer

  lazy val homeController = new controllers.Application(controllerComponents)

  lazy val router: Router = new Routes(httpErrorHandler, homeController)
}
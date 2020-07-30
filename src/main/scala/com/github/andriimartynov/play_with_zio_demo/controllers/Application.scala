package com.github.andriimartynov.play_with_zio_demo.controllers

import play.api.mvc._
import com.github.andriimartynov.play_with_zio_demo.search.SearchService
import com.github.andriimartynov.play_with_zio_demo.search.SearchService.SearchService
import zio.mvc._
import zio.{ Runtime, Task }

class Application(cc: ControllerComponents)(implicit
  runtime: Runtime[SearchService]
) extends AbstractController(cc) {

  def index: Action[AnyContent] =
    Action.task { _ =>
      Task.fromFunction(_ => Ok("Your new application is ready"))
    }

  def search(q: String): Action[AnyContent] =
    Action.rio { _ =>
      SearchService.search(q).map(s => Ok(s))
    }

}

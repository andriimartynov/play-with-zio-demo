package zio

import play.api.mvc.{Action, ActionBuilder, BodyParser, Result}

import scala.concurrent.Future

package object mvc {

  implicit class ActionBuilderOps[+R[_], B](
                                             actionBuilder: ActionBuilder[R, B]
                                           ) {

    def task(
              zioActionBody: R[B] => Task[Result]
            )(
              implicit runtime: Runtime[_]
            ): Action[B] = actionBuilder.async { request =>
      zioActionBody(request)
    }

    def task[A](
                 bp: BodyParser[A]
               )(
                 zioActionBody: R[A] => Task[Result]
               )(
                 implicit runtime: Runtime[_]
               ): Action[A] = actionBuilder(bp).async { request =>
      zioActionBody(request)
    }

    def rio[D](
                zioActionBody: R[B] => RIO[Has[D], Result]
              )(
                implicit runtime: Runtime[_],
                layer: Layer[Nothing, Has[D]]
              ): Action[B] = actionBuilder.async { request =>
      zioActionBody(request)
    }

    def rio[D, A](
                   bp: BodyParser[A]
                 )(
                   zioActionBody: R[A] => RIO[Has[D], Result]
                 )(
                   implicit runtime: Runtime[_],
                   layer: Layer[Nothing, Has[D]]
                 ): Action[A] = actionBuilder(bp).async { request =>
      zioActionBody(request)
    }
  }

  implicit private def unsafeRunToFuture[D](
                                     rio: RIO[Has[D], Result]
                                   )(
                                     implicit runtime: Runtime[_],
                                     layer: Layer[Nothing, Has[D]]
                                   ): Future[Result] =
    rio.provideLayer(layer)

  implicit private def unsafeRunToFuture(
                                          task: Task[Result]
                                        )(
                                          implicit runtime: Runtime[_]
                                        ): Future[Result] =
    runtime.unsafeRunToFuture(task)

}

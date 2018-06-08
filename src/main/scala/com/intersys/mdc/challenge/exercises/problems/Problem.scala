package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.{Route, StandardRoute}
import akka.http.scaladsl.server.Directives

trait Problem extends Directives with JsonSupport {

  def htmlResponse(response: String): StandardRoute =
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, response))

  def jsonResponse(response: String): StandardRoute =
    complete(HttpEntity(ContentTypes.`application/json`, response))

  def badRequestResponse: StandardRoute =
    complete(StatusCodes.BadRequest)

  def solution: Route
}

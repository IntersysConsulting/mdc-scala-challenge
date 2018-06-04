package com.intersys.mdc.challenge

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}

object Server extends Context {
 val route: Route = pathPrefix("hello") {
  pathEnd {
   complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Hello World!</h1>"))
  }
 }

 def main(args: Array[String]): Unit = {
  Http().bindAndHandle(route, Settings.Http.host, Settings.Http.port)
 }
}

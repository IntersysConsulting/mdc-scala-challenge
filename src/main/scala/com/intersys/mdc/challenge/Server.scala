package com.intersys.mdc.challenge

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route

import com.intersys.mdc.challenge.exercises.Exercises

object Server extends Context {
 val route: Route = Exercises.route

 def main(args: Array[String]): Unit = {
  Http().bindAndHandle(route, Settings.Http.host, Settings.Http.port)
 }
}

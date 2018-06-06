package com.intersys.mdc.challenge.exercises

import com.intersys.mdc.challenge.exercises.problems._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

case object Exercises {
  val route: Route = pathPrefix("problems") {
    Example.solution ~
    Problem1.solution ~
    Problem2.solution ~
    Problem3.solution ~
    Problem4.solution ~
    Problem5.solution
  }
}

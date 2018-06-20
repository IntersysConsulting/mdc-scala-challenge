package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route

case object Problem3 extends Problem {

  /**
    * Recursive Pascal Triangle
    * Description:
    * There are many ways of implementing a pascal triangle. In this particular exercise, the goal is to provide a
    * recursive implementation. You need to research the nature of the pascal triangle in
    * order to code the solution. Moreover, you need to add the get verb for this endpoint (see prev. exercises).
    *
    * Hints:
    * - Use the htmlResponse(response: String) function instead of complete(_). For further information on the htmlResponse
    * function see the Problem trait in the Problem.scala file.
    * - Build the triangle as a string using <br> in order to emulate linebreaks.
    *
    * Key Points:
    * > Use a recursive function.
    * > The size will always be a positive integer.
    *
    * Example:
    * Get Request: /problems/3?size=3
    * Response: 1<br>1 1<br>1 2 1<br>1 3 3 1
    */
  case class pascal(n: Int)

  def triangle(lev: Int): List[Int] = lev match {
      case 0 => List(1)
      case l: Int => List(1) ::: triangle(l - 1).zip(triangle(l - 1).tail).map { case (a, b) => a + b } ::: List(1)
    }


  //val level = (0 to size).toList.map(triangle)
  //val levelString = level.foldLeft("")((chain, elements) => chain + elements.mkString(" ") + "<br>")

  val solution: Route = path("3") {
    get {
      parameters('size.as[Int]) {
        (size) => {


          val level = (0 to size).toList.map(triangle)
          val levelString = level.map(_.mkString(" ")).mkString("<br>")


          htmlResponse(levelString)

        }

      }
    }

  }
}

package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route

import scala.collection.mutable.ArrayBuilder

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

  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  def pascalTriangle(size: Int): String = {
    var outer = new ArrayBuilder.ofRef[Array[Int]]
    for (row <- 0 to size) {
      val inner = new ArrayBuilder.ofInt
      for (col <- 0 to row)
        inner += pascal(col, row)
      outer += inner.result
    }
    outer.result.map(_.mkString("", " ", "")).mkString("", "<br>", "")
  }

  val solution: Route = path("3") {
    // <---- Your code starts here. --->
    get {
      parameters('size.as[Int]) {
        size => complete(pascalTriangle(size))
        //Emmanuel Vallejo: On exersices directives it says: 1 point for completing the endpoint as requested (using htmlResponse).
        //no htmlResponse used on this exersice
      }
    }
    // <---- Your code ends  here. ---->
  }

}

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

  val solution: Route = path("3") {
    // <---- Your code starts here. --->
    get {
      parameters('size.as[Int]) {
        size => {
          def pascalTriangle(size: Int, line: Int = 0): String = {
            def elem(i: Int, j: Int): Int =
              if (j >= i || j == 0) 1 else elem(i-1, j-1) + elem(i-1, j)
            val stringLine = (0 to line) map {elem(line, _)} mkString " "
            if (line < size) stringLine + "<br>" + pascalTriangle(size, line+1)
            else stringLine
          }
          val challengeResponse: String = pascalTriangle(size)
          htmlResponse(challengeResponse)
        }
      }
    }
    // <---- Your code ends  here. ---->
  }

}

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

  var x = (value: Int) => {
    var retorno = "";
    def pascal(c: Int, r: Int): Int = if (c == r || c == 0) 1 else pascal(c - 1, r - 1) + pascal(c, r - 1)

    for (row <- 0 to value) {
      for (col <- 0 to row) {
        retorno = retorno + pascal(col, row).toString()
        if (col == row && col != value) {
          retorno = retorno + "<br>"
        }
        else if (col!=row && col!=value)
          (retorno = retorno + " ")
      }

    }
    retorno
  }



  val solution: Route = path("3") {
    // <---- Your code starts here. --->
    get {

      parameters('size.as[String]) {

        size => {
          val challengeSolution: String = {
            val retorno =  x(size.toInt)
            retorno
          }
          htmlResponse(challengeSolution)
          //complete(challengeSolution)
        }
        // <---- Your code starts here. --->
        /// ???
        // <---- Your code ends  here. ---->
      }

    }
    // <---- Your code ends  here. ---->
  }

}

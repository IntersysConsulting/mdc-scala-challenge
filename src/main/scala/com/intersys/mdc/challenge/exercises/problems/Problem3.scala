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
    * Response: 1<br>1 1<br>1 2 1<br>1 3 3 1
    */
  //case class PascalTri(response: String)

  val solution: Route = path("3") {
    get {
      parameters('size.as[Int]) {
        size => {
          val challengeResponse: String = {

            def pascal(level: Int): List[Int] = {
              level match {
                case 0 => List(1)
                case n: Int => List(1) ::: pascal(n - 1).zip(pascal(n - 1).tail).map { case (a, b) => a + b } ::: List(1)
              }
            }

            val pre = (0 to size).toList.map(pascal)
            val pre2 = pre.foldLeft("")((acc,e) => acc + e.mkString(" ") + "<br>")

            val result = pre2.take(pre2.length-4)

            result

          }
          htmlResponse(challengeResponse)
        }
      }
    }
  }
}

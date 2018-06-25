package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route
import com.intersys.mdc.challenge.exercises.problems.Problem1.{MixedString, complete, get, parameters}

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
    get {
      parameters('size.as[Int]) {
        (size) => {
          var a = "1<br>"
          val next = (l :List[Int]) => {
            val rn = (1::((l,l.tail).zipped.map(_ + _):+1))
            a++=(rn.mkString(" ")++("<br>"))
            rn
          }

          Iterator.iterate(List(1))(next).drop(size).next
          //rintln(a)
          //def times[A](n:Int, f:A=>A, a:A):A = Iterator.iterate(a)(f).drop(n).next
          //def times[A](n:Int, f:A=>A, a:A):A = if (n==0) a else ntimes(n-1, f, f(a))

          htmlResponse(a.substring(0,a.lastIndexOf("<br>")))
        }
      }
    }
  }
}


package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route
import scala.annotation.tailrec

case object Problem3 extends Problem {

  
    /* Recursive Pascal Triangle
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

  def getPascalTriangle(size: Long): String = getPascalTriangleAcc(size, Seq[Long](), "")

  @tailrec
  private def getPascalTriangleAcc(left: Long, prev: Seq[Long], acc: String): String = {
    val curr: Seq[Long] = prev.size match {
      case 0 => Seq(1L)
      case 1 => Seq(1L, 1L)
      case _ => Seq(1L) ++ prev.sliding(2).toList.map {case Seq(n1, n2) => n1 + n2} ++ Seq(1L)
    }
    val accString = acc + curr.mkString(" ")

    left match {
      case 0 => ""
      case 1 => accString
      case _ => getPascalTriangleAcc(left - 1, curr, accString + "<br>")
    }
  }

  val solution: Route = path("3") {
    get {
      parameters('size.as[Long]) { size => htmlResponse(getPascalTriangle(size + 1)) }
    }
  }

}

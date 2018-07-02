package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route

import scala.annotation.tailrec
import scala.collection.immutable.ListMap

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

  def currentLevel(lastLevel: List[Int]): List[Int] = {
    var nextLevel = List(1)
    var i = 0

    for(i <- 0 to (lastLevel.length-2)) {
      nextLevel = nextLevel :+ (lastLevel(i) + lastLevel(i+1))
    }

    nextLevel :+ 1
  }

  def pascalTriangle(size: Int): Map[Int, List[Int]] = {
    @tailrec
    def recPascalTriangle(size: Int, current: Int, triangle: Map[Int, List[Int]]): Map[Int, List[Int]] = {
      var newtriangle = triangle + (current -> currentLevel(triangle(current-1)))

      if (size != current) {
        recPascalTriangle(size, current + 1, newtriangle)
      }
      else {
        newtriangle
      }
    }

    if(size == 0) Map(0 -> List(1)) else recPascalTriangle(size, 1, Map(0 -> List(1)))
  }

  val solution: Route = path("3") {
    get {
      parameters('size.as[Int]) {
        size => {
          var challengeResponse: String = {
            ListMap(pascalTriangle(size).toSeq.sortBy(_._1):_*).values.map(_.mkString(" ")).mkString("<br>")
          }
          htmlResponse(challengeResponse)
        }
      }
    }
  }

}

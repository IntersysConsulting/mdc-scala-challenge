package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route
import com.intersys.mdc.challenge.exercises.problems.Problem2.{SuperDigit, complete, get, parameters, superDigit}

import scala.annotation.tailrec

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
  def factorial (n:Int):BigInt = {
    @tailrec
    def fact(i: BigInt, accumulator: BigInt): BigInt = {
      if (i <= 1) accumulator
      else fact(i - 1, i * accumulator)
    }
    fact(n,1)
  }

  def combinations(m:Int,n:Int):BigInt={
    factorial(m)./(factorial(n)*factorial(m-n))
  }

  def pascalLine(line:Int):String={
    0.to(line).toArray.map(x=>String.valueOf(combinations(line,x))).mkString(" ")
  }

  def pascalTriangle(level:Int):String={
    0.to(level).toArray.map(x=>pascalLine(x)).mkString("<br>")
  }
  val solution: Route = path("3") {
    get {
      parameters('size.as[Int]){
        (size)=>{
          this.htmlResponse(pascalTriangle(size))
        }
      }
    }
  }

}

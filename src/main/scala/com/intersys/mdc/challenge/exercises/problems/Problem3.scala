package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route

case object Problem3 extends Problem {

  def factorial(n: Long): Long = n match {
    case 0 => 1
    case _ => n * factorial(n-1)
  }

  def tPascal(depth:Int,row:Int,column:Int,triangulo:String): String = {

    if(column < row && row < depth)
      triangulo + (factorial(row) / (factorial(column) * factorial(row-column))) + " " + tPascal(depth,row,column+1,triangulo)

    else if(column == row && row < depth )
      triangulo + (factorial(row) / (factorial(column) * factorial(row-column))) + "<br>" + "\n" + tPascal(depth,row+1,0,triangulo)

    else if(column < row && row == depth )
      triangulo + (factorial(row) / (factorial(column) * factorial(row-column))) + " " + tPascal(depth,row,column+1,triangulo)

    else if(column == row && row == depth )
      triangulo + (factorial(row) / (factorial(column) * factorial(row-column)))

    else return triangulo
  }

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
      parameter('size.as[Int]){
        (size) => {
          htmlResponse(response = tPascal(size,0,0,"").replace("\n",""))
        }
      }
    }
  }

}

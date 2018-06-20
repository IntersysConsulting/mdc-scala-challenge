package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route

import scala.annotation.tailrec

case object Problem2 extends Problem {

  /**
    * Super Digit
    * Description:
    * We define the superdigit of an integer x as a number of one digit resulting of the recursive sum of
    * the digits of x (e.g. the superdigit of 9875 is (9+8+7+5 = 29) => (9+2 = 11) => (1+1=2) => 2).
    * This endpoint is expecting a get request with two integers: n, k. Your goal is to return the superdigit
    * of a number P defined as the number n concatenated k-times (e.g. n=123, k=3, then P=123123123).
    * Complete the get-request extracting the parameters (see previous exercises if needed). Don't forget to add
    * the following code at the end of the expression: complete(challengeResponse)
    * where challengeResponse is a SuperDigit object.
    *
    * Key Points:
    * > Complete the get endpoint. Use the SuperDigit case class.
    * > Use recursion to solve the problem.
    * > n and k will always be positive integers.
    *
    * Example:
    * Get request: /problems/2?n=123&k=3
    * Response: {"n":123,"k":3,"value":9}
    */

  case class SuperDigit(n: Long, k: Long, value: Long)

  def recSuperDigit(p: Long): Long = {
    @tailrec
    def recSum(p: Long): Long = {
      if(p > 9) recSum(p.toString.map(_.asDigit).sum) else p
    }

    if(p > 9) recSum(p) else p
  }

  val solution: Route = path("2") {
    get {
      parameters('n.as[String], 'k.as[Int]) {
        (n, k) => {
          val challengeResponse: SuperDigit = SuperDigit(n.toLong, k.toLong, recSuperDigit(recSuperDigit(n.toLong)*recSuperDigit(k.toLong)))

          complete(challengeResponse)
        }
      }
    }
  }

}

package com.intersys.mdc.challenge

import com.intersys.mdc.challenge.exercises.problems.JsonSupport
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

import com.intersys.mdc.challenge.exercises.problems.Example.FibonacciResult
//import com.intersys.mdc.challenge.exercises.problems.Problem1.MixedString
//import com.intersys.mdc.challenge.exercises.problems.Problem2.SuperDigit
//import com.intersys.mdc.challenge.exercises.problems.Problem4.Calculation
//import com.intersys.mdc.challenge.exercises.problems.Problem5.IntListResult

class ServerSpecs extends WordSpec with Matchers with ScalatestRouteTest with JsonSupport {

  "(0) The example problem" should {
    "return a correct answer for size=5" in {
      Get("/problems/example?size=5") ~> Server.route ~> check {
        val response = responseAs[String]
        val fibonacciResult = FibonacciResult(List(0,1,1,2,3,5))
        response shouldBe fibonacciResult.toJson.toString
      }
    }
    "return a correct answer for size=21" in {
      Get("/problems/example?size=21") ~> Server.route ~> check {
        val response = responseAs[String]
        val fibonacciResult = FibonacciResult(List(0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,10946))
        response shouldBe fibonacciResult.toJson.toString
      }
    }
  }

  /**
  "(1) The first problem" should {
    "return a correct answer for firstWord=intersys, secondWord=consulting" in {
      Get("/problems/1?firstWord=intersys&secondWord=consulting") ~> Server.route ~> check {
        val response = responseAs[String]
        val mixedString = MixedString("intersys", "consulting", "icnotnesruslytsing")
        response shouldBe mixedString.toJson.toString
      }
    }
    "return a correct answer for firstWord=qwerty, secondWord=asdfgh" in {
      Get("/problems/1?firstWord=qwerty&secondWord=asdfgh") ~> Server.route ~> check {
        val response = responseAs[String]
        val mixedString = MixedString("qwerty", "asdfgh", "qawsedrftgyh")
        response shouldBe mixedString.toJson.toString
      }
    }
  }

  "(2) The second problem" should {
    "return a correct answer for n=148, k=3" in {
      Get("/problems/2?n=148&k=3") ~> Server.route ~> check {
        val response = responseAs[String]
        val superDigit = SuperDigit(148, 3, 3)
        response shouldBe superDigit.toJson.toString
      }
    }
    "return a correct answer for n=123456789, k=987" in {
      Get("/problems/2?n=123456789&k=987") ~> Server.route ~> check {
        val response = responseAs[String]
        val superDigit = SuperDigit(123456789, 987, 9)
        response shouldBe superDigit.toJson.toString
      }
    }
    "return a correct answer for n=98765432123456789, k=73194628" in {
      Get("/problems/2?n=98765432123456789&k=73194628") ~> Server.route ~> check {
        val response = responseAs[String]
        val superDigit = SuperDigit(98765432123456789L, 73194628L, 5)
        response shouldBe superDigit.toJson.toString
      }
    }
  }

  "(3) The third problem" should {
    "return a correct answer for size=3" in {
      Get("/problems/3?size=3") ~> Server.route ~> check {
        val response = responseAs[String]
        val pascalTriangle =
          """
            |1<br>
            |1 1<br>
            |1 2 1<br>
            |1 3 3 1
          """.stripMargin
        response shouldBe pascalTriangle.replaceAll("\n", "").trim
      }
    }
    "return a correct answer for size=15" in {
      Get("/problems/3?size=15") ~> Server.route ~> check {
        val response = responseAs[String]
        val pascalTriangle =
          """
            |1<br>
            |1 1<br>
            |1 2 1<br>
            |1 3 3 1<br>
            |1 4 6 4 1<br>
            |1 5 10 10 5 1<br>
            |1 6 15 20 15 6 1<br>
            |1 7 21 35 35 21 7 1<br>
            |1 8 28 56 70 56 28 8 1<br>
            |1 9 36 84 126 126 84 36 9 1<br>
            |1 10 45 120 210 252 210 120 45 10 1<br>
            |1 11 55 165 330 462 462 330 165 55 11 1<br>
            |1 12 66 220 495 792 924 792 495 220 66 12 1<br>
            |1 13 78 286 715 1287 1716 1716 1287 715 286 78 13 1<br>
            |1 14 91 364 1001 2002 3003 3432 3003 2002 1001 364 91 14 1<br>
            |1 15 105 455 1365 3003 5005 6435 6435 5005 3003 1365 455 105 15 1
          """.stripMargin
        response shouldBe pascalTriangle.replaceAll("\n", "").trim
      }
    }
  }

  "(4) The fourth problem" should {
    "return a correct answer for a=73, b=19, operation=sum" in  {
      Get("/problems/4?a=73&b=19&operation=sum") ~> Server.route ~> check {
        val response = responseAs[String]
        val calculation = Calculation("sum", 73, 19, 92)
        response shouldBe calculation.toJson.toString
      }
    }

    "return a bad request error for a=73, b=19, operation=um" in {
      Get("/problems/4?a=73&b=19&operation=um") ~> Server.route ~> check {
        val response = responseAs[String]
        val calculation = "The request contains bad syntax or cannot be fulfilled."
        response shouldBe calculation
      }
    }

    "retrun a bad request error for a=1, b=0, operation=division" in {
      Get("/problems/4?a=1&b=0&operation=division") ~> Server.route ~> check {
        val response = responseAs[String]
        val calculation = "The request contains bad syntax or cannot be fulfilled."
        response shouldBe calculation
      }
    }
  }

  "(5) The fifth problem" should {
    "return a correct answer for a=10, b=7, c=32" in {
      Get("/problems/5?a=10&b=7&c=32") ~> Server.route ~> check {
        val response = responseAs[String]
        val intListResult = IntListResult(3, 12, 2240, "10 -> 7 -> 32 -> ")
        response shouldBe intListResult.toJson.toString
      }
    }
    "return a correct answer for a=578, b=679, c=342" in {
      Get("/problems/5?a=578&b=679&c=342") ~> Server.route ~> check {
        val response = responseAs[String]
        val intListResult = IntListResult(3, 580, 134222004, "578 -> 679 -> 342 -> ")
        response shouldBe intListResult.toJson.toString
      }
    }
  }
    **/
}

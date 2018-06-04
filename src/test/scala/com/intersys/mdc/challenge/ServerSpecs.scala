package com.intersys.mdc.challenge

import akka.http.scaladsl.testkit.ScalatestRouteTest
import  org.scalatest.{Matchers, WordSpec}

class ServerSpecs extends WordSpec with Matchers with ScalatestRouteTest {
  "The Service" should {
    "Return the hello world string" in {
      Get("/hello") ~> Server.route ~> check {
        val response   = responseAs[String]
        val helloWorld = "<h1>Hello World!</h1>"
        response shouldBe helloWorld
      }
    }
  }
}

package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.intersys.mdc.challenge.exercises.problems.Example.FibonacciResult
import spray.json.DefaultJsonProtocol
import com.intersys.mdc.challenge.exercises.problems.Problem1.MixedString
//import com.intersys.mdc.challenge.exercises.problems.Problem2.SuperDigit
//import com.intersys.mdc.challenge.exercises.problems.Problem4.Calculation
//import com.intersys.mdc.challenge.exercises.problems.Problem5.IntListResult

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val jsonFormatFibonacciResults = jsonFormat1(FibonacciResult)
  implicit val jsonFormatMixedString = jsonFormat3(MixedString)
  //implicit val jsonFormatSuperDigit = jsonFormat3(SuperDigit)
  //implicit val jsonFormatCalculation = jsonFormat4(Calculation)
  //implicit val jsonFormatIntListResult = jsonFormat4(IntListResult)
}

package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route

case object Problem1 extends Problem {


  implicit class StringOps(string: String) {
    def getStr(i: Int): String = scala.util.Try(string.charAt(i).toString).toOption.getOrElse("")
  }

  final case class MixedString(first: String, second: String, mixed: String)


  /**
    * Mixing Strings
    * Description:
    * This endpoint expects a get request with two strings as parameters (i.e. firstWord, secondWord).
    * Your goal is to take both words and create a third one by intercalating the letters
    * (e.g. "ABC" and "123" should be "A1B2C3").
    * - Note that the words might have different length.
    * - Your expression should evaluate to a MixedString object.
    * - Try to use scala's collections and methods (hint: map).
    *
    * Key Points:
    * > The code required to parse the request/response is provided to you.
    *   - For more information on the AKKA-HTTP API see: <https://doc.akka.io/docs/akka-http/current/server-side/index.html>
    * > You should complete the code in the block expression for the challengeSolution val.
    * > The "StringOps implicit class" is commonly known as a type class.
    *   - For more information on type classes see (advanced): <https://blog.scalac.io/2017/04/19/typeclasses-in-scala.html>
    * > The "final case class MixedString" must contain the resulting mixed string in the filed "mixed".
    *   - For more information about case classes see: <https://docs.scala-lang.org/tour/case-classes.html>
    *
    * Example:
    * Get request: /problems/1?firstWord=abcdef&secondWord=1234
    * Response: {"first":"abcdef","second":"1234","mixed":"a1b2c3d4ef"}
    */

 /* var mixure = (inputOne:String, inputTwo:String) => {

    val lower = (low:String, high:String) =>{
      var newString=""
      for (i <- 0 until low.length()){
        newString = newString +  low.charAt(i).toString() + high.charAt(i).toString()
      }

      for (i <- low.length until high.length() )//if i >= inputOneLength )
      {  newString = newString + high.charAt(i).toString()}
      newString

    }

    val higher = (low:String, high:String) =>{
      var newString=""
      for (i <- 0 until high.length()){
        newString = newString + low.charAt(i).toString() + high.charAt(i).toString()
      }

      for (i <- high.length until low.length() )//if i >= inputOneLength )
      {  newString = newString + low.charAt(i).toString() }
      newString

    }

    val equal = (valueOne: String, valueTwo:String)=>{
      var newString=""
      for (i <- 0 until valueOne.length()){
        newString = newString +  valueOne.charAt(i).toString() + valueTwo.charAt(i).toString() }
      newString

    }

    if (inputOne.length() < inputTwo.length)
    {
      lower(inputOne, inputTwo)

    }

    else if (inputOne.length() > inputTwo.length)
    {
      higher(inputOne, inputTwo)

    }
    else
      equal(inputOne, inputTwo)
  }*/
  /*def mixure(inputOne:String, inputTwo:String):String=
  {
    // var inputOne : String = "Holaw"
    //var inputTwo:String = "1234"

    var newString = "";
    if (inputOne.length() < inputTwo.length)
    {
      for (i <- 0 until inputOne.length()){
        newString = newString +  inputOne.charAt(i).toString() + inputTwo.charAt(i).toString()
      }

      for (i <- inputOne.length until inputTwo.length() )//if i >= inputOneLength )
      {  newString = newString + inputTwo.charAt(i).toString() }
      println(newString)
    }

    else if (inputOne.length() == inputTwo.length)
    {
      for (i <- 0 until inputOne.length()){
        newString = newString +  inputOne.charAt(i).toString() + inputTwo.charAt(i).toString()
      }
      println(newString)

    }

    else if (inputOne.length() > inputTwo.length)
    {
      for (i <- 0 until inputTwo.length()){
        newString = newString +  inputOne.charAt(i).toString() + inputTwo.charAt(i).toString
      }

      for (i <- inputTwo.length until inputOne.length() )//if i >= inputOneLength )
      {  newString = newString + inputOne.charAt(i).toString }
      println(newString)
    }
    newString
  }*/


  val solution: Route = path("1") {
    get {
      parameters('firstWord.as[String], 'secondWord.as[String]) {
        (first, second) => {
          val challengeSolution: MixedString = {
            // <---- Your code starts here. --->
           // var mix = mixure(first, second)
            var mix = (first.zipAll(second,"","").map{ case (val1,val2) => s"$val1$val2" }).mkString
            //var mixure = MixedString("123", "ABC", "1A2B3C")
            var abc = MixedString(first, second, mix)
            abc
            // <---- Your code starts here. --->
            //var abc = MixedString("123", "ABC", "1A2B3C")
            // abc

            // <---- Your code ends  here. ---->
            // <---- Your code ends  here. ---->
          }
          complete(challengeSolution)
        }
      }
    }
  }

}

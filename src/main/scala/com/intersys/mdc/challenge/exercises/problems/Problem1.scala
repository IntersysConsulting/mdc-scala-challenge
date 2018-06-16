package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route

import scala.annotation.tailrec

case object Problem1 extends Problem {

  implicit class ListExtraOps[A](as: List[A]) {
    def interleave(that: List[A]): List[A] = {
      @tailrec def loop(xs: List[A], ys: List[A], acc: List[A]): List[A] = xs match {
        case h :: t =>
          loop(ys, t, h :: acc)
        case Nil      =>
          acc reverse_::: ys
      }
      loop(as, that, List.empty[A])
    }
  }

  implicit class StringExtraOps(s: String) {
    def interleave(that: String): String =
      StringBuilder.newBuilder.appendAll(s.toList interleave that.toList).result
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

  val solution: Route = path("1") {
    get {
      parameters('firstWord.as[String], 'secondWord.as[String]) {
        (first, second) =>
          val mixed = first interleave second
          complete(MixedString(first, second, mixed))
      }
    }
  }

}

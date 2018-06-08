package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route
import scala.annotation.tailrec

case object Example extends Problem {

  /**
    * Fibonacci
    * Description:
    * This is an example problem. In a real problem the description should provide a detailed explanation of
    * the activity. In this case, the problem would consist in providing a recursive fibonacci implementation for the endpoint.
    * As it can be seen, the functions, classes and endpoint code was provided. You can expect some of these elements
    * to be provided in future exercises as well.
    *
    * Key points:
    * > Follow the comments in order to understand the relevant parts of these code.
    * > Running "sbt test" in the console should pass the tests for this example.
    * > Running "sbt run" should initialize the app. You can make your own get requests at: http://127.0.0.1:8080/problems/example?size=15
    *
    * Examples:
    * This section will provide you with at least one get request example and the expected response.
    */

  // This class is used as a wrapper to serialize the result.
  // See the JsonSupport trait for the json implementation.
  final case class FibonacciResult(result: List[BigInt])

  // Recursive definition of fibonacci.
  // A recursive algorithm generates a series of stack frames (for each recursive call).
  // Deep levels of recursion can create a StackOverflowError. The following fibonacci implementation
  // might crash for big values of n.
  def fibonacci(n: Int): List[BigInt] = {
    def recFibo(n: Int): BigInt =
      if (n < 2) 1 else recFibo(n - 1) + recFibo(n - 2)
    if (n <= 0) List[BigInt]() else 0 :: (0 until n).toList.map(recFibo)
  }

  // Tail Recursive definition of fibonacci.
  // Scala can optimize recursion when the function is tail-recursive.
  // Tail recursive functions are "functions which call themselves as their last action".
  // The scala compiler optimizes the bytecode to only use one stack frame (instead of one per level of recursion).
  def tailRecFibonacci(n: Int): List[BigInt] = {
    @tailrec
    def recFibo(n: Int, a: BigInt = 0, b: BigInt = 1): BigInt = n match {
      case 0 => a
      case 1 => b
      case _ => recFibo(n-1, b, a+b)
    }
    if (n < 0) List[BigInt]() else (0 to n).toList.map(recFibo(_))
  }

  // The solution route, required for any implementation that extends Problem.
  // See the type signature of the challengeSolution variable.
  val solution: Route = path("example") {
    get {
      parameters('size.as[Int]) {
        size => {
          val challengeSolution: FibonacciResult = FibonacciResult(tailRecFibonacci(size))
          complete(challengeSolution)
        }
      }
    }
  }
}

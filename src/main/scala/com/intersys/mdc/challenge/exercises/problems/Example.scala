package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route

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
    * > Running "sbt run" should initialize the app. You can make your own get requests at: http:0.0.0.0:8080/problems/example?size=5
    *
    * Examples:
    * This section will provide you with at least one get request example and the expected response.
    */

  // This class is used as a wrapper to serialize the result.
  // See the JsonSupport trait for the json implementation.
  final case class FibonacciResult(result: List[Int])

  // Recursive definition of fibonacci.
  def fibonacci(n: Int): List[Int] = {
    def recFibo(n: Int): Int =
      if (n < 2) 1 else recFibo(n - 1) + recFibo(n - 2)
    if (n <= 0) List[Int]() else 0 :: (0 until n).toList.map(recFibo)
  }

  // The solution route, required for any implementation that extends Problem.
  // See the type signature of the challengeSolution variable.
  val solution: Route = path("example") {
    get {
      parameters('size.as[Int]) {
        size => {
          val challengeSolution: FibonacciResult = FibonacciResult(fibonacci(size))
          complete(challengeSolution)
        }
      }
    }
  }
}

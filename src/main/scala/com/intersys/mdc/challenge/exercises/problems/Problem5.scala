package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route

case object Problem5 extends Problem {

  /**
    * IntList ADT
    * Description:
    * In this problem we introduce the concept of traits for data modeling and structural
    * recursion with fold. We provide an implementation of a custom LinkedList for Integers (IntList). This pattern
    * is commonly known as Sum Type Pattern (see <book: Essential Scala (4.4 The Sum Type Pattern)>).
    * This endpoint is expecting 3 integer parameters (a, b, c) and we use our IntLint to return basic operations such as
    * sum (sum), length (len), inner multiplication (mult) and a string representation (str) using arrows "->" for
    * each item and final element. Your goal is to complete the missing implementations of the code:
    *
    * A) Implement the length method for our IntList using pattern matching.
    * B) Implement the sum method for our IntList using pattern matching.
    * C) Generalize the length and sum implementation into a method called fold.
    * D) Generalize the fold method to a generic implementation and call it genericFold[B].
    * E) Complete the challengeResponse variable with the inner multiplication using the fold method.
    * F) Complete the challengeResponse variable with a string representation of the IntList (e.g. a -> b -> c -> )
    * using the genericFold method.
    *
    * Key Points:
    * > Note that the methods are implemented in the IntList trait using pattern matching. We could also have
    * implemented these for each class/object using inheritance.
    *   - For further information on traits and sealed traits see: <https://gist.github.com/mattbarackman/82b1712add45ceffa8ffe56f81b8bcc2>
    *   - For further information on algebraic datatypes and AST see: <book: Essential Scala (4._ Modelling Data with Traits)>
    * > Scala's collections use a very similar definition of fold.
    *   - For further information on generics and fold see: <book: Essential Scala (5.3 Generic Folds for Generic Data)>
    *   - For further information on fold, foldRight and foldLeft see: <https://commitlogs.com/2016/09/10/scala-fold-foldleft-and-foldright/>
    *
    * Examples:
    * Get Request: /problems/5?a=5&b=6&c=2
    * Response: {"len":3,"sum":13,"mult":60,"str":"5 -> 6 -> 2 -> "}
    */

  case class IntListResult(len: Int, sum: Int, mult: Int, str: String)

  sealed trait IntList {

    // Example method that uses pattern matching
    def map(f: Int => Int): IntList = this match {
      case Final => Final
      case Item(head, tail) => Item(f(head), tail)
    }

    // A) Implement the length method
    def length(): Int = this match{
      case Final  => 0
      case Item(head, tail) =>  1 + tail.length()

    }
    // B) Implement the sum method
    def sum(): Int = this match{
      case Final  => 0
      case Item(head, tail) => head + tail.sum

    }

    // C) Implement a generalization of the above methods and call it fold.
    def fold(end: Int,f:(Int, Int)=> Int ):Int = this match {
      case Final => end
      //case Item(head, tail) => end + tail.fold(end, f)
      case Item(head, tail) => f(head , tail.fold(end, f))

    }

    def fold[A](end: A, f: (Int, A) => A): A =
      this match {
        case Final => end
        case Item(hd, tl) => f(hd, tl.fold(end, f))
      }

    // D) Implement a generic fold (generalization over the fold on C).
    // def genericFold[B](end: ???, f: ???): B = ???
    def multiplication: Int =
      fold[Int](1, (hd, tl) => hd * tl)



  }

  final object Final extends IntList
  final case class Item(head: Int, tail: IntList) extends IntList

  val solution: Route = path("5") {
    get {
      parameters('a.as[Int], 'b.as[Int], 'c.as[Int]) {
        (a, b, c) => {

          val myList = Item(a, Item(b, Item(c, Final)))

          // E) Complete the challengeResponse with the inner multiplication of the list using the fold method.

          // F) Use the generic fold to create a string representation of the list.
          var result = IntListResult(myList.length(), myList.sum(), myList.multiplication, a+" -> "+b+" -> "+c + " -> ")
          val challengeResponse: IntListResult = result

          complete(challengeResponse)
        }
      }
    }
  }
}

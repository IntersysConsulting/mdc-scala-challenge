package com.intersys.mdc.challenge.exercises.problems

import akka.http.scaladsl.server.Route
import com.intersys.mdc.challenge.exercises.problems.Problem4.Calculation

import scala.util.{Left, Right}

case object Problem6 extends Problem {

  /**
    * Interpolation
    * Description:
    * This problem introduces the Either monad* in the scala standard collection.
    * The endpoint is expecting a get request with a seriesName parameter and a dataList parameter.
    * The dataList parameter can be converted to a vector using "dataList.toVector" resulting in a Vector[String].
    * The elements of this vector are either "stringified doubles" (e.g. "45.7") or a string with the value "missing"
    * or any other string that cannot be casted to a Double.
    * Therefore, an example of dataList.toVector can be: Vector[String]("1", "2", "missing", "4").
    * Your goal is to interpolate the missing values (resulting in a Vector[Double]) following these constraints:
    *   - Use linear interpolation.
    *     - The linear function is defined as: y = b + m * x
    *     - Use "x" as the index (position) on the vector.
    *     - For more information on linear interpolation see <https://en.wikipedia.org/wiki/Linear_interpolation>
    *   - Use the InterpolationSuccess case class to represent a successful interpolation.
    *     - A successful interpolation involves the correct interpolation of all missing values of the vector.
    *     - Infer further usage by the type signatures in the code.
    *     - A missing value can be interpolated only if the surrounding elements are doubles
    *       (e.g. Vector("1", "missing", "2", "missing", "5") can be interpolated
    *       while Vector("1", "missing", "missing", "4", "5") cannot).
    *   - Use the InterpolationFailure case class to represent a failed interpolation.
    *     - You must provide a reason (string) for using this case class.
    *     - Use the string "At least 3 elements are needed to perform the operation." when the vector has less than 3 elements.
    *     - Use the string "Failed to perform interpolation operation." when the success conditions are not met.
    *   - Use the success case as a Either > Right and the failure case as a Either > Left.
    *     - For more information on Scala's Either see: <https://www.scala-lang.org/api/2.12.6/scala/util/Either.html>
    *     - For an example of usage on Scala's Either see: <https://alvinalexander.com/scala/scala-either-left-right-example-option-some-none-null>
    *
    * Key Points:
    * > If possible, you should use the patterns and techniques seen in previous exercises that best fit the
    * problem (e.g. case class, implicit class, Options, pattern matching).
    * > Note that the scala's Either does not fulfill the requirements to be consider a monad.
    *   - For a solid understanding on monads see: <book: Scala with Cats (Part I. Theory) or at least (Chapter 4: Monads)>
    *   - For more information on monads see (at least the first 11 minutes): <https://youtu.be/U0lK0hnbc4U>
    *   - For more information on monad composition see: <https://youtu.be/hGMndafDcc8>
    *   - There are two relevant libraries for FP in scala (not required for this problem): scalaz and cats.
    *     - Scala Cats <https://typelevel.org/cats/>
    *     - Scalaz     <https://github.com/scalaz/scalaz>
    *
    * Examples:
    *
    * Get Request: /problems/6?seriesName=f(x)=x&dataList=4&dataList=missing&dataList=2&dataList=1&dataList=0
    * Response: {"seriesName":"f(x)=x","result":[0.0,1.0,2.0,3.0,4.0]}
    * - Notes:
    *   - The Akka Http framework provides dataList elements from right to left.
    *   - In this case, the missing value for the linear function is 3 which is correctly estimated from the linear interpolation.
    *
    * Get Request: /problems/6?seriesName=f(x)=x^2&dataList=16&dataList=missing&dataList=4&dataList=1&dataList=0
    * Response: {"seriesName":"f(x)=x^2","result":[0.0,1.0,4.0,10.0,16.0]}
    * - Notes:
    *   - Here the missing value is 9 but the linear interpolation estimates 10.
    *
    * Get Request: /problems/6?seriesName=f(x)=x&dataList=4&dataList=3&dataList=2&dataList=1&dataList=missing
    * Response: {"seriesName":"f(x)=x","data":["missing","1","2","3","4"],"reason":"Failed to perform interpolation operation."}
    * - Notes:
    *   - This example returns an InterpolationFailure because the missing value is not surrounded by two "double" values
    *   to interpolate from (the technique used to estimate a value outside the domain of the datapoints available is called
    *   extrapolation or forecasting).
    *
    * Get Request: /problems/6?seriesName=f(x)=x&dataList=1&dataList=0
    * Response: {"seriesName":"f(x)=x","data":["0","1"],"reason":"At least 3 elements are needed to perform the operation."}
    * - Notes:
    *   - This example returns an InterpolationFailure because a vector of datapoints with length less or equal than 2
    *   does not contains enough information to perform a interpolation for any of its elements.
    *   - Note that a vector of size 3 contains enough datapoints to perform an interpolation for the value in the
    *   index 1 (second position).
    */


  case class InterpolationSuccess(seriesName: String, result: Seq[Double])
  case class InterpolationFailure(seriesName: String, data: Seq[String], reason: String)

  // <---- Your code starts here. ---> (optional)

  implicit class StringOps(string: String) {
    def asDouble: Option[Double] = scala.util.Try(string.toDouble).toOption
  }

  def linInterpolation(dataList:Seq[String], res:Seq[Double], pos:Int):Seq[Double]={
    if(pos==dataList.length) return res
    if(dataList(pos).indexOf("miss")>=0&&pos<dataList.length-1) return linInterpolation(dataList,res :+ lerp(dataList(pos-1).asDouble.get,dataList(pos+1).asDouble.get,pos,dataList.length),pos+1)
    return linInterpolation(dataList,res:+ dataList(pos).asDouble.get,pos+1)
  }

  def lerp(start: Double, end: Double, pos: Int, lt:Int): Double = (start + end)/2

  def failInterpolation(dataList:Seq[String]):String={
    if(dataList.length<3) return "At least 3 elements are needed to perform the operation."
    if(!isInterpolable(dataList)) return "Failed to perform interpolation operation."
    if(dataList(0).indexOf("miss")>=0|| dataList(dataList.length-1).indexOf("miss")>=0) return "Failed to perform interpolation operation."
    return ""
  }

  def isInterpolable(dataList:Seq[String]):Boolean= {
    if(dataList.length<3) return false
    if(dataList(0).indexOf("miss")>=0 || dataList(dataList.length-1).indexOf("miss")>=0) return false
    dataList.sliding(2).foreach((x)=> if(x(0).indexOf("miss")>=0&&x(1).indexOf("miss")>=0) return false)
    return true

  }

  // <---- Your code ends here. ---> (optional)

  val solution: Route = path("6") {
    get {
      parameters('seriesName, 'dataList.*) {
        (seriesName, dataList) =>  {

          var challengeResponse:Either[InterpolationSuccess,InterpolationFailure] =
            if(isInterpolable(dataList.toSeq)) Left(InterpolationSuccess(seriesName, linInterpolation(dataList.toSeq,Seq[Double](),0)))
            else Right(InterpolationFailure(seriesName,dataList.toSeq,failInterpolation(dataList.toSeq)))

          // <---- Your code ends  here. ---->

          // Uncomment this code segment when your solution is ready.
          challengeResponse match {
            case Right(success) => complete(success)
            case Left(failure)  => complete(failure)
          }
        }
      }
    }
  }
}

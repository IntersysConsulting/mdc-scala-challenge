# MDC Scala Challenge

This challenge contains basic exercises regarding scala programming. The activity consists of 
completing the missing parts of the API coded in Akka Https. You might be required to provide an implementation for 
some methods, add functionality or code critical components of an endpoint. Each problem is an endpoint of the API. 

The package `com.intersys.mdc.challenge.exercises.problems` contains the problems. In the first stage, 
your code will be automatically checked by the compiler and the test cases (using the akka http test library). Every 
participant whose solution compiles and passes the test will be subject to a code review activity in which clean code,
best practices and functional programming style will be evaluated.   

## Dependencies

You must have installed:
* [Git](https://git-scm.com/)
* [Scala Build Tool (SBT)](https://www.scala-sbt.org/)
* [Recommended] [IntelliJ Idea with scala plugin](https://www.jetbrains.com/idea/)

## Usage

Follow these instructions:
1. Use git to clone this repo to your local machine: `git clone https://github.com/IntersysConsulting/mdc-scala-challenge`
2. The default branch (`challenge`) contains the problems. You need to create a new branch and provide your answers: 
    * Create a branch with your name: `git checkout -b MyName`
    * Note: Consider that you will be required to push your branch on the deadline (not before).
3. Run `sbt test` to see the correct/wrong implementations or `sbt run` to initialize the API at localhost `127.0.0.1:8080`.
    * You can change the configuration ports and host at: `src/main/resources/application.conf`.
    * The challenge should compile since first use (even without any implementation).  
4. If needed, fix the endpoint implementations (`src/main/com/intersys/mdc/challenge/problems/...`).
5. If needed, return to step 3. 

### Run the tests
The challenge provides custom test cases using the Akka TestKit and Scala Test library. To run the tests 
open a terminal and run: `sbt test`. Green results indicate that you passed the test (by default there is an example
that passes the test cases). A problem with a wrong implementation or missing parts will fail the tests. Furthermore, 
if your code does not compiles that means that the solution you provided is definitely wrong.  

If you identify a bug or want to contribute more test cases, go to section: **Authors and contributions**.

### Run the app
The API compiles even without providing a solution to the problems. To compile and run the app use the following 
command: `sbt run`. You will find the running app at localhost (`http://127.0.0.1:8080`). If you visit an endpoint 
without an implementation you will see an internal server error (due to a not implemented error). 
Otherwise, you might expect an html, json or bad request response. 

You can find the example endpoint at: `http://127.0.0.1:8080/problems/example?size=15`
* The size parameter indicates the length of the fibonacci sequence (excluding the 0).

You can find the endpoint of a problem "i" at: `http://127.0.0.1:8080/problems/i`
* Where i is the problem number (i.e. 1, 2, ..., 6)
* All endpoints expect query parameters for a get request. 

## Authors and contributions

Please add an `issue` if you identify any problem/bug or create a `pull request` to add functionality/exercises/etc. 

For further information:
* [Rodrigo Hernández Mota](https://github.com/rhdzmota)

## License

All the rights are reserved to Intersys Consulting. 

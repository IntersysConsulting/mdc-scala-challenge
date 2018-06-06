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
2. The default branch (`solution`) contains the solutions, so make sure to be in the `challenge` branch.
    * Fetch if needed: `git fetch --all`
    * Move into the challenge branch: `git checkout challenge`
3. In order to track your progress, create a branch with your name (from the challenge branch): `git checkout -b solution/<my-name>` 
4. Run `sbt test` to see the correct/wrong implementations or `sbt run` to initialize the API at `0.0.0.0:8080`. 
5. If needed, fix the endpoint implementations (`src/main/com/intersys/mdc/challenge/problems/...`).
6. If needed, return to step 4. 

### Run the tests
The challenge provides custom test cases using the Akka TestKit and Scala Test library. To run the tests 
open a terminal and run: `sbt test`. Green results indicate that you passed the test (by default there is an example
that passes the test cases). A problem with a wrong implementation or missing parts will fail the tests. 

If you identify a bug or want to contribute more test cases, go to section: **Authors and contributions**.

### Run the app
The API compiles even without providing a solution to the problems. To compile and run the app use the follwing 
command: `sbt run`. You will find the running app at localhost (`http://0.0.0.0:8080`). If you visit an endpoint 
without an implementation you will see an internal server error. Otherwise, you might expect an html, json or
bad request response. 

## Authors and contributions

Please add an `issue` if you identify any problem/bug or create a `pull request` to add functionality/exercises/etc. 

For further information:
* [Rodrigo Hernández Mota](https://github.com/rhdzmota)

## License

All the rights are reserved to Intersys Consulting. 

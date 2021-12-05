package lt.markvl.adventofcode

import utils.TestUtils

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PuzzleThreeTest extends AnyFlatSpec, Matchers, TestUtils:

  "Part One" should "work with example data" in {
    val input = "/PuzzleThree/exampleInput".readLines(identity)
    Diagnostics.consumption(input) should be(198)
  }

  it should "Work with real data" in {
    val input = "/PuzzleThree/input".readLines(identity)
    Diagnostics.consumption(input) should be(2003336)
  }

  "Part Two" should "work with example data" in {
    val input = "/PuzzleThree/exampleInput".readLines(identity)
    Diagnostics.lifeSupportRating(input) should be(230)
  }

  it should "Work with real data" in {
    val input = "/PuzzleThree/input".readLines(identity)
    Diagnostics.lifeSupportRating(input) should be(1877139)
  }

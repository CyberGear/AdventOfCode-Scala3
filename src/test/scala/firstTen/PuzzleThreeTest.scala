package lt.markvl.adventofcode.firstTen

import lt.markvl.adventofcode.utils.TestUtils

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PuzzleThreeTest extends AnyFlatSpec, Matchers, TestUtils:

  "Part One" should "work with example data" in {
    val input = "/firstTen/PuzzleThree/exampleInput".readLines()
    Diagnostics.consumption(input) should be(198)
  }

  it should "Work with real data" in {
    val input = "/firstTen/PuzzleThree/input".readLines()
    Diagnostics.consumption(input) should be(2003336)
  }

  "Part Two" should "work with example data" in {
    val input = "/firstTen/PuzzleThree/exampleInput".readLines()
    Diagnostics.lifeSupportRating(input) should be(230)
  }

  it should "Work with real data" in {
    val input = "/firstTen/PuzzleThree/input".readLines()
    Diagnostics.lifeSupportRating(input) should be(1877139)
  }

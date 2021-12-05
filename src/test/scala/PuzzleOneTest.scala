package lt.markvl.adventofcode

import utils.TestUtils

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PuzzleOneTest extends AnyFlatSpec, Matchers, TestUtils:

  object PuzzleOne:
    def countIncreases(input: List[Int]): Int =
      input.sliding(2).count(s => s.head < s.last)
    def countSliding3SumIncreases(input: List[Int]): Int =
      countIncreases(input.sliding(3).map(_.sum).toList)

  "Part One" should "work with example data" in {
    val input = "/PuzzleOne/exampleInput".readLinesAsInts
    PuzzleOne.countIncreases(input) should be(7)
  }

  it should "work with real data" in {
    val input = "/PuzzleOne/input".readLinesAsInts
    PuzzleOne.countIncreases(input) should be(1477)
  }

  "Part Two" should "work with example" in {
    val input = "/PuzzleOne/exampleInput".readLinesAsInts
    PuzzleOne.countSliding3SumIncreases(input) should be(5)
  }

  it should "work with real data" in {
    val input = "/PuzzleOne/input".readLinesAsInts
    PuzzleOne.countSliding3SumIncreases(input) should be(1523)
  }

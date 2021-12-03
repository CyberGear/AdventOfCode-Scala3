package lt.markvl.adventofcode

import lt.markvl.adventofcode.utils.TestUtils
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PuzzleOneTest extends AnyFlatSpec with Matchers with TestUtils {

  "Part One" should "work with example data" in {
    val input = "/PuzzleOne/exampleInput".readLinesAsInts
    PuzzleOne.countIncreases(input) should be (7)
  }

  it should "work with real data" in {
    val input = "/PuzzleOne/input".readLinesAsInts
    PuzzleOne.countIncreases(input) should be (1477)
  }

  "Part Two" should "work with example" in {
    val input = "/PuzzleOne/exampleInput".readLinesAsInts
    PuzzleOne.countSliding3SumIncreases(input) should be (5)
  }

  it should "work with real data" in {
    val input = "/PuzzleOne/input".readLinesAsInts
    PuzzleOne.countSliding3SumIncreases(input) should be (1523)
  }

}

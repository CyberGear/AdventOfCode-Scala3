package lt.markvl.adventofcode

import basin.*
import display.{Entry, SegmentDisplay}
import lt.markvl.adventofcode.utils.TestUtils

import monocle.syntax.all.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PuzzleNineTest extends AnyFlatSpec, Matchers, TestUtils:

  def readFile(path: String): List[List[Int]] =
    path.readLines(_.map(_.toString.toInt).toList)

  "Part One" should "work with example data" in {
    val basinMap: List[List[Int]] = readFile("/PuzzleNine/exampleInput")
    basinMap.riskLevelSum should be(15)
  }

  it should "work with real data" in {
    val basinMap: List[List[Int]] = readFile("/PuzzleNine/input")
    basinMap.riskLevelSum should be(572)
  }

  "Part Two" should "work with example data" in {
    val basinMap: List[List[Int]] = readFile("/PuzzleNine/exampleInput")
    basinMap.multiplyThreeLargestBasins should be(1134)
  }

  it should "work with real data" in {
    val basinMap: List[List[Int]] = readFile("/PuzzleNine/input")
    basinMap.multiplyThreeLargestBasins should be(847044)
  }

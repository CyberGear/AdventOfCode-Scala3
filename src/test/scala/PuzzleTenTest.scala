package lt.markvl.adventofcode

import basin.*
import display.{Entry, SegmentDisplay}
import utils.TestUtils

import lt.markvl.adventofcode.syntax.SpellCheck
import monocle.syntax.all.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PuzzleTenTest extends AnyFlatSpec, Matchers, TestUtils:

  "Part One" should "work with example data" in {
    val lines: List[String] = "/PuzzleTen/exampleInput".readLines()
    SpellCheck.corruptionScore(lines) should be(26397)
  }

  it should "work with real data" in {
    val lines: List[String] = "/PuzzleTen/input".readLines()
    SpellCheck.corruptionScore(lines) should be(294195)
  }

  "Part Two" should "work with example data" in {
    val lines: List[String] = "/PuzzleTen/exampleInput".readLines()
    SpellCheck.completionMiddleScore(lines) should be(288957)
  }

  it should "work with real data" in {
    val lines: List[String] = "/PuzzleTen/input".readLines()
    SpellCheck.completionMiddleScore(lines) should be(3490802734L)
  }
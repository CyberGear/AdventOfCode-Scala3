package lt.markvl.adventofcode.firstTen

import lt.markvl.adventofcode.firstTen.basin.*
import lt.markvl.adventofcode.firstTen.display.{Entry, SegmentDisplay}
import lt.markvl.adventofcode.firstTen.syntax.SpellCheck
import lt.markvl.adventofcode.utils.TestUtils

import monocle.syntax.all.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PuzzleTenTest extends AnyFlatSpec, Matchers, TestUtils:

  "Part One" should "work with example data" in {
    val lines: List[String] = "/firstTen/PuzzleTen/exampleInput".readLines()
    SpellCheck.corruptionScore(lines) should be(26397)
  }

  it should "work with real data" in {
    val lines: List[String] = "/firstTen/PuzzleTen/input".readLines()
    SpellCheck.corruptionScore(lines) should be(294195)
  }

  "Part Two" should "work with example data" in {
    val lines: List[String] = "/firstTen/PuzzleTen/exampleInput".readLines()
    SpellCheck.completionMiddleScore(lines) should be(288957)
  }

  it should "work with real data" in {
    val lines: List[String] = "/firstTen/PuzzleTen/input".readLines()
    SpellCheck.completionMiddleScore(lines) should be(3490802734L)
  }

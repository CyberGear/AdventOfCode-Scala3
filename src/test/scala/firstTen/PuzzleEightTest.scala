package lt.markvl.adventofcode.firstTen

import Crab.*
import lt.markvl.adventofcode.firstTen.display.{Entry, SegmentDisplay}
import lt.markvl.adventofcode.utils.TestUtils

import monocle.syntax.all.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PuzzleEightTest extends AnyFlatSpec, Matchers, TestUtils:

  def readFile(path: String): List[Entry] =
    path.readLines().map { line =>
      val Array(signals, output) = line.split(" \\| ")
      Entry(signals.split(" ").toList, output.split(" ").toList)
    }

  "Part One" should "work with example data" in {
    val entries: List[Entry] = readFile("/firstTen/PuzzleEight/exampleInput")
    SegmentDisplay.countSimpleOutputDigits(entries) should be(26)
  }

  it should "work with real data" in {
    val entries: List[Entry] = readFile("/firstTen/PuzzleEight/input")
    SegmentDisplay.countSimpleOutputDigits(entries) should be(321)
  }

  "Part Two" should "work with example data" in {
    val entries: List[Entry] = readFile("/firstTen/PuzzleEight/exampleInput")
    SegmentDisplay.outputsSum(entries) should be(61229)
  }

  it should "work with real data" in {
    val entries: List[Entry] = readFile("/firstTen/PuzzleEight/input")
    SegmentDisplay.outputsSum(entries) should be(1028926)
  }

package lt.markvl.adventofcode

import Crab.*
import utils.TestUtils

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
    val entries: List[Entry] = readFile("/PuzzleEight/exampleInput")
    SegmentDisplay.countSimpleOutputDigits(entries) should be(26)
  }

  it should "work with real data" in {
    val entries: List[Entry] = readFile("/PuzzleEight/input")
    SegmentDisplay.countSimpleOutputDigits(entries) should be(321)
  }

//  "Part Two" should "work with example data" in {
//    val crabs: List[String] = readFile("/PuzzleEight/exampleInput")
////    crabs.minFuelInefficientsEngine should be(168)
//  }
//
//  it should "work with real data" in {
//    val crabs: List[String] = readFile("/PuzzleEight/input")
////    crabs.minFuelInefficientsEngine should be(98368490)
//  }

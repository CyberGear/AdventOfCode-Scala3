package lt.markvl.adventofcode

import lt.markvl.adventofcode.utils.TestUtils
import monocle.syntax.all.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import Crab.*

class PuzzleSevenTest extends AnyFlatSpec, Matchers, TestUtils:

  def readFile(path: String): List[Crab] =
    path.readLines().head.split(",").map(Crab.fromString).toList

  "Part One" should "work with example data" in {
    val crabs: List[Crab] = readFile("/PuzzleSeven/exampleInput")
    crabs.minFuelToAlign() should be(37)
  }

  it should "work with real data" in {
    val crabs: List[Crab] = readFile("/PuzzleSeven/input")
    crabs.minFuelToAlign() should be(355150)
  }

  "Part Two" should "work with example data" in {
    val crabs: List[Crab] = readFile("/PuzzleSeven/exampleInput")
    crabs.minFuelInefficientsEngine should be(168)
  }

  it should "work with real data" in {
    val crabs: List[Crab] = readFile("/PuzzleSeven/input")
    crabs.minFuelInefficientsEngine should be(98368490)
  }

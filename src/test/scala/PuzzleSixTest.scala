package lt.markvl.adventofcode

import lt.markvl.adventofcode.utils.TestUtils

import monocle.syntax.all.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import Lanternfish.*

class PuzzleSixTest extends AnyFlatSpec, Matchers, TestUtils:

  def readFile(path: String): List[Lanternfish] =
    path.readLines().head.split(",").map(Lanternfish.fromString).toList

  "Part One" should "work with example data" in {
    val lanternfish: List[Lanternfish] = readFile("/PuzzleSix/exampleInput")
    lanternfish.populationSize(80) should be(5934)
  }

  it should "work with real data" in {
    val lanternfish: List[Lanternfish] = readFile("/PuzzleSix/input")
    lanternfish.populationSize(80) should be(352195)
  }

  "Part two" should "work with example data" in {
    val lanternfish: List[Lanternfish] = readFile("/PuzzleSix/exampleInput")
    lanternfish.populationSize(256) should be(26984457539L)
  }

  it should "work with real data" in {
    val lanternfish: List[Lanternfish] = readFile("/PuzzleSix/input")
    lanternfish.populationSize(256) should be(1600306001288L)
  }

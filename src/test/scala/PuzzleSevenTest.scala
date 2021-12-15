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
    crabs.bestAlignment should be(2)
  }

//  it should "work with real data" in {
//    val crabs: List[Crab] = readFile("/PuzzleSeven/input")
//    crabs.populationSize(80) should be(352195)
//  }
//
//  "Part two" should "work with example data" in {
//    val crabs: List[Crab] = readFile("/PuzzleSeven/exampleInput")
//    crabs.populationSize(256) should be(26984457539L)
//  }
//
//  it should "work with real data" in {
//    val crabs: List[Crab] = readFile("/PuzzleSeven/input")
//    crabs.populationSize(256) should be(1600306001288L)
//  }

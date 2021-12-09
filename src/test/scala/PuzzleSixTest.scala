package lt.markvl.adventofcode

import utils.TestUtils

import monocle.syntax.all.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import Lanternfish.*

class PuzzleSixTest extends AnyFlatSpec, Matchers, TestUtils:

  // Lanternfish / 9;(7)

  "Part One" should "work with example data" in {
    val lanternfish: List[Lanternfish] =
      "/PuzzleSix/exampleInput"
        .readLines(_.split(",").map(Lanternfish.fromString))
        .head
        .toList

    Lanternfish.schoolSize(lanternfish, 80) should be(5934)
  }

  it should "work with real data" in {
    val lanternfish: List[Lanternfish] =
      "/PuzzleSix/input"
        .readLines(_.split(",").map(Lanternfish.fromString))
        .head
        .toList

    Lanternfish.schoolSize(lanternfish, 80) should be(352195)
  }

  "Part two" should "work with example data" in {
    val lanternfish: List[Lanternfish] =
      "/PuzzleSix/exampleInput"
        .readLines(_.split(",").map(Lanternfish.fromString))
        .head
        .toList

    Lanternfish.schoolSize(lanternfish, 256) should be(26984457539L)
  }

//  it should "work with real data" in {}

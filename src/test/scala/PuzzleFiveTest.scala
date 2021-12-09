package lt.markvl.adventofcode

import utils.TestUtils

import monocle.syntax.all.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PuzzleFiveTest extends AnyFlatSpec, Matchers, TestUtils:

  "Part One" should "work with example data" in {
    val input = "/PuzzleFive/exampleInput".readLines(Pipe.fromString)

    HydrothermalPipes.countDangerousPoints(
      input.filter(_.nonDiagonal)
    ) should be(5)
  }

  it should "work with real data" in {
    val input = "/PuzzleFive/input".readLines(Pipe.fromString)

    HydrothermalPipes.countDangerousPoints(
      input.filter(_.nonDiagonal)
    ) should be(6687)
  }

  "Part two" should "work with example data" in {
    val input = "/PuzzleFive/exampleInput".readLines(Pipe.fromString)
    HydrothermalPipes.countDangerousPoints(input) should be(12)
  }

  it should "work with real data" in {
    val input = "/PuzzleFive/input".readLines(Pipe.fromString)
    HydrothermalPipes.countDangerousPoints(input) should be(19851)
  }

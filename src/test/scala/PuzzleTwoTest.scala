package lt.markvl.adventofcode

import utils.TestUtils

import lt.markvl.adventofcode.model.Move
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PuzzleTwoTest extends AnyFlatSpec, Matchers, TestUtils:

//  "Part One" should "work with example data" in {
//    val input = "/PuzzleTwo/exampleInput".readLines(Move.fromString)
//    val finalPosition = PuzzleTwo.resolvePosition(input)
//    PuzzleTwo.positionProduct(finalPosition) should be (150)
//  }
//
//  it should "work with real data" in {
//    val input = "/PuzzleTwo/input".readLines(Move.fromString)
//    val finalPosition = PuzzleTwo.resolvePosition(input)
//    PuzzleTwo.positionProduct(finalPosition) should be (150)
//  }

  "Part Two" should "work with example data" in {
    val input = "/PuzzleTwo/exampleInput".readLines(Move.fromString)
    val finalPosition = Submarine.resolvePosition(input)
    Submarine.positionProduct(finalPosition) should be(900)
  }

  it should "work with real data" in {
    val input = "/PuzzleTwo/input".readLines(Move.fromString)
    val finalPosition = Submarine.resolvePosition(input)
    Submarine.positionProduct(finalPosition) should be(2078985210)
  }

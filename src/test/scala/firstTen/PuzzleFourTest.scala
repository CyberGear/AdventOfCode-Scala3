package lt.markvl.adventofcode.firstTen

import lt.markvl.adventofcode.utils.TestUtils

import monocle.syntax.all.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PuzzleFourTest extends AnyFlatSpec, Matchers, TestUtils:

  def boardReader(
      in: List[List[String]],
      out: List[Board] = Nil,
      acc: Board = Board(None, List.empty)
  ): List[Board] =
    in match
      case Nil         => out :+ acc
      case Nil :: tail => boardReader(tail, out :+ acc)
      case head :: tail =>
        boardReader(
          tail,
          out,
          acc
            .focus(_.numbers)
            .modify(_ :+ head.map(n => Fresh.apply(n.toInt)))
        )

  def readData(path: String): (List[Int], List[Board]) =
    val input =
      path.readLines(
        _.split(" +").filterNot(_.isBlank).toList
      )
    val choices = input.head.head.split(",").map(_.toInt).toList
    val boards = boardReader(input.tail.tail)
    choices -> boards

  "Part One" should "work with example data" in {
    val (choices, boards) = readData("/firstTen/PuzzleFour/exampleInput")
    BingoGame.firstWinBoardScore(choices, boards) should be(4512)
  }

  it should "Work with real data" in {
    val (choices, boards) = readData("/firstTen/PuzzleFour/input")
    BingoGame.firstWinBoardScore(choices, boards) should be(27027)
  }

  "Part two" should "work with example data" in {
    val (choices, boards) = readData("/firstTen/PuzzleFour/exampleInput")
    BingoGame.lastWinBoardScore(choices, boards) should be(1924)
  }

  it should "Work with real data" in {
    val (choices, boards) = readData("/firstTen/PuzzleFour/input")
    BingoGame.lastWinBoardScore(choices, boards) should be(36975)
  }

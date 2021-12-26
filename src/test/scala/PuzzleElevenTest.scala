package lt.markvl.adventofcode

import basin.*
import utils.TestUtils
import octoflash.*

import monocle.syntax.all.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PuzzleElevenTest extends AnyFlatSpec, Matchers, TestUtils:

  def readData(path: String): Octopi = Octopi(path.readLines(_.map(_.toString.toInt).toList))

  "Part One" should "work with example data" in {
    val octopi: Octopi = readData("/PuzzleEleven/exampleInput")
    Predictor.countFlashes(octopi, 100) should be(1656)
  }

  it should "work with real data" in {
    val octopi: Octopi = readData("/PuzzleEleven/input")
    Predictor.countFlashes(octopi, 100) should be(1585)
  }

  "Part Two" should "work with example data" in {
    val octopi: Octopi = readData("/PuzzleEleven/exampleInput")
    Predictor.findTotalFlashStep(octopi) should be(195)
  }

  it should "work with real data" in {
    val octopi: Octopi = readData("/PuzzleEleven/input")
    Predictor.findTotalFlashStep(octopi) should be(382)
  }

  def makeString(a: List[List[Int]], e: List[List[Int]]): String =
    import scala.io.AnsiColor._
    val zips: List[List[(Int, Int)]] = a.zip(e).map((a, e) => a zip e)

    zips
      .map(_.map {
        case (a, e) if a == e => s" ${GREEN}$a${RESET} "
        case (a, e) if a != e => s" ${RED}$a${BLUE}$e${RESET}"
      }.mkString)
      .mkString("\n", "\n", "\n")

  extension (octopi: Octopi)
    def shouldBeLikeThis(s: String): Unit =
      val expected = Octopi(s.split("\n").map(_.map(_.toString.toInt).toList).toList)
      withClue(makeString(octopi.octopi, expected.octopi)) {
        octopi should be(expected)
      }

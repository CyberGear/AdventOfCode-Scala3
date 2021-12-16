package lt.markvl.adventofcode
package display

import lt.markvl.adventofcode.display.{Digit, Entry}
import Digit.*
import scala.util.Try

object SegmentDisplay:

  // One to map             (7-1)   x -> a
  // second to directly map (1)     x -> c ; y -> f
  // third to swap          (1)     y -> c ; x -> f
  // fourth to directly map (4-7)   x -> b ; y -> d
  // fifth to swap          (4-7)   y -> b ; x -> d
  // sixth to directly map  (8-4)   x -> e ; y -> g
  // seventh to swap        (8-4)   y -> e ; x -> g

  def aOption(entry: Entry): List[List[(String, String)]] =
    List(List(entry.patterns.sortBy(_.length).take(2).reverse.reduce(_ diff _) -> "a"))

  def cfOptions(entry: Entry): List[List[(String, String)]] =
    val List(x, y) = entry.patterns.minBy(_.length).map(_.toString).toList
    List(
      List(x -> "c", y -> "f"),
      List(y -> "c", x -> "f")
    )

  def bdOptions(entry: Entry): List[List[(String, String)]] =
    val List(x, y) = entry.patterns.sortBy(_.length).slice(1, 3).reverse.reduce(_ diff _).map(_.toString).toList
    List(
      List(x -> "b", y -> "d"),
      List(y -> "b", x -> "d")
    )

  def egOptions(entry: Entry): List[List[(String, String)]] =
    val a = aOption(entry).head.head._1
    val patterns = entry.patterns.sortBy(_.length)
    val List(x, y) = (patterns.last diff (patterns(2) + a)).map(_.toString).toList
    List(
      List(x -> "e", y -> "g"),
      List(y -> "e", x -> "g")
    )

  def generateVariations(entry: Entry): List[Map[String, String]] =
    for
      aRules <- aOption(entry)
      cfRules <- cfOptions(entry)
      bdRules <- bdOptions(entry)
      egRules <- egOptions(entry)
    yield (aRules ++ cfRules ++ bdRules ++ egRules).toMap

  def outputsSum(entries: List[Entry]): Int =
    val numbers =
      for
        entry <- entries
        rules <- generateVariations(entry)
        afterRules = entry.apply(rules)
        if afterRules.hasDigits
      yield afterRules.output.flatMap(s => Digit.withSegments(s.sorted)).map(_.output).mkString.toInt
    numbers.sum

  def countSimpleOutputDigits(entries: List[Entry]): Int =
    entries
      .map(_.output.map(_.length).count(sLength => sLength == 2 || sLength == 3 || sLength == 4 || sLength == 7))
      .sum

enum Digit(val segments: String, val output: Int):
  case Zero extends Digit("abcefg", 0)
  case One extends Digit("cf", 1)
  case Two extends Digit("acdeg", 2)
  case Three extends Digit("acdfg", 3)
  case Four extends Digit("bcdf", 4)
  case Five extends Digit("abdfg", 5)
  case Six extends Digit("abdefg", 6)
  case Seven extends Digit("acf", 7)
  case Eight extends Digit("abcdefg", 8)
  case Nine extends Digit("abcdfg", 9)

object Digit:
  def withSegments(s: String): Option[Digit] = Digit.values.find(_.segments == s)

case class Entry(patterns: List[String], output: List[String]):

  def apply(rules: Map[String, String]): Entry =
    Entry(
      patterns.map(_.apply(rules)),
      output.map(_.apply(rules))
    )
  def hasDigits: Boolean =
    patterns.map(_.sorted).flatMap(Digit.withSegments).size == patterns.size

extension (s: String)
  def apply(rules: Map[String, String]): String =
    s.map(_.toString).map(rules(_)).mkString

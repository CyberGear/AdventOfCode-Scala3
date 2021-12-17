package lt.markvl.adventofcode
package syntax

import Chunk.*

import scala.annotation.tailrec

object SpellCheck:

  def validate(line: String): Line =
    @tailrec
    def loop(in: List[String], stack: List[String] = Nil): Line = in match
      case Opening(o) :: tail                             => loop(tail, stack :+ o)
      case Closing(c) :: tail if openFor(c) == stack.last => loop(tail, stack.init)
      case Closing(c) :: _                                => Corrupted(line, c, closeFor(stack.last))
      case _                                              => Incomplete(line, stack.reverse.map(closeFor(_)))
    loop(line.map(_.toString).toList)

  def validate(lines: List[String]): List[Line] =
    lines.map(validate)

  def corruptionScore(lines: List[String]): Int =
    validate(lines).map {
      case Corrupted(_, mistake, _) => mistakePointsFor(mistake)
      case _                        => 0
    }.sum

  def completionMiddleScore(lines: List[String]): Long =
    val sortedScores = validate(lines)
      .collect { case Incomplete(_, missing) => missing }
      .map(_.foldLeft(0L)((score, miss) => score * 5 + autocompletePointsFor(miss)))
      .sorted
    sortedScores(sortedScores.size / 2)

enum Chunk(val open: String, val close: String, val mistakePoints: Int, val autocompletePoints: Int):
  case `()` extends Chunk("(", ")", 3, 1)
  case `[]` extends Chunk("[", "]", 57, 2)
  case `{}` extends Chunk("{", "}", 1197, 3)
  case `<>` extends Chunk("<", ">", 25137, 4)

object Chunk:
  val openings: Seq[String] = Chunk.values.map(_.open)
  val closings: Seq[String] = Chunk.values.map(_.close)
  val closeFor: Map[String, String] = Chunk.values.map(v => v.open -> v.close).toMap
  val openFor: Map[String, String] = Chunk.values.map(v => v.close -> v.open).toMap
  val mistakePointsFor: Map[String, Int] = Chunk.values.map(v => v.close -> v.mistakePoints).toMap
  val autocompletePointsFor: Map[String, Int] = Chunk.values.map(v => v.close -> v.autocompletePoints).toMap

  object Opening:
    def unapply(s: String): Option[String] = openings.find(_ == s)

  object Closing:
    def unapply(s: String): Option[String] = closings.find(_ == s)

sealed trait Line:
  def string: String

case class Corrupted(override val string: String, mistake: String, expected: String) extends Line
case class Incomplete(override val string: String, missing: List[String]) extends Line

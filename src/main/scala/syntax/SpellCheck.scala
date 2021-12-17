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
      case _                                              => Incomplete(line, stack.last)
    loop(line.map(_.toString).toList)

  def validate(lines: List[String]): List[Line] =
    lines.map(validate)

  def corruptionScore(lines: List[String]): Int =
    validate(lines).map {
      case Corrupted(_, mistake, _) => pointsFor(mistake)
      case _                        => 0
    }.sum

enum Chunk(val open: String, val close: String, val points: Int):
  case `()` extends Chunk("(", ")", 3)
  case `[]` extends Chunk("[", "]", 57)
  case `{}` extends Chunk("{", "}", 1197)
  case `<>` extends Chunk("<", ">", 25137)

object Chunk:
  val openings: Seq[String] = Chunk.values.map(_.open)
  val closings: Seq[String] = Chunk.values.map(_.close)
  val closeFor: Map[String, String] = Chunk.values.map(v => v.open -> v.close).toMap
  val openFor: Map[String, String] = Chunk.values.map(v => v.close -> v.open).toMap
  val pointsFor: Map[String, Int] = Chunk.values.map(v => v.close -> v.points).toMap

  object Opening:
    def unapply(s: String): Option[String] = openings.find(_ == s)

  object Closing:
    def unapply(s: String): Option[String] = closings.find(_ == s)

sealed trait Line:
  def string: String

case class Corrupted(override val string: String, mistake: String, expected: String) extends Line
case class Incomplete(override val string: String, notClosed: String) extends Line

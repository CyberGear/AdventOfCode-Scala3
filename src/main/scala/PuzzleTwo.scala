package lt.markvl.adventofcode

import monocle.syntax.all.*

import scala.util.matching.Regex

object PuzzleTwo:

  def resolvePosition(
      moves: List[Move],
      initialPosition: Position = Position()
  ): Position =
    moves.foldLeft(initialPosition)((pos, move) => pos move move)

  def positionProduct(position: Position): Int =
    position.horizontal * position.depth

sealed trait Move

case class Forward(x: Int) extends Move
case class Down(x: Int) extends Move
case class Up(x: Int) extends Move

object Move:

  val ForwardRegex: Regex = """forward (\d+)""".r
  val DownRegex: Regex = """down (\d+)""".r
  val UpRegex: Regex = """up (\d+)""".r

  def fromString(s: String): Move = s match
    case ForwardRegex(x) => Forward(x.toInt)
    case DownRegex(x)    => Down(x.toInt)
    case UpRegex(x)      => Up(x.toInt)

case class Position(horizontal: Int = 0, depth: Int = 0, aim: Int = 0)

extension (position: Position)
  def move(move: Move): Position = move match
    case Forward(x) =>
      position
        .focus(_.horizontal)
        .modify(_ + x)
        .focus(_.depth)
        .modify(_ + (position.aim * x))
    case Down(x) => position.focus(_.aim).modify(_ + x)
    case Up(x)   => position.focus(_.aim).modify(_ - x)

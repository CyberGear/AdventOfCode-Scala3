package lt.markvl.adventofcode

import model.{Move, Position}

import eu.timepit.refined.*
import eu.timepit.refined.auto.*
import monocle.syntax.all.*
import Moves.*

import scala.util.matching.Regex

case class Submarine(position: Position = Position())

extension (submarine: Submarine)

  def updatePosition(moves: List[Move]): Position =
    submarine
      .focus(_.position)
      .modify(initialPosition =>
        moves.foldLeft(initialPosition)((pos, move) => pos move move)
      )

  def positionProduct(position: Position): Int =
    position.horizontal * position.depth

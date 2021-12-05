package lt.markvl.adventofcode
package model

import eu.timepit.refined.*
import eu.timepit.refined.auto.*
import eu.timepit.refined.types.numeric.NonNegInt

case class Position(horizontal: NonNegInt, depth: NonNegInt, aim: NonNegInt)

object Position:
  def apply(): Position = Position(0, 0, 0)

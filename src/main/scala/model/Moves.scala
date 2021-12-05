package lt.markvl.adventofcode
package model

import cats.implicits.*
import cats.syntax.either.catsSyntaxEither

import scala.util.matching.Regex
import eu.timepit.refined.*
import eu.timepit.refined.api.Validate
import eu.timepit.refined.auto.*
import eu.timepit.refined.types.numeric.NonNegInt
import lt.markvl.adventofcode.{Down, Forward, Move, Up}

object Moves:

  sealed trait Move

  opaque type Forward = NonNegInt with Move
  object Forward:
    def create(int: Int)(implicit
        v: Validate[Int, Forward]
    ): Either[String, Forward] =
      refineV[Forward](int)

  opaque type Down = NonNegInt with Move
  object Down:
    def create(int: Int)(implicit
        v: Validate[Int, Down]
    ): Either[String, Down] =
      refineV[Down](int)

  opaque type Up = NonNegInt with Move
  object Up:
    def create(int: Int)(implicit
        v: Validate[Int, Up]
    ): Either[String, Up] =
      refineV[Up](int)

  object Move:
    private val ForwardRegex: Regex = """forward (\d+)""".r
    private val DownRegex: Regex = """down (\d+)""".r
    private val UpRegex: Regex = """up (\d+)""".r

    def fromString(s: String): Either[Throwable, Move] = s match
      case ForwardRegex(x) => Forward.create(x.toInt)
      case DownRegex(x)    => Down(x.toInt)
      case UpRegex(x)      => Up(x.toInt)

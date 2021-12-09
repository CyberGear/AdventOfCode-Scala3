package lt.markvl.adventofcode

import scala.annotation.tailrec
import scala.collection.IterableOnce

case class Lanternfish(internalTimer: Long)

object Lanternfish:
  implicit val flatenner: Lanternfish => List[Lanternfish] = _ match
    case Lanternfish(0)   => List(Lanternfish(6), Lanternfish(8))
    case Lanternfish(int) => List(Lanternfish(int - 1))

  @tailrec
  def schoolSize(school: List[Lanternfish], cycles: Int): Long =
    if cycles == 0 then school.size
    else schoolSize(school.flatten, cycles - 1)

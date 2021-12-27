package lt.markvl.adventofcode.firstTen

import scala.annotation.tailrec

object Crab:
  opaque type Crab = Int
  def fromString(s: String): Crab = s.toInt

  extension (crabs: List[Crab])
    def minFuelToAlign(consumptionAlgorithm: Int => Int = identity): Int =
      @tailrec
      def loop(in: List[Crab], pos: Int = 0, acc: List[(Int, Int)] = Nil): Int =
        if pos == in.size then acc.last._2
        else if acc.size > 1 && acc.last._2 > acc.init.last._2 then acc.init.last._2
        else loop(in, pos + 1, acc :+ pos -> in.map(c => consumptionAlgorithm(math.abs(c - pos))).sum)

      loop(crabs.sorted)

    def minFuelInefficientsEngine: Int =
      minFuelToAlign(_.to(1).by(-1).sum)

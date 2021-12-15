package lt.markvl.adventofcode

import scala.annotation.tailrec
import scala.collection.{IterableOnce, mutable}
import scala.collection.mutable.{ListBuffer, Seq}
import scala.runtime.LazyChar
import Lanternfish.{Lanternfish, *}

object Lanternfish:
  opaque type Lanternfish = Int
  def fromString(s: String): Lanternfish = s.toInt

  def schoolSize(school: List[Lanternfish], cycles: Int): Long =

    @tailrec
    def loop(in: Map[Lanternfish, Long], itr: Int = 0): Long =
      if cycles == itr then in.values.sum
      else
        val moms = in.filter((fish, _) => itr >= fish && (itr - fish) % 7 == 0).values.sum
        if (moms > 0) loop(in + ((itr + 9) -> moms), itr + 1)
        else loop(in, itr + 1)

    loop(school.groupBy(k => k).view.mapValues(_.size.toLong).toMap)

  extension (lanternfish: List[Lanternfish])
    def populationSize(days: Int): Long =
      schoolSize(lanternfish, days)

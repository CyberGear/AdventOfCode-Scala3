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
    val lanternMap = school.groupBy(k => k).view.mapValues(_.size.toLong).toMap
    val sizes: mutable.Map[Lanternfish, Long] = mutable.Map(lanternMap.toSeq: _*)
    for (d <- 0 until cycles) {
      val moms = sizes.filter((fish, _) => d >= fish && (d - fish) % 7 == 0).values.sum
      if moms > 0 then sizes += (d + 9) -> moms
    }
    sizes.values.sum

  extension (lanternfish: List[Lanternfish])
    def populationSize(days: Int): Long =
      schoolSize(lanternfish, days)

package lt.markvl.adventofcode

import scala.annotation.tailrec
import scala.collection.{IterableOnce, mutable}
import scala.collection.mutable.{ListBuffer, Seq}
import scala.runtime.LazyChar
import Lanternfish.*

object Lanternfish:
  opaque type Lanternfish = Int
  def fromString(s: String): Lanternfish = s.toInt

  implicit val flatenner: Lanternfish => List[Lanternfish] = _ match
    case 0   => List(6, 8)
    case int => List((int - 1).toShort)

  def schoolSize(school: List[Lanternfish], cycles: Int): Long =
    val mSeq = ListBuffer(school: _*)
    var size = 0L
    while (mSeq.nonEmpty) {
      val fish = mSeq.last
      val count = math.ceil((cycles - fish) / 7.0).toInt
      val children = (0 until count).map(_ * 7 + fish + 9)
      mSeq -= fish ++= children
      size = size + 1
    }
    size

  extension (lanternfish: List[Lanternfish])
    def populationSize(days: Int): Long =
      schoolSize(lanternfish, days)

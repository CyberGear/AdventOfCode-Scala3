package lt.markvl.adventofcode

import scala.annotation.tailrec
import scala.collection.IterableOnce
import scala.collection.parallel.CollectionConverters.seqIsParallelizable
import scala.collection.parallel.immutable.*
import scala.runtime.LazyChar

object Lanternfish:
  opaque type Lanternfish = Short
  def fromString(s: String): Lanternfish = s.toShort

  implicit val flatenner: Lanternfish => List[Lanternfish] = _ match
    case 0: Lanternfish => List(6.toShort, 8.toShort)
    case x: Lanternfish => List((x - 1).toShort)

  def schoolSize(school: List[Lanternfish], cycles: Int): Long =
    dividedSchoolSize(ParSeq(school: _*), cycles)

  @tailrec
  def dividedSchoolSize(
      school: ParSeq[Lanternfish],
      cycles: Int,
      queue: ParSeq[Segment] = ParSeq(),
      acc: Long = 0
  ): Long =
    def log(s: String): Unit =
      println(
        s"$s school: ${school.size}; cycles: $cycles; queue: ${queue.size}; acc: $acc; "
      )

    if cycles == 0 && queue.isEmpty then acc + school.size
    else if school.size > 10000000 then
      log("/\\")
      val (a, b) = school.splitAt(school.size / 2)
      dividedSchoolSize(a, cycles, queue :+ Segment(b, cycles), acc)
    else if cycles == 0 then
      log("\\/")
      dividedSchoolSize(queue.head.school, queue.head.cycles, queue.tail, acc + school.size)
    else dividedSchoolSize(school.flatten, cycles - 1, queue, acc)

case class Segment(school: ParSeq[Short], cycles: Int)

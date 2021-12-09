package lt.markvl.adventofcode

import scala.annotation.tailrec
import scala.collection.IterableOnce
import scala.runtime.LazyChar

import Lanternfish.*

object Lanternfish:
  opaque type Lanternfish = Short
  def fromString(s: String): Lanternfish = s.toShort

extension (lanternfish: List[Lanternfish]) def populationSize(days: Int): Int = ???

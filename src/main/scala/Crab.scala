package lt.markvl.adventofcode

object Crab:
  opaque type Crab = Int
  def fromString(s: String): Crab = s.toInt

  extension (crabs: List[Crab])
    def bestAlignment: Int =
      println(crabs.groupBy(a => a).map((k, count) => k -> k * count.size))
      0

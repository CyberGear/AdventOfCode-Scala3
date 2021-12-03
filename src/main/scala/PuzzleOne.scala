package lt.markvl.adventofcode

object PuzzleOne:

  def countIncreases(input: List[Int]): Int =
    input.sliding(2).count(s => s.head < s.last)

  def countSliding3SumIncreases(input: List[Int]): Int =
    countIncreases(input.sliding(3).map(_.sum).toList)

package lt.markvl.adventofcode.firstTen.basin

import scala.annotation.tailrec

extension (map: List[List[Int]])

  def adjacent(c: Int, length: Int): List[Int] =
    if c == 0 then List(c + 1)
    else if (c == length - 1) then List(c - 1)
    else List(c - 1, c + 1)

  def surroundings(x: Int, y: Int): Seq[(Int, Int)] =
    val coords =
      for
        ax <- adjacent(x, map.length)
        ay <- adjacent(y, map.head.length)
      yield List(x -> ay, ax -> y)
    coords.flatten

  def isLowPoint(x: Int, y: Int): Boolean =
    surroundings(x, y).map((x, y) => map(x)(y)).forall(_ > map(x)(y))

  def lowPoints: Seq[(Int, Int)] =
    for
      x <- map.indices
      y <- map.head.indices
      if isLowPoint(x, y)
    yield x -> y

  def riskLevelSum: Int = lowPoints.map(map(_)(_)).map(_ + 1).sum

  def basinOf(x: Int, y: Int): Int =
    @tailrec
    def loop(area: List[(Int, Int)] = List(x -> y)): Int =
      val newArea = area
        .flatMap((x, y) => surroundings(x, y))
        .distinct
        .diff(area)
        .filterNot((x, y) => map(x)(y) == 9)
      if newArea.isEmpty then area.size
      else loop(newArea ++ area)
    loop()

  def multiplyThreeLargestBasins: Int =
    lowPoints.map((x, y) => basinOf(x, y)).sorted.takeRight(3).reduce(_ * _)

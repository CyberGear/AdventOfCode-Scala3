package lt.markvl.adventofcode
package basin

extension (basin: List[List[Int]])

  def adjacent(c: Int, length: Int): List[Int] =
    if c == 0 then List(c + 1)
    else if (c == length - 1) then List(c - 1)
    else List(c - 1, c + 1)

  def surroundings(x: Int, y: Int): Seq[Int] =
    val coords =
      for
        ax <- adjacent(x, basin.length)
        ay <- adjacent(y, basin.head.length)
      yield List(basin(x)(ay), basin(ax)(y))
    coords.flatten

  def isLowPoint(x: Int, y: Int): Boolean =
    surroundings(x, y).forall(_ > basin(x)(y))

  def lowPoints: Seq[Int] =
    for
      x <- basin.indices
      y <- basin.head.indices
      if isLowPoint(x, y)
    yield basin(x)(y)

  def riskLevelSum: Int = lowPoints.map(_ + 1).sum

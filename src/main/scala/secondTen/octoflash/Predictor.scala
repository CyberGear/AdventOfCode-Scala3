package lt.markvl.adventofcode.secondTen.octoflash

import scala.annotation.tailrec

object Predictor:
  def countFlashes(octopi: Octopi, steps: Int): Int =
    @tailrec
    def iterate(octopi: Octopi, steps: Int, flashes: Int = 0): Int =
      if steps < 0 then flashes
      else iterate(octopi.increment, steps - 1, flashes + octopi.countFlashes)
    iterate(octopi, steps)

  def findTotalFlashStep(octopi: Octopi): Int =
    @tailrec
    def iterate(octopi: Octopi, steps: Int = 0): Int =
      octopi.countFlashes match
        case 100 => steps
        case _   => iterate(octopi.increment, steps + 1)
    iterate(octopi)

case class Octopi(octopi: List[List[Int]]):

  def filterPints(f: Int => Boolean): Seq[Point] = for
    x <- octopi.indices
    y <- octopi.head.indices
    if f(octopi(x)(y))
  yield Point(x, y)

  def adjacent(c: Int, length: Int): List[Int] =
    List(c - 1, c, c + 1).filter(c => c >= 0 && c < length)

  def neighbours(points: Seq[Point]): Seq[Point] = points
    .flatMap(point =>
      for
        xs <- adjacent(point.x, octopi.length)
        ys <- adjacent(point.y, octopi.head.length)
      yield Point(xs, ys)
    )
    .filterNot(points.contains)

  def count(f: Int => Boolean = _ => true): Int = octopi.flatMap(_.filter(f)).size

  def countFlashes: Int = count(p => p == 0 || p > 9)

  def flashes: Seq[Point] = filterPints(v => v > 9)

  def hasFlashes: Boolean = octopi.exists(_.exists(_ > 9))

  @tailrec
  final def radiate(processedFlashes: List[Point] = Nil): Octopi =
    flashes diff processedFlashes match
      case Nil         => this.map(processedFlashes, _ => 0)
      case flashPoints => increment(neighbours(flashPoints)).radiate(processedFlashes ++ flashPoints)

  def increment: Octopi =
    val inc = copy(octopi.map(_.map(_ + 1)))
    if inc.hasFlashes then inc.radiate()
    else inc

  def map(points: Seq[Point], f: Int => Int = _ + 1): Octopi =
    copy(points.foldLeft(octopi) { case (octopi, Point(x, y)) =>
      octopi.updated(x, octopi(x).updated(y, f(octopi(x)(y))))
    })

  def increment(points: Seq[Point]): Octopi = map(points, _ + 1)

  def show(tag: String = ""): Octopi =
    import scala.io.AnsiColor._
    println(tag)

    val flashed = flashes
    val souroundings = neighbours(flashed)
    val nines = filterPints(_ == 9)

    for (i <- octopi.indices)
      for (j <- octopi.head.indices)
        val p = Point(i, j)
        val v = octopi(i)(j)
        val number =
          if flashed.contains(p) && v > 9 then s"$YELLOW_B$BLACK$BOLD + $RESET"
          else if flashed.contains(p) then s"$YELLOW_B$BLACK$BOLD $v $RESET"
          else if souroundings.contains(p) && nines.contains(p) then s"$RED_B$BLACK$BOLD $v $RESET"
          else if souroundings.contains(p) then s"$RED_B$WHITE$BOLD $v $RESET"
          else if nines.contains(p) then s"$GREEN_B$BLACK 9 $RESET"
          else s"$GREEN_B$WHITE $v $RESET"
        print(number)
      println()
    this

object Octopi:
  def apply(octopi: List[List[Int]]): Octopi = new Octopi(octopi)

case class Point(x: Int, y: Int)

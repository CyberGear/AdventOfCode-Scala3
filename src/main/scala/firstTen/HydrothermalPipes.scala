package lt.markvl.adventofcode.firstTen

import monocle.syntax.all.*

object HydrothermalPipes:
  def countDangerousPoints(pipes: List[Pipe]): Int =
    val emptyPlain = Plain.ofSize(pipes.maxX + 1, pipes.maxY + 1)
    val plain = pipes.foldLeft(emptyPlain)((plain, pipe) => plain.draw(pipe))
    plain.countDangerousPoints

case class Point(x: Int, y: Int)

object Point:
  def fromString(s: String): Point =
    val List(x, y) = s.split(",").toList
    Point(x.toInt, y.toInt)

case class Pipe(start: Point, end: Point)

object Pipe:
  def fromString(s: String): Pipe =
    val Array(start, end) = s
      .split(" -> ")
      .map(Point.fromString)
    Pipe(start, end)

extension (pipe: Pipe)
  def horizontal: Boolean = pipe.start.y == pipe.end.y
  def vertical: Boolean = pipe.start.x == pipe.end.x
  def nonDiagonal: Boolean = horizontal || vertical
  def points: List[Point] =
    if horizontal then (pipe.start.x iterate pipe.end.x).map(Point(_, pipe.start.y))
    else if vertical then (pipe.start.y iterate pipe.end.y).map(Point(pipe.start.x, _))
    else
      val Pipe(start, end) = pipe
      val positions = (start.x iterate end.x) zip (start.y iterate end.y)
      positions.map((x, y) => Point(x, y))

extension (pipes: List[Pipe])
  def maxX: Int = pipes.map(p => math.max(p.start.x, p.end.x)).max
  def maxY: Int = pipes.map(p => math.max(p.start.y, p.end.y)).max

case class Plain(points: List[List[Int]])

object Plain:
  def ofSize(x: Int, y: Int): Plain =
    Plain(List.fill(x)(List.fill(y)(0)))

extension (plain: Plain)
  def countDangerousPoints: Int = plain.points.flatten.count(_ > 1)

  def size: (Int, Int) = plain.points.size -> plain.points.head.size

  def show(): Plain =
    val (xSize, ySize) = size
    println()
    for (y <- 0 until ySize)
      for (x <- 0 until xSize)
        print(plain.points(x)(y))
      println()
    plain

  def draw(point: Point): Plain =
    val newValue = plain.points(point.x)(point.y) + 1
    plain
      .focus(_.points)
      .modify(p => p.updated(point.x, p(point.x).updated(point.y, newValue)))

  def draw(pipe: Pipe): Plain =
//    println(pipe)
    pipe.points.foldLeft(plain)((plain, point) => plain.draw(point)) // .show()

extension (start: Int)
  def iterate(end: Int): List[Int] =
    val step = if end > start then 1 else -1
    (start to end by step).toList

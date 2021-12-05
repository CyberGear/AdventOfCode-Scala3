package lt.markvl.adventofcode

import monocle.syntax.all.*

import scala.annotation.tailrec

object Diagnostics:
  def consumption(data: List[String]): Int =
    val state = Statistics(data)
    state.gammaRate * state.epsilonRate

  def lifeSupportRating(data: List[String]): Int =
    val state = Statistics(data)
    state.oxygenGeneratorRating(data) * state.co2ScrubberRating(data)

case class Statistics(size: Int, oneCounts: List[Int])

object Statistics:

  def apply(data: List[String]): Statistics =
    val initialState = Statistics(data.size, List.fill(data.head.length)(0))
    data.foldLeft(initialState)((state, byteString) =>
      state.updateCounts(byteString)
    )

extension (state: Statistics)

  def gammaRate: Int =
    val byteString = state.oneCounts
      .map(c => if c * 2 >= state.size then 1 else 0)
      .mkString
    Integer.parseInt(byteString, 2)

  def epsilonRate: Int =
    val halfCount = state.size / 2.0
    val byteString = state.oneCounts
      .map(c => if c * 2 < state.size then 1 else 0)
      .mkString
    Integer.parseInt(byteString, 2)

  def updateCounts(bits: String): Statistics =
    state
      .focus(_.oneCounts)
      .modify(_.zip(bits.map(_.asDigit)).map((a, b) => a + b))

  @tailrec
  def ratingFilter(
      filter: Statistics => String,
      in: List[String],
      depth: Int = 0
  ): String =
    if in.length == 1 then in.head
    else {
      ratingFilter(
        filter,
        in.filter(_(depth) == filter(Statistics(in))(depth)),
        depth + 1
      )
    }

  def oxygenGeneratorRating(data: List[String]): Int =
    val rating = ratingFilter(s => toBinaryString(s.gammaRate), data)
    Integer.parseInt(rating, 2)

  def co2ScrubberRating(data: List[String]): Int =
    val rating = ratingFilter(s => toBinaryString(s.epsilonRate), data)
    Integer.parseInt(rating, 2)

  def toBinaryString(int: Int): String =
    Integer.toBinaryString(int).reverse.padTo(state.oneCounts.size, '0').reverse

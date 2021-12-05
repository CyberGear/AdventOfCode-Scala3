package lt.markvl.adventofcode

import monocle.syntax.all.*

object FuelConsumption:
  def consumption(data: List[String]): Int =
    val initialState = FuelState(data.size, List.fill(data.head.length)(0))
    val finalState =
      data.foldLeft(initialState)((state, byteString) =>
        state.updateCounts(byteString)
      )
    finalState.gammaRate * finalState.epsilonRate

case class FuelState(size: Int, oneCounts: List[Int])

extension (state: FuelState)

  def gammaRate: Int =
    val halfCount = state.size / 2.0
    val byteString = state.oneCounts
      .map(c => if c > halfCount then 1 else 0)
      .mkString
    Integer.parseInt(byteString, 2)

  def epsilonRate: Int =
    val halfCount = state.size / 2.0
    val byteString = state.oneCounts
      .map(c => if c < halfCount then 1 else 0)
      .mkString
    Integer.parseInt(byteString, 2)

  def updateCounts(bits: String): FuelState =
    state
      .focus(_.oneCounts)
      .modify(_.zip(bits.map(_.asDigit)).map((a, b) => a + b))

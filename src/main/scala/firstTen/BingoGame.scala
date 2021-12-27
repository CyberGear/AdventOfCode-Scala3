package lt.markvl.adventofcode.firstTen

import monocle.syntax.all.*

import scala.annotation.tailrec

object BingoGame:

  @tailrec
  def firstWinBoardScore(choices: List[Int], boards: List[Board]): Int =
    boards.find(_.isVictorious) match
      case Some(board) => board.score
      case None        => firstWinBoardScore(choices.tail, boards.mark(choices.head))

  @tailrec
  def lastWinBoardScore(choices: List[Int], boards: List[Board]): Int =
    boards match
      case board :: Nil if board.isVictorious => board.score
      case _ =>
        val (_, stillPLaying) = boards.partition(_.isVictorious)
        lastWinBoardScore(choices.tail, stillPLaying.mark(choices.head))

case class Board(lastCalledNumber: Option[Int], numbers: List[List[Number]])

sealed trait Number

case class Fresh(x: Int) extends Number
case class Marked(x: Int) extends Number

extension (board: Board)
  def rows: List[List[Number]] = board.numbers
  def columns: List[List[Number]] =
    List
      .fill(board.numbers.size)(board.numbers)
      .zipWithIndex
      .map((tbl, i) => tbl.map(_(i)))
  def isVictorious: Boolean =
    rows.hasFilled || columns.hasFilled
  def score: Int =
    board.lastCalledNumber
      .map(_ * board.numbers.flatten.map {
        case Fresh(x) => x
        case _        => 0
      }.sum)
      .getOrElse(0)

extension (numbers: List[List[Number]])
  def hasFilled: Boolean = numbers.exists(_.forall {
    case _: Marked => true
    case _         => false
  })

extension (boards: List[Board])
  def mark(number: Int): List[Board] =
    boards.map(_.mark(number))

extension (board: Board)
  def mark(number: Int): Board =
    board
      .focus(_.lastCalledNumber)
      .replace(Some(number))
      .focus(_.numbers)
      .modify(_.map(_.map {
        case Fresh(x) if x == number => Marked(number)
        case other                   => other
      }))

package lt.markvl.adventofcode

object SegmentDisplay:
  def countSimpleOutputDigits(entries: List[Entry]): Int =
    entries
      .map(_.output.map(_.length).count(sLength => sLength == 2 || sLength == 3 || sLength == 4 || sLength == 7))
      .sum

enum Digit(val segments: String, output: Int):
  case Zero extends Digit("abcefg", 0)
  case One extends Digit("cf", 1)
  case Two extends Digit("acdeg", 2)
  case Three extends Digit("acdfg", 3)
  case Four extends Digit("bcdf", 4)
  case Five extends Digit("abdfg", 5)
  case Six extends Digit("abdefg", 6)
  case Seven extends Digit("acf", 7)
  case Eight extends Digit("abcdefg", 8)
  case Nine extends Digit("abcdfg", 9)

case class Entry(patterns: List[String], output: List[String])

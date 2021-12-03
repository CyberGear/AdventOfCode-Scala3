package lt.markvl.adventofcode
package utils

import scala.io.Source

trait TestUtils:
  extension (s: String)
    def readLines[A](f: String => A): List[A] =
      val source = Source.fromInputStream(getClass.getResourceAsStream(s))
      val values = source.getLines().map(f).toList
      source.close()
      values

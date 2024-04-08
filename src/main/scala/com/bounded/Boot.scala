package com.bounded

import org.slf4j.LoggerFactory
import scala.io.{Source, StdIn}
import scala.util.{Try, Failure, Success, Using}


object Main {

  private val logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    val lines = args.headOption match {
      case Some(filePath) => readLinesFromFile(filePath)
      case None => readLinesFromStdIn()
    }

    lines match {
      case Success(lines) => processLines(lines)
      case Failure(exception) => logger.error("Failed to read input: ", exception)
    }
  }

  private def readLinesFromFile(filePath: String): Try[List[String]] = {
    Using(Source.fromFile(filePath))(_.getLines().toList)
  }

  private def readLinesFromStdIn(): Try[List[String]] = Try {
    logger.warn("Enter the grid (type 'exit' or a new line to finish):")
    Iterator.continually(StdIn.readLine())
      .takeWhile {
        case "" | null => false
        case line if line.toUpperCase == "EXIT" => false
        case _ => true
      }.toList
  }

  private def processLines(lines: List[String]): Unit = {
    val allLinesSameLength = lines.map(_.length).distinct.length == 1

    if (!allLinesSameLength) {
        logger.error("Error: All lines must be of the same length.")
        sys.exit(1)
    }
    val matrix = lines.map(_.toArray).toArray
    val boxes = BoundingBox.findBoundingBoxes(matrix)
    // SL4J logger has some issues
    boxes.foreach { box =>
      println(s"(${box.topLeft.x},${box.topLeft.y})(${box.bottomRight.x},${box.bottomRight.y})")
    }
  }

}

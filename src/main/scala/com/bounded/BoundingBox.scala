package com.bounded

import scala.collection.mutable

case class Point(x: Int, y: Int)
case class Box(topLeft: Point, bottomRight: Point) {
  def update(point: Point): Box = Box(
    topLeft = Point(math.min(topLeft.x, point.x), math.min(topLeft.y, point.y)),
    bottomRight = Point(math.max(bottomRight.x, point.x), math.max(bottomRight.y, point.y))
  )
}

object BoundingBox {
  def dfs(
    matrix: Array[Array[Char]],
    x: Int,
    y: Int,
    visited: mutable.Set[Point],
    currentBox: Box
  ): Box = {
    if (x < 0 || x >= matrix.length || y < 0 || y >= matrix(0).length || matrix(x)(y) != '*' || visited.contains(Point(x, y))) {
      return currentBox
    }
    visited.add(Point(x, y))
    val updatedBox = currentBox.update(Point(x, y))

    val directions = List((0, 1), (1, 0), (-1, 0), (0, -1))
    directions.foldLeft(updatedBox) { (accBox, dir) =>
      dfs(matrix, x + dir._1, y + dir._2, visited, accBox)
    }
  }

  def findBoundingBoxes(matrix: Array[Array[Char]]): Seq[Box] = {
    val visited = mutable.Set.empty[Point]
    for {
      x <- matrix.indices
      y <- matrix(0).indices
      if matrix(x)(y) == '*' && !visited.contains(Point(x, y))
    } yield {
      val initialBox = Box(Point(x, y), Point(x, y))
      dfs(matrix, x, y, visited, initialBox)
    }
  }

}

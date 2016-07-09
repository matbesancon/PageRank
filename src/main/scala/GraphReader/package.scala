/**
  * Created by mbesancon on 05.07.16.
  */
import scala.io.Source

package object GraphReader{
  def readEdges(path: String): Vector[(Int,Int)] = {
    def inverseLine(line: String): (Int,Int) = {
      val splitLine: Array[Int] = line.split("\t").map(_.toInt)
      (splitLine(1),splitLine(0))
    }
    val stream = getClass.getResourceAsStream(path)
    Source.fromInputStream(stream).getLines().map(inverseLine(_)).toVector
  }
}

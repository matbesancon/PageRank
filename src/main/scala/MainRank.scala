/**
  * Created by mbesancon on 7/9/16.
  */
import GraphReader._
import PageRank._
import java.io
import java.io.PrintWriter

import collection.parallel.ParSeq;

object MainRank {
  def main(args: Array[String]) {
    val graph = readEdges("/Email-Enron.txt")
    val parGraph: ParSeq[(Int,Int)] = graph.par
    // get all nodes
    val nodeId: ParSeq[Int] = parGraph.map{_._1}.toSet.union(parGraph.map{_._2}.toSet).toSeq
    val startRank: ParSeq[Float] = nodeId.map{x=>1.0f/nodeId.size}
    val damp = 0.8f
    val endRank = compRank(startRank,graph,0.4f,0.001f)
    // nodeId.zip(endRank).foreach(tup=>println(tup._1 + " " + tup._2))
    lazy val result: String = nodeId.zip(endRank).map(tup=>(tup._1 + " " + tup._2)).mkString("\n")
    new PrintWriter("resultRank") { write(result); close}
  }

  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }

}

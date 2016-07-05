/**
  * Created by mbesancon on 05.07.16.
  */

import collection.GenSeq
import scala.math.sqrt
import annotation.tailrec

object PageRank {

  type LinkMat = GenSeq[(Int,Int)]

  def sumElements(R: GenSeq[Float], A: LinkMat, j: Int): Float = {
    // sums all PageRanks / number of links for a column j
    val totalLinks = A.filter(_._2==j)
    if (totalLinks.isEmpty) sys.error("No link in the page " + j + " at sumElements")
    else R(j)/totalLinks.size
  }

  // find all pages pointing to i A(i,j) exists
  def findConnected(i: Int, A: LinkMat):GenSeq[Int] = A.filter(_._1==i).map(_._2).toSeq

  def converged(r1: GenSeq[Float], r2: GenSeq[Float], eps: Float): Boolean = {
    val totSquare: Float = r1.zip(r2).map(p=>(p._1-p._2)*(p._1-p._2)).sum
    sqrt(totSquare/r1.size)<=eps
  }

  @tailrec def compRank(R: GenSeq[Float], A: LinkMat, damp: Float, eps: Float, niter: Int = 0,niterMax: Int = 10000): GenSeq[Float] = {
    val rankIndex: GenSeq[Int] = 0 until R.size
    val rightRank: GenSeq[Float] = rankIndex map{i:Int =>
      val connected = findConnected(i,A)
      connected.map{j:Int => sumElements(R, A, j)}.sum
    }
    val newRank = rightRank map {damp*_ + (1-damp)/R.size}
    if(converged(newRank,R,eps)) newRank
    else if(niter>=niterMax) {
      println("Max iteration reached")
      newRank
    } else compRank(newRank,A,damp,eps,niter+1,niterMax)
  }
}

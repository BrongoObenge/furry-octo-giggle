package core

/**
  * Created by jiar on 2-6-16.
  */
class EuclideanDistance() {
  def calculateDistance(client1:Array[Double], client2:Array[Double], index:Int=0, sum:Double=0):Double={
    if(index > client1.length-1) return sum.abs
    calculateDistance(client1, client2, index, client1(index) - client2(index))
  }
}

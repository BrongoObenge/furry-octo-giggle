package core

import config.Config

/**
  * Created by jiar on 2-6-16.
  */
class Kmeans(clusters:Int, maxIterations:Int, dataset:Array[Array[Double]]) {
  val random = scala.util.Random
  val _clusters:Int = clusters
  val _maxIterations:Int = maxIterations
  val _dataset = dataset
  val _cluster:Array[Array[Array[Double]]] = Array(Array(Array()))
  val a:EuclideanDistance = new EuclideanDistance()


  def start(): Unit ={

    val centroids:Array[Array[Double]] = initializeRandomCentroids()

    createCluster(centroids)
  }
  def createCluster(centroids:Array[Array[Double]], index:Int=0, cluster:Array[Array[Double]]=Array[Array[Double]]()): Array[Array[Double]] ={  //Array[Array[Double]]
    if(index > _dataset.length-1) return cluster
    val closest:(Int, Double) = calculateClosestCentroid(centroids, _dataset(index))  //Index of what should be replaced
    val newArr:Array[Array[Double]] = replaceME((closest._1, cluster), cluster(closest._1) ++ _dataset(index))   //Index,
    createCluster(centroids, index+1, newArr)
  }

  def replaceME(replace:(Int, Array[Array[Double]]), replaceVal:Array[Double], index:Int=0, value:Array[Array[Double]]=Array[Array[Double]]()): Array[Array[Double]] ={
    if(index > replace._2.length-1) return value
    if(index == replace._1) replaceME(replace, replaceVal, index+1, value :+ replaceVal)
      else replaceME(replace, replaceVal, index+1, value :+ replace._2(index))
  }
  def calculateClosestCentroid(centroids:Array[Array[Double]], client:Array[Double], index:Int=0, closest:(Int, Double)=(Int.MaxValue,Double.MaxValue)): (Int, Double) ={ //Closest (Index of array, value)
    if(index > centroids.length-1) return closest

    val distance:Double = a.calculateEuclideanDistanceBetweenTwoArrays(client, centroids(index))
    if(distance < closest._2){
      calculateClosestCentroid(centroids, client, index+1, (index ,distance))
    } else {
      calculateClosestCentroid(centroids, client, index+1, closest)
    }

  }


  def initializeRandomCentroids(index:Int=0, value:Array[Array[Double]]=Array[Array[Double]]()):Array[Array[Double]]={
    if (index > _clusters-1){
      if(Config.debug){
        println("==Printing Random Centroids==")
        println(value.foreach((arr) => {arr.foreach((line) => {print(line+",")});print("\n")}))
        println("\n\n\n\n")
      }
      return value
    }
    val tempVal:Array[Array[Double]] = Array(fillRandomSpace(32))
    initializeRandomCentroids(index+1, value ++ tempVal)
  }

  def fillRandomSpace(dimentions:Int, index:Int=0, value:Array[Double]=Array[Double]()):Array[Double]={
    if (index > dimentions-1) return value  //32 dimentions
    if(this.random.nextInt()>5){
      val arr:Array[Double] = Array(1)
      fillRandomSpace(dimentions, index+1, value++arr)
    }else{
      val arr:Array[Double] = Array(0)
      fillRandomSpace(dimentions, index+1, value++arr)
    }
  }


}

package core

import scala.io.Source
import config.Config
/**
  * Created by jiar on 2-6-16.
  */
class DataService {

  def getWineData():Array[Array[Double]]= {
    val source = Source.fromFile(Config.wineDataLocation)
    var matrix:Array[Array[Double]] = Array.ofDim[Double](100, 32)
    try {
      var anotherIndex = 0
      for (line <- source.getLines()) {
        var index = 0
        val arr = line.split(",")
        arr.foreach((l) => {
          matrix(index)(anotherIndex) = l.toDouble
          index +=1
        })
        anotherIndex += 1
      }
    } finally {
      source.close()
    }
    if(Config.debug){
      println("==Printing Data Matrix==")
      println(matrix.foreach((arr) => {arr.foreach((line) => {print(line+",")});print("\n")}))
      println("--Printing Data Matrix--")
    }
    matrix
  }
}

package core

/**
  * Created by jiar on 2-6-16.
  */
object Main extends App {
  val data:Array[Array[Double]] = new DataService().getWineData()
  val a:Kmeans = new Kmeans(4,4, data)
  printCluster(a.start())


  def printCluster(cluster:Array[Array[Array[Double]]]): Unit ={
    var index = 0

    cluster.foreach((c) => {
      println("====cluster"+index+"=====")
      var anotherIndex = 0
      c.foreach((e) =>{
        print("client"+anotherIndex +" bought: ")
        var yetAnotherIndex=0
        e.foreach((h)=>{
          yetAnotherIndex=yetAnotherIndex+1
          if(h == 1){
            print(" " +yetAnotherIndex)
          }
        })
        anotherIndex = anotherIndex+1
        println("")
      })
      println("\n")
      index = index+1
    })
  }
}
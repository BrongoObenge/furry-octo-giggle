package core

/**
  * Created by jiar on 2-6-16.
  */
object Main extends App {
  val data:Array[Array[Double]] = new DataService().getWineData()
  val a:Kmeans = new Kmeans(4,4, data)
  a.start()
}
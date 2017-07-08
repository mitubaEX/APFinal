/**
  * Created by mituba on 2017/07/06.
  */
package advanced

import scala.io.Source

class FileReader(filenameArray: Array[String]) {
  def getTemperatureList(readLineList: List[Array[String]]): List[(Double, Double, Double)]  =
    (for(s <- readLineList) yield (s(4).toDouble, s(5).toDouble, s(6).toDouble)).toList

  def getReadLineList(filename: String) =
    Source.fromFile(filename).getLines().map(n => n.split(",")).toList

  def getMergedTemperatureList(filenameList: List[String]): List[(Double, Double, Double)] =
    filenameList match {
      case (head :: Nil) => getTemperatureList(getReadLineList(head))
      case (head :: tail) => getTemperatureList(getReadLineList(head)) ++ getMergedTemperatureList(tail)
      case _ => List((0.0, 0.0, 0.0))
    }

  def read: Unit ={
    val temperatureList = getMergedTemperatureList(filenameArray.toList)
    val average = new CalcAveMaxMin().getAverage(temperatureList)
    val max = new CalcAveMaxMin().getMax(temperatureList)
    val min = new CalcAveMaxMin().getMin(temperatureList)
    printf("%f,%f,%f\n", max, min, average)
  }
}

package advanced

import scala.io.Source

/**
  * Created by mituba on 2017/07/08.
  */
class FileReaderFrequency(filenameArray: Array[String]) extends FileReader(filenameArray){
  def getTemperatureStringList(readLineList: List[Array[String]]): List[String]  =
    (for(s <- readLineList) yield s(7)).toList

  def getMergedTemperatureStringList(filenameList: List[String]): List[String] =
    filenameList match {
      case (head :: Nil) => getTemperatureStringList(getReadLineList(head))
      case (head :: tail) => getTemperatureStringList(getReadLineList(head)) ++ getMergedTemperatureStringList(tail)
      case _ => List("")
    }

  def getPutMap(temp: String, map: Map[String, Int]): Map[String, Int] =
    map.updated(temp, map.getOrElse(temp, 0) + 1)

  def getFrequencyMap(temperatureList: List[String], map: Map[String, Int] = Map.empty[String, Int]): Map[String, Int] ={
    temperatureList match {
      case (head :: Nil) => getPutMap(head, map)
      case (head :: tail) => getFrequencyMap(tail, getPutMap(head, map))
      case _ => map
    }
  }

  override
  def read: Unit = {
    val temperatureList = getFrequencyMap(getMergedTemperatureStringList(filenameArray.toList))
    temperatureList.foreach(n => println(n._1 + "," + n._2.toString))
  }
}

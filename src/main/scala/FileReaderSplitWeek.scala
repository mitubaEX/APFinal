package advanced

import scala.io.Source

/**
  * Created by mituba on 2017/07/14.
  */
class FileReaderSplitWeek(filenameArray: Array[String]) extends FileReader(filenameArray){
  def getReadLineStringList(filename: String): List[Array[String]] =
    Source.fromFile(filename).getLines().map(n => n.split(",")).toList

  def getMergedTemperatureStringList(filenameList: List[String]): List[Array[String]] =
    filenameList match {
      case (head :: Nil) => getReadLineStringList(head)
      case (head :: tail) => getReadLineStringList(head) ++ getMergedTemperatureStringList(tail)
      case _ => List(Array.empty[String])
    }

  def getFilteredList(tempList: List[Array[String]]): List[Int] =
    tempList.zipWithIndex.filter(n => n._1(3) == "月").map(n => n._2)

  def getWeeklyScoreCalc(tempList: List[Array[String]], index: Int): String = {
    val calc = new CalcAveMaxMin()
    (index to index + 6).foreach(println)
    var end = 0
    println(tempList.size)
    if(tempList.size - (index + 6) < 0)
      end = tempList.size - 1
    else
      end = index + 6
    val weekList = (index to end).map(n => ((tempList(n)) (0), (tempList(n)) (1), (tempList(n)) (2), (tempList(n)) (4).toDouble, (tempList(n)) (5).toDouble, (tempList(n)) (6).toDouble)).toList
    weekList(0)._1 + "-" + weekList(0)._2 + "-" + weekList(0)._3 + "," + calc.getMaxWeek(weekList) + "," + calc.getMinWeek(weekList) + "," + calc.getAverageWeek(weekList)
  }


  def getWeeklyScore(tempList: List[Array[String]], filteredList: List[Int]): List[String] =
    filteredList.map(n => getWeeklyScoreCalc(tempList, n))

  override
  def read: String = {
    val temperatureList = getMergedTemperatureStringList(filenameArray.toList)
    val filteredList = getFilteredList(temperatureList)
    getWeeklyScore(temperatureList, filteredList).mkString("\n")

  }
}

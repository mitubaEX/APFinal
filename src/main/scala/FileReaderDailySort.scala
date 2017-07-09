package advanced

/**
  * Created by mituba on 2017/07/09.
  */
class FileReaderDailySort(filenameArray: Array[String]) extends FileReader(filenameArray){
  def getTemperatureDailyList(readLineList: List[Array[String]]): List[(String, String, Double, Double, Double)]  =
    (for(s <- readLineList) yield (s(0), s(1), s(4).toDouble, s(5).toDouble, s(6).toDouble)).toList

  def getMergedTemperatureDailyList(filenameList: List[String]): List[(String, String, Double, Double, Double)] =
    filenameList match {
      case (head :: Nil) => getTemperatureDailyList(getReadLineList(head))
      case (head :: tail) => getTemperatureDailyList(getReadLineList(head)) ++ getMergedTemperatureDailyList(tail)
      case _ => List(("", "", 0.0, 0.0, 0.0))
    }

  def getPutMap(year: String, month: String, dailyMap: List[(String, String)]): List[(String, String)] =
    dailyMap :+ (year, month)

  def getDailyMap(mergedTemperatureDailyList: List[(String, String, Double, Double, Double)], list: List[(String, String)] = List.empty[(String, String)]): List[(String, String)] =
    mergedTemperatureDailyList match {
      case (head :: Nil) => getPutMap(head._1, head._2, list)
      case (head :: tail) => getDailyMap(tail, getPutMap(head._1, head._2, list))
      case _ => list
    }

  def getFilteredDailyList(list: List[(String, String, Double, Double, Double)], dailyMap: List[(String, String)]): List[List[(String, String, Double, Double, Double)]] = {
    dailyMap.map(n => list.filter(i => i._1 == n._1).filter(i => i._2 == n._2)).toList
  }

  override
  def read: String = {
    val mergedTemperatureDailyList = getMergedTemperatureDailyList(filenameArray.toList)
    val sortedList = mergedTemperatureDailyList.sortWith(_._1 > _._1).sortWith(_._2 > _._2)
    val dailyMap = getDailyMap(sortedList)
    val filteredDailyList = getFilteredDailyList(sortedList, dailyMap.distinct)
    val calc = new CalcAveMaxMin
    filteredDailyList.map(n => "%s-%s,%f,%f,%f".format(n(0)._1, n(0)._2, calc.getMaxDaily(n), calc.getMinDaily(n), calc.getAverageDaily(n))).mkString("\n")
  }
}

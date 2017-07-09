package advanced

/**
  * Created by mituba on 2017/07/09.
  */
class FileReaderFrequencyCalc(filenameArray: Array[String]) extends FileReader(filenameArray){
  def getTemperatureStringList(readLineList: List[Array[String]]): List[(String, Double, Double, Double)]  =
    (for(s <- readLineList) yield (s(7), s(4).toDouble, s(5).toDouble, s(6).toDouble)).toList

  def getMergedTemperatureStringList(filenameList: List[String]): List[(String, Double, Double, Double)] =
    filenameList match {
      case (head :: Nil) => getTemperatureStringList(getReadLineList(head))
      case (head :: tail) => getTemperatureStringList(getReadLineList(head)) ++ getMergedTemperatureStringList(tail)
      case _ => List(("", 0.0, 0.0, 0.0))
    }

  def getPutMap(temp: String, map: Map[String, Int]): Map[String, Int] =
    map.updated(temp, map.getOrElse(temp, 0) + 1)

  def getPutListMap(temp: String,
                    tupple: (Double, Double, Double),
                    listMap: Map[String, List[(Double, Double, Double)]]): Map[String, List[(Double, Double, Double)]] =
    listMap.updated(temp, listMap.getOrElse(temp, List(tupple)) :+ tupple)

  def getFrequencyMap(temperatureList: List[(String, Double, Double, Double)],
                      map: Map[String, Int] = Map.empty[String, Int]): Map[String, Int] ={
    temperatureList match {
      case (head :: Nil) => getPutMap(head._1, map)
      case (head :: tail) => getFrequencyMap(tail, getPutMap(head._1, map))
      case _ => map
    }
  }

  def getFrequencyListMap(temperatureList: List[(String, Double, Double, Double)],
                      listMap: Map[String, List[(Double, Double, Double)]] = Map.empty[String, List[(Double, Double, Double)]]): Map[String, List[(Double, Double, Double)]] ={
    temperatureList match {
      case (head :: Nil) => getPutListMap(head._1, (head._2, head._3, head._4),listMap)
      case (head :: tail) => getFrequencyListMap(tail, getPutListMap(head._1, (head._2, head._3, head._4), listMap))
      case _ => listMap
    }
  }

  override
  def read: String = {
    val mergedTemperatureStringList = getMergedTemperatureStringList(filenameArray.toList)
    val temperatureList = getFrequencyMap(mergedTemperatureStringList)
    val temperatureDataMapList = getFrequencyListMap(mergedTemperatureStringList)
    val calc = new CalcAveMaxMin
    temperatureList.map(n => n._1 + "," + n._2.toString
      + "," + "%f".format(calc.getMax(temperatureDataMapList.get(n._1).get))
      + "," + "%f".format(calc.getMin(temperatureDataMapList.get(n._1).get))
      + "," + "%f".format(calc.getAverage(temperatureDataMapList.get(n._1).get))
    ).mkString("\n")
  }
}

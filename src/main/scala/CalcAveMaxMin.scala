/**
  * Created by mituba on 2017/07/08.
  */
package advanced

class CalcAveMaxMin() {
  def getAverage(temperatureList: List[(Double, Double, Double)], sum: Double = 0.0, count: Double = 0.0): Double =
    temperatureList match {
      case (head :: Nil) => (sum + head._1) / (count + 1.0)
      case (head :: tail) => getAverage (tail, sum + head._1, count + 1.0)
      case _ => sum / count
    }

  def getMax(temperatureList: List[(Double, Double, Double)], max: Double = 0.0): Double =
    temperatureList match {
      case (head :: Nil) => Math.max(max, head._2)
      case (head :: tail) => getMax (tail, Math.max(max, head._2))
      case _ => max
    }

  def getMin(temperatureList: List[(Double, Double, Double)], min: Double = Double.MaxValue): Double =
    temperatureList match {
      case (head :: Nil) => Math.min(min, head._3)
      case (head :: tail) => getMin (tail, Math.min(min, head._3))
      case _ => min
    }

  def getAverageDaily(temperatureList: List[(String, String, Double, Double, Double)], sum: Double = 0.0, count: Double = 0.0): Double =
    temperatureList match {
      case (head :: Nil) => (sum + head._3) / (count + 1.0)
      case (head :: tail) => getAverageDaily (tail, sum + head._3, count + 1.0)
      case _ => sum / count
    }

  def getMaxDaily(temperatureList: List[(String, String, Double, Double, Double)], max: Double = 0.0): Double =
    temperatureList match {
      case (head :: Nil) => Math.max(max, head._4)
      case (head :: tail) => getMaxDaily (tail, Math.max(max, head._4))
      case _ => max
    }

  def getMinDaily(temperatureList: List[(String, String, Double, Double, Double)], min: Double = Double.MaxValue): Double =
    temperatureList match {
      case (head :: Nil) => Math.min(min, head._5)
      case (head :: tail) => getMinDaily (tail, Math.min(min, head._5))
      case _ => min
    }
}

package advanced

import java.io.PrintWriter

object Main{
  def main(args: Array[String]){
    val four = new FileReaderDailySort(args).read // 4
//    new FileReaderFrequency(args).read // 3
    val five = new FileReaderFrequencyCalc(args).read // 5
    val one = new FileReader(args).read //1
    val two = new FileReaderFilterSunny(args).read //2
    val two2 = new FileReaderFilterRain(args).read //2
    val six = new FileReaderSplitWeek(args).read //6
    println(four + "\n" + five + "\n全体：" + one + "\n晴：" + two + "\n雨：" + two2)
    fileWrite(List(six))
  }

  def fileWrite(list: List[String]): Unit ={
    val file = new PrintWriter("weekly-temperatures.csv")
    file.write(list.mkString("\n"))
    file.close()
  }
}

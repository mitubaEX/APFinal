package advanced

object Main{
  def main(args: Array[String]){
    new FileReaderDailySort(args).read // 4
//    new FileReaderFrequency(args).read // 3
    new FileReaderFrequencyCalc(args).read // 5
    print("全体：")
    new FileReader(args).read //1
    print("晴：")
    new FileReaderFilterSunny(args).read //2
    print("雨：")
    new FileReaderFilterRain(args).read //2
  }
}

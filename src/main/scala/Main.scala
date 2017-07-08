package advanced

object Main{
  def main(args: Array[String]){
    new FileReaderDailySort(args).read // 4
    new FileReaderFrequency(args).read // 3
    new FileReader(args).read //1
    new FileReaderFilterSunny(args).read //2
    new FileReaderFilterRain(args).read //2
  }
}

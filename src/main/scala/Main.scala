package advanced

object Main{
  def main(args: Array[String]){
    println(args(0))
    new FileReader(args).read
    new FileReaderFilterSunny(args).read
    new FileReaderFilterRain(args).read
  }
}

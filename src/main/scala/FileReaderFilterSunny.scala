/**
  * Created by mituba on 2017/07/08.
  */
package advanced
import scala.io.Source

class FileReaderFilterSunny(filenameArray: Array[String]) extends FileReader(filenameArray){
  override
  def getReadLineList(filename: String) =
    Source.fromFile(filename)
      .getLines()
      .map(n => n.split(","))
      .filter(n => n(7).contains("晴") || n(8).contains("晴"))
      .toList
}

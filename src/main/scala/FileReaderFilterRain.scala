/**
  * Created by mituba on 2017/07/08.
  */
package advanced

import scala.io.Source

class FileReaderFilterRain(filenameArray: Array[String]) extends FileReader(filenameArray){
  override
  def getReadLineList(filename: String) =
    Source.fromFile(filename)
      .getLines()
      .map(n => n.split(","))
      .filter(n => n(7).contains("雨") || n(8).contains("雨"))
      .toList
}

package util
import java.io.{BufferedReader, InputStreamReader}
import java.net.URL
class FetchUrls(url:String) {

  def fetchData:StringBuilder={

    val urlObj = new URL(url)
    val in = new BufferedReader(new InputStreamReader(urlObj.openStream))
    var inputLine = in.readLine
    var sb = new StringBuilder("")
    while (inputLine != null) {
    sb ++= inputLine.trim
      inputLine = in.readLine
    }
    sb
  }

}

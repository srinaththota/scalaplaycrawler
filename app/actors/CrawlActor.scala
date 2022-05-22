package actors

import akka.actor.Actor
import util.FetchUrls

class CrawlActor(fetcher:String) extends Actor{

  def receive:Receive={
    case url:String => sender ! new FetchUrls(url).listDownUrls
  }
}

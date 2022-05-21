package services

import actors.{CrawlActor}

import javax.inject.Inject
import akka.actor.{ActorSystem, Props}
object Fetcher{
  def props = Props(new CrawlActor("crawlerTypeFetch"))
}
class CrawlerService  @Inject() (system:ActorSystem){

  def ListProductUrls(url:String):Unit={
    val user = system.actorOf(Fetcher.props)
    user ! url
  }
}

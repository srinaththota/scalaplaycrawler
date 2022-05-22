package services

import actors.CrawlActor

import scala.util.{Failure, Success}
import javax.inject.Inject
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._
import scala.language.postfixOps

object Fetcher{
  def props = Props(new CrawlActor("crawlerTypeFetch"))
}
class CrawlerService  @Inject() (system:ActorSystem){

  def fetchData(url:String):Any = {

    val user = system.actorOf(Fetcher.props)
    implicit val timeout:Timeout = Timeout(6 seconds)
    val actorData = user ? url

    val res=Await.result(actorData,6.seconds)

    res
  }
}

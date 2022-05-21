package actors

import akka.actor.Actor

class CrawlActor(fetcher:String) extends Actor{

  def receive:Receive={
    case _ => println("hi")
  }
}

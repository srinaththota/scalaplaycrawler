package controllers
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import net.liftweb.json._
import net.liftweb.json.Serialization.write
import services.CrawlerService

import javax.inject.Inject
import scala.concurrent.ExecutionContext


case class Response(url: String, data: String)

class HomeController @Inject()(cc:ControllerComponents,crawlerService:CrawlerService)(implicit ec: ExecutionContext) extends AbstractController(cc){
  def index:Action[AnyContent] = Action { implicit request =>
    var list:List[String]=List.empty
    val body: AnyContent          = request.body
    val jsonBody: Option[JsValue] = body.asJson
    jsonBody
      .map { json =>
        implicit val formats = DefaultFormats
        val arr=((json \ "url").get)
        val regex = "(\".*?\")".r
        val arrContent = arr.toString()
        val iterator = regex findAllIn arrContent
        while(iterator.hasNext){
          var url = iterator.next()
          url = url.replaceAll("\"","");
          val response = Response(url,crawlerService.fetchData(url).toString)
          val jsonString = write(response)
          list ::= jsonString
        }
      }
      .getOrElse {
        BadRequest("Expecting application/json request body")
      }
    implicit val formats = DefaultFormats
      val finalresponse = write(list)
    Ok(finalresponse)
}
}



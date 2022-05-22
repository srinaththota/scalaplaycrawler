package controllers
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.CrawlerService

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class HomeController @Inject()(cc:ControllerComponents,crawlerService:CrawlerService)(implicit ec: ExecutionContext) extends AbstractController(cc){
  def index:Action[AnyContent] = Action { implicit request =>
    val body: AnyContent          = request.body
    val jsonBody: Option[JsValue] = body.asJson
    jsonBody
      .map { json =>

        Ok((json \ "url").as[String] + "data "+crawlerService.ListProductUrls((json \ "url").as[String]))
      }
      .getOrElse {
        BadRequest("Expecting application/json request body")
      }
   //Ok(s"response $request")
}
}



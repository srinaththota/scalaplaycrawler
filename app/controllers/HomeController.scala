package controllers
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents}
import services.CrawlerService

import javax.inject.Inject

class HomeController @Inject()(cc:ControllerComponents,crawlerService:CrawlerService)  extends AbstractController(cc){
  def index = Action { implicit request =>
    val body: AnyContent          = request.body
    val jsonBody: Option[JsValue] = body.asJson
    jsonBody
      .map { json =>
        crawlerService.ListProductUrls((json \ "url").as[String])
        Ok("Got: " + (json \ "url").as[String])
      }
      .getOrElse {
        BadRequest("Expecting application/json request body")
      }
   //Ok(s"response $request")
}
}



package controllers
import play.api.mvc.{AbstractController, ControllerComponents}


import javax.inject.Inject

class HomeController @Inject()(cc:ControllerComponents)  extends AbstractController(cc){
  def index = Action { implicit request =>
   Ok("response")
}
}

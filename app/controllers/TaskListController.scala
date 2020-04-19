package controllers

import Model.ToDoClass
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import services.{TaskListService, TaskListServiceImpl}

import scala.util.Try


@Singleton
class TaskListController @Inject()(cc: ControllerComponents, taskListServiceImpl: TaskListServiceImpl) extends AbstractController(cc) {
  def toDoPage = Action {
    Ok(views.html.ToDoPage())
  }

  def showTaskList = Action {
    Try {
      val httpRes = taskListServiceImpl.showTaskListSvc
      val lst = httpRes.flatMap {
        td =>
          List(
            td.id, td.name, td.description
          )
      }
      Ok("Current Pending tasks are: " + lst)
    }.getOrElse {
      Ok("Oops")
    }
  }

  def getitem = Action {
    Ok("Some result")
  }

  def addToDoItem = Action { request =>
    val postVals = request.body.asFormUrlEncoded
    postVals.map {
      args =>
        val name = args("name").head
        val description = args("description").head
        val httpRes = taskListServiceImpl.addToDoItemSvc(name, description)
        Ok(s"number of records added: $httpRes")
    }.getOrElse(Ok("Oops"))

  }

  def updateToDoItem(id: Int, name: String, description: String) = Action {
    val httpRes = taskListServiceImpl.updateToDoItemSvc(id, name, description)
    Ok(s"$httpRes")
  }

  def deleteToDoItem(id: Int) = Action {
    val httpRes = taskListServiceImpl.deleteToDoItemSvc(id)
    Ok(s"$httpRes")
  }
}

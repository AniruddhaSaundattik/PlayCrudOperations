package services

import java.sql.ResultSet

import Model.ToDoClass
import javax.inject.Inject
import repository.TaskListRepository

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

class TaskListServiceImpl @Inject()(tlr: TaskListRepository) extends TaskListService {
  override def showTaskListSvc(): List[ToDoClass] = {
    var result: List[ToDoClass] = Nil
    val f = Future {
      tlr.getTaskList
    }
    f.onComplete{
      case Success(value) => result = value
      case Failure(exception) => println("Exception occured", exception)
    }
    Thread.sleep(2000)
    result
  }

  override def addToDoItemSvc(name: String, description: String) : Int = {
    var result: Int = 0
    val f = Future {
      tlr.addItem(name,description)
    }
    f.onComplete{
      case Success(value) => result = value
      case Failure(exception) => println("Exception Occured",exception)
    }
    Thread.sleep(2000)
    result
  }

  override def updateToDoItemSvc(id: Int, name: String, description: String) = {
     tlr.updateItem(id,name,description)
  }

  override def deleteToDoItemSvc(id: Int): Boolean = {
    return tlr.deleteItem(id)
  }
}

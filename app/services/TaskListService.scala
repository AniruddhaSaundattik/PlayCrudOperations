package services

import java.sql.ResultSet

import Model.ToDoClass


trait TaskListService  {
  def showTaskListSvc(): List[ToDoClass]
  def addToDoItemSvc(name: String, description: String): Int
  def updateToDoItemSvc(id: Int, name: String, description: String): Boolean
  def deleteToDoItemSvc(id: Int): Boolean
}

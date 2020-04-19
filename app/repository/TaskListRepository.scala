package repository

import java.sql.{Connection, DriverManager, Statement}

import Model.ToDoClass

class TaskListRepository  {
    //Connection with database
    Class.forName("org.h2.Driver")
    val conn: Connection = DriverManager.getConnection("jdbc:h2:~/test", "sa","")
    val st: Statement = conn.createStatement()

    def getTaskList: List[ToDoClass] ={
      val result = st.executeQuery("select * from tasklisttable")
      var taskList : List[ToDoClass] = Nil
      while(result.next()){
        val ToDoClassItem = new ToDoClass(result.getInt("id"),result.getString("name"),result.getString("description"))
        taskList = taskList.+:(ToDoClassItem)
      }
      taskList
    }

    def addItem(name: String, description: String) ={
      val sqlEx = s"insert into tasklisttable (name,description) values('$name','$description')"
      val result = st.executeUpdate(sqlEx)
      result
    }

    def updateItem(id: Int, name: String, description: String) = {

      //if(name.isDefined & description.isDefined){
        val result = st.execute(s"update tasklisttable set name = $name, description = $description where id = $id")
        result
      /*} else if (name.isDefined){
         st.execute(s"update tasklisttable set name = $name where id = $id")
      } else if(description.isDefined){
         st.execute(s"update tasklisttable set description = $description where id = $id")
      }*/


    }

  def deleteItem(id: Int) = {
    st.execute(s"delete from tasklisttable where id = $id   ")
  }


 // conn.close()

}

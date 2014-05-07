
import io.zhpooer.estore.utils.TransactionManager

object HelloWorld extends App {
  println("Hello World")
//  TransactionManager.getConnection();
//  val stmt = TransactionManager.getConnection().createStatement();
//  val sql = "insert into user( email, password, nickname, role, active, activecode)"+
//  "values('zhpooer@gmail.com', '123', 'zhpooer', 'normal', 0, null)"
//  stmt.executeUpdate(sql);
  
  try{
    val a = 10/0;
  }catch {
    case e:Exception =>
      throw new RuntimeException(e)
  } finally {
    println("close it")
  }
}


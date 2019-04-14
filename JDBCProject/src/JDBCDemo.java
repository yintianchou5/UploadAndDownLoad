import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//数据库驱动
//					驱动jar						具体驱动类								连接字符串
//Oracle		ojdbc-x.jar				oracle.jdbc.OracleDriver					jdbc:oracle:thin:@localhost:1521:ORCL
//MySQL		mysql-connector-java-x.jar		com.mysql.jdbc.Driver					jdbc:mysql://localhost:3306/数据库实例名
//SqlServer	sqljdbc-x.jar		com.microsoft.sqlserver.jdbc.SQLServerDriver		jdbc:microsoft:sqlserver:localhost:1433;databasename=数据库实例名
//
//使用jdbc操作数据库时，如果对数据库进行了更换，只需要替换：驱动、具体驱动类、连接字符串、用户名、密码
public class JDBCDemo {
	private static final String URL="jdbc:mysql://localhost:3306/books";
	private static final String USERNAME="Tiger";
	private static final String PWD="123456";
	
	public static void update(){
		Connection connection=null;
		Statement stmt=null;
		try {
		//a.导入驱动，加载具体的驱动类
		Class.forName("com.mysql.jdbc.Driver");	
		//b.与数据库建立连接
		connection =DriverManager.getConnection(URL, USERNAME,PWD);
		//c.发送sql，执行
		stmt= connection.createStatement();
		//String sql="insert into users values(1,'zs')";
		//String sql="update users set name='ww' where id=1";
		String sql="delete from users where id=1";
		int count=stmt.executeUpdate(sql);
		//d.处理结果集 （查询）
		if(count>0) {
			System.out.println("操作成功！");
		}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			
				try {
					if(stmt!=null) stmt.close();
					if(connection!=null) 	connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	public static void query() {
		Connection connection=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
		//a.导入驱动，加载具体的驱动类
		Class.forName("com.mysql.jdbc.Driver");	
		//b.与数据库建立连接
		connection =DriverManager.getConnection(URL, USERNAME,PWD);
		//c.发送sql
		stmt= connection.createStatement();
		String sql="select * from users";
		//执行
		rs=stmt.executeQuery(sql);
		//d.处理结果集 （查询）
		while(rs.next()) {
			int uno=rs.getInt("id");
			String uname=rs.getString("name");
			//int uno=rs.getInt(1);
			//String uname=rs.getString(2);
			System.out.println(uno+"---"+uname);
		}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			
				try {
					if(rs!=null) rs.close();
					if(stmt!=null) stmt.close();
					if(connection!=null) 	connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	public static void main(String[] args) {
		//update();
		query();
	}
}

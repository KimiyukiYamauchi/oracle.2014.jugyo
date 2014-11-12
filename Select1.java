import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Select1 {
	private String _user = "yamauchi";
	private String _pass = "password";
	private String _host = "172.16.40.4"; // Oracle
	//private String _host = "localhost"; // MySQL
	private String _sid = "db11";
	
	public static void main(String[] args){

		Select1 sample = new Select1();
		try {
			sample.select();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void select() throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle
			//Class.forName("org.gjt.mm.mysql.Driver"); // MySQL
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + _host + ":1521:" + _sid, _user, _pass); // Oracle
					//"jdbc:mysql://" + _host + "/" + _sid, _user, _pass); // MySQL
			
			String sql = "select e.empno, e.ename, e.job, m.ename, dname, loc ";
			sql += "from employees e left outer join employees m on e.mgr = m.empno ";
			sql += "join departments d  on e.deptno = d.deptno ";
			sql += "order by e.empno";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			
			System.out.printf("%2s %2s %2s %2s %4s %s\n", "番号", "名前", "職種", "上司", "部門", "場所");
			System.out.println("------------------------------");

			while(rs.next()){
				String empno = rs.getString(1);
				String ename = rs.getString(2);
				String job = rs.getString(3);
				String mgr = rs.getString(4);
				String dname = rs.getString(5);
				String loc = rs.getString(6);

				System.out.printf("%s %s %s %s %s %s\n", empno, ename, job, mgr, dname, loc);
			}
		}catch(ClassNotFoundException e){
			throw e;
		}catch(SQLException e){
			throw e;
		}catch(Exception e){
			throw e;
		}finally{
			if(conn != null){
				conn.close();
			}
			if(ps != null){
				ps.close();
				ps = null;
			}
			if(rs != null){
				rs.close();
				rs = null;
			}
		}
	}
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Sample {
	private String _user = "yamauchi";
	private String _pass = "password";
	private String _host = "172.16.40.4";
	private String _sid = "db11";
	
	public static void main(String[] args){

		Sample sample = new Sample();
		try {
			sample.select();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void select() throws Exception{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + _host + ":1521:" + _sid, _user, _pass);

String sql = "select e.empno, e.ename, e.job, m.ename, dname, loc ";
sql += "from employees e left outer join employees m ";
sql += "on e.mgr = m.empno ";
sql += "join departments d ";
sql += "on e.deptno = d.deptno";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()){
				String dept_id = rs.getString(1);
				String dept_name = rs.getString(2);

				System.out.printf("%s\t%s\t\n", dept_id, dept_name);
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
			if(st != null){
				st.close();
				st = null;
			}
			if(rs != null){
				rs.close();
				rs = null;
			}
		}
	}
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.Scanner;

public class Delete1 {
	private String _user = "yamauchi";
	private String _pass = "password";
	private String _host = "172.16.40.4"; // Oracle
	//private String _host = "localhost"; // MySQL
	private String _sid = "db11";
	private static int count = 0; // 件数をカウント	
	
	public static void main(String[] args){
		Scanner stdIn = new Scanner(System.in);

		Delete1 sample = new Delete1();
		try {

			sample.select();
			System.out.println();

			System.out.print("社員番号：");
			int empno = stdIn.nextInt();
			System.out.println();

			sample.select(empno);
			System.out.print("削除しますか(yes/no)");
			String delete_kakunin = stdIn.next();
			System.out.println();
			if(delete_kakunin.equals("yes")){
				if(count != 0){
					System.out.println("部下がいる場合は削除できません");
				}else{
					sample.delete(empno);
				}
			}

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
			
			System.out.printf("%2s %-3s %2s %2s %-4s %s\n", "番号", "名前", "職種", "上司", "部門", "場所");
			System.out.println("------------------------------");

			while(rs.next()){
				String empno = rs.getString(1);
				String ename = rs.getString(2);
				String job = rs.getString(3);
				String mgr = rs.getString(4);
				String dname = rs.getString(5);
				String loc = rs.getString(6);

				System.out.printf("%2s %-3s %2s %2s %-4s %s\n", empno, ename, job, mgr, dname, loc);
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

	private void select(int pempno) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle
			//Class.forName("org.gjt.mm.mysql.Driver"); // MySQL
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + _host + ":1521:" + _sid, _user, _pass); // Oracle
					//"jdbc:mysql://" + _host + "/" + _sid, _user, _pass); // MySQL
			
			String sql = "select empno, ename ";
			sql += "from employees ";
			sql += "where empno = ? order by empno";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, pempno);

			rs = ps.executeQuery();
			//System.out.println(sql);			
			
			while(rs.next()){
				count++;

				String empno = rs.getString(1);
				String ename = rs.getString(2);

				System.out.printf("社員番号  ： %s\n", empno);
				System.out.printf("社員名    ： %s\n", ename);
			}

			if(count!=0){
				sql = "select ename ";
				sql += "from employees ";
				sql += "where mgr = ?";

				ps = conn.prepareStatement(sql);
				ps.setInt(1, pempno);

				rs = ps.executeQuery();

				System.out.println("部下  ：");

				count = 0; // 今度は部下の人数をカウント
				while(rs.next()){
					count++;

					String ename = rs.getString(1);

					System.out.printf("%s\n", ename);
				}

				if(count == 0){
					System.out.println("部下はいません");
				}
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

	private void delete(int pempno) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle
			//Class.forName("org.gjt.mm.mysql.Driver"); // MySQL
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + _host + ":1521:" + _sid, _user, _pass); // Oracle
					//"jdbc:mysql://" + _host + "/" + _sid, _user, _pass); // MySQL
			
			String sql = "delete from employees ";
			sql += "where empno = ?";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, pempno);

			int num = ps.executeUpdate();
			//System.out.println(sql);			
			
			
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
		}
	}

}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Update1 {
	private String _user = "yamauchi";
	private String _pass = "password";
	private String _host = "172.16.40.4"; // Oracle
	//private String _host = "localhost"; // MySQL
	private String _sid = "db11";
	private static int count = 0; // 件数をカウント	
	
	public static void main(String[] args){
		Scanner stdIn = new Scanner(System.in);

		Update1 sample = new Update1();
		try {

			sample.select();
			System.out.println();

			System.out.print("社員番号：");
			int empno = stdIn.nextInt();
			System.out.println();

			sample.update(empno);

			/*
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
			*/

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

	private void update(int pempno) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle
			//Class.forName("org.gjt.mm.mysql.Driver"); // MySQL
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + _host + ":1521:" + _sid, _user, _pass); // Oracle
					//"jdbc:mysql://" + _host + "/" + _sid, _user, _pass); // MySQL
			
			String sql = "select ename, yomi, job, mgr,  ";
			sql += "hiredate, sal, comm, deptno ";
			sql += "from employees ";
			sql += "where empno = ?";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, pempno);

			rs = ps.executeQuery();
			//System.out.println(sql);			
			
			while(rs.next()){
				count++;

				String ename = rs.getString(1);
				String yomi = rs.getString(2);
				String job = rs.getString(3);
				String mgr = rs.getString(4);
				String hiredate = rs.getString(5);
				hiredate = hiredate.substring(0, 10);
				String sal = rs.getString(6);
				String comm = rs.getString(7);
				String deptno = rs.getString(8);

				System.out.printf("社員名(%s)：", ename);
				String tmp = reader.readLine();
				if(tmp.length()>0){
					ename = tmp;
				}
				System.out.printf("ローマ字(%s)：", yomi);
				tmp = reader.readLine();
				if(tmp.length()>0){
					yomi = tmp;
				}
				System.out.printf("職種(%s)：", job);
				tmp = reader.readLine();
				if(tmp.length()>0){
					job = tmp;
				}
				System.out.printf("上司の社員番号(%s)：", mgr);
				tmp = reader.readLine();
				if(tmp.length()>0){
					mgr = tmp;
				}
				System.out.printf("入社日(%s)：", hiredate);
				tmp = reader.readLine();
				if(tmp.length()>0){
					hiredate = tmp;
				}
				System.out.printf("給与(%s)：", sal);
				tmp = reader.readLine();
				if(tmp.length()>0){
					sal = tmp;
				}
				System.out.printf("歩合(%s)：", comm);
				tmp = reader.readLine();
				if(tmp.length()>0){
					comm = tmp;
				}
				System.out.printf("部門番号(%s)：", deptno);
				tmp = reader.readLine();
				if(tmp.length()>0){
					deptno = tmp;
				}

				sql = "update employees set ename = ?, yomi = ?, job = ?, ";
				sql += "mgr = ?, hiredate = ?, sal = ?, comm = ?, deptno = ? ";
				sql += "where empno = ?";

				ps = conn.prepareStatement(sql);
				ps.setString(1, ename);
				ps.setString(2, yomi);
				ps.setString(3, job);
				ps.setString(4, mgr);
			 	ps.setString(5, hiredate);
				ps.setString(6, sal);
				ps.setString(7, comm);
				ps.setString(8, deptno);
				ps.setInt(9, pempno);	

				int num = ps.executeUpdate();


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

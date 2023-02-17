import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JoinDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "jspid";
	String pw = "jsppw";
	Connection conn =null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public JoinDao() {
		try {
			Class.forName(driver);
			System.out.println("드라이버 로드 성공");
			conn =DriverManager.getConnection(url, id, pw);
			System.out.println("DB연결 성공");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("드라이버 로드 실패");
			System.out.println("DB연결 실패");
			e.printStackTrace();
		}
	}//JoinDao

	public Map<String, Integer> findId() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String sql = "select id, pw from customer";
		try {
			ps= conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				map.put(rs.getString("id"), rs.getInt("pw"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return map;
	}

	public int insertCustomer(JoinDto jd) {
		int cnt = -1;
		String sql = "insert into customer(id,pw,name,addr) values(?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, jd.getId());
			ps.setInt(2,jd.getPw());
			ps.setString(3, jd.getName());
			ps.setString(4, jd.getAddr());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public int deleteCustomer(JoinDto jd) {
		int cnt = -1;
		String sql = "delete from customer where id =?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, jd.getId());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	


}

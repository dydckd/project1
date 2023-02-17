import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BasketDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "jspid";
	String pw = "jsppw";
	Connection conn =null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public BasketDao() {
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
	}//BasketDao
	
	public ArrayList<BasketDto> getAllProducts(String id2) {
		ArrayList<BasketDto> lists = new ArrayList<BasketDto>();
		String sql = "select * from basket where id = ? order by no";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id2);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BasketDto bd = new BasketDto();
				bd.setNo(rs.getInt("no"));
				bd.setId(rs.getString("id"));
				bd.setCode(rs.getInt("code"));
				bd.setName(rs.getString("name"));
				bd.setPrice(rs.getInt("price"));
				bd.setStock(rs.getInt("stock"));
				
				lists.add(bd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}
	public ArrayList<BasketDto> getAllProducts() {
		ArrayList<BasketDto> lists = new ArrayList<BasketDto>();
		String sql = "select * from basket order by no";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BasketDto bd = new BasketDto();
				bd.setNo(rs.getInt("no"));
				bd.setId(rs.getString("id"));
				bd.setCode(rs.getInt("code"));
				bd.setName(rs.getString("name"));
				bd.setPrice(rs.getInt("price"));
				bd.setStock(rs.getInt("stock"));
				
				lists.add(bd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	public int insertProduct(BasketDto bd) {
		System.out.println(bd.getId()+"/"+bd.getStock());
		String sql = "insert into basket values(baskseq.nextval,?,?,?,?,1)";
		int cnt = -1;
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, bd.getId());
			ps.setInt(2, bd.getCode());
			ps.setString(3, bd.getName());
			ps.setInt(4, bd.getPrice());
			
			cnt= ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public int deleteProduct(BasketDto bd) {
		int cnt = -1;
		String sql ="delete basket where no=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, bd.getNo());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public int getTotalPrice(String id) {
		int price=0;
		String sql ="select sum(price) total from basket where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				price = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return price;
	}

	public ArrayList<String> getStockById(String id) {
		ArrayList<String> lists = new ArrayList<>();
		String sql = "select name,stock from basket where id= '"+id+"'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				lists.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	public int getAllCount(String id2) {
		int count = -1;
		String sql = "select count(*) num from basket where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id2);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	public int deleteById(String id2) {
		int cnt = -1;
		String sql = "delete from basket where id =?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id2);
			
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}

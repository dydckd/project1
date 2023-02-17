import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class ProductsDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "jspid";
	String pw = "jsppw";
	Connection conn =null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public ProductsDao() {
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
	}//ProductsDao

	public ArrayList<ProductsDto> getAllProducts() {
		ArrayList<ProductsDto> lists = new ArrayList<>();
		String sql = "select * from products order by code";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				ProductsDto pd = new ProductsDto();
				pd.setCode(rs.getInt("code"));
				pd.setName(rs.getString("name"));
				pd.setPrice(rs.getInt("price"));
				pd.setStock(rs.getInt("stock"));

				lists.add(pd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	public void updateStock(ArrayList<String> lists) {
		String sql = "update products set stock = ((select stock from products where name =?)-1) where name=?";
		try {
			for (String i : lists) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, i);
				ps.setString(2, i);

				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int insertProducts(ProductsDto pd) {
		int cnt = -1;
		String sql = "insert into products values(?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,pd.getCode());
			ps.setString(2, pd.getName());
			ps.setInt(3, pd.getPrice());
			ps.setInt(4, pd.getStock());

			cnt = ps.executeUpdate();
		}catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("code의 값은 중복될수 없습니다.");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public int updateProducts(ProductsDto pd) {
		System.out.println(pd.getCode()+"/"+ pd.getName()+"/"+pd.getPrice());
		int cnt = -1;
		String sql = "update products set name=?,price=?,stock=? where code =?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pd.getName());
			ps.setInt(2, pd.getPrice());
			ps.setInt(3, pd.getStock());
			ps.setInt(4, pd.getCode());

			cnt = ps.executeUpdate();
		}catch (SQLIntegrityConstraintViolationException e) {
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public int deleteProducts(ProductsDto pd) {
		int cnt = -1;
		String sql ="delete from products where code = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pd.getCode());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	
}

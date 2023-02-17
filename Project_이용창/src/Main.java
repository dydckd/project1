import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

import oracle.net.aso.p;

public class Main extends JFrame implements ActionListener{
	JTable ptable = null;
	JTable btable = null;
	JTable otable = null;
	JTable adtable = null;

	JScrollPane pscrollPane = null;
	JScrollPane bscrollPane = null;
	JScrollPane oscrollPane = null;
	JScrollPane adscrollPane = null;

	Container contentPane = null;
	JPanel login = null;
	JPanel join = null;
	JPanel customer = null;
	JPanel admin = null;
	//login
	JLabel loginidJLabel = null;
	JLabel loginpwJLable = null;
	JTextField loginidField = null;
	JTextField loginpwField = null;
	JButton loginButton = null;
	JButton joinButton = null;
	JButton deleteButton = null;
	//join
	JLabel joinIdJLabel = null;
	JLabel joinPwJLabel = null;
	JLabel joinNameJLabel = null;
	JLabel joinAddrJLabel = null;

	JTextField joinIdField = null;
	JTextField joinPwField = null;
	JTextField joinNameField = null;
	JTextField joinAddrField = null;

	JButton agreeButton = null;
	JButton cancelButton = null;
	//delete
	JPanel delete = null;
	JLabel deleteIdJLabel = null;
	JLabel deletePwJLabel = null;

	JTextField deleteIdField = null;
	JTextField deletePwField = null;

	JButton deleteCheckButton = null;
	JButton deleteCancelButton = null;

	//products
	String[] productColumName = {"code","제품명","가격","재고"};
	String[] basketColumName = {"no","id","code","제품명","가격","수량"};
	Object[][] productRowData;
	Object[][] basketRowData;
	ArrayList<ProductsDto> plists = null;
	ArrayList<BasketDto> blists = null;
	String id;
	JButton productsInsertbtn = null;
	JButton productsdeletebtn = null;
	JButton productsOrderbtn = null;
	JButton productslogoutbtn = null;

	//order
	JPanel order = null;
	JButton orderCheckbtn = null;
	JButton orderExitbtn = null;
	JLabel price = null;
	ArrayList<BasketDto> olists = null;

	//admin
	JButton adminInsertbtn = null;
	JButton adminUpdatebtn = null;
	JButton adminDeletebtn = null;
	JButton adminOrderbtn = null;
	JButton adminlogoutbtn = null;
	ArrayList<ProductsDto> adlists = null;
	JTextField adCodeField = null;
	JTextField adNameField = null;
	JTextField adPriceField = null;
	JTextField adStockField = null;

	//nowOrder
	JPanel noorder = null;
	JTable notable = null;
	JScrollPane noscrollPane = null;
	String[] nowOrderColumName = {"id","code","제품명","가격","재고"};
	Object[][] nowOrderRowData;
	ArrayList<BasketDto> nolists = null;

	JTextField orderid = null;
	JTextField ordercount = null;
	JTextField orderprice = null;

	JButton checkOrderbtn = null;
	JButton exitbtn = null;

	JoinDao jdao = new JoinDao();
	ProductsDao pdao = new ProductsDao();
	BasketDao bdao = new BasketDao();
	CardLayout c=null;
	JPanel cards = null;
	BasketDto bd = null;
	ProductsDto pd = null;

	public Main(String title) {
		super(title);
		contentPane = getContentPane();


		cards = new JPanel(new CardLayout());
		cards.setBounds(0,0,700,700);

		//login 패널
		login = new JPanel();
		login.setLayout(null);

		login.setBounds(0, 0, 600, 600);

		loginidJLabel =new JLabel("id");
		loginpwJLable =new JLabel("pw");

		loginidJLabel.setBounds(100, 200, 100, 20);
		loginpwJLable.setBounds(100, 240, 100, 20);

		loginidField = new JTextField();
		loginpwField = new JTextField();

		loginidField.setBounds(220, 200, 100, 20);
		loginpwField.setBounds(220, 240, 100, 20);


		loginButton =new JButton("로그인");
		joinButton =new JButton("회원 가입");
		deleteButton =new JButton("회원 탈퇴");

		loginButton.setBounds(100,300,100,50);
		joinButton.setBounds(250,300,100,50);
		deleteButton.setBounds(400,300,100,50);

		loginButton.addActionListener(this);	
		joinButton.addActionListener(this);
		deleteButton.addActionListener(this);


		login.add(loginidJLabel);
		login.add(loginpwJLable);
		login.add(loginidField);
		login.add(loginpwField);
		login.add(loginButton);
		login.add(joinButton);
		login.add(deleteButton);


		//회원가입 패널
		join = new JPanel();
		join.setLayout(null);

		join.setBounds(0, 0, 600, 600);

		joinIdJLabel =new JLabel("id");
		joinPwJLabel =new JLabel("pw");
		joinNameJLabel =new JLabel("이름");
		joinAddrJLabel =new JLabel("주소");

		joinIdJLabel.setBounds(100, 100, 100, 20);
		joinPwJLabel.setBounds(100, 150, 100, 20);
		joinNameJLabel.setBounds(100, 200, 100, 20);
		joinAddrJLabel.setBounds(100, 250, 100, 20);

		joinIdField = new JTextField();
		joinPwField = new JTextField();
		joinNameField = new JTextField();
		joinAddrField = new JTextField();

		joinIdField.setBounds(220, 100, 100, 20);
		joinPwField.setBounds(220, 150, 100, 20);
		joinNameField.setBounds(220, 200, 100, 20);
		joinAddrField.setBounds(220, 250, 100, 20);

		agreeButton = new JButton("가입하기");
		cancelButton = new JButton("취소");
		agreeButton.setBounds(150, 300, 100, 50);
		cancelButton.setBounds(300, 300, 100, 50);

		agreeButton.addActionListener(this);
		cancelButton.addActionListener(this);

		join.add(joinIdJLabel);
		join.add(joinPwJLabel);
		join.add(joinNameJLabel);
		join.add(joinAddrJLabel);
		join.add(joinIdField);
		join.add(joinPwField);
		join.add(joinNameField);
		join.add(joinAddrField);
		join.add(agreeButton);
		join.add(cancelButton);

		//탈퇴 패널
		delete = new JPanel();
		delete.setLayout(null);
		delete.setBounds(0, 0, 600, 600);

		deleteIdJLabel =new JLabel("id");
		deletePwJLabel =new JLabel("pw");

		deleteIdJLabel.setBounds(100, 100, 100, 20);
		deletePwJLabel.setBounds(100, 150, 100, 20);

		deleteIdField = new JTextField();
		deletePwField = new JTextField();

		deleteIdField.setBounds(220, 100, 100, 20);
		deletePwField.setBounds(220, 150, 100, 20);

		deleteCheckButton = new JButton("탈퇴");
		deleteCancelButton = new JButton("취소");

		deleteCheckButton.setBounds(150, 300, 100, 50);
		deleteCancelButton.setBounds(300, 300, 100, 50);

		deleteCheckButton.addActionListener(this);
		deleteCancelButton.addActionListener(this);

		delete.add(deleteIdJLabel);
		delete.add(deletePwJLabel);
		delete.add(deleteIdField);
		delete.add(deletePwField);
		delete.add(deleteCheckButton);
		delete.add(deleteCancelButton);

		//고객 패널
		customer = new JPanel();
		customer.setLayout(null);

		customer.setBackground(Color.cyan);
		plists = pdao.getAllProducts();
		blists = bdao.getAllProducts(id);
		productRowData = new Object [plists.size()][productColumName.length];
		basketRowData = new Object [blists.size()][basketColumName.length];
		fillProductsData();
		fillBasketData();
		ptable = new JTable(productRowData,productColumName);
		btable = new JTable(basketRowData,basketColumName);

		ptable.addMouseListener(new MouseHandler());
		btable.addMouseListener(new MouseHandler());

		pscrollPane = new JScrollPane(ptable);
		bscrollPane = new JScrollPane(btable);
		pscrollPane.setBounds(0, 0, 680, 250);
		bscrollPane.setBounds(0, 250, 680, 250);

		JPanel button = new JPanel();
		button.setLayout(new GridLayout(1,4));
		button.setBounds(0, 500, 680, 100);

		productsInsertbtn = new JButton("담기");
		productsdeletebtn = new JButton("삭제");
		productsOrderbtn = new JButton("주문하기");
		productslogoutbtn = new JButton("로그아웃");

		productsInsertbtn.addActionListener(this);
		productsdeletebtn.addActionListener(this);
		productsOrderbtn.addActionListener(this);
		productslogoutbtn.addActionListener(this);

		button.add(productsInsertbtn);
		button.add(productsdeletebtn);
		button.add(productsOrderbtn);
		button.add(productslogoutbtn);

		//		productsInsertbtn.setBounds(0, 400, 100, 50);
		//		productsdeletebtn.setBounds(150, 400, 100, 50);
		//		productslistsbtn.setBounds(300, 400, 100, 50);

		customer.add(pscrollPane);
		customer.add(bscrollPane);
		customer.add(button);

		//주문하기 패널
		order = new JPanel();
		order.setLayout(null);
		olists = bdao.getAllProducts(id);
		basketRowData = new Object [olists.size()][basketColumName.length];
		fillOrderData();
		otable = new JTable(basketRowData,basketColumName);
		oscrollPane = new JScrollPane(otable);
		oscrollPane.setBounds(0, 0, 680, 300);

		JLabel totalPrice = new JLabel("총 금액");
		price = new JLabel();

		price.setBackground(Color.white);
		totalPrice.setBounds(150, 320, 100, 50);
		price.setBounds(270, 320, 100, 50);

		orderCheckbtn = new JButton("확인");
		orderExitbtn = new JButton("종료");

		orderCheckbtn.setBounds(100, 390, 100, 50);
		orderExitbtn.setBounds(250, 390, 100, 50);

		orderCheckbtn.addActionListener(this);
		orderExitbtn.addActionListener(this);

		order.add(totalPrice);
		order.add(price);
		order.add(oscrollPane);
		order.add(orderCheckbtn);
		order.add(orderExitbtn);

		//관리자 패널
		admin = new JPanel();
		admin.setLayout(null);
		admin.setBackground(Color.orange);

		adlists = pdao.getAllProducts();
		productRowData = new Object [adlists.size()][productColumName.length];
		fillAdproductsData();
		adtable = new JTable(productRowData,productColumName);
		adtable.addMouseListener(new MouseHandler());
		adscrollPane = new JScrollPane(adtable);
		adscrollPane.setBounds(0, 0, 680, 250);

		admin.add(adscrollPane);



		JLabel code = new JLabel("code");
		JLabel name = new JLabel("제품명");
		JLabel price = new JLabel("가격");
		JLabel stock = new JLabel("제고");

		code.setBounds(0, 270, 40, 20);
		name.setBounds(150, 270, 40, 20);
		price.setBounds(300, 270, 40, 20);
		stock.setBounds(455, 270, 40, 20);

		admin.add(code);
		admin.add(name);
		admin.add(price);
		admin.add(stock);

		adCodeField = new JTextField();
		adNameField = new JTextField();
		adPriceField = new JTextField();
		adStockField = new JTextField();

		adCodeField.setBounds(45, 270, 100, 20);
		adNameField.setBounds(195, 270, 100, 20);
		adPriceField.setBounds(350, 270, 100, 20);
		adStockField.setBounds(500, 270, 100, 20);

		admin.add(adCodeField);
		admin.add(adNameField);
		admin.add(adPriceField);
		admin.add(adStockField);



		JPanel adbutton = new JPanel();
		adbutton.setLayout(new GridLayout(1,5));
		adbutton.setBounds(0, 450, 680, 200);

		adminInsertbtn = new JButton("메뉴추가");
		adminUpdatebtn = new JButton("메뉴변경");
		adminDeletebtn = new JButton("메뉴삭제");
		adminOrderbtn = new JButton("주문현황");
		adminlogoutbtn = new JButton("로그아웃");

		adminInsertbtn.addActionListener(this);
		adminUpdatebtn.addActionListener(this);
		adminDeletebtn.addActionListener(this);
		adminOrderbtn.addActionListener(this);
		adminlogoutbtn.addActionListener(this);

		adbutton.add(adminInsertbtn);
		adbutton.add(adminUpdatebtn);
		adbutton.add(adminDeletebtn);
		adbutton.add(adminOrderbtn);
		adbutton.add(adminlogoutbtn);

		admin.add(adbutton);

		//주문현황 패널
		noorder = new JPanel();
		noorder.setLayout(null);
		nolists = bdao.getAllProducts();
		nowOrderRowData = new Object [nolists.size()][nowOrderColumName.length];
		fillNowOrderData();
		notable = new JTable(nowOrderRowData,nowOrderColumName);
		notable.addMouseListener(new MouseHandler());
		noscrollPane = new JScrollPane(notable);
		noscrollPane.setBounds(0, 0, 680, 300);

		noorder.add(noscrollPane);

		JLabel id = new JLabel("id");
		JLabel count = new JLabel("총 갯수");
		JLabel totalprice = new JLabel("총 합계");

		orderid = new JTextField();
		ordercount = new JTextField();
		orderprice = new JTextField();

		id.setBounds(0, 320, 50, 50);
		count.setBounds(190, 320, 50, 50);
		totalPrice.setBounds(380, 320, 50, 50);

		orderid.setBounds(70, 320, 100, 50);
		ordercount.setBounds(260, 320, 100, 50);
		orderprice.setBounds(450, 320, 100, 50);

		noorder.add(id);
		noorder.add(count);
		noorder.add(totalPrice);
		noorder.add(orderid);
		noorder.add(ordercount);
		noorder.add(orderprice);

		checkOrderbtn = new JButton("확인");
		exitbtn = new JButton("종료");

		checkOrderbtn.setBounds(50,400,100,20);
		exitbtn.setBounds(170,400,100,20);

		checkOrderbtn.addActionListener(this);
		exitbtn.addActionListener(this);

		noorder.add(checkOrderbtn);
		noorder.add(exitbtn);


		cards.add(login,"login");
		cards.add(join,"join");
		cards.add(delete,"delete");
		cards.add(customer,"customer");
		cards.add(admin,"admin");
		cards.add(order,"order");
		cards.add(noorder,"noorder");

		contentPane.add(cards);
		c = (CardLayout) cards.getLayout();

		setSize(620,620);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void fillNowOrderData() {
		Object[] arr = nolists.toArray();		
		int j = 0; 
		for(int i=0; i<arr.length;i++) {
			BasketDto bd = (BasketDto)arr[i];
			nowOrderRowData[i][j++] = bd.getId();
			nowOrderRowData[i][j++] = bd.getCode();
			nowOrderRowData[i][j++] = bd.getName();
			nowOrderRowData[i][j++] = bd.getPrice();
			nowOrderRowData[i][j++] = bd.getStock();
			j = 0;
		}

	}

	private void fillAdproductsData() {
		Object[] arr = adlists.toArray();		
		int j = 0; 
		for(int i=0; i<arr.length;i++) {
			ProductsDto pd = (ProductsDto)arr[i];
			productRowData[i][j++] = pd.getCode();
			productRowData[i][j++] = pd.getName();
			productRowData[i][j++] = pd.getPrice();
			productRowData[i][j++] = pd.getStock();
			j = 0;
		}

	}

	private void fillOrderData() {
		Object[] arr = olists.toArray();		
		int j = 0; 
		for(int i=0; i<arr.length;i++) {
			BasketDto bd = (BasketDto)arr[i];
			basketRowData[i][j++] = bd.getNo();
			basketRowData[i][j++] = bd.getId();
			basketRowData[i][j++] = bd.getCode();
			basketRowData[i][j++] = bd.getName();
			basketRowData[i][j++] = bd.getPrice();
			basketRowData[i][j++] = bd.getStock();
			j = 0;
		}

	}

	private void fillBasketData() {
		Object[] arr = blists.toArray();		
		int j = 0;
		for(int i=0; i<arr.length;i++) {
			BasketDto bd = (BasketDto)arr[i];
			basketRowData[i][j++] = bd.getNo();
			basketRowData[i][j++] = bd.getId();
			basketRowData[i][j++] = bd.getCode();
			basketRowData[i][j++] = bd.getName();
			basketRowData[i][j++] = bd.getPrice();
			basketRowData[i][j++] = bd.getStock();
			j = 0;
		}
	}

	private void fillProductsData() {
		Object[] arr = plists.toArray();
		int j = 0; 
		for(int i=0; i<arr.length;i++) {
			ProductsDto pd = (ProductsDto)arr[i];
			productRowData[i][j++] = pd.getCode();
			productRowData[i][j++] = pd.getName();
			productRowData[i][j++] = pd.getPrice();
			productRowData[i][j++] = pd.getStock();
			j = 0;
		}
	}

	public static void main(String[] args) {
		new Main("주문 관리 시스템");
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == loginButton) {
			System.out.println("로그인 버튼");
			boolean result = checklogData();
			if(result) {
				Map<String, Integer> map = jdao.findId();
				login(map);
			}
		}
		if(obj == joinButton) {
			System.out.println("가입 버튼");
			c.show(cards,"join");
		}
		if(obj == deleteButton) {
			System.out.println("탈퇴 버튼");
			c.show(cards, "delete");
		}
		if(obj == agreeButton) {
			System.out.println("가입하기 버튼");
			boolean result = checkJoinData();
			if(result) {
				JoinDto jd = new JoinDto();
				jd.setId(joinIdField.getText());
				jd.setPw(Integer.parseInt(joinPwField.getText()));
				jd.setName(joinNameField.getText());
				jd.setAddr(joinAddrField.getText());
				int cnt = jdao.insertCustomer(jd);
				if(cnt>0) {
					System.out.println("가입성공");
					JOptionPane.showMessageDialog(this,"회원가입을 성공했습니다.","가입성공",JOptionPane.PLAIN_MESSAGE);
					clearTextField();
					c.show(cards, "login");
				}
				else {
					System.out.println("가입실패");
				}
			}
		}

		if(obj == cancelButton) {
			System.out.println("취소버튼");
			c.show(cards,"login");
		}
		if(obj == deleteCheckButton) {
			System.out.println("탈퇴 확인 버튼");
			boolean result = checkdelData();
			if(result) {
				JoinDto jd = new JoinDto();
				jd.setId(deleteIdField.getText());
				jd.setPw(Integer.parseInt(deletePwField.getText()));
				Map<String, Integer> map = jdao.findId();
				if(map == null ) {
					JOptionPane.showMessageDialog(this,"id가 존재하지 않습니다","에러발생",JOptionPane.ERROR_MESSAGE);
					clearTextField();
				}
				else {
					delete(map,jd);
				}

			}
		}

		if(obj == productsInsertbtn) {
			System.out.println("담기버튼");
			int cnt = bdao.insertProduct(bd);
			if(cnt>0) {
				System.out.println("담기 성공");
				getAllProduct();
			}
			else
				System.out.println("실패");
		}
		if(obj == productsdeletebtn) {
			System.out.println("삭제버튼");
			int cnt = bdao.deleteProduct(bd);
			if(cnt>0) {
				System.out.println("삭제 성공");
				getAllProduct();
			}
		}
		if(obj == productsOrderbtn) {
			System.out.println("주문하기");
			int total = bdao.getTotalPrice(id);
			price.setText(String.valueOf(total));
			getAllOrder();
			c.show(cards, "order");


		}
		if(obj == productslogoutbtn) {
			System.out.println("로그아웃 버튼");
			c.show(cards, "login");

		}
		if(obj == deleteCancelButton) {
			System.out.println("취소버튼");
			c.show(cards,"login");
		}
		if(obj == orderCheckbtn) {
			System.out.println("주문확인 버튼");
			JOptionPane.showMessageDialog(this,"주문완료 되었습니다 종료합니다.","주문성공",JOptionPane.PLAIN_MESSAGE);
			ArrayList<String> lists= bdao.getStockById(id);
			pdao.updateStock(lists);
			System.exit(0);

		}
		if(obj == orderExitbtn) {
			System.out.println("종료");
			System.exit(0);
		}

		//admin
		if(obj == adminInsertbtn) {
			ProductsDto pd = null;
			System.out.println("메뉴추가");
			boolean result = checkAdData();

			if(result) {
				pd = new ProductsDto();
				pd.setCode(Integer.parseInt(adCodeField.getText()));
				pd.setName(adNameField.getText());
				pd.setPrice(Integer.parseInt(adPriceField.getText()));
				pd.setStock(Integer.parseInt(adStockField.getText()));
			}

			boolean result2 = validate(pd);
			if(result2) {
				int cnt = pdao.insertProducts(pd);
				if(cnt>0) {
					System.out.println("추가 성공");
					getAllProductadtable();
					clearTextField();
				}
				else {
					System.out.println("실패");
				}
			}
		}
		if(obj == adminUpdatebtn) {
			System.out.println("메뉴변경");
			boolean result = checkAdData();
			if(result) {
				ProductsDto pd = new ProductsDto();
				pd.setCode(Integer.parseInt(adCodeField.getText()));
				pd.setName(adNameField.getText());
				pd.setPrice(Integer.parseInt(adPriceField.getText()));
				pd.setStock(Integer.parseInt(adStockField.getText()));
				int cnt = pdao.updateProducts(pd);
				if(cnt>0) {
					System.out.println("변경 성공");
					getAllProductadtable();
					clearTextField();
				}
				else {
					System.out.println("실패");
				}
			}
		}
		if(obj == adminDeletebtn) {
			System.out.println("메뉴삭제");
			boolean result = checkAdData();
			if(result) {
				ProductsDto pd = new ProductsDto();
				pd.setCode(Integer.parseInt(adCodeField.getText()));
				pd.setName(adNameField.getText());
				pd.setPrice(Integer.parseInt(adPriceField.getText()));
				pd.setStock(Integer.parseInt(adStockField.getText()));
				int cnt = pdao.deleteProducts(pd);
				if(cnt>0) {
					System.out.println("삭제 성공");
					getAllProductadtable();
					clearTextField();
				}
				else {
					System.out.println("실패");
				}
			}
		}
		if(obj == adminOrderbtn) {
			System.out.println("주문현황");
			c.show(cards,"noorder");
		}

		if(obj == adminlogoutbtn) {
			System.out.println("로그아웃");
			c.show(cards, "login");
		}
		if(obj == checkOrderbtn) {
			System.out.println("처리 완료");
			String id = orderid.getText();
			int cnt = bdao.deleteById(id);
			if(cnt>0) {
				getAllProductnotable();
				clearTextField();
			}
		}
		if(obj == exitbtn) {
			System.out.println("종료");
			System.exit(0);
		}


	}
	private boolean validate(ProductsDto pd) {
		ArrayList<ProductsDto> list = pdao.getAllProducts();
		for(int i =0;i<list.size();i++) {
			ProductsDto pd2 = list.get(i);
			if(pd.getCode()==pd2.getCode()) {
				System.out.println("중복된 코드는 사용할수 없습니다.");
				JOptionPane.showMessageDialog(this,"중복된 코드는 사용할수 없습니다.","에러발생",JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if(pd.getName().equals(pd2.getName())) {
				System.out.println("중복된 품명은 사용할수 없습니다.");
				JOptionPane.showMessageDialog(this,"중복된 품명은 사용할수 없습니다.","에러발생",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		return true;
	}
	private void getAllProductnotable() {
		nolists = bdao.getAllProducts();
		nowOrderRowData = new Object [nolists.size()][nowOrderColumName.length];
		fillNowOrderData();
		notable = new JTable(nowOrderRowData,nowOrderColumName);
		noscrollPane.setViewportView(notable);
		notable.addMouseListener(new MouseHandler());
	}

	private void getAllProductadtable() {
		adlists = pdao.getAllProducts();
		productRowData = new Object [adlists.size()][productColumName.length];
		fillAdproductsData();
		adtable = new JTable(productRowData,productColumName);
		adscrollPane.setViewportView(adtable);
		adtable.addMouseListener(new MouseHandler());
	}

	private void getAllProduct() {
		blists = bdao.getAllProducts(id);
		basketRowData = new Object [blists.size()][basketColumName.length];
		fillBasketData();
		btable = new JTable(basketRowData,basketColumName);
		bscrollPane.setViewportView(btable);
		btable.addMouseListener(new MouseHandler());
	}

	private void getAllOrder() {
		olists = bdao.getAllProducts(id);
		basketRowData = new Object [olists.size()][basketColumName.length];
		fillOrderData();
		otable = new JTable(basketRowData,basketColumName);
		oscrollPane.setViewportView(otable);
	}

	private void delete(Map<String, Integer> map,JoinDto jd) {
		id= deleteIdField.getText();
		if(map.get(id)!=null) {
			if(map.get(id)==Integer.parseInt(deletePwField.getText())) {
				System.out.println("아이디 비번 확인 성공");
				int cnt = jdao.deleteCustomer(jd);
				clearTextField();
				if(cnt>0) 
					System.out.println("탈퇴 성공");
				c.show(cards, "login");
			}
			else {
				System.out.println("비밀번호가 틀렸습니다");
				JOptionPane.showMessageDialog(this,"비밀번호가 틀렸습니다","에러발생",JOptionPane.ERROR_MESSAGE);
				clearTextField();
			}
		}
	}

	private boolean checkAdData() {
		if(adCodeField.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"코드를 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE);
			adCodeField.requestFocus();
			return false;
		}
		if(adNameField.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"제품명을 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE);
			adNameField.requestFocus();
			return false;
		}
		if(adPriceField.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"가격을 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE);
			adPriceField.requestFocus();
			return false;
		}
		if(adStockField.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"수량을 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE);
			adStockField.requestFocus();
			return false;
		}
		return true;
	}
	private boolean checklogData() {
		if(loginidField.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"아이디를 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE);
			loginidField.requestFocus();
			return false;
		}
		if(loginpwField.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"비밀번호를 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE);
			loginpwField.requestFocus();
			return false;
		}
		return true;
	}

	private boolean checkdelData() {
		if(deleteIdField.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"아이디를 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE);
			deleteIdField.requestFocus();
			return false;
		}
		if(deletePwField.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"비밀번호를 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE);
			deletePwField.requestFocus();
			return false;
		}
		return true;
	}

	private boolean checkJoinData() {

		if(joinIdField.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"아이디를 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE);
			joinIdField.requestFocus();
			return false;
		}
		if(joinPwField.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"비밀번호를 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE);
			joinPwField.requestFocus();
			return false;
		}
		if(joinNameField.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"이름를 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE);
			joinNameField.requestFocus();
			return false;
		}
		if(joinAddrField.getText().length()==0) {
			JOptionPane.showMessageDialog(this,"주소를 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE);
			joinAddrField.requestFocus();
			return false;
		}
		return true;
	}//checkData

	public void clearTextField() {
		loginidField.setText("");
		loginpwField.setText("");
		joinIdField.setText("");
		joinPwField.setText("");
		joinNameField.setText("");
		joinAddrField.setText("");
		deleteIdField.setText("");
		deletePwField.setText("");
		adCodeField.setText("");
		adNameField.setText("");
		adPriceField.setText("");
		adStockField.setText("");
		orderid.setText("");
		ordercount.setText("");
		orderprice.setText("");
	}

	private void login(Map<String, Integer> map) {
		id= loginidField.getText();
		if(map.get(id)!=null) {
			if(map.get(id)==Integer.parseInt(loginpwField.getText())) {
				System.out.println("로그인 성공");
				if(id.equals("admin")) {
					c.show(cards, "admin");
				}
				else {
					getAllProduct();
					c.show(cards, "customer");
				}
			}
			else {
				System.out.println("비밀번호가 틀렸습니다");
				JOptionPane.showMessageDialog(this,"비밀번호가 틀렸습니다","에러발생",JOptionPane.ERROR_MESSAGE);
				clearTextField();
			}
		}
		else {
			System.out.println("등록된 아이디가 없습니다.");
			JOptionPane.showMessageDialog(this,"등록된아이디가 없습니다","에러발생",JOptionPane.ERROR_MESSAGE);
			clearTextField();
		}
	}//login

	class MouseHandler extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			System.out.println("마우스 이벤트");
			Object obj = e.getSource();
			if(obj==ptable) {
				int row = ptable.getSelectedRow();
				System.out.println("row:" + row);
				bd = new BasketDto();
				bd.setId(id);
				bd.setCode((int)(ptable.getValueAt(row, 0)));
				bd.setName((String)(ptable.getValueAt(row, 1)));
				bd.setPrice((int)(ptable.getValueAt(row, 2)));
				bd.setStock((int)(ptable.getValueAt(row, 3)));
			}
			if(obj == btable) {
				int row = btable.getSelectedRow();
				System.out.println("row:" + row);
				bd = new BasketDto();
				bd.setNo((int)(btable.getValueAt(row, 0)));
				bd.setId((String)(btable.getValueAt(row, 1)));
				bd.setCode((int)(btable.getValueAt(row, 2)));
				bd.setName((String)(btable.getValueAt(row, 3)));
				bd.setPrice((int)(btable.getValueAt(row, 4)));
				bd.setStock((int)(btable.getValueAt(row, 5)));
			}
			if(obj==adtable) {
				int row = adtable.getSelectedRow();
				System.out.println("row:" + row);
				pd = new ProductsDto();
				pd.setCode((int)(adtable.getValueAt(row, 0)));
				pd.setName((String)(adtable.getValueAt(row, 1)));
				pd.setPrice((int)(adtable.getValueAt(row, 2)));
				pd.setStock((int)(adtable.getValueAt(row, 3)));

				adCodeField.setText(adtable.getValueAt(row, 0).toString());
				adNameField.setText(adtable.getValueAt(row, 1).toString());
				adPriceField.setText(adtable.getValueAt(row, 2).toString());
				adStockField.setText(adtable.getValueAt(row, 3).toString());
			}
			if(obj== notable) {
				int row = notable.getSelectedRow();
				System.out.println("row:" + row);
				bd = new BasketDto();
				bd.setId((notable.getValueAt(row, 0).toString()));
				bd.setCode(Integer.parseInt(notable.getValueAt(row, 1).toString()));
				bd.setName((notable.getValueAt(row, 2).toString()));
				bd.setPrice(Integer.parseInt(notable.getValueAt(row, 3).toString()));
				bd.setStock(Integer.parseInt(notable.getValueAt(row, 4).toString()));

				orderid.setText(notable.getValueAt(row, 0).toString());
				ordercount.setText( String.valueOf(bdao.getAllCount(orderid.getText())));
				orderprice.setText(String.valueOf(bdao.getTotalPrice(orderid.getText())));
			}
		}
	}


}

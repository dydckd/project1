import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener{
	JTable ptable = null;
	JTable btable = null;
	JTable otable = null;	
	JTable adtable = null;
	Font labelfont = null;

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
	JLabel totalP = null;
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
		cards.setBounds(0,0,700,550);
		labelfont = new Font("맑은 고딕",Font.BOLD,20);
		Font buttonfont = new Font("맑은 고딕",Font.BOLD,14);
		//login 패널

		ImageIcon icon = new ImageIcon("C:\\Users\\lyc\\java\\Project_이용창\\src\\로그인 이미지.jpg");
		login = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		login.setLayout(null);
		login.setBounds(0, 0, 600, 550);
		
		JLabel loginlabel = new JLabel("쌍용 분식");
		loginlabel.setBounds(230, 100, 500, 50);
		loginlabel.setFont(new Font("맑은 고딕",Font.BOLD,50));
		loginidJLabel =new JLabel("id");
		loginpwJLable =new JLabel("pw");
		loginidJLabel.setFont(labelfont);
		loginpwJLable.setFont(labelfont);
		loginidJLabel.setBounds(200, 200, 100, 20);
		loginpwJLable.setBounds(200, 240, 100, 20);

		loginidField = new JTextField();
		loginpwField = new JTextField();

		loginidField.setBounds(300, 200, 100, 20);
		loginpwField.setBounds(300, 240, 100, 20);


		loginButton =new JButton("로그인");
		joinButton =new JButton("회원 가입");
		deleteButton =new JButton("회원 탈퇴");

		loginButton.setFont(buttonfont);
		joinButton.setFont(buttonfont);
		deleteButton.setFont(buttonfont);


		loginButton.setBounds(420,210,100,50);
		joinButton.setBounds(200,300,100,50);
		deleteButton.setBounds(350,300,100,50);

		loginButton.addActionListener(this);	
		joinButton.addActionListener(this);
		deleteButton.addActionListener(this);

		login.add(loginlabel);
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

		join.setBounds(0, 0, 600, 550);

		JLabel joinlabel = new JLabel("회원가입");
		joinIdJLabel =new JLabel("id");
		joinPwJLabel =new JLabel("pw");
		joinNameJLabel =new JLabel("이름");
		joinAddrJLabel =new JLabel("주소");

		joinlabel.setBounds(250, 20, 200, 50);
		joinIdJLabel.setBounds(220, 100, 100, 20);
		joinPwJLabel.setBounds(220, 170, 100, 20);
		joinNameJLabel.setBounds(220, 240, 100, 20);
		joinAddrJLabel.setBounds(220, 310, 100, 20);

		joinlabel.setFont(new Font("맑은 고딕",Font.BOLD,30));
		joinIdJLabel.setFont(labelfont);
		joinPwJLabel.setFont(labelfont);
		joinNameJLabel.setFont(labelfont);
		joinAddrJLabel.setFont(labelfont);

		joinIdField = new JTextField();
		joinPwField = new JTextField();
		joinNameField = new JTextField();
		joinAddrField = new JTextField();

		joinIdField.setBounds(200, 130, 200, 20);
		joinPwField.setBounds(200, 200, 200, 20);
		joinNameField.setBounds(200, 270, 200, 20);
		joinAddrField.setBounds(200, 340, 200, 20);

		agreeButton = new JButton("가입하기");
		cancelButton = new JButton("취소");
		agreeButton.setBounds(180, 400, 100, 50);
		cancelButton.setBounds(330, 400, 100, 50);

		agreeButton.setFont(buttonfont);
		agreeButton.setBackground(Color.black);
		agreeButton.setForeground(Color.white);
		cancelButton.setFont(buttonfont);
		cancelButton.setBackground(Color.black);
		cancelButton.setForeground(Color.white);

		agreeButton.addActionListener(this);
		cancelButton.addActionListener(this);

		join.add(joinlabel);
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
		delete.setBounds(0, 0, 600, 550);

		JLabel deleteJLabel = new JLabel("회원탈퇴");
		deleteIdJLabel =new JLabel("id");
		deletePwJLabel =new JLabel("pw");

		deleteJLabel.setBounds(250, 20, 200, 50);
		deleteIdJLabel.setBounds(220, 140, 100, 20);
		deletePwJLabel.setBounds(220, 210, 100, 20);

		deleteJLabel.setFont(new Font("맑은 고딕",Font.BOLD,30));
		deleteIdJLabel.setFont(labelfont);
		deletePwJLabel.setFont(labelfont);

		deleteIdField = new JTextField();
		deletePwField = new JTextField();

		deleteIdField.setBounds(200, 170, 200, 20);
		deletePwField.setBounds(200, 240, 200, 20);

		deleteCheckButton = new JButton("탈퇴");
		deleteCancelButton = new JButton("취소");

		deleteCheckButton.setBounds(180, 350, 100, 50);
		deleteCancelButton.setBounds(330, 350, 100, 50);

		deleteCheckButton.setFont(buttonfont);
		deleteCheckButton.setBackground(Color.black);
		deleteCheckButton.setForeground(Color.white);
		deleteCancelButton.setFont(buttonfont);
		deleteCancelButton.setBackground(Color.black);
		deleteCancelButton.setForeground(Color.white);

		deleteCheckButton.addActionListener(this);
		deleteCancelButton.addActionListener(this);

		delete.add(deleteJLabel);
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

		btable.setFont(labelfont);
		btable.setRowHeight(40);
		btable.setBackground(Color.white);

		ptable.setFont(labelfont);
		ptable.setRowHeight(40);

		ptable.addMouseListener(new MouseHandler());
		btable.addMouseListener(new MouseHandler());

		pscrollPane = new JScrollPane(ptable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		bscrollPane = new JScrollPane(btable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pscrollPane.setBounds(0, 0, 690, 235);
		bscrollPane.setBounds(0, 235, 690, 235);
		bscrollPane.setViewportView(btable);

		JPanel button = new JPanel();
		button.setLayout(new GridLayout(1,4));
		button.setBounds(0, 470, 690, 50);

		productsInsertbtn = new JButton("담기");
		productsdeletebtn = new JButton("삭제");
		productsOrderbtn = new JButton("주문하기");
		productslogoutbtn = new JButton("로그아웃");

		productsInsertbtn.addActionListener(this);
		productsdeletebtn.addActionListener(this);
		productsOrderbtn.addActionListener(this);
		productslogoutbtn.addActionListener(this);

		productsInsertbtn.setFont(buttonfont);
		productsdeletebtn.setFont(buttonfont);
		productsOrderbtn.setFont(buttonfont);
		productslogoutbtn.setFont(buttonfont);

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
		order.setBackground(Color.orange);
		olists = bdao.getAllProducts(id);
		basketRowData = new Object [olists.size()][basketColumName.length];
		fillOrderData();
		otable = new JTable(basketRowData,basketColumName);
		otable.setFont(labelfont);
		otable.setRowHeight(40);
		oscrollPane = new JScrollPane(otable);
		oscrollPane.setBounds(0, 0, 690, 300);



		totalP = new JLabel("총금액 :");
		price = new JLabel();


		totalP.setBounds(220, 320, 100, 50);
		price.setBounds(350, 320, 100, 50);
		totalP.setFont(labelfont);
		price.setFont(labelfont);

		orderCheckbtn = new JButton("확인");
		orderExitbtn = new JButton("종료");

		orderCheckbtn.setBounds(150, 390, 100, 50);
		orderExitbtn.setBounds(350, 390, 100, 50);
		
		orderCheckbtn.setFont(buttonfont);
		orderExitbtn.setFont(buttonfont);
		
		orderCheckbtn.addActionListener(this);
		orderExitbtn.addActionListener(this);



		order.add(totalP);
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

		adtable.setFont(labelfont);
		adtable.setRowHeight(40);

		adscrollPane = new JScrollPane(adtable);
		adscrollPane.setBounds(0, 0, 690, 350);

		admin.add(adscrollPane);



		JLabel code = new JLabel("code");
		JLabel name = new JLabel("제품명");
		JLabel price = new JLabel("가격");
		JLabel stock = new JLabel("제고");

		code.setBounds(0, 370, 50, 30);
		name.setBounds(170, 370, 60, 30);
		price.setBounds(350, 370, 50, 30);
		stock.setBounds(520, 370, 50, 30);

		code.setFont(labelfont);
		name.setFont(labelfont);
		price.setFont(labelfont);
		stock.setFont(labelfont);

		admin.add(code);
		admin.add(name);
		admin.add(price);
		admin.add(stock);

		adCodeField = new JTextField();
		adNameField = new JTextField();
		adPriceField = new JTextField();
		adStockField = new JTextField();

		adCodeField.setBounds(60, 370, 100, 20);
		adNameField.setBounds(240, 370, 100, 20);
		adPriceField.setBounds(410, 370, 100, 20);
		adStockField.setBounds(590, 370, 100, 20);

		admin.add(adCodeField);
		admin.add(adNameField);
		admin.add(adPriceField);
		admin.add(adStockField);



		JPanel adbutton = new JPanel();
		adbutton.setLayout(new GridLayout(1,5));
		adbutton.setBounds(0, 470, 690, 50);

		adminInsertbtn = new JButton("메뉴추가");
		adminUpdatebtn = new JButton("메뉴변경");
		adminDeletebtn = new JButton("메뉴삭제");
		adminOrderbtn = new JButton("주문현황");
		adminlogoutbtn = new JButton("로그아웃");

		adminInsertbtn.setFont(buttonfont);
		adminUpdatebtn.setFont(buttonfont);
		adminDeletebtn.setFont(buttonfont);
		adminOrderbtn.setFont(buttonfont);
		adminlogoutbtn.setFont(buttonfont);

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
		noorder.setBackground(Color.orange);
		nolists = bdao.getAllProducts();
		nowOrderRowData = new Object [nolists.size()][nowOrderColumName.length];
		fillNowOrderData();
		notable = new JTable(nowOrderRowData,nowOrderColumName);
		notable.addMouseListener(new MouseHandler());
		
		notable.setFont(labelfont);
		notable.setRowHeight(40);
		noscrollPane = new JScrollPane(notable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		noscrollPane.setBounds(0, 0, 690, 300);
		

		noorder.add(noscrollPane);

		JLabel idlabel = new JLabel("id");
		JLabel countlabel = new JLabel("총 갯수");
		JLabel totalpricelabel = new JLabel("총 합계");
		
		idlabel.setFont(labelfont);
		countlabel.setFont(labelfont);
		totalpricelabel.setFont(labelfont);

		orderid = new JTextField();
		ordercount = new JTextField();
		orderprice = new JTextField();

		idlabel.setBounds(50, 350, 70, 30);
		countlabel.setBounds(240, 350, 70, 30);
		totalpricelabel.setBounds(430, 350, 70, 30);

		orderid.setBounds(120, 360, 100, 20);
		ordercount.setBounds(310, 360, 100, 20);
		orderprice.setBounds(500, 360, 100, 20);

		noorder.add(idlabel);
		noorder.add(countlabel);
		noorder.add(totalpricelabel);
		noorder.add(orderid);
		noorder.add(ordercount);
		noorder.add(orderprice);

		checkOrderbtn = new JButton("확인");
		exitbtn = new JButton("종료");

		checkOrderbtn.setBounds(170,420,100,50);
		exitbtn.setBounds(430,420,100,50);
		
		checkOrderbtn.setFont(buttonfont);
		exitbtn.setFont(buttonfont);

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

		setSize(700,550);
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
				Map map = jdao.findId();
				if(map.get(jd.getId())!=null){
					JOptionPane.showMessageDialog(this,"이미 존재하는 아이디 입니다.","에러발생",JOptionPane.ERROR_MESSAGE);
					clearTextField();
					return;
				}
				int cnt = jdao.insertCustomer(jd);
				if(cnt>0) {
					System.out.println("가입성공");
					JOptionPane.showMessageDialog(this,"회원가입을 성공했습니다.","가입성공",JOptionPane.PLAIN_MESSAGE);
					clearTextField();
					c.show(cards, "login");
				}
				else {
					System.out.println("가입실패");
					JOptionPane.showMessageDialog(this,"회원가입을 실패했습니다.","가입실패",JOptionPane.ERROR_MESSAGE);
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
				if(map.get(jd.getId()) == null ) {
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
			boolean result = checkstock(pd);
			if(result) {
				int cnt = bdao.insertProduct(bd);
				if(cnt>0) {
					System.out.println("담기 성공");
					getAllProduct();
				}
				else
					System.out.println("실패");
			}
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
			clearTextField();
			JOptionPane.showMessageDialog(this,"로그아웃 되었습니다.","로그아웃",JOptionPane.PLAIN_MESSAGE);
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
			clearTextField();
			JOptionPane.showMessageDialog(this,"로그아웃 되었습니다.","로그아웃",JOptionPane.PLAIN_MESSAGE);
			c.show(cards, "login");
		}
		if(obj == checkOrderbtn) {
			System.out.println("처리 완료");
			String id = orderid.getText();
			int cnt = bdao.deleteById(id);
			if(cnt>0) {
				JOptionPane.showMessageDialog(this,"주문처리 완료.","완료",JOptionPane.PLAIN_MESSAGE);
				getAllProductnotable();
				clearTextField();
				
			}
		}
		if(obj == exitbtn) {
			System.out.println("종료");
			System.exit(0);
		}


	}
	private boolean checkstock(ProductsDto pd) {
		if(pd.getStock()==0) {
			JOptionPane.showMessageDialog(this,"제고가 없습니다.","에러발생",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
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
		notable.setFont(labelfont);
		notable.setRowHeight(40);
		noscrollPane.setViewportView(notable);
		notable.addMouseListener(new MouseHandler());
	}

	private void getAllProductadtable() {
		adlists = pdao.getAllProducts();
		productRowData = new Object [adlists.size()][productColumName.length];
		fillAdproductsData();
		adtable = new JTable(productRowData,productColumName);
		adtable.setFont(labelfont);
		adtable.setRowHeight(40);
		adscrollPane.setViewportView(adtable);
		adtable.addMouseListener(new MouseHandler());
	}

	private void getAllProduct() {
		blists = bdao.getAllProducts(id);
		basketRowData = new Object [blists.size()][basketColumName.length];
		fillBasketData();
		btable = new JTable(basketRowData,basketColumName);
		btable.setFont(labelfont);
		btable.setRowHeight(40);
		btable.setBackground(Color.white);
		bscrollPane.setViewportView(btable);
		
		btable.addMouseListener(new MouseHandler());
	}

	private void getAllOrder() {
		olists = bdao.getAllProducts(id);
		basketRowData = new Object [olists.size()][basketColumName.length];
		fillOrderData();
		otable = new JTable(basketRowData,basketColumName);
		otable.setFont(labelfont);
		otable.setRowHeight(40);
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
				JOptionPane.showMessageDialog(this,"탈퇴 되었습니다.","회원탈퇴",JOptionPane.PLAIN_MESSAGE);
				c.show(cards, "login");
			}
			else {
				System.out.println("비밀번호가 틀렸습니다.");
				JOptionPane.showMessageDialog(this,"비밀번호가 틀렸습니다.","에러발생",JOptionPane.ERROR_MESSAGE);
				clearTextField();
			}
		}
		//		else {
		//			System.out.println("아이디가 존재하지 않습니다.");
		//			JOptionPane.showMessageDialog(this,"아이디가 존재하지 않습니다.","에러발생",JOptionPane.ERROR_MESSAGE);
		//			clearTextField();
		//		}
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
				pd = new ProductsDto();
				pd.setCode((int)(adtable.getValueAt(row, 0)));
				pd.setName((String)(adtable.getValueAt(row, 1)));
				pd.setPrice((int)(adtable.getValueAt(row, 2)));
				pd.setStock((int)(adtable.getValueAt(row, 3)));
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

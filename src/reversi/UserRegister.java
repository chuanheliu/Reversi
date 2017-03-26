package reversi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * the program start from here, you should decide to log in or sign up.
 * @author 
 *
 */
class MyPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image image = new ImageIcon("src/reversi/background1.jpg").getImage();
		g.drawImage(image, 0, 0, this);
	}
}

/**
 * regist class.
 * @author 
 */
class UserRegister extends JFrame {

	//judge whether the user has existence
	public static boolean hasCon = false; 

	private static final long serialVersionUID = 1L;
	private MyPanel panel;
	Facade fcd=new Facade();
	//username label
	private JLabel lab_Username = new JLabel("Username:");
	private JTextField jta_text = new JTextField();
	Font lab = new Font("Times New Roman",1,20);
	private JLabel lat_password = new JLabel("Password:");
	Font lat  = new Font("Times New Roman",1,20);
	private JTextField jtb_text = new JTextField();
	private JButton btn_register = new JButton("Register");
	private JButton btn_login = new JButton("Log in");
	private JButton btn_logout = new JButton("Exit");
	Font btn = new Font("Times New Roman",2,20);
	public static  int pd = 0;
	public static String ak1, ak2;

	//regist
	public void Regis() {

		this.dispose();  // Close the current interface
		new UI();   // Open new interface
	}
	//log in
	public void logIn() {		
		fcd.ConnectSQL();
		fcd.SQLverify(jta_text.getText(), jtb_text.getText());
		this.jta_text.setText("");
		this.jtb_text.setText("");
		if(hasCon)
			setVisible(false);
	}

	public UserRegister() {
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Reversi");
		this.setResizable(false);
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Regis();
			}
		});
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jta_text.getText().isEmpty()||jtb_text.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter your username", "Error", JOptionPane.WARNING_MESSAGE);
				else
					logIn();

			}
		});
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		init();

		panel = new MyPanel();
		panel.add(lab_Username);
		panel.add(lat_password);
		panel.add(jta_text);
		panel.add(jtb_text);
		panel.add(btn_register);
		panel.add(btn_login);
		panel.add(btn_logout);
		panel.setLayout(null);

		getContentPane().add(panel);
		setVisible(true);
	}


	private void init() {
		lab_Username.setSize(200,100);
		lab_Username.setLocation(60,100);
		lab_Username.setFont(lab);

		lat_password.setSize(200,100);
		lat_password.setLocation(60,140);
		lat_password.setFont(lat);

		jta_text.setSize(190,30);
		jta_text.setLocation(160,135);

		jtb_text.setSize(190,30);
		jtb_text.setLocation(160,175);

		btn_register.setSize(120, 40);
		btn_register.setLocation(15, 220);
		btn_register.setFont(btn);

		btn_login.setSize(120, 40);
		btn_login.setLocation(135, 220);
		btn_login.setFont(btn);

		btn_logout.setSize(120, 40);
		btn_logout.setLocation(255, 220);
		btn_logout.setFont(btn);
	}

	public static void main(String[] args)
			throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		new UserRegister();
	}
}
package reversi;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;

import javax.swing.*;

/**
 * UI design of the program
 * 
 */
public class UI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	Facade fcd=new Facade();

	JFrame jf; // test
	JPanel jp; // test
	JLabel jl1,jl2;
	static JTextField jtf1,jtf2;
	JButton jb1,jb2;
	Connection ct; // test

	//	private PreparedStatement as;



	public UI()	{
		jf=new JFrame();
		jp=new JPanel();
		jl1=new JLabel("Please enter your username");
		jtf1=new JTextField(10);
		jtf1.setToolTipText("The username must be a 3-6 digit letters or numbers");
		jl2=new JLabel("Please enter your password");
		jtf2=new JTextField(10);
		jtf2.setToolTipText("The password must be a 6 digit letters or numbers");


		jb1=new JButton("Return");
		jb1.setToolTipText("Click me back to login interface");
		jb2=new JButton("Register");
		jb1.addActionListener(this);
		jb2.addActionListener(this);

		jp.setLayout(new GridLayout(3,2));

		jp.add(jl1);
		jp.add(jtf1);

		jp.add(jl2);
		jp.add(jtf2);

		jp.add(jb1);
		jp.add(jb2);

		this.add(jp);
		this.setTitle("Register");
		this.setBounds(500, 300, 400, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}



	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand()=="Return")
		{
			this.dispose();
			new UserRegister().setVisible(true);

		}else if(e.getActionCommand()=="Register")
		{

			this.Register();

		}

	}
	public void Register() {
		try  //try block
		{
			//declare variables
			String usr = "";
			String pw = "";

			usr = jtf1.getText().trim();
			pw = jtf2.getText().trim();

			if (usr.equals("")|| pw.equals(""))	{
				JOptionPane.showMessageDialog(null,"Name or password is wrong","Error",JOptionPane.ERROR_MESSAGE);
			}
			ct=DriverManager.getConnection("jdbc:postgresql://mod-fund-databases.cs.bham.ac.uk:5432/oxa613", "oxa613", "zlxsclmjko");
			PreparedStatement checkAccountExists = ct.prepareStatement("SELECT * FROM users WHERE username = ?"); 
			checkAccountExists.setString(1, usr);

			try (ResultSet rs = checkAccountExists.executeQuery()) {
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "User already exists.", "Error", JOptionPane.ERROR_MESSAGE);
				} else 

				{
					String IQuery = "INSERT INTO users (username, password) VALUES('"+usr+"', '"+pw+"')";
					System.out.println(IQuery);//print on console
					System.out.println("Connecting to a selected database...");

					Class.forName("org.postgresql.Driver");
					ct=DriverManager.getConnection("jdbc:postgresql://mod-fund-databases.cs.bham.ac.uk:5432/oxa613", "oxa613", "zlxsclmjko");
					System.out.println("Connected database successfully...");

					((Connection)ct).createStatement().execute(IQuery);
					String SMessage = "Record added for "+usr;

					JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
					((java.sql.Connection)ct).close();
					dispose();
					new UserRegister().setVisible(true);
				}				
			}
		}

		catch (SQLException se) 
		{
			se.printStackTrace();
		}
		catch (Exception a) //catch block
		{
			a.printStackTrace();
		}

	}
}

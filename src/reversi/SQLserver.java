package reversi;

import java.sql.*;
import javax.swing.*;
import reversi.UserRegister;

/**
 * this class is used to connected to the SQL
 *
 */
public class SQLserver {

	static int x;
	Connection ct;
	PreparedStatement ps;
	ResultSet rs;
	String user,pwd;

	/**
	 * constructor
	 */
	public SQLserver()	{
	}
	private static final SQLserver ss=new SQLserver();

	public static SQLserver getInstance()
	{
		return ss;
	}

	
	public void ConnectSQL() {
		try {
			//load driver
			Class.forName("org.postgresql.Driver");
			//connect
			ct=DriverManager.getConnection("jdbc:postgresql://mod-fund-databases.cs.bham.ac.uk:5432/oxa613", "oxa613", "zlxsclmjko");

			System.out.println("Try to login");

		} catch (Exception e) {
			e.printStackTrace(); // error
		}
	}

	/**
	 * regist function
	 * @param id
	 * @param password
	 */
	public void UserRegis(String a,String b)	{
		try {
			ps=ct.prepareStatement("insert into users values(?,?,?,?)");
			ps.setString(1,a);
			ps.setString(2,b);


			int i=ps.executeUpdate();
			if(i==1)
			{
				JOptionPane.showMessageDialog(null, "ע��ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);

			}else
			{
				JOptionPane.showMessageDialog(null, "ע��ʧ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	//verify the user when you want to sign up
	public void SQLverify(String a,String b) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		try {
			ps=ct.prepareStatement("select * from users where username=? and password=? ");
			ps.setString(1, a);
			ps.setString(2, b);

			rs = ps.executeQuery();

			if(rs.next()) {	
				user = rs.getString(1);
				pwd = rs.getString(2);
				System.out.println("Logging in succesfull, from user:");
				System.out.println(user + "\t" + pwd + "\t");
				UserRegister.hasCon = true;
				JOptionPane.showMessageDialog(null, "Login succesful.");
				new MainScreen().setVisible(true);			

			}else {
				JOptionPane.showMessageDialog(null, "Login failed.", "ERROR", JOptionPane.ERROR_MESSAGE);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
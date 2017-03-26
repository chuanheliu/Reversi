package reversi;

import javax.swing.UnsupportedLookAndFeelException;

/*
 * Facade class
 */
public class Facade {
	
	SQLserver s=SQLserver.getInstance();
	
	//connect database
	public void ConnectSQL(){
		
		s.ConnectSQL();
	}
	
	//Verify login successfully or not
	public void SQLverify(String a,String b){
		try {
			s.SQLverify(a, b);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
}
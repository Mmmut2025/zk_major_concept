package myproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;

public class DynamicGridComposer extends SelectorComposer<Div>{
	@Wire Grid dbgrid;
	
	@Override
	public void doAfterCompose(Div comp) throws Exception {
		super.doAfterCompose(comp);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root123");
		System.out.println("connection establish");
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from emp");
		
		ArrayList<String[]> emps = new ArrayList<>();
		
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String email = rs.getString(3);
			String password = rs.getString(4);
			
			emps.add(new String[]{id+"",name,email,password});
			
		}
		
		ListModelList<String[]> lml  = new ListModelList(emps);
		dbgrid.setModel(lml);
	}
}

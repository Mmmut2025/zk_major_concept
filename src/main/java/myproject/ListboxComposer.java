package myproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

public class ListboxComposer extends SelectorComposer<Window>{
	@Wire Listbox boxlist;
	
	@Listen("onClick=#btn")
	public void getDetails() {
		//code for login user
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root123");
	    	System.out.println("database connected succesfully");
	    	PreparedStatement ps = con.prepareStatement("select * from employee");
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()) {
	    		int id = rs.getInt("id");
	    		String name = rs.getString("name");
	    		double salary = rs.getDouble("salary");
	    		
	    		System.out.println(id + " " + name + " salary");
	    		Listitem item = new Listitem(); //create row
	    		
	    		//create all 3 cell 
	    		item.appendChild(new Listcell(id+""));
	    		item.appendChild(new Listcell(name+""));
	    		item.appendChild(new Listcell(salary+""));
	    		
	    		boxlist.appendChild(item);
	    	}
		}
	    catch(Exception e1){
	  		e1.printStackTrace();
	  		System.out.println(e1.getMessage());
	  	}
	}
}

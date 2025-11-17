package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.zkoss.zk.ui.select.SelectorComposer;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import dao.MyConnection;
import dao.UserDao;



public class UserToDbAndDbToList extends SelectorComposer<Window>{
	@Wire Button submit,reset;
	@Wire Textbox txtId,txtName,txtEmail,txtPass;
	
	@Wire Listbox dblistbox;
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		
		
		Statement st = MyConnection.getMyConnection().createStatement();
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
		dblistbox.setModel(lml);
	}
	
	@Listen("onClick=#submit")
	public void submitData() {
		int id = Integer.parseInt(txtId.getValue());
		String name = txtName.getValue();
		String email = txtEmail.getValue();
		String password = txtPass.getValue();
		
		UserDao udao = new UserDao();
		boolean isInsert = udao.submitData(id,name,email,password);
		
		if(isInsert) {
			
			alert("Data successfully inserted");
			//reloadData(id,name,email,password);
		}
		else {
			alert("Somethind wrong to storing data");
		}
	}
}

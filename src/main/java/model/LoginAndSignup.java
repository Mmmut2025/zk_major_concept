package model;

import java.nio.channels.SelectableChannel;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;

public class LoginAndSignup extends SelectorComposer<Component>{
	@Wire private Textbox user,pass;
	
	@Listen("onClick=#login")
	public void loginUser(){
		Executions.sendRedirect("login.zul");
	}
	
	@Listen("onClick=#btnSubmit")
	public void HOMEPAR() {
		Executions.sendRedirect("home.zul");
	}
	
	@Listen("onClick=#logoutBtn")
	public void logout() {
		Executions.sendRedirect("index.zul");
	}
}

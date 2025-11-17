package controllers;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


public class PlayerAuthentication extends SelectorComposer<Window>{

	
	private static int TOTAL_ALVAILABLE_USERS = 2;
	private static int subscriber = 0;
	private static String orginalOTP = "";
	
	@Wire
	Textbox user;
	
	@Listen("onClick = #submit")       
	public void userEntry() {	
		String userOTP = user.getValue();
		
		if (userOTP.isBlank()) {
			Messagebox.show("Please enter valid OTP. OTP can't be empty!");
		}
		else if (orginalOTP.isBlank()) {
			orginalOTP = userOTP;
		} else if(!userOTP.equals(orginalOTP)) {
			Messagebox.show("OTP does'n matched!!");
		}
		
		if(subscriber < TOTAL_ALVAILABLE_USERS) {
			subscriber++;
			Executions.sendRedirect("ticToe.zul");
		}else {
			
				Messagebox.show("Room is full!!");
			}
		}
	@Listen("onClick = #exit")
	public void userExit() {
		subscriber--;
		
		if (subscriber == 0) {
			orginalOTP = "";
		}
		Executions.sendRedirect("index.zul");
	}
	}
	


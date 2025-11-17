package myproject;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class GameAuthentication extends SelectorComposer<Window> {
	@Wire private Textbox user;
	List<String> allUser = new ArrayList<>();
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		EventQueue<Event> EQ = EventQueues.lookup("tic2", EventQueues.APPLICATION, true);
		EQ.subscribe(new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				String userName = (String) event.getData();
				
				if(allUser.size()==0 ||  allUser.size() < 2 && allUser.get(0).equals(userName)){
					allUser.add(userName);
					Executions.sendRedirect("ticToe.zul");
				}
				else if(allUser.size() >= 2){
					Messagebox.show("You are not authorized to play game ");
				}
				else {
					Messagebox.show("Please enter correct otp");
				}
			}
		});
	}
	
	@Listen("onClick=#submit")
	public void isAuthorized() {
		String userName = user.getValue();
		
		EventQueue<Event> EQ = EventQueues.lookup("tic2", EventQueues.APPLICATION, true);
		Event event = new Event("check",null,userName);
		EQ.publish(event);
	}
	
	
}

package myproject;

import java.util.ArrayList;
import java.util.List;

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
import org.zkoss.zkmax.ui.select.annotation.Subscribe;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class TicToeComposer extends SelectorComposer<Window> {
	@Wire
	Button b1, b2, b3, b4, b5, b6, b7, b8, b9, reset, exit;
	Boolean flag = true;
	@Wire
	Textbox userOtp;
	List<String> otps = new ArrayList<>();
	List<String> sessions = new ArrayList<>();  
	EventQueue<Event> sessionQueue;
	EventQueue<Event> q;
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		q = EventQueues.lookup("tic2", EventQueues.APPLICATION, true);
		sessionQueue = EventQueues.lookup("ses",EventQueues.APPLICATION,true);
		
		sessionQueue.subscribe(e -> {
			String data = e.getData().toString();
			if((sessions.size() >= 1 && !sessions.get(sessions.size()-1).equals(data)) || sessions.size() == 0)
				sessions.add(data);
		});
	}
	

	@Subscribe(value = "tic2", scope = EventQueues.APPLICATION)
	public void doAction(Event event) {
		String eventName = event.getName();
		if (eventName.equals("auth")) {
			String otp = (String) event.getData();
			if (otps.size() == 0 || otps.size() < 2 && otps.get(0).equals(otp)) {
				otps.add(otp);
			}
		} else if (eventName.equals("exitGameNow")) {
			otps.clear();
			Executions.sendRedirect("index.zul");
		} else if(eventName.equals("resetMode")) {
			resetGame();
		}
		else if(eventName.equals("gameMode")) {
			String btnId = event.getData().toString().split("@")[0];
			String sess1 = event.getData().toString().split("@")[1];
			if(sessions.size()>=1 && sessions.get(sessions.size()-1).equals(sess1)) return;
			
			Button b = (Button) getSelf().getFellow(btnId);
			b.setLabel(flag ? "X" : "O");
			flag = !flag;
			b.setDisabled(true);

			if (b1.getLabel() == b2.getLabel() && b1.getLabel() == b3.getLabel() && b1.getLabel() != ""
					|| b4.getLabel() == b5.getLabel() && b4.getLabel() == b6.getLabel() && b4.getLabel() != ""
					|| b7.getLabel() == b8.getLabel() && b7.getLabel() == b9.getLabel() && b7.getLabel() != ""
					|| b1.getLabel() == b4.getLabel() && b1.getLabel() == b7.getLabel() && b1.getLabel() != ""
					|| b2.getLabel() == b5.getLabel() && b2.getLabel() == b8.getLabel() && b2.getLabel() != ""
					|| b3.getLabel() == b6.getLabel() && b3.getLabel() == b9.getLabel() && b3.getLabel() != ""
					|| b1.getLabel() == b5.getLabel() && b1.getLabel() == b9.getLabel() && b1.getLabel() != ""
					|| b3.getLabel() == b5.getLabel() && b3.getLabel() == b7.getLabel() && b3.getLabel() != "") {
				alert(b.getLabel() + " Win!");
				resetGame();
			}
		}
	}

	@Listen("onClick=#reset")
	public void resetGame(Event e) {
		Button b = (Button) e.getTarget();
		Event eve = new Event("resetMode", null, b.getId());
		q.publish(eve);
	}

	@Listen("onClick=#submit")
	public void isAuthenticated() {
		String otp = userOtp.getText();
		if (otp.length() < 6) {
			Messagebox.show("Please provide the otp of atleast 6 length");
			return;
		}
		
		if (otps.size() == 0 || otps.size() < 2 && otps.get(0).equals(otp)) {
			q.publish(new Event("auth", null, otp));
			Executions.sendRedirect("ticToe.zul");
		} else if (otps.size() < 2 && !otps.get(0).equals(otp)) {
			Messagebox.show("Please provide the correct otp");
		} else {
			Messagebox.show("Game Room is full");
		}
	}

	@Listen("onClick=#b1,#b2,#b3,#b4,#b5,#b6,#b7,#b8,#b9")
	public void gameOn(Event e) {
		Button b = (Button) (e.getTarget());
		
		String sess = Executions.getCurrent().getSession().getNativeSession().toString().substring(50);
		
		Event event = new Event("gameMode", null, b.getId()+"@"+sess); // Event(name,target/components,data)
		q.publish(event);	
		
		sessionQueue.publish(new Event("onSession",null,sess));
		alert(sessions.size()+"");
	}
	
	@Listen("onClick=#exit")
	public void exitGame(Event e) {
		Button b = (Button) e.getTarget();
		Event eve = new Event("exitGameNow", null, b.getId());
		q.publish(eve);
	}
	
	
	public void resetGame() {
		b1.setLabel("");
		b1.setDisabled(false);
		b2.setLabel("");
		b2.setDisabled(false);
		b3.setLabel("");
		b3.setDisabled(false);
		b4.setLabel("");
		b4.setDisabled(false);
		b5.setLabel("");
		b5.setDisabled(false);
		b6.setLabel("");
		b6.setDisabled(false);
		b7.setLabel("");
		b7.setDisabled(false);
		b8.setLabel("");
		b8.setDisabled(false);
		b9.setLabel("");
		b9.setDisabled(false);
	}
}

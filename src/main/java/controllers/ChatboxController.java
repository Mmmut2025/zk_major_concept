package controllers;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;


public class ChatboxController extends SelectorComposer<Window> implements EventListener<Event>{
	@Wire private Textbox uname , t1;
	@Wire private Vbox inf;
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		EventQueue<Event> q = EventQueues.lookup("chat", EventQueues.APPLICATION, true);
		q.subscribe(this);
		
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		Label l1 = new Label(event.getData().toString());
		l1.setParent(inf);
	}
	
	@Listen("onClick = #btn")
	public void post() {
		String text = uname.getValue() + ":"+t1.getValue();
		
		EventQueue<Event> q = EventQueues.lookup("chat", EventQueues.APPLICATION, false);
		Event e = new Event("onChat" , null , text);
		q.publish(e);
		
		t1.setValue("");
	}
}

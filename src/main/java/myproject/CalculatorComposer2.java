package myproject;

import org.zkoss.zul.Window;  

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class CalculatorComposer2 extends SelectorComposer<Window>{
	
	@Wire
	Textbox input;
	
	@Listen("onClick = button")
	public void handle(Event e) {
		Button bt = (Button)e.getTarget();	
		String label = bt.getLabel();
		
		switch(label) {
		case "AC" -> input.setValue("0");
	
		case "⌫" -> {
			
			if(input.getValue().length() == 0) {
				input.setValue("0");
			}
			input.setValue(input.getValue().substring(0,input.getValue().length()-1));
		}
		
		case "=" -> {
			try {
			String ip = input.getValue().replaceAll("÷", "/");
			Expression exp = new ExpressionBuilder(ip).build();
			input.setValue(exp.evaluate()+"");
			}
			catch(Exception e1){
				alert("please provide the correct format of calculation");
				System.out.println(e1.getMessage());
			}
		}
		
		default ->{
			input.setValue(input.getValue()+label);
		}
		}	
	}
}
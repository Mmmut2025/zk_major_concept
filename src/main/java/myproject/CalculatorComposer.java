package myproject;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

public class CalculatorComposer extends SelectorComposer<Window>{
	@Wire
	Doublebox first,second;
	@Wire
	Label res;
	
	@Listen("onClick= #clear")
	public void clearAll(Event e) {
		first.setText("");
		second.setText("");
	}
	
	// Listen to ALL buttons in one go
    @Listen("onClick = #plus, #minus, #multi, #divide")
    public void handleOperation(Event event) {
        Button clickedButton = (Button) event.getTarget();  // which button clicked?
        String operation = clickedButton.getId();   // get button id
        

     // Check if any field is empty
        if (first.getValue() == null || second.getValue() == null) {
            res.setValue("Please enter both numbers!");
            return;
        }
        
        try {
            double num1 = first.getValue();
            double num2 = second.getValue();
            double result = 0;

            switch (operation) {
                case "plus":
                    result = num1 + num2;
                    break;
                case "minus":
                    result = num1 - num2;
                    break;
                case "multi":
                    result = num1 * num2;
                    break;
                case "divide":
                    if (num2 == 0) {
                        res.setValue("Cannot divide by zero");
                        return;
                    }
                    result = (double) num1 / num2;
                    break;
            }

            res.setValue("Result: " + result);
        } catch (NumberFormatException e) {
           alert("please enter only numeric value");
        }
    }
}
package myproject;

import java.util.Collection;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

public class IdController extends SelectorComposer<Window>{
	@Wire Button b1;
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		//System.out.println(b1.getLabel());
		
		Button btn2 = (Button)b1.getFellow("b2");
		//System.out.println(btn2.getLabel());
		
//		Collection<Component> fellows = btn2.getFellows();
//		for(Component comp1:fellows) {
//			System.out.println(comp1.getId());
//		}
		
		IdSpace spaceOwner = b1.getSpaceOwner();
		System.out.println(spaceOwner);
//		
		System.out.println(((Component) spaceOwner).getFirstChild());
//		System.out.println(btn2.getRoot());
		
		Window win2 = (Window)(b1.getSpaceOwner()).getFellow("w2");
		System.out.println(win2.getChildren());
		
		
		
		
		
	}
}

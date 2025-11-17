package myproject;

import java.util.ArrayList;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.theme.Themes;

public class DynamicListBoxComposer extends SelectorComposer<Window>{
	@Wire Listbox lb1 , lb2;
	@Wire Combobox themeId;
	
	
	
	@Listen("onSelect=#themeId")
	public void chageTheme() {
		Themes.setTheme(Executions.getCurrent(), themeId.getSelectedItem().getValue());
		Executions.sendRedirect("");
	}
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		
			
		String[][] arr2 = new String[][]{
			{"Apple" ,"20","14 box"},
			{"Banana","14","20 box"},
			{"Papaya","25","10 box"}
		};
		
		ListModelArray<String[]> lma = new ListModelArray(arr2);
		
		ArrayList<String[]> fruitList = new ArrayList<>();
		fruitList.add(new String[]{"Apple","20","10 box"});
		ListModelList lml = new ListModelList(fruitList);
		
		lb1.setModel(lma);
		lb2.setModel(lml);
		
		
		
	}
	
}

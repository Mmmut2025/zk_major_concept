package myproject;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.lowagie.text.ListItem;

public class StopWatchComposer extends SelectorComposer<Window> {

    @Wire private Button startbtn, stopbtn;
    @Wire private Label timer;
    @Wire private Timer clockTimer , clockTimer2;

    private int hours = 0, minutes = 0, seconds = 0;

    @Listen("onClick = #startbtn")
    public void startTimer() {
        clockTimer.start(); 
    }

    @Listen("onClick = #stopbtn")
    public void stopTimer() {
        clockTimer.stop(); 
    }
    
    @Listen("onClick = #resetbtn")
    public void reset() {
      hours = 0;
      minutes = 0;
      seconds = 0;
      timer.setValue(String.format("%02d.%02d.%02d", hours, minutes, seconds));
      
    }

    @Listen("onTimer = #clockTimer")
    public void updateTime() {
        // Increment stopwatch
        seconds++;
        if (seconds >= 60) { seconds = 0; minutes++; }
        if (minutes >= 60) { minutes = 0; hours++; }

        timer.setValue(String.format("%02d.%02d.%02d", hours, minutes, seconds));
    }
    
        
    @Wire private Listcell indiaTime;
    @Wire private Listcell usaTime;
    @Wire private Listcell chinaTime;
    @Wire private Listcell ausTime;
    @Wire private Listcell germanyTime;
    @Wire private Timer clockTimer1;

    @Listen("onTimer = #clockTimer1")
    public void updateWorldTime() {
        indiaTime.setLabel(LocalTime.now(ZoneId.of("Asia/Kolkata")).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        usaTime.setLabel(LocalTime.now(ZoneId.of("America/New_York")).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        chinaTime.setLabel(LocalTime.now(ZoneId.of("Asia/Shanghai")).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        ausTime.setLabel(LocalTime.now(ZoneId.of("Australia/Sydney")).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        germanyTime.setLabel(LocalTime.now(ZoneId.of("Europe/Berlin")).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
    
    
    
    @Wire private Timebox timebox;
    @Wire private Listbox alarmList;
    
    List<LocalTime> alarms = new ArrayList<LocalTime>();
    
    @Listen("onClick=#add")
    public void addAlarm() {
    	Date date = timebox.getValue();
    	if (date == null) return; // safety check
    	LocalTime time = date.toInstant()
                 .atZone(ZoneId.systemDefault())
                 .toLocalTime();
    	
    	alarms.add(time);
    	
    	Listitem item = new Listitem();
    	
    	item.appendChild(new Listcell(time.toString()));
   
    	Button b = new Button("Remove");
     	b.addEventListener("onClick", e -> alarmList.removeChild(item));
     	Listcell actionCell = new Listcell();
     	actionCell.appendChild(b);
     	item.appendChild(actionCell);

    	alarmList.appendChild(item);
    }
    
    @Listen("onTimer = #clockTimer2")
    public void checkAlarms() {
        LocalTime now = LocalTime.now().withSecond(0).withNano(0); // ignore seconds for simplicity
        for (LocalTime alarm : alarms) {
            if (alarm.equals(now)) {
            	Messagebox.show("Hello! bhaiya.");
            	alarms.remove(alarm);
            	break; // prevent ConcurrentModificationException
            }
        }
    }
}

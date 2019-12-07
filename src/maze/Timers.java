package maze;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.Timer;
 
public class Timers extends JTextField { 
    private Date now = null;
    private Timer timer;
    public Timers() {
    	setEditable(false);
    	Calendar time=Calendar.getInstance();
    	time.set(0, 0, 0, 0, 0, 0);
    	now=time.getTime();
         timer= new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                now = new Date(now.getTime() + 1000);
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                setText(formatter.format(now));
            }
        });
    }
    public void start(){
    	timer.start();
    }
    public void stop(){
    	timer.stop();
    }
    public void proceed(){
    	timer.restart();
    }
	public void restart(){
    	stop();
    	Calendar time=Calendar.getInstance();
    	time.set(0, 0, 0, 0, 0, 0);
    	now=time.getTime();
         timer= new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                now = new Date(now.getTime() + 1000);
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                setText(formatter.format(now));
            }
        });
    }
}
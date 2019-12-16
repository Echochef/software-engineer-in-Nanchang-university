
import java.awt.event.*;


public class CalendarMainclass {
	public static void main(String args[]) {
		CalendarFrame frame = new CalendarFrame();
		frame.setTitle("日历应用程序");
		frame.setBounds(100, 100, 360, 300);
		frame.setVisible(true);
		frame.validate();
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        System.exit(0);//退出系统
		    }
		});
	}
}

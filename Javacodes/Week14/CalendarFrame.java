
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CalendarFrame extends Frame implements ActionListener,KeyListener{
	Label labelDay[] = new Label[42];
	Label labelYear;
	Button titleName[] = new Button[7];
	Button nextMonth, previousMonth;
	Label showMessage;
	TextField inputYear;
	CalendarBean calendar;
	String name[] = { "日", "一", "二", "三", "四", "五", "六" };
	int year = 2013, month = 1;
	String days[];

	public CalendarFrame() {		
		calendar = new CalendarBean();
		calendar.setYear(year);
		calendar.setMonth(month);
		days = calendar.getCalendar();	
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.add(getCenterPanel());		
		add(scrollPane,BorderLayout.CENTER);// 窗口添加scrollPane在中心区域
	    add(getNorthPanel(),BorderLayout.NORTH);
	    add(getSouthPanel(),BorderLayout.SOUTH);// 窗口添加pNorth 在北面区域
		
	}
	
	private Panel getNorthPanel() {
		Panel panel = new Panel();	
		labelYear=new Label("请输入年份:");
		inputYear=new TextField(5);
		nextMonth=new Button("下月");
		previousMonth=new Button("上月");
		inputYear.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				try {
					year=Integer.parseInt(inputYear.getText());
					
				}catch(NumberFormatException e1)
				{
					JOptionPane.showMessageDialog(null,"Error Year");
					inputYear.setFocusable(true);
				
				}
				calendar.setYear(year);
				calendar.setMonth(month);
				days=calendar.getCalendar();
				for(int i=0;i<42;i++)
				{
					labelDay[i].setText(days[i]);
				}
				showMessage.setText("日历"+calendar.getYear()+"年"+calendar.getMonth()+"月");
				
			}
				});
		previousMonth.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				month--;
				if(month<1)
				{
					year--;
					month=12;
				}
				calendar.setYear(year);
				calendar.setMonth(month);
				days=calendar.getCalendar();
				for(int i=0;i<42;i++)
				{
					labelDay[i].setText(days[i]);
				}
				showMessage.setText("日历"+calendar.getYear()+"年"+calendar.getMonth()+"月");
				
			}
				});
		nextMonth.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
			month++;
			if(month>12)
			{
				year++;
				month=1;
			}
			calendar.setYear(year);
			calendar.setMonth(month);
			days=calendar.getCalendar();
			for(int i=0;i<42;i++)
			{
				labelDay[i].setText(days[i]);
			}
			showMessage.setText("日历"+calendar.getYear()+"年"+calendar.getMonth()+"月");
			}
				});
		panel.add(labelYear);
		panel.add(inputYear);
		panel.add(nextMonth);
		panel.add(previousMonth);
		return panel;
	}	
	private Panel getCenterPanel() {
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(7,7));// 将panel的布局设置为7行7列的GridLayout布局
		for (int i = 0; i < 7; i++) {
			titleName[i] = new Button(name[i]);
			panel.add(titleName[i]);// panel添加组件titleName[i]。
		}
		for (int i = 0; i < 42; i++) {
			labelDay[i] = new Label("", Label.CENTER);
			panel.add(labelDay[i]);// panel添加组件labelDay[i]。
		}
		for (int i = 0; i < 42; i++) {
			labelDay[i].setText(days[i]);
		}
		return panel;
	}	
	private Panel getSouthPanel() {
		Panel panel = new Panel();
		showMessage=new Label();
		showMessage.setText("日历："+calendar.getYear()+"年"+calendar.getMonth()+"月");
		panel.add(showMessage);
		return panel;
	}
}


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class CopyOfArtFont extends JFrame {
	JComboBox<String> fontType;//字体样式下拉框, 
	JComboBox<String> fontSize;//字体大小下拉框
	JComboBox<String> windowStyle;//窗体样式下拉框
	
	JCheckBox boldBx;// 粗体按钮
	JCheckBox italicBx;// 斜体按钮
	JButton colorBtn;// 颜色按钮；
	String[] fontNames;// 字体名称;
	String[] fontSizes;// 字体大小；

	JLabel label;// 输入提示标签；
	JTextField inputText;// 文字输入框；
	JTextArea txtArea;// 文字显示区;
	JPanel northPanel;// 字体设置；
	JPanel centerPanel;// 显示效果区
	JPanel southPanel;//样式设置

	Font font;
	int boldStyle, italicStyle, underlineStyle;
	int fontSizeStyle;
	String fontNameStyle;
	Color colorStyle = Color.black;// 设置字体的默认颜色为黑色;
	String[] style = { "默认显示效果", "Windows显示效果", "Unix显示效果" };

	public CopyOfArtFont() {
		super("字体设置");
		// 设置默认字体
		boldStyle = 0;
		italicStyle = 0;
		underlineStyle = 0;
		fontSizeStyle = 10;
		fontNameStyle = "宋体";
		font = new Font(fontNameStyle, boldStyle + italicStyle, fontSizeStyle);

		northPanel = getNorthPanel();
		centerPanel = getCenterPanel();
		southPanel = getSouthPanel();
		// 设置容器;
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(northPanel,BorderLayout.NORTH);
		container.add(centerPanel,BorderLayout.CENTER);
		container.add(southPanel,BorderLayout.SOUTH);
		setSize(500, 300);
		setLocationRelativeTo(null);
		setVisible(true);	}	
	private JPanel getNorthPanel() {
		JPanel panel = new JPanel();
		label=new JLabel("输入",JLabel.LEFT);
		inputText=new JTextField(10);
		boldBx=new JCheckBox("粗体");
		italicBx=new JCheckBox("斜体");
		colorBtn=new JButton("颜色");
		inputText.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				txtArea.setText(inputText.getText());
			}
				});
		boldBx.addItemListener(new ItemListener()
				{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange()==ItemEvent.SELECTED)
					boldStyle=1;
				else
					boldStyle=0;
				font=new Font(fontNameStyle,boldStyle+italicStyle,fontSizeStyle);
				txtArea.setFont(font);
			}
				});
		italicBx.addItemListener(new ItemListener()
				{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange()==ItemEvent.SELECTED)
					italicStyle=1;
				else
					italicStyle=0;
				font=new Font(fontNameStyle,boldStyle+italicStyle,fontSizeStyle);
				txtArea.setFont(font);
			}
				});
		colorBtn.addActionListener(new ActionListener()
				{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				colorStyle=JColorChooser.showDialog(null, "choose one color", colorStyle);
				colorBtn.setForeground(colorStyle);
				txtArea.setForeground(colorStyle);
				font=new Font(fontNameStyle,boldStyle+italicStyle,fontSizeStyle);
				txtArea.setFont(font);
			}
				});
		panel.add(label);
		panel.add(inputText);
		panel.add(boldBx);
		panel.add(italicBx);
		panel.add(colorBtn);
		return panel;
	}	
	private JPanel getCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		txtArea=new JTextArea();
		panel.add(txtArea,BorderLayout.CENTER);
		return panel;
	}	
	private JPanel getSouthPanel() {
		JPanel panel = new JPanel();
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		fontNames=ge.getAvailableFontFamilyNames();
		fontType=new JComboBox(fontNames);
		fontType.setEditable(false);
		fontType.setMaximumRowCount(10);
		fontSizes=new String[63];
		for(int i=0;i<fontSizes.length;i++)
			fontSizes[i]=Integer.toString((i+10));
		fontSize=new JComboBox(fontSizes);
		fontSize.setEditable(false);
		fontSize.setMaximumRowCount(10);
		windowStyle=new JComboBox(style);
		fontType.addItemListener(new ItemListener()
				{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				fontNameStyle=(String)e.getItem();
				font=new Font(fontNameStyle,boldStyle+italicStyle,fontSizeStyle);
				txtArea.setFont(font);
			}
				});
		fontSize.addItemListener(new ItemListener()
				{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				String s=(String)e.getItem();
				fontSizeStyle=Integer.parseInt(s);
				font=new Font(fontNameStyle,boldStyle+italicStyle,fontSizeStyle);
				txtArea.setFont(font);
			}
				});
		windowStyle.addItemListener(new ItemListener()
				{
			@Override
			public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == windowStyle){
				String s = (String) e.getItem();
				String className = "";
				if (s.equals("Windows显示效果"))
					className = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
				else if (s.equals("Unix显示效果"))
					className = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
				else if (s.equals("默认显示效果"))
					className = UIManager.getCrossPlatformLookAndFeelClassName();
				try {
					UIManager.setLookAndFeel(className);
					SwingUtilities.updateComponentTreeUI(this);
				} catch (Exception de) {
					System.out.println("Exception happened!");
				}
			}
		}

				});
		panel.add(fontSize);
		panel.add(fontType);
		panel.add(windowStyle);
		return panel;
	}
	public static void main(String args[]) {
		CopyOfArtFont artFont = new CopyOfArtFont();
		artFont.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}


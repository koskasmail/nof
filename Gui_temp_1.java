import java.awt.Font;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui_temp_1 //extends JFrame 
{
	private   JFrame        frame        =   null;
//T	private   JTextPane     pane         =   null;
//T	private   JScrollPane   scroll       =   null;
    private   TextArea      ta           =   null;
	private   JPanel        jpfrm1       =   null;
	private   String        StLine       =   "";


	//
	//set man page line
	private void setText(String StText)
	{
		this.StLine = this.StLine + StText;
	}


	//
	//get man page
	private String getText()
	{
		return StLine;
	}


	private void SetManPages()
	{
		
		//check if man_page exist --else-- prisent this.
		String nr = "\r\n";
		setText(nr + " ==[ H E L P ]== " + nr + "" + nr);
		setText("1. Select a file." + nr);
		setText("2. Select a String to find in file." + nr);	    
		setText("3. Select a type of search." + nr);
		setText("4. Press submit button -  to start string seeking process in file." + nr);
		setText("5. Click the properties button to view search result." + nr);
		setText("    You can locate the result files in the  nof  local folder." + nr);
		setText("    Every search has his own result file." + nr);
		setText("" + nr);		
		setText("Note: when exit the nof program all the temp files will be erased." + nr);
		setText("" + nr);
		setText("" + nr);
		setText("" + nr);
	}



	public Gui_temp_1() 
	{	
		//read manual
		SetManPages();

		frame = new JFrame("..Help...");
		frame.setResizable(true);		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(nof.class.getResource("/icons/yamon.jpg")));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//.EXIT_ON_CLOSE);

		jpfrm1 = new JPanel();
		frame.getRootPane().add(jpfrm1);
		jpfrm1.setLayout(null);
		


		
		ta = new TextArea();
		ta.setBounds(10, 10, 420, 180);  //10, 10, 520, 280
		ta.setFont(new Font("Serif", Font.PLAIN, 16));
		ta.setEditable(false);
		ta.setText(getText());
		jpfrm1.add(ta);

	
/*	    frame.setMaximizable(true);
	       20:        setIconifiable(true);
	       21:        setClosable(true);
	       22:        setResizable(true);
*/

		
//calc full screen window
/*	    Dimension screenSize = getToolkit().getScreenSize();
	    int width = screenSize.width * 8 / 10;
	    int height = screenSize.height * 8 / 10;
	    setBounds(width/8, height/8, width, height);
	    setVisible(true);		*/
		
		
// JEditorPane
/*		JEditorPane ep = new JEditorPane();
		ep.setText("test");
		ep.setBounds(10, 10, 100, 100);
		ep.setEditable(false);
		ep.setVisible(true);        
		jpfrm1.add(ep);
*/		
		

//multi pane with GridLayout (matrix)		
		//JPanel p=new JPanel(new GridLayout(2,1));		
		//window 1
		//pane.setText(" 1. Select a file. \r\n 2. select a String to find in file. \r\n 3. select a type of search. \r\n 4. press submit button -  to start string seeking process in file. \r\n 5. click the properties button to view search result. \r\n \r\n you can locate the result files in the  nof  local folder. \r\n every search has his own result file. \r\n \r\n note: when exit the nof program all the temp files will be erased.");
		//		p.add(scroll);

		
//
// --JTextPane with scroll
/*		pane   = new JTextPane();
		pane.setText(getText());
		pane.setEditable(false);		
		scroll = new JScrollPane(pane);
		
		frame.getContentPane().add(scroll);  //.add(scrool);  //.add(scrool2);		
*/
			
		

		//
		//frame listener (main-Frame)
		//		
		frame.addWindowListener(new WindowAdapter()
		{
			//Exit(0)
			public void windowClosing(WindowEvent w)
			{
				frame.dispose();
				//	log l = new log("event","menu-bar","Exit - have been pressed");
				//	erase del = new erase(StStr);
				System.exit(0); // Exit Program
			}
		});

/*		ta.addPropertyChangeListener(new listener()
		{
			
		});*/
		
		
// Final form step.
		frame.getContentPane().add(jpfrm1);
		
		frame.setSize(546, 326); //540, 390
		frame.setVisible(true);
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

	}

	public static void main (String args [])
	{
		Gui_temp_1 h = new Gui_temp_1();
	}	
}	


//old_platform(delte after one version//
/*
//public class help { }


import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;


public class help
{
	private   JFrame     frmAbout     =   null;
	private   JPanel     panel        =   null;
	private   JLabel     lblgif1      =   null;
    private   JLabel     lblTHelp     =   null;
    private   JTextPane  txtPadHelp   =   null;

	private   JButton   Frm1BtnExit   =   null;

	public help()
	{


		//create form
		frmAbout = new JFrame("NOF...Help");
		frmAbout.setResizable(false);
		frmAbout.setIconImage(Toolkit.getDefaultToolkit().getImage(about.class.getResource("/icons/star.jpg")));

        //create panel
		panel = new JPanel();
		panel.setForeground(Color.BLACK);

		//create bound/s
		panel.setLayout(null);
		frmAbout.getContentPane().add(panel);


		lblgif1 = new JLabel("");
		lblgif1.setIcon(new ImageIcon(help.class.getResource("/icons/star.jpg")));
		lblgif1.setBounds(10, 10, 30, 30);
		panel.add(lblgif1);


		lblTHelp = new JLabel("");
		lblTHelp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTHelp.setText("Help :");
		lblTHelp.setBounds(50, 10, 60, 30);
		panel.add(lblTHelp);


		txtPadHelp = new JTextPane();
		txtPadHelp.setToolTipText("manual page / help");
		txtPadHelp.scrollRectToVisible(null);
		txtPadHelp.setEditable(false);
		txtPadHelp.setBackground(Color.LIGHT_GRAY);//White
		txtPadHelp.setBounds(10, 51, 454, 193);
		txtPadHelp.setText(" 1. Select a file. \n 2. select a String to find in file. \n 3. select a type of search. \n 4. press submit button -  to start string seeking process in file. \n 5. click the properties button to view search result. \n \n you can locate the result files in the  nof  local folder. \n every search has his own result file. \n \n note: when exit the nof program all the temp files will be erased.");
		panel.add(txtPadHelp);





		frmAbout.setSize(480, 280);
		frmAbout.setVisible(true);

		frmAbout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



		frmAbout.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent w)
			{
				frmAbout.dispose();
				System.out.println("Exit Current Window.");
				//System.exit(0); // Exit Program
			}
		});

	}


	public static void main (String args [])
	{
		help h = new help();
	}


}*/
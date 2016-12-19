import java.awt.Font;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class help //extends JFrame 
{
	private   JFrame        frame        =   null;
    private   TextArea      ta           =   null;
	private   JPanel        jpfrm1       =   null;
	private   String        StLine       =   "";
	
	private   Integer   intFontSize      =   16 ;
	private   String    FontName         =   "" ;
	

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
		setText("מחק שורה זו היא בעייתית" + nr);
		setText("" + nr);
		setText("" + nr);
		setText("" + nr);
	}



	public help() 
	{	
		//font
		this.intFontSize = 14;
		this.FontName = "Courier New" ; //"Serif", "Courier New"
		
		
		//read manual
		SetManPages();

		frame = new JFrame("..Help...");
		frame.setResizable(true);		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(nof.class.getResource("/icons/yamon.jpg")));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//.EXIT_ON_CLOSE););

		jpfrm1 = new JPanel();
		frame.getRootPane().add(jpfrm1);
		jpfrm1.setLayout(null);
		


		ta = new TextArea();
		ta.setBounds(1, 1, 790, 480);  //10, 10, 520, 280
		ta.setFont(new Font(this.FontName, Font.PLAIN, this.intFontSize));
		ta.setEditable(false);
		ta.setText(getText());
		jpfrm1.add(ta);			
		
		
		
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
				//System.exit(0); // Exit Program
			}
		});

		
		
// Final form step.
		frame.getContentPane().add(jpfrm1);
		
		//frame.setSize(546, 326); //540, 390
		frame.setSize(800, 572); //MIN(546, 326)  //MAX(800, 572)		
		frame.setVisible(true);
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

	}

	public static void main (String args [])
	{
		help h = new help();
	}	
}	


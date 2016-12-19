import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class viewer //extends JFrame 
{
	private   JFrame        frame        =   null;
    private   TextArea      ta           =   null;
	private   JPanel        jpfrm1       =   null;
	private   String        StLine       =   "";
	
	private   JButton      ViewerButtonPageTop  =   null;	
	private   JButton      ViewerButtonPageMid  =   null;
	private   JButton      ViewerButtonPageBot  =   null;	
	
	//more button:  search, file read from line + buffer 
	private   JButton      ViewerButtonClose  =   null;
	
	private   log       l    = null; //log	
	
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



	public viewer() 
	{	
		//read manual
		SetManPages();

		frame = new JFrame("..Viewer...");
		frame.setResizable(true);		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(nof.class.getResource("/icons/yamon.jpg")));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//.EXIT_ON_CLOSE);

		jpfrm1 = new JPanel();
		frame.getRootPane().add(jpfrm1);
		jpfrm1.setLayout(null);
		
		
		ta = new TextArea();
		ta.setBounds(1, 1, 790, 485);  //10, 10, 520, 280
		ta.setFont(new Font("Serif", Font.PLAIN, 16));
//		ta.setEditable(false);
		ta.setText(getText());
		jpfrm1.add(ta);			

		
		ViewerButtonClose = new JButton("Close");
		ViewerButtonClose.setBounds(3, 517, 117, 25);//1, 500, 117, 25
		ViewerButtonClose.setToolTipText("close the current window.");
		jpfrm1.add(ViewerButtonClose);

		
		ViewerButtonPageTop = new JButton("TOP");
		ViewerButtonPageTop.setBounds(3, 489, 117, 25);//1, 500, 117, 25
		ViewerButtonPageTop.setToolTipText("5ViewerButtonPageTop");
		jpfrm1.add(ViewerButtonPageTop);
		
		ViewerButtonPageMid = new JButton("MID");
		ViewerButtonPageMid.setBounds(122, 489, 117, 25);//1, 500, 117, 25
		ViewerButtonPageMid.setToolTipText("ViewerButtonPageMid");
		jpfrm1.add(ViewerButtonPageMid);
		
		ViewerButtonPageBot = new JButton("BOT");
		ViewerButtonPageBot.setBounds(241, 489, 117, 25);//1, 500, 117, 25
		ViewerButtonPageBot.setToolTipText("ViewerButtonPageBot");
		jpfrm1.add(ViewerButtonPageBot);		
		
		
		

		//
		//[hotKeys (Alt + *)]------------------------------------------
		//
		
		ViewerButtonClose.setMnemonic(KeyEvent.VK_C);   //close 
		ViewerButtonPageTop.setMnemonic(KeyEvent.VK_1); //top
		ViewerButtonPageMid.setMnemonic(KeyEvent.VK_2); //mid
		ViewerButtonPageBot.setMnemonic(KeyEvent.VK_3); //bot
		

		//
		//[listers]------------------------------------------
		//
	
		
		//
		//[Listener--ViewerButtonClose--(Close Current Window)--]------------------------------------------
		//			
		ViewerButtonClose.addActionListener(new ActionListener( )
		{
			public void actionPerformed(ActionEvent ev)
			{
				l = new log("event","Viewer","Viewer window is now closed");
				frame.dispose();
				//log l = new log("event","menu-bar","Exit - have been pressed"); //exit Frame viewer (current-window)			
				//System.exit(0); // Exit Program
			}
		});
		

		//
		//frame listener (Viewer)
		//		
		frame.addWindowListener(new WindowAdapter()
		{
			//Exit(0)
			public void windowClosing(WindowEvent w)
			{
				l = new log("event","Viewer","Viewer window is now closed");
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
		viewer h = new viewer();
	}	
}	

/*
View IDEA:
1. Window: Viewer Window / Remote Control Window + Viewer Window 
2. Button: btnTop, btnMid, btnBut, BtnDirction (RTL, LTR), 

*/



/* Example 1: append to textArea
        public void actionPerformed(ActionEvent e) {
            int retval = m_fc.showOpenDialog(FileInputGUI.this);
            if (retval == JFileChooser.APPROVE_OPTION) {
                File inFile = m_fc.getSelectedFile();
                try {
                    FileReader fr = new FileReader(inFile);
                    BufferedReader bufRdr = new BufferedReader(fr);
                    
                    String line = null;
                    while ((line = bufRdr.readLine()) != null){
                        m_fileContents.append(line);
                        m_fileContents.append("\n");
                    }
                    bufRdr.close();
                    
                } catch (IOException ioex) {
                    System.err.println(ioex);
                    System.exit(1);
                }
            }
        }//end actionPerformed
    }//end inner listener class OpenAction
}
 
 */



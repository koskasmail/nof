import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class view //extends JFrame 
{
	private   JFrame        frame        =   null ;
	private   JToolBar      bar          =   null ;	
    private   JTextArea     ta           =   null ;
	private   JPanel        jpfrm1       =   null ;
	private   String        StLine       =   ""   ;
	private   BorderLayout  bord         =   null ; 
	private   JScrollPane   scroll       =   null ;
	
	
	private   JButton      viewButtonPageTop  =   null ;	
	private   JButton      viewButtonPageMid  =   null ;
	private   JButton      viewButtonPageBot  =   null ;	
	
	//private   ImageIcon    image1             =   null ;
	//private   ImageIcon    image2             =   null ;
	//private   ImageIcon    image3             =   null ;
	//private   ImageIcon    image4             =   null ;	
	
	//TODO more button:  search, file read from line + buffer 
	private   JButton      viewButtonClose    =   null ;	
	
	private   log       l    = null; //log	

	

	//[setText], Add line to text "this.StLine"------------------------------------------
	// 
	private void setText(String StText)
	{
		this.StLine = this.StLine + StText;
	}
	

	//[getText], Add line from text "this.StLine"------------------------------------------
	// 
	private String getText()
	{
		return StLine;
	}
	

	//[SetManPages], Fixed Man Pages (del.me)------------------------------------------
	//
	private void SetManPages()
	{	
		//check if man_page exist --else-- prisent this.
		String nr = "\r\n";
		setText(nr + " ==[ H E L P ** ]== " + nr + "" + nr);
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

	
	//[view], GUI MAIN MENU + Listner------------------------------------------
	//
	public view() 
	{	
		//read manual
		SetManPages();

		frame = new JFrame("..view...");
		frame.setResizable(true);		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(nof.class.getResource("/icons/yamon.jpg")));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//.EXIT_ON_CLOSE);

		jpfrm1 = new JPanel();
		frame.getRootPane().add(jpfrm1);
		jpfrm1.setLayout(null);

		
       //image1 = new ImageIcon("pgup.jpg");  
       //viewButtonPageTop = new JButton(" head ",image1);
       //viewButtonPageTop = new JButton(" head ",new ImageIcon(view.class.getResource("/icons/pgup.jpg")));
       viewButtonPageTop = new JButton("head ");
       viewButtonPageTop.setToolTipText("file read buffer up");

       
       //image2 = new ImageIcon("icons/pgdown.jpg"); 
       viewButtonPageMid = new JButton("clear");
       viewButtonPageMid.setToolTipText("file read buffer down");       
	        
      // image3 = new ImageIcon("icons/nof.jpg");
       viewButtonPageBot = new JButton("tail ");

      // image4 = new ImageIcon("icons/go.jpg");
       viewButtonClose = new JButton("close");       
       	         
       bar = new JToolBar();        
       bar.setToolTipText("Tool Bar");
       
       
       bar.add(viewButtonPageTop);
       bar.add(viewButtonPageMid);
       bar.add(viewButtonPageBot);
       bar.add(viewButtonClose);
       
       
       //text erea with scrollpane
       ta = new JTextArea(8,40);
       scroll = new JScrollPane(ta);
       
       //border - add 2 windows to 1
       bord = new BorderLayout();
       jpfrm1.setLayout(bord);
       
	   //toolbar + text erea
	   jpfrm1.add("North",bar); //ta
	   jpfrm1.add("Center",scroll);				
		
	   ta.setText(getText());
		

		//
		//[hotKeys (Alt + *)]------------------------------------------
		//	
		viewButtonClose.setMnemonic(KeyEvent.VK_C);   //close 
		viewButtonPageTop.setMnemonic(KeyEvent.VK_1); //top
		viewButtonPageMid.setMnemonic(KeyEvent.VK_2); //mid
		viewButtonPageBot.setMnemonic(KeyEvent.VK_3); //bot
		
		

		//
		//[listers]------------------------------------------
		//
	
		
		//
		//[Listener--viewButtonClose--(Close Current Window)--]------------------------------------------
		//			
		viewButtonClose.addActionListener(new ActionListener( )
		{
			public void actionPerformed(ActionEvent ev)
			{
				l = new log("event","view","view window is now closed");
				frame.dispose();
				//log l = new log("event","menu-bar","Exit - have been pressed"); //exit Frame view (current-window)			
				//System.exit(0); // Exit Program
			}
		});
		

		//
		//frame listener (view)
		//		
		frame.addWindowListener(new WindowAdapter()
		{
			//Exit(0)
			public void windowClosing(WindowEvent w)
			{
				l = new log("event","view","view window is now closed");
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
		view h = new view();
	}	
}	


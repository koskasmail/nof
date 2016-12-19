

import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.eclipse.wb.swing.FocusTraversalOnArray;



public class nof_ver_1 
{
	private   String[ ]    fileItems = new String[ ] { "Help", "About",  "Exit" };
	private   JTextField   textField;

	private   String     StSourceFileName = null;

	private   String     StStr          = null;     //source
	private   String[]   StStrSplit;                //target - array
	private   String     delimiter      = ",";      //delimiter for breaking the string


	//property
	private   long      intTotalLineNumber   = 0;
	private   long      intTotalCharInFile   = 0;
	private   long      intFileSize          = 0;
	private   long      intTotalMatches      = 0;
	private   long []   intToatalperSearch   = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//array (5 + 40).
	private   String    stFileName           = null;
	private   int       intSeekType          = 1; //which radio buttun.


	//file choser
	private   JFileChooser   fileopen   =   new JFileChooser();


	//Gui - Variable

	private   JFrame       frame                =   null;
	private   JPanel       panel                =   null;

	private   JMenuBar     mbar                 =   null;
	private   JMenu        fileMenu             =   null;
	private   JMenuItem    item                 =   null;

	private   JPanel       jpfrm1               =   null;

	private   JTextField   frm1txtSelectFile    =   null;
	private   JLabel       frm1lblStringSeek1   =   null;

	private   JButton      Frm1SelectFile       =   null;
	private   JButton      Frm1BtProperties     =   null;
	private   JButton      Frm1BtnExit          =   null;
	private   JButton      Frm1Butclean         =   null;

	private   JButton      Frm1Butsubmit        =   null;

	private   JTextField   frm1txtStringSeek1   =   null;


	//Radio Button
	private   JRadioButton   rbfull                    =   null; //full record/line
	private   JRadioButton   rbFromTo                  =   null; //from min_char_number_position -> max_char_number_position
	private   JRadioButton   rbFromToEndOfSeekString   =   null; //from min_char_number_position --> count(seeksting);
	private   JRadioButton   rbfromToEnd               =   null; // from this char_number_position -> till max of the record/line

	private    JLabel        frm1lblfromchar           =   null;
	private    JTextField    frm1txtfromchar           =   null;
	private    JLabel        frm1lbltochar             =   null;
	private    JTextField    frm1txttochar             =   null;


	private   String        StSerachFull               =   "search_all";
	private   String        StrbFromTo                 =   "search_x..y";
	private   String        StFromToEndOfSeekString    =   "search_x..count(y)";
	private   String        StFromToEnd                =   "search_x..eol";
	private   ButtonGroup   grpSearch                  =   null;

	//file read
	private   String   str   =   null;

	//property enable_true/false
	private   boolean  blproperty = false;

	//****

	//private   JProgressBar   pbRR;

	static    long   minlf   =   0;  // read file - min line.
	static    long   maxlf   =   0;	 // read file - max line.
	static    long   currif  =   0;  // current record
	private   long   curper  =   0;  // current pecent
	private   long   preper  =   0;  // preper if priv = curper

	static   pbar   spb     =   null;

	public static  int    os   =   0  ;  //0=windows;1=linux;


	//private Graphics g;


	
	

	//
	//---[setRadioButtonSelection]---------------------------------
	//			
	private void setRadioButtonSelection(int intCase)
	{
		rbfull.setFont(new Font("Arial", Font.PLAIN, 11));
		rbFromTo.setFont(new Font("Arial", Font.PLAIN, 11));
		rbFromToEndOfSeekString.setFont(new Font("Arial", Font.PLAIN, 11));
		rbfromToEnd.setFont(new Font("Arial", Font.PLAIN, 11));

		switch (intCase)
		{
		case 1:
			rbfull.setFont(new Font("Arial", Font.BOLD, 11));
			break;
		case 2:
			rbFromTo.setFont(new Font("Arial", Font.BOLD, 11));
			break;
		case 3:
			rbFromToEndOfSeekString.setFont(new Font("Arial", Font.BOLD, 11));
			break;
		case 4:
			rbfromToEnd.setFont(new Font("Arial", Font.BOLD, 11));
			break;
		}
	}

	
	//
	//---[SetVisibleOnOff]---------------------------------
	//		
	private void SetVisibleOnOff(int intValue)
	{
		switch (intValue)
		{
		case 0: //off
			frame.setEnabled(false);
			Frm1SelectFile.setEnabled(false);
			frm1txtSelectFile.setEnabled(false);
			frm1txtStringSeek1.setEnabled(false);
			Frm1Butsubmit.setEnabled(false);
			Frm1Butclean.setEnabled(false);
			Frm1BtProperties.setEnabled(false);
			Frm1BtnExit.setEnabled(false);
			frm1lblStringSeek1.setEnabled(false);
			rbfull.setEnabled(false);
			rbFromTo.setEnabled(false);
			rbFromToEndOfSeekString.setEnabled(false);
			rbfromToEnd.setEnabled(false);
			frm1lblfromchar.setEnabled(false);
			frm1lbltochar.setEnabled(false);
			frm1txtfromchar.setEnabled(false);
			frm1txttochar.setEnabled(false);
			fileMenu.setEnabled(false);
			frame.update(frame.getGraphics());
		break;
		case 1: //on
			frame.setEnabled(true);
			Frm1SelectFile.setEnabled(true);
			frm1txtSelectFile.setEnabled(true);
			frm1txtStringSeek1.setEnabled(true);
			Frm1Butsubmit.setEnabled(true);
			Frm1Butclean.setEnabled(true);
			Frm1BtProperties.setEnabled(true);
			Frm1BtnExit.setEnabled(true);
			frm1lblStringSeek1.setEnabled(true);
			rbfull.setEnabled(true);
			rbFromTo.setEnabled(true);
			rbFromToEndOfSeekString.setEnabled(true);
			rbfromToEnd.setEnabled(true);
			frm1lblfromchar.setEnabled(true);
			frm1lbltochar.setEnabled(true);
			frm1txtfromchar.setEnabled(true);
			frm1txttochar.setEnabled(true);
			fileMenu.setEnabled(true);
			frame.update(frame.getGraphics());
		break;		
		}		
	}

	
	//
	//---[SetVisibleAction]---------------------------------
	//	
	private void SetVisibleAction(int intValue)
	{
		switch (intValue)
		{
		case 1:
			frm1lblfromchar.setVisible(false);
			frm1txtfromchar.setVisible(false);
			frm1lbltochar.setVisible(false);
			frm1txttochar.setVisible(false);
			frm1txtfromchar.setText("");
			frm1txttochar.setText("");
			break;
		case 2:
			frm1lblfromchar.setVisible(true);
			frm1txtfromchar.setVisible(true);
			frm1lbltochar.setVisible(true);
			frm1txttochar.setVisible(true);
			frm1txtfromchar.setText("");
			frm1txttochar.setText("");
			break;
		case 3:
		case 4:
			frm1lblfromchar.setVisible(true);
			frm1txtfromchar.setVisible(true);
			frm1lbltochar.setVisible(false);
			frm1txttochar.setVisible(false);
			frm1txtfromchar.setText("");
			frm1txttochar.setText("");
			break;
		}
	}

	
	//
	//---[fnReadTextFileProperty]---------------------------------
	//
	public int fnReadTextFileProperty(String StTextFileName) throws Exception
	{
		intTotalLineNumber = 0;
		intTotalCharInFile = 0;
		stFileName = StTextFileName;
		intFileSize = 0;

		//file size.
		File fs = new File(stFileName);
		intFileSize = fs.length();
		intFileSize = (intFileSize/1024);

		if (intFileSize == 0)
			intFileSize = 1;

		pbar spb2 = new pbar();
		spb2.setStatus(0, 100, 0); //min,max,cur
		spb2.pbStart();
		spb2.setCurValue(0);
		spb2.label.setText("Calculating...");
		//spb.pb.setVisible(false);
		spb2.frame.update(spb2.frame.getGraphics());
		frame.update(frame.getGraphics());

		try
		{
			str = null;
			BufferedReader in = new BufferedReader(new FileReader(StTextFileName));

			while ((str = in.readLine()) != null)
			{
				intTotalLineNumber++;
				intTotalCharInFile = intTotalCharInFile + str.length();
			}
			in.close();
		}
		catch (IOException e)
		{
			log l = new log("warning","File","<"+StTextFileName+">"+ " Not Exist, Please Select Another File " + e + "." );
			return 0;
		}

		maxlf = intTotalLineNumber;

		spb2.setCurValue(100);
		spb2.frame.dispose();


		//System.out.println(" **********~~~~ " + intTotalLineNumber + " ~~~~ " + intTotalCharInFile + " ~~~~ " + intFileSize);
		return 0;
	}


	//
	//---[fnWriteResultToTextFile]---------------------------------
	//
	private void fnWriteResultToTextFile(long SearchFileIndex, String NewLineToAdd, String Search) throws Exception
	{
		String StTargetFileName = "./FileTmp_" + Search + ".nof";

		log l = new log("data","<"+Search+">","<"+StTargetFileName+">"+ "~~~>   " + NewLineToAdd);

		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(StTargetFileName, true));

			if (os == 0)
			{
			   out.write("\n\r" + NewLineToAdd + "\n\r"); //"\n\r"
			   //System.out.println("=nof=os-"+os);
			}
			
			if (os == 1)
			{
               out.write(NewLineToAdd + "\n");
			   //System.out.println("=nof=os-"+os);
			}

			out.close();
		}
		catch (IOException e)
		{
			//TODO
			l = new log("info","File","<"+StSourceFileName+">"+ " Not Exist, Please Select Another File. " + e);
		}
	}

	/*	private void updateBar ()
	{
		pbRR.setValue((int) curper);
	}
	 */



	//
	//---[fnReadTextFile]---------------------------------
	//
	private int fnReadTextFile(String StTextFileName) throws Exception
	{
		//int i = 0;
		int index = 0;

		intTotalMatches = 0;

		curper = 0; //current record value
		currif = 0; //current record pecent

		//*******

		spb = new pbar();
		spb.setStatus(0, 100, 0); //min,max,cur
		spb.pbStart();
		spb.setCurValue(0);
		spb.frame.update(spb.frame.getGraphics());
		frame.update(frame.getGraphics());


		//*******

		try
		{
			str = null;
			BufferedReader in = new BufferedReader(new FileReader(StTextFileName));

			//reset value =0
			for (index=0; index < intToatalperSearch.length ; index++)
			{
				intToatalperSearch[index] = 0;
			}

			while ((str = in.readLine()) != null)
			{

				for(index=0; index < StStrSplit.length ; index++)
				{
					switch (intSeekType)
					{
					//full
					case 1:
						if (str.toLowerCase().matches(".*"+StStrSplit[index].toLowerCase()+".*") == true)
						{
							intTotalMatches++;
							intToatalperSearch[index] = intToatalperSearch[index] + 1;
							fnWriteResultToTextFile(index,str.toLowerCase(), StStrSplit[index].toLowerCase());
						}
						break;
						//x..y
					case 2:
						if ((!frm1txtfromchar.getText().equals(null)) &&
								(!frm1txtfromchar.getText().equals("")) &&
								(!frm1txttochar.getText().equals(null)) &&
								(!frm1txttochar.getText().equals("")))
						{

							//x,y
							int intfrom = Integer.valueOf(frm1txtfromchar.getText());
							int intto = Integer.valueOf(frm1txttochar.getText());
							int inttmp = 0;

							if ((str.length() > 0) && (intto > 0) && (intfrom > 0))
							{

								if (intfrom > intto)
								{
									inttmp = intfrom;
									intfrom = intto;
									intto = inttmp;
								}


								if (str.length() < intto)
								{
									continue;
									//intto = str.length();
								}


								if (str.length() < intfrom)
								{
									continue;
									//intfrom = 1;
								}

								if (str.toLowerCase().substring(intfrom-1,intto).matches(".*"+StStrSplit[index].toLowerCase()+".*") == true)
								{
									intTotalMatches++;
									intToatalperSearch[index] = intToatalperSearch[index] + 1;

									fnWriteResultToTextFile(index,str.toLowerCase(), StStrSplit[index].toLowerCase());
								}
							}
							else
							{
								//=0--> skip/do nothing.
							}


						}
						else  ////***if empty, do like case = 1; (all the line) ****////
						{
							if (str.toLowerCase().matches(".*"+StStrSplit[index].toLowerCase()+".*") == true)
							{
								intTotalMatches++;
								intToatalperSearch[index] = intToatalperSearch[index] + 1;

								fnWriteResultToTextFile(index,str.toLowerCase(), StStrSplit[index].toLowerCase());
							}
						}
						break;
					case 3:
						if ((!frm1txtfromchar.getText().equals(null)) &&
								(!frm1txtfromchar.getText().equals("")))
						{
							//x,y
							int intfrom = Integer.valueOf(frm1txtfromchar.getText());
							int intto = Integer.valueOf((StStrSplit[index].length() + intfrom)-1);


							if ((str.length() > 0) && (intto > 0) && (intfrom > 0))
							{
								if (str.length() < intto)
								{
									continue;
									//intto = str.length();
								}


								if (str.length() < intfrom)
								{
									continue;
									//intfrom = 1;
								}


								if (str.toLowerCase().substring(intfrom-1,intto).matches(".*"+StStrSplit[index].toLowerCase()+".*") == true)
								{
									intTotalMatches++;
									intToatalperSearch[index] = intToatalperSearch[index] + 1;

									fnWriteResultToTextFile(index,str.toLowerCase(), StStrSplit[index].toLowerCase());
								}
							}
							else
							{
								//=0--> skip/do nothing.
							}
						}
						else  ////***if empty, do like case = 1; (all the line) ****////
						{
							if (str.toLowerCase().matches(".*"+StStrSplit[index].toLowerCase()+".*") == true)
							{
								intTotalMatches++;
								intToatalperSearch[index] = intToatalperSearch[index] + 1;

								fnWriteResultToTextFile(index,str.toLowerCase(), StStrSplit[index].toLowerCase());
							}
						}
						break;
					case 4:
						if ((!frm1txtfromchar.getText().equals(null)) &&
								(!frm1txtfromchar.getText().equals("")))
						{
							//x,y
							int intfrom = Integer.valueOf(frm1txtfromchar.getText());
							int intto = Integer.valueOf(str.length());


							if ((str.length() > 0) && (intto > 0) && (intfrom > 0))
							{
								if (str.length() < intfrom)
								{
									continue;
								}


								if (str.toLowerCase().substring(intfrom-1,intto).matches(".*"+StStrSplit[index].toLowerCase()+".*") == true)
								{
									intTotalMatches++;
									intToatalperSearch[index] = intToatalperSearch[index] + 1;

									fnWriteResultToTextFile(index,str.toLowerCase(), StStrSplit[index].toLowerCase());
								}
							}
							else
							{
								//=0--> skip/do nothing.
							}
						}
						else ////***if empty, do like case = 1; (all the line) ****////
						{
							if (str.toLowerCase().matches(".*"+StStrSplit[index].toLowerCase()+".*") == true)
							{
								intTotalMatches++;
								intToatalperSearch[index] = intToatalperSearch[index] + 1;

								fnWriteResultToTextFile(index,str.toLowerCase(), StStrSplit[index].toLowerCase());
							}
						}
						break;
					}

				}

				//
				// (%) percent
				//
				currif++;

				double c =  (double) currif / maxlf;
				curper =  (long) ((double) c * 100);


				spb.setCurValue((int)curper);
				
				//spb.pb.setValue((int) this.currif);


				if (curper > preper)
				{
					spb.frame.update(spb.frame.getGraphics());
					frame.update(frame.getGraphics());
				}

				preper = curper;


				System.out.println(" " + maxlf + "--" + currif + "~~~-> " + curper + "%");


				//intTotalLineNumber++;
				//System.out.println("  " + intTotalLineNumber + "  ");
			}
			in.close();
		}
		catch (IOException e)
		{
			log l = new log("warning","File","<"+StTextFileName+">"+ " Not Exist, Please Select Another File " + e + "." );
			return 0;
		}

		
		
		log l = new log("output","source","file-name"+ "~~~>   " + "<" + stFileName + ">");
		l = new log("output","result","Total chars in file "+ "~~~>   " + "<" + intTotalCharInFile + ">");
		l = new log("output","result","Total lines in file "+ "~~~>   " + "<" + intTotalLineNumber + ">");
		l = new log("output","result","File Size "+ "~~~>   " + "<" + intFileSize + " kb>");
		l = new log("output","result","Total matches in file "+ "~~~>   " + "<" + intTotalMatches + ">");
		//System.out.println(" **********~~~~ " + intTotalLineNumber + " ~~~~ " + intTotalCharInFile + " ~~~~ " + intFileSize);

		//
		//print result
		for(index=0; index < StStrSplit.length ; index++)
		{
			l = new log("output",StStrSplit[index], "~~~>   " + "<" + intToatalperSearch[index]+ ">");
		}
		return 1;
	}

	//
	//---[check_file_existence]---------------------------------
	//
	private boolean check_file_existence (String sfn)
	{
		File file=new File(sfn);
		boolean exists = file.exists();

		return exists; //true = exist;
	}

	
	//
	//
	//---[check_Gui_Field_value]---------------------------------
	//
	private boolean check_Gui_Field_value()
	{
	   boolean exists = true;
	   
	   if ( (frm1txtSelectFile.getText().equals("")) || (frm1txtSelectFile.getText().equals(null)) || (frm1txtStringSeek1.getText().equals(null)) || (frm1txtStringSeek1.getText().equals("")) )
         {
		   JOptionPane.showMessageDialog(frame ,"~~ WARNING ~~ \n \n Data Missing... \n Select: File-Name, Seek String  \n ","WARNING", JOptionPane.WARNING_MESSAGE);
		   log l = new log("Warning","No user interaction","the user did not enter a filename or did not enter a seek string");
		   exists = false;
	     }
	   else
	   {
		   exists = true;
	   }
	   
	   return exists;
	}

	//
	//
	//
	private nof_ver_1()
	{
		blproperty = false;
	
		

		frame = new JFrame("NOF...");
		frame.setResizable(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(nof_ver_1.class.getResource("/icons/star.jpg")));
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//[Frame.Menu-Bar]------------------------------------------
		// menu-bar + pop up result.
		mbar = new JMenuBar();
		fileMenu = new JMenu("Menu");

		
		//listers
		ActionListener printListener = new ActionListener(  )
		{
			public void actionPerformed(ActionEvent event)
			{
				if (event.getActionCommand().equals("Help"))
				{
					log l = new log("event","menu-bar","Help - have been pressed");
					//System.out.println("___HELP___");
					//JOptionPane.showMessageDialog(frame ,"~~ HELP ~~ \n 1. Select a file. \n 2. select a String to find in file. \n 3. select a type of search. \n 4. press submit button -  to start string seeking process in file. ","Help", JOptionPane.INFORMATION_MESSAGE);
					help h = new help();
				}

				if (event.getActionCommand().equals("About"))
				{
					log l = new log("event","menu-bar","About - have been pressed");
					//System.out.println("___About___");
					//JOptionPane.showMessageDialog(frame ," Name: NOF \n Version: 1.0 \n \n Developed by: Yaron Kessler \n \n Powered by: Java \n License: Free for use","About", JOptionPane.INFORMATION_MESSAGE);
					about nf = new about();
				}

				//Exit(0)
				if (event.getActionCommand().equals("Exit"))
				{
					frame.dispose();
					log l = new log("event","menu-bar","Exit - have been pressed");
					erase del = new erase(StStr);
					System.exit(0); // Exit Program
				}
			}
		};

		//Create the "File" menu items and add to menu
		for (int i=0; i < fileItems.length; i++)
		{
			item = new JMenuItem(fileItems[i]);
			item.addActionListener(printListener);
			fileMenu.add(item);
		}


		mbar.add(fileMenu);


		jpfrm1 = new JPanel();
		frame.getRootPane().add(jpfrm1);
		jpfrm1.setLayout(null);



		Frm1SelectFile = new JButton("select a file");
		Frm1SelectFile.setBounds(12, 20, 117, 25);
		Frm1SelectFile.setToolTipText("please select a source file, for the processing data job - automatically.");
		jpfrm1.add(Frm1SelectFile);



		frm1txtSelectFile = new JTextField("");
		frm1txtSelectFile.setBounds(150, 20, 366, 22); //y100
		frm1txtSelectFile.setFont(new Font("Dialog", Font.BOLD, 12));
		frm1txtSelectFile.setToolTipText("please select a source file, for the processing data job - manually.");
		jpfrm1.add(frm1txtSelectFile);


		frm1lblStringSeek1 = new JLabel("Seek a String : ");
		frm1lblStringSeek1.setBounds(18, 60, 110, 22);
		frm1lblStringSeek1.setFont(new Font("Dialog", Font.BOLD, 12));
		frm1lblStringSeek1.setToolTipText("please select the string that you wish to search the file for.");
		jpfrm1.add(frm1lblStringSeek1);


		frm1txtStringSeek1 = new JTextField();
		frm1txtStringSeek1.setBounds(150, 60, 366, 22);
		frm1txtStringSeek1.setFont(new Font("Dialog", Font.BOLD, 12));
		frm1txtStringSeek1.setText(null);
		frm1txtStringSeek1.setToolTipText("please select the string that you wish to search the file for.");
		jpfrm1.add(frm1txtStringSeek1);



		Frm1Butsubmit = new JButton("Submit");
		Frm1Butsubmit.setActionCommand("submit");
		Frm1Butsubmit.setBounds(12, 220, 117, 25);
		Frm1Butsubmit.setToolTipText("press submit to start process.");
		jpfrm1.add(Frm1Butsubmit);



		Frm1Butclean = new JButton("Clear");
		Frm1Butclean.setBounds(141, 220, 117, 25);
		Frm1Butclean.setToolTipText("press clear to clear the search fields.");
		jpfrm1.add(Frm1Butclean);


		/*		frm1lblsubstatus = new JLabel("");
		frm1lblsubstatus.setBounds(12, 300, 505, 22);
		frm1lblsubstatus.setFont(new Font("Dialog", Font.BOLD, 12));
		jpfrm1.add(frm1lblsubstatus);*/



		Frm1BtProperties = new JButton("Properties");
		Frm1BtProperties.setBounds(270, 220, 117, 25); //12, 260, 117, 25
		Frm1BtProperties.setToolTipText("press Properties to view the result of the search.");
		jpfrm1.add(Frm1BtProperties);

		/*		//add label to panel + panel to frame
		Frm1Btn2 = new JButton("View");
		Frm1Btn2.setBounds(141, 260, 117, 25);
		jpfrm1.add(Frm1Btn2);
		 */

		/*		Frm1Btn3 = new JButton("Result");
		Frm1Btn3.setBounds(270, 260, 117, 25);
		jpfrm1.add(Frm1Btn3);
		 */

		Frm1BtnExit = new JButton(".Exit.");
		Frm1BtnExit.setBounds(399, 220, 117, 25); //399, 260, 117, 25
		Frm1BtnExit.setToolTipText("press exit to terminate this application.");
		jpfrm1.add(Frm1BtnExit);



		frm1lblfromchar = new JLabel("x: ");
		frm1lblfromchar.setBounds(200, 119, 20, 22);
		frm1lblfromchar.setFont(new Font("Dialog", Font.BOLD, 12));
		jpfrm1.add(frm1lblfromchar);


		frm1txtfromchar = new JTextField(3);
		frm1txtfromchar.setBounds(220, 120, 100, 22);
		frm1txtfromchar.setFont(new Font("Dialog", Font.BOLD, 12));
		frm1txtfromchar.setText(null);
		jpfrm1.add(frm1txtfromchar);



		frm1lbltochar = new JLabel("y: ");
		frm1lbltochar.setBounds(200, 149, 20, 22);
		frm1lbltochar.setFont(new Font("Dialog", Font.BOLD, 12));
		jpfrm1.add(frm1lbltochar);


		frm1txttochar = new JTextField(3);
		frm1txttochar.setBounds(220, 150, 100, 22);
		frm1txttochar.setFont(new Font("Dialog", Font.BOLD, 12));
		frm1txttochar.setText(null);
		jpfrm1.add(frm1txttochar);



		//Radio - Create the radio buttons.
		rbfull = new JRadioButton(StSerachFull);
		rbfull.setFont(new Font("Arial", Font.PLAIN, 11));
		rbfull.setActionCommand(StSerachFull);
		rbfull.setBounds(12, 100, 160, 22);
		rbfull.setSelected(true);
		rbfull.setToolTipText(StSerachFull + " --> search string in the full record/line.");
		jpfrm1.add(rbfull);



		//Radio - Create the radio buttons.
		rbFromTo = new JRadioButton(StrbFromTo);
		rbFromTo.setFont(new Font("Arial", Font.PLAIN, 11));
		rbFromTo.setActionCommand(StrbFromTo);
		rbFromTo.setBounds(12, 120, 160, 22);
		rbFromTo.setSelected(false);
		rbFromTo.setToolTipText(StrbFromTo + " --> search string in record, from x to y.");
		jpfrm1.add(rbFromTo);



		//Radio - Create the radio buttons.
		rbFromToEndOfSeekString = new JRadioButton(StFromToEndOfSeekString);
		rbFromToEndOfSeekString.setFont(new Font("Arial", Font.PLAIN, 11));
		rbFromToEndOfSeekString.setActionCommand(StFromToEndOfSeekString);
		rbFromToEndOfSeekString.setBounds(12, 140, 160, 22);
		rbFromToEndOfSeekString.setSelected(false);
		rbFromToEndOfSeekString.setToolTipText(StFromToEndOfSeekString + " --> search string in record, from x to y.");
		jpfrm1.add(rbFromToEndOfSeekString);



		//Radio - Create the radio buttons.
		rbfromToEnd = new JRadioButton(StFromToEnd);
		rbfromToEnd.setFont(new Font("Arial", Font.PLAIN, 11));
		rbfromToEnd.setActionCommand(StFromToEnd);
		rbfromToEnd.setBounds(12, 160, 160, 22);
		rbfromToEnd.setSelected(false);
		rbfromToEnd.setToolTipText(StSerachFull + " --> search string in record, from x to end-of-record/line.");
		jpfrm1.add(rbfromToEnd);



		//Radio --> Group.
		grpSearch = new ButtonGroup();
		grpSearch.add(rbfull);
		grpSearch.add(rbFromTo);
		grpSearch.add(rbFromToEndOfSeekString);
		grpSearch.add(rbfromToEnd);


		//default visible
		SetVisibleAction(1);
		setRadioButtonSelection(1);


		//
		//hotKeys (Alt + *)
		//

		rbfull.setMnemonic(KeyEvent.VK_1);
		rbFromTo.setMnemonic(KeyEvent.VK_2);
		rbFromToEndOfSeekString.setMnemonic(KeyEvent.VK_3);
		rbfromToEnd.setMnemonic(KeyEvent.VK_4);


		Frm1Butclean.setMnemonic(KeyEvent.VK_C);
		Frm1Butsubmit.setMnemonic(KeyEvent.VK_S);
		Frm1BtProperties.setMnemonic(KeyEvent.VK_P);
		Frm1BtnExit.setMnemonic(KeyEvent.VK_E);
		
		Frm1SelectFile.setMnemonic(KeyEvent.VK_F);




		//progress bar
		/*		pbRR = new JProgressBar();
		pbRR.setMinimum(0);
		pbRR.setMaximum(100);
		pbRR.setValue(0);
		pbRR.setBounds(12, 200, 150, 16);
		//pbRR.setIndeterminate(false); //true = no value; false= with value
		jpfrm1.add(pbRR);
		 */


		//
		// *** Don't Delete ***
		//
		/*		//KEYBOARD - listener (demo)
		KeyListener keyListener = new KeyListener()
		{
			public void keyPressed(KeyEvent keyEvent)
			{
				printIt("Pressed", keyEvent);
			}
			public void keyReleased(KeyEvent keyEvent)
			{
				printIt("Released", keyEvent);
			}
			public void keyTyped(KeyEvent keyEvent)
			{
				printIt("Typed", keyEvent);
			}

			private void printIt(String title, KeyEvent keyEvent)
			{
				int keyCode = keyEvent.getKeyCode();
				String keyText = KeyEvent.getKeyText(keyCode);
				System.out.println(title + " : " + keyText);
			}
		};
		frm1txttochar.addKeyListener(keyListener);
		 */


		//
		// *** Listener/s ***
		//

		// frm1txtfromchar
		// frm1txttochar
		// KEYBOARD - listener
		KeyListener keyListener = new KeyListener()
		{
			public void keyPressed(KeyEvent keyEvent)	{ 	}

			public void keyReleased(KeyEvent keyEvent)
			{
				int keyCode = keyEvent.getKeyCode();

				//!(65-90=a-z;48-57=0-9; 32=spacebar;backspace=8;caps=20;num=144;scroll=145;sapce=32;
				if ((keyCode >=65 && keyCode <= 90) || (keyCode == 32))
				{
					//not 0-9 number value
					frm1txttochar.setText("");
					frm1txtfromchar.setText("");
				}

			}
			public void keyTyped(KeyEvent keyEvent)	{ }
		};

		frm1txtfromchar.addKeyListener(keyListener);
		frm1txttochar.addKeyListener(keyListener);


		//
		//frame listener (main-Frame)
		//
		frame.addWindowListener(new WindowAdapter()
		{
			//Exit(0)
			public void windowClosing(WindowEvent w)
			{
				frame.dispose();
				log l = new log("event","menu-bar","Exit - have been pressed");
				erase del = new erase(StStr);
				System.exit(0); // Exit Program
			}
		});



		//
		// Action Listeners --- <rbfull>
		//
		rbfull.addActionListener(new ActionListener( )
		{
			public void actionPerformed(ActionEvent e)
			{
				intSeekType = 1;
				setRadioButtonSelection(intSeekType);
				SetVisibleAction(intSeekType);

				log l = new log("event","Sorting","rbfull - have been pressed");
			}
		});

		//
		// Action Listeners --- <rbFromTo>
		//
		rbFromTo.addActionListener(new ActionListener( )
		{
			public void actionPerformed(ActionEvent e)
			{
				intSeekType = 2;
				setRadioButtonSelection(intSeekType);
				SetVisibleAction(intSeekType);

				log l = new log("event","Sorting","rbFromTo - have been pressed");
			}
		});

		//
		// Action Listeners --- <rbFromToEndOfSeekString>
		//
		rbFromToEndOfSeekString.addActionListener(new ActionListener( )
		{
			public void actionPerformed(ActionEvent e)
			{
				intSeekType = 3;
				setRadioButtonSelection(intSeekType);
				SetVisibleAction(intSeekType);

				log l = new log("event","Sorting","rbFromToEndOfSeekString - have been pressed");
			}
		});

		//
		// Action Listeners --- <rbfromToEnd>
		//
		rbfromToEnd.addActionListener(new ActionListener( )
		{
			public void actionPerformed(ActionEvent e)
			{
				intSeekType = 4;
				setRadioButtonSelection(intSeekType);
				SetVisibleAction(intSeekType);

				log l = new log("event","Sorting","rbfromToEnd - have been pressed");
			}
		});



		//
		// Action Listeners --- <execute>
		//
		Frm1SelectFile.addActionListener(new ActionListener( )
		{
			public void actionPerformed(ActionEvent ev)
			{

				int ret = fileopen.showDialog(panel, "Select a file...");//"Open file"

				if (ret == JFileChooser.APPROVE_OPTION)
				{
					File file = fileopen.getSelectedFile();

					StSourceFileName = file.toString();
					frm1txtSelectFile.setText(StSourceFileName);
					log l = new log("info","File Chooser",file.toString());
				}
			}
		});


		Frm1Butclean.addActionListener(new ActionListener( )
		{
			public void actionPerformed(ActionEvent ev)
			{
				StSourceFileName = null;
				frm1txtSelectFile.setText("");
				frm1txtStringSeek1.setText(null);
				intSeekType = 1;
				setRadioButtonSelection(intSeekType);
				rbfull.setSelected(true);
				SetVisibleAction(1);
				blproperty = false;
			}
		});


		//Frm1Butsubmit
		Frm1Butsubmit.addActionListener(new ActionListener( )
		{

			public void actionPerformed(ActionEvent ev)
			{
				SetVisibleOnOff(0);

				//**refresh button.
				Frm1Butsubmit.update(Frm1Butsubmit.getGraphics());
				frame.update(frame.getGraphics());

				boolean blFileExist;
				
				//***
				//yak-todo: send function --> frm1txtSelectFile.getText(), check value, return true/false
				//if false write an error message box,
				//if true continu.
				
				//***yak create function "check_field_file_name" return true or false
				if ( (frm1txtSelectFile.getText().equals("")) || (frm1txtSelectFile.getText().equals(null)) || (frm1txtStringSeek1.getText().equals(null)) || (frm1txtStringSeek1.getText().equals("")) )
				{
					JOptionPane.showMessageDialog(frame ,"~~ WARNING ~~ \n \n Data Missing... \n Select: File-Name, Seek String  \n ","WARNING", JOptionPane.WARNING_MESSAGE);
					log l = new log("Warning","No user interaction","the user did not enter a filename or did not enter a seek string");
				}
/*				if (check_Gui_Field_value() == true)
				{
					
				}
*/				
				//***yak check if  "check_field_file_name"  = false
				else
				{
					//***yak
					//2. check if file-exist. (
					StSourceFileName = frm1txtSelectFile.getText();

					blFileExist = check_file_existence(StSourceFileName);
					if (blFileExist == true)
					{
						log l = new log("info","File","<"+StSourceFileName+">" + " Exist");



						//TODO check if StStr exist - yes=erase all old files(erase.java) / no=skip

						if (StStr != null)
						{
							erase del = new erase(StStr);
						}

						// StStr <-- frm1txtStringSeek1
						StStr = frm1txtStringSeek1.getText();


						//split into String Array.
						StStrSplit = StStr.split(delimiter);
						//l = new log("input","String Seek ",StStr);


						/* print substrings */
						for(int i =0; i < StStrSplit.length ; i++)
						{
							l = new log("input","String Seek",StStrSplit[i]);
						}


						// write to log - reading source file.
						l = new log("info","File Reading", " " + frm1txtSelectFile.getText().toString() + "");


						try
						{
							//create ReadFileproperty.java
							//count min/max
							fnReadTextFileProperty(frm1txtSelectFile.getText().toString());

							//create ReadFileDate.java
							//read file
							fnReadTextFile(frm1txtSelectFile.getText().toString());
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}


						//write to log - end of process.
						l = new log("info","sub process", "have been completed");


						//3.Properties -> property the file (enable)
						blproperty = true;

					}
					else
					{
						log l = new log("info","File","<"+StSourceFileName+">"+ " Not Exist, Please Select Another File.");
					} //file not exits.

					//3. end of process
				}

/*				Frm1Butsubmit.setEnabled(true);
				frame.setEnabled(true);*/
				SetVisibleOnOff(1);
			}
		});


		Frm1BtProperties.addActionListener(new ActionListener( )
		{
			public void actionPerformed(ActionEvent ev)
			{
				log l = new log("event","Frm1BtProperties", "have been pressed");
				if (blproperty == true) //property
				{
					//System.out.println("~~property~~");
					//"file-name"+ "~~~>   " + "<" + stFileName + ">"
					//"Total lines in file "+ "~~~>   " + "<" + intTotalLineNumber+ ">"
					//"Total matches in file "+ "~~~>   " + "<" + intTotalMatches+ ">"

					int     index  =  0;
					//String  stCol  =  "";
					
					property pr = new property();
					pr.setResult(" Source file name"      , "   " + stFileName                         ,0);
					pr.setResult(" Total lines in file"   , "   " + String.valueOf(intTotalLineNumber) ,1);
					pr.setResult(" Total char in file"    , "   " + String.valueOf(intTotalCharInFile) ,2);
					pr.setResult(" Source file Size"      , "   " + String.valueOf(intFileSize)+" kb " ,3);
					pr.setResult(" Total matches in file" , "   " + String.valueOf(intTotalMatches)    ,4);					
					
					
					
					
					
					for(index=0; index < StStrSplit.length ; index++)
					{
						//l = new log("output",StStrSplit[index], "~~~>   " + "<" + intToatalperSearch[index]+ ">");
						//stCol = stCol + StStrSplit[index] + "  ~~~>   <" + intToatalperSearch[index] + "> \n  ";
						pr.setResult(" <<  " + String.valueOf(StStrSplit[index]) + "  >> ", "   " + String.valueOf(intToatalperSearch[index])+ "  match",index+5);
						//pr.setResult();
					}
				
					
					pr.getResult();
					//JOptionPane.showMessageDialog(frame ,"~~ property ~~ \n \n file-name ~~~>  <" + stFileName + "> \n Total lines in file ~~~>  < " + intTotalLineNumber+ "> \n Total char in file ~~~>  < " + intTotalCharInFile + "> \n File Size ~~~>  < " + intFileSize + " kb> \n Total matches in file ~~~>  <" + intTotalMatches+ "> \n total matches: \n" + stCol + "\n" ,"property", JOptionPane.INFORMATION_MESSAGE);
				}

				if (blproperty == false) //no property
				{
					JOptionPane.showMessageDialog(frame ,"~~ property ~~ \n \n no properties exist, please submit a search job first \n ","property", JOptionPane.INFORMATION_MESSAGE);
					//System.out.println("no properties exist, please submit a search job first.");
				}
			}
		});



		//Exit(0)
		Frm1BtnExit.addActionListener(new ActionListener( )
		{
			public void actionPerformed(ActionEvent ev)
			{
				frame.dispose();
				log l = new log("event","menu-bar","Exit - have been pressed");
				erase del = new erase(StStr);
				System.exit(0); // Exit Program
			}
		});



		//adding
		frame.setJMenuBar(mbar); //adding menu-bar --> frame
		frame.getContentPane().add(jpfrm1);


		frame.setSize(536, 316); //540, 390
		//frame.setSize(800, 572);frame.setSize(800, 572);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		//
		//Order By Tab/s.Tab
		frame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{Frm1SelectFile, frm1txtSelectFile, frm1txtStringSeek1, rbfull, rbFromTo, rbFromToEndOfSeekString, rbfromToEnd, frm1txtfromchar, frm1txttochar, Frm1Butsubmit, Frm1Butclean, Frm1BtProperties, Frm1BtnExit}));

		
/*		//OS - 0=win; 1=lin
		log l = new log("info","Nof Software","nof software has started successfully");
		
		this.os = 0; 
		l.os = this.os;
*/		
	}

	//
	//Main()
	//
	public static void main(String[] args)  throws PropertyVetoException
	{
		nof_ver_1 nf = new nof_ver_1();	
		log l = new log("info","Nof Software","nof software has started successfully");

		if (args.length > 0)
		{
			nf.os = Integer.valueOf(args[0]);

			if ((nf.os == 0) || (nf.os == 1))
			{
				l.os = nf.os;
				System.out.println("os = " + nf.os);
			}			
			
			if ((nf.os != 0) && (nf.os != 1))
			{
				nf.os = 0; 
				l.os = nf.os;
				System.out.println("os = Windows");
			}
		}
		else
		{	
			System.out.println("os = WINdows");
			//OS - 0=win; 1=lin
			nf.os = 0; 
			l.os = nf.os;	
		}

		//		javax.swing.SwingUtilities.invokeLater(new Runnable()
		//		{
		//			public void run()
		//			{
		//				new nof().start();
		//			}
		//		});
	}
}




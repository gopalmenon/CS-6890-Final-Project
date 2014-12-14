package menon.cs6890.FinalProject.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import menon.cs6890.FinalProject.Controller.UserRequestParser;

@SuppressWarnings("serial")
public class UserDialog extends JFrame {
	
	private static final int DIALOG_WIDTH = 640;
	private static final int DIALOG_HEIGHT = 480;
	private static final String EMPTY_STRING = "";
	public static final String RESERVATION_AGENT_PROMPT = "Fres: ";
	private static final String RESERVATION_AGENT_STARTING_QUESTION = "Hi. I'm Fres, the Flight REservation System. How may I help you today?";
	private static final String USER_PROMPT = "You: ";
	private static final String NEW_LINE_PLUS_SPACER = "\n\n";
	
	private UserRequestParser userRequestParser;
	
    /**
     * Constructor
     * 
     * @param SCreen Title
     */
    public UserDialog(String name) {
        super(name);
        setResizable(false);
        setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userRequestParser = new UserRequestParser();
   }
    
   private void createUserInterface() {
	   
	   	Container contentPane = this.getContentPane();
	   	contentPane.setLayout(new BorderLayout());

	   	//Create a panel for the text area containing the conversation
	   	JPanel conversationPanel = new JPanel();
	   	conversationPanel.setLayout(new BorderLayout());
	   	JTextArea conversationHistory = new JTextArea(28, 10);
	   	conversationHistory.setEditable(false);
	   	conversationHistory.setLineWrap(true);
	   	conversationHistory.setWrapStyleWord(true);
	   	conversationHistory.setText(RESERVATION_AGENT_PROMPT + RESERVATION_AGENT_STARTING_QUESTION);
	   	conversationHistory.setBackground(Color.LIGHT_GRAY);
	   	JScrollPane conversationHistoryScrollPane = new JScrollPane(conversationHistory);
	   	conversationHistoryScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	   	conversationPanel.add(new JLabel(" "), BorderLayout.WEST);
	   	conversationPanel.add(conversationHistoryScrollPane, BorderLayout.CENTER);
	   	conversationPanel.add(new JLabel(" "), BorderLayout.EAST);
	   	
	   	//Create a panel for the user input and Send button
	   	JPanel userInteractionPanel = new JPanel();
	   	userInteractionPanel.setLayout(new BorderLayout());	   	
	   	JTextField userInput = new JTextField(50);
	   	userInput.addActionListener(this.new UserInputActionListener(conversationHistory, userInput));
	   	JButton sendButton = new JButton("Send");
	   	sendButton.addActionListener(this.new UserInputActionListener(conversationHistory, userInput));
	   	userInteractionPanel.add(new JLabel(" "), BorderLayout.WEST);
	   	userInteractionPanel.add(userInput, BorderLayout.CENTER);
	   	userInteractionPanel.add(sendButton, BorderLayout.EAST);
	   	
	   	contentPane.add(conversationPanel, BorderLayout.NORTH);
	   	contentPane.add(userInteractionPanel, BorderLayout.CENTER);
    	this.pack();
    	this.setVisible(true);
	   	userInput.requestFocusInWindow();

   }
    
    
   /**
    * Create the GUI and show it.  For thread safety,
    * this method is invoked from the
    * event dispatch thread.
    */
   private static void createAndShowGUI() {
       //Create and set up the window.
   		UserDialog frame = new UserDialog("Flight Reservation System");
       //Create the UI
       frame.createUserInterface();
   }
    
   public static void main(String[] args) {
       /* Use an appropriate Look and Feel */
       try {
           UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
       } catch (UnsupportedLookAndFeelException ex) {
           ex.printStackTrace();
       } catch (IllegalAccessException ex) {
           ex.printStackTrace();
       } catch (InstantiationException ex) {
           ex.printStackTrace();
       } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
       }
       /* Turn off metal's use of bold fonts */
       UIManager.put("swing.boldMetal", Boolean.FALSE);
        
       //Schedule a job for the event dispatch thread:
       //creating and showing this application's GUI.
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               createAndShowGUI();
           }
       });
   }
   
   private class UserInputActionListener implements ActionListener {
   	
		private JTextArea conversationHistory;
		private JTextField userInput;
		
		public UserInputActionListener(JTextArea conversationHistory, JTextField userInput) {
			this.conversationHistory = conversationHistory;
			this.userInput = userInput;
		}
   	
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String userQuery = userInput.getText().trim();
			
			if (!EMPTY_STRING.equals(userQuery.trim())) {
				this.conversationHistory.append(NEW_LINE_PLUS_SPACER + USER_PROMPT + userQuery);
				this.conversationHistory.setCaretPosition(this.conversationHistory.getDocument().getLength());
				
				this.conversationHistory.append(NEW_LINE_PLUS_SPACER + UserDialog.this.userRequestParser.parse(userQuery));
			}
			
			this.userInput.setText(EMPTY_STRING);
		}
   	
   }
}

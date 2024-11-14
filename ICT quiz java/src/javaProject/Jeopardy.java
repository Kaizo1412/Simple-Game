package javaProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jeopardy extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	//Declare variables
	public static final int WIDTH = 1100; //Dimensions of Jeopardy game board
	public static final int HEIGHT = 1000;
	private JPanel board; //Name of board(panel)
	private JPanel info;
	private JButton [] myJeopardy; //Array for buttons
	private JButton reset;
	private JLabel player1Wins; //Number of wins per player
	private JLabel player2Wins;
	private static String player1; //Variable to store player 1 name
	private static String player2; //Variable to store player 2 name
	private String answer;
	private static int p1Score; //Variable to keep track of player 1 score
	private static int p2Score; //Variable to keep track to player 2 score
	private int score;
	private int turn;
	
/*************************************************************************/
	public Jeopardy(Color theColor){
		super("ICT QUIZ"); //Name that appears at the top
		setSize(WIDTH, HEIGHT); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5,5));
		
		board = new JPanel(new GridLayout(6, 6,15,5)); // Grid layout width = 6, height = 7
		getContentPane().setBackground(Color.BLACK);
		
		myJeopardy = new JButton[36];// Array number
		for (int i = 0; i < myJeopardy.length; i++){ //Loop to construct each button
			myJeopardy[i] = new JButton(""+i);// construct new button
			categoryPics(i); //Call method to display pictures on each button
			
			//Make only question buttons clickable
			if(i >5){
			myJeopardy[i].addActionListener(this); // To make it clickable
			}//End if
			
			myJeopardy[i].setSize(10,10);
			categories(i); //Call method to set button background colors
			myJeopardy[i].setForeground(Color.BLACK);
			board.add(myJeopardy[i]); 
		}// End for
				
		add(board, BorderLayout.CENTER);
		info = new JPanel(new FlowLayout());// layout of another panel at bottom of game
		reset = new JButton("RESET"); //Reset button
		reset.addActionListener(this); //Make reset button clickable
		player1Wins = new JLabel(player1 + "'s Score: ");
		player2Wins = new JLabel(player2 + "'s Score: ");
		info.add(player1Wins); //Add buttons to board
		info.add(reset);
		info.add(player2Wins);
		add(info, BorderLayout.SOUTH);
	}//End Jeopardy
	
/*************************************************************************/

	public static void main(String[] args) {
		
		int input = 0; //Get user input
				
		do{ //Menu to display rules, enter player names, play game and exit menu
			input = Integer.parseInt(JOptionPane.showInputDialog("1. View the rules of the game \n"
					+ "2. Enter Player One and Player Two names \n"
					+ "3. Play the game! \n"
					+ "4. Exit Menu"));
			
			if (input == 1){ //Rules of the game
				JOptionPane.showMessageDialog(null, "1. This is a two player game. \n"
						+ "2. Must pick a category and a point value to play \n"
						+ "3. Click on the chosen box for the question. \n"
						+ "4. Correct responses must satisfy the demands of both the clue and the category. \n"
						+ "5. Responses must be spelled correctly \n"
						+ "6. Click the “Reset” button to start all over.\n"
						+ "7. Player with highest dollar amount at end of game wins", "Rules",
						JOptionPane.INFORMATION_MESSAGE);
				
			}//End if
				else if (input == 2){ //Enter player 1 and player 2 names
					player1 = JOptionPane.showInputDialog("Enter Player One's name: ");
					player2 = JOptionPane.showInputDialog("Enter Player Two's name: ");
				}//End else
					else if (input == 3){
						while (player1 == null || player2 == null){ //While loop validation if no entry for names
							JOptionPane.showMessageDialog(null, "You didn't enter a name for Player One or Player Two. \n"
									+ "Please enter names for Player One or Player Two: ");
							player1 = JOptionPane.showInputDialog("Enter Player One's name: ");
							player2 = JOptionPane.showInputDialog("Enter Player Two's name: ");
						}//End while
						
						//Start Game
						Jeopardy w = new Jeopardy (Color.RED);
						w.setVisible(true);
					}//End else if
		}
		while (input > 0 && input < 4);
		
	}//End main
	/************************************************************************/
	public void actionPerformed(ActionEvent e) {
		System.out.println(e); //What prints out in console
		String actionCommand = e.getActionCommand();
		System.out.println("this is the item : " + actionCommand); //Prints out index location of button in array
		
		if ( actionCommand.equals("RESET")){
			resetBoard(); //Call to reset game
		}//End if
		else{
			int pos = Integer.parseInt(actionCommand);// convert string into int
			System.out.println(pos);
			if (turn % 2 == 0){
				questions(pos, score); //Call method to display questions for each button
				p1Score += score; //Running total score
				player1Wins.setText(player1 + "'s Score: $" + p1Score); //Display scores and player names
				turn++;
			}//if
			else if (turn % 2 == 1){
				questions(pos, score);
				p2Score += score;
				player2Wins.setText(player2 + "'s Score: $" + p2Score); //Display scores and player names
				turn++;
			}//End else if
		}//End else
		winner(); //Call to display winner
	}//End actionPerformed

	/************************************************************************/
	public void winner(){
		//If every button is disabled, the game ends and displays the winner's name
		if  (myJeopardy[6].isEnabled() == false && myJeopardy[7].isEnabled() == false && myJeopardy[8].isEnabled() == false &&
			myJeopardy[9].isEnabled() == false && myJeopardy[10].isEnabled() == false && myJeopardy[11].isEnabled() == false &&
			myJeopardy[12].isEnabled() == false && myJeopardy[13].isEnabled() == false && myJeopardy[14].isEnabled() == false &&
			myJeopardy[15].isEnabled() == false && myJeopardy[16].isEnabled() == false && myJeopardy[17].isEnabled() == false &&
			myJeopardy[18].isEnabled() == false && myJeopardy[19].isEnabled() == false && myJeopardy[20].isEnabled() == false &&
			myJeopardy[21].isEnabled() == false && myJeopardy[22].isEnabled() == false && myJeopardy[23].isEnabled() == false &&
			myJeopardy[24].isEnabled() == false && myJeopardy[25].isEnabled() == false && myJeopardy[26].isEnabled() == false &&
			myJeopardy[27].isEnabled() == false && myJeopardy[28].isEnabled() == false && myJeopardy[29].isEnabled() == false &&
			myJeopardy[30].isEnabled() == false && myJeopardy[31].isEnabled() == false && myJeopardy[32].isEnabled() == false &&
			myJeopardy[33].isEnabled() == false && myJeopardy[34].isEnabled() == false && myJeopardy[35].isEnabled() == false ){
				if (p1Score > p2Score || p1Score >= 6300){
					JOptionPane.showMessageDialog(null, player1 + " is the WINNER!!!" + "\nyour score : " + p1Score,"SCORE",JOptionPane.INFORMATION_MESSAGE);
					disableButtons(); //Call to disable buttons
				}
				else if (p1Score < p2Score || p2Score >= 6300){
					JOptionPane.showMessageDialog(null, player2 + " is the WINNER!!!" + "\nyour score : " + p2Score,"SCORE",JOptionPane.INFORMATION_MESSAGE);
					disableButtons(); //Call to disable buttons
				}
		}
	}//End winner
	
	/************************************************************************/

	public int questions(int pos, int score){
	
		switch (pos){
		//computer engineering Questions
		case 6: myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html> computer engineering for $100 </html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("What does CPU stand for?\r\n" + //
										"\r\n" + //
										"a) Central Processing Unit\r\n" + //
										"b) Computer Personal Unit\r\n" + //
										"c) Central Power Unit\r\n" + //
										"d) Computer Processing Unit");
				answer = answer.toLowerCase();
				
				if (answer.equals("a") || answer.equals("central processing unit")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\nCorrect Answer : Central Processing Unit");
					score = moneyWrong(pos);
				}
			break;
			/******************/
		case 12: myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html> computer engineering for $200 </html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("Which programming language is known as the backbone of web development?\r\n" + //
										"\r\n" + //
										"a) Python\r\n" + //
										"b) JavaScript\r\n" + //
										"c) C++\r\n" + //
										"d) Java");
				answer = answer.toLowerCase();
				
				if (answer.equals("b") || answer.equals("javascript")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\nCorrect Answer : JavaScript");
					score = moneyWrong(pos);
				}
			break;
			/************/
		case 18:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html> computer engineering for $300 </html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("What is the primary function of a router in a computer network?\r\n" + //
										"\r\n" + //
										"a) To store data\r\n" + //
										"b) To provide power\r\n" + //
										"c) To route data between different networks\r\n" + //
										"d) To compile code");
				answer = answer.toLowerCase();
				
				if (answer.equals("c") || answer.equals("to route data between different networks")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: To route data between different networks");
					score = moneyWrong(pos);
				}
				break;
			/****************/
		case 24: myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html> computer engineering for $400</html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("What is the primary difference between DRAM (Dynamic RAM) and SRAM (Static RAM)?\r\n" + //
										"\r\n" + //
										"a) DRAM is faster than SRAM\r\n" + //
										"b) SRAM is more power-efficient than DRAM\r\n" + //
										"c) DRAM needs to be refreshed periodically, while SRAM does not\r\n" + //
										"d) SRAM is used for hard drives, while DRAM is used for CPUs");
				answer = answer.toLowerCase();
				
				if (answer.equals("c") || answer.equals("dram needs to be refreshed periodically, while sram does not")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: DRAM needs to be refreshed periodically, while SRAM does not");
					score = moneyWrong(pos);
				}
			break;
			/****************/
		case 30:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html> computer engineering for $500 </html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("In computer architecture, what does the term \"pipelining\" refer to?\r\n" + //
										"\r\n" + //
										"a) A method of compressing data\r\n" + //
										"b) A technique where multiple instruction phases are overlapped\r\n" + //
										"c) A way to improve battery efficiency\r\n" + //
										"d) A type of network protocol");
				answer = answer.toLowerCase();
				
				if (answer.equals("b") || answer.equals("a technique where multiple instruction phases are overlapped")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: A technique where multiple instruction phases are overlapped");
					score = moneyWrong(pos);
				}
			break;

		/************************************************************************/

		//Networking 
		case 7:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html>Networking for $100</html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("What does \"IP\" stand for in \"IP address\"?\r\n" + //
										"\r\n" + //
										"a) Internet Provider\r\n" + //
										"b) Internet Protocol\r\n" + //
										"c) Internet Program\r\n" + //
										"d) Internal Protocol");
				answer = answer.toLowerCase();
				
				if (answer.equals("b") || answer.equals("internet Protocol")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Internet Protocol");
					score = moneyWrong(pos);
				}
			break;
			/*************/
		case 13:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html>Networking for $200</html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog(" Which device is used to connect multiple devices on a local area network (LAN)?\r\n" + //
										"\r\n" + //
										"a) Modem\r\n" + //
										"b) Router\r\n" + //
										"c) Switch\r\n" + //
										"d) Firewall");
				answer = answer.toLowerCase();
				
				if (answer.equals("c") || answer.equals("switch")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Switch");
					score = moneyWrong(pos);
				}
			break;
			/************/
		case 19:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html>Networking for $300</html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("What is the purpose of the Domain Name System (DNS)?\r\n" + //
										"\r\n" + //
										"a) To provide a security layer for networks\r\n" + //
										"b) To translate domain names into IP addresses\r\n" + //
										"c) To assign MAC addresses to devices\r\n" + //
										"d) To route data between different networks");
				answer = answer.toLowerCase();
				
				if (answer.equals("b") || answer.equals("to translate domain names into IP addresses")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: To translate domain names into IP addresses");
					score = moneyWrong(pos);
				}
			break;
			/************/
		case 25:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html>Networking for $400</html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("What is the primary function of the OSI model's Transport Layer?\r\n" + //
										"\r\n" + //
										"a) To establish, manage, and terminate connections\r\n" + //
										"b) To ensure error-free data transmission\r\n" + //
										"c) To route data packets across networks\r\n" + //
										"d) To format data for end-user presentation");
				answer = answer.toLowerCase();
				
				if (answer.equals("a") || answer.equals("to establish, manage, and terminate connections")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: To establish, manage, and terminate connections");
					score = moneyWrong(pos);
				}
			break;
			/************/
		case 31:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html>Networking for $500</html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("What is the main advantage of using MPLS (Multiprotocol Label Switching) in a network?\r\n" + //
										"\r\n" + //
										"a) It increases the speed of wireless connections\r\n" + //
										"b) It reduces the need for network switches\r\n" + //
										"c) It provides end-to-end encryption\r\n" + //
										"d) It simplifies the routing process by using labels");
				answer = answer.toLowerCase();
				
				if (answer.equals("d") || answer.equals("it simplifies the routing process by using labels")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: It simplifies the routing process by using labels");
					score = moneyWrong(pos);
				}
			break;

			/************************************************************************/

			//Multimedia
			case 8:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
					myJeopardy[pos].setText("<html>Multimedia for $100</html>");
					myJeopardy[pos].setEnabled(false);
					myJeopardy[pos].setBackground(Color.BLACK);
					myJeopardy[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("What does JPEG stand for?\r\n" + //
												"\r\n" + //
												"a) Java Photographic Editing Group\r\n" + //
												"b) Java Picture Editing Graphics\r\n" + //
												"c) Joint Pixel Editing Group\r\n" + //
												"d) Joint Photographic Experts Group");
					answer = answer.toLowerCase();
					
					if (answer.equals("d") || answer.equals("joint photographic experts group")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = money(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Joint Photographic Experts Group");
						score = moneyWrong(pos);
					}
				break;
				/*************/
			case 14:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
					myJeopardy[pos].setText("<html>Multimedia for $200<</html>");
					myJeopardy[pos].setEnabled(false);
					myJeopardy[pos].setBackground(Color.BLACK);
					myJeopardy[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("Which file format is commonly used for audio files?\r\n" + //
												"\r\n" + //
												"a) .mp3\r\n" + //
												"b) .txt\r\n" + //
												"c) .exe\r\n" + //
												"d) .pdf");
					answer = answer.toLowerCase();
					
					if (answer.equals("a") || answer.equals(".mp3")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = money(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: .mp3");
						score = moneyWrong(pos);
					}
				break;
				/************/
			case 20:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
					myJeopardy[pos].setText("<html>Multimedia for $300<</html>");
					myJeopardy[pos].setEnabled(false);
					myJeopardy[pos].setBackground(Color.BLACK);
					myJeopardy[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("Which of these is a lossy audio compression format?\r\n" + //
												"\r\n" + //
												"a) FLAC\r\n" + //
												"b) WAV\r\n" + //
												"c) MP3\r\n" + //
												"d) ALAC");
					answer = answer.toLowerCase();
					
					if (answer.equals("c") || answer.equals("mp3")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = money(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: MP3");
						score = moneyWrong(pos);
					}
				break;
				/************/
			case 26:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
					myJeopardy[pos].setText("<html>Multimedia for $400<</html>");
					myJeopardy[pos].setEnabled(false);
					myJeopardy[pos].setBackground(Color.BLACK);
					myJeopardy[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("In video compression, what does the term \"GOP\" stand for and what is its significance?\r\n" + //
												"\r\n" + //
												"a) General Output Protocol; it determines the output format of video\r\n" + //
												"b) Group of Pictures; it defines the sequence of frames between keyframes\r\n" + //
												"c) Group of Pixels; it refers to a cluster of pixels processed together\r\n" + //
												"d) General Operation Parameter; it sets the parameters for video editing");
					answer = answer.toLowerCase();
					
					if (answer.equals("b") || answer.equals("group of Pictures; it defines the sequence of frames between keyframes")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = money(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Group of Pictures; it defines the sequence of frames between keyframes");
						score = moneyWrong(pos);
					}
				break;
				/************/
			case 32:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
					myJeopardy[pos].setText("<html>Multimedia for $500<</html>");
					myJeopardy[pos].setEnabled(false);
					myJeopardy[pos].setBackground(Color.BLACK);
					myJeopardy[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("When it comes to video editing and production, which of the following statements is true regarding the use of interlaced video?\r\n" + //
												"\r\n" + //
												"a) Interlaced video provides better quality than progressive video for fast-moving scenes.\r\n" + //
												"b) Interlaced video displays each frame as a single complete image.\r\n" + //
												"c) Interlaced video reduces file size by displaying alternating sets of lines.\r\n" + //
												"d) Interlaced video is primarily used in modern digital video formats.");
					answer = answer.toLowerCase();
					
					if (answer.equals("c") || answer.equals("interlaced video reduces file size by displaying alternating sets of lines")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = money(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Interlaced video reduces file size by displaying alternating sets of lines");
						score = moneyWrong(pos);
					}
				break;

				/************************************************************************/
		
		//Security 
		case 9:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html>Security for $100</html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("What does \"HTTPS\" stand for?\r\n" + //
										"\r\n" + //
										"a) High Text Transfer Protocol Service\r\n" + //
										"b) High Text Transfer Protocol Secure\r\n" + //
										"c) HyperText Transfer Protocol Service\r\n" + //
										"d) HyperText Transfer Protocol Secure");
				answer = answer.toLowerCase();
				
				if (answer.equals("d") || answer.equals("hyperText transfer protocol secure")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: HyperText Transfer Protocol Secure");
					score = moneyWrong(pos);
				}
			break;
			/*************/
		case 15:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html>Security for $200</html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("Which of the following is a common method used by attackers to gain unauthorized access to systems?\r\n" + //
										"\r\n" + //
										"a) Phishing\r\n" + //
										"b) Streaming\r\n" + //
										"c) Browsing\r\n" + //
										"d) Encoding");
				answer = answer.toLowerCase();
				
				if (answer.equals("a") || answer.equals("Phishing")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Phishing");
					score = moneyWrong(pos);
				}
			break;
			/************/
		case 21:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html>Security for $300</html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("Which of the following statements about two-factor authentication (2FA) is NOT true?\r\n" + //
										"\r\n" + //
										"a) Two-factor authentication requires two different types of credentials for verification.\r\n" + //
										"b) Two-factor authentication completely eliminates the risk of unauthorized access.\r\n" + //
										"c) Two-factor authentication can include something you know, something you have, or something you are.\r\n" + //
										"d) Two-factor authentication adds an extra layer of security beyond just a password.");
				answer = answer.toLowerCase();
				
				if (answer.equals("b") || answer.equals("two-factor authentication completely eliminates the risk of unauthorized access.")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Two-factor authentication completely eliminates the risk of unauthorized access.");
					score = moneyWrong(pos);
				}
			break;
			/************/
		case 27:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,10));
				myJeopardy[pos].setText("<html>Security for $400</html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("What is the main purpose of the Public Key Infrastructure (PKI)?\r\n" + //
										"\r\n" + //
										"a) To manage digital certificates and public-key encryption\r\n" + //
										"b) To compress data for storage\r\n" + //
										"c) To monitor network traffic\r\n" + //
										"d) To provide secure wireless communication");
				answer = answer.toLowerCase();
				
				if (answer.equals("a") || answer.equals("to manage digital certificates and public-key encryption")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: To manage digital certificates and public-key encryption");
					score = moneyWrong(pos);
				}
			break;
			/************/
		case 33:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
				myJeopardy[pos].setText("<html>Security for $500</html>");
				myJeopardy[pos].setEnabled(false);
				myJeopardy[pos].setBackground(Color.BLACK);
				myJeopardy[pos].setIcon(null);
				
				answer = JOptionPane.showInputDialog("What is the primary difference between symmetric and asymmetric encryption?\r\n" + //
										"\r\n" + //
										"a) Asymmetric encryption can only be used for encrypting data at rest\r\n" + //
										"b) Symmetric encryption is slower than asymmetric encryption\r\n" + //
										"c) Symmetric encryption uses one key for encryption and decryption, while asymmetric encryption uses a pair of keys (public and private)\r\n" + //
										"d) Symmetric encryption provides better security than asymmetric encryption");
				answer = answer.toLowerCase();
				
				if (answer.equals("c") || answer.equals("symmetric encryption uses one key for encryption and decryption, while asymmetric encryption uses a pair of keys (public and private)")){
					JOptionPane.showMessageDialog(null, "CORRECT!");
					score = money(pos);
					System.out.println(score);
				}else{
					JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer:Symmetric encryption uses one key for encryption and decryption, while asymmetric encryption uses a pair of keys (public and private)");
					score = moneyWrong(pos);
				}
			break;
			
			/************************************************************************/

			//Programming
			case 10:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
					myJeopardy[pos].setText("<html>Programming for $100</html>");
					myJeopardy[pos].setEnabled(false);
					myJeopardy[pos].setBackground(Color.BLACK);
					myJeopardy[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("What does \"HTML\" stand for?\r\n" + //
												"\r\n" + //
												"a) HyperText Markup Language\r\n" + //
												"b) HighText Machine Language\r\n" + //
												"c) HyperText Machine Language\r\n" + //
												"d) HighText Markup Language");
					answer = answer.toLowerCase();
					
					if (answer.equals("a") || answer.equals("hypertext markup language")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = money(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: HyperText Markup Language");
						score = moneyWrong(pos);
					}
				break;
				/*************/
			case 16:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
					myJeopardy[pos].setText("<html>Programming for $200</html>");
					myJeopardy[pos].setEnabled(false);
					myJeopardy[pos].setBackground(Color.BLACK);
					myJeopardy[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("Which of the following is a widely used programming language for developing Android apps?\r\n" + //
												"\r\n" + //
												"a) Swift\r\n" + //
												"b) Kotlin\r\n" + //
												"c) C#\r\n" + //
												"d) Ruby");
					answer = answer.toLowerCase();
					
					if (answer.equals("b") || answer.equals("kotlin")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = money(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Kotlin");
						score = moneyWrong(pos);
					}
				break;
				/************/
			case 22:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
					myJeopardy[pos].setText("<html>Programming for $300</html>");
					myJeopardy[pos].setEnabled(false);
					myJeopardy[pos].setBackground(Color.BLACK);
					myJeopardy[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("What is the primary purpose of a loop in programming?\r\n" + //
												"\r\n" + //
												"a) To store data\r\n" + //
												"b) To perform a repetitive task\r\n" + //
												"c) To define variables\r\n" + //
												"d) To compile code");
					answer = answer.toLowerCase();
					
					if (answer.equals("b") || answer.equals("to perform a repetitive task")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = money(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: To perform a repetitive task");
						score = moneyWrong(pos);
					}
				break;
				/************/
			case 28:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
					myJeopardy[pos].setText("<html>Programming for $400</html>");
					myJeopardy[pos].setEnabled(false);
					myJeopardy[pos].setBackground(Color.BLACK);
					myJeopardy[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("Which data structure uses the Last In, First Out (LIFO) principle?\r\n" + //
												"\r\n" + //
												"a) Queue\r\n" + //
												"b) Array\r\n" + //
												"c) Linked List\r\n" + //
												"d) Stack");
					answer = answer.toLowerCase();
					
					if (answer.equals("d") || answer.equals("stack")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = money(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG!\n Correct Answer: Stack");
						score = moneyWrong(pos);
					}
				break;
				/************/
			case 34:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
					myJeopardy[pos].setText("<html>Programming for $500</html>");
					myJeopardy[pos].setEnabled(false);
					myJeopardy[pos].setBackground(Color.BLACK);
					myJeopardy[pos].setIcon(null);
					
					answer = JOptionPane.showInputDialog("What is the time complexity of the QuickSort algorithm in the average case?\r\n" + //
												"\r\n" + //
												"a) O(n)\r\n" + //
												"b) O(n log n)\r\n" + //
												"c) O(n^2)\r\n" + //
												"d) O(log n)");
					answer = answer.toLowerCase();
					
					if (answer.equals("b") || answer.equals("O(n log n)")){
						JOptionPane.showMessageDialog(null, "CORRECT!");
						score = money(pos);
						System.out.println(score);
					}else{
						JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: O(n log n)");
						score = moneyWrong(pos);
					}
				break;

			/************************************************************************/

			//computer
				case 11:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
						myJeopardy[pos].setText("<html>computer for $100</html>");
						myJeopardy[pos].setEnabled(false);
						myJeopardy[pos].setBackground(Color.BLACK);
						myJeopardy[pos].setIcon(null);
						
						answer = JOptionPane.showInputDialog("What does \"RAM\" stand for in computer terminology?\r\n" + //
														"\r\n" + //
														"a) Random Access Memory\r\n" + //
														"b) Readily Available Memory\r\n" + //
														"c) Random Allocation Memory\r\n" + //
														"d) Read-Only Memory");
						answer = answer.toLowerCase();
						
						if (answer.equals("a") || answer.equals("random access memory")){
							JOptionPane.showMessageDialog(null, "CORRECT!");
							score = money(pos);
							System.out.println(score);
						}else{
							JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Random Access Memory");
							score = moneyWrong(pos);
						}
					break;
					/*************/
				case 17:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
						myJeopardy[pos].setText("<html>computer for $200</html>");
						myJeopardy[pos].setEnabled(false);
						myJeopardy[pos].setBackground(Color.BLACK);
						myJeopardy[pos].setIcon(null);
						
						answer = JOptionPane.showInputDialog("Which company developed the Windows operating system?\r\n" + //
														"\r\n" + //
														"a) Apple\r\n" + //
														"b) IBM\r\n" + //
														"c) Microsoft\r\n" + //
														"d) Google");
						answer = answer.toLowerCase();
						
						if (answer.equals("c") || answer.equals("microsoft")){
							JOptionPane.showMessageDialog(null, "CORRECT!");
							score = money(pos);
							System.out.println(score);
						}else{
							JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Microsoft");
							score = moneyWrong(pos);
						}
					break;
					/************/
				case 23:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
						myJeopardy[pos].setText("<html>computer for $300</html>");
						myJeopardy[pos].setEnabled(false);
						myJeopardy[pos].setBackground(Color.BLACK);
						myJeopardy[pos].setIcon(null);
						
						answer = JOptionPane.showInputDialog("What is the primary function of an operating system?\r\n" + //
														"\r\n" + //
														"a) To perform calculations\r\n" + //
														"b) To manage computer hardware and software resources\r\n" + //
														"c) To provide internet connectivity\r\n" + //
														"d) To edit documents");
						answer = answer.toLowerCase();
						
						if (answer.equals("b") || answer.equals("to manage computer hardware and software resources")){
							JOptionPane.showMessageDialog(null, "CORRECT!");
							score = money(pos);
							System.out.println(score);
						}else{
							JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: To manage computer hardware and software resources");
							score = moneyWrong(pos);
						}
					break;
					/************/
				case 29:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
						myJeopardy[pos].setText("<html>computer for $400</html>");
						myJeopardy[pos].setEnabled(false);
						myJeopardy[pos].setBackground(Color.BLACK);
						myJeopardy[pos].setIcon(null);
						
						answer = JOptionPane.showInputDialog("What does the term \"GUI\" stand for in computing?\r\n" + //
														"\r\n" + //
														"a) General User Interface\r\n" + //
														"b) General Unified Interface\r\n" + //
														"c) Graphical Unified Interface\r\n" + //
														"d) graphical user interface");
						answer = answer.toLowerCase();
						
						if (answer.equals("d") || answer.equals("graphical user interface")){
							JOptionPane.showMessageDialog(null, "CORRECT!");
							score = money(pos);
							System.out.println(score);
						}else{
							JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Graphical User Interface");
							score = moneyWrong(pos);
						}
					break;
					/************/
				case 35:myJeopardy[pos].setFont(new Font("Arial", Font.BOLD,11));
						myJeopardy[pos].setText("<html>computer for $500</html>");
						myJeopardy[pos].setEnabled(false);
						myJeopardy[pos].setBackground(Color.BLACK);
						myJeopardy[pos].setIcon(null);
						
						answer = JOptionPane.showInputDialog(" What is the primary advantage of using a solid-state drive (SSD) over a traditional hard disk drive (HDD)?\r\n" + //
														"\r\n" + //
														"a) Lower cost\r\n" + //
														"b) Higher storage capacity\r\n" + //
														"c) Faster data access speeds\r\n" + //
														"d) Better compatibility with older systems");
						answer = answer.toLowerCase();
						
						if (answer.equals("c") || answer.equals("faster data access speeds")){
							JOptionPane.showMessageDialog(null, "CORRECT!");
							score = money(pos);
							System.out.println(score);
						}else{
							JOptionPane.showMessageDialog(null, "WRONG! \n Correct Answer: Faster data access speeds");
							score = moneyWrong(pos);
						}
					break;
					/************/
		}//End switch
		return score;
	}

	/************************************************************************/
	
	public int money(int pos){
		//Method to set what each button is worth score wise

		if (pos == 6 || pos == 7 || pos == 8 || pos == 9 || pos == 10 || pos == 11){
			score = 100;
		}
		else if (pos == 12 || pos == 13 || pos == 14 || pos == 15 || pos == 16 || pos == 17){
			score = 200;
		}
		else if (pos == 18 || pos == 19 || pos == 20 || pos == 21 || pos == 22 || pos == 23){
			score = 300;
		}
		else if (pos == 24 || pos == 25 || pos == 26 || pos == 27 || pos == 28 || pos == 29){
			score = 400;
		}
		else if (pos == 30 || pos == 31 || pos == 32 || pos == 33 || pos == 34 || pos == 35){
			score = 500;
		}
		return score;
	}//End money
	
	/************************************************************************/

	public int moneyWrong(int pos){
		//Method to display a score of ) if answer is incorrect
		if (pos == 6 || pos == 7 || pos == 8 || pos == 9 || pos == 10 || pos == 11 || 
			pos == 12 || pos == 13 || pos == 14 || pos == 15 || pos == 16 || pos == 17 ||
			pos == 18 || pos == 19 || pos == 20 || pos == 21 || pos == 22 || pos == 23 ||
			pos == 24 || pos == 25 || pos == 26 || pos == 27 || pos == 28 || pos == 29 ||
			pos == 30 || pos == 31 || pos == 32 || pos == 33 || pos == 34 || pos == 35 ){
			score = 0;
		}//End if
		return score;
	}//End money
	
	/************************************************************************/

	public void categories(int i){
		if(i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5){
			myJeopardy[i].setBackground(Color.BLACK);
		}else if(i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11){
			myJeopardy[i].setBackground(Color.BLACK);
		}else if(i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17){
				myJeopardy[i].setBackground(Color.BLACK);
		}else if(i == 18 || i == 19 || i == 20 || i == 21 || i == 22 || i == 23){
				myJeopardy[i].setBackground(Color.BLACK);
		}else if(i == 24 || i == 25 || i == 26 || i == 27 || i == 28 || i == 29){
			myJeopardy[i].setBackground(Color.BLACK);
		}else if(i == 30 || i == 31 || i == 32 || i == 33 || i == 34 || i == 35){
			myJeopardy[i].setBackground(Color.BLACK);
		}//End if
	}//End categories

/*******************************************************************/
public void categoryPics(int i){
		switch (i){
		case 0: 
			ImageIcon im = new ImageIcon(new ImageIcon("src/gambar/ce.jpg").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(im);
		break;
		case 1: 
			ImageIcon ima = new ImageIcon(new ImageIcon("src/gambar/cn.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(ima);
		break;
		case 2: 
			ImageIcon imag = new ImageIcon(new ImageIcon("src/gambar/mm.jpg").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(imag);
		break;
		case 3: 
			ImageIcon image = new ImageIcon(new ImageIcon("src/gambar/cs.jpg").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image);
		break;
		case 4: 
			ImageIcon image1 = new ImageIcon(new ImageIcon("src/gambar/cp.jpg").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image1);
		break;
		case 5: 
			ImageIcon image2 = new ImageIcon(new ImageIcon("src/gambar/com.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image2);
		break;
		/**********************/

		case 6: 
			ImageIcon image3 = new ImageIcon(new ImageIcon("src/gambar/100.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image3);
		break;
		case 12: 
			ImageIcon image4 = new ImageIcon(new ImageIcon("src/gambar/200.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image4);
		break;
		case 18: 
			ImageIcon image5 = new ImageIcon(new ImageIcon("src/gambar/300.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image5);
		break;
		case 24: 
			ImageIcon image6 = new ImageIcon(new ImageIcon("src/gambar/400.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image6);
		break;
		case 30: 
			ImageIcon image7 = new ImageIcon(new ImageIcon("src/gambar/500.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image7);
		break;
		
		
/************************/

		case 7: 
			ImageIcon image9 = new ImageIcon(new ImageIcon("src/gambar/100.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image9);
		break;
		case 13: 
			ImageIcon image10 = new ImageIcon(new ImageIcon("src/gambar/200.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image10);
		break;
		case 19: 
			ImageIcon image11 = new ImageIcon(new ImageIcon("src/gambar/300.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image11);
		break;
		case 25: 
			ImageIcon image12 = new ImageIcon(new ImageIcon("src/gambar/400.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image12);
		break;
		case 31: 
			ImageIcon image13 = new ImageIcon(new ImageIcon("src/gambar/500.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image13);
		break;
		
/**********************/
		
		case 8: 
			ImageIcon image15 = new ImageIcon(new ImageIcon("src/gambar/100.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image15);
		break;
		case 14: 
			ImageIcon image16 = new ImageIcon(new ImageIcon("src/gambar/200.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image16);
		break;
		case 20: 
			ImageIcon image17 = new ImageIcon(new ImageIcon("src/gambar/300.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image17);
		break;
		case 26: 
			ImageIcon image18 = new ImageIcon(new ImageIcon("src/gambar/400.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image18);
		break;
		case 32: 
			ImageIcon image19 = new ImageIcon(new ImageIcon("src/gambar/500.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image19);
		break;
		
/************************/
		
		case 9: 
			ImageIcon image21 = new ImageIcon(new ImageIcon("src/gambar/100.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image21);
		break;
		case 15: 
			ImageIcon image22 = new ImageIcon(new ImageIcon("src/gambar/200.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image22);
		break;
		case 21: 
			ImageIcon image23 = new ImageIcon(new ImageIcon("src/gambar/300.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image23);
		break;
		case 27: 
			ImageIcon image24 = new ImageIcon(new ImageIcon("src/gambar/400.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image24);
		break;
		case 33: 
			ImageIcon image25 = new ImageIcon(new ImageIcon("src/gambar/500.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image25);
		break;
		
/***************************/
		
		case 10: 
			ImageIcon image27 = new ImageIcon(new ImageIcon("src/gambar/100.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image27);
		break;
		case 16: 
			ImageIcon image28 = new ImageIcon(new ImageIcon("src/gambar/200.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image28);
		break;
		case 22: 
			ImageIcon image29 = new ImageIcon(new ImageIcon("src/gambar/300.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image29);
		break;
		case 28: 
			ImageIcon image30 = new ImageIcon(new ImageIcon("src/gambar/400.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image30);
		break;
		case 34: 
			ImageIcon image31 = new ImageIcon(new ImageIcon("src/gambar/500.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image31);
		break;
		
/***************************/
		
		case 11: 
			ImageIcon image33 = new ImageIcon(new ImageIcon("src/gambar/100.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image33);
		break;
		case 17: 
			ImageIcon image34 = new ImageIcon(new ImageIcon("src/gambar/200.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image34);
		break;
		case 23: 
			ImageIcon image35 = new ImageIcon(new ImageIcon("src/gambar/300.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image35);
		break;
		case 29: 
			ImageIcon image36 = new ImageIcon(new ImageIcon("src/gambar/400.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image36);
		break;
		case 35: 
			ImageIcon image37 = new ImageIcon(new ImageIcon("src/gambar/500.png").getImage().getScaledInstance(
					180,100, Image.SCALE_DEFAULT));
			myJeopardy[i].setIcon(image37);
		break;
		}//End switch
	}//End categoryPics

/*******************************************************************/

	public void resetBoard(){
		for (int i = 0; i < myJeopardy.length; i++){
			myJeopardy[i].setEnabled(true);
			myJeopardy[i].setText("" + i);
			categories(i);
			categoryPics(i);
			myJeopardy[i].setForeground(Color.BLACK);
			p1Score = 0;
			p2Score = 0;
			player1Wins.setText(player1 + "'s Score: " + p1Score);
			player2Wins.setText(player2 + "'s Score: " + p2Score);
		}
	}//End resetBoard

/*******************************************************************/

	public void disableButtons(){
		for (int i = 0; i < myJeopardy.length; i++){
			myJeopardy[i].setEnabled(false);
			myJeopardy[i].setText("" + i);
			categories(i);
			categoryPics(i);
			myJeopardy[i].setForeground(Color.BLACK);
		}//End for
	}//End disableBoard	
}//End Jeopardy
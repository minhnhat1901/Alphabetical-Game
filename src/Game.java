import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.util.HashSet;
import java.util.Set;

import java.util.Random;
import java.util.Scanner;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Frame;
import javax.swing.UIManager;

public class Game extends JFrame implements ActionListener
{
	//Global variables
	Clip clipSoundC;
	Clip clipSoundI;
	Clip gameSound;
	Frame frame;

	Random r = new Random();	//Used to generate random order of questions asked
	
	//String array that holds all the questions
	String[] questions = 
		{
				"What is the first letter of this fruit?",
				"When we greet people, we say _ello!",
				"What letter does this animal end with? ",
				"What is the first letter of this plant?",
				"You can sing a _ong.",
				"What is the first letter of this animal?",
				"When you're tired, you take a  _ap.",
				"What is the first letter of this color?",
				"What letter does this vegetable end with?",
				"You can use a _encil to write things"
		};
	
	Set<Integer> visited = new HashSet<>();	//Create set to hold unique values of visited questions
	
	int total_questions = questions.length;	//Holds length of array
	int index;
	
	//More global variables 
	JTextArea textarea;
	JLabel pic;
	JLabel liveScore;
	
	JButton choice1;
	JButton choice2;
	JButton choice3;
	JButton choice4;
	
	static int point = 0;	//counter to hold points scored
	
	//2D array of strings to hold answer choices
	String choices [][] = 
		{
				{"A", "B", "F", "O"},
				{"X", "A", "H", "J"},
				{"B", "O", "W", "T"},
				{"H", "S", "X", "U"},
				{"S", "Q", "R", "P"},
				{"I", "D", "G", "S"},
				{"O", "U", "N", "B"},
				{"R", "P", "B", "V"},
				{"Y", "G", "Z", "I"},
				{"C", "P", "L", "S"}
		};
	
	char answer; //holds user's answer
	
	//Character array to hold the answers to the questions
	char[] answers = 
	{
			'A','C','D','B','A','B','C',
			'A','D','B'
	};
	
	//ImageIcon array to hold images associated with each question
	ImageIcon[] image = 
	{
			new ImageIcon(this.getClass().getResource("/Apple.jpg")),
			new ImageIcon(this.getClass().getResource("/Hello.jpg")),
			new ImageIcon(this.getClass().getResource("/Cat.jpg")),
			new ImageIcon(this.getClass().getResource("/Sunflower.png")),
			new ImageIcon(this.getClass().getResource("/Song.jpg")),
			new ImageIcon(this.getClass().getResource("/Dog.jpg")),
			new ImageIcon(this.getClass().getResource("/Nap.jpg")),
			new ImageIcon(this.getClass().getResource("/Red.png")),
			new ImageIcon(this.getClass().getResource("/Broccoli.jpg")),
			new ImageIcon(this.getClass().getResource("/Pencil.jpg"))
	};
	
	//More global variables with some private variables created from WindowBuilder
	JLabel name1;
	JLabel name2;
	JLabel thanks;
	static String playerName;
	private JPanel contentPane;
	private Panel panel_1;
	private JButton LEADER_BOARD;
	private Scanner IntialScoreReader;
	String player1;
	int score1;
	String player2;
	int score2;
	String player3;
	int score3;
	String player4;
	int score4;
	String player5;
	int score5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Game frame = new Game();	//Create Game frame
					//Set relative location and visibility of the frame
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} 
				
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() 
	{
		//Add styles to frame
		UIManager.put("OptionPane.minimumSize", new Dimension(150,200));
		UIManager.put
		("OptionPane.background", new Color(224, 224, 224));
		UIManager.put("Panel.background", new Color(224, 224, 224));
		Font font = new Font("Comic Sans MS", Font.BOLD, 30);
		UIManager.put("TextField.font", font);
		
		//Create label with styles for window that asks for user's name
		name1 = new JLabel("Please enter your first name (No spaces).");
		name1.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		
		//Use JOptionPane to get the user's name
		playerName = JOptionPane.showInputDialog(frame, name1);
		
		//Validate the name that the name the user inputted is an acutal name
		if (playerName == null)
		{
			UIManager.put("OptionPane.minimumSize",
					new Dimension(450,100));
			UIManager.put
			("OptionPane.background", new Color(224, 224, 224));
			UIManager.put("Panel.background", 
					new Color(224, 224, 224));
			thanks = new JLabel("Thanks for playing!");
			thanks.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
			JOptionPane.showMessageDialog(null, thanks);
			System.exit(0);
		}
		
		while(playerName.length() == 0 || playerName.contains(" "))
        {
            UIManager.put("OptionPane.minimumSize",new Dimension(700,100));
            UIManager.put
            ("OptionPane.background", new Color(224, 224, 224));
            UIManager.put("Panel.background", new Color(224, 224, 224));
            String message = (playerName.length() == 0) ? "Blank! Please enter your first name!" : "No spaces please!";
            name2 = new JLabel(message);
            name2.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
            
            JOptionPane.showMessageDialog (frame, name2);
            playerName = JOptionPane.showInputDialog(frame, name1);
            
            // if user wants to click "X"
            if(playerName == null)
            {
                // thank you message
                UIManager.put("OptionPane.minimumSize",
                        new Dimension(450,100));
                UIManager.put
                ("OptionPane.background", new Color(224, 224, 224));
                UIManager.put("Panel.background", new Color(224, 224, 224));
                thanks = new JLabel("Thanks for playing!");
                thanks.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
                JOptionPane.showMessageDialog(null, thanks);
                 // Exit JOption
                 System.exit(0);
            }
        }
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		//Create side panel
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Add bell image (Christmas Theme)
		ImageIcon imageBell = new ImageIcon
				(this.getClass().getResource("/bells1.png"));
		
		//Create multiple choice buttons for the user to choose from
		choice1 = new JButton();
		choice1.setBackground(new Color(255, 255, 153));
		choice1.setBounds(323, 576, 121, 118);
		choice1.addActionListener(this);
		contentPane.add(choice1);
		
		choice2 = new JButton();
		choice2.setBackground(new Color(255, 255, 153));
		choice2.setBounds(470, 576, 121, 118);
		choice2.addActionListener(this);
		contentPane.add(choice2);
		
		choice3 = new JButton();
		choice3.setBackground(new Color(255, 255, 153));
		choice3.setBounds(618, 576, 121, 118);
		choice3.addActionListener(this);
		contentPane.add(choice3);
		
		choice4 = new JButton();
		choice4.setBackground(new Color(255, 255, 153));
		choice4.setBounds(772, 576, 121, 118);
		choice4.addActionListener(this);
		contentPane.add(choice4);
		
		//Create new panel
		panel_1 = new Panel();
		panel_1.setBackground(new Color(204, 255, 204));
		panel_1.setBounds(317, 46, 611, 465);
		
		//Create new text area
		textarea = new JTextArea();
		textarea.setBackground(new Color(204, 204, 255));
		textarea.setLineWrap(true);
		textarea.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		textarea.setBounds(0,0,611,59);
		textarea.setEditable(false);
		
		//Create new label to hold the question's corresponding image
		pic = new JLabel("");
		pic.setHorizontalAlignment(SwingConstants.CENTER);
		pic.setBounds(136,100,347,299);
		
		//Start by setting point counter to 0
		point = 0;
		//Get question
		nextQuestion();
		
		//Add items to panel
		panel_1.setLayout(null);
		panel_1.add(textarea);
		panel_1.add(pic);
		
		//Add panel to frame
		contentPane.add(panel_1);
		
		//Have a label to keep live score and add styles
		liveScore = new JLabel("Score: " + point);
		liveScore.setHorizontalAlignment(SwingConstants.CENTER);
		liveScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		liveScore.setBounds(136, 410, 347, 44);
		panel_1.add(liveScore);
		
		//Set background color
		Panel panel = new Panel();
		panel.setBounds(0, 0, 267, 765);
		contentPane.add(panel);
		panel.setBackground(new Color(255, 153, 153));
		panel.setLayout(null);
		
		//Create exit button
		JButton EXIT2_Button = new JButton("EXIT");
		EXIT2_Button.setBackground(new Color(255, 255, 153));
		EXIT2_Button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				stopGameSound();
				dispose(); // throw away this page
				UIManager.put("OptionPane.minimumSize",
						new Dimension(450,100));
				UIManager.put
				("OptionPane.background", new Color(224, 224, 224));
				UIManager.put("Panel.background", new Color(224, 224, 224));
				thanks = new JLabel("Thanks for playing!");
				thanks.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
				JOptionPane.showMessageDialog(null, thanks);
				System.exit(0);
			}
		});
		//Add exit button styles
		EXIT2_Button.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		EXIT2_Button.setBounds(37, 399, 201, 85);
		panel.add(EXIT2_Button);
		
		//Create How To Play button that will link to the How to Play page
		JButton HTP2_Button = new JButton("How To Play");
		HTP2_Button.setFocusPainted(false);
		HTP2_Button.setBackground(new Color(255, 255, 153));
		HTP2_Button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				UIManager.put
				("OptionPane.minimumSize",new Dimension(780,300));
				UIManager.put
				("OptionPane.background", new Color(204, 204, 255));
				UIManager.put
				("Panel.background",new Color(204, 204, 255));
				JTextArea msg = new JTextArea();
				msg.setBackground(new Color(204, 204, 255));
				msg.setText
				("   You will be asked an alphabet question. \r\n"
				+ "   4 choices will be displayed on the screen.\r\n"
				+ "   Click on the choice you think is correct.");
				msg.setEditable(false);
				msg.setFont(new Font("Comic Sans MS", Font.PLAIN, 34));
				JOptionPane.showMessageDialog(frame, msg, "How to Play",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		//Add button styles
		HTP2_Button.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		HTP2_Button.setBounds(37, 159, 201, 85);
		panel.add(HTP2_Button);
		
		//Add bell image for Christmas theme
		JLabel bell_pic = new JLabel("New label");
		bell_pic.setBounds(37, 521, 200, 200);
		bell_pic.setIcon(imageBell);
		panel.add(bell_pic);
		
		//Add green side panel
		JPanel Green_Panel = new JPanel();
		Green_Panel.setBackground(new Color(51, 153, 51));
		Green_Panel.setBounds(0, 0, 267, 106);
		panel.add(Green_Panel);
		Green_Panel.setLayout(null);
		
		//Have the name of the game shown in a label
		JLabel Alphabet_Learner = new JLabel("Alphabet Learner");
		Alphabet_Learner.setForeground(Color.ORANGE);
		Alphabet_Learner.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		Alphabet_Learner.setHorizontalAlignment(SwingConstants.CENTER);
		Alphabet_Learner.setBounds(0, 32, 267, 46);
		Green_Panel.add(Alphabet_Learner);
		
		/*
		 * Create leaderboard button. THis button will take users who hold
		 * a top 5 score and add their names and scores to the leaderboard.
		 * There are already 5 players who have "played" the game by default
		 * and their scores have been recorded to the leaderboard. Any new
		 * scores that out-score the current leaders will be added to the
		 * leaderboard.
		 */
		LEADER_BOARD = new JButton("Leaderboard");
		LEADER_BOARD.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		LEADER_BOARD.setFocusPainted(false);
		LEADER_BOARD.setBackground(new Color(255, 255, 153));
		LEADER_BOARD.setBounds(37, 274, 201, 85);
		panel.add(LEADER_BOARD);
		
		LEADER_BOARD.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					IntialScoreReader = new Scanner
							(new File("Scores/scores.txt"));
					int rank = 1;
					while (IntialScoreReader.hasNextLine()) 
					{
						if (rank>5)
						{
							break;
						}
						String info = IntialScoreReader.nextLine();
						String player = info.split(" ")[0];
						int score = Integer.parseInt(info.split(" ")[1]);
						if (rank==1) 
						{
							player1 = player;
							score1 = score;
						} 
						
						else if (rank==2) 
						{
							player2 = player;
							score2 = score;
						} 
						
						else if (rank==3) 
						{
							player3 = player;
							score3 = score;
						} 
						
						else if (rank==4) 
						{
							player4 = player;
							score4 = score;
						} 
						
						else if (rank==5) 
						{
							player5 = player;
							score5 = score;
						}
						
						rank++;
					}
				} 
				
				catch (FileNotFoundException exc) 
				{
					exc.printStackTrace();
				}
				
				UIManager.put
				("OptionPane.minimumSize",new Dimension(300,100));
				UIManager.put
				("OptionPane.background", new Color(204, 204, 255));
				UIManager.put
				("Panel.background",new Color(204, 204, 255));
				JTextArea ScoreBoard = new JTextArea();
				ScoreBoard.setBackground(new Color(204, 204, 255));
				ScoreBoard.setText
				(" Leaderboard \r\n"
				+ player1 + " : " +  score1 + "\r\n"
				+ player2 + " : " +  score2 + "\r\n"
				+ player3 + " : " +  score3 + "\r\n"
				+ player4 + " : " +  score4 + "\r\n"
				+ player5 + " : " +  score5);
				ScoreBoard.setEditable(false);
				ScoreBoard.setFont(
						new Font("Comic Sans MS", Font.PLAIN, 30));
				JOptionPane.showMessageDialog
				(frame, ScoreBoard, "Leaderboard",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//Add game sound
		try
		{
			gameSound = AudioSystem.getClip();
			AudioInputStream input = 
					AudioSystem.getAudioInputStream
					(new File("Music/Jingle_Bells.wav"));
			gameSound.open(input);
			gameSound.loop(Clip.LOOP_CONTINUOUSLY);
			gameSound.start();
		}
		
		catch (UnsupportedAudioFileException e) 
		{
            e.printStackTrace();
        } 
		
		catch (IOException e) 
		{
            e.printStackTrace();
        } 
		
		catch (LineUnavailableException e) 
		{
            e.printStackTrace();
        }
	}
	
	//Method that will get a new question to be displayed to the game
	public void nextQuestion()
	{
		if(visited.size() >= total_questions)
		{
			stopGameSound();
			dispose();
			Leaderboard lb = new Leaderboard();
			lb.setLocationRelativeTo(null);
			lb.setVisible(true);
		}
		
		else 
		{
			index = r.nextInt(questions.length);

			while (visited.contains(index)) 
			{
				index = r.nextInt(questions.length);
			}
			
			visited.add(index);
			
			textarea.setText(questions[index]);
			pic.setIcon(image[index]);
			
			choice1.setText(choices[index][0]);
			choice1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

			choice2.setText(choices[index][1]);
			choice2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

			choice3.setText(choices[index][2]);
			choice3.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

			choice4.setText(choices[index][3]);
			choice4.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		}
	}
	
	//Method that will handle any performed action from the user (comparing their answers to the answer key)
	public void actionPerformed(ActionEvent e)
	{
		//Add styles
		UIManager.put("OptionPane.minimumSize",new Dimension(400,100));
		UIManager.put("OptionPane.background", new Color(229,204,255));
		UIManager.put("Panel.background", new Color(229,204,255));
		//Code repeated for all 4 answers
		if(e.getSource()== choice1) 
		{
			answer = 'A';
			if(answer == answers[index]) 
			{
				try
				{
					clipSoundC = AudioSystem.getClip();
					AudioInputStream input = 
							AudioSystem.getAudioInputStream
							(new File("Music/CorrectFINAL.wav"));	//Play correct sound
					clipSoundC.open(input);
					clipSoundC.start();
					
					//Create pop up that will alert the user that they have answered the question correctly
					JLabel correct = new JLabel("    Correct!");
					correct.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
					correct.setForeground(Color.BLUE);
					JOptionPane.showMessageDialog(frame, correct);
					stopMusicC();
				}
				
				catch (UnsupportedAudioFileException err) 
				{
		            err.printStackTrace();
		        } 
				
				catch (IOException ex) 
				{
		            ex.printStackTrace();
		        } 
				
				catch (LineUnavailableException er) 
				{
		            er.printStackTrace();
		        }
				
				point++;	//Increment point if the user answers correctly
				liveScore.setText("Score: " + point);	//Update live counter
				nextQuestion();
			}
			
			else
			{
				try
				{
					clipSoundI = AudioSystem.getClip();
					AudioInputStream input = 
							AudioSystem.getAudioInputStream
							(new File("Music/IncorrectFINAL.wav"));	//Play incorrect sound
					clipSoundI.open(input);
					clipSoundI.start();
					
					//Create pop up that will alert the user that they have answered the question incorrectly
					JLabel incorrect = new JLabel("    Incorrect!");
					incorrect.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
					incorrect.setForeground(Color.RED);
					JOptionPane.showMessageDialog(frame, incorrect);
					
					stopMusicI();
				}
				
				catch (UnsupportedAudioFileException err) 
				{
		            err.printStackTrace();
		        } 
				
				catch (IOException ex) 
				{
		            ex.printStackTrace();
		        } 
				
				catch (LineUnavailableException er) 
				{
		            er.printStackTrace();
		        }
				
				nextQuestion();
			}
		}
		
		if(e.getSource()== choice2) 
		{
			answer = 'B';
			if(answer == answers[index]) 
			{
				try
				{
					clipSoundC = AudioSystem.getClip();
					AudioInputStream input = 
							AudioSystem.getAudioInputStream
							(new File("Music/CorrectFINAL.wav"));	//Play correct sound
					clipSoundC.open(input);
					clipSoundC.start();
					
					//Create pop up that will alert the user that they have answered the question correctly
					JLabel correct = new JLabel("    Correct!");
					correct.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
					correct.setForeground(Color.BLUE);
					JOptionPane.showMessageDialog(frame, correct);
					stopMusicC();
					
				}
				
				catch (UnsupportedAudioFileException err) 
				{
		            err.printStackTrace();
		        } 
				
				catch (IOException ex) 
				{
		            ex.printStackTrace();
		        } 
				
				catch (LineUnavailableException er) 
				{
		            er.printStackTrace();
		        }
				
				point++;	//Increment point if the user answers correctly
				liveScore.setText("Score: " + point);	//Update live counter
				nextQuestion();
			}
			
			else
			{
				try
				{
					clipSoundI = AudioSystem.getClip();
					AudioInputStream input = 
							AudioSystem.getAudioInputStream
							(new File("Music/IncorrectFINAL.wav"));	//Play incorrect sound
					clipSoundI.open(input);
					clipSoundI.start();
					
					//Create pop up that will alert the user that they have answered the question incorrectly
					JLabel incorrect = new JLabel("    Incorrect!");
					incorrect.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
					incorrect.setForeground(Color.RED);
					JOptionPane.showMessageDialog(frame, incorrect);
					
					stopMusicI();
				}
				
				catch (UnsupportedAudioFileException err) 
				{
		            err.printStackTrace();
		        } 
				
				catch (IOException ex) 
				{
		            ex.printStackTrace();
		        } 
				
				catch (LineUnavailableException er) 
				{
		            er.printStackTrace();
		        }
				
				nextQuestion();
			}
		}
		
		if(e.getSource()== choice3) 
		{
			answer = 'C';
			if(answer == answers[index]) 
			{
				try
				{
					clipSoundC = AudioSystem.getClip();
					AudioInputStream input = 
							AudioSystem.getAudioInputStream
							(new File("Music/CorrectFINAL.wav"));	//Play correct sound
					clipSoundC.open(input);
					clipSoundC.start();
					
					//Create pop up that will alert the user that they have answered the question correctly
					JLabel correct = new JLabel("    Correct!");
					correct.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
					correct.setForeground(Color.BLUE);
					JOptionPane.showMessageDialog(frame, correct);
					
					stopMusicC();
				}
				
				catch (UnsupportedAudioFileException err) 
				{
		            err.printStackTrace();
		        } 
				
				catch (IOException ex) 
				{
		            ex.printStackTrace();
		        } 
				
				catch (LineUnavailableException er) 
				{
		            er.printStackTrace();
		        }
				
				point++;	//Increment point if the user answers correctly
				liveScore.setText("Score: " + point);	//Update live counter
				nextQuestion();
			}
			
			else
			{
				try
				{
					clipSoundI = AudioSystem.getClip();
					AudioInputStream input = 
							AudioSystem.getAudioInputStream
							(new File("Music/IncorrectFINAL.wav"));	//Play incorrect sound
					clipSoundI.open(input);
					clipSoundI.start();
					
					//Create pop up that will alert the user that they have answered the question incorrectly
					JLabel incorrect = new JLabel("    Incorrect!");
					incorrect.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
					incorrect.setForeground(Color.RED);
					JOptionPane.showMessageDialog(frame, incorrect);
					
					stopMusicI();
				}
				
				catch (UnsupportedAudioFileException err) 
				{
		            err.printStackTrace();
		        } 
				
				catch (IOException ex) 
				{
		            ex.printStackTrace();
		        } 
				
				catch (LineUnavailableException er) 
				{
		            er.printStackTrace();
		        }
				
				nextQuestion();
			}
		}
		
		if(e.getSource()== choice4) 
		{
			answer = 'D';
			if(answer == answers[index]) 
			{
				try
				{
					clipSoundC = AudioSystem.getClip();
					AudioInputStream input = 
							AudioSystem.getAudioInputStream
							(new File("Music/CorrectFINAL.wav"));	//Play correct sound
					clipSoundC.open(input);
					clipSoundC.start();
					
					//Create pop up that will alert the user that they have answered the question correctly
					JLabel correct = new JLabel("    Correct!");
					correct.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
					correct.setForeground(Color.BLUE);
					JOptionPane.showMessageDialog(frame, correct);
					
					stopMusicC();
				}
				
				catch (UnsupportedAudioFileException err) 
				{
		            err.printStackTrace();
		        } 
				
				catch (IOException ex) 
				{
		            ex.printStackTrace();
		        } 
				
				catch (LineUnavailableException er) 
				{
		            er.printStackTrace();
		        }
				
				point++;	//Increment point if the user answers correctly
				liveScore.setText("Score: " + point);	//Update live counter
				nextQuestion();
			}
			
			else
			{
				try
				{
					clipSoundI = AudioSystem.getClip();
					AudioInputStream input = 
							AudioSystem.getAudioInputStream
							(new File("Music/IncorrectFINAL.wav"));	//Play incorrect sound
					clipSoundI.open(input);
					clipSoundI.start();
					
					//Create pop up that will alert the user that they have answered the question incorrectly
					JLabel incorrect = new JLabel("    Incorrect!");
					incorrect.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
					incorrect.setForeground(Color.RED);
					JOptionPane.showMessageDialog(frame, incorrect);
					
					stopMusicI();
				}
				
				catch (UnsupportedAudioFileException err) 
				{
		            err.printStackTrace();
		        } 
				
				catch (IOException ex) 
				{
		            ex.printStackTrace();
		        } 
				
				catch (LineUnavailableException er) 
				{
		            er.printStackTrace();
		        }
				
				nextQuestion();
			}
		}
	}
	
	//Method to stop music track C
	public void stopMusicC()
	{
		clipSoundC.stop();
	}
	
	//Method to stop music track I
	public void stopMusicI()
	{
		clipSoundI.stop();
	}
	
	//Method to stop game sound
	public void stopGameSound()
	{
		gameSound.stop();
	}
}

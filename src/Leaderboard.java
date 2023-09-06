import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Leaderboard extends JFrame 
{
	private JPanel contentPane;
	private Scanner scoresReader;
	private Scanner top5Reader;
	
	int[] PlayerScore = new int[6];
	int temp_PS;
	int temp1_PS;
	int temp2_PS;
	int temp3_PS;
	int temp4_PS;
	String[] PlayerName = new String[6];	//To hold player names in leaderboard
	String temp_PN;
	String temp1_PN;
	String temp2_PN;
	String temp3_PN;
	String temp4_PN;
	
	Clip clipLb;	//For music
	JLabel thankYou;
	JLabel Sorry;

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
					//Create leaderboard frame
					Leaderboard frame = new Leaderboard();
					//Set the relative location which displays in the center of 
					//the screen and visibility of the frame
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
	public Leaderboard() 
	{
		//Set the title of the frame and add styles
		setTitle("Leaderboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Create a label that will hold an entry for the leaderboard and add styles
		JLabel Score = new JLabel();
		Score.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		Score.setBounds(323, 134, 245, 85);
		Score.setText("Your score: " + Game.point);
		contentPane.add(Score);
		
		//Crate a label that will hold the "Leaderboard" title and add styles
		JLabel Leaderboard = new JLabel("Leaderboard");
		Leaderboard.setHorizontalAlignment(SwingConstants.CENTER);
		Leaderboard.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
		Leaderboard.setBounds(267, 205, 355, 73);
		contentPane.add(Leaderboard);
		
		//Create empty labels that will hold as placeholders for the top 5 scorers
		JLabel Score1 = new JLabel("");
		Score1.setBounds(123, 289, 355, 56);
		contentPane.add(Score1);
		
		JLabel Score2 = new JLabel("");
		Score2.setBounds(123, 354, 355, 56);
		contentPane.add(Score2);
		
		JLabel Score3 = new JLabel("");
		Score3.setBounds(123, 419, 355, 56);
		contentPane.add(Score3);
		
		JLabel Score4 = new JLabel("");
		Score4.setBounds(123, 486, 355, 56);
		contentPane.add(Score4);
		
		JLabel Score5 = new JLabel("");
		Score5.setBounds(123, 553, 355, 56);
		contentPane.add(Score5);
		
		//Create a new panel, set its background color, and add additional styles
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 51));
		panel.setBounds(0, 0, 986, 96);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//Use the panel from above to add a label with the name of the game
		JLabel Header = new JLabel("AlphabetLearner");
		Header.setForeground(Color.ORANGE);
		Header.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		Header.setBounds(46, 23, 253, 46);
		panel.add(Header);
		
		//Create a new panel and add styles
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 0, 51));
		panel_1.setBounds(0, 96, 43, 667);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//Create a new panel and add styles
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 0, 51));
		panel_2.setBounds(40, 92, 946, 42);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		//Create a new panel and add styles
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 0, 51));
		panel_3.setBounds(937, 128, 49, 635);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		//Create a PLAY AGAIN button
		JButton PLAY_AGAIN = new JButton("MAIN MENU");
		PLAY_AGAIN.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				stopLBSound();
				dispose(); // throw away this page
				
				// Go back to main frame
				MainFrame m = new MainFrame();
				m.setLocationRelativeTo(null);
				m.setVisible(true);
			}
		});
		
		//Add styles to the button
		PLAY_AGAIN.setBackground(new Color(255, 255, 153));
		PLAY_AGAIN.setForeground(new Color(0, 0, 0));
		PLAY_AGAIN.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		PLAY_AGAIN.setBounds(180, 626, 245, 98);
		contentPane.add(PLAY_AGAIN);
		
		//Create an EXIT button and add styles
		JButton EXIT_BUTTON = new JButton("EXIT");
		EXIT_BUTTON.setBackground(new Color(255, 255, 153));
		EXIT_BUTTON.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		EXIT_BUTTON.setBounds(510, 624, 221, 98);
		contentPane.add(EXIT_BUTTON);
		
		//Add a label that will have the Christmas Tree image to add to the game's theme
		JLabel TREE = new JLabel();
		TREE.setBounds(531, 289, 272, 314);
		ImageIcon XMAS = new ImageIcon
				(this.getClass().getResource("/XMasTree1.png"));
		TREE.setIcon(XMAS);
		contentPane.add(TREE);
		
		EXIT_BUTTON.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				stopLBSound();
				dispose();
				UIManager.put("OptionPane.minimumSize",
						new Dimension(450,100));
				UIManager.put
				("OptionPane.background", new Color(224, 224, 224));
				UIManager.put("Panel.background", new Color(224, 224, 224));
				thankYou = new JLabel("Thanks for playing!");
				thankYou.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
				JOptionPane.showMessageDialog(null, thankYou);
				
				System.exit(0);
			}
		});
		
		/*
		 * Add and modify scores from the leaderboard in-game to the text file scores.txt
		 * which holds the all-time leaderboard that is saved even when the application
		 * terminates. Only store the top 5 scores instead of all scores
		 */
		try 
		{
			scoresReader = new Scanner(new File("Scores/scores.txt"));
			int rank = 1;
			while (scoresReader.hasNextLine()) 
			{
				if (rank>5)
				{
					break;
				}
				
				String info = scoresReader.nextLine();
				String player = info.split(" ")[0];
				int score = Integer.parseInt(info.split(" ")[1]);
				//Add players to ranks based on their scores
				if (rank==1) 
				{
					PlayerName[0] = player;
					PlayerScore[0] = score;
				} 
				
				else if (rank==2) 
				{
					PlayerName[1] = player;
					PlayerScore[1] = score;
				}
				
				else if (rank==3) 
				{
					PlayerName[2] = player;
					PlayerScore[2] = score;
				} 
				
				else if (rank==4)
				{
					PlayerName[3] = player;
					PlayerScore[3] = score;
				} 
				
				else if (rank==5) 
				{
					PlayerName[4] = player;
					PlayerScore[4] = score;
				}
				rank++;
			}
		} 
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		PlayerScore[5] = Game.point;
		PlayerName[5] = Game.playerName;
		if(PlayerScore[5] <= PlayerScore[4])
		{
			UIManager.put("OptionPane.minimumSize",
					new Dimension(500,150));
			UIManager.put
			("OptionPane.background", new Color(224, 224, 224));
			UIManager.put("Panel.background", new Color(224, 224, 224));
			Sorry = new JLabel("Sorry, you didn't make it on "
					+ "the leaderboard");
			Sorry.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
			JOptionPane.showMessageDialog(null, Sorry);
		}
		
		//Compare and order the scores in order of highest score to lowest score
		if(PlayerScore[5] > PlayerScore[4])
		{
			temp_PN = PlayerName[5];
			PlayerName[5] = PlayerName[4];
			PlayerName[4] = temp_PN;
			
			temp_PS = PlayerScore[5];
			PlayerScore[5] = PlayerScore[4];
			PlayerScore[4] = temp_PS;
			
			
			if(PlayerScore[4] > PlayerScore[3])
			{
				temp1_PN = PlayerName[4];
				PlayerName[4] = PlayerName[3];
				PlayerName[3] = temp1_PN;
				
				temp1_PS = PlayerScore[4];
				PlayerScore[4] = PlayerScore[3];
				PlayerScore[3] = temp1_PS;
				
				if(PlayerScore[3] > PlayerScore[2])
				{
					temp2_PN = PlayerName[3];
					PlayerName[3] = PlayerName[2];
					PlayerName[2] = temp2_PN;
					
					temp2_PS = PlayerScore[3];
					PlayerScore[3] = PlayerScore[2];
					PlayerScore[2] = temp2_PS;
					
					if(PlayerScore[2] > PlayerScore[1])
					{
						temp3_PN = PlayerName[2];
						PlayerName[2] = PlayerName[1];
						PlayerName[1] = temp3_PN;
						
						temp3_PS = PlayerScore[2];
						PlayerScore[2] = PlayerScore[1];
						PlayerScore[1] = temp3_PS;
						
						if(PlayerScore[1] > PlayerScore[0])
						{
							temp4_PN = PlayerName[1];
							PlayerName[1] = PlayerName[0];
							PlayerName[0] = temp4_PN;
							
							temp4_PS = PlayerScore[1];
							PlayerScore[1] = PlayerScore[0];
							PlayerScore[0] = temp4_PS;
						}
					}
					
				}
				
			}
		}
		
		//Take scores from the file and add them to the application's leaderboard
		try
		{
			File f = new File("Scores/scores.txt");
			FileWriter fw = new FileWriter(f);
			for(int i = 0; i< 5; i++) 
			{
				if (PlayerName[i] == null) 
				{
					break;
				}
				
				fw.write(PlayerName[i] + " " + PlayerScore[i] + "\n");
			}
			 
			fw.close();
		}
		
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		/*
		 * Read the file and format the string before writing the top
		 * 5 scores to the leaderboard where the empty labels were
		 */
		
		try 
		{
			top5Reader = new Scanner(new File("Scores/scores.txt"));
			int rank1 = 1;
			while (top5Reader.hasNextLine()) 
			{
				if (rank1 > 5)
				{
					break;
				}
					
				String info1 = top5Reader.nextLine();
				String player1 = info1.split(" ")[0];
				int score1 = Integer.parseInt(info1.split(" ")[1]);
				if (rank1 == 1) 
				{
					Score1.setFont(new Font
							("Comic Sans MS", Font.PLAIN, 40));
					Score1.setText("1) " + player1 + " - " + score1);
				} 
				
				else if (rank1 == 2) 
				{
					Score2.setFont(new Font
							("Comic Sans MS", Font.PLAIN, 40));
					Score2.setText("2) " + player1 + " - " + score1);
				} 
				
				else if (rank1 == 3) 
				{
					Score3.setFont(new Font
							("Comic Sans MS", Font.PLAIN, 40));
					Score3.setText("3) " + player1 + " - " + score1);
				} 
				
				else if (rank1 == 4) 
				{
					Score4.setFont(new Font
							("Comic Sans MS", Font.PLAIN, 40));
					Score4.setText("4) " + player1 + " - " + score1);
				} 
				
				else if (rank1 == 5) 
				{
					PlayerName[4] = player1;
					PlayerScore[4] = score1;
					Score5.setFont(new Font
							("Comic Sans MS", Font.PLAIN, 40));
					Score5.setText("5) " + player1 + " - " + score1);
				}
				
				rank1++;
			}
		} 
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		//Loop the music until the application terminates
		try
		{
			clipLb = AudioSystem.getClip();
			AudioInputStream input = 
					AudioSystem.getAudioInputStream
					(new File("Music/WWYAMC.wav"));
			clipLb.open(input);
			clipLb.loop(Clip.LOOP_CONTINUOUSLY);
			clipLb.start();
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
	//Stops the game sound
	public void stopLBSound()
	{
		clipLb.stop();
	}
}

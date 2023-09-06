import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Panel;
import javax.swing.JTextField;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame 
{
	//Global variables
	Clip clip;
	JLabel ThankU;
	
	private JPanel contentPane;

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
					MainFrame frame = new MainFrame();	//Create new frame
					//Set relative location and visibility of frame
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() 
	{
		// Set Title
		setTitle("AlphabetLearner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		
		// Create content Pane
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Add label ABC visual
		JLabel pic1 = new JLabel("");
		ImageIcon imageABC = new ImageIcon
				(this.getClass().getResource("/TestPic.png"));
		pic1.setIcon(imageABC);
		pic1.setBounds(456,264,290,228);
		getContentPane().add(pic1);
		
		// Add Logo
		JLabel pic2 = new JLabel("");
		ImageIcon logo = new ImageIcon
				(this.getClass().getResource("/logo.jpg"));
		pic2.setIcon(logo);
		pic2.setBounds(214,275,210,207);
		getContentPane().add(pic2);
		
		// Welcome label with styles
		JLabel Welcome_Header = new JLabel("Welcome");
		Welcome_Header.setBounds(348, 124, 245, 88);
		Welcome_Header.setHorizontalAlignment(SwingConstants.CENTER);
		Welcome_Header.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
		contentPane.add(Welcome_Header);
		
		// Play button
		JButton PLAY_Button = new JButton("PLAY");
		PLAY_Button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				stop();
				dispose(); // throw away this page
				
				// Go back to main frame
				Game game = new Game();
				game.setLocationRelativeTo(null);
				game.setVisible(true);
			}
		});
		
		//Create PLAY button with styles
		PLAY_Button.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		PLAY_Button.setBackground(new Color(255, 255, 153));
		PLAY_Button.setBounds(359, 568, 200, 100);
		contentPane.add(PLAY_Button);
		
		// Create Green Panel
		Panel panel = new Panel();
		panel.setBackground(new Color(51, 153, 0));
		panel.setBounds(0, 0, 988, 106);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Add buttons into Green Panel
		
		// How to play button that links the Main Frame to the How to Play Page
		JButton HTP_Button = new JButton("How to Play");
		HTP_Button.setBounds(507, 12, 187, 65);
		HTP_Button.setFocusPainted(false);
		panel.add(HTP_Button);
		HTP_Button.setBackground(new Color(255, 255, 153));
		HTP_Button.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		
		// Exit button to exit the game
		JButton EXIT_Button = new JButton("Exit");
		EXIT_Button.setBounds(744, 12, 139, 65);
		panel.add(EXIT_Button);
		EXIT_Button.setBackground(new Color(255, 255, 153));
		EXIT_Button.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		
		// Label that's in green panel
		JLabel GAME_label = new JLabel("AlphabetLearner");
		GAME_label.setForeground(Color.ORANGE);
		GAME_label.setHorizontalAlignment(SwingConstants.CENTER);
		GAME_label.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		GAME_label.setBounds(54, 17, 238, 44);
		panel.add(GAME_label);
		
		// Insert music
		try
		{
			clip = AudioSystem.getClip();
			AudioInputStream input = 
					AudioSystem.getAudioInputStream
					(new File("Music/ABCSong.wav"));
			clip.open(input);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
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
		
		// Exit button
		EXIT_Button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				stop();
				dispose(); // throw away this page
				//Add styles
				UIManager.put("OptionPane.minimumSize",
						new Dimension(450,100));
				UIManager.put
				("OptionPane.background", new Color(224, 224, 224));
				UIManager.put("Panel.background", new Color(224, 224, 224));
				ThankU = new JLabel("Thanks for playing!");
				ThankU.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
				JOptionPane.showMessageDialog(null, ThankU);
				System.exit(0);
			}
		});
		
		// HowToPlay Button
		HTP_Button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				stop();
				dispose();
				HowToPlay h = new HowToPlay();
				h.setLocationRelativeTo(null);
				h.setVisible(true);
			}
		});
	}
	
	// Stop the music from playing
	public void stop()
	{
		clip.stop();
	}
}

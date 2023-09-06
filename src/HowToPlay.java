import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

//How to Play page
public class HowToPlay extends JFrame 
{
	//Panel that will hold all the content
	private JPanel contentPane;
	Clip clip1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() //Run the application
			{
				try 
				{
					HowToPlay frame = new HowToPlay();	//Create new frame
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
	public HowToPlay()
	{	
		setTitle("Instructions");	//Title the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Close operation
		setBounds(100, 100, 1000, 800);	//Set size
		contentPane = new JPanel();
		//Add color and borders
		contentPane.setForeground(new Color(0, 51, 153));
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Create label that will title the frame for the user while adding styles
		JLabel HTP_Header = new JLabel("How to Play");
		HTP_Header.setForeground(new Color(0, 51, 153));
		HTP_Header.setBounds(312, 143, 279, 88);
		HTP_Header.setHorizontalAlignment(SwingConstants.CENTER);
		HTP_Header.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
		contentPane.add(HTP_Header);
		
		// Green logo
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 0));
		panel.setBounds(0, 0, 988, 108);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//Give game title in a label and add styles
		JLabel lblNewLabel_2 = new JLabel("AlphabetLearner");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		lblNewLabel_2.setForeground(Color.ORANGE);
		lblNewLabel_2.setBounds(54, 17, 238, 44);
		panel.add(lblNewLabel_2);
		
		// Create a text area with the instructions
		JTextArea t = new JTextArea();
		t.setForeground(new Color(0, 51, 153));
		t.setBackground(new Color(204, 204, 255));
		t.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		t.setText("You will be asked an alphabet question. "
				+ "\r\n4 choices will be displayed on the screen."
				+ "\r\nClick on the choice you think is correct. ");
		t.setBounds(119, 302, 775, 210);
		t.setEditable(false);
		contentPane.add(t);
		
		//Create a DONE button that will take the user back to the main frame
		JButton DONE_Button = new JButton("DONE");
		DONE_Button.setBackground(new Color(255, 255, 153));
		DONE_Button.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		DONE_Button.setBounds(368, 568, 209, 80);
		contentPane.add(DONE_Button);
		
		//Add thinking face image
		JLabel Thinking_Pic = new JLabel("New label");
		Thinking_Pic.setBounds(624, 133, 120, 120);
		ImageIcon thinking = new ImageIcon
				(this.getClass().getResource("/thinking1.png"));
		Thinking_Pic.setIcon(thinking);
		contentPane.add(Thinking_Pic);
		
		//Music 
		try
		{
			clip1 = AudioSystem.getClip();
			AudioInputStream input = 
					AudioSystem.getAudioInputStream
					(new File("Music/Bingo_Song.wav"));
			clip1.open(input);
			clip1.loop(Clip.LOOP_CONTINUOUSLY);
			clip1.start();
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
		
		// Go Back to Main
		DONE_Button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// stop Music
				stopMusic();
				dispose(); // throw away this page
				
				// Go back to main frame
				MainFrame m = new MainFrame();
				m.setLocationRelativeTo(null);
				m.setVisible(true);
			}
		});
	}
	//Method to stop the music from playing
	public void stopMusic()
	{
		clip1.stop();
	}
}

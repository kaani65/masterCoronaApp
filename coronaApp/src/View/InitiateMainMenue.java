package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controler.InitiateRegistration;
import Model.OracleDsSingleton;
import Model.Person;

import Controler.PersonDAO;


public class InitiateMainMenue implements ActionListener {


	private static JButton backButton;
	private static JButton newestNumbers;
	private static JButton staySafe;
	private static ImageIcon icon;
	private static JTextField coronaSearchfield;
	private static JButton coronaSearchButton;



	public void mainMenue(JFrame frame, JPanel panel, InitiateSearch iSearch, InitiateLogin alogin, InitiateMainMenue mMenue, InitiateRegistration register) {

		ImageIcon icon = new ImageIcon("src/images/coronapic.png");
		JButton coronapic = new JButton("", icon);


		coronapic.setContentAreaFilled(false);
		coronapic.setBounds(90, 30, 200, 200);
		panel.add(coronapic);



		coronaSearchfield = new JTextField();
		coronaSearchfield.setBounds(110, 250, 150, 30);
		panel.add(coronaSearchfield);

		backButton = new JButton("<");
		backButton.setFont(new Font("Arial", Font.BOLD,20));
		backButton.setBackground(Color.GRAY);
		backButton.setBounds(20, 20, 30, 30);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					panel.removeAll();
					//panel.validate();
					frame.setVisible(false);
					alogin.login(frame, panel, alogin, mMenue, register, iSearch);
					panel.add(frame, panel);
				}catch (Exception exception){
					System.out.println("Back to Login");
				}
			}

		} );

		panel.add(backButton);

		coronaSearchButton = new JButton("Corona Search");
		coronaSearchButton.setBounds(80, 300, 80, 25);
		coronaSearchButton.setSize(202,60);
		panel.add(coronaSearchButton);
		coronaSearchButton.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			String suche = coronaSearchfield.getText();
			PersonDAO searchh = new PersonDAO();
			InitiateSearch iSearch = new InitiateSearch();			
			panel.removeAll();
			frame.setVisible(false);
			iSearch.InitiateSearch(frame, panel,mMenue,alogin,register,iSearch);
			
			try {
				List<Person> matchedPersons = searchh.searchL(suche);
				int matchedPersonsSize = matchedPersons.size();
				
				for (int i = 0; i < matchedPersonsSize; i++) {
					iSearch.createSearchB(panel, frame, i + 2, matchedPersons.get(i)); // + 2 so that there is room left for the back button
				}
			}
			catch(Exception f){
				System.out.println("delfin.exe ist erfolgreich Installiert ;)");
			}
		}
		
		
		

	});
		newestNumbers = new JButton("Newst Numbers");
		newestNumbers.setBounds(80, 500, 80, 25);
		newestNumbers.setSize(202, 60);
		newestNumbers.addActionListener((ActionListener) new InitiateMainMenue());
		panel.add(newestNumbers);

		staySafe = new JButton("Stay Safe");
		staySafe.setBounds(80, 400, 80, 25);
		staySafe.setSize(202, 60);
		staySafe.addActionListener((ActionListener) new InitiateMainMenue());
		panel.add(staySafe);

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Desktop browser = Desktop.getDesktop();
		if (e.getSource() == newestNumbers) {
			try {
				browser.browse(new URI("http://coronavirus.app"));
			} catch (IOException err) {

			} catch (URISyntaxException err) {

			}

		}
		if (e.getSource() == staySafe) {
			try {
				browser.browse(new URI("https://www.rki.de/DE/Content/InfAZ/N/Neuartiges_Coronavirus/nCoV.html"));
			} catch (IOException err) {

			} catch (URISyntaxException err) {

			}
		}
	}

}
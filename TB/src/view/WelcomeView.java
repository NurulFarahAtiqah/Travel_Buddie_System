package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

@SuppressWarnings({ "unused", "serial" })
public class WelcomeView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeView frame = new WelcomeView();
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
	public WelcomeView() {
		setTitle("TRAVEL BUDGET");
		try {UIManager.setLookAndFeel(new NimbusLookAndFeel());} catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO");
		lblNewLabel.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 70));
		lblNewLabel.setBounds(288, 73, 331, 159);
		contentPane.add(lblNewLabel);
		
		JButton btnLetsGo = new JButton("LETS GO !");
		btnLetsGo.setForeground(new Color(255, 255, 255));
		btnLetsGo.setBackground(new Color(139, 69, 19));
		btnLetsGo.setFont(new Font("Lucida Fax", Font.BOLD, 16));
		btnLetsGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				LoginView loginView = new LoginView();
				loginView.setVisible(true);
				dispose();
			}
		});
		btnLetsGo.setBounds(373, 400, 150, 40);
		contentPane.add(btnLetsGo);
		
		JLabel lblTravelBudget = new JLabel("TRAVEL BUDGET");
		lblTravelBudget.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 80));
		lblTravelBudget.setBounds(235, 166, 443, 141);
		contentPane.add(lblTravelBudget);
		
		JLabel lblNewLabel_1 = new JLabel("S  Y  S  T  E  M");
		lblNewLabel_1.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 50));
		lblNewLabel_1.setBounds(288, 243, 348, 110);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Java\\TravellerBudget\\BackgroundImage\\backy003.jpg"));
		lblNewLabel_2.setBounds(0, 0, 884, 562);
		contentPane.add(lblNewLabel_2);
	}
}

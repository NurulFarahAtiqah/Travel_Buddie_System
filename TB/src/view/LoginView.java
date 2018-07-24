package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Controller.SessionManager;
import Controller.SubscribeManager;
import Model.Session;
import Model.Subscribe;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

@SuppressWarnings({ "unused", "serial" })
public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField txtExamplegmailcom;
	private JButton btnImAlreadySubscribe;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setVisible(true);
		setTitle("TRAVEL BUDGET");
		try {UIManager.setLookAndFeel(new NimbusLookAndFeel());} catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 25));
		lblEmail.setBounds(133, 340, 88, 35);
		contentPane.add(lblEmail);

		lblNewLabel = new JLabel("S U B S C R I B E");
		lblNewLabel.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 70));
		lblNewLabel.setBounds(220, 179, 495, 105);
		contentPane.add(lblNewLabel);

		lblNewLabel_2 = new JLabel("THANK YOU FOR SUBSCRIBING WITH US !");
		lblNewLabel_2.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(346, 510, 208, 41);
		contentPane.add(lblNewLabel_2);

		txtExamplegmailcom = new JTextField();
		txtExamplegmailcom.setBackground(new Color(188, 143, 143));
		txtExamplegmailcom.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtExamplegmailcom.setBounds(231, 340, 449, 35);
		contentPane.add(txtExamplegmailcom);
		txtExamplegmailcom.setColumns(10);


		JButton btnNewButton = new JButton("Subscribe");
		btnNewButton.setBackground(new Color(188, 143, 143));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				boolean checkEmail = validate(txtExamplegmailcom.getText().toString());
				System.out.println("checkEmail = "+checkEmail);
				if(checkEmail==false)
				{
					JOptionPane.showMessageDialog(null, "Please insert correct email.", "Warning",JOptionPane.WARNING_MESSAGE);
				}
				else
				{

					if (txtExamplegmailcom.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Please insert your email.", "Warning",JOptionPane.WARNING_MESSAGE);

					}

					else
					{
						String email = txtExamplegmailcom.getText();
						Subscribe subscribe = new Subscribe();
						subscribe.setEmail(email);

						SubscribeManager subscribeManager = new SubscribeManager();
						boolean status;
						try {
							status = subscribeManager.findSubscribe(email);


							if(status == true)
							{
								JOptionPane.showMessageDialog(null, "Your are already subscribed. Please click button \"I'm Already Subscribe\"");
							}

							else
							{

								int id = subscribeManager.insertSubscribe(subscribe);


								JOptionPane.showMessageDialog(null, "Data Saved");


								Session session = new Session();
								session.setId_subscribe(id);

								Date dNowDate = new Date();
								SimpleDateFormat ftDate = new SimpleDateFormat ("dd-MM-yyyy");
								session.setDate(ftDate.format(dNowDate));

								Date dNowTime = new Date();
								SimpleDateFormat ftTime = new SimpleDateFormat ("hh:mm:ss a zzz");
								session.setTime(ftTime.format(dNowTime));

								SessionManager sessionmanager = new SessionManager();
								int idSession = sessionmanager.insertLog(session);
								session.setId(idSession);

								System.out.println("LoginView session "+session.getId());


								LocationView locationView = new LocationView(session);
								locationView.setVisible(true);
								dispose();

							}

						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}

			}
		});
		btnNewButton.setBounds(471, 408, 183, 35);
		contentPane.add(btnNewButton);

		btnImAlreadySubscribe = new JButton("I'm Already Subscribe");
		btnImAlreadySubscribe.setBackground(new Color(188, 143, 143));
		btnImAlreadySubscribe.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnImAlreadySubscribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtExamplegmailcom.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please insert your email.", "Warning",JOptionPane.WARNING_MESSAGE);
				}else
				{
					SubscribeManager subscribeManager = new SubscribeManager();

					try {
						boolean status = subscribeManager.findSubscribe(txtExamplegmailcom.getText());

						if(status == false)
						{
							JOptionPane.showMessageDialog(null, "Your are not subsribe. Please click subscribe button.", "Warning",JOptionPane.WARNING_MESSAGE);
						}
						else if(status == true)
						{

							int id = subscribeManager.findSubscribeId(txtExamplegmailcom.getText());
							Session session = new Session();
							session.setId_subscribe(id);

							Date dNowDate = new Date();
							SimpleDateFormat ftDate = new SimpleDateFormat ("dd-MM-yyyy");
							session.setDate(ftDate.format(dNowDate));

							Date dNowTime = new Date();
							SimpleDateFormat ftTime = new SimpleDateFormat ("hh:mm:ss a zzz");
							session.setTime(ftTime.format(dNowTime));

							SessionManager sessionmanager = new SessionManager();
							int idSession = sessionmanager.insertLog(session);
							session.setId(idSession);

							System.out.println("LoginView session "+session.getId());
							LocationView locationView = new LocationView(session);
							locationView.setVisible(true);
							dispose();
						}

					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}
			}
		});
		btnImAlreadySubscribe.setBounds(264, 408, 183, 35);
		contentPane.add(btnImAlreadySubscribe);

		JLabel lblNewLabel_3 = new JLabel("(eg; example@gmail.com)");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(241, 366, 136, 41);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Java\\TravellerBudget\\BackgroundImage\\backy006.jpg"));
		lblNewLabel_1.setBounds(0, 0, 884, 562);
		contentPane.add(lblNewLabel_1);
	}



	/**
	 * Validate hex with regular expression
	 *
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
}

// Select SUM(totalEstimation) FROM estimation WHERE id_session =? 

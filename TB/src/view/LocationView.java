package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Controller.AttractionManager;
import Controller.FoodManager;
import Controller.HotelManager;
import Controller.LocationManager;
import Model.Session;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class LocationView extends JFrame {

	private JPanel contentPane;
	private JButton btnSearch;
	private JButton btnFood;
	private JButton btnHotel;
	private JButton btnAttraction;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocationView frame = new LocationView(null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LocationView(Session session) {
		setTitle("TRAVEL BUDGET");
		System.out.println("LocationView session "+session.getId());
		setVisible(true);
		try {UIManager.setLookAndFeel(new NimbusLookAndFeel());} catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSearchYourLocation = new JLabel("Search Your State : ");
		lblSearchYourLocation.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 20));
		lblSearchYourLocation.setBounds(138, 65, 148, 28);
		contentPane.add(lblSearchYourLocation);



		ArrayList<String> state = new ArrayList<String>();

		HotelManager HotelMngr = new HotelManager();
		FoodManager FoodMngr = new FoodManager();
		AttractionManager AttractionMngr = new AttractionManager();

		try {

			ArrayList<String> stateHotel = HotelMngr.viewAllStateHotel();
			for(String stateHotelTemp : stateHotel )
			{
				boolean status = false;
				for (String stateTemp : state)
				{

					if (stateHotelTemp.equals(stateTemp))
					{
						status = true;
					}
				}
				if (status == false)
				{
					state.add(stateHotelTemp);
				}
			}

			ArrayList<String> stateFood = FoodMngr.viewAllStateFood();
			for(String stateFoodTemp : stateFood )
			{
				boolean status = false;
				for (String stateTemp : state)
				{
					if (stateFoodTemp.equals(stateTemp))
					{
						status = true;
					}
				}
				if (status == false)
				{
					state.add(stateFoodTemp);
				}
			}

			ArrayList<String> stateAttraction = AttractionMngr.viewAllStateAttraction();
			for(String stateAttractionTemp : stateAttraction )
			{
				boolean status = false;
				for (String stateTemp : state)
				{
					if (stateAttractionTemp.equals(stateTemp))
					{
						status = true;
					}
				}
				if (status == false)
				{
					state.add(stateAttractionTemp);
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(state.size());

		String[] states= new String[state.size()];

		for(int index=0 ; index<state.size() ; index++)
		{
			states[index]=state.get(index);
		}
		
		lblNewLabel_1 = new JLabel("Want to exit from system ?");
		lblNewLabel_1.setFont(new Font("Gill Sans MT", Font.ITALIC, 16));
		lblNewLabel_1.setBounds(378, 465, 179, 25);
		contentPane.add(lblNewLabel_1);

		JComboBox comboBox = new JComboBox(states);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox.setBounds(270, 65, 318, 28);

		contentPane.add(comboBox);

		btnFood = new JButton("F O O D");
		btnFood.setForeground(new Color(255, 255, 255));
		btnFood.setBackground(new Color(160, 82, 45));
		btnFood.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 25));
		btnFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FoodMenu food = new FoodMenu(comboBox.getSelectedItem().toString(), session);
				dispose();
			}
		});
		btnFood.setBounds(270, 336, 353, 67);
		contentPane.add(btnFood);

		btnHotel = new JButton("H O T E L");
		btnHotel.setForeground(new Color(255, 255, 255));
		btnHotel.setBackground(new Color(160, 82, 45));
		btnHotel.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 25));
		btnHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HotelMenu frame = new HotelMenu(comboBox.getSelectedItem().toString(), session);
				dispose();
			}
		});
		btnHotel.setBounds(270, 165, 353, 67);
		contentPane.add(btnHotel);

		btnAttraction = new JButton("A T T R A C T I O N");
		btnAttraction.setForeground(new Color(255, 255, 255));
		btnAttraction.setBackground(new Color(160, 82, 45));
		btnAttraction.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 25));
		btnAttraction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AttractionMenu attractionMenu = new AttractionMenu(comboBox.getSelectedItem().toString(), session);
				attractionMenu.setVisible(true);
				dispose();
				
				
			}
		});
		btnAttraction.setBounds(270, 250, 353, 67);
		contentPane.add(btnAttraction);
		
		btnAttraction.setEnabled(false);
		btnHotel.setEnabled(false);
		btnFood.setEnabled(false);
		
//		btnAttraction.setVisible(false);
//		btnHotel.setVisible(false);
//		btnFood.setVisible(false);
		

		btnSearch = new JButton("SELECT");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//				OptionMenu optionMenu = new OptionMenu();
				//				optionMenu.setVisible(true);
				//				dispose();

				System.out.println(comboBox.getSelectedItem());
				
//				btnAttraction.setVisible(false);
//				btnHotel.setVisible(false);
//				btnFood.setVisible(false);
				
				btnAttraction.setEnabled(false);
				btnHotel.setEnabled(false);
				btnFood.setEnabled(false);
				
				HotelManager HotelMngr = new HotelManager();
				FoodManager FoodMngr = new FoodManager();
				AttractionManager AttractionMngr = new AttractionManager();
				
				try {
					ArrayList<String> stateHotel = HotelMngr.viewSelectedStateHotel(comboBox.getSelectedItem().toString());
					ArrayList<String> stateFood = FoodMngr.viewSelectedStateFood(comboBox.getSelectedItem().toString());
					ArrayList<String> stateAttraction = AttractionMngr.viewSelectedStateAttraction(comboBox.getSelectedItem().toString());
					
					if(stateHotel.size() != 0)
					{
						//btnHotel.setVisible(true);
						btnHotel.setEnabled(true);
					}
					
					if(stateFood.size() != 0)
					{
						//btnFood.setVisible(true);
						btnFood.setEnabled(true);
					}
					
					if(stateAttraction.size() != 0)
					{
						//btnAttraction.setVisible(true);
						btnAttraction.setEnabled(true);
					}
					
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}
		});
		btnSearch.setBounds(598, 64, 103, 30);
		contentPane.add(btnSearch);
		
		btnNewButton = new JButton("EXIT");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(128, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginView frame = new LoginView();
				dispose();
			}
		});
		btnNewButton.setBounds(410, 501, 90, 30);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Java\\TravellerBudget\\BackgroundImage\\backy007.jpg"));
		lblNewLabel.setBounds(0, 0, 884, 562);
		contentPane.add(lblNewLabel);



	}
}

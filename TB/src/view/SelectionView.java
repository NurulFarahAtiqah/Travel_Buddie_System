package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.mysql.jdbc.EscapeTokenizer;

import Controller.AttractionManager;
import Controller.EstimationManager;
import Controller.FoodManager;
import Controller.HotelManager;
import Model.Attraction;
import Model.Estimation;
import Model.Food;
import Model.Hotel;
import Model.Session;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.Font;

public class SelectionView {

	private JFrame frmTravelBudget;
	private int idHotel = 0;
	private int idFood = 0;
	private int idAttraction =0;
	private String name = null;
	private Double price = 0.0;
	private String state = null;
	private String section = null;
	private Session session;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectionView window = new SelectionView("C:\\Users\\User\\Desktop\\Java\\TravellerBudget\\Gambar\\HOTEL\\HotelImpian.jpeg", null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectionView(String image, Session session) {

		initialize(image,session);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String image, Session session) {
		System.out.println("SelectionView session "+session.getId());
		try {UIManager.setLookAndFeel(new NimbusLookAndFeel());} catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}

		frmTravelBudget = new JFrame();
		frmTravelBudget.setTitle("TRAVEL BUDGET");
		frmTravelBudget.setVisible(true);
		frmTravelBudget.setBounds(100, 100, 598, 355);
		frmTravelBudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTravelBudget.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 224));
		panel.setBounds(20, 41, 215, 179);
		frmTravelBudget.getContentPane().add(panel);


		SpinnerModel spinnerModel = new SpinnerNumberModel(0,  0,  null, 1);
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setFont(new Font("Tahoma", Font.BOLD, 11));
		spinner.setBounds(333, 180, 60, 23);
		frmTravelBudget.getContentPane().add(spinner);

		try {


			HotelManager hotelmanager = new HotelManager();
			ArrayList<Hotel> listHotel = hotelmanager.viewSelectedHotelFromPath(image);
			if (!listHotel.isEmpty())
			{
				for(Hotel listHotelTemp : listHotel)
				{
					idHotel = listHotelTemp.getId();
					name = listHotelTemp.getName();
					price = listHotelTemp.getPrice();
					state = listHotelTemp.getState();
					section = "Hotel";
				}
			}

			FoodManager foodmanager = new FoodManager();
			ArrayList<Food> listFood = foodmanager.viewSelectedFoodFromPath(image);
			if (!listFood.isEmpty())
			{
				for(Food listHotelTemp : listFood)
				{
					idFood = listHotelTemp.getId();
					name = listHotelTemp.getName();
					price = listHotelTemp.getPrice();
					state = listHotelTemp.getState();
					section = "Food";
				}
			}

			AttractionManager attractionmanager = new AttractionManager();
			ArrayList<Attraction> listAttraction = attractionmanager.viewSelectedAttractionFromPath(image);
			if (!listAttraction.isEmpty())
			{
				for(Attraction listAttractionTemp : listAttraction)
				{
					idAttraction = listAttractionTemp.getId();
					name = listAttractionTemp.getName();
					price = listAttractionTemp.getPrice();
					state = listAttractionTemp.getState();
					section = "Attraction";
				}
			}


		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageIcon picTempOri = new ImageIcon (image);
		Image img = picTempOri.getImage();
		Image newimg = img.getScaledInstance(((150*picTempOri.getIconWidth())/picTempOri.getIconHeight()), 150,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);

		JLabel lblNewLabel = new JLabel(newIcon);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Name        :");
		lblNewLabel_1.setBounds(245, 74, 78, 14);
		frmTravelBudget.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Price (RM) :");
		lblNewLabel_2.setBounds(245, 111, 78, 14);
		frmTravelBudget.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("State          :");
		lblNewLabel_3.setBounds(245, 147, 78, 14);
		frmTravelBudget.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(name);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(333, 74, 256, 14);
		frmTravelBudget.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(price.toString());
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(333, 111, 256, 14);
		frmTravelBudget.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(state);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(333, 147, 256, 14);
		frmTravelBudget.getContentPane().add(lblNewLabel_6);

		JButton btnNewButton = new JButton("Add To Budget");
		btnNewButton.setBackground(new Color(245, 222, 179));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				double totalEstimation = price*Integer.parseInt(spinner.getValue().toString());
				int id_session = session.getId();
				int id_food = idFood;
				int id_Hotel = idHotel;
				int id_Attraction = idAttraction;

				Estimation estimation = new Estimation();
				estimation.setTotalEstimation(totalEstimation);
				estimation.setId_session(id_session);
				estimation.setId_food(id_food);
				estimation.setId_Hotel(id_Hotel);
				estimation.setId_Attraction(id_Attraction);
				estimation.setQuantity(Integer.parseInt(spinner.getValue().toString()));
				System.out.println("Spinner : "+Integer.parseInt(spinner.getValue().toString()));

				EstimationManager estimationmanager = new EstimationManager();
				try {
					int status = estimationmanager.insertEstimation(estimation);
					if (status == 0)
						JOptionPane.showMessageDialog(null, "Unsuccessfully add to budget!", "Warning",JOptionPane.WARNING_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Successfully add to budget!", "Status",JOptionPane.INFORMATION_MESSAGE);

				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		btnNewButton.setBounds(287, 276, 132, 30);
		frmTravelBudget.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("View Budget");
		btnNewButton_1.setBackground(new Color(245, 222, 179));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				EstimationManager estimationmanager = new EstimationManager();
				try {
					
					ArrayList<Estimation> listEstimation =  estimationmanager.viewEstimation(session);
					
					if(listEstimation.size()==0)
					{
						JOptionPane.showMessageDialog(null, "Please add to budget!", "Warning",JOptionPane.WARNING_MESSAGE);
					}
					else if(listEstimation.size()!=0)
					{
						BudgetView bv = new BudgetView(session);
						frmTravelBudget.dispose();
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				
			}
		});
		btnNewButton_1.setBounds(435, 276, 126, 30);
		frmTravelBudget.getContentPane().add(btnNewButton_1);

		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(160, 82, 45));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmTravelBudget.dispose();
			}
		});
		btnBack.setBounds(20, 276, 100, 30);
		frmTravelBudget.getContentPane().add(btnBack);

		JLabel lblNewLabel_7 = new JLabel("Quantity     :");
		lblNewLabel_7.setBounds(245, 183, 78, 14);
		frmTravelBudget.getContentPane().add(lblNewLabel_7);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 224));
		panel_1.setBounds(234, 41, 327, 179);
		frmTravelBudget.getContentPane().add(panel_1);

		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Java\\TravellerBudget\\BackgroundImage\\backy007.jpg"));
		lblNewLabel_8.setBounds(0, 0, 589, 317);
		frmTravelBudget.getContentPane().add(lblNewLabel_8);


	}
}

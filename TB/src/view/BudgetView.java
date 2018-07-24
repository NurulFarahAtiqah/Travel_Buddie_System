package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;

import Controller.AttractionManager;
import Controller.EstimationManager;
import Controller.FoodManager;
import Controller.HotelManager;
import Model.Estimation;
import Model.Session;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class BudgetView {

	private JFrame frmBudgetView;

	Double attractionTotal = 0.0;
	Double foodTotal = 0.0;
	Double hotelTotal = 0.0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Session session = new Session();
					session.setId(31);
					session.setId_subscribe(1);
					session.setDate("23-05-2017");
					session.setTime("02:44:49 PM SGT");
					BudgetView window = new BudgetView(session);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BudgetView(Session session) {
		initialize(session);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Session session) {
		frmBudgetView = new JFrame();
		frmBudgetView.setTitle("BUDGET VIEW");
		frmBudgetView.setVisible(true);
		frmBudgetView.setBounds(100, 100, 608, 426);
		frmBudgetView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBudgetView.getContentPane().setLayout(null);


		JLabel lbTotalEstimation = new JLabel("New label");
		lbTotalEstimation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbTotalEstimation.setBounds(489, 346, 93, 29);
		frmBudgetView.getContentPane().add(lbTotalEstimation);

		JLabel lblEmail = new JLabel("New label");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(66, 30, 359, 18);
		frmBudgetView.getContentPane().add(lblEmail);

		EstimationManager estimationmanager = new EstimationManager();
		try {
			ArrayList<Estimation> listEstimation =  estimationmanager.viewEstimation(session);
			lbTotalEstimation.setText(estimationmanager.calculateEstimation(session).toString());

			int id_session = listEstimation.get(0).getId_session();
			String email = estimationmanager.findEmail(session);
			lblEmail.setText(email);


			String estimationData[][]= new String[listEstimation.size()][4];

			String estimationId [] = new String [listEstimation.size()];
			
			String type = null;


			for(int index=0 ; index<listEstimation.size() ; index++)
			{
				String nameSection = null;

				if (listEstimation.get(index).getId_Attraction() != 0){
					int idSection = listEstimation.get(index).getId_Attraction();

					AttractionManager attractionmanager = new AttractionManager();
					nameSection = attractionmanager.selectAttractionNameFromID(idSection);
					type = "Attraction";
					attractionTotal =  attractionTotal+listEstimation.get(index).getTotalEstimation();
					System.out.println("attractionTotal "+attractionTotal);
				}

				if (listEstimation.get(index).getId_food() != 0){
					int idSection = listEstimation.get(index).getId_food();

					FoodManager foodtmanager = new FoodManager();
					nameSection = foodtmanager.selectFoodNameFromID(idSection);
					type = "Food";
					foodTotal =  foodTotal+listEstimation.get(index).getTotalEstimation();
					System.out.println("foodTotal "+foodTotal);
				}

				if (listEstimation.get(index).getId_Hotel() != 0){
					int idSection = listEstimation.get(index).getId_Hotel();

					HotelManager hoteltmanager = new HotelManager();
					nameSection = hoteltmanager.selectHotelNameFromID(idSection);
					type = "Hotel";
					hotelTotal =  hotelTotal+listEstimation.get(index).getTotalEstimation();
					System.out.println("hotelTotal "+hotelTotal);
				}

				estimationId[index] = String.valueOf(listEstimation.get(index).getId());
				estimationData[index][0] = type;
				estimationData[index][1] = nameSection;
				estimationData[index][2] = Integer.toString(listEstimation.get(index).getQuantity());
				estimationData[index][3] = Double.toString(listEstimation.get(index).getTotalEstimation());

			}

			String column[]={"TYPE","NAME","QUANTITY","TOTAL PRICE (RM)"};         
			final JTable jt=new JTable(estimationData,column);    
			//jt.setCellSelectionEnabled(true);  
			//ListSelectionModel select= jt.getSelectionModel();  
			//select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
			//select.addListSelectionListener(new ListSelectionListener() {  
			//public void valueChanged(ListSelectionEvent e) {  

			//ModifiedBudget mb = new ModifiedBudget(session);
			//frame.dispose();

			//					String Data = null;  
			//					int[] row = jt.getSelectedRows();  
			//					int[] columns = jt.getSelectedColumns();  
			//					for (int i = 0; i < row.length; i++) {  
			//						String type = (String)jt.getValueAt(row[i],0);
			//						String name = (String)jt.getValueAt(row[i],1);
			//						String quantity = (String)jt.getValueAt(row[i],2);
			//						System.out.println(type + name + quantity);
			//					}
			//
			//					System.out.println("Table element selected is: " + Data);    
			//				}       
			//			}); 

			jt.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int row = jt.rowAtPoint(evt.getPoint());
					int col = jt.columnAtPoint(evt.getPoint());
					if (row >= 0 && col >= 0) {

						String type = (String)jt.getValueAt(row,0);
						String name = (String)jt.getValueAt(row,1);
						String quantity = (String)jt.getValueAt(row,2);
						String totalPrice = (String)jt.getValueAt(row,3);
						System.out.println(type + name + quantity);
						
						String estimationId1 = estimationId[jt.rowAtPoint(evt.getPoint())];
						ModifiedBudget mb = new ModifiedBudget(session,type,name,quantity,totalPrice,estimationId1);
						frmBudgetView.dispose();
					}
				}
			});


			JScrollPane scrollPane = new JScrollPane(jt);
			scrollPane.setBounds(10, 75, 572, 239);
			frmBudgetView.getContentPane().add(scrollPane);

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}





		JLabel lblNewLabel = new JLabel("TOTAL BUDGET (RM) :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(331, 350, 162, 21);
		frmBudgetView.getContentPane().add(lblNewLabel);



		JLabel lblNewLabel_2 = new JLabel("User : ");
		lblNewLabel_2.setBounds(21, 29, 46, 21);
		frmBudgetView.getContentPane().add(lblNewLabel_2);





		JButton btnNewButton_1 = new JButton("ANALYSIS CHART");
		btnNewButton_1.setForeground(new java.awt.Color(139, 69, 19));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnNewButton_1.setBackground(new java.awt.Color(245, 222, 179));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//PieChart_AWT pie = new PieChart_AWT("Analysis Budget",attractionTotal,  foodTotal,  hotelTotal);
				//RefineryUtilities.centerFrameOnScreen(pie);  
				PieView window = new PieView("Analysis Budget",attractionTotal,  foodTotal,  hotelTotal);

			}
		});
		btnNewButton_1.setBounds(435, 24, 147, 30);
		frmBudgetView.getContentPane().add(btnNewButton_1);

		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setBackground(new java.awt.Color(139, 69, 19));
		btnNewButton.setForeground(new java.awt.Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmBudgetView.dispose();
			}
		});
		btnNewButton.setBounds(10, 347, 103, 30);
		frmBudgetView.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Java\\TravellerBudget\\BackgroundImage\\backy007.jpg"));
		lblNewLabel_1.setBounds(0, 0, 592, 388);
		frmBudgetView.getContentPane().add(lblNewLabel_1);

	}

}

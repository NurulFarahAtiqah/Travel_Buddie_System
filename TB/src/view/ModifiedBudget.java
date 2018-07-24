package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import Controller.EstimationManager;
import Controller.HotelManager;
import Model.Session;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class ModifiedBudget {

	private JFrame frmUpdate;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifiedBudget window = new ModifiedBudget(null,null,null,null,null,null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param quantity 
	 * @param name 
	 * @param type 
	 * @param estimationId 
	 */
	public ModifiedBudget(Session session, String type, String name, String quantity, String totalPrice , String estimationId) {
		initialize(session,  type,  name,  quantity, totalPrice ,estimationId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Session session, String type, String name, String quantity, String totalPrice, String estimationId) {
		frmUpdate = new JFrame();
		frmUpdate.setTitle("UPDATE");
		frmUpdate.setVisible(true);
		frmUpdate.setBounds(100, 100, 450, 333);
		frmUpdate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUpdate.getContentPane().setLayout(null);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setBounds(40, 38, 46, 14);
		frmUpdate.getContentPane().add(lblType);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(40, 80, 46, 14);
		frmUpdate.getContentPane().add(lblName);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setBounds(40, 149, 72, 24);
		frmUpdate.getContentPane().add(lblQuantity);
		

		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setText(name);
		lblNewLabel.setBounds(105, 80, 319, 14);
		frmUpdate.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setText(type);
		lblNewLabel_1.setBounds(105, 38, 319, 14);
		frmUpdate.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(105, 114, 165, 24);
		frmUpdate.getContentPane().add(lblNewLabel_4);
		
		Double pricePerPax = Double.parseDouble(totalPrice)/Double.parseDouble(quantity);
		System.out.println("totalPrice = "+Double.parseDouble(totalPrice));
		System.out.println("quantity = "+Double.parseDouble(quantity));
		System.out.println("pricePerPax = "+pricePerPax);
		lblNewLabel_4.setText(pricePerPax.toString());
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBackground(new Color(139, 69, 19));
		btnEdit.setForeground(new Color(255, 255, 255));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstimationManager em = new EstimationManager();
				try {
					int status =  em.updateEstimation(Integer.parseInt(textField.getText()),(Double.parseDouble(textField.getText())*Double.parseDouble(lblNewLabel_4.getText())), Integer.parseInt(estimationId));
					if(status == 0)
					{
						JOptionPane.showMessageDialog(null, "Unsuccessfully update!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Successfully update!");
						BudgetView window = new BudgetView(session);
						frmUpdate.dispose();
					}
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setBounds(163, 198, 89, 23);
		frmUpdate.getContentPane().add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBackground(new Color(139, 69, 19));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EstimationManager em = new EstimationManager();
				try {
					int status =  em.deleteEstimation(Integer.parseInt(estimationId));
					if(status == 0)
					{
						JOptionPane.showMessageDialog(null, "Unsuccessfully delete!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Successfully delete!");
						BudgetView window = new BudgetView(session);
						frmUpdate.dispose();
					}
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnDelete.setBounds(274, 198, 89, 23);
		frmUpdate.getContentPane().add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(139, 69, 19));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BudgetView window = new BudgetView(session);
				frmUpdate.dispose();
			}
		});
		btnCancel.setBounds(51, 198, 89, 23);
		frmUpdate.getContentPane().add(btnCancel);
		
		textField = new JTextField();
		
		textField.setBounds(105, 151, 165, 24);
		textField.setText(quantity);
		frmUpdate.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Price :   RM");
		lblNewLabel_3.setBounds(40, 114, 66, 24);
		frmUpdate.getContentPane().add(lblNewLabel_3);
		

		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Java\\TravellerBudget\\BackgroundImage\\backy007.jpg"));
		lblNewLabel_2.setBounds(0, 0, 434, 295);
		frmUpdate.getContentPane().add(lblNewLabel_2);
		

		

	}
}

package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class PieView {

	private JFrame frmAnalysisBudget;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PieView window = new PieView("Mobile Sales", 30.0, 20.0, 40.0);
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PieView( String title, Double attractionTotal, Double foodTotal, Double hotelTotal ) {
		initialize(  title,  attractionTotal,  foodTotal,  hotelTotal );
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize( String title, Double attractionTotal, Double foodTotal, Double hotelTotal ) {
		frmAnalysisBudget = new JFrame();
		frmAnalysisBudget.setTitle("ANALYSIS BUDGET");
		frmAnalysisBudget.setVisible(true);
		frmAnalysisBudget.setBounds(100, 100, 764, 543);
		frmAnalysisBudget.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnalysisBudget.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 728, 437);
		frmAnalysisBudget.getContentPane().add(panel);
		
		
	    DefaultPieDataset dataset = new DefaultPieDataset( );
	    dataset.setValue( "Hotel RM"+hotelTotal , new Double( hotelTotal ) );  
	    dataset.setValue( "Food RM"+foodTotal ,new Double( foodTotal ) );   
	    dataset.setValue( "Attraction RM"+attractionTotal ,new Double( attractionTotal ) ); 
		JFreeChart chart = ChartFactory.createPieChart(      
		         "Analysis Budget",   // chart title 
		         dataset,          // data    
		         true,             // include legend   
		         true, 
		         false);
		ChartPanel CP = new ChartPanel(chart);
		panel.add(CP,BorderLayout.CENTER);
		
		JButton btnClose = new JButton("Close");
		btnClose.setForeground(new Color(255, 255, 255));
		btnClose.setBackground(new Color(139, 69, 19));
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAnalysisBudget.dispose();
			}
		});
		btnClose.setBounds(332, 471, 89, 23);
		frmAnalysisBudget.getContentPane().add(btnClose);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Java\\TravellerBudget\\BackgroundImage\\backy007.jpg"));
		lblNewLabel.setBounds(0, 0, 748, 505);
		frmAnalysisBudget.getContentPane().add(lblNewLabel);
		
		
		
		
	}

}

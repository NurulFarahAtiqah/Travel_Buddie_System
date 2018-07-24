package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Controller.HotelManager;
import Model.Hotel;
import Model.Session;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class HotelMenu extends JFrame {

	private JPanel contentPane;

	JScrollPane scrollPane;
	JPanel panel;
	private static ArrayList<File> fImagesCollection = new ArrayList<File>();
	private GridLayout grid_PictureSelect = new GridLayout(Integer.valueOf(fImagesCollection.size())*3, 3,50,50);
	private Session session;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelMenu frame = new HotelMenu("JOHOR", null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HotelMenu(String state, Session session) {
		this.session = session;
		System.out.println("HotelMenu session "+session.getId());
		setVisible(true);
		fImagesCollection = new ArrayList<File>();
		setTitle("HOTEL");
		try {UIManager.setLookAndFeel(new NimbusLookAndFeel());} catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 865, 421);
		contentPane.add(scrollPane);
		
		 panel = new JPanel();
		scrollPane.setViewportView(panel);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(139, 69, 19));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocationView frame = new LocationView(session);
				dispose();
			}
		});
		btnNewButton.setBounds(388, 486, 103, 30);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\Java\\TravellerBudget\\BackgroundImage\\backy007.jpg"));
		lblNewLabel.setBounds(0, 0, 884, 562);
		contentPane.add(lblNewLabel);
		
		HotelManager hotelmanager = new HotelManager();
		try {
			ArrayList<Hotel> listHotel = hotelmanager.viewSelectedHotel(state);
			for(Hotel listHotelTemp : listHotel)
			{
				fImagesCollection.add(new File(listHotelTemp.getPath()));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		viewImagePanel_PictureSelect();
	}
	
	/*
	 * Display Image on Jpanel Scroll [Picture Select]
	 */
	public void viewImagePanel_PictureSelect()
	{

		scrollPane.setOpaque(false);//*
		scrollPane.getViewport().setOpaque(false);//*
		scrollPane.setBorder(null);//*
		
		panel = new JPanel();
		panel.setOpaque(false);//*
		panel.setBorder(null);//*
		panel.setLayout(grid_PictureSelect); 
		scrollPane.setViewportView(panel);
		
		for(File file : fImagesCollection)
		{
			ImageIcon picTempOri = new ImageIcon (file.getAbsolutePath().toString());
			
			Image img = picTempOri.getImage();
			Image newimg = img.getScaledInstance(((150*picTempOri.getIconWidth())/picTempOri.getIconHeight()), 150,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newimg);
			
			JButton picButton = new JButton();
			picButton.setIcon(newIcon);
			String nameTemp = file.getName().toString();
			String[] name = nameTemp.split("\\.");
			picButton.setText(name[0]);
			picButton.setFont(new Font("Times New Roman", Font.BOLD, 19));//*
			picButton.setForeground(Color.BLACK);//*
			picButton.setHorizontalTextPosition(JLabel.CENTER);
			picButton.setVerticalTextPosition(JLabel.BOTTOM);
			picButton.setOpaque(false);
			picButton.setContentAreaFilled(false);
			picButton.setBorderPainted(false);
			picButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
	
					
					String testSplitarg0 = arg0.getSource().toString();
					String[] outputSplitarg0 = testSplitarg0.split("\\,");
					
					String testSplitarg0Text = outputSplitarg0[25];
					String[] outputSplitText= testSplitarg0Text.split("\\=");
					String selectedImageShortName =outputSplitText[1]; 
	
					String fullImageDirectory = ""; 
					for (int i=0 ; i<fImagesCollection.size() ; i++)
					{
						String fileDirectory = fImagesCollection.get(i).getAbsolutePath().toString();
							if(fileDirectory.contains(selectedImageShortName))
			               {
								fullImageDirectory = fileDirectory;
			               }
					}
	
					// pop-up image detail box 
					SelectionView window = new SelectionView(fullImageDirectory,session);
//					boolean deleteImg = popupDetailImageBox_PictureSelect(fullImageDirectory,selectedImageShortName);
//					if (deleteImg== true)
//					{
//						//orderDeleteImg_PictureSelect(selectedImageShortName);
//					}
					
				}
			});
			panel.add(picButton);
			panel.repaint();
			panel.revalidate();
		}
	}
	
	/*
	 * pop-up image detail box [Picture Select]
	 */
	public boolean popupDetailImageBox_PictureSelect(String fullImageDirectory , String selectedImageShortName)
	{
		boolean deleteImage = false;
		
		String directoryImg = fullImageDirectory;
		
		ImageIcon picTempOri = new ImageIcon (directoryImg);
		Image img = picTempOri.getImage();
		Image newimg = img.getScaledInstance(((200*picTempOri.getIconWidth())/picTempOri.getIconHeight()), 200,  java.awt.Image.SCALE_SMOOTH);//Image newimg = img.getScaledInstance(400, 400,  java.awt.Image.SCALE_SMOOTH); //(Length,Height)
		ImageIcon newIcon = new ImageIcon(newimg);
		JLabel lbl = new JLabel(newIcon);
		
		JLabel lblTitle = new JLabel("<html>"+selectedImageShortName+"</html>");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 19));
		JLabel lblDirectory = new JLabel("<html>"+directoryImg+"</html>");
		lblDirectory.setFont(new Font("Times New Roman", Font.BOLD, 19));
		
		final JPanel dataPanel = new JPanel();
		dataPanel.setOpaque(false);
		
		  dataPanel.add(lbl);
		  dataPanel.add(Box.createVerticalStrut(15));
		  JLabel lblPicture= new JLabel("<html>Picture :</html>");
		  lblPicture.setFont(new Font("Times New Roman", Font.PLAIN, 19));
	      dataPanel.add(lblPicture);
	      dataPanel.add(lblTitle);

	      dataPanel.add(Box.createVerticalStrut(15));
		  JLabel lblPath= new JLabel("<html>Path :</html>");
		  lblPath.setFont(new Font("Times New Roman", Font.PLAIN, 19));
	      dataPanel.add(lblPath);
	      dataPanel.add(lblDirectory);
	      dataPanel.add(Box.createVerticalStrut(15));
	      dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

	    Object[] options = {"Back","Remove Picture"};
		int n = JOptionPane.showOptionDialog(
		null,
		dataPanel,
		"Picture",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.PLAIN_MESSAGE,
		null,
		options,
		options[0]);

		if (n==0)
		{
			
			deleteImage = false;
		}
		else if (n==1)
		{
			
			deleteImage = true;
		}
		
		return deleteImage;
	}
	
	
	
}

package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import server.remote.IRemote;
import server.remote.Remote;

import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;


public class ShowDescriptionAdmin implements ActionListener {


		private JFrame frame;
		
		private JPanel bookPanel;
		private JLabel lblImage;
		private JLabel lblTitle;
		private JTextField txtTitle;
		private JLabel lblAuthor;
		private JTextField txtAuthor;
		private JLabel lblCategory;
		private JTextField txtCategory;
		private JLabel lblEdition;
		private JTextField txtEdition;
		private	JLabel lblDescription;
		private JTextPane txtDescription;
		private JLabel lblPrice;
		private JTextField txtPrice;
		private JLabel lblISBN;
		private JTextField txtISBN;
		private JLabel lblRank;
		private JTextField txtRank;
		private JButton btnGoBack;
		private JTable reviewsTable;
		//private ReviewTableModel m;
		private JScrollPane scrollReviews;
		private JPanel reviewPanel;
		private JLabel label;
		private JButton btnRemove;
		
		private JButton btnEditImage;
		private JButton btnEditTitle;
		private JButton btnEditAuthor;
		private JButton btnEditCategory;
		private JButton btnEditEdition;
		private	JButton btnEditDescription;
		private JButton btnEditPrice;
		private JButton btnEditISBN;
		private JButton btnEditRank;
		
		private JButton btnsaveChanges;
		
		private static String email;
		private static String title;
		private static IRemote server;
		
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ShowDescriptionAdmin window = new ShowDescriptionAdmin(title, email);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the application.
		 */
		public ShowDescriptionAdmin(String title, String email) {
			
			// Create and set up the window.
			frame = new JFrame("Book Shop");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// Display the window.
			frame.setBounds(0, 0, 991, 661);
			frame.setVisible(true);
			frame.setBackground(SystemColor.window);
			
			// Initialize the contents of the frame.
			try {
				server = new Remote();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			this.email = email;
			this.title = title;
			initializeShowDescription();
		}
		
		/**
		 * Initialize the contents of the bookSearch JPanel 
		 */
		private void initializeShowDescription(){
			frame.getContentPane().setLayout(new BorderLayout(0, 0));
			bookPanel = new JPanel();
			bookPanel.setBounds(0, 0, 734, 272);
			frame.getContentPane().add(bookPanel, BorderLayout.CENTER);
			bookPanel.setLayout(null);
			
			lblImage = new JLabel("Image");
			lblImage.setBounds(46, 39, 232, 274);
			bookPanel.add(lblImage);
			
			this.btnEditImage = new JButton(" Edit ");
			this.btnEditImage.setOpaque(false);
			this.btnEditImage.setContentAreaFilled(false);
			this.btnEditImage.setBorderPainted(false);
			this.btnEditImage.setBounds(46, 39, 260, 274);
			bookPanel.add(this.btnEditImage);
			this.btnEditImage.addActionListener(this);
			
			lblTitle = new JLabel("Title:");
			lblTitle.setBounds(357, 60, 32, 23);
			bookPanel.add(lblTitle);
			
			lblAuthor = new JLabel("Author:");
			lblAuthor.setBounds(357, 94, 45, 23);
			bookPanel.add(lblAuthor);
			
			lblCategory = new JLabel("Category: ");
			lblCategory.setBounds(357, 128, 111, 23);
			bookPanel.add(lblCategory);
			
			lblEdition = new JLabel("Edition: ");
			lblEdition.setBounds(357, 162, 45, 23);
			bookPanel.add(lblEdition);
			
			lblDescription = new JLabel("Description:  ");
			lblDescription.setBounds(357, 230, 92, 23);
			bookPanel.add(lblDescription);
			
			lblPrice = new JLabel("Price: ");
			lblPrice.setBounds(674, 80, 64, 24);
			bookPanel.add(lblPrice);
			
			lblRank = new JLabel("Rank: ");
			lblRank.setBounds(674, 136, 45, 23);
			bookPanel.add(lblRank);
			
			
			txtTitle = new JTextField(title);
			txtTitle.setEditable(false);
			txtTitle.setBounds(415, 59, 177, 23);
			bookPanel.add(txtTitle);
			txtTitle.setColumns(10);
			
			this.btnEditTitle = new JButton(" Edit ");
			//this.btnEditTitle.setIcon(new ImageIcon(ShowDescriptionAdmin.class.getResource("iconoEditar.png")));
			this.btnEditTitle.setOpaque(false);
			this.btnEditTitle.setContentAreaFilled(false);
			this.btnEditTitle.setBorderPainted(false);
			this.btnEditTitle.setBounds(542, 59, 177, 23);
			bookPanel.add(this.btnEditTitle);
			this.btnEditTitle.addActionListener(this);
			
			try {
				txtAuthor = new JTextField(server.getBookByTitle(title).getAuthor());
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			txtAuthor.setEditable(false);
			txtAuthor.setColumns(10);
			txtAuthor.setBounds(415, 94, 177, 23);
			bookPanel.add(txtAuthor);
			
			this.btnEditAuthor = new JButton(" Edit ");
			//this.btnEditAuthor.setIcon(new ImageIcon(ShowDescriptionAdmin.class.getResource("iconoEditar.png")));
			this.btnEditAuthor.setOpaque(false);
			this.btnEditAuthor.setContentAreaFilled(false);
			this.btnEditAuthor.setBorderPainted(false);
			this.btnEditAuthor.setBounds(542, 94, 177, 23);
			bookPanel.add(this.btnEditAuthor);
			this.btnEditAuthor.addActionListener(this);
			
			try {
				txtCategory = new JTextField(server.getBookByTitle(title).getCategory());
			} catch (RemoteException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
			txtCategory.setEditable(false);
			txtCategory.setColumns(10);
			txtCategory.setBounds(415, 128, 177, 23);
			bookPanel.add(txtCategory);
			
			this.btnEditCategory = new JButton(" Edit ");
			//this.btnEditCategory.setIcon(new ImageIcon(ShowDescriptionAdmin.class.getResource("iconoEditar.png")));
			this.btnEditCategory.setOpaque(false);
			this.btnEditCategory.setContentAreaFilled(false);
			this.btnEditCategory.setBorderPainted(false);
			this.btnEditCategory.setBounds(542, 128, 177, 23);
			bookPanel.add(this.btnEditCategory);
			this.btnEditCategory.addActionListener (this);
			
			try {
				txtEdition = new JTextField(server.getBookByTitle(title).getEdition());
			} catch (RemoteException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			txtEdition.setEditable(false);
			txtEdition.setColumns(10);
			txtEdition.setBounds(415, 162, 177, 23);
			bookPanel.add(txtEdition);
			
			this.btnEditEdition = new JButton(" Edit ");
			//this.btnEditEdition.setIcon(new ImageIcon(ShowDescriptionAdmin.class.getResource("iconoEditar.png")));
			this.btnEditEdition.setOpaque(false);
			this.btnEditEdition.setContentAreaFilled(false);
			this.btnEditEdition.setBorderPainted(false);
			this.btnEditEdition.setBounds(542, 162, 177, 23);
			bookPanel.add(this.btnEditEdition);
			this.btnEditEdition.addActionListener(this);
			
			try {
				txtPrice = new JTextField(""+server.getBookByTitle(title).getPrice());
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			txtPrice.setEditable(false);
			txtPrice.setColumns(10);
			txtPrice.setBounds(716, 80, 92, 23);
			bookPanel.add(txtPrice);
			
			lblISBN = new JLabel("ISBN: ");
			lblISBN.setBounds(357, 196, 45, 23);
			bookPanel.add(lblISBN);
			
			try {
				txtISBN = new JTextField("" + server.getBookByTitle(title).getISBN());
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			txtISBN.setEditable(false);
			txtISBN.setColumns(10);
			txtISBN.setBounds(415, 196, 177, 23);
			bookPanel.add(txtISBN);
			
			this.btnEditISBN = new JButton(" Edit ");
			//this.btnEditISBN.setIcon(new ImageIcon(ShowDescriptionAdmin.class.getResource("iconoEditar.png")));
			this.btnEditISBN.setOpaque(false);
			this.btnEditISBN.setContentAreaFilled(false);
			this.btnEditISBN.setBorderPainted(false);
			this.btnEditISBN.setBounds(542, 196, 177, 23);
			bookPanel.add(this.btnEditISBN);
			this.btnEditISBN.addActionListener(this);
			
			try {
				txtRank = new JTextField("" + server.averageRatingByBook(title)+ " /10");
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			txtRank.setEditable(false);
			txtRank.setColumns(10);
			txtRank.setBounds(716, 136, 92, 23);
			bookPanel.add(txtRank);
			
			this.btnEditRank = new JButton(" Edit ");
			//this.btnEditRank.setIcon(new ImageIcon(ShowDescriptionAdmin.class.getResource("iconoEditar.png")));
			this.btnEditRank.setOpaque(false);
			this.btnEditRank.setContentAreaFilled(false);
			this.btnEditRank.setBorderPainted(false);
			this.btnEditRank.setBounds(800, 136, 92, 23);
			bookPanel.add(this.btnEditRank);
			this.btnEditRank.addActionListener(this);
			
			txtDescription = new JTextPane();
			txtDescription.setEditable(false);
			try {
				txtDescription.setText(server.getBookByTitle(title).getDescription());
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			txtDescription.setBounds(357, 251, 460, 58);
			bookPanel.add(txtDescription);
			
			this.btnEditDescription = new JButton(" Edit ");
			//this.btnEditDescription.setIcon(new ImageIcon(ShowDescriptionAdmin.class.getResource("iconoEditar.png")));
			this.btnEditDescription.setOpaque(false);
			this.btnEditDescription.setContentAreaFilled(false);
			this.btnEditDescription.setBorderPainted(false);
			this.btnEditDescription.setBounds(810, 251, 92, 23);
			bookPanel.add(this.btnEditDescription);
			this.btnEditDescription.addActionListener(this);
			
			
			this.btnEditPrice = new JButton(" Edit ");
			//this.btnEditPrice.setIcon(new ImageIcon(ShowDescriptionAdmin.class.getResource("iconoEditar.png")));
			this.btnEditPrice.setOpaque(false);
			this.btnEditPrice.setContentAreaFilled(false);
			this.btnEditPrice.setBorderPainted(false);
			this.btnEditPrice.setBounds(800, 81, 89, 23);
			bookPanel.add(this.btnEditPrice);
			this.btnEditPrice.addActionListener(this);
			
			reviewPanel = new JPanel();
			reviewPanel.setBounds(0, 337, 975, 285);
			bookPanel.add(reviewPanel);
			reviewPanel.setLayout(null);
			
			label = new JLabel("REVIEWS");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			label.setBounds(10, 5, 68, 17);
			reviewPanel.add(label);
			
			// Create the JTable and the table model 
			TableModel reviewTableModel = new ReviewTableModel(title,server);
			reviewsTable = new JTable(reviewTableModel);
			((client.gui.ReviewTableModel) reviewTableModel).setValues(server);
			//reviewsTable.setBounds(106, 34, 682, 33);
			reviewsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			reviewsTable.setBackground(SystemColor.window);
			
			//Create the scroll pane and add the table to it
			scrollReviews = new JScrollPane(reviewsTable);
			scrollReviews.setEnabled(false);
			scrollReviews.getViewport().setBackground(Color.white);
			
			//Add the scroll pane to this panel.
			scrollReviews.setBounds(106, 100, 682, 80);
			reviewPanel.add(scrollReviews);
			
			btnGoBack = new JButton("Go Back");
			btnGoBack.setBounds(10, 251, 89, 23);
			reviewPanel.add(btnGoBack);
			btnGoBack.addActionListener(new ActionListener() {
							
				@Override
				public void actionPerformed(ActionEvent e) {
					ShowBooksAdmin showBooksAdmin = new ShowBooksAdmin(email);
					frame.dispose();
					frame.revalidate();
					frame.repaint();
				}
			});
			
			btnRemove = new JButton("Remove");
			btnRemove.setBounds(699, 190, 89, 23);
			reviewPanel.add(btnRemove);
			btnRemove.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Borrar data y poner los datos + nuevo libro en Jtable
					int selectedRow = reviewsTable.getSelectedRow();
					try {
						server.deleteReview(server.getBookReviews(title).get(selectedRow).getId_review());
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					
				}
			});
		
			btnsaveChanges = new JButton("Save Changes");
			btnsaveChanges.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					txtTitle.setEditable(false);
					txtAuthor.setEditable(false);
					txtCategory.setEditable(false);
					txtEdition.setEditable(false);
					txtDescription.setEditable(false);
					txtPrice.setEditable(false);
					txtISBN.setEditable(false);
					txtRank.setEditable(false);
					}
			});
			btnsaveChanges.setBounds(700, 251, 120, 23);
			reviewPanel.add(btnsaveChanges);
			
		}
			

		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton o = (JButton) arg0.getSource();
			if(o == btnEditTitle){
				this.txtTitle.setEditable(true);
			}else if(o == btnEditAuthor){
				this.txtAuthor.setEditable(true);
			}else if(o == btnEditCategory){
				this.txtCategory.setEditable(true);
			}else if(o == btnEditEdition){
				this.txtEdition.setEditable(true);
			}else if(o == btnEditISBN){
				this.txtISBN.setEditable(true);
			}else if(o == btnEditDescription){
				this.txtDescription.setEditable(true);
			}else if(o == btnEditPrice){
				this.txtPrice.setEditable(true);
			}else if(o == btnEditRank){
				this.txtRank.setEditable(true);
			}
		}
}
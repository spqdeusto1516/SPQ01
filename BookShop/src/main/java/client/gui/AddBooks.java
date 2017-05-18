package client.gui;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import server.remote.IRemote;
import server.remote.Remote;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

public class AddBooks {

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

	private JButton btnAddBooks;
	IRemote server;
	
	private static int ISBN;
	private static String title;
	private static String category;
	private static String edition;
	private static String author;
	private static double price;
	private static String description;
	private static String img;
	
	private static String email;
	
	private ShowBooksAdmin showBookadmin;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBooks window = new AddBooks(email);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public AddBooks(String email) {
		// Create and set up the window.
		frame = new JFrame("Add Book");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// Display the window.
		frame.setBounds(100, 100, 550, 470);
		frame.setVisible(true);
		frame.setBackground(SystemColor.window);

		// Initialize the contents of the frame.
		try {
			server = new Remote();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.email = email;
		initializeAddBooks();
	}

	/**
	 * Initialize the contents of the bookSearch JPanel 
	 */
	private void initializeAddBooks(){
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		bookPanel = new JPanel();
		bookPanel.setBounds(0, 0, 734, 272);
		frame.getContentPane().add(bookPanel, BorderLayout.CENTER);
		bookPanel.setLayout(null);

		lblTitle = new JLabel("Title:");
		lblTitle.setBounds(25, 30, 32, 23);
		bookPanel.add(lblTitle);

		txtTitle = new JTextField();
		txtTitle.setBounds(100, 30, 150, 23);
		bookPanel.add(txtTitle);
		txtTitle.setColumns(10);
		
		lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(25, 80, 52, 23);
		bookPanel.add(lblAuthor);

		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(100, 80, 150, 23);
		bookPanel.add(txtAuthor);

		lblCategory = new JLabel("Category: ");
		lblCategory.setBounds(25, 130, 72, 23);
		bookPanel.add(lblCategory);

		txtCategory = new JTextField();
		txtCategory.setColumns(10);
		txtCategory.setBounds(100, 130, 150, 23);
		bookPanel.add(txtCategory);

		lblEdition = new JLabel("Edition: ");
		lblEdition.setBounds(25, 180, 92, 23);
		bookPanel.add(lblEdition);

		txtEdition = new JTextField();
		txtEdition.setColumns(10);
		txtEdition.setBounds(100, 180, 150, 23);
		bookPanel.add(txtEdition);

		lblDescription = new JLabel("Description:  ");
		lblDescription.setBounds(25, 230, 112, 23);
		bookPanel.add(lblDescription);

		txtDescription = new JTextPane();
		txtDescription.setBounds(100, 230, 200, 30);
		bookPanel.add(txtDescription);

		lblPrice = new JLabel("Price: ");
		lblPrice.setBounds(25, 280, 132, 23);
		bookPanel.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(100, 280, 150, 23);
		bookPanel.add(txtPrice);
		//price = Double.valueOf(txtPrice.getText());
		price = 0;

		lblISBN = new JLabel("ISBN: ");
		lblISBN.setBounds(25, 330, 152, 23);
		bookPanel.add(lblISBN);

		txtISBN = new JTextField();
		txtISBN.setColumns(10);
		txtISBN.setBounds(100, 330, 150, 23);
		bookPanel.add(txtISBN);
		//ISBN = Integer.parseInt(txtISBN.getText());
		ISBN = 5;
/*
		lblImage = new JLabel("Image");
		lblImage.setBounds(300, 140, 150, 23);
		bookPanel.add(lblImage);
*/
		img = null;
		
		btnAddBooks = new JButton("ADD BOOK");
		btnAddBooks.setBounds(350, 350, 150, 23);
		bookPanel.add(btnAddBooks);
		btnAddBooks.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				title = txtTitle.getText();
				author = txtAuthor.getText();
				category = txtCategory.getText();
				edition = txtEdition.getText();
				description = txtDescription.getText();
				//price
				//ISBN
				//img
				
				try {
					server.addBook(ISBN, title, category, edition, author, price, description, img);
					showBookadmin = new ShowBooksAdmin(email);
					frame.dispose();
					frame.revalidate();
					frame.repaint();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
			}
		});








	}
}

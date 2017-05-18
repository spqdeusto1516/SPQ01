package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import server.remote.IRemote;
import server.remote.Remote;

public class ShowBooksAdmin {
	private ShowDescriptionAdmin showDescriptionAdmin;
	private JFrame frame;

	private JPanel bookSearch;
	private JLabel lblConnect;
	private JLabel lblSearch;
	private String textuser;
	private JComboBox cmbSearch;
	private String cmbSearchSelection;
	private JPanel book;
	private JTextField textSearchUser;
	private JButton btnSearch;
	private JTable listOfBooks;
	TableModel bookTableModel;
	private JScrollPane scrollListBooks;
	private JButton btnRefresh;
	private JButton btnAddBook;
	private JButton btnDeleteBook;
	private JButton btnLogOut;
	
	private static String email;
	private LogIn logIn;
	private AddBooks addBook;
	IRemote server;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowBooksAdmin window = new ShowBooksAdmin(email);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShowBooksAdmin(String email) {
		
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
		
		initializebookSearchAdmin();
	}
	
	public ShowBooksAdmin() {
		
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
		initializebookSearchAdmin();
	}
	
	/**
	 * Initialize the contents of the bookSearch JPanel 
	 */
	private void initializebookSearchAdmin(){
		
		// initialization of items used
		String[] Menu = {"Title", "Author", "ISBN"};

		// Beginning of Book Search JPanel
		bookSearch = new JPanel();
		bookSearch.setBackground(SystemColor.window);
		bookSearch.setLayout(new GridBagLayout());
		frame.getContentPane().add(bookSearch, BorderLayout.CENTER);
		//bookSearch.setVisible(false);
		
		btnAddBook = new JButton("Add book");
		btnAddBook.setVerticalAlignment(SwingConstants.TOP);
		btnAddBook.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		btnAddBook.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_btnAddABook = new GridBagConstraints();
		gbc_btnAddABook.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddABook.gridx = 4;
		gbc_btnAddABook.gridy = 0;
		bookSearch.add(btnAddBook, gbc_btnAddABook);
		btnAddBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addBook = new AddBooks(email);
				frame.dispose();
				frame.revalidate();
				frame.repaint();
			}
		});
		
		// JLabel component about search message
		lblSearch = new JLabel("Book Search");
		lblSearch.setBackground(SystemColor.window);
		lblSearch.setForeground(new Color(0, 0, 0));
		lblSearch.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.fill= GridBagConstraints.BOTH;
		gbc_lblSearch.gridx = 1;
		gbc_lblSearch.gridy = 1;
		bookSearch.add(lblSearch, gbc_lblSearch);
				
		// JComboBox component about choosing according what is the search
		cmbSearch = new JComboBox<Object>(Menu);
		cmbSearch.setBackground(SystemColor.window);
		cmbSearch.setForeground(new Color(0, 0, 0));
		cmbSearch.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		GridBagConstraints gbc_cmbSearch = new GridBagConstraints();
		gbc_cmbSearch.insets = new Insets(15, 0, 5, 5);
		gbc_cmbSearch.gridx = 1;
		gbc_cmbSearch.gridy = 2;
		gbc_cmbSearch.fill= GridBagConstraints.BOTH;
		bookSearch.add(cmbSearch, gbc_cmbSearch);
		
		// JTextField component about the text that is going to be searched
		textSearchUser = new JTextField("Enter text here!");
		textSearchUser.setBackground(SystemColor.window);
		textSearchUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cmbSearchSelection = (String) cmbSearch.getSelectedItem();
				textuser = textSearchUser.getText();
				if (textuser.equals("Enter text here!")) textSearchUser.setText("");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String textuser = textSearchUser.getText();
				if (textuser.equals("")) textSearchUser.setText("Enter text here!");
				
			}
		});
		
		textSearchUser.setForeground(SystemColor.textInactiveText);
		textSearchUser.setColumns(20);
		textSearchUser.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		GridBagConstraints gbc_textSearchUser = new GridBagConstraints();
		gbc_textSearchUser.insets = new Insets(15, 0, 5, 5);
		gbc_textSearchUser.gridx = 2;
		gbc_textSearchUser.gridy = 2;
		gbc_textSearchUser.fill= GridBagConstraints.BOTH;
		bookSearch.add(textSearchUser, gbc_textSearchUser);

		// JTable with all the available books
		
		// Create the JTable and the table model 
		bookTableModel = new BookTableModel();
		((client.gui.BookTableModel) bookTableModel).setValues(server);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(bookTableModel);
		listOfBooks = new JTable(bookTableModel);
		listOfBooks.setRowHeight(40);
		listOfBooks.setRowSorter(sorter);
		listOfBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOfBooks.setBackground(SystemColor.window);
		listOfBooks.setFont(new Font("Yu Gothic", Font.PLAIN, 20));
		
		//Create the scroll pane and add the table to it
		scrollListBooks = new JScrollPane(listOfBooks);
		scrollListBooks.setEnabled(false);
		scrollListBooks.getViewport().setBackground(Color.white);
		Dimension d = listOfBooks.getPreferredSize();
		scrollListBooks.setPreferredSize(
		    new Dimension(d.width,listOfBooks.getRowHeight()*listOfBooks.getRowCount()+25));
		// Mouse Listener -> go to a specific book
		listOfBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					//Select a row to see the description window
					listOfBooks.getSelectedRow();
					String title = (String) bookTableModel.getValueAt(listOfBooks.getSelectedRow(), 0);
					showDescriptionAdmin = new ShowDescriptionAdmin(title, email);
					frame.dispose();
					frame.revalidate();
					frame.repaint();	
				}
			}
		});
		
		//Add the scroll pane to this panel.
		GridBagConstraints gbc_scrollListBooks = new GridBagConstraints();
		gbc_scrollListBooks.gridwidth = 2;
		gbc_scrollListBooks.insets = new Insets(15, 0, 5, 5);
		gbc_scrollListBooks.gridx = 1;
		gbc_scrollListBooks.gridy = 3;
		gbc_scrollListBooks.fill = GridBagConstraints.HORIZONTAL;
		bookSearch.add(scrollListBooks, gbc_scrollListBooks);
		
		// Create JButton for search
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		btnSearch.setBackground(new Color(95, 158, 160));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String searchText = textSearchUser.getText();
				String type = (String) cmbSearch.getSelectedItem();
				if (searchText.length() != 0){
					if(type == "Title"){
						RowFilter rowFilter = RowFilter.regexFilter(searchText, 0);
						((DefaultRowSorter<TableModel, Integer>) listOfBooks.getRowSorter()).setRowFilter(rowFilter);
					}else if (type == "Author"){
						RowFilter rowFilter = RowFilter.regexFilter(searchText, 1);
						((DefaultRowSorter<TableModel, Integer>) listOfBooks.getRowSorter()).setRowFilter(rowFilter);
					}else if(type == "ISBN"){
						RowFilter rowFilter = RowFilter.regexFilter(searchText, 2);
						((DefaultRowSorter<TableModel, Integer>) listOfBooks.getRowSorter()).setRowFilter(rowFilter);	
					}
				}else{
					sorter.setRowFilter(null);
				}			
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(15, 0, 5, 0);
		gbc_btnSearch.gridx = 3;
		gbc_btnSearch.gridy = 2;
		gbc_btnSearch.fill= GridBagConstraints.BOTH;
		//Image imgSearch = new ImageIcon(this.getClass().getResource("search.png")).getImage();
		//btnSearch.setIcon( (Icon) new ImageIcon(imgSearch));
		bookSearch.add(btnSearch, gbc_btnSearch);		
		
		// Create JButton for refreshing data of JTable
		btnRefresh = new JButton("Refresh");
		btnRefresh.setVerticalAlignment(SwingConstants.TOP);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorter.setRowFilter(null);
				textSearchUser.setText("");	
			}
		});
		btnRefresh.setBackground(UIManager.getColor("Button.light"));
		btnRefresh.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
		gbc_btnRefresh.insets = new Insets(0, 0, 5, 0);
		gbc_btnRefresh.gridx = 3;
		gbc_btnRefresh.gridy = 3;
		gbc_btnRefresh.fill= GridBagConstraints.HORIZONTAL;
		bookSearch.add(btnRefresh, gbc_btnRefresh);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.setVerticalAlignment(SwingConstants.TOP);
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logIn = new LogIn();
				frame.dispose();
				frame.revalidate();
				frame.repaint();
			}
		});
		
		btnDeleteBook = new JButton("Delete book");
		btnDeleteBook.setVerticalAlignment(SwingConstants.TOP);
		btnDeleteBook.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		btnDeleteBook.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_btnDeleteABook = new GridBagConstraints();
		gbc_btnDeleteABook.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteABook.gridx = 3;
		gbc_btnDeleteABook.gridy = 4;
		bookSearch.add(btnDeleteBook, gbc_btnDeleteABook);
		btnDeleteBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Borrar data y poner los datos + nuevo libro en Jtable
				int selectedRow = listOfBooks.getSelectedRow();
				try {
					server.deleteBook(Integer.parseInt((String) bookTableModel.getValueAt(selectedRow, 2)));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				ShowBooksAdmin refresh = new ShowBooksAdmin(email);
				
			}
		});
		
		btnLogOut.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		btnLogOut.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_btnLogOut = new GridBagConstraints();
		gbc_btnLogOut.gridx = 3;
		gbc_btnLogOut.gridy = 5;
		bookSearch.add(btnLogOut, gbc_btnLogOut);
		
	}
}

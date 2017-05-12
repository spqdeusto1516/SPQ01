package client.gui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import server.data.Book;
import server.remote.IRemote;
import server.remote.Remote;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

public class ShowBooks {

	private ShowDescription showDescription;
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
	private JScrollPane scrollListBooks;
	private JButton btnRefresh;
	private JButton btnLogOut;
	
	private static boolean role;
	private LogIn logIn;
	IRemote server;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowBooks window = new ShowBooks(role);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShowBooks(boolean role) {
		
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
		this.role = role;
		initializebookSearch(role);
	}
	
	/**
	 * Initialize the contents of the bookSearch JPanel 
	 */
	private void initializebookSearch(boolean role){
		
		// initialization of items used
		String[] Menu = {"Title", "Author", "ISBN"};

		// Beginning of Book Search JPanel
		bookSearch = new JPanel();
		bookSearch.setBackground(SystemColor.window);
		bookSearch.setLayout(new GridBagLayout());
		frame.getContentPane().add(bookSearch, BorderLayout.CENTER);
		//bookSearch.setVisible(false);
		
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
		TableModel BookTableModel = new BookTableModel();
		((client.gui.BookTableModel) BookTableModel).setValues(server);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(BookTableModel);
		listOfBooks = new JTable(BookTableModel);
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
//		// Mouse Listener -> go to a specific book
		listOfBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Select a row to see the description window
				listOfBooks.getSelectedRow();
				String title = (String) BookTableModel.getValueAt(listOfBooks.getSelectedRow(), 0);
				
				if (role == false){  //User
					showDescription = new ShowDescription(title);
					frame.dispose();
					frame.revalidate();
					frame.repaint();
				}else{  //true --> admin
					//TODO coger un book y pasarselo a la ventana para q lo muestre
					//showDescriptionAdmin = new ShowDescriptionAdmin();
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
				//TODO
				if (searchText.length() == 0){
					sorter.setRowFilter(null);
				}
				else{
					sorter.setRowFilter(RowFilter.regexFilter(searchText));
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
		btnLogOut.setFont(new Font("Yu Gothic", Font.PLAIN, 25));
		btnLogOut.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_btnLogOut = new GridBagConstraints();
		gbc_btnLogOut.gridx = 3;
		gbc_btnLogOut.gridy = 5;
		bookSearch.add(btnLogOut, gbc_btnLogOut);
		
	}
}

class BookTableModel  extends AbstractTableModel {

	String[] columnNames = {"TITLE", "AUTHOR", "ISBN", "RATING","PRICE"};
	Object[][] data = null;

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int col) {
		 return columnNames[col];
	}

	@Override
	public int getRowCount() {
		 return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
        fireTableCellUpdated(row, col);
	}
	
	public void setValues(IRemote server) {
		try {
			if(server.showBooksInStore().size()!= 0){
				data = new String[server.showBooksInStore().size()][5];
			}
			else{
				data = new String[0][4];
			}
			for (int i = 0; i < server.showBooksInStore().size(); i++)
			{
				data[i][0] = server.showBooksInStore().get(i).getTitle();
				data[i][1] = server.showBooksInStore().get(i).getAuthor();
				data[i][2] = "" + server.showBooksInStore().get(i).getISBN();
				data[i][3] = "" + server.averageRatingByBook(server.showBooksInStore().get(i).getTitle()) + " /10";
				data[i][4] = "" + server.showBooksInStore().get(i).getPrice() + " €";
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
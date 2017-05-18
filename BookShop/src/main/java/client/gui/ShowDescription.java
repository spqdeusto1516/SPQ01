package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import server.data.Review;
import server.data.User;
import server.remote.IRemote;
import server.remote.Remote;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;


public class ShowDescription {


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
		private JButton btnBuy;
		private JButton btnGoBack;
		private JTable reviewsTable;
		private JScrollPane scrollReviews;
		private JPanel reviewPanel;
		private JLabel label;
		private JTextPane txtReview;
		private JButton btnSend;
		private JComboBox<Integer> cmbRate;
		private JLabel lCmbRate;
		
		
		private String email;
		private static String title;
		private static IRemote server;
	
		/**
		 * Launch the application.
		 */
//		public static void main(String[] args) {
//			EventQueue.invokeLater(new Runnable() {
//				public void run() {
//					try {
//						ShowDescription window = new ShowDescription(new User("test", "test", false));
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
//			
//		}

		/**
		 * Create the application.
		 * @param user 
		 * @param user 
		 * @throws RemoteException 
		 */
		public ShowDescription(String title, String email) throws RemoteException {
			
			
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
		 * @throws RemoteException 
		 */
		private void initializeShowDescription() throws RemoteException{
			frame.getContentPane().setLayout(new BorderLayout(0, 0));
			bookPanel = new JPanel();
			bookPanel.setBounds(0, 0, 734, 272);
			frame.getContentPane().add(bookPanel, BorderLayout.CENTER);
			bookPanel.setLayout(null);
			
			lblImage = new JLabel("Image");
			lblImage.setBounds(46, 39, 232, 274);
			bookPanel.add(lblImage);
			
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
			
			txtAuthor = new JTextField(server.getBookByTitle(title).getAuthor());
			txtAuthor.setEditable(false);
			txtAuthor.setColumns(10);
			txtAuthor.setBounds(415, 94, 177, 23);
			bookPanel.add(txtAuthor);
			
			txtCategory = new JTextField(server.getBookByTitle(title).getCategory());
			txtCategory.setEditable(false);
			txtCategory.setColumns(10);
			txtCategory.setBounds(415, 128, 177, 23);
			bookPanel.add(txtCategory);
			
			txtEdition = new JTextField(server.getBookByTitle(title).getEdition());
			txtEdition.setEditable(false);
			txtEdition.setColumns(10);
			txtEdition.setBounds(415, 162, 177, 23);
			bookPanel.add(txtEdition);
			
			txtPrice = new JTextField(""+server.getBookByTitle(title).getPrice());
			txtPrice.setEditable(false);
			txtPrice.setColumns(10);
			txtPrice.setBounds(716, 80, 92, 23);
			bookPanel.add(txtPrice);
			
			lblISBN = new JLabel("ISBN: ");
			lblISBN.setBounds(357, 196, 45, 23);
			bookPanel.add(lblISBN);
			
			txtISBN = new JTextField("" + server.getBookByTitle(title).getISBN());
			txtISBN.setEditable(false);
			txtISBN.setColumns(10);
			txtISBN.setBounds(415, 196, 177, 23);
			bookPanel.add(txtISBN);
			
			txtRank = new JTextField("" + server.averageRatingByBook(title)+ " /10");
			txtRank.setEditable(false);
			txtRank.setColumns(10);
			txtRank.setBounds(716, 136, 92, 23);
			bookPanel.add(txtRank);
			
			txtDescription = new JTextPane();
			txtDescription.setEditable(false);
			txtDescription.setText(server.getBookByTitle(title).getDescription());
			txtDescription.setBounds(357, 251, 460, 58);
			bookPanel.add(txtDescription);
			
			btnBuy = new JButton("BUY");
			btnBuy.setBounds(818, 81, 89, 23);
			bookPanel.add(btnBuy);
			
			reviewPanel = new JPanel();
			reviewPanel.setBounds(0, 337, 975, 285);
			bookPanel.add(reviewPanel);
			reviewPanel.setLayout(null);
			
			label = new JLabel("REVIEWS");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			label.setBounds(10, 5, 68, 17);
			reviewPanel.add(label);
			
			txtReview = new JTextPane();
			txtReview.setBounds(106, 34, 682, 33);
			reviewPanel.add(txtReview);
			
			btnSend = new JButton("Send");
			btnSend.setBounds(780, 70, 89, 23);
			btnSend.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						server.addReview(server.getBookByTitle(title),new Review(txtReview.getText(), cmbRate.getSelectedIndex()),server.getUser(email));
						JOptionPane.showMessageDialog(null, "Your comment and rate SEND!", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
						txtReview.setText("");
						cmbRate.setSelectedIndex(0);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
			reviewPanel.add(btnSend);
			
			lCmbRate = new JLabel("Rate:");
			lCmbRate.setFont(new Font("Tahoma", Font.BOLD, 14));
			lCmbRate.setBounds(699, 73, 68, 17);
			reviewPanel.add(lCmbRate);
			
			cmbRate = new JComboBox<Integer>();
			for(int i=0;i<11;i++){
				cmbRate.addItem(i);
			}
			cmbRate.setBounds(735,70,40,25);
			reviewPanel.add(cmbRate);
			
			// Create the JTable and the table model 
			TableModel reviewTableModel = new ReviewTableModel(title, server);
			reviewsTable = new JTable(reviewTableModel);
			((client.gui.ReviewTableModel) reviewTableModel).setValues(server);
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
					ShowBooks showBooks = new ShowBooks(email);
					frame.dispose();
					frame.revalidate();
					frame.repaint();
				}
			});
			
		}
}

class ReviewTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String[] columnNames = {"USER", "COMMENT"};
	Object[][] data = null;
	
	String title;

	public ReviewTableModel(String title, IRemote server) {
		this.title=title;
	}

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
			if(server.getBookReviews(title).size()!= 0){
				data = new String[server.getBookReviews(title).size()][2];
			}
			else{
				data = new String[0][2];
			}
			for (int i = 0; i < server.getBookReviews(title).size(); i++)
			{
				data[i][0] = server.getBookReviews(title).get(i).getUser().getEmail();
				data[i][1] = server.getBookReviews(title).get(i).getComment();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}



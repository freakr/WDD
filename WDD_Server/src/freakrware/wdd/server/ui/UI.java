package freakrware.wdd.server.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import freakrware.wdd.server.core.ThreadPooledServer;
import freakrware.wdd.server.resources.DataBase;

public class UI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6639347605033934529L;
	@SuppressWarnings("serial")
	private static final Vector<String> COLUMN_IDENTIFIERS = new Vector<String>() {
        {
        	add("ActionID");
            add("SentFrom");
            add("SentTo");
            add("SentTrue");
            add("Message");
            add("Date");
            
        }
	};
	public ThreadPooledServer server;
	public JPanel main;
	private JMenuBar menubar;
	private JMenuItem endItem;
	private JMenu menu;
	private static JTable table;
	private DefaultTableModel MyTableModel;
	private int frheight = 550;
	private int frwidth = 1000;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public UI(){
		super("WDD-Server");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setPreferredSize(new Dimension(frwidth,frheight));
		setResizable(false);
		Point dim2 = new Point((int)(dim.getWidth()/2-frwidth/2),(int)(dim.getHeight()/2-frheight/2));
		setLocation(dim2);
		setLayout(new BorderLayout());
		Border bo = new LineBorder(Color.blue);
        menubar = new JMenuBar();
        menu = new JMenu("Menu");
        JSeparator sep = new JSeparator();
        endItem = new JMenuItem();
        endItem.setText("Exit");
        menu.add(sep);
        menu.add(endItem);
        menu.setBorder(bo);
        menubar.add(menu);
        main = new JPanel();
        MyTableModel = new DefaultTableModel();
        MyTableModel.setColumnIdentifiers(COLUMN_IDENTIFIERS);
        table = new JTable(MyTableModel);
        table.setEnabled(true);
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setBorder(bo);
        table.setPreferredSize(new Dimension(frwidth-25,frheight-50));
        main.setBorder(bo);
        main.setPreferredSize(new Dimension(frwidth-6,frheight-(frheight-50)));
        setJMenuBar(menubar);
        getContentPane().add(main, BorderLayout.LINE_START);
		getContentPane().add(new JScrollPane(table), BorderLayout.SOUTH);
		refresh_ui();
		setVisible(true);
		
        endItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	System.exit(EXIT_ON_CLOSE);
            }
        });
	}
	public void refresh_ui(){
		refreshTableData();
		reload();
		pack();
        
	}
	private void refreshTableData(){
        Vector results = new DataBase().get_messageboard();
        MyTableModel.setDataVector(results, COLUMN_IDENTIFIERS);
        MyTableModel.fireTableDataChanged();
	}
	private void reload() {
		 
		 final int V1 = 200,V2 = 80,V3 = 376,V4 = 150;
		 
		 	table.getColumnModel().getColumn(0).setPreferredWidth(V2);
	        table.getColumnModel().getColumn(1).setPreferredWidth(V4);
	        table.getColumnModel().getColumn(2).setPreferredWidth(V4);
	        table.getColumnModel().getColumn(3).setPreferredWidth(V2);
	        table.getColumnModel().getColumn(4).setPreferredWidth(V3);
	        table.getColumnModel().getColumn(5).setPreferredWidth(V1);
	        table.getColumnModel().getColumn(3).setCellRenderer(table.getDefaultRenderer(Boolean.class));
	        //table.getColumn(table.getColumnName(0)).setCellRenderer(new JRendererLeft());
	        //table.getColumn(table.getColumnName(1)).setCellRenderer(new JRenderer());
	        //table.getColumn(table.getColumnName(2)).setCellRenderer(new JRenderer());
	        //table.getColumn(table.getColumnName(3)).setCellRenderer(new JRenderer());
	    }

}

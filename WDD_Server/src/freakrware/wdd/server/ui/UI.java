package freakrware.wdd.server.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import freakrware.wdd.server.core.ThreadPooledServer;

public class UI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6639347605033934529L;
	public ThreadPooledServer server;
	public JPanel main;
	private JToolBar toolbar;

	

	public UI(){
		super("UI");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(1000,800);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        toolbar = new JToolBar();
        main = new JPanel();
       
        main.add(toolbar);
        getContentPane().add(toolbar, BorderLayout.NORTH);
		getContentPane().add(main, BorderLayout.LINE_START);
		pack();
        main.setVisible(true);
        
		
	}

}

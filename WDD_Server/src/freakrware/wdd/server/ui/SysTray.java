package freakrware.wdd.server.ui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.SocketAddress;

import freakrware.wdd.server.core.ThreadPooledServer;
import freakrware.wdd.server.resources.DataBase;
import freakrware.wdd.server.resources.Interfaces;



public class SysTray implements Interfaces{

	TrayIcon trayIcon = null;
	final static Image IMAGE_START = set_image("A");
	ThreadPooledServer server;
	public SocketAddress clientip;
	public boolean uiclosed = false;
	private DataBase DB;
	private UI ui;
	
	public SysTray(ThreadPooledServer server, DataBase DB, UI ui) {
		this.server = server ;
		this.DB = DB;
		this.ui = ui;
	}
	public void start() {
	
	
    if (SystemTray.isSupported()) {
        SystemTray tray = SystemTray.getSystemTray();
        ActionListener beenden = new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		server.stop();
                System.exit(0);
        	}

        };
        ActionListener open = new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		if(!ui.isVisible()){
        			ui.setVisible(true);
        		}
        	}

        };
        ActionListener serverstart = new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		
        		if (server.isStopped()){
        			server = new ThreadPooledServer(PORT,DB);
        			System.out.println("Starting Server");
        			server.tray = SysTray.this;
        			update("A");
        			new Thread(server).start();
        		}
        		else{
        			System.out.println("Server is already running");
        		}
            }

        };
        ActionListener serverstop = new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		System.out.println("Stopping Server");
        		update("I");
        		server.stop();
        		
            }

        };
        PopupMenu popup = new PopupMenu();
        MenuItem sstopItem = new MenuItem();
        sstopItem.addActionListener(serverstop);
        sstopItem.setLabel("Server-Stop");
        MenuItem openItem = new MenuItem();
        openItem.addActionListener(open);
        openItem.setLabel("Open");
        MenuItem sstartItem = new MenuItem();
        sstartItem.addActionListener(serverstart);
        sstartItem.setLabel("Server-(Re)-Start");
        MenuItem endItem = new MenuItem();
        endItem.addActionListener(beenden);
        endItem.setLabel("Exit");
        popup.add(openItem);
        popup.add(sstopItem);
        popup.add(sstartItem);
        popup.add(endItem);
        trayIcon = new TrayIcon(IMAGE_START, "WDD_Server", popup);
        trayIcon.addActionListener(beenden);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println(e);
        }
    } else {
    }
 }
	
	public void update(String status){
		if (trayIcon != null) {
			
			BufferedImage image = set_image(status);
			trayIcon.setImageAutoSize(true);
			trayIcon.setImage(image);
			if (status.equals("C")){
				trayIcon.setToolTip("WDD_Server : linked to IP = "+String.valueOf(clientip));
			}else
			{
			trayIcon.setToolTip("WDD_Server");
			}
		}
	}
	private static BufferedImage set_image(String status){
		
		BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);  
			Graphics2D g2d = image.createGraphics();
			Font font = g2d.getFont();  
			font = font.deriveFont(Font.BOLD,18.0f); // or any other size  
			g2d.setFont(font);
			g2d.setColor(Color.GREEN);
			g2d.drawString(status,3, 15);
			g2d.dispose(); 
		
		return image;
	}
	
}

package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

@SuppressWarnings({ "serial", "unused" })
public class MainFrame extends JFrame 
{
	private DirectoryPanel m_directoryPanel;
	private BasketPanel m_basketPanel;
	private ControlPanel m_controlPanel;
	private EnrollPanel m_enrollTable;
	private JPanel m_jPanel1, m_jPanel2, m_jPanel3;
	private JLabel m_jLabel1, m_jLabel2;

	


	public MainFrame(String text) throws IOException {
		EtchedBorder eborder =  new EtchedBorder();
		// Set Attribute
		this.setLayout(new BorderLayout());
		this.setTitle("명지대학교");
		this.setLocation(600, 200);
		this.setSize(800, 530);
		this.setVisible(true);
		this.setResizable(false);
		// Set Panel to contain components
        this.m_jPanel1 = new JPanel();
        this.m_jPanel2 = new JPanel();	
		this.m_jPanel3 = new JPanel();
		this.m_jPanel3.setLayout(new BorderLayout());
		this.m_directoryPanel = new DirectoryPanel(text);
		this.m_controlPanel = new ControlPanel(text);
		this.m_enrollTable = new EnrollPanel(text);
		this.m_basketPanel = new BasketPanel(text);
		
		
		this.m_jPanel3.add(this.m_basketPanel,"North");		
		this.m_jPanel3.add(this.m_controlPanel,"Center");		
		this.m_jPanel3.add(this.m_enrollTable, "South");
		
		// Make a border around directoryPanel
		this.m_directoryPanel.setBorder(eborder);
		this.add(this.m_directoryPanel,"West");
		this.add(this.m_jPanel3,"East");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
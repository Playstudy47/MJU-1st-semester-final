package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class EnrollPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	private static EnrollTable m_enrollTable;
	private JButton m_jButton1, m_jButton2;
	private static String m_userId;
	private static String m_currentDate;
	private static JLabel m_jLabel;
	private JPanel m_jpanel;
	
	public EnrollPanel(String text) throws IOException 
	{
		m_userId = text;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
		Calendar time = Calendar.getInstance();
				
		m_currentDate = format.format(time.getTime());
				
		
		EtchedBorder eborder =  new EtchedBorder();
		EnrollPanel.m_enrollTable = EnrollTable.GetInstance();
		EnrollPanel.m_jLabel = new JLabel("<등록 강의>                                           " + EnrollPanel.m_enrollTable.returnCredit(text) + "/21   "  + m_currentDate ); 
		this.m_jButton1 = new JButton("선택 삭제");
		this.m_jButton2 = new JButton("전체 삭제");
		this.m_jpanel = new JPanel();
		
		this.setLayout(new BorderLayout()); 
		this.m_jpanel.setLayout(new GridLayout(2, 1));
		EnrollPanel.m_enrollTable.refresh(text);
		
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setPreferredSize(new Dimension(300, 200));
		scrollpane.setViewportView(EnrollPanel.m_enrollTable);

		EnrollPanel.m_jLabel.setBorder(eborder);
		
		this.m_jpanel.add(m_jButton1);
		m_jButton1.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
				try 
				{
					m_enrollTable.delEnroll(m_userId);
					refreshCredits();
					m_enrollTable.refresh(m_userId);
				}
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				}
			}
		});
		m_jButton1.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				m_jButton1.setBackground(Color.WHITE);
				}
				public void mouseExited(java.awt.event.MouseEvent evt) 
				{
				m_jButton1.setBackground(UIManager.getColor("control"));
				}
		});
		
		this.m_jpanel.add(m_jButton2);
		m_jButton2.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
		        PrintWriter pw = null;
				try {
					pw = new PrintWriter("../LMS/data/" + m_userId + "enroll");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        pw.write("");
		        pw.close();
				try {
					refreshCredits();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					m_enrollTable.refresh(m_userId);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		m_jButton2.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				m_jButton2.setBackground(Color.WHITE);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				m_jButton2.setBackground(UIManager.getColor("control"));
			}
		});
		
		this.add(m_jpanel, "East");
		this.add(EnrollPanel.m_jLabel,"South");
		this.add(scrollpane,"Center");
	}
	
	public static void refreshCredits() throws FileNotFoundException
	{
		m_jLabel.setText("<등록 강의>                                           " + m_enrollTable.returnCredit(m_userId) + "/21   "  + m_currentDate);
	}
	
}

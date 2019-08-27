package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class DirectoryPanel extends JPanel 
{

	private DirectoryList m_campusList;
	private DirectoryList m_collegeList;
	private DirectoryList m_departmentList;
	private LectureTable m_lectureTable;
	private BasketTable m_basketTable;
	private EnrollTable m_enrollTable;
	private JButton m_jButton1, m_jButton2, m_jButton3;
	private JPanel m_jPanel1, m_jPanel2, m_jPanel3;
	private ListSelectionHandler m_listSelectionHandler;
	private String m_userId;

	public DirectoryPanel(String text) throws IOException 
	{
		EtchedBorder eborder =  new EtchedBorder();
		m_userId = text;
		this.m_jPanel1 = new JPanel();
		this.m_jPanel2 = new JPanel();
		this.m_jPanel3 = new JPanel();
		this.m_jButton1 = new JButton("담기");
		this.m_jButton2 = new JButton("신청");
		this.m_jButton3 = new JButton("저장");
		this.m_listSelectionHandler = new ListSelectionHandler();
		this.m_lectureTable = new LectureTable(text);
		this.m_basketTable = BasketTable.GetInstance();
		this.m_enrollTable = EnrollTable.GetInstance();
		
		JScrollPane scrollpane = new JScrollPane();
		this.m_campusList = new DirectoryList(this.m_listSelectionHandler);
		scrollpane.setViewportView(this.m_campusList);
		m_jPanel1.add(scrollpane);

		scrollpane = new JScrollPane();
		this.m_collegeList = new DirectoryList(this.m_listSelectionHandler);
		scrollpane.setViewportView(this.m_collegeList);
		m_jPanel1.add(scrollpane);

		scrollpane = new JScrollPane();
		this.m_departmentList = new DirectoryList(this.m_listSelectionHandler);
		scrollpane.setViewportView(this.m_departmentList);
		m_jPanel1.add(scrollpane);
		
		setLayout(new GridLayout(3,2));
		scrollpane = new JScrollPane();
		this.m_lectureTable = new LectureTable(m_userId);
		scrollpane.setPreferredSize(new Dimension(370, 150));
		scrollpane.setViewportView(this.m_lectureTable);
		scrollpane.setBorder(eborder);
		m_jPanel2.add(scrollpane);
		m_jPanel3.setLayout(new GridLayout(1,3));
		m_jPanel3.setBorder(eborder);
		m_jPanel3.add(m_jButton1);
		m_jButton1.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
				try {
					m_lectureTable.addBasket(m_userId);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					BasketPanel.refreshCredits();
					m_basketTable.refresh(m_userId);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			

		});
		
		m_jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				m_jButton1.setBackground(Color.WHITE);
				}
				public void mouseExited(java.awt.event.MouseEvent evt) {
				m_jButton1.setBackground(UIManager.getColor("control"));
				}
				});
		
		m_jPanel3.add(m_jButton2);
		m_jButton2.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
				try {
					m_lectureTable.enroll(m_userId);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					EnrollPanel.refreshCredits();
					m_enrollTable.refresh(m_userId);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		m_jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				m_jButton2.setBackground(Color.WHITE);
				}
				public void mouseExited(java.awt.event.MouseEvent evt) {
				m_jButton2.setBackground(UIManager.getColor("control"));
				}
				});
		m_jButton3.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
				 EventQueue.invokeLater(new Runnable() 
				 { 
				       public void run() 
				       { 
				    	   setVisible(false);
				    	   try 
				    	   {
				    		   new LoginFrame();
				    	   } 
				    	   catch (IOException e) 
				    	   {
				    		   e.printStackTrace();
				    	   }
				       } 
				 }); 

			}
			

		});
		
		m_jButton3.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				m_jButton3.setBackground(Color.WHITE);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				m_jButton3.setBackground(UIManager.getColor("control"));
			}
		});
		setLayout(new GridLayout(3,1));
		
		this.add(m_jPanel1);
		this.add(m_jPanel2);
		this.add(m_jPanel3);		
		this.refresh(null);
	}

	public void refresh(Object source) throws IOException 
	{
		if (source == null) 
		{
			this.m_campusList.refresh("root");	
		}
		else if (source == m_campusList) 
		{
			String fileName = m_campusList.getSelectedFileName();
			fileName = this.m_collegeList.refresh(fileName);
			
		}
		else if (source == m_collegeList) 
		{
			String fileName = this.m_collegeList.getSelectedFileName();
			fileName = this.m_departmentList.refresh(fileName);
			
		}
		else if (source == m_departmentList) 
		{
			String fileName = this.m_departmentList.getSelectedFileName();
			this.m_lectureTable.refresh(fileName);
		}
	}
	
	private class ListSelectionHandler implements ListSelectionListener 
	{
		@Override
		public void valueChanged(ListSelectionEvent event) 
		{
			try 
			{
				refresh(event.getSource());
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

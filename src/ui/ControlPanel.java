package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controller.CBasket;
import entity.EBasket;

public class ControlPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	private EnrollTable m_enrollTable;
	private BasketTable m_basketList;
	private NoticeFrame noticeFrame;
	private JButton m_jButton1, m_jButton2;
	private CBasket m_cBasket;
	private Vector<EBasket> m_eBaskets;
	private String m_userId;
	
	public ControlPanel(String text) throws IOException
	{
		m_userId = text;
		this.noticeFrame = new NoticeFrame();
		this.m_enrollTable = EnrollTable.GetInstance();
		this.m_basketList = BasketTable.GetInstance();
		this.m_cBasket = new CBasket();
		this.m_jButton1 = new JButton("ก้");
		this.add(m_jButton1);
		m_jButton1.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
				try 
				{
					Transport_To_Enroll(m_userId);
					BasketPanel.refreshCredits();
					EnrollPanel.refreshCredits();
				} catch (FileNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		m_jButton1.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				m_jButton1.setBackground(Color.WHITE);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
			m_jButton1.setBackground(UIManager.getColor("control"));
			}
				
		});
		
		this.m_jButton2 = new JButton("ก่");
		this.add(m_jButton2);
		m_jButton2.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
				try {
					Transport_To_Basket(m_userId);
					BasketPanel.refreshCredits();
					EnrollPanel.refreshCredits();
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
		
	}

	
	public void Transport_To_Basket(String text) throws FileNotFoundException
	{
		if(this.m_enrollTable.getSelectedRow() != -1)
		{
			int credits = this.m_basketList.returnCredit(text);
			if( credits + Integer.valueOf((String)this.m_enrollTable.getValueAt(this.m_enrollTable.getSelectedRow(), 2)) <= 21 )
			{
				boolean flag = true;
		    	String value = "";
				try 
				{
					this.m_eBaskets = this.m_cBasket.getItems("../LMS/data/"+ text +"basket");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		    	value = this.m_enrollTable.getValueAt(this.m_enrollTable.getSelectedRow(), 2).toString()
		    			+ " " + this.m_enrollTable.getValueAt(this.m_enrollTable.getSelectedRow(), 0).toString()
		    			+ " " + this.m_enrollTable.getValueAt(this.m_enrollTable.getSelectedRow(), 1).toString();
		    	
		    	for (EBasket eBasket : m_eBaskets) 
				{
		    		if(this.m_enrollTable.getValueAt(this.m_enrollTable.getSelectedRow(), 0).toString().equals(eBasket.getName()))
		    		{
		    			flag = false;
		    			noticeFrame.addFailedNotice();
		    		}
				}
		    	
		    	if(flag) 
		    	{
		    		FileWriter pw = null;
					try {
						pw = new FileWriter("../LMS/data/"+ text +"basket", true);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
						pw.write("\r\n"+ value);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
						pw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		    	}
			}
			else
			{
				noticeFrame.creditsOverflowedNotice(credits);
			}
			this.m_enrollTable.delEnroll(m_userId);
			this.m_enrollTable.refresh(m_userId);
			this.m_basketList.refresh(m_userId);

		}
		
	}
	
	public void Transport_To_Enroll(String text) throws FileNotFoundException
	{
		if(this.m_basketList.getSelectedRow() != -1)
		{
			int credits = this.m_enrollTable.returnCredit(text);
			if( credits + Integer.valueOf((String)this.m_basketList.getValueAt(this.m_basketList.getSelectedRow(), 2)) <= 21 )
			{
				boolean flag = true;
		    	String value = "";
				try 
				{
					this.m_eBaskets = this.m_cBasket.getItems("../LMS/data/"+ text +"enroll");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		    	value = this.m_basketList.getValueAt(this.m_basketList.getSelectedRow(), 2).toString()
		    			+ " " + this.m_basketList.getValueAt(this.m_basketList.getSelectedRow(), 0).toString()
		    			+ " " + this.m_basketList.getValueAt(this.m_basketList.getSelectedRow(), 1).toString();
		    	
		    	for (EBasket eBasket : m_eBaskets) 
				{
		    		if(this.m_basketList.getValueAt(this.m_basketList.getSelectedRow(), 0).toString().equals(eBasket.getName()))
		    		{
		    			flag = false;
		    			noticeFrame.addFailedNotice();
		    		}
				}
		    	
		    	if(flag) 
		    	{
		    		FileWriter pw = null;
					try {
						pw = new FileWriter("../LMS/data/"+ text +"enroll", true);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
						pw.write("\r\n"+ value);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
						pw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		    	}
		    	
			}
			else
	    	{
	    		noticeFrame.creditsOverflowedNotice(credits);
	    	}
			this.m_basketList.delBasket(m_userId);
			this.m_enrollTable.refresh(m_userId);
			this.m_basketList.refresh(m_userId);
		}	
	}
}

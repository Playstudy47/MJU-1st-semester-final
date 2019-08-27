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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class BasketPanel extends JPanel{

	private static BasketTable m_basketTable;
	private JButton m_jButton1, m_jButton2;
	private JPanel m_jPanel;
	private static JLabel m_jLabel;
	private static String m_userId;
	private static final long serialVersionUID = 1L;

	public BasketPanel(String text) throws IOException 
	{
		EtchedBorder eborder =  new EtchedBorder();
		JScrollPane scrollpane = new JScrollPane();
		
		BasketPanel.m_basketTable = BasketTable.GetInstance();
		BasketPanel.m_jLabel = new JLabel("<장바구니>                                           " + BasketPanel.m_basketTable.returnCredit(text) + "/21"); 
		BasketPanel.m_basketTable.refresh(text);
		this.m_jPanel = new JPanel();
		m_userId = text;
		scrollpane.setPreferredSize(new Dimension(300, 200));
		scrollpane.setViewportView(BasketPanel.m_basketTable);
		setLayout(new BorderLayout()); 
		this.m_jPanel.setLayout(new GridLayout(2, 1)); 
		m_jLabel.setBorder(eborder);
		this.add(BasketPanel.m_jLabel,"North");
		this.add(scrollpane,"Center");		
		this.add(m_jPanel, "East");
		this.m_jButton1 = new JButton("선택 삭제");
		this.m_jPanel.add(m_jButton1);
		m_jButton1.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
				try 
				{
					m_basketTable.delBasket(text);
					refreshCredits();
					m_basketTable.refresh(text);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
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
		this.m_jButton2 = new JButton("전체 삭제");
		this.m_jPanel.add(m_jButton2);
		m_jButton2.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
				PrintWriter pw = null;
				try {
					pw = new PrintWriter("../LMS/data/" + m_userId + "basket");
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
					m_basketTable.refresh(m_userId);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		m_jButton2.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				m_jButton2.setBackground(Color.WHITE);
				}
				public void mouseExited(java.awt.event.MouseEvent evt) 
				{
					m_jButton2.setBackground(UIManager.getColor("control"));
				}
		});
	}
	public static void refreshCredits() throws FileNotFoundException
	{
		m_jLabel.setText("<장바구니>                                           " + m_basketTable.returnCredit(m_userId) + "/21");
	}

}

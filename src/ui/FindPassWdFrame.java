package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.CRegister;
import entity.ERegister;

public class FindPassWdFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JButton m_confirmButton;
	private JLabel m_idLabel, m_nameLabel;
	private JTextField m_idField, m_nameField;
	private JPanel m_idPanel, m_namePanel, m_confirmPanel;
	private CRegister m_cRegister;
	private Vector<ERegister> m_eRegisters;
	private NoticeFrame m_noticeFrame;
	
	public FindPassWdFrame()
	{
		
		this.m_noticeFrame = new NoticeFrame();
		this.m_eRegisters = new Vector<ERegister>();
		this.m_cRegister = new CRegister();
		this.m_idPanel = new JPanel();
		this.m_namePanel = new JPanel();
		this.m_confirmPanel = new JPanel();
		this.m_confirmButton = new JButton("확인");
		this.m_idLabel = new JLabel("아이디");
		this.m_nameLabel = new JLabel("이름");
		this.m_idField = new JTextField(10);
		this.m_nameField = new JTextField(10);
        
		this.m_idPanel.add(this.m_idLabel);
		this.m_idPanel.add(this.m_idField);
		this.m_namePanel.add(this.m_nameLabel);
		this.m_namePanel.add(this.m_nameField);
		this.m_confirmPanel.add(this.m_confirmButton);
		this.m_confirmButton.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
				boolean flag = false;
				String passWd = null;
				String userName = null;
				try 
				{
					m_eRegisters = m_cRegister.getItems("../LMS/data/login");
				} catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				}
				for (ERegister eRegister : m_eRegisters) 
				{
					if(m_idField.getText().equals(eRegister.getId()) && m_nameField.getText().equals(eRegister.getName()))
					{
						flag = true;
						passWd = eRegister.getPasswd();
						userName = eRegister.getName();
					}
				}
				if(flag)
				{
					m_noticeFrame.findPassWdSuccessed(userName, passWd);
				}
				else
				{
					m_noticeFrame.findPassWdFailed();
				}
			}
		});
		this.m_confirmButton.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				m_confirmButton.setBackground(Color.WHITE);
				}
				public void mouseExited(java.awt.event.MouseEvent evt) 
				{
					m_confirmButton.setBackground(UIManager.getColor("control"));
				}
		});
		this.add(this.m_idPanel);
		this.add(this.m_namePanel);
		this.add(this.m_confirmPanel);
      
		this.setResizable(false);
		this.setLayout(new GridLayout(3,1));
		this.setTitle("비밀번호 찾기");
		this.setLocation(500, 300);
		this.setSize(250, 300);
		this.setVisible(true);
	}
}

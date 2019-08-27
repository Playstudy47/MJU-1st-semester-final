package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.CRegister;
import entity.ERegister;

public class ChangePasswdFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JButton m_confirmButton;
	private JLabel m_passwdLabel,m_idLabel, m_nameLabel, m_newPasswdLabel;
	private JTextField m_idField, m_passwdField, m_nameField, m_newPasswdField ;
	private JPanel m_idPanel, m_pwPanel, m_namePanel, m_newPasswdPanel, m_confirmPanel;
	private CRegister m_cRegister;
	private Vector<ERegister> m_eRegisters;
	private NoticeFrame m_noticeFrame;
	
	public ChangePasswdFrame()
	{
		this.m_noticeFrame = new NoticeFrame();
		this.m_newPasswdPanel = new JPanel();
		this.m_eRegisters = new Vector<ERegister>();
		this.m_cRegister = new CRegister();
		this.m_idPanel = new JPanel();
		this.m_pwPanel = new JPanel();
		this.m_namePanel = new JPanel();
		this.m_confirmPanel = new JPanel();
		this.m_confirmButton = new JButton("확인");
		this.m_idLabel = new JLabel("아이디");
		this.m_passwdLabel = new JLabel("비밀번호");
		this.m_nameLabel = new JLabel("이름");
		this.m_newPasswdLabel = new JLabel("새 비밀번호");
		this.m_idField = new JTextField(10);
		this.m_newPasswdField = new JTextField(10);
		this.m_passwdField = new JTextField(10);
		this.m_nameField = new JTextField(10);
        
		this.m_idPanel.add(this.m_idLabel);
		this.m_idPanel.add(this.m_idField);
		this.m_pwPanel.add(this.m_passwdLabel);
		this.m_pwPanel.add(this.m_passwdField);
		this.m_namePanel.add(this.m_nameLabel);
		this.m_namePanel.add(this.m_nameField);
		this.m_newPasswdPanel.add(this.m_newPasswdLabel);
		this.m_newPasswdPanel.add(this.m_newPasswdField);
		this.m_confirmPanel.add(this.m_confirmButton);
		this.m_confirmButton.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
				boolean flag = false;
				try 
				{
					m_eRegisters = m_cRegister.getItems("../LMS/data/login");
				} catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				}
				for (ERegister eRegister : m_eRegisters) 
				{
					if(eRegister.getId().equals(m_idField.getText()) && eRegister.getName().equals(m_nameField.getText()))
					{
						flag = true;
					}
				}
				if(flag)
				{
					if(!(m_newPasswdField.getText().equals(m_passwdField.getText())))
					{
						String[] array = new String[4];
						String value = "";
						BufferedReader br = null;
						try {
							br = new BufferedReader(new FileReader("../LMS/data/login"));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        while(true) 
				        {
				            String line = null;
							try 
							{
								line = br.readLine();
							} catch (IOException e) 
							{
								e.printStackTrace();
							}
				            if (line == null) break;
				            
				            if(!(line.equals("")))
				            {
				            	 array = line.split(" ");
						            
						            if(array[0].equals(m_idField.getText()))
						            {
							            array[1] = m_newPasswdField.getText();
							            
						            }
						           
				            	 value = value + array[0] + " " + array[1] + " " + array[2] + "\r\n";
				            }
				           
				            
				        }   
				        
				        PrintWriter pw = null;
						try 
						{
							pw = new PrintWriter("../LMS/data/login");
						} catch (FileNotFoundException e) 
						{
							e.printStackTrace();
						}
				        
				        pw.write(value);
				        pw.close();
				        
				        m_noticeFrame.successedChangePasswd(); 
					}
					else
					{
						m_noticeFrame.usedPasswd(); 
					}
				}
				else
				{
					m_noticeFrame.notFoundUserInfo();
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
		this.add(this.m_pwPanel);
		this.add(this.m_newPasswdPanel);
		this.add(this.m_namePanel);
		this.add(this.m_confirmPanel);
      
		this.setResizable(false);
		this.setLayout(new GridLayout(5,1));
		this.setTitle("비밀번호 변경");
		this.setLocation(500, 300);
		this.setSize(250, 300);
		this.setVisible(true);
	}
}

package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class CancelFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JButton m_confirmButton;
	private JLabel m_passwdLabel,m_idLabel, m_nameLabel;
	private JTextField m_idField, m_passwdField, m_nameField;
	private JPanel m_idPanel, m_pwPanel, m_namePanel, m_confirmPanel;
	private NoticeFrame m_noticeFrame;
	
	public CancelFrame()
	{

		this.m_noticeFrame = new NoticeFrame();
		this.m_idPanel = new JPanel();
		this.m_pwPanel = new JPanel();
		this.m_namePanel = new JPanel();
		this.m_confirmPanel = new JPanel();
		this.m_confirmButton = new JButton("확인");
		this.m_idLabel = new JLabel("아이디");
		this.m_passwdLabel = new JLabel("비밀번호");
		this.m_nameLabel = new JLabel("이름");
		this.m_idField = new JTextField(10);
		this.m_passwdField = new JTextField(10);
		this.m_nameField = new JTextField(10);
        
		this.m_idPanel.add(this.m_idLabel);
		this.m_idPanel.add(this.m_idField);
		this.m_pwPanel.add(this.m_passwdLabel);
		this.m_pwPanel.add(this.m_passwdField);
		this.m_namePanel.add(this.m_nameLabel);
		this.m_namePanel.add(this.m_nameField);
		this.m_confirmPanel.add(this.m_confirmButton);
		this.m_confirmButton.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
				boolean flag = false;
				String key = "";
				String value = null;
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
					try {
						line = br.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            if (line == null) break;
		            
		            value = m_idField.getText() + " " + m_passwdField.getText() + " " + m_nameField.getText();
		            if(!(value.equals(line)))
		            {
		            	key = key + line + "\r\n";
		            }
		            else 
		            {
		            	flag = true;
		            }
		            
		        } 
		        
		        if(flag)
		        {
		        	PrintWriter pw = null;
					try 
					{
						pw = new PrintWriter("../LMS/data/login");
					} 
					catch (FileNotFoundException e) 
					{
						e.printStackTrace();
					}
				    pw.write(key);
				    pw.close();
				    
				    File enrollFile = new File("../LMS/data/" + m_idField.getText() + "enroll");
				    File basketFile = new File("../LMS/data/" + m_idField.getText() + "basket");
				    if(enrollFile.exists() && basketFile.exists())
				    {
				    	enrollFile.delete();
				    	basketFile.delete();
				    	m_noticeFrame.cancelSuccessed();
				    }
				       
		        }
		        else
		        {
		        	m_noticeFrame.notFoundUserInfo();
		        }
		       
			}
		});
		m_confirmButton.addMouseListener(new java.awt.event.MouseAdapter() 
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
		this.add(this.m_namePanel);
		this.add(this.m_confirmPanel);
      
		this.setResizable(false);
		this.setLayout(new GridLayout(4,1));
		this.setTitle("회원 탈퇴");
		this.setLocation(500, 300);
		this.setSize(250, 300);
		this.setVisible(true);
	}
	
}

package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.CRegister;
import entity.ERegister;

public class SignUpFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JButton m_confirmButton;
	private JLabel m_passwdLabel,m_idLabel, m_nameLabel;
	private JTextField m_idField, m_passwdField, m_nameField;
	private JPanel m_idPanel, m_pwPanel, m_namePanel, m_confirmPanel;
	private CRegister m_cRegister;
	private Vector<ERegister> m_eRegisters;
	private NoticeFrame m_noticeFrame;
	
	public SignUpFrame()
	{
		
		this.m_noticeFrame = new NoticeFrame();
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
				// if any textfields are empty it doesn't work
				if(!(m_idField.getText().equals("")) && !(m_passwdField.getText().equals("")) && !(m_nameField.getText().equals("")))
				{
					try {
						register(m_idField.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					m_noticeFrame.registerFailedNotice();
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
		this.setTitle("회원 가입");
		this.setLocation(500, 300);
		this.setSize(250, 300);
		this.setVisible(true);
	}
	
	@SuppressWarnings("resource")
	public void register(String userId) throws IOException
	{
		boolean flag = true;
		try 
		{
			this.m_eRegisters = this.m_cRegister.getItems("../LMS/data/login");
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		
		for (ERegister eRegister : m_eRegisters) 
		{
			if(this.m_idField.getText().equals(eRegister.getId()))
			{
				flag = false;
			}
		}
		
		if(flag)
		{
			File file1 = new File("../LMS/data/" + userId + "basket") ;
            FileWriter fw = new FileWriter(file1, true) ;
             

            fw.write("");
            fw.flush();
            File file2 = new File("../LMS/data/" + userId + "enroll") ;
            fw = new FileWriter(file2, true);
            
            fw.write("");
            fw.flush();
            fw.close();

			FileWriter pw = null;
			try 
			{
				pw = new FileWriter("../LMS/data/login", true);
			} catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
	        try {
				pw.write("\r\n"+ this.m_idField.getText() + " " + this.m_passwdField.getText() + " " + this.m_nameField.getText());
			} catch (IOException e) 
	        {
				e.printStackTrace();
			}
	        try {
				pw.close();
			} catch (IOException e) 
	        {
				e.printStackTrace();
			}
	        this.setVisible(false);
	        this.m_noticeFrame.registerSuccessedNotice(m_nameField.getText());
	        
		}
		else
		{
			this.m_noticeFrame.usedIdNotice();
		}
	}
		
}

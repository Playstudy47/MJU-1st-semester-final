package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TermOfServiceFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel m_jPanel;
	private JCheckBox m_jCheckBox;
	private JButton m_jButton;
	private NoticeFrame m_noticeFrame;

	public TermOfServiceFrame()
	{
		this.setTitle("가입 약관");
		this.setLocation(600, 200);
		this.setSize(800, 530);
		this.setVisible(true);
		this.setResizable(false);
		
		this.setLayout(new BorderLayout());
		this.m_jPanel = new JPanel();
		this.m_noticeFrame = new NoticeFrame();
		this.m_jButton = new JButton("확인");
		this.m_jCheckBox = new JCheckBox("동의", false);
		BufferedReader br = null;
		String value = "";
		try 
		{
			br = new BufferedReader(new FileReader("../LMS/data/termofservice"));
		} catch (FileNotFoundException e)
		{	
			e.printStackTrace();
		}
		while(true) 
        {
			
            String line = null;
			try 
			{
				line = br.readLine();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
            if (line == null) break;
            
            value = value + line + "\r\n";
            
        }
		TextArea term = new TextArea(value);
		term.setEditable(false);
		this.m_jPanel.add(m_jCheckBox);
		this.m_jPanel.add(m_jButton);
		this.m_jButton.addActionListener(new ActionListener()
		{			
			public void actionPerformed(ActionEvent event)
			{
				if(m_jCheckBox.isSelected())
				{
					setVisible(false);
					new SignUpFrame();
				}
				else
				{
					m_noticeFrame.disagreeCheckBox();
				}
			}
		});
		m_jButton.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				m_jButton.setBackground(Color.WHITE);
				}
				public void mouseExited(java.awt.event.MouseEvent evt) 
				{
					m_jButton.setBackground(UIManager.getColor("control"));
				}
		});
		this.add(m_jPanel,"South");
		this.add(term,"Center");
	}
	
}

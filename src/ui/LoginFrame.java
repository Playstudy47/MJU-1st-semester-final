package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import controller.CRegister;
import entity.ERegister;

public class LoginFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private int m_loginFailCount;
	private JLabel m_jLabell,m_jLabel2,m_jLabel3;
	private JTextField m_id, m_passwd;
    private JPanel m_idPanel,m_pwPanel,m_loginPanel, m_buttonPanel;
    private JButton m_loginButton,m_signUpButton, m_findPasswdButton, m_cancelMemberButton, m_changePasswdButton;
    private CRegister m_cRegister;
	private Vector<ERegister> m_eRegisters;
    private NoticeFrame m_noticeFrame;
    
    public LoginFrame() throws IOException
    {
    	// Set BackGround Color
    	this.getContentPane().setBackground(Color.WHITE);
    	
    	// Set LoginFrame's layout at Flow Layout
    	this.setLayout(new FlowLayout());
        
    	// Make borders
        EtchedBorder eborder =  new EtchedBorder();

        this.m_cRegister = new CRegister();
        this.m_eRegisters = new Vector<ERegister>();
        this.m_loginFailCount = 0;
        this.m_jLabell = new JLabel("MJU");
        this.m_jLabell.setBorder(eborder);
        this.m_noticeFrame = new NoticeFrame();
        this.m_idPanel = new JPanel();
        this.m_pwPanel = new JPanel();
        this.m_jLabel3 = new JLabel("ID");
        this.m_jLabel2 = new JLabel("Password");
        this.m_id = new JTextField(10);
        this.m_passwd = new JPasswordField(10);
        this.m_loginPanel = new JPanel();
        this.m_buttonPanel = new JPanel();
        
        // Set buttonPanel layout at BorderLayout
        this.m_buttonPanel.setLayout(new BorderLayout());
        
        // Set buttonPanel layout at GridLayout
        this.m_loginPanel.setLayout(new GridLayout(2,2));
        this.m_loginButton = new JButton("로그인");
        this.m_loginButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
            	// if loginFailCount overs 5 you can't login
            	if(m_loginFailCount <= 5)
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
						//if id and passwd are correct flag become true
						if(m_id.getText().equals(eRegister.getId()) && m_passwd.getText().equals(eRegister.getPasswd()))
						{
							flag = true;
						}
					}
					
					if(flag)
					{
						setVisible(false);
						String userId= m_id.getText();
						try 
						{
							new MainFrame(userId);
						} catch (IOException e) 
						{
							e.printStackTrace();
						} 
						AudioInputStream ais = null;
						try 
						{
							ais = AudioSystem.getAudioInputStream(new File("../LMS/data/Successed.wav"));
						}
						catch (UnsupportedAudioFileException | IOException e1) 
						{
							e1.printStackTrace();
						}
						
						Clip clip = null;
						try 
						{
							clip = AudioSystem.getClip();
						} catch (LineUnavailableException e) 
						{
							e.printStackTrace();
						}
						clip.stop();
						try 
						{
							clip.open(ais);
						} catch (LineUnavailableException | IOException e) 
						{
							e.printStackTrace();
						}
						
						clip.start();
						
						m_noticeFrame.loginSuccessedNotice();
							
					}
					else 
					{       
						//if flag is false loginFailCount get bigger
						++m_loginFailCount;
						
						//if loginFailCount overs 6 noticeframe will appear
						if(m_loginFailCount == 6)
						{
							m_noticeFrame.loginCountOverFlowed();
						}
						else
						{
						// if loginFailCount doesn't over 6 loginFailNotice will appear
							m_noticeFrame.loginFailedNotice(m_loginFailCount);
						}
					}   
            	}
            	else
            	{
            		m_noticeFrame.loginCountOverFlowed();
            	}
            
            }
        });
        this.m_loginButton.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				m_loginButton.setBackground(Color.WHITE);
				}
				public void mouseExited(java.awt.event.MouseEvent evt) 
				{
					m_loginButton.setBackground(UIManager.getColor("control"));
				}
		});
        
        this.m_signUpButton = new JButton("회원가입");
        this.m_signUpButton.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent event)
    		{
        		new TermOfServiceFrame();
    		}
        });
        this.m_signUpButton.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				m_signUpButton.setBackground(Color.WHITE);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				m_signUpButton.setBackground(UIManager.getColor("control"));
			}
		});
        
        this.m_findPasswdButton = new JButton("비밀번호 찾기");
        this.m_findPasswdButton.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent event)
    		{
        		new FindPassWdFrame();
    		}
        });
        this.m_findPasswdButton.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				m_findPasswdButton.setBackground(Color.WHITE);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				m_findPasswdButton.setBackground(UIManager.getColor("control"));
			}
		});
        
        this.m_cancelMemberButton = new JButton("회원 탈퇴");
        this.m_cancelMemberButton.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent event)
    		{
        		new CancelFrame();
    		}
        });
        
        this.m_cancelMemberButton.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				m_cancelMemberButton.setBackground(Color.WHITE);
				}
				public void mouseExited(java.awt.event.MouseEvent evt) 
				{
					m_cancelMemberButton.setBackground(UIManager.getColor("control"));
				}
		});
        
        this.m_changePasswdButton = new JButton("비밀번호 변경");
        this.m_changePasswdButton.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent event)
    		{
        		new ChangePasswdFrame();
    		}
        });
        
        this.m_changePasswdButton.addMouseListener(new java.awt.event.MouseAdapter() 
		{
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				m_changePasswdButton.setBackground(Color.WHITE);
				}
				public void mouseExited(java.awt.event.MouseEvent evt) 
				{
					m_changePasswdButton.setBackground(UIManager.getColor("control"));
				}
		});
         
          
        
        this.m_idPanel.add(m_jLabel3);
        this.m_idPanel.add(m_id);
        this. m_pwPanel.add( m_jLabel2 );
        this.m_pwPanel.add( m_passwd );
        this.m_loginPanel.add(m_loginButton);
        this. m_loginPanel.add(m_signUpButton);
        this. m_loginPanel.add(m_findPasswdButton);
        this.m_loginPanel.add(m_cancelMemberButton);
        this.m_buttonPanel.add(m_loginPanel,"Center");
        this.m_buttonPanel.add(m_changePasswdButton,"South");
        this.m_idPanel.setBorder(eborder);
        this.m_pwPanel.setBorder(eborder);
        this. m_buttonPanel.setBorder(eborder);
          
        Image ic = null;
        File sourceimage = new File("../LMS/data/Q.jpg");
        try 
        {
        	ic = ImageIO.read(sourceimage);
        }
        catch (IOException e) 
        {
        	e.printStackTrace();
        }
          
        JLabel schoolIc = new JLabel(new ImageIcon(ic));
          
        this.add(this.m_jLabell);
        this.add(this.m_idPanel);
        this.add(this.m_pwPanel);
        this.add(this.m_buttonPanel);
        this.add(schoolIc);
          
        this.setTitle("명지대학교 로그인");
        this.setVisible(true);
        this.setResizable(false);
        this.setLocation(500, 300);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void Run() 
    {
    	
    }
}

package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class NoticeFrame extends JFrame
{
	private JLabel m_jLabel;
	private static final long serialVersionUID = 1L;
	public NoticeFrame()
	{
		setTitle("알림");
		setLocation(640,400);
		setSize(350, 100);
		setResizable(false);
		
		this.m_jLabel = new JLabel();
		add(m_jLabel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public void registerSuccessedNotice(String name)
	{

		m_jLabel.setText("회원가입이 완료되었습니다." + "  " + name + "님 환영합니다.");
        setVisible(true);
	}
	public void loginSuccessedNotice()
	{
		m_jLabel.setText("환영합니다.");
        setVisible(true);
	}
	
	public void loginFailedNotice(int loginFailCount)
	{
		m_jLabel.setText("아이디 혹은 비밀번호를 잘못 입력하셨습니다.  " + loginFailCount +"/5회");
        setVisible(true);
	}
	
	public void addFailedNotice()
	{
		m_jLabel.setText("이미 등록된 강의 입니다.");
        setVisible(true);
	}
	
	public void registerFailedNotice()
	{
		m_jLabel.setText("빈칸 없이 입력해주세요.");
        setVisible(true);
	}
	
	public void usedIdNotice()
	{
		m_jLabel.setText("이미 사용중인 아이디입니다.");
        setVisible(true);
	}
	
	public void creditsOverflowedNotice(int credits)
	{
		m_jLabel.setText("최대 학점 을 초과 했습니다." + "\r\n" + "현재 학점은 "+ credits +"/21점 입니다.");
        setVisible(true);
	}
	public void loginCountOverFlowed() 
	{
		m_jLabel.setText("로그인 실패 횟수 5번을 초과하여 이용이 제한됩니다.");
        setVisible(true);
		
	}
	public void findPassWdSuccessed(String name, String Passwd)
	{
		m_jLabel.setText(name +"님의 비밀번호는 " + Passwd + "입니다");
        setVisible(true);
	}
	public void findPassWdFailed() 
	{
		m_jLabel.setText("해당 비밀번호를 찾을 수 없습니다.");
        setVisible(true);

	}
	public void notFoundUserInfo() 
	{
		m_jLabel.setText("해당 유저의 정보를 찾을 수 없습니다.");
        setVisible(true);
	}
	public void cancelSuccessed() 
	{
		m_jLabel.setText("회원탈퇴가 완료되었습니다.");
        setVisible(true);
	}
	public void disagreeCheckBox() 
	{
		m_jLabel.setText("약관에 동의해주세요.");
        setVisible(true);
	}
	public void successedChangePasswd() 
	{
		m_jLabel.setText("비밀번호가 변경되었습니다.");
        setVisible(true);
	}
	public void usedPasswd() 
	{
		m_jLabel.setText("같은 비밀번호로 변경 할 수 없습니다.");
        setVisible(true);
	}
}

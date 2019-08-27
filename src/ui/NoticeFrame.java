package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class NoticeFrame extends JFrame
{
	private JLabel m_jLabel;
	private static final long serialVersionUID = 1L;
	public NoticeFrame()
	{
		setTitle("�˸�");
		setLocation(640,400);
		setSize(350, 100);
		setResizable(false);
		
		this.m_jLabel = new JLabel();
		add(m_jLabel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public void registerSuccessedNotice(String name)
	{

		m_jLabel.setText("ȸ�������� �Ϸ�Ǿ����ϴ�." + "  " + name + "�� ȯ���մϴ�.");
        setVisible(true);
	}
	public void loginSuccessedNotice()
	{
		m_jLabel.setText("ȯ���մϴ�.");
        setVisible(true);
	}
	
	public void loginFailedNotice(int loginFailCount)
	{
		m_jLabel.setText("���̵� Ȥ�� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.  " + loginFailCount +"/5ȸ");
        setVisible(true);
	}
	
	public void addFailedNotice()
	{
		m_jLabel.setText("�̹� ��ϵ� ���� �Դϴ�.");
        setVisible(true);
	}
	
	public void registerFailedNotice()
	{
		m_jLabel.setText("��ĭ ���� �Է����ּ���.");
        setVisible(true);
	}
	
	public void usedIdNotice()
	{
		m_jLabel.setText("�̹� ������� ���̵��Դϴ�.");
        setVisible(true);
	}
	
	public void creditsOverflowedNotice(int credits)
	{
		m_jLabel.setText("�ִ� ���� �� �ʰ� �߽��ϴ�." + "\r\n" + "���� ������ "+ credits +"/21�� �Դϴ�.");
        setVisible(true);
	}
	public void loginCountOverFlowed() 
	{
		m_jLabel.setText("�α��� ���� Ƚ�� 5���� �ʰ��Ͽ� �̿��� ���ѵ˴ϴ�.");
        setVisible(true);
		
	}
	public void findPassWdSuccessed(String name, String Passwd)
	{
		m_jLabel.setText(name +"���� ��й�ȣ�� " + Passwd + "�Դϴ�");
        setVisible(true);
	}
	public void findPassWdFailed() 
	{
		m_jLabel.setText("�ش� ��й�ȣ�� ã�� �� �����ϴ�.");
        setVisible(true);

	}
	public void notFoundUserInfo() 
	{
		m_jLabel.setText("�ش� ������ ������ ã�� �� �����ϴ�.");
        setVisible(true);
	}
	public void cancelSuccessed() 
	{
		m_jLabel.setText("ȸ��Ż�� �Ϸ�Ǿ����ϴ�.");
        setVisible(true);
	}
	public void disagreeCheckBox() 
	{
		m_jLabel.setText("����� �������ּ���.");
        setVisible(true);
	}
	public void successedChangePasswd() 
	{
		m_jLabel.setText("��й�ȣ�� ����Ǿ����ϴ�.");
        setVisible(true);
	}
	public void usedPasswd() 
	{
		m_jLabel.setText("���� ��й�ȣ�� ���� �� �� �����ϴ�.");
        setVisible(true);
	}
}

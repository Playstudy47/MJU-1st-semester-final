package ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.CBasket;
import entity.EBasket;

public class EnrollTable extends JTable
{
	private static final long serialVersionUID = 1L;

	private static EnrollTable s_instance; 
	
	private String [] m_head = {"강의명 ", "교수 이름","학점"};
	private CBasket m_cBasket;
	private Vector<EBasket> m_eBaskets;
	private DefaultTableModel m_tableModel;

	private EnrollTable() 
	{
		this.m_cBasket = new CBasket();
    	this.m_tableModel = new DefaultTableModel(null, m_head)
    	{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int ColIndex)
    		{
    			return false;
    		}
    	};
    	this.getTableHeader().setReorderingAllowed(false);
    	this.getTableHeader().setResizingAllowed(false);
    			
	}

	public static EnrollTable GetInstance()
	{
		if(s_instance == null)
			s_instance = new EnrollTable();
		return s_instance;
	}
	
	
	public void refresh(String userId) throws FileNotFoundException 
	{
		this.m_eBaskets = this.m_cBasket.getItems("../LMS/data/" + userId + "enroll");

		this.m_tableModel.setRowCount(0);
		for (EBasket eBasket : m_eBaskets)
		{
			Vector<String> row = new Vector<String>();
			row.addElement(eBasket.getName());
			row.addElement(eBasket.getProfessorName());
			row.addElement(Integer.toString(eBasket.getNumber()));
			m_tableModel.addRow(row);
		}
		
		this.setModel(m_tableModel);
		this.setSize(200,200);
		this.updateUI();
		
	}	
	

	public void delEnroll(String userId) throws FileNotFoundException
	{
		if(this.getSelectedRow() != -1) 
		{
			String key = "";
			String value = null;
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader("../LMS/data/" + userId + "enroll"));
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
	            
	            value = this.getValueAt(this.getSelectedRow(), 2).toString()
	            		+ " " + this.getValueAt(this.getSelectedRow(), 0).toString()
	            		+ " " + this.getValueAt(this.getSelectedRow(), 1).toString();
	            if(!(value.equals(line)))
	            {
	            	key = key + line + "\r\n";
	            }
	            
	        }   
	        PrintWriter pw = new PrintWriter("../LMS/data/" + userId + "enroll");
	        
	        pw.write(key);
	        pw.close();
	        this.updateUI();  
		}
		
	}
	
	public int returnCredit(String userId) throws FileNotFoundException
	{
		this.m_eBaskets = this.m_cBasket.getItems("../LMS/data/" + userId + "enroll");
		int credit = 0;
		
		for (EBasket eBasket : m_eBaskets)
		{
			credit = credit + eBasket.getNumber();
		}
		return credit;
	}
}

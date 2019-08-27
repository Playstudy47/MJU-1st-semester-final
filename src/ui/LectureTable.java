package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.CBasket;
import controller.CLecture;
import entity.EBasket;
import entity.ELecture;


public class LectureTable extends JTable
{	
    private static final long serialVersionUID = 1L;
    private String [] m_head = {"강의명 ", "교수 이름","학점"};
    private DefaultTableModel m_tableModel;
    private CLecture m_cLecture;
	private Vector<ELecture> m_eLectures;
	private Vector<EBasket> m_eBaskets;
	private CBasket m_cBasket;
	private NoticeFrame m_noticeFrame;
	private EnrollTable m_enrollTable;
	private BasketTable m_basketTable;
	private String userId;
	
	public LectureTable(String text) throws IOException
    {
		userId = text;
		this.m_enrollTable = EnrollTable.GetInstance();
		this.m_basketTable = BasketTable.GetInstance();
		this.m_noticeFrame = new NoticeFrame();
		this.m_cBasket = new CBasket();
    	this.m_cLecture = new CLecture();
    	this.m_tableModel = new DefaultTableModel(null, m_head)
    	{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int ColIndex)
    		{
    			return false;
    		}
    	};
    	this.addMouseListener(new MyMouseListener());	
    	this.getTableHeader().setReorderingAllowed(false);
    	this.getTableHeader().setResizingAllowed(false);

    }	

	private class MyMouseListener extends MouseAdapter 
	{
   	 
	    @Override
	    public void mouseClicked(MouseEvent e) 
	    {
	    	if (e.getClickCount() == 2)
	    	{
	    		try 
	    		{
					addBasket(userId);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		try {
					BasketPanel.refreshCredits();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					m_basketTable.refresh(userId);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    }
	}    
	public void refresh(String fileName)  
	{
		try 
		{
			this.m_eLectures = this.m_cLecture.getItems("data/" + fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.m_tableModel.setRowCount(0);
		
		for (ELecture eLecture : m_eLectures) 
		{
			Vector<String> row = new Vector<String>();
			row.addElement(eLecture.getName());
			row.addElement(eLecture.getProfessorName());
			row.addElement(eLecture.getCredit());
			m_tableModel.addRow(row);
		}
		
		this.setModel(m_tableModel);
		this.setSize(200,200);
		this.updateUI();
	}
	
	
	public void addBasket(String text) throws IOException
	{
		if(this.getSelectedRow() != -1)
		{	
			int credits = this.m_basketTable.returnCredit(text);
			if( this.m_basketTable.returnCredit(text) + Integer.valueOf((String)this.getValueAt(this.getSelectedRow(), 2)) <= 21 )
			{
				boolean flag = true;
		    	String value = "";
				try 
				{
					this.m_eBaskets = this.m_cBasket.getItems("../LMS/data/"+ text + "basket");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		    	value = this.getValueAt(this.getSelectedRow(), 2).toString()
		    			+ " " + this.getValueAt(this.getSelectedRow(), 0).toString()
		    			+ " " + this.getValueAt(this.getSelectedRow(), 1).toString();
		    	
		    	for (EBasket eBasket : m_eBaskets) 
				{
		    		if(this.getValueAt(this.getSelectedRow(), 0).toString().equals(eBasket.getName()))
		    		{
		    			flag = false;
		    			m_noticeFrame.addFailedNotice();
		    		}
				}
		    	
		    	if(flag) 
		    	{
		    		FileWriter pw = null;
					try 
					{
						pw = new FileWriter("../LMS/data/"+ text + "basket", true);
					} catch (FileNotFoundException e) 
					{
						e.printStackTrace();
					} catch (IOException e) 
					{
						e.printStackTrace();
					}
			        try {
						pw.write("\r\n"+ value);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
						pw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
			}
			else
			{
				m_noticeFrame.creditsOverflowedNotice(credits);
			}
			
	    	
		}
	}
	
	public void enroll(String text) throws FileNotFoundException
	{
		if(this.getSelectedRow() != -1)
		{		
			int credits = this.m_enrollTable.returnCredit(text);
			if( this.m_enrollTable.returnCredit(text) + Integer.valueOf((String)this.getValueAt(this.getSelectedRow(), 2)) <= 21 )
			{
				boolean flag = true;
		    	String value = "";
				try 
				{
					this.m_eBaskets = this.m_cBasket.getItems("../LMS/data/" + text +"enroll");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		    	value = this.getValueAt(this.getSelectedRow(), 2).toString()
		    			+ " " + this.getValueAt(this.getSelectedRow(), 0).toString()
		    			+ " " + this.getValueAt(this.getSelectedRow(), 1).toString();
		    	
		    	for (EBasket eBasket : m_eBaskets) 
				{
		    		if(this.getValueAt(this.getSelectedRow(), 0).toString().equals(eBasket.getName()))
		    		{
		    			flag = false;
		    			m_noticeFrame.addFailedNotice();
		    			break;
		    		}
				}
		    	
		    	if(flag) 
		    	{
		    		FileWriter pw = null;
					try {
						pw = new FileWriter("../LMS/data/" + text +"enroll", true);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
						pw.write("\r\n"+ value);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
						pw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
				
				
			}
			else
			{
				m_noticeFrame.creditsOverflowedNotice(credits);
			}
		}
		
		
	}
	
	


/*
    public LectureTable() throws IOException
    {
    	
    	this.listSelectionhandler = new ListSelectionHandler();
    	this.m_tableModel = new DefaultTableModel();
    	
    	this.m_jTable = new JTable();
        this.Run(fileN);
        
        DefaultTableModel tableModel= new DefaultTableModel(null, m_head);
        
       // m_jTable = new JTable(tableModel);

    }
    
    public void Run(String fileName) throws IOException
    {
    	
    	int p = 0;
    	this.jButton = new JButton("담기");
    	this.fileN = fileName;
    	m_array.clear();
    	
    	@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("../LMS/data/" + fileN));
    	Vector<String> array = new Vector<String>(5);
    	
    	while(true)
    	{
    		String line = br.readLine();
    	
    		if (line == null) break;
    	
    		Vector<String> temp = new Vector<String>();
    	
    		for(int i = 0; i < line.split(" ").length; ++i)
    		{
    			temp.addElement(line.split(" ")[i]);
    		}
    		m_array.addElement(temp);
        	

    		
    		// m_array.addElement(line.split(" ")[i]);
    		
    		for(int i = 0; i < line.split(" ").length; ++i)
            {
            	array.addElement(line.split(" ")[i]);
            }
    	}
    	
    	Vector<String> head = new Vector<String>();
    	
    	head.addElement("강의번호");
    	head.addElement("1");
    	head.addElement("1");
    	head.addElement("학점");
    	head.addElement("강의시간");
        
    	Vector<String> temp = new Vector<String>();
        temp.add(array.elementAt(0));
        temp.add(array.elementAt(1));
        temp.add(array.elementAt(2));
        temp.add(array.elementAt(3));
        temp.add(array.elementAt(4));
    
       
        
        System.out.println(m_array.size());
        
        m_tableModel= new DefaultTableModel(m_head, 0);
        
        while(p < m_array.size())
        {
        	m_tableModel.addRow(m_array.elementAt(p));
        	++p;
        }
        
        m_jTable.setModel(m_tableModel);
        
    	m_jScollPane = new JScrollPane(m_jTable);
    	m_jScollPane.setViewportView(m_jTable);
    	m_jScollPane.setPreferredSize(new Dimension(300,300));
    	
    	this.add(m_jScollPane);
    	this.add(jButton);
    	


        // defaultTableModel.setDataVector(array, temp);
    	// Object[][] arr = m_array.stream().map(List::toArray).toArray(Object[][]::new);
    	jButton.addActionListener(new ActionListener() 
        {
    		
    		
            public void actionPerformed(ActionEvent event) 
            {
            	int column;
            	int row = m_jTable.getSelectedRow();
            	
            	String key = "";
            	String value = "";
            	
            	for(column = 0; column < 5; ++column) 
            	{
            		value = m_jTable.getValueAt(row, column).toString();
            		System.out.println(value);
            		key = key +" " + value;
            	}
            	FileWriter pw = null;
				try {
					pw = new FileWriter("../LMS/data/basket", true);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                try {
					pw.write("\r\n"+ key);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                try {
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              BasketPanel basketPanel = null;
			try {
				basketPanel = new BasketPanel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              basketPanel.refresh();
        		
            }
        });

    	

    }
    public void ReadDataFromRile(String fileName)
    {
    	/*
        // Get table model from JTable
        DefaultTableModel model= (DefaultTableModel)m_jTable.getModel();
        // Set count of row to zero
        model.setRowCount(0);

        // For file reading.
        FileReader rw = null;
        BufferedReader br = null;

        // Exception handling is BAD in C++
        // But it seems like Exception handling is essential in Java
        try {

            //File Reading
            rw = new FileReader("../LMS/data/" + fileName);
            br = new BufferedReader(rw);

            //Reading a file line by line
            String readLine = null;

            // Time-Complexity: O(n^2) which is not OKAY.
            // You'd better find a better solution.
            while ((readLine = br.readLine()) != null)
            {
                Object[] row = new Object[5];

                String[] sliceTest = readLine.split(" ");

                for (int i = 0; i < sliceTest.length; ++i)
                {
                    if(sliceTest[i] == null)
                        break;
                    row[i] = sliceTest[i];
                }

                // Since row is always allocated 5 elements, check is the last index of row null pointer or not.
                // If it isn't, send data to table model.
                if(row[4] != null)
                {
                    model.addRow(row);
                    model.fireTableDataChanged();
                }
            }
        }
        catch (IOException e)
        {
        }
    
    }
    
    private class ListSelectionHandler implements ListSelectionListener 
    {
		@Override
		public void valueChanged(ListSelectionEvent event) 
		{}
	}
*/
}



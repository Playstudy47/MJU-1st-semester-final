package ui;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import controller.CDirectory;
import entity.EDirectory;

@SuppressWarnings("serial")
public class DirectoryList extends JList<String> 
{
	private CDirectory m_cDirectory;
	private Vector<EDirectory> m_directories;
	private Vector<String> m_courseData;
	
	public DirectoryList(ListSelectionListener listSelectionHandler) 
	{
		//attribute
		this.setPreferredSize(new Dimension(100, 200));
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.m_cDirectory = new CDirectory();
		this.m_courseData = new Vector<String>();
		//set model
		this.addListSelectionListener(listSelectionHandler);
		this.setListData(m_courseData);

	}

	public String getSelectedFileName() 
	{
		int selectedIndex = this.getSelectedIndex();
		return this.m_directories.get(selectedIndex).getHyperLink();
	}

	public String refresh(String fileName) 
	{
		try 
		{
			this.m_directories = this.m_cDirectory.getItems("data/" + fileName);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		this.m_courseData.clear();
		for (EDirectory eDirectory : m_directories) 
		{
			this.m_courseData.add(eDirectory.getName());
		}
		
		this.setSelectedIndex(0);
		this.updateUI();
		
		return this.m_directories.get(0).getHyperLink();
	}

}

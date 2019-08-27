package controller;

import java.io.FileNotFoundException;
import java.util.Vector;

import dao.DAORegister;
import entity.ERegister;

public class CRegister {

	private DAORegister daoRegister;
	
	public CRegister() {
		this.daoRegister = new DAORegister();
	}

	public Vector<ERegister> getItems(String fileName) throws FileNotFoundException {
		return this.daoRegister.getItems(fileName);
	}
}

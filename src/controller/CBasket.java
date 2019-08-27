package controller;

import java.io.FileNotFoundException;
import java.util.Vector;

import dao.DAOBasket;
import entity.EBasket;

public class CBasket {
	private DAOBasket m_daoBasket;
	
	public CBasket() {
		this.m_daoBasket = new DAOBasket();
	}

	public Vector<EBasket> getItems(String fileName) throws FileNotFoundException {
		return this.m_daoBasket.getItems(fileName);
	}

}

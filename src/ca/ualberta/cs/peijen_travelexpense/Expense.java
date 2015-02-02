/*
 Travel Expense: travel expense tracking application


    Copyright (C) 2015  Chris Lin  peijen@ualberta.ca

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */

package ca.ualberta.cs.peijen_travelexpense;

import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable {

	
	/**
	 * Expense serializable ID
	 */
	private static final long serialVersionUID = 2342770718616942378L;
	protected String expenseName;
	protected Date date = new Date();	
	protected String description;
    protected String status;
    
	public Expense(String expenseName) {
		this.expenseName = expenseName;
	}

	public String getName() {
	
		return this.expenseName;
	}
	
	public Date getDate(){
		return this.date;
	}

	public String toString(){
		return getName();
		
	}
	
	public boolean equals(Object compareExpense){
		
		if(compareExpense != null && compareExpense.getClass() == this.getClass()){
			return this.equals((Expense)compareExpense);
		}
		else{
		return false;
		}
		
	}
	public boolean equals(Expense compareExpense){
		if(compareExpense == null){
			return false;
		}
		return getName().equals(compareExpense.getName());
	}
	
	
	public int hashCode(){
		return ("Expense"+getName()).hashCode();
	}
}

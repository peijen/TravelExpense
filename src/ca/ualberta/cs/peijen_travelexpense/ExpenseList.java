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
import java.util.ArrayList;
import java.util.Collection;

public class ExpenseList implements Serializable {

	
	/**
	 * ExpenseList serialization ID
	 */
	private static final long serialVersionUID = -3035358862621688030L;
	protected ArrayList<Expense> expenseList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ExpenseList(){
		expenseList = new ArrayList<Expense>();
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners(){
		if(listeners == null){
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}

	public Collection<Expense> getExpenses() {
		
		return expenseList;
	}

	public void addExpense(Expense testExpense) {
		expenseList.add(testExpense);
		notifyListeners();
		
	}

	private void notifyListeners() {
		// TODO Auto-generated method stub
		for (Listener listener : getListeners()) {
			listener.update();			
		}		
	}
	


	public void deleteExpense(Expense testExpense) {
		expenseList.remove(testExpense);
		notifyListeners();
		
	}

	public int size() {
		
		return expenseList.size();
	}

	public boolean contains(Expense testExpense) {
	
		return expenseList.contains(testExpense);
	}
	public Expense chooseExpense() {
		int size = expenseList.size();
		/*
		if(size<=0){
			
		}
		*/
		int element = 0;
		return expenseList.get(element);
		
	}
	

	public void addListener(Listener l) {
	
		getListeners().add(l);
		
	}

	public void removeListener(Listener l) {
		
		getListeners().remove(l);
	}

}

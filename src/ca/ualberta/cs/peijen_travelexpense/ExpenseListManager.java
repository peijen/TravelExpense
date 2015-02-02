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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ExpenseListManager {

	static final String prefFile = "CxpenseList";  //const
	static final String elKey = "expenseList";     //const
	Context context;
	
	
	//base on eclass youtube video by Abram Hindle:https://www.youtube.com/watch?v=uat-8Z6U_Co
	//allow us to get expenselist manager	
	static private ExpenseListManager expenseListManager = null;
	
	public static void initManager(Context context){
		if(expenseListManager == null){
			if(context ==null){
				throw new RuntimeException("missing context for ExpenseListManager");
			}
			expenseListManager = new ExpenseListManager(context);
		}		
	}
	
	public static ExpenseListManager getManager(){
		if(expenseListManager == null){			
				throw new RuntimeException("Did not initialize Manager");
		}
		return expenseListManager;
	}
	
	
	public ExpenseListManager(Context context) {
		
		this.context = context;
	}
	public ExpenseList loadExpenseList() throws ClassNotFoundException, IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String expenseListData = settings.getString(elKey,"");
		// check if the list is empty
		if(expenseListData.equals("")){
			return new ExpenseList();  //if it's empty, create a new list
		}
		else{
			return expenseListFromString(expenseListData);  //return list
		}	 
	}
	
	static public ExpenseList expenseListFromString(String expenseListData) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(expenseListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ExpenseList) oi.readObject();
		
		
		
	}
	static public String expenseListToString(ExpenseList el) throws IOException {
	
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(el);
		oo.close();
		byte bytes[] = bo.toByteArray();
		
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}
	
	public void saveExpenseList(ExpenseList el) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(elKey, expenseListToString(el));
		editor.commit();
		
		
	}
}

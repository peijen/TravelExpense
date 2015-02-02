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

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class ClaimInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.claiminfoactivity);
		

		ExpenseListManager.initManager(this.getApplicationContext());
		
		
		//base on eclass youtube video by Abram Hindle: https://www.youtube.com/watch?v=7zKCuqScaRE
		//adding and update expenses onto claim list
		ListView listView = (ListView) findViewById(R.id.expenselistView);		
		Collection<Expense> expenses = ExpenseListController.getExpenseList().getExpenses();			
		
		final ArrayList<Expense> list = new ArrayList<Expense>(expenses);// share the list, not gonna change it		
		final ArrayAdapter<Expense> expenseAdapter = new ArrayAdapter<Expense>(this, android.R.layout.simple_list_item_1, list);
	    listView.setAdapter(expenseAdapter);
	
	    
	    //update to make our adapter now that list has been changed
	    ExpenseListController.getExpenseList().addListener(new Listener(){
	    	@Override
	    	public void update(){
	    		list.clear();
	    		Collection<Expense> expenses = ExpenseListController.getExpenseList().getExpenses();
	    		list.addAll(expenses);
	    		expenseAdapter.notifyDataSetChanged();
	    	}
	    });
	    
	    //base on eclass youtube video by Abram Hindle: https://www.youtube.com/watch?v=7zKCuqScaRE
	    //delete an expense
	    listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				//Toast.makeText(ClaimListActivity.this,"Delete "+ list.get(position).toString(), Toast.LENGTH_SHORT).show();
				
				AlertDialog.Builder adb = new AlertDialog.Builder(ClaimInfoActivity.this); //set alert dialog for deleting
				adb.setMessage("Delete "+list.get(position).toString()+ "?");
				adb.setCancelable(true);
				final int FinalPosition = position;
				//set options for delete claims or cancel
				adb.setPositiveButton("Delete", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Expense expense = list.get(FinalPosition);
						ExpenseListController.getExpenseList().deleteExpense(expense);						
					}								
				});
				adb.setNegativeButton("Cancel", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {						
						//do nothing here since we dont want to delete the claim
					}					
				});
				adb.show();			
				return false;
			}	    	    	
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim_info, menu);
		return true;
		
	}
	public void addexpenseitem(MenuItem menu) //click add an new expense 
	{
		Toast.makeText(this, "Add an expense", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ClaimInfoActivity.this, AddExpense.class);
		startActivity(intent); //move to the adding expense layout

		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

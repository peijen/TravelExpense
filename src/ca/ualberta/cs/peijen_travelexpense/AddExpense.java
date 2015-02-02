package ca.ualberta.cs.peijen_travelexpense;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

//this class is for adding a new expense
public class AddExpense extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addexpense);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_expense, menu);
		return true;
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
	
	public void done(View v){ //finish adding
		Toast.makeText(this,"added an expense item", Toast.LENGTH_SHORT).show(); // show message
		ExpenseListController es = new ExpenseListController();
		EditText textview = (EditText) findViewById(R.id.expenseName);
		es.addExpense(new Expense(textview.getText().toString()));
		
		Intent intent = new Intent(AddExpense.this,ClaimInfoActivity.class);
		startActivity(intent);  //move back to the expense list
		
	}
	
}

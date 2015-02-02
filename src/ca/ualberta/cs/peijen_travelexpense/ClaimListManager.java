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

public class ClaimListManager {

	static final String prefFile = "ClaimList";  //const
	static final String clKey = "claimList";     //const
	Context context;
	
	
	//base on eclass youtube video by Abram Hindle:https://www.youtube.com/watch?v=uat-8Z6U_Co
	//allow us to get claimlist manager	
	static private ClaimListManager claimListManager = null;
	
	public static void initManager(Context context){
		if(claimListManager == null){
			if(context ==null){
				throw new RuntimeException("missing context for ClaimListManager");
			}
			claimListManager = new ClaimListManager(context);
		}		
	}
	
	public static ClaimListManager getManager(){
		if(claimListManager == null){			
				throw new RuntimeException("Did not initialize Manager");
		}
		return claimListManager;
	}
	
	
	public ClaimListManager(Context context) {
		
		this.context = context;
	}
	public ClaimList loadClaimList() throws ClassNotFoundException, IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String claimListData = settings.getString(clKey,"");
		// check if the list is empty
		if(claimListData.equals("")){
			return new ClaimList();  //if it's empty, create a new list
		}
		else{
			return claimListFromString(claimListData);  //return list
		}	 
	}
	
	static public ClaimList claimListFromString(String claimListData) throws ClassNotFoundException, IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(claimListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ClaimList) oi.readObject();
		
		
		
	}
	static public String claimListToString(ClaimList cl) throws IOException {
	
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(cl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}
	
	public void saveClaimList(ClaimList cl) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(clKey, claimListToString(cl));
		editor.commit();
		
		
	}
	
}

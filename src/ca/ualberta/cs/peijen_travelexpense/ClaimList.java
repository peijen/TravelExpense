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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

//based on eclass video by Abram Hindle: https://www.youtube.com/watch?v=uat-8Z6U_Co

public class ClaimList implements Serializable {
	/**
	 * ClaimList serialization ID
	 */
	private static final long serialVersionUID = 8921619427084031978L;
	protected ArrayList<Claim> claimList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ClaimList(){
		claimList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners(){
		if(listeners == null){
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}

	public Collection<Claim> getClaims() {
		
		return claimList;
	}

	public void addClaim(Claim testClaim) {
		claimList.add(testClaim);
		notifyListeners();
		
	}

	private void notifyListeners() {
		// TODO Auto-generated method stub
		for (Listener listener : getListeners()) {
			listener.update();			
		}		
	}
	


	public void deleteClaim(Claim testClaim) {
		claimList.remove(testClaim);
		notifyListeners();
		
	}

	public int size() {
		
		return claimList.size();
	}

	public boolean contains(Claim testClaim) {
	
		return claimList.contains(testClaim);
	}
	public Claim chooseClaim() {
		int size = claimList.size();
		/*
		if(size<=0){
			
		}
		*/
		int element = 0;
		return claimList.get(element);
		
	}
	

	public void addListener(Listener l) {
	
		getListeners().add(l);
		
	}

	public void removeListener(Listener l) {
		
		getListeners().remove(l);
	}

}

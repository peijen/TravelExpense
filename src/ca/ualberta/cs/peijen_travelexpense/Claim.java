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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Claim implements Serializable {

	/**
	 * Claim serializable ID
	 */
	private static final long serialVersionUID = -1505129927945278821L;
	protected String claimName;
	protected Date startdate = new Date();
	protected Date enddate = new Date();
	protected String description;
    protected String status;
   
	public Claim(String claimName) {
		this.claimName = claimName;
	}

	public String getName() {
	
		return this.claimName;
	}
	
	public Date getStartDate(){
		return this.startdate;
	}

	public Date getEndDate(){
	
		return this.enddate;
	}
	public String toString(){
		return getName();
		
	}
	
	public boolean equals(Object compareClaim){
		
		if(compareClaim != null && compareClaim.getClass() == this.getClass()){
			return this.equals((Claim)compareClaim);
		}
		else{
		return false;
		}
		
	}
	public boolean equals(Claim compareClaim){
		if(compareClaim == null){
			return false;
		}
		return getName().equals(compareClaim.getName());
	}
	public int hashCode(){
		return ("Claim"+getName()).hashCode();
	}
}

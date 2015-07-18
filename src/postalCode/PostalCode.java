/* File Name: PostalCode.java
 * Course Name: CST8130 - Data Structures
 * Lab Section: 
 * Student Name: Warsame Egal
 * Date: 2015/1/29
 */

package postalCode;

import geodetic.*;

import java.util.Comparator;

public class PostalCode {
	private String code;
	private String city;
	private String province;
	private Coordinate coordinate;

	/*
	 * Following methods are getter and setters 
	 */
	public PostalCode(String code, String city) {this.code = code; this.city = city;}
	
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}

	public String getCity() {return city;}
	public void setCity(String city) {this.city = city;}

	public String getProvince() {return province;}
	public void setProvince(String province) {this.province = province;}

	public double getLatitude() {return coordinate.getLatitude();}
  public void setLatitude(Coordinate latitude) {this.coordinate = latitude;}

	public double getLongitude() {return coordinate.getLongitude();}
	public void setLongitude(Coordinate longitude) {this.coordinate = longitude;}
	
	public Coordinate getCoordinate() {return coordinate;} // removes QuadTree error in add method
	public void setCoordinate(Coordinate coordinate) {this.coordinate = coordinate;} //might delete this useless

		/*split() is a method in the String class. It is a "factory" method that manufactures an array of references to Strings.
		 * Each of the reference-to-String values in the new array points at a newly created String which contains a copy of a small portion of the original String.
		 * The full "inputLine" contains the entire PostalCode record with all fields clustered together.
		 * Using the split() method allows us to separate that record into the individual fields.
		 * The argument to split() defines the delimiters that determine the field boundaries which are either 1) the pipe character | or 2) the newline
		 * character
		*/
	public PostalCode(String inputLine) {
		
		String[] fields = inputLine.split("\\||\\r\\n"); 

		// The newly manufactured array of references-to-String now contains individual String objects for each individual field
		// Selectively capture the fields that matter (using the index number) and abandon those that don't matter.
		code = fields[1]; // code is of type String, thus we need only capture the reference-to value.
		city = fields[2]; // code is of type String, thus we need only capture the reference-to value.
		province = fields[3]; // province is of type String, thus we need only capture the reference-to value.
		 // Create temporary latitude type double. The field containing latitude is input as a String of digit CHARACTERS, BUT must be converted to type double
		 // Create temporary longitude type double. The field containing longitude is input as a String of digit CHARACTERS, BUT must be converted to type double
		coordinate = new Coordinate(Double.parseDouble(fields[6]), (Double.parseDouble(fields[7]))); // pass Coordinate latitude and longitude values to coordinate object  
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s", code, city, province, coordinate.toString()); // replaced latitude and longitude with coordinate that calls the toString in coorinate class
	}
	
	/*
	 * // SINGLETON DESIGN PATTERN public static class ComparePostalCode implements Comparator<PostalCode> {
	 * 
	 * // implement compare() using code No ComparePostalCode object will be created if the instance variable never used. public final static ComparePostalCode instance = new ComparePostalCode();
	 * 
	 * // private keyword prevents creation of ComparePostalCode objects outside the class private ComparePostalCode() { }
	 * 
	 * // Comparator interface enforces requirement that a compare() method be implemented.
	 */
	public static class CompareCode implements Comparator<PostalCode> {

		// implement compare() using code No CompareCode object will be created if the instance variable never used.
		public final static CompareCode instance = new CompareCode();

		// private keyword prevents creation of CompareCode objects outside the class
		private CompareCode() {
		}

		// Comparator interface enforces requirement that a compare() method be implemented.
		@Override
		public int compare(PostalCode postal1, PostalCode postal2) {
			return postal1.code.compareTo(postal2.code);
		}
	}

	public static class CompareCity implements Comparator<PostalCode> {

		// implement compare() using city No CompareCity object will be created if the instance variable never used.
		public final static CompareCity instance = new CompareCity();

		// private keyword prevents creation of CompareCity objects outside the class
		private CompareCity() {
		}

		// Comparator interface enforces requirement that a compare() method be implemented.
		@Override
		public int compare(PostalCode postal1, PostalCode postal2) {
			return postal1.city.compareTo(postal2.city);
		}
	}

	public static class CompareProvince implements Comparator<PostalCode> {

		// implement compare() using province No CompareProvince object will be created if the instance variable never used.
		public final static CompareProvince instance = new CompareProvince();

		// private keyword prevents creation of CompareProvince objects outside the class
		private CompareProvince() {
		}

		// Comparator interface enforces requirement that a compare() method be implemented.
		@Override
		public int compare(PostalCode postal1, PostalCode postal2) {
			return postal1.province.compareTo(postal2.province);
		}
	}

	public static class CompareLatitude implements Comparator<PostalCode> {

		// implement compare() using latitude No CompareLatitude object will be created if the instance variable never used.
		public final static CompareLatitude instance = new CompareLatitude();

		// private keyword prevents creation of CompareLatitude objects outside the class
		private CompareLatitude() {
		}

		// Comparator interface enforces requirement that a compare() method be implemented.
		@Override
		public int compare(PostalCode coordinate1, PostalCode coordinate2) {
			if (coordinate1.getLatitude() > coordinate2.getLatitude()) {
				return 1;
			} else if (coordinate1.getLatitude() < coordinate2.getLatitude()) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	public static class CompareLongitude implements Comparator<PostalCode> {

		// implement compare() using longitude No CompareLongitude object will be created if the instance variable never used.
		public final static CompareLongitude instance = new CompareLongitude();

		// private keyword prevents creation of CompareLongitude objects outside the class
		private CompareLongitude() {
		}

		// Comparator interface enforces requirement that a compare() method be implemented.
		@Override
		public int compare(PostalCode coordinate1, PostalCode coordinate2) {
			if (coordinate1.getLongitude() > coordinate2.getLongitude()) {
				return 1;
			} else if (coordinate1.getLongitude() < coordinate2.getLongitude()) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
}

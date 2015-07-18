package JUnitTest;

import geodetic.Coordinate;
import postalCode.PostalCodeIndex;
import postalCode.QuadTree;

public class test {

	public static void main(String [] args) {
		Coordinate targetCoord = null;
PostalCodeIndex index = new PostalCodeIndex();
index.find( targetCoord);
		
	}
}

/*


quadTree = new QuadTree(new Coordinate(100,-100), new Coordinate(-100,100), 1);
for (PostalCode postalCode : latitudeOrderList){
  quadTree.add(postalCode);
}


*/
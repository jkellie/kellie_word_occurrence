//Author Name: Jason Kellie
//Date: 03/14/2021
//Program Name: kellie_module8_junit
//Purpose: unit testing
package javafxconsoletextarea;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class FXMLDocumentController implements Initializable {
	@FXML
	private TextArea textAreaUI;
	static TextArea staticTxtArea;
	File file = new File("src/macbeth.txt");
	Scanner scan; {
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	int num = 0;
	Map<String,Integer> map = new HashMap<String, Integer>();


	@FXML
	private void handleButtonAction(ActionEvent event) {

		while (scan.hasNext()) {
			String value = scan.next();
			if (!map.containsKey(value)) map.put(value,1);
			else {
				int count = (map.get(value));
				map.remove(value);
				map.put(value,count+1);
			}
		}

		Set<Map.Entry<String, Integer>> set = map.entrySet();

		List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set);

		Collections.sort( sortedList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare( Map.Entry<String, Integer> x, Map.Entry<String, Integer> y ) {
				return (y.getValue()).compareTo( x.getValue() );
			}
		} );

		System.out.println("Here are the results sorted by frequency:\n");
		for(Map.Entry<String, Integer> i:sortedList) {
			num++;
			System.out.println(num+") " + i.getKey()+" : "+i.getValue());
			if(num > 19) break;
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		staticTxtArea = textAreaUI;
	}

}
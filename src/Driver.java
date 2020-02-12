/**
 * This is test class. For testing all classes, including Automation, Rule, Generation and Cell class.
 * @author Ching Han Huang
 * @version 1.0
 */
import java.io.IOException;
import java.util.Arrays;
public class Driver {

	public static void main(String[] args) throws IOException {
		
		// Cell class
		Cell cell = new Cell();
		cell.setCell('*');
		System.out.println(cell.toString());
		
		// Generation class
		//char[] cha = {'*', '*', '*'};
		//Generation generation = new Generation(cha);

		// Rule class
		Rule rule = new Rule(22);
		// Test convertNumberBase & convertNumToBool method
		char[] ch = rule.convertNumBase();
		for (int i = 0; i < 8; ++i) {			
			System.out.print(ch[i] + "->");
			System.out.print(rule.convertCharToBool(ch[i]));
			System.out.print("  ");
		}
		System.out.println();
		// Test setBoolArr method
		boolean[] boolArr = rule.convertCharArrToBoolArr(ch);
		System.out.println(Arrays.toString(boolArr));
		// Test rules method
		boolean[] boolArr2 = {true, true, true};
		System.out.println(rule.rules(boolArr2));
		
		// Automaton class
		Automaton test = new Automaton("input.txt");
		// Test getRuleNum method
		System.out.println(test.getRuleNum());
		// Test evolve method
		test.evolve(7);
		// Test getTotalSteps method
		//System.out.println(test.getTotalSteps());
		// Test getState method
		//System.out.println(Arrays.toString(test.getState(0)));
		// Test getStateString method
		//System.out.println(test.getStateString(0));
		// Test toString method
		System.out.println(test.toString());
		// Test save method
		//test.save("output-rule30-79cells.txt");
		

	}

}

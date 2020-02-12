/**
 * This class represents how to Evolving ECA by using rules.
 * @author Ching Han Huang
 * @version 1.0
 */
import java.util.Arrays;

public class Rule {
	/**
	 * Field: rule number
	 */
	protected int ruleNum;
	
	/**
	 * Rule class constructor
	 * @param ruleNum
	 */
	public Rule(int ruleNum) {
		this.ruleNum = ruleNum;
	}
	
	/**
	 * Convert integers to base 2.
	 * And add the '0' cell until there are 8 cells in total.
	 * @return a char[] represents a base 2 char array.
	 */
	public char[] convertNumBase() {
		String baseString;
		// Convert integer value to base 2.
		baseString = Integer.toString(this.ruleNum, 2);
		// Use StringBuilder to make total numbers of cells to 8.
		StringBuilder baseStringBuilder = new StringBuilder(baseString);
		for(int i = 0; i < (8 - baseString.length()); ++i) {
			baseStringBuilder.insert(0, '0');
		}
		
		baseString = baseStringBuilder.toString();
		char[] baseCharArr = baseString.toCharArray();
		return baseCharArr;
	}
	/**
	 * Convert char to boolean.
	 * @return boolean value matches original character.
	 */
	public boolean convertCharToBool(char inputChar) {
		if (inputChar == '0') {
			return false;
		}
		else {
			return true;
		}
	}
	/**
	 * Create boolean array from input char array.
	 * @return boolean array matches original character array
	 */
	public boolean[] convertCharArrToBoolArr (char[] inputCharArr) {
		boolean[] boolArr = new boolean[inputCharArr.length];
		for (int i = 0; i < inputCharArr.length; ++i) {
			if (inputCharArr[i] == '0') {
				boolArr[i] = false;
			}
			else {
				boolArr[i] = true;
			}
		}
		return boolArr;
	}
	/**
	 * Use rule number to change the new boolean value of cells
	 * @return boolean value after the given cells by using rules.
	 */
	public boolean rules(boolean[] inputCells) {
		String baseOf3Cells;
		int index;
		char[] cellsCharArr = convertNumBase();
		boolean afterChangeBool = true;
		
		for (int i = 7; 0 <= i && i < 8; --i) {
			baseOf3Cells = Integer.toString(i, 2);
			
			// Add the '0' cell until there are 3 cells in total.
			StringBuilder baseOf3CellsStringBuilder = new StringBuilder(baseOf3Cells);
			for(int j = 0; j < (3 - baseOf3Cells.length()); ++j) {
				baseOf3CellsStringBuilder.insert(0, '0');
			}
			baseOf3Cells = baseOf3CellsStringBuilder.toString();
			char[] baseOf3CellsCharArr = baseOf3Cells.toCharArray();
			boolean[] baseOf3CellsBoolArr = convertCharArrToBoolArr(baseOf3CellsCharArr);

			// Compare boolean array of input cells with the rule of base of 3 cells.
			// And return the new boolean value after applying the rules.
			if (Arrays.equals(inputCells, baseOf3CellsBoolArr)) {
				index = 7 - i;
				char afterChangeChar = cellsCharArr[index];
				afterChangeBool = convertCharToBool(afterChangeChar);
				return afterChangeBool;
			}			
		}
		return afterChangeBool;
	}
	
	
}

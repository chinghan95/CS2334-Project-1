/**
 * This class outputs a file after evolving ECA by using rules.
 * @author Ching Han Huang
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Arrays;


public class Automaton/* extends Rule*/{
	
	private int ruleNum;
	private boolean[] initState;
	private char repFalseSymbol;
	private char repTrueSymbol;
	private int totalNumSteps;
	
	/**
	 * Construct an ECA from a rule number and an array of boolean values.
	 * The values represent the initial states of the cells, with false
	 * and true corresponding to 0 and 1 respectively.
	 * 
	 * Use '0' as the default to represent false if the ECA is not constructed from a text file.
	 * Use '1' as the default to represent true if the ECA is not constructed from a text file.
	 */
	public Automaton(int ruleNum, boolean[] initState) {
		this.ruleNum = ruleNum;
		this.initState = initState;
		this.repFalseSymbol = '0';
		this.repTrueSymbol = '1';
		this.totalNumSteps = 0;
	}
	/**
	 * Construct an ECA from the information in a text file.
	 * The file will be formatted as follows:
	 * <rule-number>
	 * <false-symbol> <true-symbol>
	 * <initial-state>
	 * 
	 */
	public Automaton(String filename) throws IOException {
		// Use BufferedReader to create reader to read the input file
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		
		// The first line indicates that the ECA evolves according to Rule(number).
		line = reader.readLine();
		// Convert String to integer value
		this.ruleNum = Integer.parseInt(line);
		
		// The second line says that '0' (or '*') and '1' (or '@') will represent false and true.
		line = reader.readLine();
		// Remove the space in String
		line = line.replaceAll(" ", "");
		char firstSymbol = line.charAt(0);
		char secondSymbol = line.charAt(1);
		setFalseSymbol(firstSymbol);
		this.repFalseSymbol = getFalseSymbol();
		setTrueSymbol(secondSymbol);
		this.repTrueSymbol = getTrueSymbol();
		
		// The bottom line is the initial state: (number) cells with only the
		// center cell in the true state.
		line = reader.readLine();
		initState = new boolean[line.length()];
		for (int i = 0; i < line.length(); ++i) {
			char symbol = line.charAt(i);
			if(symbol == this.repFalseSymbol) {
				initState[i] = false;
			}
			else if (symbol == this.repTrueSymbol){
				initState[i] = true;
			}
		}
		reader.close();
	}
	/**
	 * Return the Wolfram Code for the rule that governs the evolution of the ECA.
	 */
	public int getRuleNum() {
		return this.ruleNum;
	}
	/**
	 * Evolve the ECA a given number of steps.
	 */
	public void evolve(int numSteps) {
		this.totalNumSteps = numSteps;
		// Create a Rule class object
		Rule rule = new Rule(this.ruleNum);
		// Copy the initial State array to an array called previous generation array.
		boolean[] preGeneration = new boolean[initState.length];
		for (int index = 0; index < initState.length; ++index) {
			preGeneration[index] = initState[index];
		}
		// Create a three cells array which consists middle cell and its left & right neighbors.
		boolean[] threeBoolArr = new boolean[3];
		// Create an array after evolving using the rules.
		boolean[][] afterEvolve = new boolean[numSteps][initState.length];
		for (int i = 0; i < this.totalNumSteps; ++i) {
			for (int j = 0; j < preGeneration.length; ++j) {
				// The leftmost column cast
				if (j == 0) {
					threeBoolArr[0] = preGeneration[preGeneration.length-1];
					threeBoolArr[1] = preGeneration[0];
					threeBoolArr[2] = preGeneration[1];
				}
				// The rightmost column cast
				else if (j == (preGeneration.length-1)) {
					threeBoolArr[0] = preGeneration[preGeneration.length-2];
					threeBoolArr[1] = preGeneration[preGeneration.length-1];
					threeBoolArr[2] = preGeneration[0];
				}
				// Other casts
				else {
					threeBoolArr[0] = preGeneration[j-1];
					threeBoolArr[1] = preGeneration[j];
					threeBoolArr[2] = preGeneration[j+1];
				}
				// Use rules method in Rule class, and assign the new value to afterEvolve array.
				afterEvolve[i][j] = rule.rules(threeBoolArr);
			}
			// Update previous generation value
			preGeneration = afterEvolve[i];
			//System.out.println(Arrays.toString(afterEvolve[i]));
		}
		
	}
	/**
	 * Return the total number of steps that the ECA has evolved.
	 * @return this.totalNumSteps
	 */
	public int getTotalSteps() {		
		return this.totalNumSteps;
	}
	/**
	 * Return an array with the states of the cells after the given step.
	 * The step number must be less than or equal to the total number of steps.
	 * @param stepNum The given step number
	 * @return an array with the states of the cells after the given step.
	 */
	public boolean[] getState(int stepNum) {
		// Check stepNum
		if (stepNum == 0) {
			return initState;
		}
		if (stepNum < 0 && stepNum > totalNumSteps) {
			System.out.println("The input step number is error.");
		}
		boolean[] getStateBool = new boolean[initState.length];
		// Create a Rule class object
		Rule rule = new Rule(this.ruleNum);
		// Copy the initial State array to an array called previous generation array.
		boolean[] preGeneration = new boolean[initState.length];
		for (int index = 0; index < initState.length; ++index) {
			preGeneration[index] = initState[index];
		}
		// Create a three cells array which consists middle cell and its left & right neighbors.
		boolean[] threeBoolArr = new boolean[3];
		// Create an array after evolving using the rules.
		boolean[][] afterEvolve = new boolean[stepNum][initState.length];
		for (int i = 0; i < stepNum; ++i) {
			for (int j = 0; j < preGeneration.length; ++j) {
				// The leftmost column cast
				if (j == 0) {
					threeBoolArr[0] = preGeneration[preGeneration.length-1];
					threeBoolArr[1] = preGeneration[0];
					threeBoolArr[2] = preGeneration[1];
				}
				// The rightmost column cast
				else if (j == (preGeneration.length-1)) {
					threeBoolArr[0] = preGeneration[preGeneration.length-2];
					threeBoolArr[1] = preGeneration[preGeneration.length-1];
					threeBoolArr[2] = preGeneration[0];
				}
				// Other casts
				else {
					threeBoolArr[0] = preGeneration[j-1];
					threeBoolArr[1] = preGeneration[j];
					threeBoolArr[2] = preGeneration[j+1];
				}
				// Use rules method in Rule class, and assign the new value to afterEvolve array.
				afterEvolve[i][j] = rule.rules(threeBoolArr);
			}
			// Update previous generation value
			preGeneration = afterEvolve[i];
			//System.out.println(Arrays.toString(afterEvolve[i]));
		}
		getStateBool = afterEvolve[stepNum - 1];
	
		return getStateBool;
	}
	/**
	 * Return a String that represents the states of the cells after the given step.
	 * The String has one character for each cell, and the symbols used to represent 
	 * false and true are those returned by getFalseSymbol() and getTrueSymbol().
	 * @param stepNum
	 * @return a String represented the states of the cells
	 */
	public String getStateString(int stepNum) {
		// Check stepNum
		if (stepNum < 0 && stepNum > totalNumSteps) {
			return "The input step number is error.";
		}
		
		// Return the false and true character.
		char repFalse = getFalseSymbol();
		char repTrue = getTrueSymbol();
		// For initial state:
		if(stepNum == 0) {
			String initStateString = "";
			for (int i = 0; i < initState.length; ++i) {
				if (initState[i] == false) {
					initStateString = initStateString + repFalse;
				}
				else if(initState[i] == true) {
					initStateString = initStateString + repTrue;
				}
			}
			return initStateString;
		}
		
		// For other state:
		// Create a string represents the states of the cells after the given step.
		else {
			String stateString = "";
			
			boolean[] getStateBool = getState(stepNum);
			// Convert false (or true) value to false (or true) symbol.
			for (int i = 0; i < getStateBool.length; ++i) {
				if (getStateBool[i] == false) {
					stateString = stateString + repFalse;
				}
				else if(getStateBool[i] == true) {
					stateString = stateString + repTrue;
				}
			}
			return stateString;
		}
	}
	/**
	 * Return a String that represents the entire evolution of the ECA.
	 * The String consists of the state String for every generation joined
	 * together by newline characters.
	 */
	public String toString() {
		// Create the initial character String.
		String initECA = "";
		// Return the false and true character.
		char repFalse = getFalseSymbol();
		char repTrue = getTrueSymbol();
		// Convert false (or true) value to false (or true) symbol.
		for (int i = 0; i < initState.length; ++i) {
			if (initState[i] == false) {
				initECA = initECA + repFalse;
			}
			else if(initState[i] == true) {
				initECA = initECA + repTrue;
			}
		}
		// Create the String evolved by rules.
		String entireECA = initECA + '\n';
		String stateString;
		for (int i = 1; i < (this.totalNumSteps + 1); ++i) {
			stateString = getStateString(i);
			entireECA = entireECA + stateString + '\n';
		}
		// Remove the final newline character so an extra line is not printed 
		// to the console when printing the String with System.out.println.
		StringBuilder tempEntireECA = new StringBuilder(entireECA);
		if (tempEntireECA.length() > 0) {
			tempEntireECA.deleteCharAt(tempEntireECA.length() - 1);
		}
		return tempEntireECA.toString();
	}
	/**
	 * Save the output of toString() to a file with the given name.
	 * (Overwrite the content of the file if it already exists.
	 */
	public void save(String filename) throws IOException {
		// Use BufferedWriter to create writer to write a file.
		BufferedWriter bw = null;
		File file = new File(filename);
		try {
			/**
			 * This logic will make sure that the file gets created.
			 */
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(toString());
			System.out.println("File written Successfully");
		}catch (IOException ioexcep){
			ioexcep.printStackTrace();
		}
		finally 
		{
			try {
				if(bw != null) {
					bw.close();
				}
			}catch (Exception excep) {
				System.out.println("Error in closing the BufferedWriter" + excep);
			}
		}
	}
	/**
	 * Set the character that represent false;
	 * @param Symbol
	 */
	public void setFalseSymbol(char Symbol) {
		this.repFalseSymbol = Symbol;
	}
	/**
	 * Return the character that represents false.
	 * @return falseSymbol;
	 */
	public char getFalseSymbol() {
		return repFalseSymbol;
	}
	/**
	 * Set the character that represents false.
	 * @param Symbol
	 */
	public void setTrueSymbol(char Symbol) {
		this.repTrueSymbol = Symbol;
	}
	/**
	 * Return the character that represents true. 
	 * @return trueSymbol
	 */
	public char getTrueSymbol() {
		return repTrueSymbol;
	}
	
	

}

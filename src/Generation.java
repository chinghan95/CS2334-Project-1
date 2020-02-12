/**
 * This class creates generation of state.
 * @author Ching Han Huang
 * @version 1.0
 *
 */
public class Generation extends Cell{
	/**
	 * Field: char array type for generationArr
	 */
	private char[] generationArr;
	
	/**
	 * Set the contents of generation.
	 * @param charArr
	 */
	public void setGeneration(char[] charArr) {
		this.generationArr = charArr;
	}
	
	/**
	 * Get generation info.
	 * @return generationArr
	 */
	public char[] getGeneration() {
		return this.generationArr;
	}
	
	/**
	 * Print info of generation.
	 */
	@Override
	public String toString() {
		return "Generation: " + String.valueOf(this.generationArr);
	}
	

}

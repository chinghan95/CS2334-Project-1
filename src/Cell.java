/**
 * This class creates a cell.
 * @author Ching Han Huang
 * @version 1.0
 */
public class Cell {
	/**
	 * Field: char type for cell
	 */
	protected char cell;
	
	/**
	 * Set a character to a cell.
	 * @param character
	 */
	public void setCell(char character) {
		this.cell = character;
	}
	
	/**
	 * Get cell
	 * @return cell
	 */
	public char getCell() {
		return this.cell;
	}
	
	/**
	 * Print info of cell.
	 */
	public String toString() {
		return "Cell's symbol: " + String.valueOf(this.cell);
	}
}

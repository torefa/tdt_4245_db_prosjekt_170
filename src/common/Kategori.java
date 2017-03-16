package common;

/**
 * Represents the corresponding table in the database. 
 * Due to implementation sometimes only represeted by its id.
 *
 * @author Group 170
 */
public class Kategori {
	private int id;
	private String name;
	private Kategori parent;
	
	/**
	* Creates a new category.
	*
	* @author Group 170
	* @param id Unique key in database.
	* @param name One word description of kategori.
	* @param parent Supercategory, if null there is no supercategory.
	*/
	public Kategori(int id, String name, Kategori parent){
		this.id = id;
		this.name = name;
		this.parent = parent;
	}
	
	public void setParent(Kategori parent){
		this.parent = parent;
	}
	
	public Kategori getParent(){
		return this.parent;
	}
	
	public int getId(){
		return this.id;
	}
	
	/**
	* Puts together the name of the category and all supercategories
	* @author Group 170
	* @return Full name seperated by :, supercategory in front of subcategory.
	*/
	public String getFullName(){
		String ret = "";
		if(this.parent != null){
			ret += this.parent.getFullName() + ":";
		}
		ret += this.name;
		return ret;
	}
	
	/**
	* Alias for getFullName
	* @author Group 170
	*/
	public String toString(){
		return this.getFullName();
	}
}

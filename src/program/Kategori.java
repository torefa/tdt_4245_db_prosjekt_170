package program;

public class Kategori {
	private int id;
	private String name;
	private Kategori parent;
	
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
	public String getFullName(){
		String ret = "";
		if(this.parent != null){
			ret += this.parent.getFullName() + ":";
		}
		ret += this.name;
		return ret;
	}
	public String toString(){
		return this.getFullName();
	}
}

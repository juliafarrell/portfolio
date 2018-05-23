package sheshells;

import java.io.Serializable;

public class FileElement implements Serializable {

	String name;
	boolean isDirectory;
	
	public FileElement(String name, boolean isDirectory)
	{
		this.name = name;
		this.isDirectory = isDirectory;
	}
	
	public boolean isDirectory() { return isDirectory; }
	
	public String toString() { return "" + name; }
	
}

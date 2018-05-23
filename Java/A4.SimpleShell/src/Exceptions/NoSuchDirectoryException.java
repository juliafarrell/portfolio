package Exceptions;

public class NoSuchDirectoryException extends Exception {
	
	public NoSuchDirectoryException(String name) 
	{
		System.out.println("There is no directory with the name: " + name);
	}
	
	public NoSuchDirectoryException() 
	{
		System.out.println("The requested directory doesn't exist");
	}

}

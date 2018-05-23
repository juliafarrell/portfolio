package Exceptions;

public class IsDirectoryException extends Exception {

	public IsDirectoryException()
	{
		System.out.println("Element is a directory, not a file");
	}
}

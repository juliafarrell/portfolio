package Exceptions;

public class IsFileException extends Exception {
	
	public IsFileException()
	{
		System.out.println("File element is a file, not a directory");
	}

}

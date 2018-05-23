package Exceptions;

public class NoSuchFileException extends Exception {
	
	public NoSuchFileException()
	{
		System.out.println("No file with requested name in this directory");
	}

}

package Exceptions;

public class DirectoryNotEmptyException extends Exception {

	public DirectoryNotEmptyException()
	{
		System.out.println("The directory has files in it, can't remove");
	}
	
}

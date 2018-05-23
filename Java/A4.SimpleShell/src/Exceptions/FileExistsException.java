package Exceptions;

public class FileExistsException extends Exception
{

	public FileExistsException()
	{
		super("There is already a file with this name");
	}
	
	public FileExistsException(String file)
	{
		super("There is already a file named" + file);
	}
	
}

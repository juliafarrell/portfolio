package sheshells;

import java.io.Serializable;

import Exceptions.DirectoryNotEmptyException;
import Exceptions.FileExistsException;
import Exceptions.IsDirectoryException;
import Exceptions.IsFileException;
import Exceptions.NoSuchDirectoryException;
import Exceptions.NoSuchFileException;

public class FileSystem implements Serializable {

	GeneralTree<FileElement> fileSystem;
	Position<FileElement> currentFileElement;
	
	public FileSystem()
	{
		fileSystem = new GeneralTree<FileElement>();
		currentFileElement = fileSystem.addRoot(new FileElement("", true));
	}
	
	/**
	 * If the file name already exists within the current directory, throws FileExistsException
	 * Otherwise, does nothing
	 * @param name
	 * @throws FileExistsException
	 */
	public void checkMakeFile(String name) throws FileExistsException
	{
		Iterable<Position<FileElement>> toCheck = fileSystem.children(currentFileElement);
		if (toCheck != null)
		{
			for (Position<FileElement> fe : toCheck )
			{
				if (fe.toString().equals(name))
				{
					throw new FileExistsException(name);
				}
			}
		}
	}
	
	/**
	 * Prints all the children of the current directory
	 */
	public String ls()
	{
		String toReturn = "";
		Iterable<Position<FileElement>> kids = fileSystem.children(currentFileElement);
		if (kids == null) return "Nothing to list";
		{
			for (Position<FileElement> fe : kids)
			{
				toReturn += fe.toString() + ", ";
			}
		}
		return toReturn;
	}
	
	/**
	 * Add new file child node to current directory
	 * Throw exception if already exists
	 * @throws FileExistsException 
	 */
	public void touch(String name) throws FileExistsException
	{
		checkMakeFile(name);
		FileElement add = new FileElement(name, false);
		fileSystem.addChild(currentFileElement, add);
	}
	
	/**
	 * Add new directory child node to current directory
	 * Throw exception if already exists
	 * @throws FileExistsException 
	 */
	public void mkdir(String name) throws FileExistsException
	{
		checkMakeFile(name);
		FileElement add = new FileElement(name, true);
		fileSystem.addChild(currentFileElement, add);
	}
	
	/**
	 * Print full path name of current directory starting with root
	 */
	public String pwd()
	{
		StringBuilder toPrint = new StringBuilder("");
		StackLinked<String> theStack = new StackLinked<>();
		Position<FileElement> current = currentFileElement;
		do {
			theStack.push("/" + current.getValue().toString());
			current = fileSystem.parent(current);}
		while (fileSystem.parent(current) != null);
		while (theStack.size() > 0)
		{
			toPrint.append(theStack.pop());
		}
		return toPrint.toString();
	}

	
	/**
	 * Changes directory to specified name of new directory
	 * If the current directory doesn't have a child directory with this name, throws
	 */
	public void cd(String name) throws NoSuchDirectoryException
	{
		Iterable<Position<FileElement>> toCheck = fileSystem.children(currentFileElement);
		if (toCheck != null)
		{
			for (Position<FileElement> fe : toCheck )
			{
				if (fe.toString().equals(name) && fe.getValue().isDirectory() )
				{
					currentFileElement = fe;
				}
			}
		}
		else {throw new NoSuchDirectoryException();}
	}
	
	/**
	 * Removes the file "name" from the currentDirectory
	 * Throws if "name" refers to a directory or doesn't exist
	 */
	public void rm(String name) throws IsDirectoryException, NoSuchFileException
	{
		Iterable<Position<FileElement>> toCheck = fileSystem.children(currentFileElement);
		if (toCheck != null)
		{
			for (Position<FileElement> fe : toCheck )
			{
				if (fe.toString().equals(name) && fe.getValue().isDirectory() )
				{
					throw new IsDirectoryException();
				}
				if (fe.toString().equals(name) && fe.getValue().isDirectory() == false)
				{
					fileSystem.remove(fe);
				}
			}
		}
		else {throw new NoSuchFileException();}
	}
	
	/**
	 * Removes the directory "name" from the currentDirectory
	 * Throws if "name" refers to a file or doesn't exist
	 * @param name
	 * @throws NoSuchDirectoryException
	 * @throws IsFileException
	 */
	public void rmdir(String name) 
			throws NoSuchDirectoryException, IsFileException, DirectoryNotEmptyException
	{
		Iterable<Position<FileElement>> toCheck = fileSystem.children(currentFileElement);
		if (toCheck != null)
		{
			for (Position<FileElement> fe : toCheck )
			{
				if (fe.toString().equals(name) && fe.getValue().isDirectory() == false)
				{
					throw new IsFileException();
				}
				else if (fe.toString().equals(name) && fe.getValue().isDirectory())
				{
					if (fileSystem.remove(fe) == false)
					{
						throw new DirectoryNotEmptyException();
					}
				}
			}
		}
		else {throw new NoSuchDirectoryException();}
	}
	
	/**
	 * Pretty print the tree rooted at currentDirectory using preOrder traversal
	 */
	public void tree()
	{
		Iterable<Position<FileElement>> toPrint = fileSystem.PreOrder();
		for (Position<FileElement> p : toPrint)
		{
			int depth = fileSystem.getDepth(p);
			for (int i = 0; i < depth; i++)
			{
				System.out.print("   ");
			}
			System.out.println(p.getValue());
		}
	}
	
}

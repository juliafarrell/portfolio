package sheshells;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import Exceptions.DirectoryNotEmptyException;
import Exceptions.FileExistsException;
import Exceptions.IsDirectoryException;
import Exceptions.IsFileException;
import Exceptions.NoSuchDirectoryException;
import Exceptions.NoSuchFileException;

public class Driver 
{
	public static void main(String args[])
	{
		FileSystem fileSystem = null;
		try {
			FileInputStream fis = new FileInputStream("fs.data");
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				fileSystem = (FileSystem) ois.readObject();
				ois.close();
			} catch (ClassNotFoundException e) {
				System.out.println("Object in fs.data is not of type FileSystem");
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		if (fileSystem == null)
		{
			fileSystem = new FileSystem();
		}
		
		Scanner keyboard = new Scanner(System.in);
		boolean quit = false;
		while(quit == false)
		{
			System.out.println("Please enter a command");
			String in = keyboard.next();
			if (in.equalsIgnoreCase("ls"))
			{
				System.out.println(fileSystem.ls());
			}
			else if (in.equalsIgnoreCase("touch"))
			{
				System.out.println("What is the name of the file you want to add?");
				String toTouch = keyboard.next();
				try {
					fileSystem.touch(toTouch);
				} catch (FileExistsException e) {
					e.printStackTrace();
					e.getMessage();
				}
			}
			else if (in.equalsIgnoreCase("mkdir"))
			{
				System.out.println("What is the name of the directory you want to make?");
				try {
					fileSystem.mkdir(keyboard.next());
				} catch (FileExistsException e) {
					e.printStackTrace();
					e.getMessage();
				}
			}
			else if (in.equalsIgnoreCase("pwd"))
			{
				System.out.println(fileSystem.pwd());
			}
			else if (in.equalsIgnoreCase("cd"))
			{
				System.out.println("What do you want to change the directory to?");
				try {
					fileSystem.cd(keyboard.next());
				} catch (NoSuchDirectoryException e) {
					e.printStackTrace();
					e.getMessage();
				}
			}
			else if (in.equalsIgnoreCase("rm"))
			{
				System.out.println("What is the name of the file you want to remove?");
				try {
					fileSystem.rm(keyboard.next());
				} catch (IsDirectoryException | NoSuchFileException e) {
					e.printStackTrace();
					e.getMessage();
				}
			}
			else if (in.equalsIgnoreCase("rmdir"))
			{
				System.out.println("What is the name of the directory you want to remove?");
				try {
					fileSystem.rmdir(keyboard.next());
				} catch (NoSuchDirectoryException | IsFileException | DirectoryNotEmptyException e) {
					e.printStackTrace();
					e.getMessage();
				}
			}
			else if (in.equalsIgnoreCase("tree"))
			{
				fileSystem.tree();
			}
			else if (in.equalsIgnoreCase("quit"))
			{
				quit = true;
				keyboard.close();
				break;
			}
			else
			{
				System.out.println("Not a valid command");
				System.out.println("Valid commands: rmdir, rm, cd, pwd, mkdir, touch, ls");
			}
			
		}
		// Save once user quits
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("fs.data");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(fileSystem);
			oos.close();
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
}

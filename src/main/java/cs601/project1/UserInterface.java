package cs601.project1;

import java.util.Scanner;

public class UserInterface {

	public void startInterface()	{

		//cmd - exit  to end interface screen
		startScanning();
	}

	public void startScanning()	{
		String cmd = "start";
		try(
				Scanner scan = new Scanner(System.in);	
				)	{

			while(!cmd.equals("exit"))	{
				System.out.print("Enter command line input: ");
				cmd = scan.nextLine();
				System.out.println("command line input was "+ cmd);
				this.cmdFinder(cmd);
				
			}
			System.out.print("UserInterface closed ");			
		}
		//to find the exact function to calll
		//first checking if user input is correct/valid
		

	}
	
	public void cmdFinder(String cmd)	{
		String[] cmdList = cmd.split(" ");
		System.out.println("cmd List first: "+ cmdList[0]);
		System.out.println("cmd list second: "+cmdList[1]);
		if(cmdValidityCheck(cmdList))	{
			//process cmd
			System.out.println("Valid input");
			cmdProcessor(cmdList[0], cmdList[1]);
		}
		else	{
			System.out.println("Please try again");
		}
	}
	
	public boolean cmdValidityCheck(String[] cmdList)	{
		
		String validCmds = "find, reviewsearch, qasearch, reviewpartialsearch, qapartialsearch";
		
		if(cmdList.length == 2)	{
			System.out.println("first loop");
			if(validCmds.contains(cmdList[0].toLowerCase()))	{
				System.out.println("second loop");
				if(cmdList[1].matches("[a-zA-Z0-9]+"))	{
					System.out.println("third loop");
					return true;
				}
			}
		}
		return false;
	}
	
	public void cmdProcessor(String cmdMethod, String cmdTerm)	{
		 
	}


	public static void main(String[] args)	{

		UserInterface ui = new UserInterface();
		ui.startInterface();
	}



}

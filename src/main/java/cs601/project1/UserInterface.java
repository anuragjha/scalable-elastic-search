package cs601.project1;

import java.util.Scanner;

public class UserInterface {
	
	CmdProcessor cmdProcessor;
	
	public UserInterface()	{
		cmdProcessor = new CmdProcessor();
	}

	public void startInterface()	{

		//cmd - exit  to end interface screen
		cmdProcessor.getHelp();
		startScanning();
	}

	private void startScanning()	{
		String cmd = "start";
		try(
				Scanner scan = new Scanner(System.in);	
				)	{

			while(!cmd.toLowerCase().equals("exit"))	{
				System.out.print("Enter command line input: ");
				cmd = scan.nextLine();
				//System.out.println("command line input was "+ cmd);
				String[] cmdList = cmd.split(" ");
				if(cmd.toLowerCase().equals("exit"))	{
					System.out.print("UserInterface closed ");	
					System.exit(0);
				}
				if(cmdProcessor.isCmdValid(cmdList))	{
					this.cmdFinder(cmdList);
				}
				else	{
					System.out.println("Input command not valid, see help");
				}

			}
			System.out.println("Application Closed ");	
			System.exit(0);
		}
		//to find the exact function to calll
		//first checking if user input is correct/valid


	}




	private void cmdFinder(String[] cmdList)	{
		//String[] cmdList = cmd.split(" ");
		//System.out.println("cmd List first: "+ cmdList[0]);
		//System.out.println("cmd list second: "+cmdList[1]);
		if(cmdProcessor.isCmdValid(cmdList))	{
			//process cmd
			//System.out.println("Valid input");
			if(cmdList.length == 2)	{
				cmdProcessor.processCmd(cmdList[0].toLowerCase(), 
						cmdList[1].replaceAll("[^A-Za-z0-9]", "").toLowerCase());
			}					
			else	{
				cmdProcessor.processCmd(cmdList[0].toLowerCase());
			}
		}
		else	{
			System.out.println("Please try again");
		}
	}



	
	public static void main(String[] args)	{

		UserInterface ui = new UserInterface();
		ui.startInterface();
	}



}

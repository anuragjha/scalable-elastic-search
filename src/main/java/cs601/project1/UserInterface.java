package cs601.project1;

import java.util.Scanner;

public class UserInterface {
/*
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
			cmdProcessor(cmdList[0].toLowerCase(), cmdList[1].toLowerCase());
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

		switch(cmdMethod)	{
		case "find"	: this.asinFind(cmdTerm);
		break;
		case "reviewsearch" : this.reviewSearch(cmdTerm);
		break;
		case "qasearch"	: this.qaSearch(cmdTerm);
		break;
		case "reviewpartialsearch" : this.reviewPartialSearch(cmdTerm);
		break;
		case "qapartialsearch" : this.qaPartialSearch(cmdTerm);
		break;
		}

	}


	private void asinFind(String cmdTerm)	{
		if(AmazonDataStore.ONE.reviewAsinDataStore.get(cmdTerm) != null)	{
			StringBuilder outputBuilder = new StringBuilder();
			outputBuilder.append(AmazonDataStore.ONE.reviewAsinDataStore.get(cmdTerm).toString());
			outputBuilder.append(AmazonDataStore.ONE.quesAnsAsinDataStore.get(cmdTerm).toString());
			System.out.println(outputBuilder.toString());
		}	else	{
			System.out.println("Couldn't find asin in database");
		}

	}

	private void reviewSearch(String cmdTerm)	{
		//AmazonDataStore.ONE.reviewWordDataStore.get(cmdTerm).getInvertedIndexValues();
		if(AmazonDataStore.ONE.reviewWordDataStore.get(cmdTerm) != null)	{
			System.out.println("Finding in ReviewWordDataStore: "); 
			//AmazonDataStore.ONE.reviewWordDataStore.get(cmdTerm).getInvertedIndexValues().toString());
			AmazonDataStore.ONE.reviewWordDataStore.get(cmdTerm).getInvertedIndexValues();
		}
		else	{
			System.out.println("Couldn't find any term in Reviews");
		}
	}

	private void qaSearch(String cmdTerm)	{
		//AmazonDataStore.ONE.quesAnsWordDataStore.get(cmdTerm).getInvertedIndexValues();
		if(AmazonDataStore.ONE.quesAnsWordDataStore.get(cmdTerm) != null)	{
			System.out.println("Finding in QuesAnsWordDataStore: " + 
					AmazonDataStore.ONE.quesAnsWordDataStore.get(cmdTerm).getInvertedIndexValues().toString());
		}
		else	{
			System.out.println("Couldn't find any term in QuesAns");
		}
	}

	private void reviewPartialSearch(String cmdTerm)	{
		//get the key set, iterate over the keyset to check if the cmdTerm is contained in 
		// the which all keys, use string builder to append all the the reviews 
		
	}

	private void qaPartialSearch(String cmdTerm)	{

	}



	public static void main(String[] args)	{

		UserInterface ui = new UserInterface();
		ui.startInterface();
	}

*/

}

/**
 * 
 */
package cs601.project1;

import java.io.File;

/**
 * @author anuragjha
 *
 */
public class CmdLineArgsValidator {

	public boolean check(String[] args)	{
		return validateCmd(args);
	}

	private boolean validateCmd(String[] args)	{
		if(checkArgLength(args))	{
			if(checkArgIfCorrect(args))	{
				if(checkIfFileExists(args))	{
					return true;
				}
				else	{
					System.out.println("Cannot find either one or both the file.");
					return false;
				}
			}
			else	{
				System.out.println("Please input line params in specified format");
				System.out.println("-reviews <review_file_name.json> -qa <qa_file_name.json>");
				return false;
			}
		}
		else	{
			System.out.println("Please input 4 cmd line params: "
					+ "-reviews <review_file_name.json> -qa <qa_file_name.json>");
			return false;
		}
	}


	private boolean checkArgLength(String[] args)	{
		if(args.length == 4)	{
			return true;
		}
		return false;
	}


	private boolean checkArgIfCorrect(String[] args)	{
		if( (args[0].equals("-reviews")) && (args[2].equals("-qa"))
				&& (args[1].endsWith(".json")) 
				&& (args[3].endsWith(".json")) )	{
			return true;
		}
		return false;
	}


	private boolean checkIfFileExists(String[] args)	{
		if((new File(args[1]).exists()) && (new File(args[3]).exists()))	{
			return true;
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

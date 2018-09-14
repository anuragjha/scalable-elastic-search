/**
 * 
 */
package cs601.project1;

/**
 * @author anuragjha
 *
 */
public class CmdProcessor {

	public boolean isCmdValid(String[] cmdList)	{
		return cmdValidityCheck(cmdList);
	}
	
	
	private boolean cmdValidityCheck(String[] cmdList)	{

		String validCmds = "find, reviewsearch, qasearch, reviewpartialsearch, qapartialsearch";
		//String cmdList[] = cmd.split(" ");
		if(cmdList.length == 1)	{
			if(cmdList[0].equals("help"))	{
				return true;
			}
		}
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
	
	
	public void processCmd(String cmdMethod, String cmdTerm)	{

		switch(cmdMethod)	{
		case "find"	: System.out.println(this.asinFind(cmdTerm));
		break;
		case "reviewsearch" : System.out.println(this.reviewSearch(cmdTerm));
		break;
		case "qasearch"	: System.out.println(this.qaSearch(cmdTerm));
		break;
		case "reviewpartialsearch" : System.out.println(this.reviewPartialSearch(cmdTerm));
		break;
		case "qapartialsearch" : System.out.println(this.qaPartialSearch(cmdTerm));
		break;
		}

	}

	public void processCmd(String cmdMethod)	{
		switch(cmdMethod)	{
		case "help"	: System.out.println(help());
		break;
		}
	}

	private String asinFind(String cmdTerm)	{
		//search from hashmap of recid, AmazonReviews
		//search from hashmap of recid, AmazonReviews
		StringBuilder output = new StringBuilder();

		for(AmazonReviews review : AmazonDataStore.ONE.reviewDataStore.values())	{
			if(review.getAsin().equals(cmdTerm))	{
				output.append(review.toString());
			}
		}

		for(AmazonQuesAns quesAns : AmazonDataStore.ONE.quesAnsDataStore.values())	{
			if(quesAns.getAsin().equals(cmdTerm))	{
				output.append(quesAns.toString());
			}
		}

		if(output.length() > 1)	{
			return output.toString();
		}
		else	{
			return output.append("No results found").toString();
		}

	}

	private String reviewSearch(String cmdTerm)	{
		StringBuilder output = new StringBuilder();
		if(AmazonDataStore.ONE.reviewWordDataStore.getIndex().containsKey(cmdTerm))	{
			for(int recordId : AmazonDataStore.ONE.reviewWordDataStore.searchWord(cmdTerm))	{
				 output.append(AmazonDataStore.ONE.reviewDataStore.get(recordId).toString());
			}
			return output.toString();
		}
		else	{
			return output.append("No results found").toString();
		}
		
	}

	private String qaSearch(String cmdTerm)	{
		StringBuilder output = new StringBuilder();
		if(AmazonDataStore.ONE.quesAnsWordDataStore.getIndex().containsKey(cmdTerm))	{
			for(int recordId : AmazonDataStore.ONE.quesAnsWordDataStore.searchWord(cmdTerm))	{
				 output.append(AmazonDataStore.ONE.quesAnsDataStore.get(recordId).toString());
			}
			return output.toString();
		}
		else	{
			return output.append("No results found").toString();
		}
		
	}

	private String reviewPartialSearch(String cmdTerm)	{
		StringBuilder output = new StringBuilder();
		for(String word : AmazonDataStore.ONE.reviewWordDataStore.getIndex().keySet())	{
			if(word.contains(cmdTerm))	{
				//for each word match call ->
				//reviewSearch(String cmdTerm);
				output.append(reviewSearch(word));
			}
		}
		if(output.length() > 1)	{
			return output.toString();
		}
		else	{
			return output.append("No results found").toString();
		}


	}

	private String qaPartialSearch(String cmdTerm)	{
		StringBuilder output = new StringBuilder();
		for(String word : AmazonDataStore.ONE.quesAnsWordDataStore.getIndex().keySet())	{
			if(word.contains(cmdTerm))	{
				//for each word match call ->
				//qaSearch(String cmdTerm);
				output.append(qaSearch(word));
			}
		}
		if(output.length() > 1)	{
			return output.toString();
		}
		else	{
			return output.append("No results found").toString();
		}

	}


	private String help()	{
		return "Valid Commands are:\nfind <asin>\nreviewsearch <term>\nqasearch <term>\n"
				+ "reviewpartialsearch <term>\nqapartialsearch <term>";
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * 
 */
package cs601.project1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author anuragjha
 *
 */
public enum DataStore1 {


	ONE;

	//data of the whole record; key - record id, value - whole record data
	//HashMap<String, String> recordStore = new HashMap<String, String>();
	HashMap<String, String> rvRecordStore = new HashMap<String, String>();
	HashMap<String, String> qaRecordStore = new HashMap<String, String>();

	//KeyWordStore - key is word & value is String of record ids and freq count
	//sample key, value
	// key - rainbow, value - rv+8,rv2+1,rv5+4,rv7+3,qa+4,qa1+2,qa3+2
	//HashMap<String, StringBuilder> keyWordStore = new HashMap<String, StringBuilder>();
	HashMap<String, String> rvKeyWordStore = new HashMap<String, String>();
	HashMap<String, String> qaKeyWordStore = new HashMap<String, String>();


	public void updateRvDataStore(Reviews review)	{
		//	new DataStoreProcessor()

		//updating rv Record Store //
		rvRecordStore.put(review.getRecordId(), review.toString());


		//updating rv KeyWord Store //
		String[] wordsInRecord = review.getWords(); //creates string of lowercase words of text in reviewText and Summary
		for(String word : wordsInRecord)	{
			if(DataStore1.ONE.rvKeyWordStore.containsKey(word))	{
				//build value string
				updateWordInRvKeyWordDataStore(word, review.getRecordType(), review.getRecordId());
			}
			else{
				//add word to keyWordStore
				//stringbuilder - rv+1,review.getRecordId+1
				addNewWordInRvKeyWordDataStore(word, review.getRecordType(), review.getRecordId());
			}
		}
	}

	public void updateQaDataStore(QuesAns qa)	{

		//updating qa record Store
		qaRecordStore.put(qa.getRecordId(), qa.toString());

		//updating qa keyWord store
		String[] wordsInRecord = qa.getWords();
		for(String word : wordsInRecord)	{
			if(DataStore1.ONE.qaKeyWordStore.containsKey(word))	{
				//building value string
				updateWordInQaKeyWordDataStore(word, qa.getRecordType(), qa.getRecordId());
			}
			else	{
				//add word to qa keyWordStore
				//stringbuilder - qa+1,qa.getRecordId+1
				addNewWordInQaKeyWordDataStore(word, qa.getRecordType(), qa.getRecordId());
			}
		}
	}

	public void addNewWordInRvKeyWordDataStore(String word, String recordType ,String recordId)	{
		//StringBuilder value = new StringBuilder();
		//value = recordType+1,recordId+1
		String value = new String(recordType+"-1,"+recordId+"-1,");
		//value.append(recordType+"-1,"+recordId+"-1,");
		DataStore1.ONE.rvKeyWordStore.put(word, value);
	}

	public void addNewWordInQaKeyWordDataStore(String word, String recordType, String recordId)	{
		//StringBuilder value = new StringBuilder();
		//value = recordType+1,recordId+1
		String value = new String(recordType+"-1,"+recordId+"-1,");
		//value.append(recordType+"-1,"+recordId+"-1,");
		DataStore1.ONE.qaKeyWordStore.put(word, value);
	}


	//converting to tree hashmap as order of entry needed // no back to hash map --- Tree map taking time ?
	public void updateWordInRvKeyWordDataStore(String word, String recordType, String recordId)	{ 
		//StringBuilder value = new StringBuilder();
				String value = DataStore1.ONE.rvKeyWordStore.get(word);
				boolean updated = false;

				//System.out.println(value);

				String[] wordValue = value.split(",");
				HashMap<String,Integer> wordValueMap = new HashMap<String, Integer>(); // tmp hash map for calculations //treemap
				//System.out.println("Comparing"+wordValue.length+"and "+recordType);
				//if(wordValue.toString().contains(recordType))	{
					//System.out.println("wordValue:"+wordValue[0]); 
					// looping through each recordId+count pair of a given word in DataStore
					for(int i = 0; i < wordValue.length; i++)	{ //add hashmap inside to store and and increment
						String[] recNCount = wordValue[i].split("-"); //spliting recordId+count pair
						//String thisRec = recNCount[0];
						//int thisCount = Integer.parseInt(recNCount[1]);
						//wordValueMap.put(thisRec, thisCount);
						wordValueMap.put(recNCount[0], Integer.parseInt(recNCount[1]));
					} //now here data is in hash map
					//now comparing the previous data with a new =>  word-recordId details

					//updating the count for recordType
					//if(wordValueMap.containsKey(recordType))	{
					int thisTypeCount = wordValueMap.get(recordType);
					//thisTypeCount = thisTypeCount + 1;
					wordValueMap.put(recordType, thisTypeCount + 1);
					//updating count for individual recordID
					if(wordValueMap.containsKey(recordId))	{
						int thisRecordCount = wordValueMap.get(recordId);
						//thisRecordCount += 1;
						wordValueMap.put(recordId, thisRecordCount + 1);
					}
					else {
						//new record
						wordValueMap.put(recordId, 1);
						//	}
					} // hashmap updated for recordID 


					//converting Hashmap to StringBuilder, so that keyWordHashMap can update its record
					//for(Map.Entry<String, Integer> entry : userRevCount.entrySet())
					StringBuilder newValue = new StringBuilder();
					//starting with recordType+count,
					//newValue.append(recordType+"+"+
					for(Map.Entry<String, Integer> entry : wordValueMap.entrySet())	{
						//starting with recordType+count
						newValue.append(entry.getKey()+"-"+entry.getValue()+",");
					} // newValue is a StringBuilder object -> storing value string for keyWordStore

					//updating the DataStore with the newValue
					//System.out.println("new Value: "+newValue);
					DataStore1.ONE.rvKeyWordStore.put(word, newValue.toString());
					//DataStore.ONE.keyWordStore.put(word, value); // updating new value in DataStore
					
				//}


	}


	public void updateWordInQaKeyWordDataStore(String word, String recordType, String recordId)	{
		//StringBuilder value = new StringBuilder();
				String value = DataStore1.ONE.qaKeyWordStore.get(word);
				boolean updated = false;

				//System.out.println(value);

				String[] wordValue = value.split(",");
				HashMap<String,Integer> wordValueMap = new HashMap<String, Integer>(); // tmp hash map for calculations //treemap
				
				System.out.println("Comparing "+Arrays.toString(wordValue).contains(recordType));
				if(Arrays.toString(wordValue).contains(recordType))	{//checks if qa is present in value - should always be true
					if(Arrays.toString(wordValue).contains(recordId))	{ //checks if recordId is present in value string
						
					}
					else	{ //condition for recordId not in the value string : means that the word is first time in the record
						
					}
				}
					//System.out.println("wordValue:"+wordValue[0]); 
					// looping through each recordId+count pair of a given word in DataStore
					for(int i = 0; i < wordValue.length; i++)	{ //add hashmap inside to store and and increment
						String[] recNCount = wordValue[i].split("-"); //spliting recordId+count pair
						//String thisRec = recNCount[0];
						//int thisCount = Integer.parseInt(recNCount[1]);
						//wordValueMap.put(thisRec, thisCount);
						wordValueMap.put(recNCount[0], Integer.parseInt(recNCount[1]));
					} //now here data is in hash map
					//now comparing the previous data with a new =>  word-recordId details

					//updating the count for recordType
					//if(wordValueMap.containsKey(recordType))	{
					int thisTypeCount = wordValueMap.get(recordType);
					//thisTypeCount = thisTypeCount + 1;
					wordValueMap.put(recordType, thisTypeCount + 1);
					//updating count for individual recordID
					if(wordValueMap.containsKey(recordId))	{
						int thisRecordCount = wordValueMap.get(recordId);
						//thisRecordCount += 1;
						wordValueMap.put(recordId, thisRecordCount + 1);
					}
					else {
						//new record
						wordValueMap.put(recordId, 1);
						//	}
					} // hashmap updated for recordID 


					//converting Hashmap to StringBuilder, so that keyWordHashMap can update its record
					//for(Map.Entry<String, Integer> entry : userRevCount.entrySet())
					StringBuilder newValue = new StringBuilder();
					//starting with recordType+count,
					//newValue.append(recordType+"+"+
					for(Map.Entry<String, Integer> entry : wordValueMap.entrySet())	{
						//starting with recordType+count
						newValue.append(entry.getKey()+"-"+entry.getValue()+",");
					} // newValue is a StringBuilder object -> storing value string for keyWordStore

					//updating the DataStore with the newValue
					//System.out.println("new Value: "+newValue);
					DataStore1.ONE.qaKeyWordStore.put(word, newValue.toString());
					//DataStore.ONE.keyWordStore.put(word, value); // updating new value in DataStore
					
				//}
				
	}


	/*	
	//START here - use hashmap to store the values in value string of the word --- 
	public void updateWordInKeyWordDataStore(String word, String recordType, String recordId)	{ 
		StringBuilder value = new StringBuilder();
		value = DataStore.ONE.keyWordStore.get(word);
		boolean updated = false;

		//System.out.println(value);

		String[] wordValue = value.toString().split(",");
		HashMap<String,Integer> wordValueHashMap = new HashMap<String, Integer>(); // tmp hash map for calculations

		System.out.println("wordValue:"+wordValue[0]);   
		for(int i = 0; i < wordValue.length; i++)	{ //add hashmap inside to store and and increment
			String[] recNCount = wordValue[i].split("+"); //spliting recordId+count pair
			String thisRec = recNCount[0];
			int thisCount = Integer.parseInt(recNCount[1]);

			//incrementing global word counter
			if(i == 0)	{
				thisCount += 1;
				///int changeAt = value.indexOf(recordType) + 2; // so if value is rv+3
				///value.replace(changeAt, value.length(), String.valueOf(thisCount));
			}
			//incrementing at record level
			if(thisRec.equals(recordId))	{
				thisCount += 1;
				///int changeAt = value.indexOf(recordId) + 3; // so if value is rv2+3
				///value.replace(changeAt, value.length(), String.valueOf(thisCount));
				updated = true;
			}

		} 
		if(updated == false)	{
				value.append(recordId+"+1,");
		}
		DataStore.ONE.keyWordStore.put(word, value); // updating new value in DataStore

	}
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(DataStore1.ONE);

	}

}

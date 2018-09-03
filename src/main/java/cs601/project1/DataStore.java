package cs601.project1;

import java.util.HashMap;

public enum DataStore {
	     
	ONE;
	
	//data of the whole record; key - record id, value - whole record data
	HashMap<String, String> recordStore = new HashMap<String, String>();
	
	//KeyWordStore - key is word & value is String of record ids and freq count
	//sample key, value
	// key - rainbow, value - rv+8,rv2+1,rv5+4,rv7+3,qa+4,qa1+2,qa3+2
	HashMap<String, StringBuilder> keyWordStore = new HashMap<String, StringBuilder>();
	
	
	public void updateDataStore(Reviews review)	{
	//	new DataStoreProcessor()
		
		//updating Record Store //
		recordStore.put(review.getRecordId(), review.toString());
		System.out.println("recordStore :"+DataStore.ONE.recordStore);
		
		//updating KeyWord Store //
		String[] wordsInRecord = review.getWords();
		for(String word : wordsInRecord)	{
			if(DataStore.ONE.keyWordStore.containsKey(word))	{
				//build value string
				updateWordInKeyWordDataStore(word, review.getRecordType(), review.getRecordId());
			}
			else{
				//add word to keyWordStore
				//stringbuilder - rv+1,review.getRecordId+1
				addNewWordInKeyWordDataStore(word, review.getRecordType(), review.getRecordId());
			}
		}
	}
	
	public void updateDataStore(QuesAns qa)	{
		
	}
	
	public void addNewWordInKeyWordDataStore(String word, String recordType ,String recordId)	{
		StringBuilder value = new StringBuilder();
		//value = recordType+1,recordId+1
		value.append(recordType+"+1,"+recordId+"+1,");
		DataStore.ONE.keyWordStore.put(word, value);
	}
	
	//START here - use hashmap to store the values in value string of the word --- 
	public void updateWordInKeyWordDataStore(String word, String recordType, String recordId)	{ 
		StringBuilder value = new StringBuilder();
		value = DataStore.ONE.keyWordStore.get(word);
		boolean updated = false;
		//System.out.println(value);
		
		String[] wordValue = value.toString().split(",");
		System.out.println("wordValue:"+wordValue[0]);   
		for(int i = 0; i < wordValue.length; i++)	{ 
			String[] recNCount = wordValue[i].split("+");
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(DataStore.ONE);
		
	}

}

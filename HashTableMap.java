
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTableMap<KeyType,ValueType> implements MapADT<KeyType,ValueType>{
	@SuppressWarnings("unchecked")
	protected LinkedList<Pair<KeyType,ValueType>>[] table;
	private int size;

	@SuppressWarnings("unchecked") 
	//constructor which takes in a capacity, and sets up the table and sets the capacity of the table
	//@param int capacity which is the capacity of the array of linkedlists
	public HashTableMap(int capacity){
		table = (LinkedList<Pair<KeyType,ValueType>>[])new LinkedList[capacity];

	}

	@Override
	//put method of the hashmap class which adds a key value pair to the hashmap.
	//uses the pair class which is a pair object of key and value
	//@param Keytype key which is the key for the map
	//@param ValueType value which is the value for the map
	public boolean put(KeyType key, ValueType value) {
		Pair<KeyType, ValueType> p = new Pair<KeyType,ValueType>(key, value);
		
		if(key == null) {
			return false;
		}
		
		int index = Math.abs(key.hashCode()) % table.length;
		
		//checks if the pairs that share the same index as the key have the same key
		//if they are equal returns false, if they are different adds the pair to the linkedlist
		if(table[index] != null) {
			for(int i =0; i <table[index].size(); i++){
				if(table[index].get(i).getKey().equals(key)) {
					return false;
				}else {
					table[index].add(p);
					size++;
					break;
				}
			}
		//if the index of the array is empty, creates a linkedlist and adds the pair to the linkedlist
		}else {
			table[index] = new LinkedList();
			table[index].add(p);
			size++;
		}
		

		//System.out.println("LOAD FACTOR " + loadFactor() + key);
		
		//after pairs are added, checks the load factor each time
		//if load factor is greater then 75%, it increases the capacity and rehashes the map
		
		if(loadFactor() >= 75) {
			//System.out.println("LOADFACTOR BIGGER");
			//System.out.println("LENGTH" + table.length);
			LinkedList<Pair<KeyType,ValueType>>[] newTable = (LinkedList<Pair<KeyType,ValueType>>[])new LinkedList[table.length*2];
			rehash(newTable);
			//System.out.println(table.length);
		}
		
		
		return true;
	}


	//get method of HashMap which takes in a key and returns the value of the key
	//@param KeyType key which is the key to be given the value of
	//@param throws NoSuchElementException, if there is no key in the map to get
	public ValueType get(KeyType key) throws NoSuchElementException{
		int index = Math.abs(key.hashCode()) % table.length;
		
		if(table[index] == null) {
			throw new NoSuchElementException();
		}
		
		for(int i =0; i < table[index].size(); i++) {
			if(table[index].get(i).getKey() == key) {
				return table[index].get(i).getValue();
			}
		}
		throw new NoSuchElementException();
	
	}
	
	//method which returns the size of the hashmap
	public int size(){
		return size;
	}
	
	//method which returns true if the key passed in is already in the map
	//returns false if not in map
	//@param KeyType key, the key to search for in the map
	public boolean containsKey(KeyType key){
		int index = Math.abs(key.hashCode()) % table.length;
		if (table[index] == null) {
			return false;
		}else {
			for(int i = 0; i < table[index].size(); i++) {
				if(!table[index].get(i).getKey().equals(key)) {
					return false;
				}
			}
		}
		return true;
	}
	
	//method which removes the pair from the hashmap based on the key passed in
	//@param KeyType key, the key of the pair to remove
	public ValueType remove(KeyType key){
		ValueType v = null;
		int index = Math.abs(key.hashCode()) % table.length;
		//if(containsKey(key) == false) {
		//	return null;
		//}
		if (table[index] != null) {
			for (int i = 0; i < table[index].size(); i++) {
				if(table[index].get(i).getKey() == key) {
					v = table[index].get(i).getValue();
					table[index].remove(i);
					size--;
				}
			}
		}else {
			return null;
		}
		return v;
	}
	
	//method which clears the entire hashmap and all the pairs in the hashmap
	public void clear(){
		for(int i =0; i <table.length;i++) {
			table[i].remove(i);
		}
	}
	
	//helper method which calculates the load factor of the hashmap
	public double loadFactor() {
		double num = ((double)size() / (double)table.length) * 100;
		return num;
	}
	
	//helper method which rehashes based on the table passed in
	//@param LinkedList<Pair<KeyType, ValueType>>[] newTable the new table with a larger capacity
	public void rehash(LinkedList<Pair<KeyType, ValueType>>[] newTable) {
		//System.out.println("REHASH CALLED");
		//System.out.println("NEW LENGTH" + newTable.length);
		//int index = Math.abs(key.hashCode()) % table.length;
		
		for(int i = 0; i < table.length; i++) {
			if(table[i] != null) {
				for(int j = 0; j < table[i].size(); j++) {
					int newIndex = Math.abs(table[i].get(j).getKey().hashCode()) % newTable.length;
					//put but with newtable
					Pair<KeyType, ValueType> p = table[i].get(j);
					if(newTable[newIndex] != null) {
						newTable[newIndex].add(p);
					
					//if the index of the array is empty, creates a linkedlist and adds the pair to the linkedlist
					}else {
						newTable[newIndex] = new LinkedList();
						newTable[newIndex].add(p);
					}
					
					
				}
			}
			//checck to see if elements in table are null
			//if not null for each of elements in linkedlist
			//calculate new index where new index 
			//if spot in new table isn ull 
			
		}
		table = newTable;
	}
	
	public int capacity() {
		return table.length;
	}
	

}
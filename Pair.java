

//class which combines key and value into a pair in one object
public class Pair<K,V>{
	private K key;
	private V value;
	
	//consturctor for the class
	//@param K key of the pair
	//@param V value of the pair
	public Pair(K key, V value){
		this.key = key;
		this.value = value;
	}

	//method which returns the key
	public K getKey(){
		return key;
	}

	//method which returns the value
	public V getValue(){
		return value;
	}
}

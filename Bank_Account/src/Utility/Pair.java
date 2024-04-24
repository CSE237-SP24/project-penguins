package Utility;

import java.util.Objects;

public class Pair<T,V> {

	T thing;
	V thing2;
	
	public Pair(T thing, V thing2){
		this.thing = thing;
		this.thing2  = thing2;
		
	}
	
	
	public T first() {
		
		return this.thing;
		
	}
	
	public V second() {
		
		return this.thing2;
		
	}
	
	@Override
	public String toString() {
		return "first: " + this.thing + " second: "+this.thing2;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(thing, thing2);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		return Objects.equals(thing, other.thing) && Objects.equals(thing2, other.thing2);
	}
}

package demo.ch2;

public class GenericMemoryCell <AnyType> {
	private AnyType storeValue;
	
	public AnyType read() {
		return storeValue;
	}
	
	public void write(AnyType x) {
		storeValue = x;
	}
}

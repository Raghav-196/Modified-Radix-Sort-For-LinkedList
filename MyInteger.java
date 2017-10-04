final public class MyInteger implements Integral {
	private int value;

	public MyInteger(int value) {
		this.value = value;
	}

	@Override
	public int getIntValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return Integer.toString(value);
	}
	
	@Override
	public int hashCode() {
		return value;
	}
}

package optimizedRadixSort;

import java.util.Arrays;

public class MyLinkedList<E extends Integral> {
	
	class Node<T extends E> {
		T data;
		Node<T> next;
		
		public Node (T data) {
			this.data = data;
		}
	}
	
	public Node<E> head, tail;
	public int size;
	private final int MAX;
	
	public MyLinkedList() {
		this(Integer.MAX_VALUE);
	}
	
	public MyLinkedList(int MAX) {
		this.MAX = MAX;
	}
	
	public void add(E data) {
		if (head == null) {
			head = tail = new Node<>(data);
		} else {
			tail.next = new Node<>(data);
			tail = tail.next;
		}
		size++;
	}
	
	/**
	 * Sorts this list using the modified radixsort algorithm
	 */
	@SuppressWarnings("unchecked")
	public void sort() {
		int radix = getOptimalRadix();
		Node<E> f[] = new Node[radix];
		Node<E> e[] = new Node[radix];
		
		int d,i;
		Node<E> s;
		long exp = 1;
		
		for (int div = 1; exp < MAX; exp *= radix, div = (int) exp) {
			for (Node<E> current = head; current != null; current = current.next) {
				d = (current.data.getIntValue() / div) % radix;
				if (e[d] == null)
					f[d] = current;
				else
					e[d].next = current;
				e[d] = current;
			}
			
			s = null;
			
			for (i = radix - 1; ; i--) {
				if (e[i] != null) {
					tail = e[i];
					s = f[i];
					break;
				}
			}
			
			tail.next = null;
			
			while (--i >= 0) {
				if (e[i] != null) {
					e[i].next = s;
					s = f[i];
				}
			}
			
			head = s;
			
			Arrays.fill(e, null);
		}
	}

	/**
	 * @return 
	 * Optimal radix for the given size of the list according to the formula:
	 * radix * ( log(radix) - 1 ) = size
	 */
	private int getOptimalRadix() {
		int ret = 2;
		int sub, temp;
		if (size > 4) {
			while (ret * Math.log(ret) - ret < size) {
				ret <<= 1;
			}
			sub = ret >> 1;
			while (sub > 0) {
				temp = ret - sub;
				if (temp * Math.log(temp) - temp > size)
					ret -= sub;
				else
					sub >>=1;
			}
		}
		return ret + 1;
	}
	
	public String toString() {
		String ret = "[";
		if (size > 0) {
			ret += head.data.toString();
			for (Node<E> current = head.next; current != null; current = current.next)
				ret += (", " + current.data.toString());
		}
		return ret + "]";
	}
	
	
}

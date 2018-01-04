/*
Copyright 2017
github.com/mrchowmein
*/

import java.util.Arrays;

public class Set<T>{
	

	private static class Node<T> {

		private T data;
		private Node<T> next;
		private Node(T data){
			this.data = data;
		}
	}



	private Node<T> head; 

	private int size;

	public Set(T[] arr){
		if(arr.length != 0){
			Arrays.sort(arr);
			removeDuplicates(arr);
		} 
	}

	public Set(){
		
	}

	//Big Oh is O(1) as it doesn't take more time regardless of the size.
	public boolean isEmpty(){
		return size == 0;
	}

	//Big Oh is O(1) as it doesn't take more time regardless of the size.
	public int size(){
		return size;
	}

	//Big Oh is O(N) for the method removeDuplicates() as it needs to traverse the array once checking each element with the previous.
	private void removeDuplicates(T[] arr){

		int length = arr.length;
		if(length == 1){
			add(arr[0]);
		} else {
			for(int i = 1; i < length; i++){
				if(arr[i] != arr[i-1]){
					add(arr[i-1]);
				}
			}

			if(arr[length-1] != arr[length-2]){
				add(arr[length-1]);
			}
		}
	}

	//Big Oh is O(N) for the method remove() as it needs to traverse the linked list once checking each node with the next.  Removal or update of the next node is O(1) as it is done in constant time. 
	public T remove(T input){

		int match = 0;

		while(head != null && head.data.equals(input)){
			if(head.next == null){
				head = null;
				size--;
				match = 1;
				break;
			} else {
				head = head.next;
				size--;	
			}
		}

		Node<T> current = head;
		
		while(current != null){
			if(current.next !=null && current.next.data.equals(input)){
				current.next = current.next.next;
				current = current.next;
				size--;
				match = 1;
				break;
			} else {
				current = current.next;
			}
		}

		if(size == 0){
			head = null;
		}

		if(match == 1){
			return input;
		} else {
			return null;
		}
	}

	//Worse case for Big Oh is O(N) for searching for duplicates as the method needs to traverse the whole link list checking each node for a duplicate. Adding a new node to will be O(1) as it is done in constant time each time.
	public void add(T input){
		
		Node<T> node = new Node<T>(input);
		boolean dupFound = false;
		Node current = head;
		
		while(current != null){
			if(current.data.equals(input)){
				dupFound = true;
				break;
			} else {
				current = current.next;
			}
		}

		if(dupFound==false){
			if (head == null) {
			head = node;
			size++;
			} else {
				node.next = head;
				head = node;
				size++;
			}
		}
	}

	//Big Oh is O(N) as the method needs to traverse the linkedlist to find the element.
	public boolean contains(T input){
		
		Node current = head;
		boolean found = false;

		while(current != null){
			if(current.data.equals(input)){
				found = true;
				break;
			}
			current = current.next;
		}

		return found;
	}

	
	//Big Oh is O(N) as the method needs to traverse the linkedlist to concatenate the string.
	public String toString(){

		Node current = head;
		String dataString = "";
		while(current != null){
			if(current == head){
				dataString += current.data;
				current = current.next;
			} else {
			dataString = current.data+", " + dataString;
			current = current.next;
			}
		}

		if(size == 0){
			dataString = "Set is empty!";
		}

		return dataString;
	}
}

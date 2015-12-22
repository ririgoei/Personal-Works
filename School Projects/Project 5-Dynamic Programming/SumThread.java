//Riadiani Marcelita
//CS 146-Anna Shaverdian
//Project 5

import java.util.*;

class SumThread extends java.lang.Thread {
	
	int lo; int hi; int[] arr;
	int ans = 0;
	final int SEQUENTIAL_CUTOFF = 500;
	
	SumThread(int[] a, int low, int high){
		arr = a;
		lo = low;
		hi = high;
	}
	
	public void run(){
		if(hi - lo < SEQUENTIAL_CUTOFF){
			for(int i = lo; i < hi; i++){
				if(arr[i] < 7){
					ans++;
				}
			}
		}
				else{
					SumThread left = new SumThread(arr, lo, (hi+lo)/2);
					SumThread right = new SumThread(arr, (hi+lo)/2, hi);
					left.start();
					right.start();
					try {
						left.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						right.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ans = left.ans + right.ans + this.ans;
				}
			}
	
	public static void main(String[] args){
		Random random = new Random();
		int[] array = new int[1000];
		for(int i = 0; i < array.length; i++){
			array[i] = random.nextInt((50 - 1) + 1);
			System.out.println("Array elements: " + array[i]);
		}
		SumThread newT = new SumThread(array, 0, array.length);
		newT.run();
		System.out.println("Results: " + newT.ans);
		
	}
}
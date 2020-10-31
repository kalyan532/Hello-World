package com.java.multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class FirstWorkerMap implements Runnable{
	private ConcurrentMap<String, Integer> map;
	
	public FirstWorkerMap(ConcurrentMap<String, Integer> map) {
		this.map = map;
	}
	
	@Override
	public void run() {
		try {
			map.put("B", 1);
			map.put("H", 2);
			Thread.sleep(1000);
			map.put("F", 3);
			map.put("A", 4);
			Thread.sleep(1000);
			map.put("E", 5);

		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}



class SecondWorkerMap implements Runnable{
	private ConcurrentMap<String, Integer> map;
	
	public SecondWorkerMap(ConcurrentMap<String, Integer> map) {
		this.map = map;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(map.get("A"));
			Thread.sleep(1000);
			System.out.println(map.get("E"));
			System.out.println(map.get("F"));

		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class ConcurremtMapExe {

	public static void main(String[] args) {
		
		ConcurrentMap<String,Integer> map = new ConcurrentHashMap<>();
		
		new Thread(new FirstWorkerMap(map)).start();
		new Thread(new SecondWorkerMap(map)).start();
		
		List<String> list = new ArrayList<>();
		List<String> list2 = Collections.synchronizedList(list);
		

	}

}

package com.example.demo.model;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Data {
	
	private Data() {
		// Prevent instantiation
	}

	private static final Map<Long, String> dataStore = new ConcurrentHashMap<>();
	private static final AtomicLong idCounter = new AtomicLong();


	public static Map<Long, String> getDataStore() {
	    return dataStore;
	}
	
	public static AtomicLong getIdCounter () {
		return idCounter;
	}


}

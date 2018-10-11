package com.DamnItJenkins.ClockworkCrisisPro.dataModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum Direction 
{
	TOP_LEFT(0),
    TOP_RIGHT(1),
    BOTTOM_LEFT(2),
	BOTTOM_RIGHT(3);
	
	public final int Value;
	 
    private Direction(int value)
    {
        Value = value;
    }
 
    // Mapping difficulty to difficulty id
    private static final Map<Integer, Direction> _map = new HashMap<Integer, Direction>();
    
    static
    {
        for (Direction difficulty : Direction.values())
            _map.put(difficulty.Value, difficulty);
    }
 
    public static Direction from(int value)
    {
        return _map.get(value);
    }
    
    public static Direction Random()
    {
    	Random r = new Random();
    	    	
        return _map.get(r.nextInt(_map.size()));
    }
}



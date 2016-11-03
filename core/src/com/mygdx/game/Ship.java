package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Ship {
	public static int width =  128;
	public static int height = 100;
	private int SPEED = 5;
	private Vector2 position ;
	public static final int DIRECTION_UP = 3;
 	public static final int DIRECTION_RIGHT = 2;
 	public static final int DIRECTION_DOWN = 1;
 	public static final int DIRECTION_LEFT = 4;
 	private static final int [][] DIR_OFFSETS = new int [][] {
        {0,0},
        {0,-1},
        {1,0},
        {0,1},
        {-1,0}
    };
	public Ship(int x,int y)
	{
		position = new Vector2(x,y);
	}
	public Vector2 getPosition()
	{
		return position;
	}
	public void move(int dir) { 
    	position.x += SPEED * DIR_OFFSETS[dir][0];
        position.y += SPEED * DIR_OFFSETS[dir][1];
        
    }

}

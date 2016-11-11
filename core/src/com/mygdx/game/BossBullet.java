package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class BossBullet extends AllBullet{
	
	public BossBullet(float x, float y,int spx,int spy,int type, World world) {
		position = new Vector2(x, y);
		speedX = -1*spx;
		speedY = spy;
		bulletType = type;
		this.world = world;
		player = world.getShip();
		height = 20;
		width = 20;
	}
	
}

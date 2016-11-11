package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class PowerUp extends AllBullet {

	public PowerUp(float x, float y,int spx,int spy, World world) {
		position = new Vector2(x, y);
		speedX = -1*spx;
		speedY = spy;
		this.world = world;
		player = world.getShip();
		width = 44;
		height = 37;
	}
	
}

package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class BossBullet {
	private Vector2 position;
	private int speedX;
	private int speedY;
	private World world;
	private int bulletType;

	public BossBullet(float x, float y,int spx,int spy,int type, World world) {
		position = new Vector2(x, y);
		speedX = -1*spx;
		speedY = spy;
		bulletType = type;
		this.world = world;
	}

	public void update() {
		position.x += speedX;
		position.y += speedY;
	}

	public Vector2 getPosition() {
		return position;
	}

	public boolean hitVerticalEdge() {
		return position.x < 0;
	}
	
	public boolean hitHoriontalEdge() {
		return position.y <= 0 || position.y >= Tks.HEIGHT - 50;
	}
	
	public void bounce () {
		speedY *= -1;
	}
}

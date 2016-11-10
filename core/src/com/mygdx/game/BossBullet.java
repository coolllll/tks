package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class BossBullet {
	private Vector2 position;
	private int speedX;
	private int speedY;
	private World world;
	private int bulletType;
	private Ship player;
	public static int width = 20;
	public static int height = 20;

	public BossBullet(float x, float y,int spx,int spy,int type, World world) {
		position = new Vector2(x, y);
		speedX = -1*spx;
		speedY = spy;
		bulletType = type;
		this.world = world;
		player = world.getShip();
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
	
	public boolean isHitPlayer() {
		float x = position.x + width/2;
		float y = position.y + height/2;
		return x > player.getPosition().x
				&& x < player.getPosition().x+Ship.width
				&& y > player.getPosition().y
				&& y < player.getPosition().y+Ship.height;
	}
}

package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class PowerUp {
	public static int width = 44;
	public static int height = 37;
	private Vector2 position;
	private int speedX;
	private int speedY;
	private World world;
	private Ship player;

	public PowerUp(float x, float y,int spx,int spy, World world) {
		position = new Vector2(x, y);
		speedX = -1*spx;
		speedY = spy;
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
		return position.x + width < 0;
	}
	
	public boolean hitHoriontalEdge() {
		return position.y <= 0 || position.y+height >= Tks.HEIGHT - 50;
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

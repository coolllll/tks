package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class AllBullet {
	public Vector2 position;
	public int speedX;
	public int speedY;
	public World world;
	public int bulletType;
	public Ship player;
	public Boss boss;
	public static int width;
	public static int height;
	
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
	
	public boolean hitHorisontalEdge() {
		return position.y <= 0 || position.y >= Tks.HEIGHT - Tks.upperGap;
	}
	
	public boolean hitEdge() {
		return hitVerticalEdge() || hitHorisontalEdge();
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
	
	public boolean hitBoss() {
		return position.x > boss.getPosition().x 
				&& position.x < boss.getPosition().x + Boss.width
				&& position.y > boss.getPosition().y 
				&& position.y < boss.getPosition().y + Boss.height;
	}
}

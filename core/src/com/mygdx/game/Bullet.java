package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bullet {
	private Vector2 position;
	private int speed = 10;
	private Boss boss;
	private World world;

	public Bullet(float x, float y, World world) {
		position = new Vector2(x, y);
		this.world = world;
		boss = world.getBoss();
	}

	public void update() {
		position.x += speed;
	}

	public Vector2 getPosition() {
		return position;
	}

	public boolean hitEdge() {
		return position.x >= Tks.WIDTH;
	}

	public boolean hitBoss() {
		return position.x > boss.getPosition().x 
				&& position.x < boss.getPosition().x + Boss.width
				&& position.y > boss.getPosition().y 
				&& position.y < boss.getPosition().y + Boss.height;
	}
}

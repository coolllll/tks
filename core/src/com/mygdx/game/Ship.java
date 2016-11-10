package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Ship {
	public static int width = 51;
	public static int height = 35;
	private int speed = 5;
	private int state = 1;
	private Vector2 position;
	private World world;
	private boolean devMode = false;
	public static final int DIRECTION_UP = 3;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_DOWN = 1;
	public static final int DIRECTION_LEFT = 4;
	private static final int[][] DIR_OFFSETS = new int[][] { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	public Ship(int x, int y,World world) {
		position = new Vector2(x, y);
		this.world = world;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void move(int dir) {
		float nextX = position.x + speed * DIR_OFFSETS[dir][0];
		float nextY = position.y + speed * DIR_OFFSETS[dir][1];
		if ((nextX >= 0 && nextX + width < Tks.WIDTH - Boss.width - 20) || devMode) {
			position.x = nextX;
		}
		if ((nextY >= 0 && nextY + height < Tks.HEIGHT - 50) || devMode) {
			position.y = nextY;
		}

	}
	
	public void increaseState() {
		if (state < 3) {
			state++;
		}
	}
	
	public void increaseSpeed() {
		if (speed < 8) {
			speed++;
		}
	}
	
	public void shoot() {
		float x = this.position.x + (Ship.width);
		float y = this.position.y + (Ship.height / 2);
		float h = Ship.height/4;
		world.getBullet().add(new Bullet(x, y, world));
		if (state > 1) {
			world.getBullet().add(new Bullet(x, y+h, world));
		}
		if (state > 2) {
			world.getBullet().add(new Bullet(x, y-h, world));
		}
	}
	
	public void enebleDevMode() {
		this.devMode = true;
	}

}

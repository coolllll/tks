package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class Boss {
	private Vector2 position;
	private float hp;
	public static float maxHp = 150;
	public static int width = 155;
	public static int height = 117;
	public static float speed = 3;
	private int state = 0;
	private int delay = 50;
	private World world;
	private Random rand;

	public Boss(World world) {
		this.world = world;
		position = new Vector2(Tks.WIDTH - width - 20, (Tks.HEIGHT - 50) / 2);
		this.hp = maxHp;
		rand = new Random();
	}

	public Vector2 getPosition() {
		return position;
	}

	public float getHp() {
		return hp;
	}

	public void hitByBullet() {
		if (hp > 0) {
			hp--;
		}
	}

	public boolean isHitEdge() {
		if (position.y <= 0 || (position.y + height) >= Tks.HEIGHT - 50) {
			return true;
		} else {
			return false;
		}
	}

	public void changeState() {
		if (hp < maxHp/2) {
			state = 2;
		} else if (hp < 3*maxHp/4) {
			state = 1;
		}
	}

	public void changeDirection() {
		speed *= -1;
	}

	public void move() {
		position.y += speed;
	}

	public void shoot() {
		if (delay <= 0) {
			int n = state;
			if(n >= 0) {
				pattern1();
			} 
			if (n >= 1) {
				pattern2();
			}
			if (n >= 2) {
				pattern3();
			}
			delay = 60;
		} else {
			delay--;
		}
	}

	private void pattern1() {
		float x = this.position.x;
		float y = this.position.y + (Boss.height / 2);
		int sy = 0;
		int sx = rand.nextInt(5) + 5;
		world.getBossBullet().add(new BossBullet(x, y, sx, sy, 0, world));
	}

	private void pattern2() {
		int start = rand.nextInt(15);
		for (int i = 0; i < 5; i++) {
			float x = this.position.x;
			float y = 5 + 30 * (i + start);
			int sy = 0;
			int sx = 3;
			world.getBossBullet().add(new BossBullet(x, y, sx, sy, 0, world));
		}
	}
	
	private void pattern3() {
		int start = rand.nextInt(15);
		for (int i = 0; i < 3; i++) {
			float x = this.position.x;
			float y = 5 + 30 * (i + start);
			int sy = (i-1)*3;
			int sx = Math.abs((i-1)*3) + 2;
			world.getBossBullet().add(new BossBullet(x, y, sx, sy, 0, world));
		}
	}
}

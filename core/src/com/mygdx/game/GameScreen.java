package com.mygdx.game;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends ScreenAdapter {
	private Tks tks;
	private WorldRenderer worldRenderer;
	private World world;
	private Ship ship;
	private List<Bullet> bullet;
	private List<BossBullet> bossBullet;
	private List<PowerUp> powerUp;
	private int bulletShootDelay = 10;
	private int powerUpDelay = 1000;
	private Boss boss;
	private Random rand;

	public GameScreen(Tks tks) {
		this.tks = tks;
		world = new World(tks);
		ship = world.getShip();
		bullet = world.getBullet();
		boss = world.getBoss();
		bossBullet = world.getBossBullet();
		powerUp = world.getPowerUp();
		rand = new Random();
		worldRenderer = new WorldRenderer(tks, world);
	}

	@Override
	public void render(float delta) {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		worldRenderer.render();

	}

	private void update() {
		updateShip();
		updateBoss();
		updatePowerUp();
		updateBullet();
		updateBossBullet();
	}

	private void updateShip() {
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			ship.move(Ship.DIRECTION_LEFT);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			ship.move(Ship.DIRECTION_RIGHT);
		} else if (Gdx.input.isKeyPressed(Keys.UP)) {
			ship.move(Ship.DIRECTION_UP);
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			ship.move(Ship.DIRECTION_DOWN);
		}
		if (Gdx.input.isKeyPressed(Keys.SPACE) && bulletShootDelay <= 0) {
			ship.shoot();
			bulletShootDelay = 10;
		} else if (bulletShootDelay >= 0) {
			bulletShootDelay--;
		}
	}

	private void updateBullet() {
		for (int i = 0; i < bullet.size(); i++) {
			bullet.get(i).update();
			if (bullet.get(i).hitBoss()) {
				boss.hitByBullet();
				bullet.remove(i);
				continue;
			}
			if (bullet.get(i).hitEdge()) {
				bullet.remove(i);
			}
		}
	}
	
	private void updateBoss() {
		if (boss.isHitEdge()) {
			boss.changeDirection();
		}
		boss.changeState();
		boss.move();
		boss.shoot();
	}
	
	private void updateBossBullet() {
		for (int i = 0; i < bossBullet.size(); i++) {
			bossBullet.get(i).update();
			if(bossBullet.get(i).hitHoriontalEdge()) {
				bossBullet.get(i).bounce();
			}
			if(bossBullet.get(i).hitVerticalEdge()) {
				bossBullet.remove(i);
			}
		}
	}
	
	private void updatePowerUp()
	{
		if(powerUpDelay <= 0) {
			float x = Tks.WIDTH;
			float y = rand.nextInt(Tks.HEIGHT - 50 - PowerUp.height);
			int sy = rand.nextInt(11) - 5;
			int sx = rand.nextInt(5) + 3;
			powerUp.add(new PowerUp(x, y, sx, sy, world));
			powerUpDelay = 1000;
		} else {
			powerUpDelay--;
		}
		for (int i = 0; i < powerUp.size(); i++) {
			powerUp.get(i).update();
			if(powerUp.get(i).hitHoriontalEdge()) {
				powerUp.get(i).bounce();
			}
			if(powerUp.get(i).isHitPlayer()) {
				ship.increaseSpeed();
				ship.increaseState();
				powerUp.remove(i);
				continue;
			}
			if(powerUp.get(i).hitVerticalEdge()) {
				powerUp.remove(i);
			}
		}
	}
}
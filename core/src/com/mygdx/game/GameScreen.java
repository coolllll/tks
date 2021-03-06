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
	private int powerUpDelay = 800;
	private int pauseDelay = 5;
	public static int devModeProtection = 0;
	private int devModeCheckDelay = 5;
	private Boss boss;
	private Random rand;
	private SoundFX sound;

	public GameScreen(Tks tks) {
		this.tks = tks;
		world = new World(tks);
		getData();
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
		if (world.gameOver > 0) {
			if(world.gameOver < 3) {
				sound.stopBgm();
			}
			resetCheck();
			unPauseCheck();
			devModeEnteringCheck();
			return;
		}
		updateShip();
		updateBoss();
		updatePowerUp();
		updateBullet();
		updateBossBullet();
		pauseCheck();
	}

	private void updateShip() {
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			ship.move(Ship.DIRECTION_LEFT);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			ship.move(Ship.DIRECTION_RIGHT);
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			ship.move(Ship.DIRECTION_UP);
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			ship.move(Ship.DIRECTION_DOWN);
		}
		if (Gdx.input.isKeyPressed(Keys.SPACE) && bulletShootDelay <= 0) {
			ship.shoot();
			sound.playShootSound();
			bulletShootDelay = 20;
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
				i--;
				continue;
			}
			if (bullet.get(i).hitEdge()) {
				bullet.remove(i);
				i--;
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
		if (boss.getHp() == 0) {
			world.gameOver = 2;
			sound.playVictory();
		}
	}

	private void updateBossBullet() {
		for (int i = 0; i < bossBullet.size(); i++) {
			bossBullet.get(i).update();
			if (bossBullet.get(i).hitHorisontalEdge()) {
				bossBullet.get(i).bounce();
			}
			if (bossBullet.get(i).isHitPlayer()) {
				bossBullet.remove(i);
				world.gameOver = 1;
				i--;
				continue;
			}
			if (bossBullet.get(i).hitVerticalEdge()) {
				bossBullet.remove(i);
				i--;
			}
		}
	}

	private void updatePowerUp() {
		if (powerUpDelay <= 0) {
			float x = Tks.WIDTH;
			float y = rand.nextInt(Tks.HEIGHT - Tks.upperGap - PowerUp.height);
			int sy = rand.nextInt(11) - 5;
			int sx = rand.nextInt(5) + 3;
			powerUp.add(new PowerUp(x, y, sx, sy, world));
			powerUpDelay = 800;
		} else {
			powerUpDelay--;
		}
		for (int i = 0; i < powerUp.size(); i++) {
			powerUp.get(i).update();
			if (powerUp.get(i).hitHorisontalEdge()) {
				powerUp.get(i).bounce();
			}
			if (powerUp.get(i).isHitPlayer()) {
				ship.increaseSpeed();
				ship.increaseState();
				powerUp.remove(i);
				continue;
			}
			if (powerUp.get(i).hitVerticalEdge()) {
				powerUp.remove(i);
			}
		}
	}

	private void pauseCheck() {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) && pauseDelay == 0) {
			world.gameOver = 3;
			pauseDelay = 5;
		} else if (pauseDelay > 0) {
			pauseDelay--;
		}
	}

	private void unPauseCheck() {
		if (world.gameOver == 3 && Gdx.input.isKeyJustPressed(Keys.ENTER) && pauseDelay == 0) {
			world.gameOver = 0;
			this.devModeProtection = 0;
			pauseDelay = 5;
		} else if (pauseDelay > 0) {
			pauseDelay--;
		}
	}

	private void resetCheck() {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER) && world.gameOver < 3) {
			sound.stopVictory();
			world.resetGame();
			getData();
		}
	}

	private void devModeEnteringCheck() {
		if (this.devModeProtection == 8) {
			ship.enebleDevMode();
		}
		if (this.devModeCheckDelay == 0) {
			if (devModeProtection == 0 && Gdx.input.isKeyPressed(Keys.UP)) {
				devModeProtection = 1;
			} else if (devModeProtection == 1 && Gdx.input.isKeyPressed(Keys.UP)) {
				devModeProtection = 2;
			} else if (devModeProtection == 2 && Gdx.input.isKeyPressed(Keys.DOWN)) {
				devModeProtection = 3;
			} else if (devModeProtection == 3 && Gdx.input.isKeyPressed(Keys.DOWN)) {
				devModeProtection = 4;
			} else if (devModeProtection == 4 && Gdx.input.isKeyPressed(Keys.LEFT)) {
				devModeProtection = 5;
			} else if (devModeProtection == 5 && Gdx.input.isKeyPressed(Keys.RIGHT)) {
				devModeProtection = 6;
			} else if (devModeProtection == 6 && Gdx.input.isKeyPressed(Keys.LEFT)) {
				devModeProtection = 7;
			} else if (devModeProtection == 7 && Gdx.input.isKeyPressed(Keys.RIGHT)) {
				devModeProtection = 8;
			} else if (Gdx.input.isKeyPressed(-1)) {
				devModeProtection = 0;
			}
			this.devModeCheckDelay = 5;
		} else if (this.devModeCheckDelay > 0) {
			this.devModeCheckDelay--;
		}
	}

	private void getData() {
		ship = world.getShip();
		bullet = world.getBullet();
		boss = world.getBoss();
		bossBullet = world.getBossBullet();
		powerUp = world.getPowerUp();
		sound = world.getSound();
	}
}
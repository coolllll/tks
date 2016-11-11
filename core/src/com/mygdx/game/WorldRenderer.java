package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import com.badlogic.gdx.graphics.GL20;

public class WorldRenderer {

	private BitmapFont font;
	private Tks tks;
	public SpriteBatch batch;
	private Texture bg;
	private Texture shipPic;
	private World world;
	private Texture bulletPic;
	private Texture bossPic;
	private Texture rect;
	private Texture bossBulletPic;
	private Texture powerUpPic;
	private Texture gameOverPic;
	private Texture gameClearPic;
	private Texture pausePic;
	private List<BossBullet> bossBullet;
	private List<Bullet> bullet;
	private List<PowerUp> powerUp;

	public WorldRenderer(Tks tks, World world) {
		font = new BitmapFont();
		this.tks = tks;
		this.world = world;
		batch = Tks.batch;
		bg = new Texture("Bg.jpg");
		shipPic = new Texture("Ship.png");
		bulletPic = new Texture("Bullet.png");
		bossPic = new Texture("boss.png");
		rect = new Texture("Red.png");
		bossBulletPic = new Texture("BossBullet.png");
		powerUpPic = new Texture("s.png");
		gameOverPic = new Texture("gameover.png");
		gameClearPic = new Texture("gameclear.png");
		pausePic = new Texture("pause.png");
	}

	public void render() {
		SpriteBatch batch = Tks.batch;
		batch.begin();
		batch.draw(bg, 0, 0, Tks.WIDTH, Tks.HEIGHT);
		
		this.bulletRender();
		this.powerUpRender();
		this.playerRender();
		this.bossRender();
		this.healthBarRender();
		if (world.gameOver == 1) {
			batch.draw(gameOverPic, 0, 0);
		} else if (world.gameOver == 2) {
			batch.draw(gameClearPic, 0, 0);
		} else if (world.gameOver == 3) {
			batch.draw(pausePic, 0, 0);
			if(world.getShip().checkDevMode()) {
				font.draw(batch, "dev mode : ON", 10, 30);
			} else {
				font.draw(batch, "dev mode : OFF ("+GameScreen.devModeProtection+")", 10, 30);
			}
		}
		batch.end();
	}
	
	public void bulletRender() {
		
		bullet = world.getBullet();
		for (Bullet i : bullet) {
			batch.draw(bulletPic, i.getPosition().x, i.getPosition().y);
		}
		
		bossBullet = world.getBossBullet();
		for (BossBullet i : bossBullet) {
			batch.draw(bossBulletPic, i.getPosition().x, i.getPosition().y);
		}
	}
	
	public void powerUpRender() {
		
		powerUp = world.getPowerUp();
		for (PowerUp i : powerUp) {
			batch.draw(powerUpPic, i.getPosition().x, i.getPosition().y);
		}
	}
	
	public void playerRender() {
		Vector2 pos = world.getShip().getPosition();
		batch.draw(shipPic, pos.x, pos.y);
	}
	
	public void bossRender() {
		Vector2 pos = world.getBoss().getPosition();
		batch.draw(bossPic, pos.x, pos.y);
	}
	
	public void healthBarRender() {
		batch.draw(rect, Tks.upperGap, Tks.HEIGHT - Tks.upperGap, (Tks.WIDTH - 100) * (world.getBoss().getHp() / Boss.maxHp), 30);
		font.draw(batch, "" + (int) world.getBoss().getHp() + "/" + (int) Boss.maxHp, Tks.WIDTH / 2, Tks.HEIGHT - 30);
	}
}
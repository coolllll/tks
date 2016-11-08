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
	}

	public void render() {
		SpriteBatch batch = Tks.batch;
		batch.begin();
		batch.draw(bg, 0, 0, Tks.WIDTH, Tks.HEIGHT);
		bullet = world.getBullet();
		for (Bullet i : bullet) {
			batch.draw(bulletPic, i.getPosition().x, i.getPosition().y);
		}
		bossBullet = world.getBossBullet();
		for(BossBullet i : bossBullet) {
			batch.draw(bossBulletPic, i.getPosition().x, i.getPosition().y);
		}
		powerUp = world.getPowerUp();
		for (PowerUp i : powerUp) {
			batch.draw(powerUpPic, i.getPosition().x, i.getPosition().y);
		}
		Vector2 pos = world.getShip().getPosition();
		batch.draw(shipPic, pos.x, pos.y);
		pos = world.getBoss().getPosition();
		batch.draw(bossPic, pos.x, pos.y);
		batch.draw(rect, 50, Tks.HEIGHT - 50, (Tks.WIDTH - 100) * (world.getBoss().getHp() / Boss.maxHp), 30);
		font.draw(batch, "" + (int) world.getBoss().getHp() + "/" + (int) Boss.maxHp, Tks.WIDTH / 2, Tks.HEIGHT - 30);
		batch.end();
	}
}
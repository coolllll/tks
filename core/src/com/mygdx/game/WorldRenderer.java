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
	private tks tks;
	public SpriteBatch batch;
	private Texture bg;
	private Texture shipPic;
	private World world;
	private Texture bulletPic;
	private Texture bossPic;
	private Texture rect;
	private List<Bullet> bullet;
	public WorldRenderer(tks tks,World world)
	{
			font = new BitmapFont();
			this.tks = tks;
			this.world = world;
			batch = tks.batch;
			bg = new Texture("Bg.jpg");
			shipPic = new Texture("Ship.png");
			bulletPic = new Texture("Bullet.png");
			bossPic = new Texture("boss.png");
			rect = new Texture("Red.png");
	}
	public void render(){
		SpriteBatch batch = tks.batch;
		batch.begin();
		batch.draw(bg, 0, 0,tks.WIDTH,tks.HEIGHT);
		bullet = world.getBullet();
		for(Bullet i : bullet )
		{
			batch.draw(bulletPic, i.getPosition().x,i.getPosition().y);
		}
		Vector2 pos = world.getShip().getPosition();
		batch.draw(shipPic,pos.x,pos.y);
		pos = world.getBoss().getPosition();
		batch.draw(bossPic,pos.x,pos.y);
		batch.draw(rect,50,tks.HEIGHT-50,(tks.WIDTH-100)*(world.getBoss().getHp()/Boss.maxHp),30);
		font.draw(batch, "" + (int)world.getBoss().getHp()+"/"+(int)Boss.maxHp,tks.WIDTH/2, tks.HEIGHT-30);
		batch.end();
	}
}
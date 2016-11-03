package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends ScreenAdapter {
	private tks tks;
	private WorldRenderer worldRenderer;
	private World world;
	private Ship ship;
	private List<Bullet> bullet;
	private int delay = 10;
	private Boss boss;
	public GameScreen(tks tks)
	{
		this.tks = tks;
		world = new World(tks);
		ship = world.getShip();
		bullet = world.getBullet();
		boss = world.getBoss();
		worldRenderer = new WorldRenderer(tks,world);
	}
	@Override
    public void render(float delta) 
	{
		 update();
         Gdx.gl.glClearColor(0, 0, 0, 1);
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         
         worldRenderer.render();
  
    }
	private void update()
	{
		updateShip();
		updateBullet();
	}
	private void updateShip()
	{
		if(Gdx.input.isKeyPressed(Keys.LEFT)) 
		{
	   		 ship.move(Ship.DIRECTION_LEFT);
	    }
	   	else if(Gdx.input.isKeyPressed(Keys.RIGHT)) 
	   	{
	   		ship.move(Ship.DIRECTION_RIGHT);
	    }
	   	else if(Gdx.input.isKeyPressed(Keys.UP)) 
	   	{
	   		ship.move(Ship.DIRECTION_UP);
	    }
	   	else if(Gdx.input.isKeyPressed(Keys.DOWN)) 
	   	{
	   		ship.move(Ship.DIRECTION_DOWN);
	    }
	}
	private void updateBullet()
	{
		if(Gdx.input.isKeyPressed(Keys.SPACE)&&delay<=0)
		{
			float x = ship.getPosition().x+(Ship.width);
			float y = ship.getPosition().y+(Ship.height/2);
			bullet.add(new Bullet(x,y,tks,world));
			delay = 10;
		}
		else if(delay>=0)
		{
			delay--;
		}
		for(int i=0;i<bullet.size();i++)
		{
			bullet.get(i).update();
			if(bullet.get(i).hitBoss())
			{
				boss.hitByBullet();
				bullet.remove(i);
				continue;
			}
			if(bullet.get(i).hitEdge())
			{
				bullet.remove(i);
			}
		}
	}
}
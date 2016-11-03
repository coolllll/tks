package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Boss {
	private Vector2 position;
	private float hp;
	public static float maxHp = 30;
	public static int width = 185;
	public static int height = 117;
	public tks tks;
	public Boss(tks tks)
	{
		this.tks = tks;
		position = new Vector2(tks.WIDTH-width-20,tks.HEIGHT/2);
		this.hp = maxHp;
	}
	public Vector2 getPosition()
	{
		return position;
	}
	public float getHp()
	{
		return hp;
	}
	public void hitByBullet()
	{
		hp--;
	}
}

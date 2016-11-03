package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class World {
	private tks tks;
	private Ship ship;
	private List<Bullet> bullet;
	private Boss boss;
	public World(tks tks)
	{
		this.tks=tks;
		ship = new Ship(20,20);
		boss = new Boss(tks);
		bullet = new ArrayList<Bullet>();
	}
	public Ship getShip()
	{
		return ship;
	}
	public List<Bullet> getBullet()
	{
		return bullet;
	}
	public Boss getBoss()
	{
		return boss;
	}
}
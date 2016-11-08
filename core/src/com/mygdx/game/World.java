package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class World {
	private Tks tks;
	private Ship ship;
	private List<Bullet> bullet;
	private List<BossBullet> bossBullet;
	private List<PowerUp> powerUp;
	private Boss boss;

	public World(Tks tks) {
		this.tks = tks;
		ship = new Ship(20, 20,this);
		boss = new Boss(this);
		bullet = new ArrayList<Bullet>();
		bossBullet = new ArrayList<BossBullet>();
		powerUp = new ArrayList<PowerUp>();
	}

	public Ship getShip() {
		return ship;
	}

	public List<Bullet> getBullet() {
		return bullet;
	}
	
	public List<BossBullet> getBossBullet() {
		return bossBullet;
	}
	
	public List<PowerUp> getPowerUp() {
		return powerUp;
	}

	public Boss getBoss() {
		return boss;
	}
}
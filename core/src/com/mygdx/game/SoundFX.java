package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundFX {
	Sound shoot = Gdx.audio.newSound(Gdx.files.internal("shoot.wav"));
	Sound bgm = Gdx.audio.newSound(Gdx.files.internal("bgm.mp3"));
	Sound victory = Gdx.audio.newSound(Gdx.files.internal("victory.mp3"));
	
	public SoundFX () {
		
	}
	
	public void playShootSound() {
		shoot.play(0.08f);
	}
	
	public void playBgm() {
		bgm.play(0.08f);
	}
	
	public void stopBgm() {
		bgm.stop();
	}
	
	public void playVictory() {
		victory.play(0.1f);
	}
	
	public void stopVictory() {
		victory.stop();
	}
}

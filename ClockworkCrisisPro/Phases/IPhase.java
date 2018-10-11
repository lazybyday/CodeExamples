package com.DamnItJenkins.ClockworkCrisisPro.Phases;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IPhase
{
	public String getPhaseName();

	public ArrayList<Sprite> getSpriteList();

	public void onDraw(SpriteBatch batch);

	public boolean touchDown(int screenX, int screenY, int pointer, int button);

	public boolean touchUp(int screenX, int screenY, int pointer, int button);

	public boolean touchDragged(int screenX, int screenY, int pointer);

	public boolean mouseMoved(int screenX, int screenY);

	public boolean keyTyped(char character);

}

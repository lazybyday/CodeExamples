package com.DamnItJenkins.ClockworkCrisisPro.Phases;

import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ForegroundGritSprite extends KeyFrameSprite
{
	public ForegroundGritSprite(TextureRegion tr)
	{
		super(tr);
		m_CurrentRectF = new RectF();
		setColor(1, 1, 1, 0.05f);
	}
}

package com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites;

import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrameSprite;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.Utils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CogRotationGuideSprite extends KeyFrameSprite
{

	public CogRotationGuideSprite(TextureRegion tr)
	{
		super(tr);
	}

	public void setupPosition(float left, float top, float width, float height)
	{
		float spriteWidth = 512;

		setCurrentRectF(new RectF(
				left + (width / 2) - (spriteWidth / 2),
				top + (height / 2) - (spriteWidth / 2),
				left + (width / 2) - (spriteWidth / 2) + spriteWidth,
				top + (height / 2) - (spriteWidth / 2) + spriteWidth));

		this.setOrigin(m_CurrentRectF.width() / 2, m_CurrentRectF.height() / 2);
		// this.setOrigin(0+ 8,height);

	}

	private final float COLOR_MERGE_FACTOR = 0.01f;

	@Override
	public void setColor(Color c)
	{
		setColor(c.r, c.g, c.b, c.a);
	}

	@Override
	public void setColor(float r1, float g1, float b1, float a1)
	{
		if (this.getColor().a != a1 || this.getColor().r != r1 || this.getColor().b != b1 || this.getColor().g != g1)
		{
			super.setColor(Utils.ColourBlend(this.getColor(), COLOR_MERGE_FACTOR, r1, g1, b1, a1));
		}
	}
}

package com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites;

import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrameSprite;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.Utils;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CogGuideSprite extends KeyFrameSprite
{

	private final float MAX_WIDTH = 16;

	public CogGuideSprite(TextureRegion tr, float left, float top, float width, float height)
	{
		super(tr);

		setupPosition(left, top, width, height);
		this.setColor(1, 0, 0, 0.5f);
	}

	public void setupPosition(float left, float top, float width, float height)
	{
		float spriteWidth = MAX_WIDTH;

		if (width == 256)
		{
			spriteWidth = MAX_WIDTH / 2;
		}
		else if (width == 128)
		{
			spriteWidth = MAX_WIDTH / 4;
		}

		setCurrentRectF(new RectF(
				left + (width / 2) - (spriteWidth / 2),
				top,
				left + (width / 2) - (spriteWidth / 2) + spriteWidth,
				top + (height / 2)));

		this.setOrigin(m_CurrentRectF.width() / 2, m_CurrentRectF.height());
		// this.setOrigin(0+ 8,height);

	}

	private final float COLOR_MERGE_FACTOR = 0.05f;

	@Override
	public void setColor(float r1, float g1, float b1, float a1)
	{
		if (this.getColor().a != a1 || this.getColor().r != r1 || this.getColor().b != b1 || this.getColor().g != g1)
		{
			super.setColor(Utils.ColourBlend(this.getColor(), COLOR_MERGE_FACTOR, r1, g1, b1, a1));
		}
	}

}

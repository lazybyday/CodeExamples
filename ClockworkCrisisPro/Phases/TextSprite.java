package com.DamnItJenkins.ClockworkCrisisPro.Phases;

import com.DamnItJenkins.ClockworkCrisisPro.FontManager;
import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;

public class TextSprite extends KeyFrameSprite
{
	private String m_Text = "";
	private FontManager.Fonts m_Font;
	private int m_Padding = 0;
	private HAlignment m_TextAlignment = null;

	public TextSprite(String text, int padding, FontManager.Fonts font, HAlignment alignment)
	{
		m_Text = text;
		m_Padding = padding;
		m_Font = font;
		m_TextAlignment = alignment;
		
		
		setCurrentRectF(new RectF(
				-5000,
				-5000,
				1,
				1
			));	
	}

	@Override
	public boolean hasDrawText()
	{
		return true;
	}
	
	public boolean hasDrawImage()
	{
		return false;
	}

	@Override
	public void DrawText(SpriteBatch batch)
	{
		BitmapFont f = ClockConstants.m_FontManager.getFont(m_Font);
		
		f.drawWrapped(
				batch,
				m_Text,
				this.getX() + m_Padding,
				this.getY() + m_Padding,
				this.getWidth() - m_Padding - m_Padding,
				m_TextAlignment);
	}

}

package com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites;

import com.DamnItJenkins.ClockworkCrisisPro.FontManager;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrameSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.VictoryQuotes;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;

public class ColumnedTextSprite extends KeyFrameSprite
{	
	private String m_Column1Text = ""; 
	private String m_Column2Text = ""; 
	
	public FontManager.Fonts m_Font = FontManager.Fonts.QuoteFontWhite;
	
	public String getColumn1Text()
	{	
		return m_Column1Text;
	}
	
	public void setColumn1Text(String value)
	{
		m_Column1Text = value;
	}
	
	public String getColumn2Text()
	{
		return m_Column2Text;
	}
	
	public void setColumn2Text(String value)
	{
		m_Column2Text = value;
	}

	public ColumnedTextSprite(TextureRegion tr)
	{
		super(tr);
	}

	@Override
	public boolean hasDrawText()
	{
		return true;
	}
	
	@Override
	public void DrawText(SpriteBatch batch)
	{
		int padding = (int)(this.getWidth() * 0.08);
		
		BitmapFont font = ClockConstants.m_FontManager.getFont(m_Font);
		
		TextBounds bounds = font.getWrappedBounds(
				this.getColumn1Text(),
				this.getWidth() - padding - padding);

		font.drawWrapped(
				batch,
				this.getColumn1Text(),
				this.getX()+ padding,
				this.getY() + padding,
				this.getWidth() - padding - padding,
				HAlignment.LEFT);
		
		font.drawWrapped(
				batch,
				this.getColumn2Text(),
				this.getX()+ padding + bounds.width,
				this.getY() + padding,
				this.getWidth()- padding- padding,
				HAlignment.LEFT);
	}

}

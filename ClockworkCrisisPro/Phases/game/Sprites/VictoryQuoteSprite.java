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

public class VictoryQuoteSprite extends KeyFrameSprite
{	
	private String m_VictyoryQuoteCustomText = ""; 
	
	private String[] m_VictoryQuote = null;
	
	public FontManager.Fonts m_Font = FontManager.Fonts.QuoteFontWhite;
	
	public String getVictoryQuoteText()
	{
		if (m_VictyoryQuoteCustomText == "")
		{		
			return m_VictoryQuote[VictoryQuotes.QUOTE_TEXT];
		}
		else
		{
			return m_VictyoryQuoteCustomText;
		}			
	}

	public String getVictoryQuoteAuthor()
	{
		if (m_VictyoryQuoteCustomText == "")
		{		
			return m_VictoryQuote[VictoryQuotes.QUOTE_AUTHOR];
		}
		else
		{
			return "";
		}		
	}
	
	public void setVictyoryQuoteCustomText(String value)
	{
		m_VictyoryQuoteCustomText = value;
	}

	public VictoryQuoteSprite(TextureRegion tr)
	{
		super(tr);
		VictoryQuotes victoryQuotes = new VictoryQuotes();
		m_VictoryQuote = victoryQuotes.getRandomQuote();
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
				this.getVictoryQuoteText(),
				this.getWidth() - padding - padding);

		font.drawWrapped(
				batch,
				this.getVictoryQuoteText(),
				this.getX()+ padding,
				this.getY() + padding,
				this.getWidth() - padding - padding,
				HAlignment.LEFT);
		font.drawWrapped(
				batch,
				this.getVictoryQuoteAuthor(),
				this.getX()+ padding,
				this.getY() + padding + (font.getLineHeight()) + bounds.height,
				this.getWidth()- padding- padding,
				HAlignment.RIGHT);
	}

}

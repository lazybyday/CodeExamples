package com.DamnItJenkins.ClockworkCrisisPro;

import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontManager
{
	public static enum Fonts
	{
		  QuoteFontWhite
		, QuoteFontBlack
		, GeneralFont
	}
	
	private volatile static BitmapFont m_FontQuoteWhite = null;
	private volatile static BitmapFont m_FontQuoteBlack = null;
	private volatile static BitmapFont m_font25 = null;

	public FontManager()
	{		
	}
	
	public void LoadFonts()
	{
		float h = Gdx.graphics.getHeight();
		float screenSquare = ClockConstants.CalculateScreenSquare((float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());
        
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/upcll.ttf"));
 
		 m_font25 = generator.generateFont((int)h/25, FreeTypeFontGenerator.DEFAULT_CHARS, true); 		 		
		 m_FontQuoteWhite = generator.generateFont((int)(screenSquare/10), FreeTypeFontGenerator.DEFAULT_CHARS, true);		 
		 m_FontQuoteBlack = generator.generateFont((int)(screenSquare/10), FreeTypeFontGenerator.DEFAULT_CHARS, true);
		 m_FontQuoteBlack.setColor(0,0,0,1);
		 
		 generator.dispose(); // don't forget to dispose to avoid memory leaks!		        
	}
	
	public BitmapFont getFont(Fonts f)
	{
		switch (f)
		{
			case QuoteFontWhite:
				return m_FontQuoteWhite;
				
			case QuoteFontBlack:
				return m_FontQuoteBlack;	
				
			case GeneralFont:
				return m_font25;
		}
		
		return null;
	}
	

}

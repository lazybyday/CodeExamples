package com.DamnItJenkins.ClockworkCrisisPro.Phases.endingScene;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import com.DamnItJenkins.ClockworkCrisisPro.FontManager;
import com.DamnItJenkins.ClockworkCrisisPro.GameManager;
import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.SoundManager;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.ButtonSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.IPhase;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrame;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrameSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.TextSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.VictoryQuoteSprite;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants.ImageNames;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EndingScenePhase implements IPhase
{
	private ArrayList<Sprite> m_SpriteList;
	private boolean m_IsFirstRender;
	private GameManager m_GameManager;

	private final String BUTTON_TYPE_QUIT = "Quit";
	
	private int menuButtonOffsetY = 0;
	
	public EndingScenePhase(GameManager gameManager)
	{
		m_SpriteList = new ArrayList<Sprite>();
		m_IsFirstRender = true;
		m_GameManager = gameManager;
	}

	private void BuildSprites()
	{			
		BuildVictoryQuoteSprite();
		BuildQuitButtonSprites();
		BuildCreditsSprite();
		
		//m_GameManager.BuildForegroundCombinedSprite(m_SpriteList);
		
		ClockConstants.m_FontManager.getFont(FontManager.Fonts.QuoteFontBlack).setColor(0,0,0,0);
	}
	
	private TextSprite creditsSprite;
	
	private void BuildCreditsSprite()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		float spriteWidth = w / 2;
		float spriteHeight = h / 2;

		// square up sprite
		if (spriteWidth > spriteHeight)
		{
			spriteWidth = spriteHeight;
		}

		if (spriteHeight > spriteWidth)
		{
			spriteHeight = spriteWidth;
		}

		creditsSprite = new TextSprite(
				"Credits:\n" +
				"Alec Jenkins - Lead Developer\n" +
				"Gemma Manock - Design Rewriter\n"+
				"Elisa Weston - Events Coordinator\n" +
				"Blair Weston - Developer\n" +
				"\n and \n\n" +
				"Hunter Jenkins - Chief Whip"
						, 0, FontManager.Fonts.QuoteFontBlack, HAlignment.LEFT);

		creditsSprite.setOrigin(spriteWidth / 2, spriteHeight / 2);

		// left to right
		KeyFrame kfMove = new KeyFrame();
		int lnMilliseconds = 2000;

		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				w - spriteWidth,
				h ,
				w ,
				h+ spriteHeight);
		// kfMove.StartRotation = 0;

		lnMilliseconds += 30000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				w - spriteWidth,
				0 - spriteHeight,
				w,
				0);
		// kfMove.EndRotation = 180;

		creditsSprite.AddKeyFrame(kfMove);
		m_SpriteList.add(creditsSprite);
	}
	
	
	private VictoryQuoteSprite victoryQuoteSprite;

	private void BuildVictoryQuoteSprite()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		float spriteWidth = w / 2;
		float spriteHeight = h / 2;

		// square up sprite
		if (spriteWidth > spriteHeight)
		{
			spriteWidth = spriteHeight;
		}

		if (spriteHeight > spriteWidth)
		{
			spriteHeight = spriteWidth;
		}

		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.textblock_512),
				0,
				0,
				512,
				512);
		tr.flip(false, true);
		victoryQuoteSprite = new VictoryQuoteSprite(tr);

		victoryQuoteSprite.setOrigin(spriteWidth / 2, spriteHeight / 2);
		victoryQuoteSprite.m_Font = FontManager.Fonts.QuoteFontBlack;


		KeyFrame kfMove = new KeyFrame();
		int lnMilliseconds = 0;

		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				w / 2 - (spriteWidth / 2),
				h / 5,
				w / 2 + (spriteWidth / 2),
				h / 5 + spriteHeight);

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				w / 2 - (spriteWidth / 2),
				h / 5,
				w / 2 + (spriteWidth / 2),
				h / 5 + spriteHeight);

		victoryQuoteSprite.AddKeyFrame(kfMove);
		m_SpriteList.add(victoryQuoteSprite);
	}
	
	
	private ButtonSprite buttonSpriteQuit = null;
	
	private void BuildQuitButtonSprites()
	{
		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.button_main_menu),
				0,
				0,
				512,
				128);
		tr.flip(false, true);
		buttonSpriteQuit = new ButtonSprite(BUTTON_TYPE_QUIT, tr);

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		int w = (int) spriteWidth;
		int h = (int) (128 * (spriteWidth / 512));

		menuButtonOffsetY += h + 10;

		int xOffset = 0;
		int yOffset = menuButtonOffsetY;

		int lnMilliseconds = 1000;

		KeyFrame lastTitleKeyFframe = victoryQuoteSprite.GetKeyFrames().get(
				victoryQuoteSprite.GetKeyFrames().size() - 1);
		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				-w,
				lastTitleKeyFframe.EndRectF.bottom + yOffset,
				-w + w,
				lastTitleKeyFframe.EndRectF.bottom + yOffset + h);

		lnMilliseconds += 1000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + yOffset,
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + w + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + h + yOffset);

		buttonSpriteQuit.AddKeyFrame(kfMove);

		m_SpriteList.add(buttonSpriteQuit);
	}

	
	@Override
	public String getPhaseName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Sprite> getSpriteList()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	float fAplha = 0;

	@Override
	public void onDraw(SpriteBatch batch)
	{
		if (m_IsFirstRender)
		{
			BuildSprites();
			m_IsFirstRender = false;
		}

		Gdx.gl.glClearColor( 1, 1, 1, 1 );
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		Iterator<Sprite> it1 = m_SpriteList.iterator();
		while (it1.hasNext())
		{
			Sprite obj = it1.next();

			if (obj instanceof KeyFrameSprite)
			{
				((KeyFrameSprite) obj).ProcessKeyFrame();
			}
		}
		
			
		if (fAplha < 1)
		{
			fAplha +=0.01f;
			
			if (fAplha > 1)
			{
				fAplha =1;
			}
		}

		ClockConstants.m_FontManager.getFont(FontManager.Fonts.QuoteFontBlack).setColor(0,0,0, fAplha );

		batch.begin();

		Iterator<Sprite> it = m_SpriteList.iterator();
		while (it.hasNext())
		{
			Sprite obj = it.next();

			if (obj instanceof KeyFrameSprite)
			{
				KeyFrameSprite keyFrameSprite = (KeyFrameSprite) obj;

				if (keyFrameSprite.getIsHidden() == false)
				{
					if (keyFrameSprite.hasDrawImage())
					{
						if (!(obj instanceof VictoryQuoteSprite))
						{
							keyFrameSprite.draw(batch);
						}
					}

					if (keyFrameSprite.hasDrawText())
					{
						keyFrameSprite.DrawText(batch);
					}
				}
			}
			else
			{
				obj.draw(batch);
			}
		}
		
		batch.end();
		
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		Iterator<Sprite> it = m_SpriteList.iterator();
		while (it.hasNext())
		{
			Sprite obj = it.next();

			if (obj instanceof KeyFrameSprite)
			{
				KeyFrameSprite keyFrameSprite = (KeyFrameSprite) obj;

				if (obj instanceof ButtonSprite)
				{
					if (keyFrameSprite.getCurrentRectF().intersects(
							screenX,
							screenY,
							screenX + 1,
							screenY + 1))
					{
						if (obj instanceof ButtonSprite)
						{
							((ButtonSprite) obj).setIsMouseDown(true);
						}
					}
				}
			}
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		Iterator<Sprite> it = m_SpriteList.iterator();
		while (it.hasNext())
		{
			Sprite obj = it.next();

			if (obj instanceof KeyFrameSprite)
			{
				KeyFrameSprite keyFrameSprite = (KeyFrameSprite) obj;

				if (obj instanceof ButtonSprite)
				{
					ButtonSprite typedObj = ((ButtonSprite) obj);
					if (typedObj.getIsMouseDown() == true && keyFrameSprite.getCurrentRectF().intersects(
							screenX,
							screenY,
							screenX + 1,
							screenY + 1))
					{
						typedObj.setIsMouseDown(false);

						if (obj instanceof ButtonSprite)
						{				
							ClockConstants.m_SoundManager.PlaySound(SoundManager.SoundButtonPress);
							if (((ButtonSprite) obj).getButtonName() == BUTTON_TYPE_QUIT)
							{
								if (ClockConstants.CoreActivity != null)
								{
									ClockConstants.CoreActivity.showFullScreenAdEvent();
								}

								m_GameManager.getPhaseStack().pop();

								return true;
							}
						}
					}
					typedObj.setIsMouseDown(false);

				}
			}
		}

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

}

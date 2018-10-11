package com.DamnItJenkins.ClockworkCrisisPro.Phases.howToPlay;

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
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.CogGuideSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.DoorSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.VictoryQuoteSprite;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants.ImageNames;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HowToPlayPhase implements IPhase
{
	private ArrayList<Sprite> m_SpriteList;
	private boolean m_IsFirstRender;
	private GameManager m_GameManager;

	private final String BUTTON_TYPE_QUIT = "Quit";
	
	private int menuButtonOffsetY = 0;
	
	
	
	
	private KeyFrameSprite keyFrameSpriteRotateCogsTitle = null;
	
	public HowToPlayPhase(GameManager gameManager)
	{
		m_SpriteList = new ArrayList<Sprite>();
		m_IsFirstRender = true;
		m_GameManager = gameManager;
	}

	KeyFrameSprite cog3;
	
	private void BuildSprites()
	{			
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) w,
				(float) h);
		
		float cogWidth = spriteWidth/2;
		
		BuildDoorSprites();
		BuildVictoryQuoteSprite();
		BuildRotateCogsTitleSprites();
		BuildQuitButtonSprites();
		//BuildCog1();
		KeyFrameSprite cog1 = BuildTitleDeco1Sprite((int)((w/2) + cogWidth*0.1), (int)(cogWidth*0.2), ClockConstants.ImageNames.cog5_512, 45);
		cog1.setColor(0,1,0,1);
		
		KeyFrameSprite cog2 =BuildTitleDeco1Sprite((int)((w/2) + cogWidth*0.8), (int)(cogWidth*1), ClockConstants.ImageNames.cog6_512, 45);
		cog2.setColor(0,1,0,1);

		cog3 =BuildTitleDeco1Sprite((int)((w/2) + cogWidth*0.25), (int)(cogWidth*1.9), ClockConstants.ImageNames.cog8_512, 180);
		
		BuildFingerIcon();
		m_GameManager.BuildForegroundCombinedSprite(m_SpriteList);

	}

	
	private void BuildFingerIcon()
	{
		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.icon_finger),
				0,
				0,
				512,
				512);
		tr.flip(false, true);
		keyFrameSpriteRotateCogsTitle = new KeyFrameSprite(tr);
		keyFrameSpriteRotateCogsTitle.setOrigin(180, 60);
		int screenWidth = Gdx.graphics.getWidth();
		
		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) screenWidth,
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		spriteWidth = spriteWidth * 0.8f;
		spriteHeight = spriteHeight * 0.8f;
		
		int w = (int) spriteWidth;
		int h = (int) spriteHeight;

		int lnMilliseconds = 0;

		KeyFrame lastTitleKeyFframe = cog3.GetKeyFrames().get(
				cog3.GetKeyFrames().size() - 1);

		int xOffset = (int)(lastTitleKeyFframe.EndRectF.width() * 0.6);
		float yOffset = 0 ;

		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 500;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				screenWidth + 500,
				lastTitleKeyFframe.EndRectF.top + yOffset,
				screenWidth + 500 + spriteWidth,
				lastTitleKeyFframe.EndRectF.top + h + yOffset);
		kfMove.StartRotation = -45;

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + xOffset,
				lastTitleKeyFframe.EndRectF.top + yOffset,
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + w + xOffset,
				lastTitleKeyFframe.EndRectF.top + h + yOffset);
		kfMove.EndRotation = -45;
		
		keyFrameSpriteRotateCogsTitle.AddKeyFrame(kfMove);

		m_SpriteList.add( keyFrameSpriteRotateCogsTitle);

	}
	
	
	private void BuildRotateCogsTitleSprites()
	{
		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.rotate_cogs_title),
				0,
				0,
				512,
				128);
		tr.flip(false, true);
		keyFrameSpriteRotateCogsTitle = new KeyFrameSprite(tr);

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		int w = (int) spriteWidth;
		int h = (int) (128 * (spriteWidth / 512));

		int lnMilliseconds = 0;

		KeyFrame lastTitleKeyFframe = victoryQuoteSprite.GetKeyFrames().get(
				victoryQuoteSprite.GetKeyFrames().size() - 1);

		int xOffset = 0;
		float yOffset = -h;

		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + xOffset,
				-spriteWidth + yOffset,
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + w + xOffset,
				-spriteWidth + yOffset + h);

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + xOffset,
				lastTitleKeyFframe.EndRectF.top + yOffset,
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + w + xOffset,
				lastTitleKeyFframe.EndRectF.top + h + yOffset);

		keyFrameSpriteRotateCogsTitle.AddKeyFrame(kfMove);

		m_SpriteList.add( keyFrameSpriteRotateCogsTitle);

	}

	
	private DoorSprite spriteDoor;
	
	private void BuildDoorSprites()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		int doorGuardHeight = (int)(h*0.1f);
		
		Texture t = ClockConstants.GetTexture(ImageNames.door5_texture_512);
		t.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		spriteDoor = new DoorSprite(t, "DoorSprite", w, h-doorGuardHeight, doorGuardHeight);
		spriteDoor.setSize(w, h);

		spriteDoor.setCurrentRectF(new RectF(0, 0, w, (float)h-doorGuardHeight));
		spriteDoor.getDoorGuard().setCurrentRectF(new RectF(0, h-doorGuardHeight, w, h));
		
		m_SpriteList.add(spriteDoor);
		m_SpriteList.add(spriteDoor.getDoorGuard());
	}
	
	

private int TITLE_SPRITE_LAYER_INDEX = 2;
	
	private KeyFrameSprite BuildTitleDeco1Sprite(int xOffset, int yOffset, ImageNames renderImage, float endRotation)
	{
		int lnMilliseconds = 0;

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		spriteWidth = spriteWidth / 2;
		spriteHeight = spriteHeight / 2;

		TextureRegion tr = new TextureRegion(ClockConstants.GetTexture(renderImage), 0, 0, 512, 512);
		tr.flip(false, true);
		KeyFrameSprite kfs = new KeyFrameSprite(tr);
		kfs.setRenderImage(renderImage);

		kfs.setOrigin(spriteWidth / 2, spriteHeight / 2);

		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(-500, -500, -500 + spriteWidth, -500 + spriteHeight);
		kfMove.StartRotation = 0;

		lnMilliseconds += 1000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				(spriteWidth / 2) + xOffset,
				yOffset,
				(spriteWidth / 2) + spriteWidth + xOffset,
				spriteHeight + yOffset);
		kfMove.EndRotation = endRotation;

		kfs.AddKeyFrame(kfMove);

		KeyFrameSprite guide = CreateCogGuideSprite(kfs);
		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, guide);
		
		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, kfs);
		//m_DecoSprites.add(kfs);

		KeyFrameSprite shadow = CreateShadowSprite(kfs);
		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, shadow);
		//m_DecoSprites.add(shadow);
		
		return kfs;
		
		
	}
	
	private KeyFrameSprite CreateShadowSprite(KeyFrameSprite kfs)
	{
		ImageNames renderImage = ClockConstants.GetShadowForBmp(kfs.getRenderImage());

		TextureRegion tr = new TextureRegion(ClockConstants.GetTexture(renderImage), 0, 0, 256, 256);
		tr.flip(false, true);

		KeyFrameSprite kfsReturn = new KeyFrameSprite(tr);
		kfsReturn.setOrigin(kfs.getOriginX(), kfs.getOriginY());
		kfsReturn.setRotation(kfs.getRotation());
		kfsReturn.setRenderImage(renderImage);

		KeyFrame lastKeyFrame = kfs.GetKeyFrames().get(kfs.GetKeyFrames().size() - 1);

		int offset = (int) (lastKeyFrame.StartRectF.width() * 0.1);

		KeyFrame kfMove = new KeyFrame();

		kfMove.StartTime = lastKeyFrame.StartTime;
		kfMove.StartRectF = new RectF(
				lastKeyFrame.StartRectF.left + offset,
				lastKeyFrame.StartRectF.top + offset,
				lastKeyFrame.StartRectF.left + offset + lastKeyFrame.StartRectF.width(),
				lastKeyFrame.StartRectF.top + offset + lastKeyFrame.StartRectF.height());
		kfMove.StartRotation = lastKeyFrame.StartRotation;

		kfMove.EndTime = lastKeyFrame.EndTime;
		kfMove.EndRectF = new RectF(
				lastKeyFrame.EndRectF.left + offset,
				lastKeyFrame.EndRectF.top + offset,
				lastKeyFrame.EndRectF.left + offset + lastKeyFrame.EndRectF.width(),
				lastKeyFrame.EndRectF.top + offset + lastKeyFrame.EndRectF.height());
		kfMove.EndRotation = lastKeyFrame.EndRotation;

		kfsReturn.AddKeyFrame(kfMove);

		return kfsReturn;
	}
	
	private CogGuideSprite CreateCogGuideSprite(KeyFrameSprite kfs)
	{
		KeyFrame lastKeyFrame = kfs.GetKeyFrames().get(kfs.GetKeyFrames().size() - 1);
		
		CogGuideSprite cog1RotationGuide;
		TextureRegion tr2 = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.cog_guide2_16),
				0,
				0,
				16,
				512);
		tr2.flip(false, true);
		cog1RotationGuide = new CogGuideSprite(tr2, lastKeyFrame.EndRectF.left, lastKeyFrame.EndRectF.top , lastKeyFrame.EndRectF.width(), lastKeyFrame.EndRectF.height());
		//cog1RotationGuide.setRotation(45);
	
		int offset = 0;

		KeyFrame kfMove = new KeyFrame();

		kfMove.StartTime = lastKeyFrame.StartTime;
		kfMove.StartRectF = new RectF(
				lastKeyFrame.StartRectF.left + offset,
				lastKeyFrame.StartRectF.top + offset,
				lastKeyFrame.StartRectF.left + offset + lastKeyFrame.StartRectF.width(),
				lastKeyFrame.StartRectF.top + offset + lastKeyFrame.StartRectF.height());
		kfMove.StartRotation = lastKeyFrame.StartRotation;

		kfMove.EndTime = lastKeyFrame.EndTime;
		kfMove.EndRectF = new RectF(
				cog1RotationGuide.getCurrentRectF().left + offset,
				cog1RotationGuide.getCurrentRectF().top + offset,
				cog1RotationGuide.getCurrentRectF().left + offset + cog1RotationGuide.getCurrentRectF().width(),
				cog1RotationGuide.getCurrentRectF().top + offset + cog1RotationGuide.getCurrentRectF().height());
		kfMove.EndRotation = lastKeyFrame.EndRotation;

		cog1RotationGuide.AddKeyFrame(kfMove);
		
		
		return cog1RotationGuide;
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
		victoryQuoteSprite.m_Font = FontManager.Fonts.QuoteFontWhite;
		victoryQuoteSprite.setVictyoryQuoteCustomText("Tap and hold each cog to rotate them. You need to line up all of the cogs to win. When synchronized, each cog will turn green in colour.");


		KeyFrame kfMove = new KeyFrame();
		int lnMilliseconds = 0;

		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				0 - spriteWidth,
				h / 5,
				0,
				h / 5 + spriteHeight);

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				w / 2 - (spriteWidth ),
				h / 5,
				w / 2 ,
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

		float screenWidth = Gdx.graphics.getWidth();
		
		float spriteWidth = ClockConstants.CalculateScreenSquare(
				screenWidth,
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
				(screenWidth/2) - (lastTitleKeyFframe.EndRectF.width()/2) + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + yOffset,
				(screenWidth/2) + (lastTitleKeyFframe.EndRectF.width()/2) + xOffset,
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
		
		Iterator<Sprite> it1 = m_SpriteList.iterator();
		while (it1.hasNext())
		{
			Sprite obj = it1.next();

			if (obj instanceof KeyFrameSprite)
			{
				((KeyFrameSprite) obj).ProcessKeyFrame();
				
				if (obj instanceof CogGuideSprite)
				{
					obj.setColor(1, 0, 0, 0.5f);
				}
			}
		}
		
			
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
						keyFrameSprite.draw(batch);
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
					ClockConstants.m_SoundManager.PlaySound(SoundManager.SoundButtonPress);
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
							if (((ButtonSprite) obj).getButtonName() == BUTTON_TYPE_QUIT)
							{
								if (ClockConstants.CoreActivity != null)
								{
									ClockConstants.CoreActivity.showFullScreenAdEvent();
								}

								IPhase s = m_GameManager.getPhaseStack().pop();

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

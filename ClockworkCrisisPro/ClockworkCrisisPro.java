package com.DamnItJenkins.ClockworkCrisisPro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.DamnItJenkins.ClockworkCrisisPro.Phases.IPhase;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.mainMenu.MainMenuPhase;
import com.DamnItJenkins.ClockworkCrisisPro.ads.IAdListener;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ClockworkCrisisPro implements ApplicationListener,  InputProcessor 
{
	private List<IAdListener> m_Adlisteners = new ArrayList<IAdListener>();
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	
	private GameManager m_GameManager = null;
    int m_CurrentLevel = -1;
	private int[] m_framesPreSecond = new int[10];
	
	private boolean m_IsPaused = false;
	
	private boolean m_ShowFPS = false;
	
	public GameManager getGameManager()
	{
		return m_GameManager;
	}
	
	
	public void addAdListener(IAdListener toAdd) 
	{
		m_Adlisteners.add(toAdd);
    }

    public void showFullScreenAdEvent() 
    {
        for (IAdListener hl : m_Adlisteners)
        {
            hl.showFullScreenAd();
        }
    }
	
	
	@Override
	public void create() 
	{
		
		Gdx.gl.glDisable(GL10.GL_DITHER);
		Gdx.gl.glDisable(GL10.GL_DEPTH_TEST);
		Gdx.gl.glDisable(GL10.GL_LIGHTING);
		Gdx.gl.glDepthMask(false);
		Gdx.gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		Gdx.gl.glEnable(GL10.GL_TEXTURE_2D);
		Gdx.gl.glDisable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
	
		
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		ClockConstants.CoreActivity = this;
		
		ClockConstants.CogSize = new int[]
		{
				(int)h/2
				,(int)h/3
				,(int)h/4
				//,(int)h/5
				//,(int)h/6
		};
		
		camera = new OrthographicCamera(w, h);
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		//camera.position.set(w,h, 0);
		batch = new SpriteBatch();	
		
		m_CurrentLevel = 1;
        m_GameManager = new GameManager((int)w, (int)h);
        m_GameManager.getPhaseStack().push(new MainMenuPhase(m_GameManager));
        
        Gdx.input.setInputProcessor(this);
        
        GenerateFonts();
        GenerateSounds();
        
        for (int i = 0; i < m_framesPreSecond.length;i++)
        {
        	m_framesPreSecond[i]= 0;
        }

	}
	
	
	private void GenerateFonts ()
	{
		ClockConstants.m_FontManager.LoadFonts();
	}
	
	private void GenerateSounds()
	{
		ClockConstants.m_SoundManager.LoadSounds();
	}

	@Override
	public void dispose() {
		batch.dispose();
		ClockConstants.m_AssetManager.clear();
		ClockConstants.m_AssetManager.dispose();
		ClockConstants.m_SoundManager.DisposeSounds();
		

	}

	int intFPS = 0;
	
	@Override
	public void render() 
	{		
		Gdx.gl.glClearColor( 0, 0, 0, 1 );
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		//batch.disableBlending();
		
		Calendar time = Calendar.getInstance();		

		if (m_GameManager != null && !m_IsPaused)
		{
			IPhase currentPhase = m_GameManager.getPhaseStack().peek();
							
			currentPhase.onDraw(batch);

			float fTotalFrames = 0;

			for (int i = 0; i < m_framesPreSecond.length; i++)
			{
				fTotalFrames += m_framesPreSecond[i];
			}

			if (fTotalFrames != 0) 
			{
				intFPS = (Math.round(fTotalFrames	/ (m_framesPreSecond.length - 1) * 10) / 10);
			}
			
			
			if (m_framesPreSecond != null)
			{
				m_framesPreSecond[time.get(Calendar.SECOND) % m_framesPreSecond.length] += 1;
				m_framesPreSecond[(time.get(Calendar.SECOND) + 1) % m_framesPreSecond.length] = 0;
			}
		}
				
		
		if (m_ShowFPS)
		{
			BitmapFont f = ClockConstants.m_FontManager.getFont(FontManager.Fonts.GeneralFont);
			BitmapFont quoteFont = ClockConstants.m_FontManager.getFont(FontManager.Fonts.QuoteFontWhite);
			
			batch.begin();	
			f.draw(batch, "FPS: "+ intFPS,10, quoteFont.getLineHeight() + 10);
			batch.end();
		}
	}

	@Override
	public void resize(int width, int height) 
	{
		/*IPhase currentPhase = m_GameManager.getPhaseStack().peek();
		
		if (currentPhase instanceof MainMenuPhase)
		{		
			create() ;
		}*/
		
		/*float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w, h);

		camera.position.set(0, 0, 0);*/
	}

	@Override
	public void pause() 
	{
		m_IsPaused = true;
	}

	@Override
	public void resume() {
		GenerateFonts();
		GenerateSounds();
		m_IsPaused = false;
	}
	
    @Override
    public boolean keyDown(int keycode) 
    {
    	return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) 
    {
    	if (m_GameManager != null)
		{
			IPhase currentPhase = m_GameManager.getPhaseStack().peek();
							
			return currentPhase.keyTyped(character);
		}
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) 
    {
    	if (m_GameManager != null)
		{
			IPhase currentPhase = m_GameManager.getPhaseStack().peek();
							
			return currentPhase.touchDown(screenX, screenY, pointer, button);
		}
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) 
    {
    	if (m_GameManager != null)
		{
			IPhase currentPhase = m_GameManager.getPhaseStack().peek();
							
			return currentPhase.touchUp(screenX, screenY, pointer, button);
		}
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
    	if (m_GameManager != null)
		{
			IPhase currentPhase = m_GameManager.getPhaseStack().peek();
							
			return currentPhase.touchDragged(screenX, screenY, pointer);
		}
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

package  com.DamnItJenkins.ClockworkCrisisPro.Phases;

import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ButtonSprite extends KeyFrameSprite
{
	private String m_ButtonName = "";
	
	private boolean m_IsMouseDown = false;
	
	public String getButtonName()
	{
		return m_ButtonName;
	}
	
	public boolean getIsMouseDown()
	{
		return m_IsMouseDown;
	}
	
	public void setIsMouseDown (boolean b)
	{		
		m_IsMouseDown = b;
		
		if (b == true)
		{
			setColor(Color.YELLOW);
		}
		else
		{
			setColor(Color.WHITE);
		}
	}
	
	public ButtonSprite (String buttonName, TextureRegion tr)
	{
		super(tr);
		m_CurrentRectF = new RectF();
		m_ButtonName = buttonName;
	}

	
	
	@Override
	public String toString()
	{
		return getButtonName();
	}
}

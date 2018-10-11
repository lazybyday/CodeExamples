package com.DamnItJenkins.ClockworkCrisisPro.Phases;

import java.util.ArrayList;

import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants.ImageNames;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IKeyFrameSprite 
{
	public RectF getCurrentRectF();
	
	public ImageNames getRenderImage();
	
	public void AddKeyFrame(KeyFrame keyFrame);
	
	public ArrayList<KeyFrame> GetKeyFrames();
	
	public void ProcessKeyFrame();
	
	public boolean hasDrawImage();
	
	public boolean hasDrawText();
	
	public void DrawText (SpriteBatch batch);
	
//	public boolean onTouchEvent(MotionEvent e);
}

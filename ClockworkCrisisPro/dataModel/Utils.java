package com.DamnItJenkins.ClockworkCrisisPro.dataModel;

import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.badlogic.gdx.graphics.Color;

public final class Utils
{
	public final static boolean CirclesInterset(RectF rect1, RectF rect2)
	{
		return CirclesInterset(
				rect1.left + (rect1.width() / 2),
				rect1.top + (rect1.height() / 2),
				(rect1.width() / 2),
				rect2.left + (rect2.width() / 2),
				rect2.top + (rect2.height() / 2),
				(rect2.width() / 2));
	}

	public final static boolean CirclesInterset(float x1, float y1, float r1, float x2, float y2, float r2)
	{
		boolean blnReturn = false;
		double distance = Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));

		if (distance < (r1 + r2))
		{
			// System.out.println("circles overlap");
			blnReturn = true;
		}
		else
		{
			// System.out.println("circles are separate");
			blnReturn = false;
		}

		return blnReturn;

	}

	public static Color ColourBlend(Color startColor, float colourBlendFactor, float r1, float g1, float b1, float a1)
	{
		float COLOR_MERGE_FACTOR = colourBlendFactor;

		if (startColor.a != a1 || startColor.r != r1 || startColor.b != b1 || startColor.g != g1)
		{
			Color current = startColor;
			float r = current.r;
			float g = current.g;
			float b = current.b;
			float a = current.a;

			if (current.r > r1)
			{
				r = current.r - COLOR_MERGE_FACTOR;

				if (r < 0)
				{
					r = 0;
				}
				else if (r < r1)
				{
					r = r1;
				}
			}
			else if (current.r < r1)
			{
				r = current.r + COLOR_MERGE_FACTOR;

				if (r > 1)
				{
					r = 1;
				}
				else if (r > r1)
				{
					r = r1;
				}

			}

			if (current.g > g1)
			{
				g = current.g - COLOR_MERGE_FACTOR;

				if (g < 0)
				{
					g = 0;
				}
				else if (g < g1)
				{
					g = g1;
				}
			}
			else if (current.g < g1)
			{
				g = current.g + COLOR_MERGE_FACTOR;

				if (g > 1)
				{
					g = 1;
				}
				else if (g > g1)
				{
					g = g1;
				}
			}

			if (current.b > b1)
			{
				b = current.b - COLOR_MERGE_FACTOR;

				if (b < 0)
				{
					b = 0;
				}
				else if (b < b1)
				{
					b = b1;
				}
			}
			else if (current.b < b1)
			{
				b = current.b + COLOR_MERGE_FACTOR;

				if (b > 1)
				{
					b = 1;
				}
				else if (b > b1)
				{
					b = b1;
				}

			}

			if (current.a > a1)
			{
				a = current.a - COLOR_MERGE_FACTOR;

				if (a < 0)
				{
					a = 0;
				}
				else if (a < a1)
				{
					a = a1;
				}
			}
			else if (current.a < a1)
			{
				a = current.a + COLOR_MERGE_FACTOR;

				if (a > 1)
				{
					a = 1;
				}
				else if (a > a1)
				{
					a = a1;
				}
			}

			return new Color(r, g, b, a);
		}

		return startColor;
	}

}

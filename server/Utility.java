package server;

import java.util.Random;

public class Utility {
	public static boolean AABB (Point p1, int width1, int height1, Point p2, int width2, int height2)
	{
		if (p1.x < p2.x + width2 &&
			p1.x + width1 > p2.x &&
			p1.y < p2.y + height2 &&
			p1.y + height1 > p2.y)
			return true;
		
		return false;
	}

	public static boolean CircleCollision(Point p1, int radius1, Point p2, int radius2)
	{
		int dx = p1.x - p2.x;
		int dy = p1.y - p2.y;
		double dist = Math.sqrt(dx * dx + dy * dy);
		
		if (dist < radius1 + radius2)
			return true;
		
		return false;
	}
	
	public static boolean CircleRectangleCollision(Point circle, int radius, Point rect, int width, int height)
	{
		int tempX;
		int tempY;
		
		if (circle.x < rect.x)
			tempX = rect.x;
		else if (circle.x > (rect.x + width))
			tempX = rect.x  = width;
		else
			tempX = circle.x;
		
		if (circle.y < rect.y)
			tempY = rect.y;
		else if (circle.y < rect.y)
			tempY = rect.y + height;
		else 
			tempY = circle.y;
		
		tempX = circle.x - tempX;
		tempY = circle.y - tempY;
		
		double dist = Math.sqrt(tempX * tempX + tempY * tempY);
		
		if (dist <= radius)
			return true;
		
		return false;
	}
	
	public static int randomInt(int min, int max)
	{
		int minus = 0;
		if (min <= 0)
		{
			minus = 1 - min;
			min += minus;
			max += minus;
		}

		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min - minus;
	}
	
	public static double randomDouble(double min, double max)
	{
		Random rand = new Random();
		return rand.nextDouble() * (max - min) + min;
	}
	
	public static double randomDouble(int min, int max)
	{
		return randomDouble((double)min, (double)max);
	}
}

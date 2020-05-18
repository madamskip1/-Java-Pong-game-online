package server;

import java.util.Random;
import miscellaneous.Point;

public class Utility {
	public static boolean AABB(Point p1, int width1, int height1, Point p2, int width2, int height2) {
		int x1 = p1.getX();
		int y1 = p1.getY();
		int x2 = p2.getX();
		int y2 = p2.getY();
		if (x1 < x2 + width2 && x1 + width1 > x2 && y1 < y1 + height2 && y1 + height1 > y2)
			return true;

		return false;
	}

	public static boolean CircleCollision(Point p1, int radius1, Point p2, int radius2) {
		int dx = p1.getX() - p2.getX();
		int dy = p1.getY() - p2.getY();
		double dist = Math.sqrt(dx * dx + dy * dy);

		if (dist < radius1 + radius2)
			return true;

		return false;
	}

	public static boolean CircleRectangleCollision(Point circle, int radius, Point rect, int width, int height) {
		int tempX;
		int tempY;

		int circX = circle.getX();
		int circY = circle.getY();
		int rectX = rect.getX();
		int rectY = rect.getY();

		if (circX < rectX)
			tempX = rectX;
		else if (circX > (rectX + width))
			tempX = rectX = width;
		else
			tempX = circX;

		if (circY < rectY)
			tempY = rectY;
		else if (circY < rectY)
			tempY = rectY + height;
		else
			tempY = circY;

		tempX = circX - tempX;
		tempY = circY - tempY;

		double dist = Math.sqrt(tempX * tempX + tempY * tempY);

		if (dist <= radius)
			return true;

		return false;
	}

	public static int randomInt(int min, int max) {
		int minus = 0;
		if (min <= 0) {
			minus = 1 - min;
			min += minus;
			max += minus;
		}

		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min - minus;
	}

	public static double randomDouble(double min, double max) {
		Random rand = new Random();
		return rand.nextDouble() * (max - min) + min;
	}

	public static double randomDouble(int min, int max) {
		return randomDouble((double) min, (double) max);
	}
}

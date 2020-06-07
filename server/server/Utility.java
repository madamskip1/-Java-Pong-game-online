package server;

import java.util.Random;

/**
 * Klasa zawieraj¹ca funkcje u¿yteczne w wielu miejscach
 * 
 */
public class Utility {

	/**
	 * Wykrywa kolizjê okrêgu z prostok¹tem
	 * 
	 * @param circle - po³o¿enie okrêgu
	 * @param radius - promieñ okrêgu
	 * @param rect   - po³o¿enie prostok¹ta
	 * @param width  - szerokoœæ prostok¹ta
	 * @param height -wysokoœæ prostok¹ta
	 * @return true je¿eli kolizja wystêpuje, false jeœli nie
	 */
	public static boolean CircleRectangleCollision(Point circle, int radius, Point rect, int width, int height) {
		int tempX = 0;
		int tempY = 0;

		if (circle.x <= rect.x)
			tempX = rect.x;
		else if (circle.x > (rect.x + width))
			tempX = rect.x + width;
		if (circle.x > rect.x && circle.x < rect.x + width)
			tempX = circle.x;

		if (circle.y <= rect.y)
			tempY = rect.y;
		else if (circle.y > rect.y)
			tempY = rect.y + height;
		if (circle.y > rect.y && circle.y < rect.y + height)
			tempY = circle.y;

		tempX = circle.x - tempX;
		tempY = circle.y - tempY;

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

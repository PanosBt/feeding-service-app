package gr.hua.dit.feeding_service_app.utilites;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import gr.hua.dit.feeding_service_app.entities.Application;

/**
 * Utility classes for basic functions of our application 
 * @author panos
 *
 */
public class Utilities {

	/**
	 * = 10000 the score that an application gets when the student who submitted it
	 * deserves feeding no matter what
	 */
	public static final int ABSOLUTE_SCORE = 10000;

	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	
	/**
	 * read a date string formated as "dd/MM/yyyy" and parse/convert to a date
	 * @param dateStr the string to convert
	 * @return the Date object
	 * @throws ParseException if the string is not formated as dd/MM/yyyy
	 */
	public static Date parseDate(String dateStr) throws ParseException {
		return formatter.parse(dateStr);
	}

	/**
	 * read a Date and format/convert it to a string
	 * @param date
	 * @return the string formated as dd/MM/yyyy or null if passed Date is null
	 */
	public static String formatDate(Date date) {
		if (date != null)
			return formatter.format(date);
		return null;
	}

	/**
	 * Scores the given application based on social criteria for feeding service
	 * 
	 * @param application The application to be scored. "score" field will be
	 *                    updated upon success
	 * @return the applied score upon success, or -1 upon failure
	 */
	public static int scoreApplication(Application application) {
		int score = 0;

		if ((application.getFamilyIncome() == 0) && !application.isMother_employeed()
				&& !application.isFather_employeed())
			score = ABSOLUTE_SCORE;
		else {
			// calculate score based on income
			if (application.getFamilyIncome() < 10000)
				score += 100;
			else if (application.getFamilyIncome() < 15000)
				score += 30;
			// increase score based on num_siblings, if any
			score += application.getNum_siblings() * 20;

			// read university city to compare w/ student's origin city
			Properties prop = new Properties();
			String city;

			try (InputStream in = Utilities.class.getClassLoader().getResourceAsStream("city.properties")) {
				prop.load(in);
				city = prop.getProperty("city");
			} catch (IOException e) {
				e.printStackTrace();
				return -1;
			}

			if (!application.getOrigin_city().equals(city))
				score += 50;
		}

		application.setScore(score);
		return score;
	}

}

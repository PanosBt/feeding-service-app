package gr.hua.dit.feeding_service_app.utilites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import gr.hua.dit.feeding_service_app.entity.Application;

public class Utilities {

	public static class DateUtil {
		private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		// read a date string and parse/convert to a date
		public static Date parseDate(String dateStr) throws ParseException {
			return formatter.parse(dateStr);
		}

		// read a date and format/convert to a string
		public static String formatDate(Date theDate) {
			if (theDate != null)
				return formatter.format(theDate);
			return null;
		}
	}

	public static class ScoringUtil {

		public static int scoreApplication(Application application) {

			// TODO add unemployed parents BL

			int score = 0;

			if (application.getFamilyIncome() < 10000)
				score += 100;
			else if (application.getFamilyIncome() < 15000)
				score += 30;
			score += application.getNum_siblings() * 20;

			Properties prop = new Properties();

			String city;

			try (FileInputStream in = new FileInputStream("bl")) {
				prop.load(in);
				city = prop.getProperty("city");
			} catch (IOException e) {
				e.printStackTrace();
				city = "";
			}
			if (!application.getOrigin_city().equals(city))
				score += 50;

			return score;
		}
	}
}

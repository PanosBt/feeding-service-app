package gr.hua.dit.feeding_service_app.utilites;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

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
	public static final Integer ABSOLUTE_SCORE = 10000;

	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	private static final String CITY_PROPERTIES_PATH = "classpath:/resources/city.properties";
	
	
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
	 * extract year from a Date object the non-deprecated way
	 * @param date the Date object
	 * @return the year in the Date object
	 */
	public static int getYearFromDate(Date date) {
		// here I use Calendar instead of LocalDate because date can be either
		// java.util.Date or java.sql.Date (tnx Hibernate)
		// DESPITE the fact that Application.subm_date (where this method is mostly used)
		// is declared as a java.util.Date
		// apparently Hibernate downcasts to java.sql.Date when it fetches it
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * Scores the given application based on social criteria for feeding service
	 * 
	 * @param application The application to be scored. "score" field will be
	 *                    updated upon success
	 * @return the applied score upon success, or -1 upon failure
	 */
	public static Integer scoreApplication(Application application) {
		Integer score = 0;

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
			
			Resource resource = new ClassPathResource("city.properties");

			try (InputStream in = resource.getInputStream()) {
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
	
	/**
	 * Returns the max students that will have a feeding card for feeding service as a string 
	 * 
	 * @return the StudentLimit at the moment or -1 upon failure as a String
	 */
	public static String getStudentLimitStr() {
		
		Properties prop = new Properties();
		String studentlimit;
			
		
		try (InputStream in = Utilities.class.getResourceAsStream(CITY_PROPERTIES_PATH)){
			 prop.load(in);
			 studentlimit = prop.getProperty("limit");
		} catch (IOException e) {
			studentlimit = "-1";
			e.printStackTrace();
		}

		return studentlimit;
	}
	
	/**
	 * Returns the max students that will have a feeding card for feeding service as an Integer
	 * @return the StudentLimit at the moment or -1 upon failure as an Integer
	 */
	public static Integer getStudentLimit() {
		return Integer.parseInt(getStudentLimitStr());
	}
	
	/**
	 * Updates the max-students that will be able to have a feeding card for feeding service
	 * 
	 * @param String
	 * 
	 * @return true if update was success or false upon failure 
	 */
	public static boolean updateStudentLimit (String limit) {
		
		Properties prop = new Properties();
		boolean studentlimitupdated = true;
		
		try(InputStream in = Utilities.class.getResourceAsStream(CITY_PROPERTIES_PATH)) {
			 //FileInputStream in = new FileInputStream("city.properties");
			 prop.load(in);
		} catch (FileNotFoundException e) {
			studentlimitupdated = false;
			e.printStackTrace();
		} catch (IOException e) {
			studentlimitupdated = false;
			e.printStackTrace();
		}
		
		// this is a terrbile hack, sorry
		// TODO city.properties should not be a resource...
		URL resourceUrl = Utilities.class.getResource(CITY_PROPERTIES_PATH);
		
		File file = null;
		try {
			file = new File(resourceUrl.toURI());
		} catch (URISyntaxException e) {
			studentlimitupdated = false;
			e.printStackTrace();
		}

		try (OutputStream out = new FileOutputStream(file)) {
			prop.setProperty("limit", limit);
			
			prop.store(out, null);
		} catch (FileNotFoundException e) {
			studentlimitupdated = false;
			e.printStackTrace();
		} catch (IOException e) {
			studentlimitupdated = false;
			e.printStackTrace();
		}
		return studentlimitupdated;
		
	}
	
}

























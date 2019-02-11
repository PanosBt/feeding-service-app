package gr.hua.dit.feeding_service_app.utilites;

import java.util.HashMap;
import java.util.Map;

public class CustomAuthorityUtilities {
	public static final String STUDENT_ROLE = "ROLE_STUDENT";
	public static final String CLERK_ROLE = "ROLE_CLERK";
	public static final String SUPERVISOR_ROLE = "ROLE_SUPERVISOR";
	public static final String ADMIN_ROLE = "ROLE_ADMIN";
	
	/**
	 * Each possible user role mapped to a more normalized
	 * string for usage in prettier output
	 */
	public static final Map<String, String> NORMALIZED_ROLES = new HashMap<String, String>() {
		/**
		 * In case we want to serialize this
		 */
		private static final long serialVersionUID = 1L;
		{
			put(STUDENT_ROLE, "Student");
			put(CLERK_ROLE, "Clerk");
			put(SUPERVISOR_ROLE, "Supervisor");
			put(ADMIN_ROLE, "Admin");
			
		}
	};

}

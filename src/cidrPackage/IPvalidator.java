package cidrPackage;

import java.util.regex.*;

public class IPvalidator {

//	private String IPregex = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
	private String IPregex = "^((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9]))$";
	
	// pattern object for IP address regular expression
	private Pattern pattern = Pattern.compile(IPregex);
	
	public boolean validateIP(String ip) {
		// engine that performs match operations against user input
		Matcher matcher = pattern.matcher(ip);
		
		return matcher.matches();
	}
}

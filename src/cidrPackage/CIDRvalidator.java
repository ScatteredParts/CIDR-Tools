package cidrPackage;

import java.util.regex.*;

public class CIDRvalidator {
	
	private String CIDRregex = "^((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9]))\\/([1-9]|[1-2][0-9]|3[0-2])$";

	// pattern object for IP address regular expression
	private Pattern pattern = Pattern.compile(CIDRregex);
	
	public boolean validateCIDRnotation(String cidr) {
		// validates entire CIDR string to see if it's valid
		// no processing of capture groups
		
		Matcher matcher = pattern.matcher(cidr);
		
		return matcher.matches();
	}

	public boolean validateCIDRandPrintCaptureGroups(String cidr) {
		// validates CIDR string and prints out capture groups
		
		Matcher matcher = pattern.matcher(cidr);
		
		// count number of capture groups in pattern
		int captureGroupCount = matcher.groupCount();
		
		// print capture groups
		while (matcher.find()) {
			for (int i = 0; i <= captureGroupCount; i++) {
				System.out.println("Group(" + i + ") = " + matcher.group(i) );
			}
		}
		
		return matcher.matches();
	}

	public String getIPfromCIDRnotation(String cidr) {
		//get IP address from CIDR notation string

		//	Group 1 = entire IP address
		//	Group 2 = first octet
		//	Group 3 = second octet
		//	Group 4 = third octet
		//	Group 5 = fourth octet
		//	Group 6 = network mask bits
		
		// do pattern match
		Matcher matcher = pattern.matcher(cidr);

		matcher.find();
		return matcher.group(1);		
	}
	
	public int getMaskBitsFromCIDRnotation(String cidr) {
		//get the number of mask bits from CIDR notation string
		
		// do pattern match
		Matcher matcher = pattern.matcher(cidr);

		matcher.find();
		return Integer.parseInt(matcher.group(6));
	}
	
	public String getCIDRdata(String cidr, CIDRdata returnWhat) {
		
		String returnValue;
		
		// do pattern match
		Matcher matcher = pattern.matcher(cidr);
		
		matcher.find();
		
		switch(returnWhat) {
			case BASEIP:
				returnValue = matcher.group(1);
				break;
			case OCTET1:
				returnValue = matcher.group(2);
				break;				
			case OCTET2:
				returnValue = matcher.group(3);
				break;				
			case OCTET3:
				returnValue = matcher.group(4);
				break;				
			case OCTET4:
				returnValue = matcher.group(5);
				break;				
			case NETWORKMASK:
				returnValue = matcher.group(6);
				break;
			default:
				throw new AssertionError("Unknown CIDRdata enum value: " + returnWhat);
		}
		
		return returnValue;
	}
}

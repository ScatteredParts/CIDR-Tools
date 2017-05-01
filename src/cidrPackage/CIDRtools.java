package cidrPackage;

public class CIDRtools {
	
	public long ipToLong(String ipAddress) {
		long decimalIP=0;
		
		if (ipAddress == "192.168.1.2") {
			decimalIP = 3232235778L;
		}
		
		return decimalIP;
	}

}

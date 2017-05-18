package cidrPackage;

public class CIDRtools {
	
	public long ipToLong(String ipAddress) {
		long decimalIP=0;

		/* v1: if block to get test to pass
		if (ipAddress == "192.168.1.2") {
			decimalIP = 3232235778L;
		}
		*/
		
		String[] ipOctetArray = ipAddress.split("\\.");

		for (int i=0; i < ipOctetArray.length; i++) {
			int power =3 - i;
			
			int ip = Integer.parseInt(ipOctetArray[i]);
			decimalIP += ip * Math.pow(256, power);
		}
		
		return decimalIP;
	}

}

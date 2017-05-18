package cidrPackage;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CIDRtoolsTest {
	
	private CIDRtools cidr = new CIDRtools();
	private IPvalidator ip = new IPvalidator();
	private CIDRvalidator cidrValidator = new CIDRvalidator();
		
	@Test
	public void returns3232235778ifIPaddressIs192_168_1_2() throws Exception {
		long IPdecimal = cidr.ipToLong("192.168.1.2");
		
		assertThat(IPdecimal).isEqualTo(3232235778L);
	}
	
	@Test
	public void returns0ifIPaddressIsNot192_168_1_2() throws Exception {
		//v2: ipToLong now converts, rather than using a simple if block.  So this test will now fail
		long IPdecimal = cidr.ipToLong("127.10.30.40");
		
		assertThat(IPdecimal).isEqualTo(0L);
	}

	@Test
	public void returns2131369512ifIPaddressIs127_10_30_40() throws Exception {
		//v2: this uses the same IP as in returns0ifIPaddressIsNot192_168_1_2(), but should now return 2131369512
		long IPdecimal = cidr.ipToLong("127.10.30.40");
		
		assertThat(IPdecimal).isEqualTo(2131369512L);
	}
	
	@Test
	public void returnsFalseIfIPaddressIs192() throws Exception {
		boolean badIP = ip.validateIP("192");
		
		assertThat(badIP).isEqualTo(false);
	}
	
	@Test
	public void returnsFalseIfIPaddressIs192_168() throws Exception {
		boolean badIP = ip.validateIP("192.168");
		
		assertThat(badIP).isEqualTo(false);
	}
	
	@Test
	public void returnsFalseIfIPaddressIs192_168_1() throws Exception {
		boolean badIP = ip.validateIP("192.168.1");
		
		assertThat(badIP).isEqualTo(false);
	}
	
	@Test
	public void returnsTrueIfIPaddressIs192_168_1_2() throws Exception {
		boolean goodIP = ip.validateIP("192.168.1.2");
		
		assertThat(goodIP).isEqualTo(true);
	}
	
	@Test
	public void returnsFalseIfIPaddressHasOctetGreaterThan255() throws Exception {
		boolean whackedIP = ip.validateIP("192.168.999.1");
		
		assertThat(whackedIP).isEqualTo(false);
	}
	
	@Test
	public void returnsFalseIfIPaddressDoesntUsePeriod() throws Exception {
		boolean badIPseparator = ip.validateIP("192!168,1.2");
		
		assertThat(badIPseparator).isEqualTo(false);
	}
	
	@Test
	public void returnsFalseIfCIDRnotationHasNoSlash() throws Exception {
		boolean badCIDR = cidrValidator.validateCIDRnotation("192.168.1.2");
		
		assertThat(badCIDR).isEqualTo(false);
	}
	
	@Test
	public void returnsTrueIfGoodCIDR() throws Exception {
		boolean goodCIDR = cidrValidator.validateCIDRnotation("192.168.1.2/24");
		
		assertThat(goodCIDR).isEqualTo(true);
	}
	
	@Test
	public void returnsFalseIfBadCIDRseparator() throws Exception {
		boolean badCIDRsep = cidrValidator.validateCIDRnotation("192.168.1.2!24");
		
		assertThat(badCIDRsep).isEqualTo(false);
	}
	
	@Test
	public void returnsFalseIfCIDRMaskBitsGreaterThan32() throws Exception {
		boolean badCIDRmask = cidrValidator.validateCIDRnotation("192.168.1.2/42");
		
		assertThat(badCIDRmask).isEqualTo(false);		
	}

	@Test
	public void returnsTrueIfGoodCIDRandPrintsMatchGroups() throws Exception {
		boolean goodCIDR = cidrValidator.validateCIDRandPrintCaptureGroups("192.168.9.99/24");
		
		assertThat(goodCIDR).isEqualTo(true);
	}
	
	@Test
	public void returnsIPaddressFromGoodCIDRstring() throws Exception {
		String IPfromGoodCIDR = cidrValidator.getIPfromCIDRnotation("192.168.9.99/24");
		
		assertThat(IPfromGoodCIDR).isEqualTo("192.168.9.99");
	}
	
	@Test
	public void returnsMaskBitsFromGoodCIDRstring() throws Exception {
		int MaskBitsFromGoodCIDR = cidrValidator.getMaskBitsFromCIDRnotation("192.168.9.99/24");
		
		assertThat(MaskBitsFromGoodCIDR).isEqualTo(24);		
	}
}

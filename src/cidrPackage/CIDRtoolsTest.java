package cidrPackage;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CIDRtoolsTest {
	
	private CIDRtools cidr = new CIDRtools();
	private IPvalidator ip = new IPvalidator();
		
	@Test
	public void returns3232235778ifIPaddressIs192_168_1_2() throws Exception {
		long IPdecimal = cidr.ipToLong("192.168.1.2");
		
		assertThat(IPdecimal).isEqualTo(3232235778L);
	}
	
	@Test
	public void returns0ifIPaddressIsNot192_168_1_2() throws Exception {
		long IPdecimal = cidr.ipToLong("127.10.30.40");
		
		assertThat(IPdecimal).isEqualTo(0L);
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
	
}

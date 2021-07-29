package extentReportYoitube;

import org.testng.annotations.DataProvider;

public class dataProviderForTest {
	
	@DataProvider(name="signInData")
	public Object[][] signInData() {
		return new Object[][] {
			{"CouCou11","KK@gmail.com"}
		};
	}

}

package setting;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MariaDbConnectionTest {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://34.80.56.221:3306/mhmm?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
	private static final String USER = "hong";
	private static final String PW = "mhmm2019";

	@Test
	public void testConnection() throws Exception {
		Class.forName(DRIVER);

		try (Connection con = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

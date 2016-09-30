package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

public class HiveJdbcTool {
	static final String HIVE_URI = "jdbc:hive2://localhost:10000/default";

	static {
		try {
			Class.forName("org.apache.hive.jdbc.HiveDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) throws Exception {
		try (Connection conn = DriverManager.getConnection(HIVE_URI, "hive", "")) {
			QueryRunner runner = new QueryRunner(true);
			ResultSetHandler<List<Object[]>> rsh = new ArrayListHandler();
			List<Object[]> results = runner.query(conn, "show databases", rsh);
			results.stream()
			.map(row -> row[0])
			.forEach(System.out::println);
		}
	}
}

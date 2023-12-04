package config;

public class DatabaseConfig {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "pKMFr7CW";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Grisvo";

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }

    public static String getDbUrl() {
        return DB_URL;
    }
}

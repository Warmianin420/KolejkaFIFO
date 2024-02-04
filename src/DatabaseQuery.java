import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseQuery {
    public static void executeQuery() {
        String query = "SELECT * FROM twoja_tabela";
        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Przykład odczytu: rs.getString("nazwa_kolumny");
                System.out.println(rs.getString("nazwa_kolumny"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

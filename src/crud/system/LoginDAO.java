
package crud.system;

import java.sql.*;

public class LoginDAO {

   public static boolean login(String username, String password) {
    try {
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return true;
        }

        con.close();

    } catch (Exception e) {
        System.out.println("Login Error: " + e);
    }
    return false;
   }
}

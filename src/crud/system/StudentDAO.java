
package crud.system;
import java.sql.*;

public class StudentDAO {

    // ➕ Add
    public static void addStudent(Student s) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getGrade());

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            System.out.println("Add Error: " + e);
        }
    }

    // 📄 Get All Students (لـ JTable)
    public static ResultSet getStudents() {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery(); // مهم: بنرجّع ResultSet

        } catch (Exception e) {
            System.out.println("Get Error: " + e);
            return null;
        }
    }

    // ✏️ Update
    public static void updateStudent(Student s) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE students SET name=?, age=?, grade=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getGrade());
            ps.setInt(4, s.getId());

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            System.out.println("Update Error: " + e);
        }
    }

    // ❌ Delete
    public static void deleteStudent(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            System.out.println("Delete Error: " + e);
        }
    }
}
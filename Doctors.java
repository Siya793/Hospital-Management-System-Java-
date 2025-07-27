package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctors {

    private final Connection connection;  // Made final since it is assigned only once

    public Doctors(Connection connection, java.util.Scanner scanner) {
        this.connection = connection;
        // Removed unused 'scanner' parameter warning by commenting (if needed, keep it)
        // If scanner is not needed at all, you may remove it from both constructor and class.
    }

    // View doctors method
    public void viewDoctors() {
        String query = "SELECT * FROM doctors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors:");
            System.out.println("+------------+------------------------+------------------------+");
            System.out.println("| Doctor id  | Name                   | Specialization         |");
            System.out.println("+------------+------------------------+------------------------+");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("|%-12d|%-24s|%-24s|\n", id, name, specialization);
                System.out.println("+------------+------------------------+------------------------+");
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving doctors: " + e.getMessage());
        }
    }

    // Get doctor by id method
    public boolean getDoctorsById(int id) {
        String query = "SELECT * FROM doctors WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Simplified 'if' condition
        } catch (SQLException e) {
            System.err.println("Error while checking doctor ID: " + e.getMessage());
            return false;
        }
    }
}

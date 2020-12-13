package springmvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements StudentInterface {


    public Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public StudentService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addRecord(StudentData studentData) throws SQLException, ClassNotFoundException {

        //Query
        String sql = "INSERT INTO studentrecord (snumber, sname, gpa) Values (? , ?, ?) ;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //Binding Values to the Prepared Statement
        preparedStatement.setString(1, studentData.getSnumber());
        preparedStatement.setString(2, studentData.getSname());
        preparedStatement.setDouble(3, studentData.getGpa());

        //True If record Inserted Successfully
        if(preparedStatement.executeUpdate() == 1){
            System.out.println("One record added successfully.");
            return true;
        }
        //False If error occurs
        else
            return false;

    }

    @Override
    public boolean deleteRecord(String snumber) throws SQLException, ClassNotFoundException {

        //Query
        String query = "DELETE FROM studentrecord Where snumber = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        //Binding the variables to the prepared statement
        preparedStatement.setString(1, snumber);

        //True if one record added deleted
        if(preparedStatement.executeUpdate() == 1) {
            System.out.println("One record deleted successfully.");
            return true;
        }
        //False if unable to delete the record
        else
            return false;
    }

    @Override
    public List<StudentData> displayRecord() throws SQLException, ClassNotFoundException {

        //Query
        String query = "SELECT * FROM studentrecord;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);


        //ResultSet will contain the result from the query execution
        ResultSet resultSet = preparedStatement.executeQuery();

        StudentData studentData;

        //List to return
        List<StudentData> recordList = new ArrayList<StudentData>();
        while(resultSet.next()){
            System.out.println("true");

            studentData = new StudentData(resultSet.getString("snumber"), resultSet.getString("sname"), resultSet.getDouble("gpa"));
            System.out.println("Student -> " + studentData.getSname() + "And values -> " + resultSet.getString("sname"));
            //Add the work list item into the list
            recordList.add(studentData);
        }

        return recordList;
    }
    @Override
    public boolean editRecord(StudentData studentData) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateRecord(StudentData studentData) throws SQLException, ClassNotFoundException {
        return false;
    }
}

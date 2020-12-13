package springmvc;

import java.sql.SQLException;
import java.util.List;

public interface StudentInterface {

    public boolean addRecord(StudentData studentData) throws SQLException, ClassNotFoundException;
    public boolean deleteRecord(String snumber) throws SQLException, ClassNotFoundException;
    public List<StudentData> displayRecord() throws SQLException, ClassNotFoundException;
    public boolean editRecord(StudentData studentData) throws SQLException, ClassNotFoundException;
    public boolean updateRecord(StudentData studentData) throws SQLException, ClassNotFoundException;
}

package springmvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class StudentServiceTest {


    @InjectMocks
    private StudentService studentService;

    @Mock
    private Connection connection;

    @Mock
    private ResultSet resultSet;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ConnectionWrapper connectionWrapper;

    StudentData studentData;

    @BeforeEach
    public void Setup() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connectionWrapper.connect()).thenReturn(connection);
        studentData = new StudentData("Manvir","4749", 3.75);
    }

    @Test
    void addRecord() throws SQLException, ClassNotFoundException {
        when(connection.prepareStatement("INSERT INTO studentrecord VALUES ( ?, ?, ? )")).thenReturn(preparedStatement);
        studentService.addRecord(studentData);
        verify(preparedStatement).executeUpdate();

    }

    @Test
    void deleteRecord() throws SQLException, ClassNotFoundException {
        when(connection.prepareStatement("Delete from studentrecord where snumber = ?")).thenReturn(preparedStatement);
        studentService.deleteRecord("Manvir");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void displayRecord() throws SQLException, ClassNotFoundException {
        List<StudentData> recordList = new ArrayList<StudentData>();
        when(connection.prepareStatement("INSERT INTO studentrecord VALUES ( ?, ?, ? )")).thenReturn(preparedStatement);
        studentService.addRecord(studentData);
        verify(preparedStatement).executeUpdate();
        doReturn(resultSet).when(preparedStatement).executeQuery();
        when(resultSet.first()).thenReturn(true);
        recordList = studentService.displayRecord();
        assertEquals(0, recordList.size());
    }
}
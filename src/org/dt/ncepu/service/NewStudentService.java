package org.dt.ncepu.service;

import org.dt.ncepu.domain.NewStudent;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/09/24 0024.
 */
@Service
public class NewStudentService {
    public Connection DBConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }
        try {
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/dreamtech?useUnicode=true&characterEncoding=utf8", "root", "115878");

            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void DisConnect(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addNewStudent(NewStudent newStudent) {
        Connection connection = DBConnect();
        String sql = "INSERT INTO newstudent(n_id,n_name, n_qq, n_phone, n_class, n_time, n_direction, n_read,n_description) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newStudent.getId());
            preparedStatement.setString(2,newStudent.getName());
            preparedStatement.setString(3,newStudent.getQq());
            preparedStatement.setString(4,newStudent.getPhone());
            preparedStatement.setString(5,newStudent.getnClass());
            preparedStatement.setLong(6,newStudent.getTime());
            preparedStatement.setString(7,newStudent.getDirection());
            preparedStatement.setBoolean(8,newStudent.isRead());
            preparedStatement.setString(9,newStudent.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

    public List<NewStudent> showNewStudents() {
        Connection connection = DBConnect();
        List<NewStudent> newStudents = new ArrayList<>();
        String sql = "SELECT * FROM newstudent WHERE n_read=FALSE ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NewStudent newStudent = new NewStudent(
                        resultSet.getString("n_id"),
                        resultSet.getString("n_name"),
                        resultSet.getString("n_qq"),
                        resultSet.getString("n_phone"),
                        resultSet.getString("n_class"),
                        resultSet.getLong("n_time"),
                        resultSet.getString("n_description"),
                        resultSet.getBoolean("n_read")
                );
                newStudents.add(newStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql1 = "SELECT * FROM newstudent WHERE n_read=TRUE ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NewStudent newStudent = new NewStudent(
                        resultSet.getString("n_id"),
                        resultSet.getString("n_name"),
                        resultSet.getString("n_qq"),
                        resultSet.getString("n_phone"),
                        resultSet.getString("n_class"),
                        resultSet.getLong("n_time"),
                        resultSet.getString("n_description"),
                        resultSet.getBoolean("n_read")
                );
                newStudents.add(newStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return newStudents;
    }

    public void deleteNewStudent(String id){
        Connection connection=DBConnect();
        String sql="DELETE FROM newstudent WHERE n_id = ?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

    public boolean searchNewStudent(String id){
        Connection connection=DBConnect();
        String sql="SELECT * FROM newstudent WHERE n_id = ?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return true;
    }

    public void readNewStudent(String id){
        Connection connection=DBConnect();
        String sql="UPDATE newstudent SET n_read=TRUE WHERE n_id = ?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }
}

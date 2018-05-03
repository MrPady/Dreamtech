package org.dt.ncepu.service;

import org.dt.ncepu.domain.Project;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/09/05 0005.
 */
@Service
public class ProjectService {

    private Connection DBConnect() {
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

    private void DisConnect(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Project> showProjects(){
        Connection connection = DBConnect();
        String sql = "SELECT * FROM project";
        List<Project> projects=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Project project=new Project(
                        resultSet.getInt("p_id"),
                        resultSet.getLong("p_time"),
                        resultSet.getString("p_teamer"),
                        resultSet.getString("p_leader"),
                        resultSet.getString("p_name"),
                        resultSet.getString("p_url"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return projects;
    }

    public void addProject(Project project){
        Connection connection=DBConnect();
        String sql = "INSERT INTO project (p_time, p_teamer, p_leader, p_name, p_url) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,project.getTime());
            preparedStatement.setString(2,project.getTeamer());
            preparedStatement.setString(3,project.getLeader());
            preparedStatement.setString(4,project.getName());
            preparedStatement.setString(5,project.getUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DisConnect(connection);
        }
    }

    public void deleteProject(int id){
        Connection connection=DBConnect();
        String sql="DELETE FROM project WHERE p_id =?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DisConnect(connection);
        }
    }

    public void editProject(Project project){
        Connection connection=DBConnect();
        String sql = "Update project SET p_name=?,p_time=?,p_leader=?,p_teamer=? WHERE p_id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,project.getName());
            preparedStatement.setLong(2,project.getTime());
            preparedStatement.setString(3,project.getLeader());
            preparedStatement.setString(4,project.getTeamer());
            preparedStatement.setInt(5,project.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DisConnect(connection);
        }
    }
}

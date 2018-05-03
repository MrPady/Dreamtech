package org.dt.ncepu.service;

import org.dt.ncepu.domain.Field;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/08/14 0014.
 */
@Service
public class FieldService {

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

    public List<Field> getFields() {
        Connection connection = DBConnect();
        List<Field> fields = new ArrayList<>();
        String sql = "SELECT * FROM field";
        ResultSet rs;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Field field = new Field(
                        rs.getInt("f_id"),
                        rs.getString("f_name"),
                        rs.getBoolean("f_haschild"),
                        rs.getInt("f_father_id"),
                        rs.getString("f_description")
                );
                fields.add(field);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return fields;
    }

    public void addField(Field field) {
        Connection connection = DBConnect();
        if(field.getFatherId()!=-1){
            try {
                String sql = "INSERT INTO field (f_name,f_father_id,f_haschild) VALUES (?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, field.getName());
                preparedStatement.setInt(2, field.getFatherId());
                preparedStatement.setBoolean(3, false);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String sql1 = "UPDATE field SET f_haschild=TRUE WHERE f_id=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setInt(1, field.getFatherId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String sql = "INSERT INTO field (f_name,f_haschild) VALUES (?,?)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, field.getName());
                preparedStatement.setBoolean(2, false);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DisConnect(connection);
    }

    public String deleteField(int id) {
        Connection connection = DBConnect();
        boolean hasFather = false;
        int fatherId = 0;
        boolean onlyChild = false;
        String sql = "SELECT * FROM article WHERE a_field=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                DisConnect(connection);
                return "该领域下有文章，无法删除";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql1 = "SELECT * FROM field WHERE f_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                boolean hasChild = resultSet.getBoolean("f_hasChild");
                if (resultSet.getObject("f_father_id") != null) {
                    fatherId = resultSet.getInt("f_father_id");
                    hasFather = true;
                }
                if (hasChild) {
                    DisConnect(connection);
                    return "该领域下有子领域，无法删除";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql2 = "DELETE FROM field WHERE f_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (hasFather) {
            String sql3 = "SELECT * FROM field WHERE f_father_id=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql3);
                preparedStatement.setInt(1, fatherId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    onlyChild = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (onlyChild) {
                String sql4 = "UPDATE field SET f_haschild = FALSE  WHERE f_id =?";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql4);
                    preparedStatement.setInt(1, fatherId);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        DisConnect(connection);
        return "删除成功";
    }

    public Field getFieldFromName(String name){
        Connection connection = DBConnect();
        String sql = "SELECT * FROM field WHERE f_name=?";
        ResultSet rs;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Field field = new Field(
                        rs.getInt("f_id"),
                        rs.getString("f_name"),
                        rs.getBoolean("f_haschild"),
                        rs.getInt("f_father_id"),
                        rs.getString("f_description")
                );
                return field;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return null;
    }

    public Field getFieldFromId(int id){
        Connection connection = DBConnect();
        String sql = "SELECT * FROM field WHERE f_id=?";
        ResultSet rs;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Field field = new Field(
                        rs.getInt("f_id"),
                        rs.getString("f_name"),
                        rs.getBoolean("f_haschild"),
                        rs.getInt("f_father_id"),
                        rs.getString("f_description")
                );
                return field;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return null;
    }

    public void editField(Field field){
        Connection connection = DBConnect();
        String sql = "UPDATE field SET f_name=?,f_haschild=?,f_father_id=?,f_description=? WHERE f_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,field.getId());
            preparedStatement.setBoolean(2,field.isHasChild());
            preparedStatement.setInt(3,field.getFatherId());
            preparedStatement.setString(4,field.getDescription());
            preparedStatement.setInt(5,field.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

}

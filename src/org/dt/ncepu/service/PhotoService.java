package org.dt.ncepu.service;

import org.dt.ncepu.domain.Gallery;
import org.dt.ncepu.domain.Photo;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/08/17 0017.
 */
@Service
public class PhotoService {

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

    public void addGallery(Gallery gallery){
        Connection connection=DBConnect();
        String sql="INSERT INTO gallery (g_name, g_description, g_surface_id, g_time ) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,gallery.getName());
            preparedStatement.setString(2,gallery.getDescription());
            preparedStatement.setInt(3,gallery.getSurfaceId());
            preparedStatement.setLong(4,gallery.getTime());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

    public void editGallery(Gallery gallery){
        Connection connection=DBConnect();
        String sql="UPDATE gallery SET g_name=?,g_description=? ,g_surface_id=? WHERE g_id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,gallery.getName());
            preparedStatement.setString(2,gallery.getDescription());
            preparedStatement.setInt(3,gallery.getSurfaceId());
            preparedStatement.setInt(4,gallery.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

    public Gallery getGallery(int id){
        Connection connection=DBConnect();
        String sql="SELECT * FROM gallery WHERE g_id = ?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Gallery gallery=new Gallery(
                        resultSet.getInt("g_id"),
                        resultSet.getString("g_name"),
                        resultSet.getString("g_description"),
                        resultSet.getInt("g_surface_id"),
                        resultSet.getLong("g_time")
                );
                DisConnect(connection);
                return gallery;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return null;
    }

    public void deleteGallery(int id){
        Connection connection=DBConnect();
        String sql="DELETE FROM gallery WHERE g_id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

    public List<Gallery> getGalleries(){
        Connection connection=DBConnect();
        List<Gallery> galleries=new ArrayList<>();
        String sql="SELECT * FROM gallery";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Gallery gallery=new Gallery(
                        resultSet.getInt("g_id"),
                        resultSet.getString("g_name"),
                        resultSet.getString("g_description"),
                        resultSet.getInt("g_surface_id"),
                        resultSet.getLong("g_time")
                );
                galleries.add(gallery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DisConnect(connection);
        }
        return galleries;
    }

    public void addPhoto(Photo photo){
        Connection connection=DBConnect();
        String sql="INSERT INTO photo(p_name, p_description, p_time, p_gallery) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,photo.getName());
            preparedStatement.setString(2,photo.getDescription());
            preparedStatement.setLong(3,photo.getTime());
            preparedStatement.setInt(4,photo.getGallery());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

    public void deletePhoto(int id){
        Connection connection=DBConnect();
        String sql="DELETE FROM photo WHERE p_id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

    public List<Photo> getPhotos(int gallery){
        Connection connection=DBConnect();
        List<Photo> photos=new ArrayList<>();
        String sql="SELECT * FROM photo WHERE p_gallery=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,gallery);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Photo photo=new Photo(
                        resultSet.getString("p_name"),
                        resultSet.getInt("p_id"),
                        resultSet.getString("p_description"),
                        resultSet.getLong("p_time"),
                        resultSet.getInt("p_gallery")
                );
                photos.add(photo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return photos;
    }

    public int getPhotoId(long time){
        Connection connection=DBConnect();
        String sql="SELECT p_id FROM photo WHERE p_time=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,time);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getInt("p_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return -1;
    }

    public Photo getPhoto(int id){
        Connection connection=DBConnect();
        List<Photo> photos=new ArrayList<>();
        String sql="SELECT * FROM photo WHERE p_id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Photo photo=new Photo(
                        resultSet.getString("p_name"),
                        id,
                        resultSet.getString("p_description"),
                        resultSet.getLong("p_time"),
                        resultSet.getInt("p_gallery"));
                return photo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return null;
    }

    public void editPhoto(Photo photo,int id){
        Connection connection=DBConnect();
        String sql="UPDATE photo SET p_name=?,p_description=? WHERE p_id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,photo.getName());
            preparedStatement.setString(2,photo.getDescription());
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }
}

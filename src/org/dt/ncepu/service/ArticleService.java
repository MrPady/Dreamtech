package org.dt.ncepu.service;

import org.dt.ncepu.domain.Article;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/08/14 0014.
 */
@Service
public class ArticleService {

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

    public void deleteArticle(String id){
        Connection connection=DBConnect();
        String sql="DELETE FROM article WHERE a_id =?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DisConnect(connection);
        }
    }

    public Article getFullArticle(int id) {
        Connection connection = DBConnect();
        String sql = "SELECT * FROM article WHERE a_id=?";
        ResultSet rs;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Article article = new Article(
                        rs.getString("a_title"),
                        rs.getLong("a_time"),
                        rs.getString("a_writer"),
                        rs.getInt("a_id"),
                        rs.getInt("a_field")
                );
                return article;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DisConnect(connection);
        }
        return null;
    }

    public List<Article> getArticles(int field) {
        Connection connection = DBConnect();
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM article WHERE a_field=?";
        ResultSet rs;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, field);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Article article = new Article(
                        rs.getString("a_title"),
                        rs.getLong("a_time"),
                        rs.getString("a_writer"),
                        rs.getInt("a_id"),
                        rs.getInt("a_field")
                );
                article.setBody(rs.getString("a_body"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DisConnect(connection);
        }
        return articles;
    }

    public void addArticle(Article article){
        Connection connection=DBConnect();
        String sql = "INSERT INTO article (a_title, a_body, a_time, a_writer, a_field) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2,article.getBody());
            preparedStatement.setLong(3,article.getTime());
            preparedStatement.setString(4,article.getWriter());
            preparedStatement.setInt(5,article.getField());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DisConnect(connection);
        }
    }

    public void editArticle(Article article){
        Connection connection=DBConnect();
        String sql = "Update article SET a_body=? WHERE a_id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,article.getBody());
            preparedStatement.setInt(2,article.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DisConnect(connection);
        }
    }

}

package org.dt.ncepu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dt.ncepu.domain.Grade;
import org.dt.ncepu.domain.Login;
import org.dt.ncepu.domain.Person;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.dt.ncepu.controller.PersonBackController.useSAXParser;

/**
 * Created by Administrator on 2017/07/18 0018.
 */
@Service
public class PersonService {

    private static final Log loggers =
            LogFactory.getLog(PersonService.class);

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

    public void addPerson(Person person) {
        Connection connection = DBConnect();
        String sql = "INSERT INTO person (p_id, p_name, p_grade, p_password, p_mail, p_phone, p_qq, p_description) VALUE (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setInt(3, person.getGrade());
            preparedStatement.setString(4, person.getId());
            preparedStatement.setString(5, person.getMail());
            preparedStatement.setString(6, person.getPhone());
            preparedStatement.setString(7, person.getQq());
            preparedStatement.setString(8, person.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

    public List<Person> getPeopleFromGrade(int Grade, boolean detail) {
        Connection connection = DBConnect();
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE p_grade=?";
        ResultSet rs;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Grade);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Person p = new Person();
                p.setName(rs.getString("p_name"));
                p.setGrade(rs.getInt("p_grade"));
                p.setId(rs.getString("p_id"));
                p.setDescription(rs.getString("p_description"));
                p.setShow(rs.getBoolean("p_show"));
                if (detail) {
                    p.setQq(rs.getString("p_qq"));
                    p.setPhone(rs.getString("p_phone"));
                    p.setMail(rs.getString("p_mail"));
                }
                people.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DisConnect(connection);
        }
        return people;
    }

    public Person getPersonDetailsFromId(String id, boolean detail) {
        Connection connection = DBConnect();
        String sql = "SELECT * FROM person WHERE p_id=?";
        ResultSet rs;
        Person p = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                p = new Person();
                p.setName(rs.getString("p_name"));
                p.setGrade(rs.getInt("p_grade"));
                p.setId(rs.getString("p_id"));
                p.setHasPic(rs.getBoolean("p_haspic"));
                p.setDescription(rs.getString("p_description"));
                if (detail) {
                    p.setQq(rs.getString("p_qq"));
                    p.setPhone(rs.getString("p_phone"));
                    p.setMail(rs.getString("p_mail"));
                    p.setPassword(rs.getString("p_password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DisConnect(connection);
        }
        return p;
    }

    public void deletePersonFrom(String id) {
        Connection connection = DBConnect();
        String sql = "DELETE FROM person WHERE p_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

    public void editPerson(Person person) {
        Connection connection = DBConnect();
        String sql = "UPDATE person SET p_description=?,p_qq=?,p_phone=?,p_mail=?,p_password=?,p_haspic=? WHERE p_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getDescription());
            preparedStatement.setString(2, person.getQq());
            preparedStatement.setString(3, person.getPhone());
            preparedStatement.setString(4, person.getMail());
            preparedStatement.setString(5, person.getPassword());
            preparedStatement.setBoolean(6, false);
            preparedStatement.setString(7, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

    public int[] getGrades() {
        int i = Grade.GetMaxGrade();
        int[] years = new int[i - 1999];
        for (int m = 2000; m <= i; m++) {
            years[m - 2000] = m;
        }
        return years;
    }

    public boolean isRecruiting() {
        Document document = null;
        int i = 1;
        try {
            document = useSAXParser(Grade.path + "\\WEB-INF\\info.xml");
            Element root = document.getRootElement();
            i = Integer.parseInt(root.getChildText("recruit"));
        } catch (IOException | JDOMException e) {
            e.printStackTrace();
        }
        return i == 1;
    }

    public void changeRecruit(boolean key) {
        Document document = null;
        try {
            document = useSAXParser(Grade.path + "\\WEB-INF\\info.xml");
            Element root = document.getRootElement();
            int i = 0;
            if (key)
                i = 1;
            root.getChild("recruit").setText(String.valueOf(i));
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(document, new FileOutputStream(Grade.path + "\\WEB-INF\\info.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

    public String checkPassword(Login login) {
        Connection connection = DBConnect();
        try {
            String sql = "SELECT p_password FROM person WHERE p_id=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, login.getName());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String password = resultSet.getString("p_password");
                    if (password.equals(login.getPassword())) {
                        return "1";
                    } else {
                        return "2";
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DisConnect(connection);
            return "3";
        } catch (NullPointerException e) {
            // e.printStackTrace();
            return "4";
        }
    }

    public void changPic(String id) {
        Connection connection = DBConnect();
        String sql = "UPDATE person SET p_haspic=TRUE WHERE p_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            loggers.info(id+":changePic");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

    public String getNumbers(String number) {
        Connection connection = DBConnect();
        StringBuffer stringBuffer = new StringBuffer();
        String sql = "SELECT p_phone FROM person WHERE p_grade=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(number));
            ResultSet rs = preparedStatement.executeQuery();
            boolean key = true;
            while (rs.next()) {
                String phone = rs.getString("p_phone");
                if (phone != null) {
                    if (!key) {
                        stringBuffer.append(",");
                    }
                    stringBuffer.append(phone);
                    key = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return stringBuffer.toString();
    }

    public List<Person> getGoodStudents() {
        Connection connection = DBConnect();
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE p_show = TRUE ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Person p = new Person();
                p.setName(rs.getString("p_name"));
                p.setGrade(rs.getInt("p_grade"));
                p.setId(rs.getString("p_id"));
                p.setHasPic(rs.getBoolean("p_haspic"));
                p.setDescription(rs.getString("p_description"));
                people.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return people;
    }

    public void setGoodStudent(String id){
        Connection connection=DBConnect();
        String sql="UPDATE person SET p_show=TRUE WHERE p_id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


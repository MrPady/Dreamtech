package org.dt.ncepu.service;

import org.dt.ncepu.domain.Bill;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/08/23 0023.
 */
@Service
public class BillService {
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

    public void addBill(Bill bill) {
        Connection connection = DBConnect();
        String sql = "INSERT INTO bill(b_time, b_money, b_detail) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, bill.getTime());
            preparedStatement.setFloat(2, bill.getMoney());
            preparedStatement.setString(3, bill.getDetail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }

    public List<Bill> showBills() {
        Connection connection = DBConnect();
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bill";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getInt("b_id"),
                        resultSet.getLong("b_time"),
                        resultSet.getFloat("b_money"),
                        resultSet.getString("b_detail")
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
        return bills;
    }

    public void deleteBill(int id){
        Connection connection=DBConnect();
        String sql="DELETE FROM bill WHERE b_id = ?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DisConnect(connection);
    }
}

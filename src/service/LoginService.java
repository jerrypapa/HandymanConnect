package service;

import Login.Admin;
import Login.Customer;
import Login.Handymen;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class LoginService {
    private Connection connection=null;
    private Admin admin;
    private Handymen handymen;
    private Customer customer;

    public Admin verifyLogin(Admin admin) {
        try {
            PreparedStatement statement=null;
            ResultSet resultSet =null;
            connection= DBConnection.getConnection();

            statement=connection.prepareStatement("SELECT * Admin WHERE password=? and email=?");
            statement.setString(1,admin.getPassword());
            statement.setString(2,admin.getEmail());

            resultSet=statement.executeQuery();

            while (resultSet.next()){
                admin=new Admin(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
            }

            if(admin!=null){
                return  admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Handymen verifyHandymanLogin(Handymen handymen){
        try {
            PreparedStatement statement=null;
            ResultSet resultSet =null;
            connection= DBConnection.getConnection();

            statement=connection.prepareStatement("SELECT * Handymen WHERE password=? and email=?");
            statement.setString(1,handymen.getPassword());
            statement.setString(2,handymen.getEmail());

            resultSet=statement.executeQuery();

            while (resultSet.next()){
                handymen=new Handymen(resultSet.getInt(7),resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),resultSet.getString(3),resultSet.getInt(5),resultSet.getString(9),resultSet.getString(6),resultSet.getString(8),resultSet.getString(10));
            }

            if(handymen!=null){
                return  handymen;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer verifyCustomerLogin(Customer customer){
        try {
            PreparedStatement statement=null;
            ResultSet resultSet =null;
            connection= DBConnection.getConnection();

            statement=connection.prepareStatement("SELECT * Customers WHERE password=? and email=?");
            statement.setString(1,customer.getPassword());
            statement.setString(2,customer.getEmail());

            resultSet=statement.executeQuery();

            while (resultSet.next()){
                customer=new Customer(resultSet.getInt(7),resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),resultSet.getString(3),resultSet.getInt(5),resultSet.getString(9),resultSet.getString(6),resultSet.getString(8),resultSet.getString(10));
            }

            if(customer!=null){
                return  customer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Util.ConnectionUtil;
import Model.Message;

public class MessageDAO {

    public Message insertMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            if(message.getMessage_text() == "" || message.getMessage_text().length() == 0){
                return null;
            }
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString method here.
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * from message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public List<Message> getMessagesByUser(int id){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * from message WHERE posted_by = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public Message getMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setInt method here.
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                return message;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteMessage(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "DELETE FROM message WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString method here.
            preparedStatement.setInt(1, message_id);
            
            preparedStatement.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateMessage(int message_id, Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            if(!message.getMessage_text().isEmpty() && message.getMessage_text().length() <= 255){
    //          Write SQL logic here. You should only be inserting with the name column, so that the database may
    //          automatically generate a primary key.
                String sql = "UPDATE message SET message_text = ? WHERE message_id = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                //write preparedStatement's setString method here.
                preparedStatement.setString(1, message.getMessage_text());
                preparedStatement.setInt(2, message_id);
                
                preparedStatement.executeUpdate();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
}

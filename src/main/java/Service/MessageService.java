package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {

    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message){
        return messageDAO.insertMessage(message);
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public List<Message> getUserMessages(int id){
        return messageDAO.getMessagesByUser(id);
    }

    public Message getMessage(int id){
        return messageDAO.getMessageById(id);
    }

    public Message deletMessage(int message_id){
        Message deletedMessage = messageDAO.getMessageById(message_id);
        messageDAO.deleteMessage(message_id);
        return deletedMessage;
    }

    public Message updateMessage(int message_id, Message message){
        messageDAO.updateMessage(message_id, message);
        if(message.getMessage_text().isEmpty() || message.getMessage_text().length() > 255){
            return null;
        }
        return messageDAO.getMessageById(message_id);
    }
    
}

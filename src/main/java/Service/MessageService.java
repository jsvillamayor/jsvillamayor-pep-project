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

    public Message deletMessage(int id){
        return messageDAO.deleteMessage(id);
    }

    public Message updateMessage(Message message){
        messageDAO.updateMessage(message);
        return messageDAO.getMessageById(message.getMessage_id());
    }
    
}

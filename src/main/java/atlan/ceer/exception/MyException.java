package atlan.ceer.exception;

public class MyException extends Exception{
    //存储提示信息
    private String messages;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public MyException(String messages) {
        this.messages = messages;
    }
}

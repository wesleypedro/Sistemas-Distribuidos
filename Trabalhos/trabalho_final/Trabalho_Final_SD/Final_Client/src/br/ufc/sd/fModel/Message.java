package br.ufc.sd.fModel;

/**
 *
 * @author Wesley Pedro
 */
public class Message {
    private int messageType;
    private int requestId;
    private String objectReference;
    private int methodId;
    private String arguments;
    
    public Message(int messageType, int requestId, String objectReference, int methodId, String arguments){
        this.messageType = messageType;
        this.requestId = requestId;
        this.objectReference = objectReference;
        this.methodId = methodId;
        this.arguments = arguments;
    }

    /**
     * @return Retorna o tipo da mensagem
     * 
     * 0 - Request      1 - Reply
     */
    public int getMessageType() {
        return messageType;
    }

    /**
     * @return the requestId
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * @return the objectReference
     */
    public String getObjectReference() {
        return objectReference;
    }

    /**
     * @return the methodId
     */
    public int getMethodId() {
        return methodId;
    }

    /**
     * @return the arguments
     */
    public String getArguments() {
        return arguments;
    }
    
    

    @Override
    public String toString() {
        return "Message Type: " + getMessageType() + "\n" +
                "Request Id: " + getRequestId() + "\n" +
                "Object Reference: " + getObjectReference() + "\n" +
                "Method Id: " + getMethodId() + "\n" +
                "Arguments: " + getArguments();
    }
}

public class Proxy{
    TCPClient client;

    //  Construtor que inicia a conexão com o TCPClient
    public Proxy() {
        client = new TCPClient();
    }

    //  Métodos relativos as operações da Calculadora
    public double add(double op1, double op2){
        client.sendRequest("add;" + String.valueOf(op1) + ";" + String.valueOf(op2));
        return Double.parseDouble(client.getResponse());
    }
    public double sub(double op1, double op2){
        client.sendRequest("sub;" + String.valueOf(op1) + ";" + String.valueOf(op2));
        return Double.parseDouble(client.getResponse());
    }
    public double mul(double op1, double op2){
        client.sendRequest("mul;" + String.valueOf(op1) + ";" + String.valueOf(op2));
        return Double.parseDouble(client.getResponse());
    }
    public double div(double op1, double op2){
        client.sendRequest("div;" + String.valueOf(op1) + ";" + String.valueOf(op2));
        return Double.parseDouble(client.getResponse());
    }

    //  Métodos relativos ao Chat
    public void sendMessage(String message){
        client.sendRequest("chat;" + message);
    }
    public String getMessage(){
        return client.getResponse();
    }

    public void close(){
        client.close();
    }
}
import java.util.Scanner;

public class Chat{
    Scanner entrada = new Scanner(System.in);
    String msOu;

    public void sendMessage(String message){
        System.out.println(message);
    }

    public String getMessage(){
        msOu = entrada.nextLine();
        return "[Server] "+msOu;
    }
}
public class Despachante{
    private Esqueleto esqueleto;
    private Chat chat;
    String reply;

    public Despachante(){
        esqueleto = new Esqueleto();
        chat = new Chat();
    }

    public String invoke(String message){
        String separa[] = message.split(";");
        
        if(separa[0].equals("chat")){
            chat.sendMessage(separa[1]);
            return chat.getMessage();
        }
        
        else{            
            switch (separa[0]) {
                case "add":
                    reply = esqueleto.add(separa[1] + ";" + separa[2]);
                    break;
                case "sub":
                    reply = esqueleto.sub(separa[1] + ";" + separa[2]);
                    break;
                case "mul":
                    reply = esqueleto.mul(separa[1] + ";" + separa[2]);
                    break;
                case "div":
                    reply = esqueleto.div(separa[1] + ";" + separa[2]);
                    break;
                default:
                    return "Erro!";
            }
            return reply;
        }
    }
}
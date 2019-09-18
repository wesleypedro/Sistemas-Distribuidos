package br.ufc.sd.fJson;

import br.ufc.sd.fModel.Ingresso;
import br.ufc.sd.fModel.LoginAtt;
import br.ufc.sd.fModel.Message;
import br.ufc.sd.fModel.UserAtt;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
        
/**
 *
 * @author Wesley Pedro
 */
public enum UserJson {
    INSTANCE;
    
    Gson gson = new  Gson();
    
    //      Controle Json referente a classe de Login
    public String LoginToGson(LoginAtt att){    
        return gson.toJson(att);
    }
    
    public LoginAtt LoginFromGson(String json){
        return gson.fromJson(json, LoginAtt.class);
    }
    
    
    //      Controle Json referente a classe admController
    public String IngressoToGson(Ingresso ingresso){
        return gson.toJson(ingresso);
    }
    
    public Ingresso IngressoFromGson(String json){
        return gson.fromJson(json, Ingresso.class);
    }
    
    public List IngressoListFromGson(String jsonArray){
        String substring = jsonArray.substring(1, jsonArray.length()-2);
        
        String [] array = substring.split("}, ");
        List <Ingresso> lista = new ArrayList<>();
        
        for(int i = 0; i < array.length; i ++){
            lista.add(IngressoFromGson(array[i]+"}"));
        }
        
        return lista;
    }
    
    
    //      Controle Json referente a classe UserAtt
    public String UserAttToGson(UserAtt userAtt){
        return gson.toJson(userAtt);
    }
    
    public UserAtt UserAttFromGson(String json){
        return gson.fromJson(json, UserAtt.class);
    }
    
    
    //      Controle Json referente ao empacotamento Message na funcao doOperatio da classe Client
    public String MessageToGson(Message message){
        return gson.toJson(message);
    }
    
    public Message MessageFromGson(String json){
        return gson.fromJson(json, Message.class);
    }
}

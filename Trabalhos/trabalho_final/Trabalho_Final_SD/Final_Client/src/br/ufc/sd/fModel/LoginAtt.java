package br.ufc.sd.fModel;

/**
 *
 * @author Wesley Pedro
 */
public class LoginAtt {
    private String user;
    private String cpf;
    private String pass;
    private boolean stat;
    private String cate;
    
    
    public LoginAtt(String user, String cpf, String pass, boolean stat, String cate){
        this.user = user;
        this.cpf = cpf;
        this.pass = pass;
        this.stat = stat;
        this.cate = cate;
    }
    
    public LoginAtt(String cpf, String pass){
        this.cpf = cpf;
        this.pass = pass;
        this.stat = false;
        this.cate = null;
    }
    
    public String getUser(){
        return this.user;
    }
    
    public String getCate(){
        return this.cate;
    }
    
    public boolean getStat(){
        return this.stat;
    }
}
package br.ufc.sd.fModel;

/**
 *
 * @author Wesley Pedro
 */
public class UserAtt {
    private String cpf;
    private String username;
    private String password;
    private String category;
    private String newPassword;
    
    
    public UserAtt(String cpf, String username, String password, String category){
        this.cpf = cpf;
        this.username = username;
        this.password = password;
        this.category = category;
    }
    
    public UserAtt(String cpf){
        this.cpf = cpf;
    }
    
    public UserAtt(String cpf, String password){
        this.cpf = cpf;
        this.password = password;
    }
    
    public UserAtt(String cpf, String password, String newPassword){
        this.cpf = cpf;
        this.password = password;
        this.newPassword = newPassword;
    }
    

    public String getCpf() {
        return cpf;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCategory() {
        return category;
    }
    
    @Override
    public String toString(){
        return "Usuario: " + getUsername() + "\n"+
                "CPF: " + getCpf() + "\n"+
                "Categoria: " + getCategory();
    }
}

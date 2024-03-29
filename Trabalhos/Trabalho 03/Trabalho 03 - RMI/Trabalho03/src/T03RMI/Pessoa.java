package T03RMI;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Pessoa() {
    }

    public Pessoa(String cpf) {
        this.cpf = cpf;
    }

    public Pessoa(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }
    
    
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void print() {
        System.out.println("Nome = " + getNome() + ",\nCPF = " + getCpf() + ",\n"
                + "Email = " + getEmail() + ",\nTelefone = " + getTelefone() + "\n");
    }

}

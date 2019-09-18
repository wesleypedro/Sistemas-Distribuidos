package br.ufc.sd.fModel;

/**
 *
 * @author Wesley Pedro
 */
public class Ingresso {
    
    private int codigo;
    private String nome;
    private int quantidade;
    private String horarioInicio;
    private String horarioTermino;
    private String data;
    private String endereco;
    private double preco;

    public Ingresso(String nome, int quantidade, String horarioInicio, String horarioTermino, String data, String endereco, double preco) {
        this.codigo = -1;
        this.nome = nome;
        this.quantidade = quantidade;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
        this.data = data;
        this.endereco = endereco;
        this.preco = preco;
    }
    
    
    public Ingresso(int codigo, String nome, int quantidade, String horarioInicio, String horarioTermino, String data, String endereco, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
        this.data = data;
        this.endereco = endereco;
        this.preco = preco;
    }
    
    public Ingresso(int codigo){
        this.codigo = codigo;
    }
    
    public Ingresso(int codigo, int quantidade){
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public String getHorarioTermino() {
        return horarioTermino;
    }

    public String getData() {
        return data;
    }

    public String getEndereco() {
        return endereco;
    }

    public double getPreco() {
        return preco;
    }
    
    /**
     *
     * @return Retorna o um toString formatado com todos os atributos de Ingresso
     * concatenado em uma única String de saída
     */
    @Override
    public String toString(){
        return  "Codigo: " + getCodigo() + "\n" +
                "Nome do Evento: " + getNome() + "\n" +
                "Quantidade de ingressos: " + getQuantidade() + "\n" +
                "Horario de inicio do evento: " + getHorarioInicio() + "\n" +
                "Horario de termino do evento: " + getHorarioTermino() + "\n" +
                "Data do evento: " + getData() + "\n" +
                "Endereco do evento: " + getEndereco() +  "\n" +
                "Preco do evento: " + getPreco() + "\n";
    }
}

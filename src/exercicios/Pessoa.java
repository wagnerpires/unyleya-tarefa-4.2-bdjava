package exercicios;

import javax.persistence.*;

@Entity
@Table(name="tb_pessoa")
public class Pessoa {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String nome;
    private Integer idade;
    private String endereço;

    public Pessoa(){

    }

    public Pessoa(Long id, String nome, Integer idade, String endereço) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.endereço = endereço;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

}

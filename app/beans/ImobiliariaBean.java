package beans;

public class ImobiliariaBean {
    public Long id;
    public String nome;
    public String endereco;

    public ImobiliariaBean(
            Long id,
            String nome,
            String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public ImobiliariaBean(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

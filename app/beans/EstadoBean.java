package beans;


public class EstadoBean {
    public Long id;
    public String nome;
    public String sigla;


    public EstadoBean() {
    }

    public EstadoBean(
            Long id,
            String nome,
            String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

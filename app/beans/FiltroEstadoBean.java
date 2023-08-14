package beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import models.Estado;
import beans.search.FiltroBaseBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FiltroEstadoBean extends FiltroBaseBean<Estado> {
    public Long id;
    public String nome;
    public String sigla;

    public FiltroEstadoBean() {
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

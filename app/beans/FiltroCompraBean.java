package beans;

import beans.search.FiltroBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import models.Compra;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FiltroCompraBean extends FiltroBaseBean<Compra> {
    public Long id;
    public String dataInicioFiltro;
    public String dataFimFiltro;

    public FiltroCompraBean() {
    }

    public String getDataInicioFiltro() {
        return dataInicioFiltro;
    }

    public void setDataInicioFiltro(String dataInicioFiltro) {
        this.dataInicioFiltro = dataInicioFiltro;
    }

    public String getDataFimFiltro() {
        return dataFimFiltro;
    }

    public void setDataFimFiltro(String dataFimFiltro) {
        this.dataFimFiltro = dataFimFiltro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

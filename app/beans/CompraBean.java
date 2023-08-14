package beans;


import utils.DateUtil;

import java.util.Date;

public class CompraBean {
    public Long id;
    public Date dataInicio;
    public Date dataFim;
    public String dataInicioFmt;
    public String dataFimFmt;
    public String nomePlano;
    public String nomeImobiliaria;

    public CompraBean(
            Long id,
            Date dataInicio,
            Date dataFim,
            String nomeImobiliaria) {
        this.id = id;
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
        this.dataInicioFmt = DateUtil.formataData(dataInicio);
        this.dataFimFmt = DateUtil.formataData(dataFim);
        this.nomeImobiliaria = nomeImobiliaria;
    }

    public CompraBean(Date dataInicio, Date dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getNomeImobiliaria() {
        return nomeImobiliaria;
    }

    public void setNomeImobiliaria(String nomeImobiliaria) {
        this.nomeImobiliaria = nomeImobiliaria;
    }

    public String getDataInicioFmt() {
        return dataInicioFmt;
    }

    public void setDataInicioFmt(String dataInicioFmt) {
        this.dataInicioFmt = dataInicioFmt;
    }

    public String getDataFimFmt() {
        return dataFimFmt;
    }

    public void setDataFimFmt(String dataFimFmt) {
        this.dataFimFmt = dataFimFmt;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

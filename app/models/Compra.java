package models;

import beans.CompraBean;
import beans.FiltroCompraBean;
import beans.search.SearchResult;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Compra extends BaseEntidade{

    @Id
    @SequenceGenerator(name = "seq_compra", sequenceName = "seq_compra", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_compra")
    public Long id;

    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    public Date dataInicio;

    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    public Date dataFim;

    @ManyToOne
    @JoinColumn(name = "plano_id")
    public Plano plano;


    @ManyToOne
    @JoinColumn(name = "imobiliaria_id")
    public Imobiliaria imobiliaria;

    public Compra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Imobiliaria getImobiliaria() {
        return imobiliaria;
    }

    public void setImobiliaria(Imobiliaria imobiliaria) {
        this.imobiliaria = imobiliaria;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public static SearchResult<CompraBean> buscarPor(FiltroCompraBean filtro) {
        String select = "SELECT new beans.CompraBean(" +
                "c.id," +
                "c.dataInicio," +
                "c.dataFim," +
                "i.nome) " +
                "FROM Compra c LEFT JOIN c.imobiliaria i ";
        StringBuffer where = new StringBuffer(" WHERE 1=1 ");

        if(filtro.id != null){
            where.append(  " AND c.id = :id ");
        }

        Query query = JPA.em().createQuery(select + where + " ORDER BY c.id DESC ");

        if (filtro.id != null){
            query.setParameter("id", filtro.id);
        }

        query.setFirstResult(filtro.start.intValue());
        query.setMaxResults(filtro.length.intValue());

        List<CompraBean> Compras = query.getResultList();

        String count = "SELECT COUNT(c) FROM Compra c";

        query = JPA.em().createQuery(count + where);

        if (filtro.id != null){
            query.setParameter("id", filtro.id);
        }

        Long total = (Long) query.getSingleResult();

        return new SearchResult<CompraBean>(filtro.draw,Compras, total, filtro);
    }

    public static Compra buscarPorId(Long id){
        Query query = JPA.em().createQuery("SELECT c FROM Compra c WHERE c.id = :id ");
        query.setParameter("id", id);
        return (Compra) query.getSingleResult();
    }
    public static Long buscarQuantidadeCompras(){
        Query query = JPA.em().createQuery("SELECT COUNT (c.id) FROM Compra c ");
        return (Long) query.getSingleResult();
    }
    public static List<Compra> buscarTodas(){
        Query query = JPA.em().createQuery("SELECT c FROM Compra c ");
        return query.getResultList();
    }
}


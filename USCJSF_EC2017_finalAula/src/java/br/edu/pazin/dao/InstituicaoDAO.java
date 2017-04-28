package br.edu.pazin.dao;

import br.edu.pazin.modelo.Instituicao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Seven
 */
public class InstituicaoDAO extends GenericDAO<Instituicao> {
/*
    public InstituicaoDAO() {
    }

    public Instituicao buscarInstituicao(String nome) {
        try {
            Criteria c = getSessao().createCriteria(Instituicao.class);
            Criterion filtro = Restrictions.eq("nome", nome);
            c.add(filtro);
            return (Instituicao) c.uniqueResult();
        } catch (Exception e) {
            System.out.println("InstituicaoDAO.buscarInstituicao()");
            return null;
        }
    }

    public List<Instituicao> getTodasInstituicoes() {
        try {
            Criteria c = getSessao().createCriteria(Instituicao.class);
            c.addOrder(Order.asc("nome"));
            return c.list();
        } catch (Exception ex) {
            System.out.println("InstituicaoDAO.getTodasInstituicoes()");
            return null;
        }
    }

    public Instituicao getPorId(Integer id) {
        try {
            return (Instituicao) getSessao().get(Instituicao.class, id);
        } catch (Exception e) {
            System.out.println("InstituicaoDAO.getPorId()");
            return null;
        }
    }

    public String salvar(Instituicao instituicao) {
        String msg = "";
        try {
            if (getPorId(instituicao.getId()) == null) {
                this.inserir(instituicao);
                msg = "Instituicao inserido com sucesso!";
            } else {
                this.alterar(instituicao);
                msg = "Instituicao atualizado com sucesso!";
            }
            return msg;
        } catch (Exception ex) {
            System.out.println("InstituicaoDAO.salvar()");
            return "Problemas ao salvar o instituição: " + ex.getMessage();
        }
    }*/
}

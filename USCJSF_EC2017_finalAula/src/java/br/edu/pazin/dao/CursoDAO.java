package br.edu.pazin.dao;

import br.edu.pazin.modelo.Curso;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Seven
 */
public class CursoDAO extends GenericDAO<Curso> {

    public CursoDAO() {
    }

    public Curso buscarCurso(String nome) {
        try {
            Criteria c = getSessao().createCriteria(Curso.class);
            Criterion filtro = Restrictions.eq("nome", nome);
            c.add(filtro);
            return (Curso) c.uniqueResult();
        } catch (Exception e) {
            System.out.println("CursoDAO.buscarCurso()");
            return null;
        }
    }

    public List<Curso> getTodosCursos() {
        try {
            Criteria c = getSessao().createCriteria(Curso.class);
            c.addOrder(Order.asc("nome"));
            return c.list();
        } catch (Exception ex) {
            System.out.println("CursoDAO.getTodosCursos()");
            return null;
        }
    }

    public Curso getPorId(Integer id) {
        try {
            return (Curso) getSessao().get(Curso.class, id);
        } catch (Exception e) {
            System.out.println("CursoDAO.getPorId()");
            return null;
        }
    }

    public String salvar(Curso curso) {
        String msg = "";
        try {
            if (getPorId(curso.getId()) == null) {
                this.inserir(curso);
                msg = "Curso inserido com sucesso!";
            } else {
                this.alterar(curso);
                msg = "Curso atualizado com sucesso!";
            }
            return msg;
        } catch (Exception ex) {
            System.out.println("CursoDAO.salvar()");
            return "Problemas ao salvar o curso: " + ex.getMessage();
        }
    }
}

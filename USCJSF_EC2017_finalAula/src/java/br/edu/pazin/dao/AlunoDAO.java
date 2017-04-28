package br.edu.pazin.dao;

import br.edu.pazin.modelo.Aluno;
import br.edu.pazin.modelo.AlunoCurso;
import br.edu.pazin.modelo.Curso;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Seven
 */
public class AlunoDAO extends GenericDAO<Aluno> {

    public AlunoDAO() {

    }

    public Aluno buscarAluno(String nome) {
        try {
            Criteria c = getSessao().createCriteria(Aluno.class);
            Criterion filtro = Restrictions.eq("nome", nome);
            c.add(filtro);
            return (Aluno) c.uniqueResult();
        } catch (Exception e) {
            System.out.println("AlunoDAO.buscarAluno()");
            return null;
        }
    }

    public List<Aluno> getTodosAlunos() {
        try {
            Criteria c = getSessao().createCriteria(Aluno.class);
            c.addOrder(Order.asc("nome"));
            return c.list();
        } catch (Exception ex) {
            System.out.println("AlunoDAO.getTodosAlunos()");
            return null;
        }
    }

    public Aluno getPorId(Integer id) {
        try {
            return (Aluno) getSessao().get(Aluno.class, id);
        } catch (Exception e) {
            System.out.println("AlunoDAO.getPorId()");
            return null;
        }
    }

    public String salvar(Aluno aluno) {
        String msg = "";
        try {
            if (getPorId(aluno.getId()) == null) {
                this.inserir(aluno);
                msg = "Aluno inserido com sucesso!";
            } else {
                this.alterar(aluno);
                msg = "Aluno atualizado com sucesso!";
            }
            return msg;
        } catch (Exception ex) {
            System.out.println("AlunoDAO.salvar()");
            return "Problemas ao salvar o aluno: " + ex.getMessage();
        }
    }

    public List<Curso> listarCursosAluno(Integer id) {
        try {
            Criteria c = getSessao().createCriteria(AlunoCurso.class);
            Criterion filtro = Restrictions.eq("aluno.id", id);
            c.add(filtro);
            List<Curso> listaCursosAluno = new ArrayList<Curso>();
            List<AlunoCurso> listaCurso = c.list();

            for (AlunoCurso objeto : listaCurso) {
                listaCursosAluno.add(objeto.getCurso());
            }
            return listaCursosAluno;
        } catch (Exception e) {
            System.out.println("listarCursosAluno: " + e.getMessage());
            return null;
        }
    }

    public List<AlunoCurso> listarPerfisAluno(Integer id) {
        try {
            Criteria c = getSessao().createCriteria(AlunoCurso.class);
            Criterion filtro = Restrictions.eq("aluno.id", id);
            c.add(filtro);
            return c.list();
        } catch (Exception e) {
            System.out.println("listaPerfisAluno: " + e.getMessage());
            return null;
        }
    }
    
    public void excluirCurso(AlunoCurso obj) {
        try{
            this.getSessao().delete(obj);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}

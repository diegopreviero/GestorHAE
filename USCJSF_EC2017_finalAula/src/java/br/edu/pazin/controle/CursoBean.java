package br.edu.pazin.controle;

import br.edu.pazin.dao.CursoDAO;
import br.edu.pazin.modelo.Curso;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Seven
 */
@ManagedBean
@ViewScoped
public class CursoBean implements Serializable{

    private Curso curso;
    private List<Curso> listaCursos;
    private FacesContext fc;
    private FacesMessage fm;
    private String msg;
    private int idTab;

    public CursoBean() {
        prepararTela();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getListaCursos() {
        if (listaCursos == null) {
            CursoDAO dao = new CursoDAO();
            listaCursos = dao.getTodosCursos();
        }

        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public FacesContext getFc() {
        return fc;
    }

    public void setFc(FacesContext fc) {
        this.fc = fc;
    }

    public FacesMessage getFm() {
        return fm;
    }

    public void setFm(FacesMessage fm) {
        this.fm = fm;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getIdTab() {
        return idTab;
    }

    public void setIdTab(int idTab) {
        this.idTab = idTab;
    }

    private void prepararTela() {
        curso = new Curso();
        listaCursos = null;
        fc = FacesContext.getCurrentInstance();
    }

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getId().equalsIgnoreCase("lista")) {
            idTab = 0;
        } else if (event.getTab().getId().equalsIgnoreCase("cadastro")) {
            idTab = 1;
        }
    }

    public void limparTela() {
        prepararTela();
    }

    public void alterarCurso(Curso curso) {
        this.curso = curso;
        CursoDAO dao = new CursoDAO();
        this.idTab = 1;
    }

    public void excluirCurso(Curso curso) {
        CursoDAO dao = new CursoDAO();
        dao.excluir(curso);
        listaCursos = null;
        fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão",
                "Curso excluido com sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public void salvarCurso() {
        CursoDAO dao = new CursoDAO();
        try {
            msg = dao.salvar(curso);
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso", msg);
            limparTela();

        } catch (Exception ex) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", ex.getMessage());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }

}

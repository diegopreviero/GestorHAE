package br.edu.pazin.controle;

import br.edu.pazin.dao.AlunoDAO;
import br.edu.pazin.dao.CursoDAO;
import br.edu.pazin.modelo.Aluno;
import br.edu.pazin.modelo.AlunoCurso;
import br.edu.pazin.modelo.Curso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Seven
 */
@ManagedBean
@ViewScoped
public class AlunoBean implements Serializable {

    private Aluno aluno;
    private Curso curso;
    private List<Curso> listaCursos;
    private List<Curso> listaCursosAluno;
    private List<Aluno> listaTodosAlunos;
    private FacesContext fc;
    private FacesMessage fm;
    private String msg;
    private int idTab;

    public AlunoBean() {
        prepararTela();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
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

    public List<Curso> getListaCursosAluno() {
        return listaCursosAluno;
    }

    public void setListaCursosAluno(List<Curso> listaCursosAluno) {
        this.listaCursosAluno = listaCursosAluno;
    }
    public List<Aluno> getListaTodosAlunos() {
        if (listaTodosAlunos == null) {
            AlunoDAO dao = new AlunoDAO();
            listaTodosAlunos = dao.getTodosAlunos();
        }
        return listaTodosAlunos;
    }

    public void setListaTodosAlunos(List<Aluno> listaTodosAlunos) {
        this.listaTodosAlunos = listaTodosAlunos;
    }

    public int getIdTab() {
        return idTab;
    }

    public void setIdTab(int idTab) {
        this.idTab = idTab;
    }

    private void prepararTela() {
        aluno = new Aluno();
        listaCursosAluno = null;
        listaCursos = null;
        listaTodosAlunos = null;
        curso = new Curso();
        fc = FacesContext.getCurrentInstance();
    }

    public void prepararCurso() {
        curso = new Curso();
    }

    public void salvarCurso(ActionEvent evento) {
        try {
            CursoDAO dao = new CursoDAO();
            msg = dao.salvar(curso);
            listaCursos = null;
            getListaCursos();
        } catch (Exception ex) {
            msg = ex.getMessage();
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", msg);
        }
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

    public void alterarAluno(Aluno aluno) {
        this.aluno = aluno;
        AlunoDAO dao = new AlunoDAO();
        listaCursosAluno = dao.listarCursosAluno(aluno.getId());
        this.idTab = 1;
    }

    public void excluirAluno(Aluno aluno) {
        AlunoDAO dao = new AlunoDAO();
        dao.excluir(aluno);
        listaTodosAlunos = null;
        fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão",
                "Aluno excluido com sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public void salvarAluno() {
        AlunoDAO dao = new AlunoDAO();

        try {
            if (listaCursosAluno.isEmpty()) {
                msg = "Você deve selecionar ao menos um curso.";
                fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", msg);
            } else {
                // aqui vem a regra para salvar
                // id == null representa usuário "novo"
                if(aluno.getId()==null){
                    aluno.setListaCursos(new ArrayList<>());
                } else {
                    aluno.setListaCursos(dao.listarPerfisAluno(aluno.getId()));
                }
                // tratar remoção
                List<AlunoCurso> auxRemocao = new ArrayList<>();
                // percorre para encontrar quem deve ser removido!
                for(AlunoCurso curso : aluno.getListaCursos()){
                    if(!listaCursosAluno.contains(curso.getCurso())){
                        auxRemocao.add(curso);
                    }
                }
                for(AlunoCurso cursoRemover :auxRemocao){
                    aluno.getListaCursos().remove(cursoRemover);
                    dao.excluirCurso(cursoRemover);
                }
                
                List<Curso> jaSalvo = 
                              dao.listarCursosAluno(aluno.getId());
                for(Curso tp : listaCursosAluno){
                    // percorrer o que está selecionado na tela
                    // comparar com o que está salvo no banco
                    if(!jaSalvo.contains(tp)){
                        AlunoCurso curso = new AlunoCurso();
                        curso.setAluno(aluno);
                        curso.setCurso(tp);
                        aluno.getListaCursos().add(curso);
                    }
                }
                msg = dao.salvar(aluno);
                fm = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Sucesso", msg);
                limparTela();                
            }
        } catch (Exception ex){
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erro!", ex.getMessage());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }

    }
}

package br.edu.pazin.controle;

//import br.edu.pazin.dao.TipoAlunoDAO;
import br.edu.pazin.dao.AlunoDAO;
//import br.edu.pazin.modelo.TipoAluno;
import br.edu.pazin.modelo.Aluno;
//import br.edu.pazin.modelo.AlunoPerfil;
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
public class AlunoBean implements Serializable{

    private Aluno aluno;
//    private TipoAluno tpAluno;
//    private List<TipoAluno> listaTodosPerfil;
//    private List<TipoAluno> listaPerfilAluno;
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

//    public TipoAluno getTpAluno() {
//        return tpAluno;
//    }
//
//    public void setTpAluno(TipoAluno tpAluno) {
//        this.tpAluno = tpAluno;
//    }
//
//    public List<TipoAluno> getListaTodosPerfil() {
//        if (listaTodosPerfil == null) {
//            TipoAlunoDAO dao = new TipoAlunoDAO();
//            listaTodosPerfil = dao.getTodosTipoAlunos();
//        }
//        return listaTodosPerfil;
//    }
//
//    public void setListaTodosPerfil(List<TipoAluno> listaTodosPerfil) {
//        this.listaTodosPerfil = listaTodosPerfil;
//    }
//
//    public List<TipoAluno> getListaPerfilAluno() {
//        return listaPerfilAluno;
//    }
//
//    public void setListaPerfilAluno(List<TipoAluno> listaPerfilAluno) {
//        this.listaPerfilAluno = listaPerfilAluno;
//    }

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
//        listaPerfilAluno = null;
//        listaTodosPerfil = null;
        listaTodosAlunos = null;
//        tpAluno = new TipoAluno();
        fc = FacesContext.getCurrentInstance();
    }

    public void prepararTipoAluno() {
//        tpAluno = new TipoAluno();
    }

    public void salvarTipoAluno(ActionEvent evento) {
        try {
//            TipoAlunoDAO dao = new TipoAlunoDAO();
//            msg = dao.salvar(tpAluno);
//            listaTodosPerfil = null;
//            getListaTodosPerfil();
        } catch (Exception ex) {
            msg = ex.getMessage();
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro", msg);
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
//        listaPerfilAluno
//                = dao.listarTiposPerfisAluno(user.getId());
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
       // AlunoDAO dao = new AlunoDAO();
        // verificar atribuição da senha ao aluno
//        if ((!senha1.isEmpty())
//                && (senha1.equals(senha2))) {
//            aluno.setSenha(senha1);
//        }
//
//        try {
//            if (listaPerfilAluno.isEmpty()) {
//                msg = "Você deve selecionar ao menos um perfil.";
//                fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
//                        "Aviso!", msg);
//            } else {
//                // aqui vem a regra para salvar
//                // id == null representa usuário "novo"
//                if(aluno.getId()==null){
//                    aluno.setPerfis(new ArrayList<>());
//                } else {
//                    aluno.setPerfis(dao.listarPerfisAluno(aluno.getId()));
//                }
//                // tratar remoção
//                List<AlunoPerfil> auxRemocao = new ArrayList<>();
//                // percorre para encontrar quem deve ser removido!
//                for(AlunoPerfil perfil : aluno.getPerfis()){
//                    if(!listaPerfilAluno.contains(perfil.getTipo())){
//                        auxRemocao.add(perfil);
//                    }
//                }
//                for(AlunoPerfil perfilRemover :auxRemocao){
//                    aluno.getPerfis().remove(perfilRemover);
//                    dao.excluirPerfil(perfilRemover);
//                }
//                
//                List<TipoAluno> jaSalvo = 
//                              dao.listarTiposPerfisAluno(aluno.getId());
//                for(TipoAluno tp : listaPerfilAluno){
//                    // percorrer o que está selecionado na tela
//                    // comparar com o que está salvo no banco
//                    if(!jaSalvo.contains(tp)){
//                        AlunoPerfil perfil = new AlunoPerfil();
//                        perfil.setAluno(aluno);
//                        perfil.setTipo(tp);
//                        aluno.getPerfis().add(perfil);
//                    }
//                }
//                msg = dao.salvar(aluno);
//                fm = new FacesMessage(FacesMessage.SEVERITY_INFO, 
//                        "Sucesso", msg);
//                limparTela();                
//            }
//        } catch (Exception ex){
//            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                        "Erro!", ex.getMessage());
//        } finally {
//            FacesContext.getCurrentInstance().addMessage(null, fm);
//        }

    }
}

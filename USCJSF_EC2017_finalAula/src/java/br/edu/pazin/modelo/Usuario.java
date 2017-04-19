/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pazin.modelo;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author apazi
 */
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 60, nullable = false)
    private String nome;
    @Column(length = 100, nullable = false, unique = true)
    private String email; // usado para login
    @Column(length = 60, nullable = false)
    private String senha;
    private boolean statusAtivo;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<UsuarioPerfil> perfis;

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = encriptarSenha(senha);
    }

    public boolean isStatusAtivo() {
        return statusAtivo;
    }

    public void setStatusAtivo(boolean statusAtivo) {
        this.statusAtivo = statusAtivo;
    }

    public List<UsuarioPerfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<UsuarioPerfil> perfis) {
        this.perfis = perfis;
    }

    private String encriptarSenha(String senha) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");

            digest.update(senha.getBytes());
            return Base64.encode(digest.digest());
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    public String logar(Usuario userEncontrado) {
        if (userEncontrado == null) {
            return "Usuário não disponível na base de dados!";
        }
        if (userEncontrado.getSenha().equals(this.senha)) {
            return "OK";
        } else {
            return "Usuário ou Senha inválidos!";
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pazin.converter;

import br.edu.pazin.dao.TipoUsuarioDAO;
import br.edu.pazin.modelo.TipoUsuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author apazi
 */
@FacesConverter("AlunoCursoConverter")
public class AlunoCursoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.isEmpty())
            return null;
        Integer id = Integer.valueOf(string);
        TipoUsuarioDAO dao = new TipoUsuarioDAO();
        return (TipoUsuario) dao.getPorId(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        TipoUsuario objeto = (TipoUsuario) o;
        if(objeto == null || objeto.getId()==null)
            return null;
        return String.valueOf(objeto.getId());
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pazin.converter;

import br.edu.pazin.dao.CursoDAO;
import br.edu.pazin.modelo.Curso;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author apazi
 */
@FacesConverter("alunoCursoConverter")
public class AlunoCursoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.isEmpty())
            return null;
        Integer id = Integer.valueOf(string);
        CursoDAO dao = new CursoDAO();
        return (Curso) dao.getPorId(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Curso objeto = (Curso) o;
        if(objeto == null || objeto.getId()==null)
            return null;
        return String.valueOf(objeto.getId());
    }
    
}
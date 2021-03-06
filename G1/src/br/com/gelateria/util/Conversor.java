package br.com.gelateria.util;



import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;


import br.com.gelateria.dao.AlertaDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.persistence.AlertaDaoJpa;


@FacesConverter(value="converter" , forClass=Insumo.class)
public class Conversor implements Converter {
	
	 
	
	 public EntityManager getManager(){
	    	FacesContext fc =  FacesContext.getCurrentInstance();
	    	ExternalContext ec = fc.getExternalContext();
	    	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	    	return (EntityManager) request.getAttribute("EntityManager");		    	
	    }

	 public Object getAsObject(FacesContext context,
	            UIComponent component, String value) {
		 System.err.println("context"+"componente"+component+"value"+value);
	        Alerta retorno = new Alerta();
	        EntityManager manager = this.getManager();
	       
	            if (value != null) {
	                AlertaDao aDao = new AlertaDaoJpa(manager);
	                
	             retorno =  aDao.getById(Alerta.class, retorno.getCodigo().valueOf(value));
	             System.err.println(retorno.getCodigo()+"valor do value="+value);
	             return retorno;
	            }
	        
	            else{
	           System.err.println( "Erro="+retorno.getCodigo()+",valor do value="+value);
	        
            return null;
	            }
	    }
	 

	    @Override
	    public String getAsString(FacesContext context,UIComponent component, Object value) {
	    	System.err.println("metodo= getAsString, context"+"componente"+component+"value"+value);
	    	if (value != null) {
	        	try{
	        	Alerta alerta = (Alerta) value;
	            return alerta.getCodigo().toString();
	        	}catch(Exception e){
	        	System.err.println(e.getMessage()+"Deu erro no getAsStrign");
	        	
	        }
	        	  
	        }
	        return null;
	    }
}



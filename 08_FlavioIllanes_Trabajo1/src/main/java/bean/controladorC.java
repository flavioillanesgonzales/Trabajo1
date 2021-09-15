package bean;

import dao.FacturadorImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Facturador;

@Named(value = "controlador")
@SessionScoped
public class controladorC implements Serializable {

    private Facturador fac;
    private FacturadorImpl dao;
    private boolean valor1;
    private boolean valor2;

    public controladorC() {
        fac = new Facturador();
        dao = new FacturadorImpl();
    }

    public void mostrarMensaje1() {
        try {
        boolean verdadero = true;
        boolean falso = false;
        String mensajeSaliente = "";
        if (valor1 == verdadero) {
            System.out.println(fac.getCODUBI());
            mensajeSaliente = "Checado: ";
            valor1 = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensajeSaliente));
        } else if (valor1 == falso) {
            valor1 = true;
            mensajeSaliente = "Deschecado: ";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensajeSaliente));
        }
        } catch (Exception e) {
            System.out.println("Error en mostra1 " + e.getMessage());
        }
    }
    
    
    public void mostrarMensaje2() {
        boolean verdadero = true;
        boolean falso = false;
        String mensajeSaliente = "";
        if (valor2 == verdadero) {
            valor2 = false;
            mensajeSaliente = "Checado";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensajeSaliente));
        } else if (valor2 == falso) {
            valor2 = true;
            mensajeSaliente = "Deschecado";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensajeSaliente));
        }
    }

    public void limpiar() {
        System.out.println("Ya se limpio");
        fac = new Facturador();
    }

    public void remplazarDireccion() {
        try {
            System.out.println(fac.getCODUBI());
            String distrito = fac.getCODUBI();
            distrito = dao.enviarUbigeo(distrito);
            System.out.println(fac.getDIRFAC());
            if (fac.getDIRFAC().contains(distrito)) {
                fac.setDIRFAC(fac.getDIRFAC().replaceAll(distrito, ""));
                System.out.println("a " + fac.getDIRFAC());
            }
            System.out.println("Esta es ahora dirrecion: " + fac.getDIRFAC());
        } catch (Exception e) {
            System.out.println("Error en remplazar dirección" + e.getMessage());
        }
    }

    public List<String> completeTextUbigeo(String query) throws SQLException, Exception {
            FacturadorImpl daoUbi = new FacturadorImpl();
            return daoUbi.autocompleteUbigeo(query);
    }

    public Facturador getFac() {
        return fac;
    }

    public void setFac(Facturador fac) {
        this.fac = fac;
    }

    public FacturadorImpl getDao() {
        return dao;
    }

    public void setDao(FacturadorImpl dao) {
        this.dao = dao;
    }

    public boolean isValor1() {
        return valor1;
    }

    public void setValor1(boolean valor1) {
        this.valor1 = valor1;
    }

    public boolean isValor2() {
        return valor2;
    }

    public void setValor2(boolean valor2) {
        this.valor2 = valor2;
    }

}

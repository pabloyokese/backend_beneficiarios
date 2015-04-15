package bo.sigep.curso.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "all")
public class BeneficiariosVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int registro;
    private int idBeneficiario;
    private String nombre;
    private String apellido;

    public BeneficiariosVo() {
        super();
    }

    public BeneficiariosVo(int idBeneficiario, String nombre, String apellido) {
        super();
        this.idBeneficiario = idBeneficiario;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(int idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

}

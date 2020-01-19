/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CHELLI BONITA
 */
@Entity
@Table(name = "estadopago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadopago.findAll", query = "SELECT e FROM Estadopago e")
    , @NamedQuery(name = "Estadopago.findByIdEstadoPago", query = "SELECT e FROM Estadopago e WHERE e.idEstadoPago = :idEstadoPago")
    , @NamedQuery(name = "Estadopago.findByNombre", query = "SELECT e FROM Estadopago e WHERE e.nombre = :nombre")})
public class Estadopago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstadoPago")
    private Integer idEstadoPago;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idEstadoPago")
    private List<Pagos> pagosList;

    public Estadopago() {
    }

    public Estadopago(Integer idEstadoPago) {
        this.idEstadoPago = idEstadoPago;
    }

    public Estadopago(Integer idEstadoPago, String nombre) {
        this.idEstadoPago = idEstadoPago;
        this.nombre = nombre;
    }

    public Integer getIdEstadoPago() {
        return idEstadoPago;
    }

    public void setIdEstadoPago(Integer idEstadoPago) {
        this.idEstadoPago = idEstadoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoPago != null ? idEstadoPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadopago)) {
            return false;
        }
        Estadopago other = (Estadopago) object;
        if ((this.idEstadoPago == null && other.idEstadoPago != null) || (this.idEstadoPago != null && !this.idEstadoPago.equals(other.idEstadoPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Estadopago[ idEstadoPago=" + idEstadoPago + " ]";
    }
    
}

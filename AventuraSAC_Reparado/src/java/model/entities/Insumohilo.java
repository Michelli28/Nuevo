/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CHELLI BONITA
 */
@Entity
@Table(name = "insumohilo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insumohilo.findAll", query = "SELECT i FROM Insumohilo i")
    , @NamedQuery(name = "Insumohilo.findByIdInsumo", query = "SELECT i FROM Insumohilo i WHERE i.idInsumo = :idInsumo")
    , @NamedQuery(name = "Insumohilo.findByDetalle", query = "SELECT i FROM Insumohilo i WHERE i.detalle = :detalle")})
public class Insumohilo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInsumo")
    private Integer idInsumo;
    @Basic(optional = false)
    @Column(name = "Detalle")
    private String detalle;

    public Insumohilo() {
    }

    public Insumohilo(Integer idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Insumohilo(Integer idInsumo, String detalle) {
        this.idInsumo = idInsumo;
        this.detalle = detalle;
    }

    public Integer getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Integer idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInsumo != null ? idInsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insumohilo)) {
            return false;
        }
        Insumohilo other = (Insumohilo) object;
        if ((this.idInsumo == null && other.idInsumo != null) || (this.idInsumo != null && !this.idInsumo.equals(other.idInsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Insumohilo[ idInsumo=" + idInsumo + " ]";
    }
    
}

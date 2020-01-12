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
@Table(name = "tipomodelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipomodelo.findAll", query = "SELECT t FROM Tipomodelo t")
    , @NamedQuery(name = "Tipomodelo.findByIdTipoModelo", query = "SELECT t FROM Tipomodelo t WHERE t.idTipoModelo = :idTipoModelo")
    , @NamedQuery(name = "Tipomodelo.findByNombre", query = "SELECT t FROM Tipomodelo t WHERE t.nombre = :nombre")})
public class Tipomodelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoModelo")
    private Integer idTipoModelo;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(mappedBy = "idTipoModelo")
    private List<Fichatecnica> fichatecnicaList;

    public Tipomodelo() {
    }

    public Tipomodelo(Integer idTipoModelo) {
        this.idTipoModelo = idTipoModelo;
    }

    public Tipomodelo(Integer idTipoModelo, String nombre) {
        this.idTipoModelo = idTipoModelo;
        this.nombre = nombre;
    }

    public Integer getIdTipoModelo() {
        return idTipoModelo;
    }

    public void setIdTipoModelo(Integer idTipoModelo) {
        this.idTipoModelo = idTipoModelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Fichatecnica> getFichatecnicaList() {
        return fichatecnicaList;
    }

    public void setFichatecnicaList(List<Fichatecnica> fichatecnicaList) {
        this.fichatecnicaList = fichatecnicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoModelo != null ? idTipoModelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomodelo)) {
            return false;
        }
        Tipomodelo other = (Tipomodelo) object;
        if ((this.idTipoModelo == null && other.idTipoModelo != null) || (this.idTipoModelo != null && !this.idTipoModelo.equals(other.idTipoModelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Tipomodelo[ idTipoModelo=" + idTipoModelo + " ]";
    }
    
}

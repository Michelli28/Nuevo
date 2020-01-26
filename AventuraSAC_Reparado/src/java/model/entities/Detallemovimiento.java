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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CHELLI BONITA
 */
@Entity
@Table(name = "detallemovimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallemovimiento.findAll", query = "SELECT d FROM Detallemovimiento d")
    , @NamedQuery(name = "Detallemovimiento.findByCantidad", query = "SELECT d FROM Detallemovimiento d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "Detallemovimiento.findByDescripcion", query = "SELECT d FROM Detallemovimiento d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "Detallemovimiento.findByIdDetalle", query = "SELECT d FROM Detallemovimiento d WHERE d.idDetalle = :idDetalle")})
public class Detallemovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalle")
    private Integer idDetalle;
    @JoinColumn(name = "idMovimiento", referencedColumnName = "idMovimiento")
    @ManyToOne
    private Movimientoalmacen idMovimiento;

    public Detallemovimiento() {
    }

    public Detallemovimiento(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Detallemovimiento(Integer idDetalle, int cantidad, String descripcion) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Movimientoalmacen getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Movimientoalmacen idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallemovimiento)) {
            return false;
        }
        Detallemovimiento other = (Detallemovimiento) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Detallemovimiento[ idDetalle=" + idDetalle + " ]";
    }
    
}

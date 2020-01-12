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
@Table(name = "guiaremision_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GuiaremisionDetalle.findAll", query = "SELECT g FROM GuiaremisionDetalle g")
    , @NamedQuery(name = "GuiaremisionDetalle.findByIdDetalleGuiaRemision", query = "SELECT g FROM GuiaremisionDetalle g WHERE g.idDetalleGuiaRemision = :idDetalleGuiaRemision")
    , @NamedQuery(name = "GuiaremisionDetalle.findByCantidad", query = "SELECT g FROM GuiaremisionDetalle g WHERE g.cantidad = :cantidad")
    , @NamedQuery(name = "GuiaremisionDetalle.findByDescripcion", query = "SELECT g FROM GuiaremisionDetalle g WHERE g.descripcion = :descripcion")})
public class GuiaremisionDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalleGuiaRemision")
    private Integer idDetalleGuiaRemision;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "Descripcion")
    private String descripcion;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    @ManyToOne
    private Pedido idPedido;
    @JoinColumn(name = "idGuiaRemision", referencedColumnName = "idGuiaRemision")
    @ManyToOne
    private Guiaremision idGuiaRemision;

    public GuiaremisionDetalle() {
    }

    public GuiaremisionDetalle(Integer idDetalleGuiaRemision) {
        this.idDetalleGuiaRemision = idDetalleGuiaRemision;
    }

    public Integer getIdDetalleGuiaRemision() {
        return idDetalleGuiaRemision;
    }

    public void setIdDetalleGuiaRemision(Integer idDetalleGuiaRemision) {
        this.idDetalleGuiaRemision = idDetalleGuiaRemision;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public Guiaremision getIdGuiaRemision() {
        return idGuiaRemision;
    }

    public void setIdGuiaRemision(Guiaremision idGuiaRemision) {
        this.idGuiaRemision = idGuiaRemision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleGuiaRemision != null ? idDetalleGuiaRemision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GuiaremisionDetalle)) {
            return false;
        }
        GuiaremisionDetalle other = (GuiaremisionDetalle) object;
        if ((this.idDetalleGuiaRemision == null && other.idDetalleGuiaRemision != null) || (this.idDetalleGuiaRemision != null && !this.idDetalleGuiaRemision.equals(other.idDetalleGuiaRemision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.GuiaremisionDetalle[ idDetalleGuiaRemision=" + idDetalleGuiaRemision + " ]";
    }
    
}

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
@Table(name = "pagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p")
    , @NamedQuery(name = "Pagos.findByNumeroOperacion", query = "SELECT p FROM Pagos p WHERE p.numeroOperacion = :numeroOperacion")
    , @NamedQuery(name = "Pagos.findByFecha", query = "SELECT p FROM Pagos p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Pagos.findByMonto", query = "SELECT p FROM Pagos p WHERE p.monto = :monto")
    , @NamedQuery(name = "Pagos.findByBanco", query = "SELECT p FROM Pagos p WHERE p.banco = :banco")})
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NumeroOperacion")
    private String numeroOperacion;
    @Column(name = "Fecha")
    private String fecha;
    @Basic(optional = false)
    @Column(name = "Monto")
    private double monto;
    @Column(name = "Banco")
    private String banco;
    @JoinColumn(name = "idEstadoPago", referencedColumnName = "idEstadoPago")
    @ManyToOne
    private Estadopago idEstadoPago;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    @ManyToOne
    private Pedido idPedido;

    public Pagos() {
    }

    public Pagos(String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public Pagos(String numeroOperacion, double monto) {
        this.numeroOperacion = numeroOperacion;
        this.monto = monto;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Estadopago getIdEstadoPago() {
        return idEstadoPago;
    }

    public void setIdEstadoPago(Estadopago idEstadoPago) {
        this.idEstadoPago = idEstadoPago;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroOperacion != null ? numeroOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.numeroOperacion == null && other.numeroOperacion != null) || (this.numeroOperacion != null && !this.numeroOperacion.equals(other.numeroOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Pagos[ numeroOperacion=" + numeroOperacion + " ]";
    }
    
}

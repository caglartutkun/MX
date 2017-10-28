/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Caglar
 */
@Entity
@Table(name = "directions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Directions.findAll", query = "SELECT d FROM Directions d")
    , @NamedQuery(name = "Directions.findById", query = "SELECT d FROM Directions d WHERE d.id = :id")
    , @NamedQuery(name = "Directions.findByDirectionID", query = "SELECT d FROM Directions d WHERE d.directionID = :directionID")
    , @NamedQuery(name = "Directions.findByStepOrder", query = "SELECT d FROM Directions d WHERE d.stepOrder = :stepOrder")})
public class Directions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DirectionID")
    private int directionID;
    @Basic(optional = false)
    @Column(name = "StepOrder")
    private int stepOrder;
    @Basic(optional = false)
    @Lob
    @Column(name = "Direction")
    private String direction;

    public Directions() {
    }

    public Directions(Integer id) {
        this.id = id;
    }

    public Directions(Integer id, int directionID, int stepOrder, String direction) {
        this.id = id;
        this.directionID = directionID;
        this.stepOrder = stepOrder;
        this.direction = direction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDirectionID() {
        return directionID;
    }

    public void setDirectionID(int directionID) {
        this.directionID = directionID;
    }

    public int getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(int stepOrder) {
        this.stepOrder = stepOrder;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Directions)) {
            return false;
        }
        Directions other = (Directions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Directions[ id=" + id + " ]";
    }
    
}

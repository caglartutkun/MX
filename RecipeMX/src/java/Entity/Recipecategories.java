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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Caglar
 */
@Entity
@Table(name = "recipecategories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipecategories.findAll", query = "SELECT r FROM Recipecategories r")
    , @NamedQuery(name = "Recipecategories.findById", query = "SELECT r FROM Recipecategories r WHERE r.id = :id")
    , @NamedQuery(name = "Recipecategories.findByRecipeID", query = "SELECT r FROM Recipecategories r WHERE r.recipeID = :recipeID")
    , @NamedQuery(name = "Recipecategories.findByCategoryID", query = "SELECT r FROM Recipecategories r WHERE r.categoryID = :categoryID")})
public class Recipecategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "RecipeID")
    private int recipeID;
    @Basic(optional = false)
    @Column(name = "CategoryID")
    private int categoryID;

    public Recipecategories() {
    }

    public Recipecategories(Integer id) {
        this.id = id;
    }

    public Recipecategories(Integer id, int recipeID, int categoryID) {
        this.id = id;
        this.recipeID = recipeID;
        this.categoryID = categoryID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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
        if (!(object instanceof Recipecategories)) {
            return false;
        }
        Recipecategories other = (Recipecategories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Recipecategories[ id=" + id + " ]";
    }
    
}

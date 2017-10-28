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
@Table(name = "recipeingredients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipeingredients.findAll", query = "SELECT r FROM Recipeingredients r")
    , @NamedQuery(name = "Recipeingredients.findById", query = "SELECT r FROM Recipeingredients r WHERE r.id = :id")
    , @NamedQuery(name = "Recipeingredients.findByRecipeID", query = "SELECT r FROM Recipeingredients r WHERE r.recipeID = :recipeID")
    , @NamedQuery(name = "Recipeingredients.findByIngredientID", query = "SELECT r FROM Recipeingredients r WHERE r.ingredientID = :ingredientID")
    , @NamedQuery(name = "Recipeingredients.findByTitle", query = "SELECT r FROM Recipeingredients r WHERE r.title = :title")})
public class Recipeingredients implements Serializable {

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
    @Column(name = "IngredientID")
    private int ingredientID;
    @Basic(optional = false)
    @Column(name = "Title")
    private String title;

    public Recipeingredients() {
    }

    public Recipeingredients(Integer id) {
        this.id = id;
    }

    public Recipeingredients(Integer id, int recipeID, int ingredientID, String title) {
        this.id = id;
        this.recipeID = recipeID;
        this.ingredientID = ingredientID;
        this.title = title;
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

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        if (!(object instanceof Recipeingredients)) {
            return false;
        }
        Recipeingredients other = (Recipeingredients) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Recipeingredients[ id=" + id + " ]";
    }
    
}

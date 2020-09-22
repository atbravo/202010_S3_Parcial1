/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.persistence;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class PokemonPersistence implements Serializable{

    @PersistenceContext(unitName = "pokemonPU")
    protected EntityManager em;

    public PokemonEntity create(PokemonEntity pokemon) {
        em.persist(pokemon);
        return pokemon;
    }

    public List<PokemonEntity> findAll() {
        TypedQuery query = em.createQuery("select u from PokemonEntity u", PokemonEntity.class);
        return query.getResultList();
    }

    public PokemonEntity findByName(String name) {
        TypedQuery query = em.createQuery("Select e From PokemonEntity e where e.nombre = :name", PokemonEntity.class);
        query = query.setParameter("name", name);
        List<PokemonEntity> sameName = query.getResultList();
        PokemonEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        return result;
    }
}

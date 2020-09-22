/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.ejb;

/**
 *
 * @author estudiante
 */
import co.edu.uniandes.csw.pokemon.entities.AtaqueEntity;
import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pokemon.persistence.PokemonPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PokemonLogic {

    @Inject
    private PokemonPersistence pokemonPersistence;

    public PokemonEntity createCalificacion(PokemonEntity pokemon, List<AtaqueEntity> ataques) throws BusinessLogicException {
        if (pokemon == null) {
            throw new BusinessLogicException("El pokemon es null");
        }
        if (pokemonPersistence.findByName(pokemon.getNombre()) != null) {
            throw new BusinessLogicException("El pokemon ya existe");
        }
        if (pokemon.getTipo() == (pokemon.getDebilidad())) {
            throw new BusinessLogicException("EL tipo es igual a debilidad");
        }
        if (ataques == null || ataques.isEmpty()) {
            throw new BusinessLogicException("Debe haber al menos un ataque");
        }
        for (AtaqueEntity ataque : ataques) {
            if (ataque == null || ataque.getDaño() < 0 || ataque.getDaño() > 100) {
                throw new BusinessLogicException("El ataque es null o no esta en el rango permitido");
            }
        }
        //pokemon.setAtaques(ataques);
        PokemonEntity nuevo = pokemonPersistence.create(pokemon);
        
        return pokemon;
    }
}

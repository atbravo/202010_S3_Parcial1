/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pokemon.resources;

import co.edu.uniandes.csw.pokemon.dtos.PokemonDTO;
import co.edu.uniandes.csw.pokemon.ejb.PokemonLogic;
import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ash Ketchum
 */

@Path("/pokemons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PokemonResource {
    
    
    private static final Logger LOGGER = Logger.getLogger(PokemonResource.class.getName());
    @Inject
    private PokemonLogic logic;
    
    
    @POST
    public PokemonDTO createPokemon(PokemonDTO pokemon) throws BusinessLogicException{  
        PokemonDTO nuevo = new PokemonDTO(logic.createCalificacion(pokemon.toEntity(), pokemon.getAtaques()));
        return nuevo;
   }
    
}

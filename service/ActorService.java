package com.Softito.MovieCatalog.service;

import com.Softito.MovieCatalog.model.Actor;
import com.Softito.MovieCatalog.model.Review;
import com.Softito.MovieCatalog.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }



    public Actor saveActor(Actor actor) {

        return actorRepository.save(actor);
    }



    public Actor getActorById(Long id) {
        return actorRepository.getById(id);
    }

    public Actor updateActor(Long id, Actor updatedActor) {

        Actor actor = getActorById(id);

        actor.setFullName(updatedActor.getFullName());
        actor.setActorRole(updatedActor.getActorRole());

        return actorRepository.save(actor);
    }



    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }

}

package org.acme.api;

import org.acme.domain.Person;
import org.acme.dto.PersonDTO;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    public List<Person> all() {
        return Person.listAll();
    }

    @POST
    @Transactional
    public Person save(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setAge(personDTO.getAge());
        person.persist();
        return person;
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Person update(@PathParam("id") Long personId, PersonDTO personDTO) {
        Optional<Person> personOpt = Person.findByIdOptional(personId);
        Person person = personOpt.orElseThrow(NotFoundException::new);

        person.setName(personDTO.getName());
        person.setAge(personDTO.getAge());
        person.persist();

        return person;
    }


}
package org.acme.api;

import org.acme.domain.Person;
import org.acme.dto.PersonDTO;
import org.acme.orch.PersonRoute;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @EndpointInject(PersonRoute.SAVE_PERSON_ROUTE)
    ProducerTemplate savePersonRoute;

    @GET
    public List<Person> all() {
        return Person.listAll();
    }

    @POST
    @Transactional
    public void save(PersonDTO personDTO) {
        savePersonRoute.sendBody(personDTO);
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
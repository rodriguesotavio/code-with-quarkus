package org.acme.orch;

import org.acme.domain.Person;
import org.acme.dto.PersonDTO;
import org.apache.camel.builder.RouteBuilder;

public class PersonRoute extends RouteBuilder {

    public static final String SAVE_PERSON_ROUTE = "direct:create-person";

    @Override
    public void configure() throws Exception {
        from(SAVE_PERSON_ROUTE)
                .process(exchange -> {
                    PersonDTO personDTO = exchange.getIn().getBody(PersonDTO.class);
                    Person person = new Person();
                    person.setName(personDTO.getName());
                    person.setAge(personDTO.getAge());
                    person.persist();
                })
                .log("Salvo no banco de dados");

    }
}

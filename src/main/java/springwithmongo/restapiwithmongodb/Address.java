package springwithmongo.restapiwithmongodb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address {
    private String country;
    private String city;
    private String  postCode;
}

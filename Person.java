/**
 * The Person class represents a person with a name and an address.
 * This class provides basic information about a person.
 * 
 * @author Jesus Ordaz
 */
public class Person {
    private String name;
    private String address;

    /**
     * Constructs a Person with the specified name and address.
     * 
     * @param nameIn the name of the person
     * @param addressIn the address of the person
     */
    public Person(String nameIn, String addressIn) {
        this.name = nameIn;
        this.address = addressIn;
    }

    public Person(){}

    /**
     * Gets the name of the person.
     * 
     * @return the name of the person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the address of the person.
     * 
     * @return the address of the person
     */
    public String getAddress() {
        return this.address;
    }

    public void setName(String nameIn) {
        this.name = nameIn;
    }

    public void setAddress(String addressIn) {
        this.address = addressIn;
    }
}


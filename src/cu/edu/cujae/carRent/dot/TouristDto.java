package cu.edu.cujae.carRent.dot;

public class TouristDto {
    private String name;
    private String lastName;
    private String idPassport;
    private int age;
    private char sex;
    private String telephoneNumber;

    public TouristDto( String name, String lastName, String idPassport, int age, char sex, String telephoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.idPassport = idPassport;
        this.age = age;
        this.sex = sex;
        this.telephoneNumber = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdPassport() {
        return idPassport;
    }

    public void setIdPassport(String idPassport) {
        this.idPassport = idPassport;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

}

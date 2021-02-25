package b1.school.person;

abstract class Person {

    String name;
    short age;
    String gender;

    Person(String name, short age, String gender) {
        this.name = name;

        if(age > 0) {
            this.age = age;
        } else {
            this.age = -1;
        }

        this.gender = gender;
    }

    /**
     * Default constructor if no parameters are given.
     */
    Person() {
        this.name = "\"undefined\"";
        this.age = -1;
        this.gender = "\"undefined\"";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        if(age > 0) {
            this.age = age;
        } else {
            this.age = -1;
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.age + ", " + this.gender;
    }
}

package b1.school.person;

import java.io.Serializable;

public abstract class Person implements Serializable, Comparable<Person> {
    String name;
    short age;
    String gender;

    Person(String name, short age, String gender) {
        this.name = name;

        if (age > 0) {
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
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAge() {
        return this.age;
    }

    public void setAge(short age) {
        if (age > 0) {
            this.age = age;
        } else {
            this.age = -1;
        }
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.age + ", " + this.gender;
    }

    @Override
    public int compareTo(Person o) {
        int nameCompareResult = this.getName().compareTo(o.getName());
        if (nameCompareResult != 0) {
            return nameCompareResult;
        }

        int ageCompareResult = Integer.compare(this.age, o.age);

        if (ageCompareResult != 0) {
            return ageCompareResult;
        }

        return this.gender.compareTo(o.getGender());
    }
}

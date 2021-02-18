package b1.school.person;

abstract class Person {

    String name;
    short age;
    String gender;
    short idNumber;

    Person(String name, short age, String gender, short idNumber) {
        this.name = name;

        if(age > 0) {
            this.age = age;
        } else {
            this.age = -1;
        }

        this.gender = gender;

        if(idNumber > 0) {
            this.idNumber = idNumber;
        } else {
            this.idNumber = -1;
        }
    }

    Person() {
        this.name = "\"undefined\"";
        this.age = -1;
        this.gender = "\"undefined\"";
        this.idNumber = -1;
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

    public short getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(short idNumber) {
        if(idNumber > 0) {
            this.idNumber = idNumber;
        } else {
            this.idNumber = -1;
        }
    }

    @Override
    public String toString() {
        return this.name + ", " + this.age + ", " + this.gender + ", " + this.idNumber;
    }
}

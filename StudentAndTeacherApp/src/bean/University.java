package bean;

public class University {
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name='" + name ;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package book.java8Lambdas.domain;

import java.util.Set;

/**
 * Created by ahernandez on 10/17/16.
 */
public class Artist {
    private String name;
    private Set<String> members;
    private String origin;

    public Artist() {
    }

    public Artist(String name, Set<String> members, String origin) {
        this.name = name;
        this.members = members;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void setMembers(Set<String> members) {
        this.members = members;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isFrom(String originTest) {
        if(origin.equals(originTest)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", members=" + members +
                ", origin='" + origin + '\'' +
                '}';
    }
}

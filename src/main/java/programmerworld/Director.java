package programmerworld;

import java.util.HashMap;
import java.util.Map;

public class Director {
    private Map<String, Paper> projects = new HashMap<>();

    public void addProject(String name, Paper paper) {
        projects.put(name, paper);
    }

    public void runProject(String name) {
        if (!projects.containsKey(name)) throw new RuntimeException("no project");
        deploy(name, projects.get(name).run());
    }

    public void deploy(String projectName, Program... programs) {
    }
}

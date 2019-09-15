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
        Paper paper = projects.get(name);
        if (!paper.isClient) {
            Programmer frontEnd = new Programmer(true), backEnd = new Programmer(false);
            paper.setFrontEndProgrammer(frontEnd);
            paper.setBackEndProgrammer(backEnd);
            Program client = frontEnd.makeProgram(paper);
            Program server = backEnd.makeProgram(paper);
            deploy(name, client, server);
        } else {
            Programmer frontEnd = new Programmer(true);
            paper.setProgrammer(frontEnd);
            deploy(name, frontEnd.makeProgram(paper));
        }
    }

    private void deploy(String projectName, Program... programs) {
    }
}

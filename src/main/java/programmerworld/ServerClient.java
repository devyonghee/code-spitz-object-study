package programmerworld;

public class ServerClient implements Paper {
    Server server = new Server("test");
    Language backEndLanguage = new Language("java");
    Language frontEndLanguage = new Language("kotlinJS");

    private Programmer backEndProgrammer;
    private Programmer frontEndProgrammer;

    public void setBackEndProgrammer(Programmer programmer) {
        this.backEndProgrammer = programmer;
    }

    public void setFrontEndProgrammer(Programmer programmer) {
        this.frontEndProgrammer = programmer;
    }
}

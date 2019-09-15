package programmerworld;

public class Paper {
    public Paper(boolean isClient) {
        this.isClient = isClient;
    }

    public final boolean isClient;

    Library library = new Library("vueJS");
    Language language = new Language("kotilinJS");
    Programmer programmer;
    Server server = new Server("test");
    Language backEndLanguage = new Language("java");
    Language frontEndLanguage = new Language("kotlinJS");
    private Programmer backEndProgrammer;
    private Programmer frontEndProgrammer;

    public void setProgrammer(Programmer programmer) {
        if (isClient) this.programmer = programmer;
    }

    public void setBackEndProgrammer(Programmer programmer) {
        if (!isClient) this.backEndProgrammer = programmer;
    }

    public void setFrontEndProgrammer(Programmer programmer) {
        if (!isClient) this.frontEndProgrammer = programmer;
    }

    public Library getFrontEndLibrary() {
        return isClient ? library : null;
    }

    public Server getServer() {
        return isClient ? null : server;
    }

    public Language getBackEndLanguage() {
        return isClient ? null : backEndLanguage;
    }

    public Language getFrontEndLanguage() {
        return isClient ? language : frontEndLanguage;
    }
}

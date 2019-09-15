package programmerworld;

public class Programmer {
    public Programmer(Boolean isFrontEnd) {
        this.isFrontEnd = isFrontEnd;
    }

    public final boolean isFrontEnd;

    private Language frontLanguage;
    private Library frontLibrary;
    private Server server;
    private Language backEndLanguage;

    public Program makeProgram(Paper paper) {
        if (isFrontEnd) {
            frontLanguage = paper.getFrontEndLanguage();
            frontLibrary = paper.getFrontEndLibrary();
        } else {
            this.server = paper.getServer();
            this.backEndLanguage = paper.getBackEndLanguage();
        }
        return isFrontEnd ? makeFrontEndProgram() : makeBackEndProgram();
    }

    private Program makeFrontEndProgram() {
        return new Program();
    }

    private Program makeBackEndProgram() {
        return new Program();
    }
}

package programmerworld;

public class FrontEnd implements Programmer {
    private Language language;
    private Library library;

    @Override
    public Program makeProgram(Paper paper) {
        paper.setData(this);
        return makeFrontEndProgram();
    }

    void setLanguage(Language language) {
        this.language = language;
    }

    void setLibrary(Library library) {
        this.library = library;
    }

    private Program makeFrontEndProgram() {
        return new Program();
    }
}

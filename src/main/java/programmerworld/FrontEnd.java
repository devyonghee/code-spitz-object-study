package programmerworld;

public abstract class FrontEnd<T extends Paper> extends Programmer<T> {
    protected Language language;
    protected Library library;

    @Override
    public Program makeProgram() {
        return new Program();
    }

}

package programmerworld;

public abstract class BackEnd<T extends Paper> extends Programmer<T> {
    protected Server server;
    protected Language language;

    @Override
    public Program makeProgram() {
        return new Program();
    }
}

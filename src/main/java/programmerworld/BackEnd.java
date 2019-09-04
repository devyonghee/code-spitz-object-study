package programmerworld;

public abstract class BackEnd<T extends Paper> extends Programmer<T> {
    private Server server;
    private Language language;

    @Override
    public Program makeProgram(Paper paper) {
        new Program();
    }

}

package lab.cmis.first.generator;

public class PRSGeneratorException extends Exception {
    public PRSGeneratorException(final String msg) {
        super(msg);
    }

    public PRSGeneratorException(final String msg, final Exception e) {
        super(msg, e);
    }
}

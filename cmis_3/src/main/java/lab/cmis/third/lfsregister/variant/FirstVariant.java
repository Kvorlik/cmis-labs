package lab.cmis.third.lfsregister.variant;

public class FirstVariant implements Variant {
    private static final byte INITIAL_BYTE = 5;

    @Override
    public byte F(final byte b) {
        int ui = Byte.toUnsignedInt(b);
        byte bit = (byte) (ui ^ (ui >> 5) ^ (ui >> 7));
        return (byte) ((ui >> 1) | (bit << 7));
    }

    @Override
    public byte getInitial() {
        return INITIAL_BYTE;
    }
}

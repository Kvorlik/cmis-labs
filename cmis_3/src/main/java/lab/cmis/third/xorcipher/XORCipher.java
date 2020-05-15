package lab.cmis.third.xorcipher;

import lab.cmis.third.lfsregister.variant.FirstVariant;
import lab.cmis.third.lfsregister.variant.Variant;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class XORCipher {
    private final Variant variant;

    public XORCipher() {
        variant = new FirstVariant();
    }

    public void encode(final String path) {
        try {
            FileSystem fs = FileSystems.getDefault();
            Path filePath = fs.getPath(path);

            if (!Files.isReadable(filePath)) {
                throw new IOException("Can't reach target file.", new Exception());
            }

            byte[] fileBytes = Files.readAllBytes(filePath);

            byte current = variant.F(variant.getInitial());
            for (int i = 0; i < fileBytes.length; i++) {
                fileBytes[i] = (byte) (fileBytes[i] ^ current);
                current = variant.F(current);
            }

            Files.write(filePath, fileBytes);
        } catch (IOException e) {
            e.getMessage();
            e.getStackTrace();
        }
    }
}

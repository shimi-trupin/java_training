package algorithms;


import designPatterns.Encryption;
import designPatterns.EncryptionDecorator;
import tools.EncryptionResult;
import lombok.Getter;

import java.io.File;
import java.util.List;

/**
 * Created by shimi on 06/07/2016.
 */
public class Xor extends EncryptionDecorator {

    @Getter private List<Byte> key;

    public Xor(Encryption encryption) {
        super(encryption);
    }

    @Override
    public EncryptionResult encrypt(byte[] data, List<Byte> key) {

        setStartTime(System.currentTimeMillis());
        notifyObserver("XOR encryption started.");
        File cypher;

        this.key = key;

//        System.out.println("The encryption key is: " + this.key);
        for (int i=0; i<data.length; i++)//encrypt
        {
            data[i] = (byte) (data[i] ^ this.key.get(0));
        }

        notifyObserver("XOR encryption ended.\nTime took: "
                + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

        return new EncryptionResult(data, this.key);
    }


    @Override
    public byte[] decrypt(byte[] data, List<Byte> key) {

        setStartTime(System.currentTimeMillis());
        notifyObserver("XOR decryption started.");
        for (int i=0; i<data.length; i++)//write to file with decrypted bytes
        {
            data[i] = (byte) (data[i] ^ key.get(0));
        }

        notifyObserver("XOR decryption ended.\nTime took: "
                + Long.toString(System.currentTimeMillis() - getStartTime()) + " milliseconds");

        return data;

    }

}

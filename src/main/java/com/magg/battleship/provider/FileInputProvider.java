package com.magg.battleship.provider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of {@link InputProvider} which provides data from a file.
 * 
 * @author Mohit Aggarwal
 */
public class FileInputProvider implements InputProvider {

    private String size;
    private String firstPlayerSequence;
    private String secondPlayerSequence;
    private List<List<String>> shipsData;

    public FileInputProvider(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Input file path missing");
        }

        try {
            List<String> inputs = Files.readAllLines(Paths.get(filePath));
            size = inputs.remove(0);

            secondPlayerSequence = inputs.remove(inputs.size() - 1);
            firstPlayerSequence = inputs.remove(inputs.size() - 1);

            shipsData = toChunks(inputs, 4);

        } catch (IOException e) {
            throw new IllegalStateException("Failed to read inputs. Can't proceed", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String battleShipSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String firstPlayerWeaponSequence() {
        return firstPlayerSequence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String secondPlayerWeaponSequence() {
        return secondPlayerSequence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<String>> shipsData() {
        return shipsData;
    }

    private List<List<String>> toChunks(List<String> list, int size) {
        List<List<String>> chunks = new ArrayList<>();
        int index = 1;
        List<String> chunk = new ArrayList<>();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String line = it.next();
            chunk.add(line);
            if (index % size == 0) {
                chunks.add(chunk);
                chunk = new ArrayList<>();
            }
            index++;
        }
        return chunks;
    }

}

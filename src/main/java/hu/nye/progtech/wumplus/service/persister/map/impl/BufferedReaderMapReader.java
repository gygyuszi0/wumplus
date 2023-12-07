package hu.nye.progtech.wumplus.service.persister.map.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.wumplus.service.exception.MapReadException;
import hu.nye.progtech.wumplus.service.persister.map.MapReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default mapReader, bufferedReder segíŧségével olvas.
 */
public class BufferedReaderMapReader implements MapReader {

    private final Logger logger = LoggerFactory.getLogger(BufferedReaderMapReader.class);
    private BufferedReader bufferedReader;

    public BufferedReaderMapReader(BufferedReader bufferedReader) {

        this.bufferedReader = bufferedReader;

        try {
            this.bufferedReader.mark(4096);
        } catch (IOException e) {
            logger.error("Failed to mark BufferedReader" + e.getMessage());
        }

    }

    @Override
    public List<String> readMap() throws MapReadException {
        String line;
        List<String> result = new ArrayList<>();


        try {
            bufferedReader.reset();
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            throw new MapReadException("Failed to read map" + e.getMessage());
        }

        return result;
    }

}

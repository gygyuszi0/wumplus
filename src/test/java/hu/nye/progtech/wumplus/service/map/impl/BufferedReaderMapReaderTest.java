package hu.nye.progtech.wumplus.service.map.impl;

import hu.nye.progtech.wumplus.service.exception.MapReadException;
import hu.nye.progtech.wumplus.service.persister.map.impl.BufferedReaderMapReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class BufferedReaderMapReaderTest {

    private static final String LINE_1 = "line1";
    private static final String LINE_2 = "line1";

    @Mock
    private BufferedReader bufferedReader;

    private BufferedReaderMapReader underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BufferedReaderMapReader(bufferedReader);
    }

    @Test
    public void testReadMapShouldReturnRowsReadFromBufferedReader() throws MapReadException, IOException {
        System.out.println("[TEST\t] : Rows read correctly from file");
        System.out.println("\t\t\tGIVEN\t:" + LINE_1 + LINE_2);
        // given
        given(bufferedReader.readLine()).willReturn(LINE_1, LINE_2, null);

        // when
        List<String> result = underTest.readMap();
        System.out.println("\t\t\tRESULT\t:" + result);

        // then
        assertEquals(List.of(LINE_1, LINE_2), result);
    }

    @Test
    public void testReadMapShouldThrowMapReadExceptionWhenBufferedReaderFailsToRead() throws IOException {
        System.out.println("[TEST\t] : Reader Throw exception");
        // given
        doThrow(IOException.class).when(bufferedReader).readLine();

        // when - then
        assertThrows(MapReadException.class, () -> {
            underTest.readMap();
        });
    }

}
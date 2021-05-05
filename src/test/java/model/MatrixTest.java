package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    private Matrix<Character> matrix;

    @BeforeEach
    void setUp() {
        matrix = new Matrix<Character>(5, 3, 'I');
    }

    @Test
    void getValue() {
        for (int y = 0; y < 3; ++y)
            for (int x = 0; x < 5; ++x)
                    assertEquals('I', matrix.getValue(x, y));
    }

    @Test
    void setValue() {
        matrix.setValue(2, 2, 'N');
        assertEquals('N', matrix.getValue(2,2));
    }

    @Test
    void getNumberRows() {
        assertEquals(3, matrix.getNumberRows());
    }

    @Test
    void getNumberCol() {
        assertEquals(5, matrix.getNumberCol());
    }
}
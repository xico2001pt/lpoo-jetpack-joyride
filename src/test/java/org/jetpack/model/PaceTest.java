package org.jetpack.model;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaceTest {

    @Test
    void updateNoTime() {
        Pace pace = new Pace(100);
        assertEquals(0, pace.update(0));
    }

    @Test
    void updateOneTime() {
        Pace pace = new Pace(100);
        assertEquals(1, pace.update(100));
    }

    @Property
    public void testUpdate(@ForAll @IntRange(min = 1, max = 1000) int period,
                           @ForAll @Positive @IntRange(min = 1, max = 1000) int elapsed,
                           @ForAll @Positive long counter) {
        Pace pace = new Pace(period);
        pace.setCounter(counter);

        long paces = pace.update(elapsed);
        assertEquals((elapsed+counter)/period, paces);
        assertEquals((counter + elapsed) - pace.getCounter(), paces*period);

    }
}
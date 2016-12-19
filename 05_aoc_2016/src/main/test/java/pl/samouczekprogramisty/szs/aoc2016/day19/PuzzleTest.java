package pl.samouczekprogramisty.szs.aoc2016.day19;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolvePuzzlePart1() {
        assertEquals(1834903, new ElvesRing(3014603).findLastThief());
    }

    @Test
    public void shouldSolvePuzzlePart2() {
        assertEquals(1420280, new ElvesRing(3014603).findLastThiefInFont());
    }
}

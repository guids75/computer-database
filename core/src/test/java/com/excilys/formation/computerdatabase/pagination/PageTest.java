package com.excilys.formation.computerdatabase.pagination;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PageTest {

    private Page pages;

    @Before
    public void setUp() throws Exception {
        pages = new Page(100);

    }

    @Test
    public void testGetNbElements() {
        assertTrue("Get nbElements", pages.getNbElements() == 100);
    }

    @Test
    public void testSetNbElements() {
        pages.setNbElements(110);
        assertTrue("Set nbElements", pages.getNbElements() == 110);
    }

    @Test
    public void testCalculateNbPages() {
        pages.setNbElementsByPage(10);
        pages.calculateNbPages(100);
        assertTrue("Calcuate nbPages", pages.getNbPages() == 10);
    }

    @Test
    public void testHasNext() {
        pages.setActualPage(5);
        pages.setNbPages(10);
        assertTrue("Has nextPage", pages.hasNext());
        pages.setActualPage(10);
        assertFalse("Has nextPage", pages.hasNext());
    }

    @Test
    public void testHasPrev() {
        pages.setActualPage(5);
        pages.setNbPages(10);
        assertTrue("Has prevPage", pages.hasPrev());
        pages.setActualPage(1);
        assertFalse("Has prevPage", pages.hasPrev());
    }

}

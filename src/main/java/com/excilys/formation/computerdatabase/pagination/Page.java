package com.excilys.formation.computerdatabase.pagination;

/**
 * @author GUIDS
 *
 * @param <T>
 *            : the object stored in the pages
 */
public class Page {

    private int nbElementsByPage; // number of elements in one page
    private int actualPage; // the page opened
    private int nbPages; // the total number of pages
    private int nbElements; // the total number of elements

    public Page() {
    }

    /**
     * 
     * @param nbElements
     *            : the total number of elements to display
     */
    public Page(int nbElements) {
        this.nbElementsByPage = 10;
        this.nbElements = nbElements;
        calculateNbPages(nbElements);
        actualPage = 1;
    }

    public int getNbElements() {
        return nbElements;
    }

    public void setNbElements(int nbElements) {
        this.nbElements = nbElements;
    }

    /**
     * Calculate the number of pages needed to display nbElements elements,
     * according to the number of elements per page.
     * 
     * @param nbElements
     *            : the total number of elements to display
     */
    public void calculateNbPages(int nbElements) {
        if (nbElements > 0) {
            nbPages = (int) Math.ceil(((float) nbElements) / nbElementsByPage);
        } else {
            if (nbElements == 0) {
                nbPages = 1;
            } else {
            throw new IllegalArgumentException("nbElements for pages is illegal : must be at least 1");
            }
        }
    }

    /**
     * @return the number of elements per page
     */
    public int getNbElementsByPage() {
        return nbElementsByPage;
    }

    /**
     * @param nbElementsByPage
     *            : the number of elements per page to set
     */
    public void setNbElementsByPage(int nbElementsByPage) {
        this.nbElementsByPage = nbElementsByPage;
    }

    /**
     * @return the current page
     */
    public int getActualPage() {
        return actualPage;
    }

    /**
     * @param actualPage
     *            : the actual page to set
     */
    public void setActualPage(int actualPage) {
        this.actualPage = actualPage;
    }

    /**
     * @return the number of pages
     */
    public int getNbPages() {
        return nbPages;
    }

    /**
     * @param nbPages
     *            : number of pages to set
     */
    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    /**
     * Check if the actual page has a following one.
     * 
     * @return a boolean corresponding to the existence of a following page
     *         (true if exists)
     */
    public boolean hasNext() {
        return (actualPage != nbPages);
    }

    /**
     * Check if the actual page has a previous one.
     * 
     * @return a boolean corresponding to the existence of a previous page (true
     *         if exists)
     */
    public boolean hasPrev() {
        return (actualPage != 1);
    }

}

package net.thoughtforge.model;

import java.util.Iterator;
import java.util.List;

/**
 * A page is a sublist of a list of objects. It also maintains information about the position of the
 * sublist in relative to the entire list. 
 *
 * @param <Element> the type of content within the page.
 */
public interface Page<Element> extends Iterable<Element> {
    
    /**
     * Returns the page content as List.
     * 
     * @return the page content as List.
     */
    List<Element> getContent();
    
    /**
     * Returns the number of the current page. Is always positive and less than
     * Page#getTotalPages().
     * 
     * @return the number of the current page.
     */
    int getPageNumber();
    
    /**
     * Returns the number of elements currently on this page. 
     * 
     * @return the number of elements currently on this page.
     */
    int getNumberOfElements();
    
    /**
     * Returns the size of the page. 
     * 
     * @return the size of the page.
     */
    int getPageSize();
    
    /**
     * Returns the total amount of elements. 
     * 
     * @return the total amount of elements.
     */
    long getTotalNumberOfElements();
    
    /**
     * Returns the total number of pages. 
     * 
     * @return the total number of pages.
     */
    int getTotalPages();
    
    /**
     * Returns if there is a next page.
     * 
     * @return <code>true</code> if there is a next page.
     */
    boolean hasNextPage();
    
    /**
     * Returns if there is a previous page.
     * 
     * @return <code>true</code> if there is a previous page.
     */
    boolean hasPreviousPage();
    
    /**
     * Returns whether the current page is the first one. 
     * 
     * @return <code>true</code> if the current page is the first page.
     */
    boolean isFirstPage();
    
    /**
     * Returns whether the current page is the last one. 
     * 
     * @return <code>true</code> if the current page is the last page.
     */
    boolean isLastPage();

    @Override
    Iterator<Element> iterator();
}

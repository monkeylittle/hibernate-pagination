package net.thoughtforge.model;

public class PageRequestImpl implements PageRequest {

    private int pageNumber;
    
    private int pageSize;
    
    /**
     * Creates a page request with the specified page number and size.
     * 
     * @param pageNumber the page to be returned.  Must be an integer greater that 0.
     * @param pageSize the number of items of that page.  Must be an integer greater that 0.
     */
    public PageRequestImpl(int pageNumber, int pageSize) {
        
        if (pageNumber == 0) {
            
            throw new IllegalArgumentException("pageNumber must be greater than 0");
        }
        
        if (pageSize == 0) {
            
            throw new IllegalArgumentException("pageSize must be greater than 0");
        }
        
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
    
    @Override
    public int getPageNumber() {

        return pageNumber;
    }

    @Override
    public int getPageSize() {

        return pageSize;
    }
}

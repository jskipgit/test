package com.ironyard.dto;

import com.ironyard.data.Movie;
import org.springframework.data.domain.Page;

/**
 * Created by jasonskipper on 11/2/16.
 */
public class IronPager {
    private int currentPage;
    private int previousPage;
    private int nextPage;
    private int totalPages;
    private int totalMovies;


    public IronPager(Integer page, Page aPageOfMovies) {
        this.previousPage = page - 1;
        this.nextPage = page + 1;

        // check to see if there really is a next page
        if(nextPage >= aPageOfMovies.getTotalPages()){
            nextPage = -1;
        }
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalMovies() {
        return totalMovies;
    }

    public void setTotalMovies(int totalMovies) {
        this.totalMovies = totalMovies;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isNext(){
        return nextPage > 0;
    }


    public boolean isPrevious(){
        return previousPage >= 0;
    }
}

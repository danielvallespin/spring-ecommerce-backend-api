package com.dani.spring.ecommerce_backend_api.pageable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

//Clase para poder paginar entidades filtrando por id y indicando offset y limit
public class PageableFilters implements Pageable {

    private final long offset;
    private final int limit;
    private final Sort sort;

    public PageableFilters(long offset, int limit, Sort sort) {
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return (int) (offset / limit);
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new PageableFilters(offset + limit, limit, sort);
    }

    @Override
    public Pageable previousOrFirst() {
        return offset > limit ? new PageableFilters(offset - limit, limit, sort) : first();
    }

    @Override
    public Pageable first() {
        return new PageableFilters(0, limit, sort);
    }

    @Override
    public boolean hasPrevious() {
        return offset > 0;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new PageableFilters(pageNumber * limit, limit, sort);
    }

}

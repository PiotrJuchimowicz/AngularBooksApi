package com.company.edu.dto;

import com.company.edu.util.SortOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class GetBooksInDto {

    private SimplePagination pagination;
    private SimpleDataSorting sorting;

    public static class SimplePagination {

        private int pageNumber = 0;
        private int pageSize = 9;

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class SimpleDataSorting {

        private String column = "title";
        private SortOrder order = SortOrder.ASC;

        public String[] getSortColumns() {
            List<String> availableColumns = Arrays.asList("isbn", "title", "numberOfPages", "releaseDate", "author", "borrower", "dictionaryCategories");
            List<String> columns = new ArrayList<>();

            if (availableColumns.contains(column)) {
                if("author".equals(column)) {
                    columns.add("author.name");
                    columns.add("author.surname");
                } else if ("borrower".equals(column)) {
                    columns.add("borrower.name");
                    columns.add("borrower.surname");
                } else if("dictionaryCategories".equals(column)) {
                    columns.add("dictionaryCategoriesCount");
                } else {
                    columns.add(column);
                }
            } else {
                return new String[]{"title"};
            }

            return  columns.toArray(new String[0]);
        }

        public SortOrder getOrder() {
            return order;
        }

        public String getColumn() {
            return column;
        }
    }
}

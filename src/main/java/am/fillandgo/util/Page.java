package am.fillandgo.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The Page class represents a page of items with pagination information.
 * @param <T> The type of items in the page.
 */
@Getter
@Setter
public class Page<T> {
    private final List<T> items;
    private final int page;
    private final int size;
    private final long totalItems;
    private final int totalPages;

    /**
     * The Page class represents a page of items with pagination information.
     * @param content The type of items in the page.
     */
    public Page(List<T> content, int page, int size, long totalItems) {
        this.items = content;
        this.page = page;
        this.size = size;
        this.totalItems = totalItems;
        this.totalPages = (int) Math.ceil((double) totalItems / size);
    }
}
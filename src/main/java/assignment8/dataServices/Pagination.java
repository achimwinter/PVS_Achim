package assignment8.dataServices;

import assignment8.data.Message;

import java.util.List;

public class Pagination {


    public static <C> List<C> page(final List<C> result, int offset, int size) {
        final int fromIndex = Math.max(0, offset);
        final int toIndex = Math.max(result.size(), fromIndex + Math.max(0, size));


    }
}

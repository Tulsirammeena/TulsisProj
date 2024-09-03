package com.cams.stock;

import java.util.Comparator;

public class OrderComparators {
    public static final Comparator<Order> BUY_ORDER_COMPARATOR = (o1, o2) -> {
        // Sort by price descending for buy orders
        return Double.compare(o2.getQuantity(), o1.getQuantity()); 
    };

    public static final Comparator<Order> SELL_ORDER_COMPARATOR = (o1, o2) -> {
        // Sort by price ascending for sell orders
        return Double.compare(o1.getQuantity(), o2.getQuantity());
    };
}
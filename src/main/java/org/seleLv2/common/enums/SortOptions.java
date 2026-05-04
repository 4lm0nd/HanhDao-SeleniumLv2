package org.seleLv2.common.enums;

public enum SortOptions {

    OPTION_DEFAULT("Default sorting"),
    OPTION_POPULARITY("Sort by popularity"),
    OPTION_RATING("Sort by average rating"),
    OPTION_BY_LATEST("Sort by latest"),
    OPTION_LOW_TO_HIGH("Sort by price: low to high"),
    OPTION_HIGH_TO_LOW("Sort by price: high to low");

    private final String option;

    SortOptions(String option) {
        this.option = option;
    }

    public String getSortOption() {
        return option;
    }
}

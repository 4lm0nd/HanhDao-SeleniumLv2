package org.seleLv2.common.enums;

public enum RatingReview {
    PERFECT("star-5"),

    GOOD("star-4"),

    AVERAGE("star-3"),

    NOT_BAD("star-2"),

    VERY_POOR("star-1");

    private final String rating;

    RatingReview (String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }
}

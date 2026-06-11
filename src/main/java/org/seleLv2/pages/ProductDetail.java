package org.seleLv2.pages;

import org.seleLv2.utils.LogUtils;

import static org.seleLv2.elements.Element.$;
import static org.seleLv2.elements.Elements.$$;

public class ProductDetail {

    String tabReviews = "//a[@id='tab_reviews']";
    String btnSubmit = "//input[@id='submit']";
    String txtReview = "//textarea[@id='comment']";
    String ratingList = "//a[@class='%s']";
    String latestReview = "//ol[@class='commentlist']/li[last()]//div[@class='description']";

    public void submitReview(String rating, String review) {
        String selectedRate = String.format(ratingList, rating);
        $(selectedRate).click();
        $(txtReview).type(review);
        $(btnSubmit).click();
    }

    public void openReviewTab() {
        $(tabReviews).scrollTo().click();
    }

    public String getLatestReview() {

        if ($(latestReview).exists()) {
              return $(latestReview).text();
        }
        LogUtils.info("Review Not Found");
        return null;
    }

    private static final String REVIEWS =
            "//ol[@class='commentlist']/li//div[@class='description']";

    public int countReview() {

        int totalComments =
                $$(REVIEWS).size();

        LogUtils.info("Total reviews: " + totalComments);

        return totalComments;
    }

   }

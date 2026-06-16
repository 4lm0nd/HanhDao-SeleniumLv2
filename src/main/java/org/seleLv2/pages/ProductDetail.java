package org.seleLv2.pages;

import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.Conditions;
import org.seleLv2.utils.LogUtils;

import static org.seleLv2.elements.Element.$;
import static org.seleLv2.elements.Elements.$$;
import static org.seleLv2.utils.WaitUtils.retryWait;

public class ProductDetail {

    String tabReviews = "//a[@id='tab_reviews']";
    String btnSubmit = "//input[@id='submit']";
    String txtReview = "//textarea[@id='comment']";
    String ratingList = "//a[@class='%s']";
    String latestReview = "//ol[@class='commentlist']/li[last()]//div[@class='description']";
    String reviewList = "//ol[@class='commentlist']/li//div[@class='description']";

    public void submitReview(String rating, String review) {
        String selectedRate = String.format(ratingList, rating);
        $(selectedRate).click();
        $(txtReview).type(review);
        $(btnSubmit).click();
    }

    public void openReviewTab() {
        $(tabReviews).shouldBe(Conditions.VISIBLE);
        $(tabReviews).shouldBe(Conditions.ENABLED);
        $(tabReviews).scrollTo().click();
    }

    public String getLatestReview() {

        boolean latestRow = retryWait(() -> $(latestReview).exists(),
                Constant.timeInSecond,
                Constant.timeInMilliSecond);

        if (latestRow) {
            String reviewContent = $(latestReview).text();
            LogUtils.info("LatestContent: " + reviewContent);
            return reviewContent;
        }
        LogUtils.info("Review Not Found");
        return null;
    }


    public int countReview() {
        int totalComments =
                $$(reviewList).size();
        LogUtils.info("Total reviews: " + totalComments);
        return totalComments;
    }

   }

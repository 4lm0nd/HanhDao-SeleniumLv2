package org.seleLv2.pages;

import org.openqa.selenium.Keys;
import org.seleLv2.utils.LogUtils;

import static com.codeborne.selenide.Selenide.*;
import static org.seleLv2.elements.Elements.$;

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

        if ($(latestReview).isVisible()) {
            $(latestReview).text();
            return $(latestReview).text();
        }
        LogUtils.info("Review Not Found");
        return null;
    }

    public int countReview(){
        int totalComments = $$x("//ol[@class='commentlist']/li//div[@class='description']").size();
        LogUtils.info(totalComments);
        return totalComments;
    }
}

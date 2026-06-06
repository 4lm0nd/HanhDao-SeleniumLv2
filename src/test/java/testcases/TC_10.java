package testcases;

import base.BaseTest;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.common.enums.RatingReview;
import org.seleLv2.data.AccountInfo;
import org.seleLv2.pages.*;
import org.seleLv2.utils.AssertUtils;
import org.seleLv2.utils.DataUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_10 extends BaseTest {
    private final AccountPage accountPage = new AccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ProductList productList = new ProductList();
    private final ProductDetail productDetail = new ProductDetail();
    String account = Constant.account;
    String password = Constant.password;
    String review = "Review Test_" + DataUtils.convertDateToString();

    @Test
    public void TC10(){
        headerPage.selectHeaderMenu(HeaderItems.MY_ACCOUNT.getItems());
        AccountInfo accountInfo = new AccountInfo(account, password);
        accountPage.login(accountInfo);
        headerPage.selectHeaderMenu(HeaderItems.TAB_SHOP.getItems());
        productList.openProductDetail();
        productDetail.openReviewTab();
        int count1 = productDetail.countReview();
        productDetail.submitReview(RatingReview.GOOD.getRating(),review);
        productDetail.openReviewTab();
        WaitUtils.waitForPageLoad(Constant.timeInSecond);
        AssertUtils.assertContains(productDetail::getLatestReview,review);
        int count2 = productDetail.countReview();
        Assert.assertEquals(count1 + 1, count2);

    }
}

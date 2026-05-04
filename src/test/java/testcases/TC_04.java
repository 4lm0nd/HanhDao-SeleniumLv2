package testcases;

import base.BaseTest;
import org.seleLv2.common.constant.Constant;
import org.seleLv2.common.enums.HeaderItems;
import org.seleLv2.common.enums.SortOptions;
import org.seleLv2.data.AccountInfo;
import org.seleLv2.pages.*;
import org.seleLv2.utils.LogUtils;
import org.seleLv2.utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_04 extends BaseTest {

    String account = Constant.account;
    String password = Constant.password;
    private final AccountPage accountPage = new AccountPage();
    private final HeaderPage headerPage = new HeaderPage();
    private final ProductList productList = new ProductList();

    @Test
    public void TC04() {
        LogUtils.info(" Login with valid credentials");
        headerPage.selectHeaderMenu(HeaderItems.MY_ACCOUNT.getItems());
        AccountInfo accountInfo = new AccountInfo(account, password);
        accountPage.login(accountInfo);
        LogUtils.info("Go to Shop page");
        headerPage.selectHeaderMenu(HeaderItems.TAB_SHOP.getItems());
        LogUtils.info("Sort items by price: low to high");
        productList.filterProducts(SortOptions.OPTION_LOW_TO_HIGH.getSortOption());
        WaitUtils.waitForPageLoad(Constant.timeout);
        Assert.assertTrue(ProductList.verifySortedASC());
        LogUtils.info("Sort items by price: high to low");
        productList.filterProducts(SortOptions.OPTION_HIGH_TO_LOW.getSortOption());
        WaitUtils.waitForPageLoad(Constant.timeout);
        Assert.assertTrue(ProductList.verifySortedDESC());
    }
}

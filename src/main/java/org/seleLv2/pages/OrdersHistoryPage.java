package org.seleLv2.pages;

import org.seleLv2.data.OrderInfo;
import org.seleLv2.elements.Tables;
import org.seleLv2.utils.AssertUtils;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class OrdersHistoryPage {

    public List<OrderInfo> getActualOrders() {
        List<String> orderNumbers = Tables.getColumnValuesByHeader("Order");
        List<String> dates = Tables.getColumnValuesByHeader("Date");
        List<String> totals = Tables.getColumnValuesByHeader("Total");
        List<OrderInfo> actual = new ArrayList<>();
        for (int i = 0; i < orderNumbers.size(); i++) {
            actual.add(new OrderInfo(
                    orderNumbers.get(i),
                    dates.get(i),
                    totals.get(i)
            ));
        }

        return actual;
    }

    public void verifyOrderInfo(List<OrderInfo> expected) {

        List<OrderInfo> actual = getActualOrders();

        Assert.assertEquals(actual.size(), expected.size(), "Order list size mismatch");
        for (int i = 0; i < expected.size(); i++) {
            OrderInfo exp = expected.get(expected.size() -1-i);
            OrderInfo act = actual.get(i);
            AssertUtils.assertContains(act::getOrderNumber,exp.getOrderNumber());
            AssertUtils.assertEquals(act::getDate, exp.getDate());
            AssertUtils.assertContains(act::getTotal, exp.getTotal());
        }
    }

}



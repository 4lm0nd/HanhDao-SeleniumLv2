package org.seleLv2.listeners;

import org.seleLv2.drivers.ConfigFactory;
import org.seleLv2.utils.LogUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int maxRetry = ConfigFactory.getInt("RETRY");

    @Override
    public boolean retry(ITestResult result) {

            if (count < maxRetry) {
                count++;
                LogUtils.info("Retry " + count + " for: " + result.getName());
                return true;
            }
            return false;
        }
    }


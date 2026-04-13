package org.seleLv2.listeners;


import org.seleLv2.utils.LogUtils;
import org.testng.annotations.ITestAnnotation;
import org.testng.IAnnotationTransformer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
        LogUtils.info("Transform: " + testMethod.getName());
    }
}

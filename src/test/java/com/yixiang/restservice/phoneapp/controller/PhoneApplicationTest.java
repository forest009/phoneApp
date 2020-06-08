package com.yixiang.restservice.phoneapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Arrays;

public class PhoneApplicationTest {

    @Test
    public void testGetPhone() {
        String mockPhoneNum = "1111116";
        int mockPageNum = 1;
        String[] mockRes = new String[]{"4","1111116","111111m","111111n","111111o"};
        PhoneController phoneController = new PhoneController();
        String[] actual = phoneController.getPhone(mockPhoneNum, mockPageNum);
        Assert.isTrue(Arrays.equals(mockRes, actual));
    }
}

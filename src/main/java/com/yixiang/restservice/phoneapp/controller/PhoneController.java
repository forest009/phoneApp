package com.yixiang.restservice.phoneapp.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class PhoneController {

    String[] keyMap = {"0","1","2abc","3def","4ghi","5jkl","6mno","7pqrs","8tuv","9wxyz"};
    int pageCapacity = 500;

    @RequestMapping(value = "/getPhone/{phoneNum}/{pageNum}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseBody
    public String[] getPhone(@PathVariable("phoneNum") String phoneNum, @PathVariable("pageNum") int pageNum) {

        List<String> res = new ArrayList<>();
        res.add(totalCount((phoneNum)));
        List<String> total = phoneCombinations(phoneNum);
        int totalSize = total.size();

        if (pageNum * pageCapacity > totalSize) {
            res.addAll(1, total.subList((pageNum-1) * pageCapacity, total.size()));
        } else {
            res.addAll(1, total.subList((pageNum-1) * pageCapacity, pageNum * pageCapacity));
        }

        return res.toArray(new String[0]);
    }

    public String totalCount(String phoneNum) {
        int res = 0;
        int len = phoneNum.length();
        int[] countArr = new int[]{0, 0, 0};
        for (int i = 0; i < len; i++) {
            if (phoneNum.charAt(i) == '0' || phoneNum.charAt(i) == '1') {
                countArr[0]++;
            } else if ( phoneNum.charAt(i) == '7' || phoneNum.charAt(i) == '9') {
                countArr[1]++;
            } else {
                countArr[2]++;
            }
        }
        res = (int)(Math.pow(5, countArr[1]) * Math.pow(4, countArr[2]));
        return Integer.toString(res);
    }

    public List<String> phoneCombinations(String phoneNum) {
        List<String> res = new ArrayList<>();
        combination("", phoneNum, 0, res);
        return res;
    }
    public void combination(String prefix, String phoneNum, int offSet, List<String> res){
        if (offSet >= phoneNum.length()) {
            res.add(prefix);
            return;
        }

        String letters = keyMap[(phoneNum.charAt(offSet) - '0')];

        for (int i = 0; i < letters.length(); i++) {
            combination(prefix + letters.charAt(i), phoneNum, offSet + 1, res);
        }
    }
}
/*
    0 - space
    1 -
    2 - a b c
    3 - d e f
    4 - g h i
    5 - j k l
    6 - m n o
    7 - p q r s
    8 - t u v
    9 - w x y z
 */
package cn.anx.test;

import org.junit.Test;


public class Leetcode {

    @Test
    public void maxArea() {
        int[] height = new int[]{5, 5, 1, 7, 1, 1, 5, 2, 7, 6};
        int left = 0;
        int right = height.length - 1;
        int sumSolid = 0;
        int sumAll = 0;
        int max = 0;
//        if(height.length==0)
//            return 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max)
                max = height[i];
        }
        for (int i :
                height) {
            sumSolid += i;
        }
        if (height[left] == max && height[right] == max)
            sumAll = (right - left + 1) * height[left];
        else {
            for (int i = 0; i <= right; i++) {
                if (height[i] > height[left]) {
                    sumAll = sumAll + (i - left) * height[left];
                    left = i;
                }
                if (i == right) {
                    sumAll = sumAll + height[left];
                }
                continue;
            }
            for (int j = right; j >= left; j--) {
                if (height[j] > height[right]) {
                    sumAll = sumAll + (right - j) * height[right];
                    right = j;
                }
                continue;
            }
            sumAll += (right - left) * height[left];
        }
        System.out.println(sumAll - sumSolid);
    }

    //leetcode 005
    public String longestPalindrome(String s) {
        int count = 1;
        int start = 0;
        int end = 0;
        char[] arr = s.toCharArray();
        int length = arr.length;
        if (length == 1)
            return s;
        if (length == 0)
            return "";
        for (int i = 0; i < length; i++) {
            int k = i;
            while (k < length - 1 && arr[i] == arr[k + 1]) {
                k++;
            }
            for (int j = 0; j <= i && j < length - k; j++) {
                if (arr[i - j] == arr[k + j]) {
                    if (count < 2 * j + k - i + 1) {
                        count = 2 * j + k - i + 1;
                        start = i - j;
                        end = k + j;
                    }
                } else {
                    break;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}

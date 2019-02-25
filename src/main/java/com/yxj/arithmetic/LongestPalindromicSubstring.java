package com.yxj.arithmetic;

/**
 * @author:yuxj
 * @descriptio 最长回文字符串
 * @create:2019-02-25 19:29
 */
public class LongestPalindromicSubstring {


    public static void main(String[] args) {
        System.out.println(longestPalindrome2("babad"));
    }


    public static String longestPalindrome(String s) {

        char[] bytes = s.toCharArray();
        int length = bytes.length;
        char[] bytes1 = new char[2 * length + 1];
        for (int i = 0; i < length; i++) {
            bytes1[2 * i] = '#';
            bytes1[2 * i + 1] = bytes[i];
        }
        bytes1[2 * length] = '#';
        int[] index = new int[2 * length + 1];
        int postion = 0;
        int maxRight = 0;

        for (int i = 0; i < 2 * length + 1; i++) {
            if (i == postion) {
                for (int j = 0, k = 0; j >= 0 && k < 2 * length + 1; j--, k++) {
                    if (bytes1[j] == bytes1[k]) {
                        index[i]++;
                    } else {
                        break;
                    }
                }
                if (index[i] > maxRight + 1) {
                    maxRight = index[i] + i - 1;
                    postion = i;
                }

            }
            if (i > postion && i < maxRight) {
                index[i] = index[2 * postion - i];
                int z = index[i];
                for (int j = i - z, k = i + z; j >= 0 && k < 2 * length + 1; j--, k++) {
                    if (bytes1[j] == bytes1[k]) {
                        index[i]++;
                    } else {
                        break;
                    }
                }
                if (index[i] > maxRight + 1) {
                    maxRight = index[i] + i - 1;
                    postion = i;
                }
            }
            if (i >= maxRight) {
                int z = index[i];
                for (int j = i - z, k = i + z; j >= 0 && k < 2 * length + 1; j--, k++) {
                    if (bytes1[j] == bytes1[k]) {
                        index[i]++;
                    } else {
                        break;
                    }
                }
                if (index[i] > maxRight + 1) {
                    maxRight = index[i] + i - 1;
                    postion = i;
                }
            }
        }
        int maxIndex = 0;
        int maxNum = 0;
        for (int i = 0; i < 2 * length + 1; i++) {
            if (index[i] > maxNum) {
                maxIndex = i;
                maxNum = index[i];
            }
        }

        char[] result = new char[maxNum-1];
        int a = 0;
        for (int i = maxIndex + 1 - maxNum; i < maxIndex + maxNum - 1; i++) {
            if((i-1)%2 == 0){
                result[a] = bytes1[i];
                a++;
            }
        }
        String b = new String(result);


        return b;
    }


    /**
     * 网速看的一个更快的方法
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; s.length()-i+1>(end-start)/2+1; i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                if (len % 2 == 1) {
                    start = i - (len - 1) / 2;
                    end = i + (len - 1) / 2;
                } else {
                    start = i - (len - 1) / 2;
                    end = i + (len - 1) / 2 + 1;
                }

            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int l = left, r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

}

package com.yxj.leetcode;

/**
 * @author:yuxj
 * @descriptio String to Integer (atoi)
 * @create:2019-03-29 17:02
 */
public class StringToInteger {


    public static void main(String[] args) throws Exception {
        String s = "{\n" +
                "  \"selection_indicator\": [{\n" +
                "    \"param_key\": \"saleDepa\",\n" +
                "    \"param_name\": \"销售部门\",\n" +
                "    \"param_sort\": 1\n" +
                "  }, {\n" +
                "    \"param_key\": \"supplier\",\n" +
                "    \"param_name\": \"供应商\",\n" +
                "    \"param_sort\": 2\n" +
                "  }, {\n" +
                "    \"param_key\": \"goodClass\",\n" +
                "    \"param_name\": \"商品类别\",\n" +
                "    \"param_sort\": 3\n" +
                "  }, {\n" +
                "    \"param_key\": \"goodBrand\",\n" +
                "    \"param_name\": \"商品品牌\",\n" +
                "    \"param_sort\": 4\n" +
                "  }, {\n" +
                "    \"param_key\": \"goodVariety\",\n" +
                "    \"param_name\": \"商品品种\",\n" +
                "    \"param_sort\": 5\n" +
                "  }, {\n" +
                "    \"param_key\": \"goodName\",\n" +
                "    \"param_name\": \"商品名称\",\n" +
                "    \"param_sort\": 6\n" +
                "  }, {\n" +
                "    \"param_key\": \"saleNum\",\n" +
                "    \"param_name\": \"销售总数量\",\n" +
                "    \"param_sort\": 7,\n" +
                "    \"param_rem\": \"销售总数量：指的是销售订单中，销售已经提货的商品数量；\"\n" +
                "  }, {\n" +
                "    \"param_key\": \"spotAdvance\",\n" +
                "    \"param_name\": \"现货预收\",\n" +
                "    \"param_sort\": 8,\n" +
                "    \"param_rem\": \"现货预收：以收费或者退费时间为标准，针对于库存商品的销售收费金额合计，退费负数表示。\"\n" +
                "  }, {\n" +
                "    \"param_key\": \"customizedAdvance\",\n" +
                "    \"param_name\": \"定做预收\",\n" +
                "    \"param_sort\": 9,\n" +
                "    \"param_rem\": \"定做预收：以收费或者退费时间为标准，针对于定做商品的销售收费金额合计，退费负数表示。\"\n" +
                "  }, {\n" +
                "    \"param_key\": \"totalAdvance\",\n" +
                "    \"param_name\": \"预收合计\",\n" +
                "    \"param_sort\": 10,\n" +
                "    \"param_rem\": \"现货预收+定做预收；\"\n" +
                "  }, {\n" +
                "    \"param_key\": \"spotRealSale\",\n" +
                "    \"param_name\": \"现货实售\",\n" +
                "    \"param_sort\": 11,\n" +
                "    \"param_rem\": \"现货实售：以销售已经提货为维度，针对于库存商品销售收费金额合计，退货/撤回负数表示。\"\n" +
                "  }, {\n" +
                "    \"param_key\": \"spotRealSaleNum\",\n" +
                "    \"param_name\": \"现货实售数量\",\n" +
                "    \"param_sort\": 12,\n" +
                "    \"param_rem\": \"现货实售数量：以销售已经提货为维度，针对于库存商品的销售数量；\"\n" +
                "  }, {\n" +
                "    \"param_key\": \"customizedRealSale\",\n" +
                "    \"param_name\": \"定做实售\",\n" +
                "    \"param_sort\": 13,\n" +
                "    \"param_rem\": \"定做实售：以销售已经提货为维度，针对于定做商品销售收费金额合计，退货/撤回负数表示。\"\n" +
                "  }, {\n" +
                "    \"param_key\": \"customizedRealSaleNum\",\n" +
                "    \"param_name\": \"定做实售数量\",\n" +
                "    \"param_sort\": 14,\n" +
                "    \"param_rem\": \"定做实售数量：以销售已经提货为维度，针对于定做商品的销售数量；\"\n" +
                "  }, {\n" +
                "    \"param_key\": \"totalRealSale\",\n" +
                "    \"param_name\": \"实售合计\",\n" +
                "    \"param_sort\": 15,\n" +
                "    \"param_rem\": \"实售合计：现货实售+定做实售；\"\n" +
                "  }, {\n" +
                "    \"param_key\": \"spotPrice\",\n" +
                "    \"param_name\": \"现货进价\",\n" +
                "    \"param_sort\": 16,\n" +
                "    \"param_rem\": \"现货进价：库存商品对应的进价单价；\"\n" +
                "  }, {\n" +
                "    \"param_key\": \"customizedPrice\",\n" +
                "    \"param_name\": \"定做进价\",\n" +
                "    \"param_sort\": 17,\n" +
                "    \"param_rem\": \"定做进价：定做商品对应的进价单价；\"\n" +
                "  }, {\n" +
                "    \"param_key\": \"totalPrice\",\n" +
                "    \"param_name\": \"进价合计\",\n" +
                "    \"param_sort\": 18,\n" +
                "    \"param_rem\": \"进价合计：现货进价+定做进价\"\n" +
                "  }, {\n" +
                "    \"param_key\": \"netPrice\",\n" +
                "    \"param_name\": \"进销差价\",\n" +
                "    \"param_sort\": 19,\n" +
                "    \"param_rem\": \"进销差价：实售合计-进价合计\"\n" +
                "  }],\n" +
                "  \"filter_condition\": [{\n" +
                "    \"param_key\": \"supplier_id\",\n" +
                "    \"param_name\": \"供应商\",\n" +
                "    \"param_sort\": 1\n" +
                "  }, {\n" +
                "    \"param_key\": \"clas_id\",\n" +
                "    \"param_name\": \"商品类别\",\n" +
                "    \"param_sort\": 2\n" +
                "  }, {\n" +
                "    \"param_key\": \"bran_id\",\n" +
                "    \"param_name\": \"商品品牌\",\n" +
                "    \"param_sort\": 3\n" +
                "  }, {\n" +
                "    \"param_key\": \"vari_id\",\n" +
                "    \"param_name\": \"商品品种\",\n" +
                "    \"param_sort\": 4\n" +
                "  }, {\n" +
                "    \"param_key\": \"depa_list\",\n" +
                "    \"param_name\": \"销售部门\",\n" +
                "    \"param_sort\": 5\n" +
                "  }, {\n" +
                "    \"param_key\": \"agen_id\",\n" +
                "    \"param_name\": \"运营机构\",\n" +
                "    \"param_sort\": 6\n" +
                "  }, {\n" +
                "    \"param_key\": \"date\",\n" +
                "    \"param_name\": \"退费日期\",\n" +
                "    \"param_sort\": 7\n" +
                "  }, {\n" +
                "    \"param_key\": \"channel\",\n" +
                "    \"param_name\": \"销售渠道\",\n" +
                "    \"param_sort\": 8\n" +
                "  }, {\n" +
                "    \"param_key\": \"is_stage\",\n" +
                "    \"param_name\": \"分期统计\",\n" +
                "    \"param_sort\": 9\n" +
                "  }],\n" +
                "  \"statistical_dimension\": [{\n" +
                "    \"param_key\": \"saleDepa\",\n" +
                "    \"param_name\": \"销售部门\",\n" +
                "    \"param_sort\": 1\n" +
                "  }, {\n" +
                "    \"param_key\": \"supplier\",\n" +
                "    \"param_name\": \"供应商\",\n" +
                "    \"param_sort\": 2\n" +
                "  }, {\n" +
                "    \"param_key\": \"goodClass\",\n" +
                "    \"param_name\": \"商品类别\",\n" +
                "    \"param_sort\": 3\n" +
                "  }, {\n" +
                "    \"param_key\": \"goodBrand\",\n" +
                "    \"param_name\": \"商品品牌\",\n" +
                "    \"param_sort\": 4\n" +
                "  }, {\n" +
                "    \"param_key\": \"goodVariety\",\n" +
                "    \"param_name\": \"商品品种\",\n" +
                "    \"param_sort\": 5\n" +
                "  }, {\n" +
                "    \"param_key\": \"goodName\",\n" +
                "    \"param_name\": \"商品名称\",\n" +
                "    \"param_sort\": 6\n" +
                "  }]\n" +
                "}";

        byte[] t1 = s.getBytes("UTF-8");
        for (int i = 0; i < t1.length;) {
            byte tt = t1[i];
            if (isAscii((char) tt)) {
                byte[] ba = new byte[1];
                ba[0] = tt;
                i++;
                String result = new String(ba);
            }
            if ((tt & 0xE0) == 0xC0) {
                byte[] ba = new byte[2];
                ba[0] = tt;
                ba[1] = t1[i+1];
                i++;
                i++;
                String result = new String(ba);
            }
            if ((tt & 0xF0) == 0xE0) {
                byte[] ba = new byte[3];
                ba[0] = tt;
                ba[1] = t1[i+1];
                ba[2] = t1[i+2];
                i++;
                i++;
                i++;
                String result = new String(ba);
            }
            if ((tt & 0xF8) == 0xF0) {
                System.out.println(i);
                byte[] ba = new byte[4];
                ba[0] = tt;
                ba[1] = t1[i+1];
                ba[2] = t1[i+2];
                ba[3] = t1[i+3];
                i++;
                i++;
                i++;
                i++;
                String result = new String(ba);
                System.out.println("4个字节的字符");
                System.out.println("字符为：" + result);
            }
        }

    }

    public static boolean isAscii(char ch)
    {
        return (ch < 128);
    }


    public int myAtoi(String str) {

        boolean isPositive = true;
        return 0;




    }
}

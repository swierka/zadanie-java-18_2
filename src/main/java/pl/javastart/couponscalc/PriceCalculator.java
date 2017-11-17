package pl.javastart.couponscalc;

import java.util.*;

public class PriceCalculator {

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {
        if (null == products) return 0.0;
        else if (null==coupons) return totalAmountNoDiscout(products);
        else return bestChoice(products, coupons);
    }

    public double bestChoice(List<Product> products, List<Coupon> coupons) {
        double minAmountToPay = 0;
        Category bestCat = CatWithMaxBonus(products, coupons);
        double discountApplied = 0;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getCategory().equals(bestCat)) {
                for (int j = 0; j < coupons.size(); j++) {
                    if (products.get(i).getCategory().equals(coupons.get(j).getCategory())) {
                        discountApplied = coupons.get(j).getDiscountValueInPercents();
                    }
                }
            } else discountApplied = 0;

            minAmountToPay += products.get(i).getPrice() * (1 - (discountApplied / 100));
        }

        if (minAmountToPay < totalAmountAfterDiscountOnAll(products, coupons))
            return minAmountToPay;
        else return totalAmountAfterDiscountOnAll(products, coupons);
    }


    public Category CatWithMaxBonus(List<Product> products, List<Coupon> coupons) {
        Map<Category, Double> amounts = new HashMap<>();
        double sum = 0;

        for (int i = 0; i < coupons.size(); i++) {
            for (int j = 0; j < products.size(); j++) {
                if (coupons.get(i).getCategory() != null && coupons.get(i).getCategory().equals(products.get(j).getCategory())) {
                    sum += (products.get(j).getPrice() * coupons.get(i).getDiscountValueInPercents()) / 100;  //obliczanie lacznej kwoty znizki dla danej kat
                }

            }
            amounts.put(coupons.get(i).getCategory(), sum);
        }


        double maxValue = 0;
        for (Map.Entry<Category, Double> amounts2 : amounts.entrySet()) {
            maxValue = amounts2.getValue();

            for (int i = 0; i < coupons.size(); i++) {
                if (amounts2.getKey() == coupons.get(i).getCategory() && null != coupons.get(i).getCategory()) {
                    if (amounts2.getValue() > maxValue) {
                        maxValue = amounts2.getValue();
                    }
                }

            }
        }

        Category catWithBestCoupon = null;
        Iterator<Map.Entry<Category, Double>> iter = amounts.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Category, Double> entry = iter.next();
            if (entry.getValue().equals(maxValue)) {
                catWithBestCoupon = entry.getKey();
            }
        }
        return catWithBestCoupon;
    }


    public double totalAmountNoDiscout(List<Product> products) {
        double totalSum = 0;
        for (Product product : products) {
            totalSum += product.getPrice();
        }
        return totalSum;
    }

    public double totalAmountAfterDiscountOnAll(List<Product> products, List<Coupon> coupons) {
        double totalValueAfterDiscounts = 0;
        double discountForAllProducts = 0;
        for (int j = 0; j < coupons.size(); j++) {
            if (null == coupons.get(j).getCategory() && coupons.get(j).getDiscountValueInPercents() != 0) {
                discountForAllProducts = coupons.get(j).getDiscountValueInPercents();
            }
        }
        totalValueAfterDiscounts = totalAmountNoDiscout(products) * (1 - (discountForAllProducts / 100));

        return totalValueAfterDiscounts;
    }
}

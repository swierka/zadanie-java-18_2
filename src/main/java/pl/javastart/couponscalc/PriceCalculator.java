package pl.javastart.couponscalc;

import java.util.*;

public class PriceCalculator {

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {
        if (null == products) return 0.0;
        else return bestChoice(products, coupons);
    }

    public double bestChoice(List<Product> products, List<Coupon> coupons) {
        double minAmountToPay = 0;
        Category bestCat = maxBonusByCat(products, coupons);
        double discountApplied = 0;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getCategory().equals(bestCat)) {
                for (int j = 0; j < coupons.size(); j++) {
                    if (coupons.get(j).equals(bestCat)) discountApplied = coupons.get(j).getDiscountValueInPercents();
                }
            } else discountApplied = 0;
            minAmountToPay += products.get(i).getPrice() * (1 - (discountApplied / 100));
        }

        if (minAmountToPay < totalAmountAfterDiscountOnAll(products, coupons))
            return minAmountToPay;
        else return totalAmountAfterDiscountOnAll(products, coupons);
    }


    public Category maxBonusByCat(List<Product> products, List<Coupon> coupons) {
        Map<Double, Category> amounts = new HashMap<>();
        double sum = 0;

        for (int i = 0; i < coupons.size(); i++) {
            for (int j = 0; j < products.size(); j++) {
                if (coupons.get(i).getCategory() !=null && coupons.get(i).getCategory().equals(products.get(j).getCategory())) {
                    sum += (products.get(j).getPrice() * coupons.get(i).getDiscountValueInPercents()) / 100;  //obliczanie lacznej kwoty znizki dla danej kat
                }

            }
            amounts.put(sum,coupons.get(i).getCategory());
        }


        double maxValue = 0;
        for (Map.Entry<Double, Category> amounts2 : amounts.entrySet()) {
            maxValue = amounts2.getKey();

            for (int i = 0; i < coupons.size(); i++) {
                if (amounts2.getValue() == coupons.get(i).getCategory() && null != coupons.get(i).getCategory()) {
                    if (amounts2.getKey() > maxValue) {
                        maxValue = amounts2.getKey();
                    }
                }

            }
        }
        return amounts.get(maxValue);
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
        for (int j = 0; j < coupons.size(); j++) {
            if (null == coupons.get(j).getCategory() && coupons.get(j).getDiscountValueInPercents() != 0) {
                double discountForAllProducts = coupons.get(j).getDiscountValueInPercents();
                totalValueAfterDiscounts = totalAmountNoDiscout(products) * (1 - (discountForAllProducts / 100));
            }
        }
        return totalValueAfterDiscounts;
    }
}

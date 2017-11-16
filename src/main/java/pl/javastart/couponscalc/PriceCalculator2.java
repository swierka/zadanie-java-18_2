/*
package pl.javastart.couponscalc;

import java.util.ArrayList;
import java.util.List;

public class PriceCalculator2 {

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {
        if (null == products) return 0.0;
        else if (totalAmountWithDiscounts(products, coupons) < totalAmountAfterDiscountOnAll(products, coupons))
            return totalAmountWithDiscounts(products, coupons);
        else return totalAmountAfterDiscountOnAll(products, coupons);
    }

    public double totalAmountWithDiscounts(List<Product> products, List<Coupon> coupons) {
        List<Double> productsDiscoutPrice = new ArrayList<>();

        double price = 0;
        double totalAmountWithDiscout = 0;


        for (int i = 0; i < products.size(); i++) {
            if (null == coupons) {
                price = products.get(i).getPrice();
            } else {
                for (int j = 0; j < coupons.size(); j++) {
                    if (products.get(i).getCategory().equals(coupons.get(j).getCategory())) {
                        double percent = coupons.get(j).getDiscountValueInPercents();
                        price = products.get(i).getPrice() * (1 - (percent / 100));
                        break;
                    } else if (!products.get(i).getCategory().equals(coupons.get(j).getCategory())) {
                        price = products.get(i).getPrice();
                    }
                }
            }
            productsDiscoutPrice.add(price);
        }

        for (Double item : productsDiscoutPrice) {
            totalAmountWithDiscout += item;
        }

        return totalAmountWithDiscout;
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
*/

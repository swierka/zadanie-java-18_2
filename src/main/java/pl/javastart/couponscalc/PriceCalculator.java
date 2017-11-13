package pl.javastart.couponscalc;

import java.util.ArrayList;
import java.util.List;

public class PriceCalculator {

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {
        List<Double> productsDiscoutPrice = new ArrayList<>();

        double price = 0;
        double totalAmountWithDiscout = 0;
        double totalAmmountNoDiscout = 0;
        double discountForAllProducts = 0;
        double amountToPay = 0;

        if (null == products) price = 0;
        else {
            for (int i = 0; i < products.size(); i++) {
                totalAmmountNoDiscout += products.get(i).getPrice();
                if (null == coupons) {
                    price = products.get(i).getPrice();
                } else {
                    for (int j = 0; j < coupons.size(); j++) {
                        if (products.get(i).getCategory().equals(coupons.get(j).getCategory())) {
                            double percent = coupons.get(j).getDiscountValueInPercents();
                            price = products.get(i).getPrice() * (1 - (percent / 100));
                            System.out.println("discount price: "+price);
                        } else if (coupons.get(j).getCategory() == null && coupons.get(j).getDiscountValueInPercents() != 0) {
                            discountForAllProducts = coupons.get(j).getDiscountValueInPercents() / 100;
                            System.out.println(discountForAllProducts);
                        }
                    }
                }
                productsDiscoutPrice.add(price);
            }
        }

        for (Double item : productsDiscoutPrice) {
            totalAmountWithDiscout += item;
        }

        System.out.println("Total amount without discout: "+totalAmmountNoDiscout);

        if (totalAmmountNoDiscout * (1 - discountForAllProducts) < totalAmountWithDiscout)
            amountToPay = totalAmmountNoDiscout * (1 - discountForAllProducts);
        else amountToPay = totalAmountWithDiscout;

        System.out.println("Total amount with discout: "+totalAmountWithDiscout);

        return amountToPay;
    }
}


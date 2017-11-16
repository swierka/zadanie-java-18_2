package pl.javastart.couponscalc;

import java.util.ArrayList;
import java.util.List;

public class TEST {

    public static void main(String[] args) {
        PriceCalculator calculator = new PriceCalculator();

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD,10));
        coupons.add(new Coupon(Category.HOME,5));
        coupons.add(new Coupon(Category.CAR,1));
        coupons.add(new Coupon(null,7));


        List<Product> products = new ArrayList<>();
        products.add(new Product("mleko",10,Category.FOOD));
        products.add(new Product("zarcie",1120,Category.FOOD));
        products.add(new Product("auto",15000,Category.CAR));
        products.add(new Product("kino",100,Category.ENTERTAINMENT));
        products.add(new Product("kanapa",1000,Category.HOME));




        System.out.println("max by cat: "+calculator.maxBonusByCat(products,coupons));
        System.out.println("bez dysk: "+calculator.totalAmountNoDiscout(products));
        System.out.println("bonus na wszystko: "+calculator.totalAmountAfterDiscountOnAll(products,coupons));
        System.out.println("Best choice: "+calculator.bestChoice(products,coupons));
        System.out.println("Ile do zaplaty : "+calculator.calculatePrice(products,coupons));

    }
}

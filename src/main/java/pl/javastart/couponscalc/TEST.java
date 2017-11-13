package pl.javastart.couponscalc;

import java.util.ArrayList;
import java.util.List;

public class TEST {

    public static void main(String[] args) {
        PriceCalculator calculator = new PriceCalculator();

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD,10));
        coupons.add(new Coupon(Category.HOME,5));
        coupons.add(new Coupon(null,2));


        List<Product> products = new ArrayList<>();
        products.add(new Product("mleko",10,Category.FOOD));
        products.add(new Product("kino",100,Category.ENTERTAINMENT));
        products.add(new Product("kanapa",1000,Category.HOME));


        double kwotaDoZaplaty = calculator.calculatePrice(products,coupons);
        System.out.println("test: "+kwotaDoZaplaty);
    }
}

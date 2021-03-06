package pl.javastart.couponscalc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PriceCalculatorTest {

    @Test
    public void shouldReturnZeroForNoProducts() {
        // given
        PriceCalculator priceCalculator = new PriceCalculator();

        // when
        double result = priceCalculator.calculatePrice(null, null);

        // then
        assertThat(result, is(0.));
    }

    @Test
    public void shouldReturnPriceForSingleProductAndNoCoupons() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 6.0, Category.FOOD));

        // when
        double result = priceCalculator.calculatePrice(products, null);

        // then
        assertThat(result, is(6.0));
    }

    @Test
    public void shouldReturnPriceForSingleProductAndOneCoupon() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 10.0, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 10));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result, is(9.0));
    }

    @Test
    public void shouldUseProperDiscount() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("jedzenie",500,Category.FOOD));
        products.add(new Product("jedzenie",300,Category.FOOD));
        products.add(new Product("kanapa",500,Category.HOME));
        products.add(new Product("rozrywka",300,Category.ENTERTAINMENT));
        products.add(new Product("opel corsa",2500,Category.CAR));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 10));
        coupons.add(new Coupon(Category.HOME,20));
        coupons.add(new Coupon(null,1));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result, is(4000.0));
    }

    @Test
    public void shouldUseDiscountForAll() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("jedzenie",500,Category.FOOD));
        products.add(new Product("jedzenie",300,Category.FOOD));
        products.add(new Product("kanapa",500,Category.HOME));
        products.add(new Product("rozrywka",300,Category.ENTERTAINMENT));
        products.add(new Product("opel corsa",2500,Category.CAR));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(null,1));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result, is(4059.0));
    }

}

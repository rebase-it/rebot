/*
 *   The MIT License (MIT)
 *
 *   Copyright (c) 2017 Rebase.it ReBot <just@rebase.it>
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy of
 *   this software and associated documentation files (the "Software"), to deal in
 *   the Software without restriction, including without limitation the rights to
 *   use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 *   the Software, and to permit persons to whom the Software is furnished to do so,
 *   subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *   FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 *   COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 *   IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *   CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


package it.rebase.rebot.service.currency.test;

import it.rebase.rebot.service.currency.provider.ecb.Currency;
import it.rebase.rebot.service.currency.provider.ecb.ECBHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CurrencyObjectTest {


    @Test
    public void testCurrencyDefault() {
        // this will simulate a full command coming from a chat without command prefix
        Currency currency = new Currency("brl,gbp,usd");
        String[] expected = {"brl", "gbp", "usd"};
        Assert.assertArrayEquals(expected, currency.symbols());
    }

    @Test
    public void testCurrencyObjectFirstParameter() {
        // this will simulate a full command coming from a chat without command prefix
        Currency currency = new Currency("base eur brl,gbp,usd");
        String[] expected = {"brl", "gbp", "usd"};
        Assert.assertEquals("base", currency.firstParameter());
        Assert.assertEquals("eur", currency.baseCurrency());
        Assert.assertArrayEquals(expected, currency.symbols());
    }

    @Test
    public void testCurrencyNameParameter() {
        Currency currency = new Currency("name gbp");
        Assert.assertEquals("name", currency.firstParameter());
        Assert.assertEquals("gbp", currency.symbol());
    }

    @Test
    public void testCurrencyWithAmount() {
        Currency currency = new Currency("brl,gbp,usd 25");
        String[] expected = {"brl", "gbp", "usd"};
        Assert.assertArrayEquals(expected, currency.symbols());
        Assert.assertEquals(25, currency.exchangeValue());
    }

    @Test
    public void testCurrencyWithDefaultAmount() {
        Currency currency = new Currency("brl,gbp,usd");
        String[] expected = {"brl", "gbp", "usd"};
        Assert.assertArrayEquals(expected, currency.symbols());
        Assert.assertEquals(1, currency.exchangeValue());
    }

    @Test
    public void testCurrencyAmount() {
        Currency currency = new Currency("brl, gbp 5");
        String[] expected = {"brl", "gbp"};
        Assert.assertArrayEquals(expected, currency.symbols());
        Assert.assertEquals(5, currency.exchangeValue());
    }

    @Test
    public void testCurrencyAmountMultipleSpacesOnCurrencies() {
        Currency currency = new Currency("brl, gbp , zar 5");
        String[] expected = {"brl", "gbp", "zar"};
        Assert.assertArrayEquals(expected, currency.symbols());
        Assert.assertEquals(5, currency.exchangeValue());
    }

    @Test
    public void testCurrencyBaseWithAmount() {
        // this will simulate a full command coming from a chat without command prefix
        Currency currency = new Currency("base eur brl,gbp,usd 48");
        String[] expected = {"brl", "gbp", "usd"};
        Assert.assertEquals("base", currency.firstParameter());
        Assert.assertEquals("eur", currency.baseCurrency());
        Assert.assertArrayEquals(expected, currency.symbols());
        Assert.assertEquals(48, currency.exchangeValue());
    }

    @Test
    public void testCurrencyBaseDefaultSymbols() {
        // this will simulate a full command coming from a chat without command prefix
        Currency currency = new Currency("base eur");
        Assert.assertEquals("base", currency.firstParameter());
        Assert.assertEquals("eur", currency.baseCurrency());
        Assert.assertArrayEquals(ECBHelper.DEFAULT_SYMBOLS.split(","), currency.symbols());
    }

    @Test
    public void testCurrencyBaseDefaultSymbolswithAmount() {
        // this will simulate a full command coming from a chat without command prefix
        Currency currency = new Currency("base brl 45");
        Assert.assertEquals("base", currency.firstParameter());
        Assert.assertEquals("brl", currency.baseCurrency());
        System.out.println("LOL " + Arrays.asList(currency.symbols()));
        Assert.assertArrayEquals(ECBHelper.DEFAULT_SYMBOLS.split(","), currency.symbols());
        Assert.assertEquals(45, currency.exchangeValue());
    }
}

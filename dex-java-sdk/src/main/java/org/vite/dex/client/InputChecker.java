package org.vite.dex.client;

import org.vite.dex.client.exception.DexApiException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InputChecker {

    private static final String regEx =
            "[ `~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\t";

    private static final InputChecker checkerInst;

    static {
        checkerInst = new InputChecker();
    }

    static InputChecker checker() {
        return checkerInst;
    }

    private boolean isSpecialChar(String str) {

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    <T> InputChecker shouldNotNull(T value, String name) {
        if (value == null) {
            throw new DexApiException(
                    DexApiException.INPUT_ERROR, "[Input] " + name + " should not be null");
        }
        return checkerInst;
    }

    <T> InputChecker shouldNull(T value, String name) {
        if (value != null) {
            throw new DexApiException(
                    DexApiException.INPUT_ERROR, "[Input] " + name + " should be null");
        }
        return checkerInst;
    }

    InputChecker checkSymbol(String symbol) {
        if (symbol == null || "".equals(symbol)) {
            throw new DexApiException(DexApiException.INPUT_ERROR, "[Input] Symbol is mandatory");
        }
        if (isSpecialChar(symbol)) {
            throw new DexApiException(
                    DexApiException.INPUT_ERROR, "[Input] " + symbol + " is invalid symbol");
        }
        return checkerInst;
    }

    InputChecker checkCurrency(String currency) {
        if (currency == null || "".equals(currency)) {
            throw new DexApiException(DexApiException.INPUT_ERROR, "[Input] Currency is mandatory");
        }
        if (isSpecialChar(currency)) {
            throw new DexApiException(
                    DexApiException.INPUT_ERROR, "[Input] " + currency + " is invalid currency");
        }
        return checkerInst;
    }

    InputChecker checkAddress(String address) {
        if (address == null || "".equals(address)) {
            throw new DexApiException(DexApiException.INPUT_ERROR, "[Input] Address is mandatory");
        }
        if (isSpecialChar(address)) {
            throw new DexApiException(
                    DexApiException.INPUT_ERROR, "[Input] " + address + " is invalid address");
        }
        return checkerInst;
    }

    InputChecker checkToken(String token) {
        if (token == null || "".equals(token)) {
            throw new DexApiException(DexApiException.INPUT_ERROR, "[Input] token is mandatory");
        }
        if (isSpecialChar(token)) {
            throw new DexApiException(
                    DexApiException.INPUT_ERROR, "[Input] " + token + " is invalid address");
        }
        return checkerInst;
    }

    InputChecker checkOrder(String orderId) {
        if (orderId == null || "".equals(orderId)) {
            throw new DexApiException(DexApiException.INPUT_ERROR, "[Input] orderId is mandatory");
        }
        if (isSpecialChar(orderId)) {
            throw new DexApiException(
                    DexApiException.INPUT_ERROR, "[Input] " + orderId + " is invalid address");
        }
        return checkerInst;
    }

    InputChecker checkRange(long size, long min, long max, String name) {
        if (!(min <= size && size <= max)) {
            throw new DexApiException(
                    DexApiException.INPUT_ERROR,
                    "[Input] " + name + " is out of bound. " + size + " is not in [" + min + "," + max + "]");
        }
        return checkerInst;
    }

    InputChecker checkSymbolList(List<String> symbols) {
        if (symbols == null || symbols.size() == 0) {
            throw new DexApiException(DexApiException.INPUT_ERROR, "[Input] Symbol is mandatory");
        }
        for (String symbol : symbols) {
            checkSymbol(symbol);
        }
        return checkerInst;
    }

    InputChecker checkSymbols(String symbols) {
        if (symbols == null || symbols.length() == 0) {
            throw new DexApiException(DexApiException.INPUT_ERROR, "[Input] Symbol is mandatory");
        }
        for (String symbol : symbols.split(",")) {
            checkSymbol(symbol);
        }
        return checkerInst;
    }

    InputChecker checkAddresses(String addresses) {
        if (addresses == null || addresses.length() == 0) {
            throw new DexApiException(DexApiException.INPUT_ERROR, "[Input] addresses is mandatory");
        }
        for (String address : addresses.split(",")) {
            checkAddress(address);
        }
        return checkerInst;
    }

    InputChecker greaterOrEqual(Integer value, int base, String name) {
        if (value != null && value < base) {
            throw new DexApiException(
                    DexApiException.INPUT_ERROR, "[Input] " + name + " should be greater than " + base);
        }
        return checkerInst;
    }

    <T> InputChecker checkList(List<T> list, int min, int max, String name) {
        if (list != null) {
            if (list.size() > max) {
                throw new DexApiException(
                        DexApiException.INPUT_ERROR,
                        "[Input] " + name + " is out of bound, the max size is " + max);
            } else if (list.size() < min) {
                throw new DexApiException(
                        DexApiException.INPUT_ERROR,
                        "[Input] " + name + " should contain " + min + " item(s) at least");
            }
        }
        return checkerInst;
    }
}

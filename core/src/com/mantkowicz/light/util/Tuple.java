package com.mantkowicz.light.util;

public class Tuple<X, Y> {
    private X x;
    private Y y;

    public static <X, Y> Tuple<X, Y> of(X x, Y y) {
        return new Tuple<X, Y>().setX(x).setY(y);
    }

    public X getX() {
        return x;
    }

    Tuple<X, Y> setX(X x) {
        this.x = x;
        return this;
    }

    public Y getY() {
        return y;
    }

    Tuple<X, Y> setY(Y y) {
        this.y = y;
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object thatObj) {
        if (thatObj instanceof Tuple) {
            Tuple<X, Y> that = (Tuple<X, Y>) thatObj;
            return this.x.equals(that.x) && this.y.equals(that.y);
        }
        return super.equals(thatObj);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }
}

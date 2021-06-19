package java.org.shir;



import java.io.Serializable;

public class ILS extends Coin implements Serializable {
  private  final double value =0.28;
    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double calculate(double arg) {
        return getValue()*arg;
    }
}


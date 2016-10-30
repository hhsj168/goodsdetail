import java.math.BigDecimal;

public class Test {

    public static void main(String[] arg0){
        BigDecimal bd = new BigDecimal("38.80");
//                BigDecimal bd = new BigDecimal(orderModel.order_amount);
        BigDecimal bd2 = new BigDecimal(100);
      int   total_fee = (int) (bd.multiply(bd2).doubleValue());
        System.out.print("total_fee:::"+total_fee);
    }
}
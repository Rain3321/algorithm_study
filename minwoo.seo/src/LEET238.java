import java.util.Arrays;

public class LEET238 {
  public int[] productExceptSelf(int[] sums) {
    int[] output = new int[sums.length];
    int left = 1;
    int right = 1;

    // i 번째 숫자 = 0 ~ i - 1 까지의 곱셈
    for (int i = 0; i < sums.length; i++) {
      output[i] = left;
      left *= sums[i];
    }

    // i 번째 숫자 = N ~ i + 1 까지의 곱셈
    for (int i = sums.length - 1; i >= 0; i--) {
      output[i] *= right;
      right *= sums[i];
    }
    
    return output;
  }
}

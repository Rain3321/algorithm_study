import java.util.Arrays;
import java.util.Scanner;

public class BOJ1548 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int result = n;
        if (arr.length > 2) {
            result = 0;
            for (int start = 0; start < arr.length - 2; start++) {
                for (int end = arr.length - 1; end > start + 1; end--) {
                    if (arr[start] + arr[start + 1] > arr[end]) {
                        result = Math.max(result, end - start + 1);
                    }
                }
            }
            if (result == 0) {
                result = 2;
            }
        }
        System.out.println(result);
    }
}

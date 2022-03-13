public class LEET238 {
    public int[] productExceptSelf(int[] nums) {
        int zeroCnt = 0;
        int tot_multiply = 1;

        for (int num : nums) {
            if (num != 0) {
                tot_multiply *= num;
            } else {
                zeroCnt++;
            }
        }

        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (zeroCnt == nums.length) {
                break;
            } else if (nums[i] == 0 && zeroCnt == 1) {
                ans[i] = tot_multiply;
            } else {
                if (zeroCnt == 0)
                    ans[i] = tot_multiply / nums[i];
            }
        }
        return ans;
    }
}

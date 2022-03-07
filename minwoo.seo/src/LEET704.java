public class LEET704 {
  public int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (mid >= nums.length) {
        break;
      }
      if (nums[mid] == target) {
        return mid;
      }
      else if (nums[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return -1;
  }
}

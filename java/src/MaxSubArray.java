import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by GelinZHU on 2/20/17.
 */
public class MaxSubArray {
    static int[] array = new int[] {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

    ArrayList<Integer> findMaxCrossingSubArray(int[] array, int low, int mid, int high) {
        ArrayList<Integer> result = new ArrayList<>();

        Integer left_sum = Integer.MIN_VALUE;
        Integer right_sum = Integer.MIN_VALUE;

        int sum = 0;
        int left_max = -1;
        int right_max = -1;

        for (int i = mid; i >= low; i--) {
            sum += array[i];
            if (sum > left_sum) {
                left_sum = sum;
                left_max = i;
            }
        }

        sum = 0;

        for(int i = mid + 1; i <= high; i++) {
            sum += array[i];
            if (sum > right_sum) {
                right_sum = sum;
                right_max = i;
            }
        }

        result.add(left_max);
        result.add(right_max);
        result.add(left_sum + right_sum);
        return result;
    }

    ArrayList<Integer> findMaxSubArray(int[] array, int low, int high) {
        ArrayList<Integer> result = new ArrayList<>();
        if (low == high) {
            result.add(low);
            result.add(high);
            result.add(array[low]);
            return result;
        }

        int mid = (low + high) / 2;
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        ArrayList<Integer> middle = new ArrayList<>();

        left = findMaxSubArray(array, low, mid);
        right = findMaxSubArray(array,mid + 1, high);
        middle = findMaxCrossingSubArray(array, low, mid, high);

        if (left.get(2) > right.get(2) && left.get(2) > middle.get(2)) {
            result.add(left.get(0));
            result.add(left.get(1));
            result.add(left.get(2));
            return result;
        } else if (right.get(2) > left.get(2) && right.get(2) > middle.get(2)) {
            result.add(right.get(0) + 1);
            result.add(right.get(1));
            result.add(right.get(2));
            return result;
        } else {
            result.add(middle.get(0));
            result.add(middle.get(1));
            result.add(middle.get(2));
            return result;
        }
    }


    public static void main(String[] args) {
        MaxSubArray msa = new MaxSubArray();
        ArrayList<Integer> result = msa.findMaxSubArray(array, 0, array.length - 1);
        System.out.println(result.toArray());
    }


}

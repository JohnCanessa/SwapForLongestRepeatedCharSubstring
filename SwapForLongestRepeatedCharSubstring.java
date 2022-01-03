import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * LeetCode 1156. Swap For Longest Repeated Character Substring
 * https://leetcode.com/problems/swap-for-longest-repeated-character-substring/
 */
public class SwapForLongestRepeatedCharSubstring {


    /**
     * Return the length of the longest substring with repeated characters.
     * 
     * This implementation works most of the time.
     * Input text: acbaaa produces 3 instead of 4.
     * 
     * !!! NOT accepted as a viable solution by LeetCode!!!
     */
    static public int maxRepOpt10(String text) {
        
        // **** sanity check(s) ****
        int len = text.length();
        if (len == 1) return 1;

        // ???? ????
        System.out.println("<<<     len: " + len);

        // **** initialization ****
        int[] freq  = new int[26];
        int[] left  = new int[len];
        int[] right = new int[len];
        char[] arr  = text.toCharArray();

        // ???? ????
        System.out.println("<<<     arr: " + Arrays.toString(arr));

        int maxLen  = 0;
        int thisLen = 0;

        // **** populate freq array ****
        for (int i = 0; i < len; i++)
            freq[arr[i] - 'a']++;
        
        // ???? ????
        System.out.println("<<<    freq: " + Arrays.toString(freq));

        // **** populate left array ****
        left[0] = 1;
        for (int i = 1; i < len; i++) {
            if (arr[i - 1] == arr[i])
                left[i] = left[i - 1] + 1;
            else
                left[i] = 1;
        }

        // ???? ????
        System.out.println("<<<    left: " + Arrays.toString(left));

        // **** populate right array ****
        right[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            if (arr[i] == arr[i + 1])
                right[i] = right[i + 1] + 1;
            else
                right[i] = 1;
        }

        // ???? ????
        System.out.println("<<<   right: " + Arrays.toString(right));

        // **** traverse the arr[] (string) left to right ****
        for (int i = 1; i < len - 1; i++) {

            // ???? ????
            System.out.println( "<<<  arr[" + (i - 1) + "]: " + arr[i - 1] + 
                                " arr[" + i + "]: " + arr[i] + 
                                " arr[" + (i + 1) + "]: " + arr[i + 1]);

            // **** case: "xyx" or "yxy" ****
            if (arr[i - 1] == arr[i + 1] && arr[i - 1] != arr[i]) {

                // **** case: "xyx" ****
                if (freq[arr[i - 1] - 'a'] == left[i - 1] + right[i + 1]) {
                    
                    // ???? ????
                    thisLen = left[i - 1] + right[i + 1];

                    maxLen = Math.max(maxLen, left[i - 1] + right[i + 1]);
                }

                // **** *****
                else {

                    // ???? ????
                    thisLen = left[i - 1] + right[i + 1] + 1;

                    maxLen = Math.max(maxLen, left[i - 1] + right[i + 1] + 1);
                }
            }

            // **** case: "xxx" or "xxy" or "yxx" or "xyy" or "yyx" or "xyz" ****
            else {

                // **** case: "yyx" or "xyz" ****
                if (freq[arr[i] - 'a'] == left[i]) {

                    // ???? ????
                    thisLen = left[i];

                    maxLen = Math.max(maxLen, left[i]);
                }

                // **** case: "xxx" or "xxy" or "yxx" or "xyy" ****
                else {

                    // ???? ????
                    thisLen = left[i] + 1;

                    maxLen = Math.max(maxLen, left[i] + 1);
                }
            }

            // ???? ????
            System.out.println("<<< thisLen: " + thisLen + " maxLen: " + maxLen);
        }

        // **** returned maximum length ****
        return maxLen;
    }


    /**
     * Return the length of the longest substring with repeated characters.
     * 
     * Execution: O(n) - Space: O(n + K)
     * 
     * Runtime: 6 ms, faster than 68.45% of Java online submissions.
     * Memory Usage: 39.5 MB, less than 14.44% of Java online submissions.
     * 
     * 56 / 56 test cases passed.
     * Status: Accepted
     * Runtime: 6 ms
     * Memory Usage: 39.5 MB
     */
    static public int maxRepOpt1(String text) {

        // **** sanity check(s) ****
        int len = text.length();
        if (len == 1) return 1;

        // ???? ????
        System.out.println("<<<       len: " + len);

        // **** initialization ****
        int count       = 1;
        int maxLen      = 1;
        char[] arr      = text.toCharArray();
        int [] freqs    = new int[26];
        int [] right    = new int [len]; 

        // **** populate the right and freqs arrays ****
        for (int i = len - 1; i >= 0; i--) {
            right[i] = 1 + ((i + 1 < len && arr[i + 1] == arr[i]) ? right[i + 1] : 0);
            freqs[arr[i] - 'a']++;
        }

        // ???? ????
        System.out.println("<<<       arr: " + Arrays.toString(arr));
        System.out.println("<<<     freqs: " + Arrays.toString(freqs));
        System.out.println("<<<     right: " + Arrays.toString(right));
        
        // **** traverse the arr[] left to right ****
        for (int i = 1; i < len; i++) {

            // ???? ????
            System.out.print(   "<<<    arr[" + (i - 1) + "]: " + arr[i - 1] + 
                                " arr[" + i + "]: " + arr[i]); 
            if (i < len - 1)
                System.out.print(" arr[" + (i + 1) + "]: " + arr[i + 1]);
            System.out.println();

            // **** check if same consecutive characters ****
            if (arr[i - 1] == arr[i]) {

                // **** increment count ****
                count++;

                // ???? ????
                System.out.println("<<< [1] count: " + count);
            }
            
            // **** different consecutive characters ****
            else {

                // **** check if previous is equal to next character ****
                count = count + ((i + 1 < len && arr[i - 1] == arr[i + 1]) ? right[i + 1] : 0);

                // ???? ????
                System.out.println("<<< [2] count: " + count);

                // **** check if current character may be replaced ****
                if (freqs[arr[i - 1] - 'a'] > count) count += 1;

                // ???? ????
                System.out.println("<<< [3] count: " + count);

                // **** update maximum length of consecutive characters ****
                maxLen = Math.max(maxLen, count);

                // ???? ????
                System.out.println("<<<    maxLen: " + maxLen);

                // **** reset counter ****
                count = 1;
            }
        }

        // **** check last character in the string ****
        if (freqs[arr[len - 1] - 'a'] > count) {

            // **** increment counter ****
            count += 1;

            // ???? ????
            System.out.println("<<< [4] count: " + count);
        }

        // **** return max length or count ****
        return Math.max(maxLen, count);
    }


    /**
     * Test scaffold.
     * !!! NOT PART OF SOLUTION !!!
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read input string ****
        String text = br.readLine().trim();

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< text ==>" + text + "<==");

        // **** call function of interest and display result ****
        // System.out.println("main <<< maxRepOpt10: " + maxRepOpt10(text));

        // **** call function of interest and display result ****
        System.out.println("main <<<  maxRepOpt1: " + maxRepOpt1(text));
    }
}
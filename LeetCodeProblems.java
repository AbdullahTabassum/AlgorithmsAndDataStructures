//LeetCode problems:
//Arrays and Strings
// 	1. Longest Substring Without Repeating Characters
// 		Given a string, find the length of the longest substring without repeating characters.
// 		Example 1:
// 		Input: "abcabcbb"
// 		Output: 3 
// 		Explanation: The answer is "abc", with the length of 3. 
// 		Example 2:
// 		Input: "bbbbb"
// 		Output: 1
// 		Explanation: The answer is "b", with the length of 1.
// 		Example 3:
// 		Input: "pwwkew"
// 		Output: 3
// 		Explanation: The answer is "wke", with the length of 3. 
// 		             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
// 		Approach: Use the sliding window approach. Have a beginning pointer and an ending pointer. Beginning starts from 0 and End starts from 0. Grow the window by moving the ending by one. if a new character is found increase the size, else if a repeated character is found record the max height and increase beginning pointer; the current array will have the invariant of being having unique characters. If a new character is added and it already exists in the current window then we move the left index up one which basically remove the left item from the current window and adds the new item on the right to the window. But what if the new item is not the same as the left most one i.e.
// 			"adcrd"
// 			in this case we have a problem. when the left pointer is at a and we increase right pointer to the second d, then moving the left pointer up by one does not solve the issue of duplicate, as there is still duplicates d and d
// 		- For this problem, note, that when we find that the new item is a duplicate of some item in the array, we dont increase the right window pointer, only the left pointer. we will continue to move the left pointer and remove characters from the set until we dont have a duplicate with the new character
public class Solution {
  public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    Set < Character > set = new HashSet < >();
    int ans = 0,
    i = 0,
    j = 0;
    while (i < n && j < n) {
      // try to extend the range [i, j]
      if (!set.contains(s.charAt(j))) {
        set.add(s.charAt(j++));
        ans = Math.max(ans, j - i);
      }
      else {
        // dont increase right window pointer
        set.remove(s.charAt(i++));
      }
    }
    return ans;
  }
}

// 	2. Roman to Integer: Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
// 					Symbol       Value
// 					I             1
// 					V             5
// 					X             10
// 					L             50
// 					C             100
// 					D             500
// 					M             1000
// 					For example, two is written as II in Roman numeral, just two ones added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
// 					Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
// 					I can be placed before V (5) and X (10) to make 4 and 9. 
// 					X can be placed before L (50) and C (100) to make 40 and 90. 
// 					C can be placed before D (500) and M (1000) to make 400 and 900.
// 					Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
// 					Example 1:
// 					Input: "III"
// 					Output: 3
// 					Example 2:
// 					Input: "IV"
// 					Output: 4
// 					Example 3:
// 					Input: "IX"
// 					Output: 9
// 					Example 4:
// 					Input: "LVIII"
// 					Output: 58
// 					Explanation: L = 50, V= 5, III = 3.
// 					Approach: 	
public static int romanToInt(String s) * {
  if (s == null || s.length() == 0) return - 1;
  HashMap < Character,
  Integer > map = new HashMap < Character,
  Integer > ();
  map.put(I, 1);
  map.put(V, 5);
  map.put(X, 10);
  map.put(L, 50);
  map.put(C, 100);
  map.put(D, 500);
  map.put(M, 1000);
  int len = s.length(),
  result = map.get(s.charAt(len - 1));
  for (int i = len - 2; i >= 0; i--) {
    if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1))) result += map.get(s.charAt(i));
    else result -= map.get(s.charAt(i));
  }
  return result;
}

// 					Another possibly cleaner solution:
class Solution {
  public int romanToInt(String s) {
    HashMap < Character,
    Integer > map = new HashMap < Character,
    Integer > () {
      {
        put(I, 1);
        put(V, 5);
        put(X, 10);
        put(L, 50);
        put(C, 100);
        put(D, 500);
        put(M, 1000);
      }
    };

    int sum = map.get(s.charAt(s.length() - 1));

    for (int i = s.length() - 2; i >= 0; i--) {
      if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) sum -= map.get(s.charAt(i));
      else sum += map.get(s.charAt(i));
    }
    return sum;
  }
}

// 	1. Atoi: convert a string to an integer
// 			-> - remove white spaces, ".trim()"
// 		       - first check the first position for its sign str.charAt(0)
// 		       		- store it in an int as -1 or +1
// 		       - then go throught the rest, letter by letter
// 		       - at each letter, check if its a valid digit
// 		       		-> Character.isDigit()
// 		       		-> convert digit to number if possible: int digit = (int)(str.charAt(i) - 0)
// 		       		-> check for overflow (num < Integer.MAX_VALUE - digit)
// 		       		-> if alls good, then add it to the current number -> num = num*10 + digit
public int myAtoi(String str) {
  str = str.trim();
  if (str.isEmpty()) return 0;
  int sign = 1;
  int i = 0;
  if (str.charAt(0) == -||str.charAt(0) == +) {
    sign = (str.charAt(0) == -) ? -1 : 1;
    if (str.length() < 2 || !Character.isDigit(str.charAt(1))) {
      return 0;
    }
    i++;
  }
  int n = 0;
  while (i < str.length()) {
    if (Character.isDigit(str.charAt(i))) {
      int d = str.charAt(i) - 0;
      if (n > (Integer.MAX_VALUE - d) / 10) { //Detect the integer overflow.
        n = (sign == -1) ? Integer.MIN_VALUE: Integer.MAX_VALUE;
        return n;
      }
      n = n * 10 + d;
    } else {
      break;
    }
    i++;
  }
  return sign * n;
}

// 	2. 3 sum - find three numbers in the array which add to give you zero
// 			- iterate through the array with variable i from 0 - arr.length - 2
// 				- this is because for each iteration we have three vars, one is i, one is b = i+1 (starts at) and one starts at i+2
// 			- the other two variables are low = i+1 and hi = arr.length - 1
// 			- sort the array
// 			Approach: iterate with i thorugh the array. 2 extra indices for each iteration m,n. m starts right after i and n starts at end of array. if the sum of all positions is equal to zero, return the 3 indices. if too big decrease n by one. if too small increase m by one. now inorder to avoid duplicates you need to skip iteration of m and n by increasing or decreasing them and not checking sum.
// 			- while(lo < hi) <--- this is in an outer for loop
// 				if(-1*arr[i] = arr[lo] + arr[hi])
// 					- lo++, hi--
// 					- add the three numbers to the return set
// 					- then keep incrementing and decrrementing hi and lo till all duplicates are skipped
// 						i.e while(arr[lo+1] == arr[lo] && lo < hi) lo++
// 				- if the 2 numbers are too big then decrement hi
// 				- if they are too small then incrmenent lo
public List < List < Integer >> threeSum(int[] nums) {
  List < List < Integer >> res = new ArrayList < >();
  Arrays.sort(nums);
  for (int i = 0; i + 2 < nums.length; i++) {
    if (i > 0 && nums[i] == nums[i - 1]) { // skip same result
      continue;
    }
    int j = i + 1,
    k = nums.length - 1;
    int target = -nums[i];
    while (j < k) {
      if (nums[j] + nums[k] == target) {
        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
        j++;
        k--;
        while (j < k && nums[j] == nums[j - 1]) j++; // skip same result
        while (j < k && nums[k] == nums[k + 1]) k--; // skip same result
      } else if (nums[j] + nums[k] > target) {
        k--;
      } else {
        j++;
      }
    }
  }
  return res;
}

// 	3. Remove duplicates from sorted array
// 			- go through the array and hold 2 indices
// 				- one that holds the last position of the final array (i.e. all elements before it are non-duplicate): End-of-non-dup array = eond
// 				- the other index scans (scanner )the array for unique elements one by until the end of the array
// 				- increment eond only when there is a non-duplicate (i.e. when arr[eond] != arr[scanner])
int set = 0;
while (j < arr.length) {

  while (arr[j] == arr[j + 1]) {
    j++;
  }
  // add the item to the end of the result array
  set++;
  arr[set] = arr[j];
  j++;
}

from leet code: -i is the position of the last element of the subarray that is sorted. - j is the index we use to scan the array - whenever arr[j] != arr[i],
then we increment i and put arr[j] over there
public int removeDuplicates(int[] nums) {
  if (nums.length == 0) return 0;
  int i = 0;
  for (int j = 1; j < nums.length; j++) {
    if (nums[j] != nums[i]) {
      i++;
      nums[i] = nums[j];
    }
  }
  return i + 1;
}

// 	4. Next Permutation: get the next greater number <--- return to this to understand it better
// 			1,2,3 → 1,3,2
// 			3,2,1 → 1,2,3
// 			1,1,5 → 1,5,1
// 			Approach: A Permutation is like a recursive problem. The first permutation for a slot will have all available items after it in ascending order (1,2,3,4).The last permutation for a slot will have all items after it in descending order (4,3,2,1). There is always a slot i.e. sub array, that is at its last permutation (this is a very important this to understand), i.e. all elements after that element are in decreasing order. So in order to find this slot, all we need to do is start from the right and iterate until we find the item that is not increasing from the left (..,..,..,3,4,2,1). In this case it is 3. So this means that the item 3 is at its last permutation. So we hold the position of 3, i.e. its index. Then find the next element that would come after 3; this would be the right most element (looking to the right from 3) whose value is more than 3. That is 4 in this case. Then we would swap the 3 and 4(..,..,..,4,3,2,1). Then we would simply reverse the array after the 4 i.e. ->  ..,..,..,4,1,2,3. And viola, we are done
// 			0,1,2,5,3,3,0
public class Solution {

  boolean nextPermutation(int[] array) {
    // Find longest non-increasing suffix
    int i = array.length - 1;
    while (i > 0 && array[i - 1] >= array[i])
    i--;
    // Now i is the head index of the suffix
    // Are we at the last permutation already?
    if (i <= 0) return false;

    // Let array[i - 1] be the pivot
    // Find rightmost element that exceeds the pivot
    int j = array.length - 1;
    while (array[j] <= array[i - 1])
    j--;
    // Now the value array[j] will become the new pivot
    // Assertion: j >= i
    // Swap the pivot with j
    int temp = array[i - 1];
    array[i - 1] = array[j];
    array[j] = temp;

    // Reverse the suffix
    j = array.length - 1;
    while (i < j) {
      temp = array[i];
      array[i] = array[j];
      array[j] = temp;
      i++;
      j--;
    }

    // Successfully computed the next permutation
    return true;
  }
}

// 	5. Multiply Strings - Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
// 				Example 1:
// 				Input: num1 = "2", num2 = "3"
// 				Output: "6"
// 				Example 2:
// 				Input: num1 = "123", num2 = "456"
// 				Output: "56088"
// 				Note:
// 				The length of both num1 and num2 is < 110.
// 				Both num1 and num2 contain only digits 0-9.
// 				Both num1 and num2 do not contain any leading zero, except the number 0 itself.
// 				You must not use any built-in BigInteger library or convert the inputs to integer directly.
public String multiply(String num1, String num2) {
  int m = num1.length(),
  n = num2.length();
  int[] pos = new int[m + n];

  for (int i = m - 1; i >= 0; i--) {
    for (int j = n - 1; j >= 0; j--) {
      int mul = (num1.charAt(i) - 0) * (num2.charAt(j) - 0);
      int p1 = i + j,
      p2 = i + j + 1;
      int sum = mul + pos[p2];

      pos[p1] += sum / 10;
      pos[p2] = (sum) % 10;
    }
  }

  StringBuilder sb = new StringBuilder();
  for (int p: pos) if (! (sb.length() == 0 && p == 0)) sb.append(p);
  return sb.length() == 0 ? "0": sb.toString();
}

// 	6. Group Anagrams: Given an array of strings, group anagrams together.
// 			Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
// 			Output:
// 			[
// 			  ["ate","eat","tea"],
// 			  ["nat","tan"],
// 			  ["bat"]
// 			]
// 			- create an array of 26 items where each item represents the number of the ith alphabet
getSerialization(String s) {
  int[] arr = new int[26];
  for (char c: s.toCharArray()) {
    if (Character.isLtter()) {
      arr[(int)(c - a)]++;
    }
  }

  // create a string from the array
  StringBuilder sb = new StringBuilder();
  for (int i: arr) {
    sb.append(#);
    sb.append(i);
  }

  return sb.toString();
}

// public Map<String, List<String>> createAnagramGroups(String[] strs) {
Map < String,
List < String >> map = new HashMap < >();
for (String str: strs) {
  String code = getSerialization(str);
  // just add here the anagram to the right group
  map.putIfAbsent(code, new ArrayList < >().add(str));
}

reutrn map;
}
// 			- then, serialize the array into a string where each element is seperated by #, i.e. abbd = 1#2#0#1
// 			- then put thi=e array into a Map like this: HashMap<String, List<String>>
class Solution {
  public List < List < String >> groupAnagrams(String[] strs) {
    if (strs.length == 0) return new ArrayList();
    Map < String,
    List > ans = new HashMap < String,
    List > ();
    int[] count = new int[26];
    for (String s: strs) {
      Arrays.fill(count, 0);
      for (char c: s.toCharArray()) count[c - a]++;

      StringBuilder sb = new StringBuilder("");
      for (int i = 0; i < 26; i++) {
        sb.append(#);
        sb.append(count[i]);
      }
      String key = sb.toString();
      if (!ans.containsKey(key)) ans.put(key, new ArrayList());
      ans.get(key).add(s);
    }
    return new ArrayList(ans.values());
  }
}

// 	7. Add Binary: Given two binary strings, return their sum (also a binary string). - come back to this one
// 			Input: a = "11", b = "1"
// 			Output: "100"
// 			Input: a = "1010", b = "1011"
// 			Output: "10101"
// 			Method 1: can convert to Binary and Decimal using built in fincitons
// 					Integer.toBinaryString and Integer.parseInt(string, 2)
// 				- once you have the binary representaion of the string,
// 				- then go through each item
class Solution {
  public String addBinary(String a, String b) {
    BigInteger x = new BigInteger(a, 2);
    BigInteger y = new BigInteger(b, 2);
    BigInteger zero = new BigInteger("0", 2);
    BigInteger carry,
    answer;
    while (y.compareTo(zero) != 0) {
      answer = x.xor(y);
      carry = x.and(y).shiftLeft(1);
      x = answer;
      y = carry;
    }
    return x.toString(2);
  }
}

// 	8.  Minimum Window Substring: Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
// 				- use sliding window technique
// 				- start with 2 indices both at the beginning, b,e
// 				- keep expanding the right index until we have all of the elements accounted for
// 				- save this window
// 				- then move the left window more right, if this is no longer a valid window repeat second step
// 				- how to keep track of window -> 2 indices
// 				- tracking the items weve weve added, use a set, when move the left item right, if the new left is not the same as the old one, we remove the item from the set
// 				- check the size of the set 
// 				- actually, instead of keeping a set we can keep a map, the map keeps track of the count of all found numbers, so when we move left or right, we update the count, if the count for something is zero we remove it from the map
// 				Input: S = "ADOBECODEBANC", T = "ABC"
// 				Output: "BANC"
class Solution {
  public String minWindow(String s, String t) {

    if (s.length() == 0 || t.length() == 0) {
      return "";
    }

    // Dictionary which keeps a count of all the unique characters in t.
    Map < Character,
    Integer > dictT = new HashMap < Character,
    Integer > ();
    for (int i = 0; i < t.length(); i++) {
      int count = dictT.getOrDefault(t.charAt(i), 0);
      dictT.put(t.charAt(i), count + 1);
    }

    // Number of unique characters in t, which need to be present in the desired window.
    int required = dictT.size();

    // Left and Right pointer
    int l = 0,
    r = 0;

    // formed is used to keep track of how many unique characters in t
    // are present in the current window in its desired frequency.
    // e.g. if t is "AABC" then the window must have two As, one B and one C.
    // Thus formed would be = 3 when all these conditions are met.
    int formed = 0;

    // Dictionary which keeps a count of all the unique characters in the current window.
    Map < Character,
    Integer > windowCounts = new HashMap < Character,
    Integer > ();

    // ans list of the form (window length, left, right)
    int[] ans = { - 1,
      0,
      0
    };

    while (r < s.length()) {
      // Add one character from the right to the window
      char c = s.charAt(r);
      int count = windowCounts.getOrDefault(c, 0);
      windowCounts.put(c, count + 1);

      // If the frequency of the current character added equals to the
      // desired count in t then increment the formed count by 1.
      if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
        formed++;
      }

      // Try and contract the window till the point where it ceases to be desirable.
      while (l <= r && formed == required) {
        c = s.charAt(l);
        // Save the smallest window until now.
        if (ans[0] == -1 || r - l + 1 < ans[0]) {
          ans[0] = r - l + 1;
          ans[1] = l;
          ans[2] = r;
        }

        // The character at the position pointed by the
        // `Left` pointer is no longer a part of the window.
        windowCounts.put(c, windowCounts.get(c) - 1);
        if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
          formed--;
        }

        // Move the left pointer ahead, this would help to look for a new window.
        l++;
      }

      // Keep expanding the window once we are done contracting.
      r++;
    }

    return ans[0] == -1 ? "": s.substring(ans[1], ans[2] + 1);
  }
}

// 	9. Merge Sorted arrays: Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
// 				Input:
// 				nums1 = [1,2,3,0,0,0], m = 3
// 				nums2 = [2,5,6],       n = 3
// 				Output: [1,2,2,3,5,6]elements from nums2.
// 				- one index in nums1 one in nums2
class Solution {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    // Make a copy of nums1.
    int[] nums1_copy = new int[m];
    System.arraycopy(nums1, 0, nums1_copy, 0, m);

    // Two get pointers for nums1_copy and nums2.
    int p1 = 0;
    int p2 = 0;

    // Set pointer for nums1
    int p = 0;

    // Compare elements from nums1_copy and nums2
    // and add the smallest one into nums1.
    while ((p1 < m) && (p2 < n))
    nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];

    // if there are still elements to add
    if (p1 < m) System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
    if (p2 < n) System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
  }
}

// 				// in place
// 				- note, one may imagine that the there may be a case where the items in arr1 may get overriden; but this cant happen. Say all of the free space after the last valid element in arr1 is taken up, then that means, every item from arr2 was exhausted. thus, arr1 could not be override\			
class Solution {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    // two get pointers for nums1 and nums2
    int p1 = m - 1;
    int p2 = n - 1;
    // set pointer for nums1
    int p = m + n - 1;

    // while there are still elements to compare
    while ((p1 >= 0) && (p2 >= 0))
    // compare two elements from nums1 and nums2 
    // and add the largest one in nums1 
    nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

    // add missing elements from nums2
    System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
  }
}

// 	10. Valid Palindrome: Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
// 				Note: For the purpose of this problem, we define empty string as valid palindrome.
// 				Example 1:
// 				Input: "A man, a plan, a canal: Panama"
// 				Output: true
// 				Example 2:
// 				Input: "race a car"
// 				Output: false
class Solution {
  while (low < high) {
    while (low < high && !Character.isLetterOrDigit(s.charAt(low)))
    low++;

    while (low < high && !Character.isLetterOrDigit(s.charAt(high)))
    high--;

    if (s.charAt(low) != s.charAt(high)) return false;
    low++;
    high--;
  }
  return true;
}

// 	11. Given a method read4(), create a method read(char[] buf, int n), that fills an array with n items from a file
// 				- basically call read4 repeatedly, each time stoer in a temp char[4]
// 				- see how much you have left to read; leftToRead
// 				d	- if it is more than 4, then subtract 4 from leftToRead and copu temp buffer into return buff
// 					- if read4 returned less than 4 you need to exit as that means you are at the end of the file
// 					- keep reading till you dont need to read anymore
// 						- if you read more than what is required i.e. you need to read 3 and not 4, then copy the first three items into the return buffer
public class Solution extends Reader4 {
  /**
				     * @param buf Destination buffer
				     * @param n   Number of characters to read
				     * @return    The number of actual characters read
				     */
  public int read(char[] buf, int n) {
    int readBytes = 4;
    char[] copyBuf = new char[4];
    int pos = 0;
    while (pos < n && readBytes == 4) {
      readBytes = read4(copyBuf);
      int expectedBytes = Math.min(readBytes, n - pos);
      for (int i = 0; i < expectedBytes; i++) {
        buf[pos + i] = copyBuf[i];
      }
      pos += expectedBytes;
    }
    return pos;
  }
}

// 	12. One Edit Distance: Given two strings s and t, determine if they are both one edit distance apart.
// 				Note: 
// 				There are 3 possiblities to satisify one edit distance apart:
// 					Insert a character into s to get t
// 					Delete a character from s to get t
// 					Replace a character of s to get t
// 				Approach:
// 					bunch of if statements
// 						- first check the sizes are of by at most one
// 						- then, from this we can determine if its a deletion or an insertion or a letter is wrong
// 						- then based on the above,
// 							- if it is a deletion go through the words letter by letter (comparing each)
// 								- at the first mismatch, we will assume its a deletion, then move one of the pointer up by one (of the word which is not deleted). then up till the end of the strings,k they should be the same
class Solution {
  public boolean isOneEditDistance(String s, String t) {
    int ns = s.length();
    int nt = t.length();

    // Ensure that s is shorter than t.
    if (ns > nt) return isOneEditDistance(t, s);

    // The strings are NOT one edit away distance  
    // if the length diff is more than 1.
    if (nt - ns > 1) return false;

    for (int i = 0; i < ns; i++)
    if (s.charAt(i) != t.charAt(i))
    // if strings have the same length
    if (ns == nt) return s.substring(i + 1).equals(t.substring(i + 1));
    // if strings have different lengths
    else return s.substring(i).equals(t.substring(i + 1));

    // If there is no diffs on ns distance
    // the strings are one edit away only if
    // t has one more character. 
    return (ns + 1 == nt);
  }
}

// 	13. Product of Array Except Self: Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
// 				Example:
// 					Input:  [1,2,3,4]
// 					Output: [24,12,8,6]
// 					Note: Please solve it without division and in O(n).
// 					Follow up:
// 					Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.).
// 					Approach- Basically, keep two arrays, LeftProduct and RightProduct. Then basically fill up the LeftArray with the product of all the numbers to the left of the ith position. And do the same for the RightProduct but from the right.
// 					Then run through the original array, and to calculate the ith product, simply find the product of LeftProduct[i] and RightProduct[i]. This is basically multiplying all of the numbers to the left and right of the ith item (i.e. every item except i)
class Solution {
  public int[] productExceptSelf(int[] nums) {

    // The length of the input array
    int length = nums.length;

    // The left and right arrays as described in the algorithm
    int[] L = new int[length];
    int[] R = new int[length];

    // Final answer array to be returned
    int[] answer = new int[length];

    // L[i] contains the product of all the elements to the left
    // Note: for the element at index 0, there are no elements to the left,
    // so L[0] would be 1
    L[0] = 1;
    for (int i = 1; i < length; i++) {

      // L[i - 1] already contains the product of elements to the left of i - 1
      // Simply multiplying it with nums[i - 1] would give the product of all
      // elements to the left of index i
      L[i] = nums[i - 1] * L[i - 1];
    }

    // R[i] contains the product of all the elements to the right
    // Note: for the element at index length - 1, there are no elements to the right,
    // so the R[length - 1] would be 1
    R[length - 1] = 1;
    for (int i = length - 2; i >= 0; i--) {

      // R[i + 1] already contains the product of elements to the right of i + 1
      // Simply multiplying it with nums[i + 1] would give the product of all
      // elements to the right of index i
      R[i] = nums[i + 1] * R[i + 1];
    }

    // Constructing the answer array
    for (int i = 0; i < length; i++) {
      // For the first element, R[i] would be product except self
      // For the last element of the array, product except self would be L[i]
      // Else, multiple product of all elements to the left and to the right
      answer[i] = L[i] * R[i];
    }

    return answer;
  }
}

// 	14. Integer to english word: Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
// 				Example 1:
// 				Input: 123
// 				Output: "One Hundred Twenty Three"
// 				Example 2:
// 				Input: 12345
// 				Output: "Twelve Thousand Three Hundred Forty Five"
// 				Example 3:
// 				Input: 1234567
// 				Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
// 				Example 4:
// 				Input: 1234567891
// 				Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
// 				Approach: there are multiple cases
// 					- read upto three digits from the right at a time
// 					- start at the right
// 						- check the size
// 							- if the size is 1, then just print out the number in english
// 								String getSingleDigitWord(int i)
// 							- if the size is two, then there get the numberith mapping from the 10s digits
// 								- if the number is a 1, then you need to get the teens mapping
// 									String getTeenMapping(int ones)
// 								- if the number is a more than one, then you need to get
// 									String getTensMapping(int tens, int ones)
// 							- if the size is three, get the last digit, append hundred to it
// 								String get Hundred
// 					From leetcode:
// 					Letith simplify the problem by representing it as a set of simple sub-problems. One could split the initial integer 1234567890 on the groups containing not more than three digits 1.234.567.890. That results in representation 1 Billion 234 Million 567 Thousand 890 and reduces the initial problem to how to convert 3-digit integer to English word. One could split further 234 -> 2 Hundred 34 into two sub-problems : convert 1-digit integer and convert 2-digit integer. The first one is trivial. The second one could be reduced to the first one for all 2-digit integers but the ones from 10 to 19 which should be considered separately.
class Solution {
  public String one(int num) {
    switch (num) {
    case 1:
      return "One";
    case 2:
      return "Two";
    case 3:
      return "Three";
    case 4:
      return "Four";
    case 5:
      return "Five";
    case 6:
      return "Six";
    case 7:
      return "Seven";
    case 8:
      return "Eight";
    case 9:
      return "Nine";
    }
    return "";
  }

  public String twoLessThan20(int num) {
    switch (num) {
    case 10:
      return "Ten";
    case 11:
      return "Eleven";
    case 12:
      return "Twelve";
    case 13:
      return "Thirteen";
    case 14:
      return "Fourteen";
    case 15:
      return "Fifteen";
    case 16:
      return "Sixteen";
    case 17:
      return "Seventeen";
    case 18:
      return "Eighteen";
    case 19:
      return "Nineteen";
    }
    return "";
  }

  public String ten(int num) {
    switch (num) {
    case 2:
      return "Twenty";
    case 3:
      return "Thirty";
    case 4:
      return "Forty";
    case 5:
      return "Fifty";
    case 6:
      return "Sixty";
    case 7:
      return "Seventy";
    case 8:
      return "Eighty";
    case 9:
      return "Ninety";
    }
    return "";
  }

  public String two(int num) {
    if (num == 0) return "";
    else if (num < 10) return one(num);
    else if (num < 20) return twoLessThan20(num);
    else {
      int tenner = num / 10;
      int rest = num - tenner * 10;
      if (rest != 0) return ten(tenner) + " " + one(rest);
      else return ten(tenner);
    }
  }

  public String three(int num) {
    int hundred = num / 100;
    int rest = num - hundred * 100;
    String res = "";
    if (hundred * rest != 0) res = one(hundred) + " Hundred " + two(rest);
    else if ((hundred == 0) && (rest != 0)) res = two(rest);
    else if ((hundred != 0) && (rest == 0)) res = one(hundred) + " Hundred";
    return res;
  }

  public String numberToWords(int num) {
    if (num == 0) return "Zero";

    int billion = num / 1000000000;
    int million = (num - billion * 1000000000) / 1000000;
    int thousand = (num - billion * 1000000000 - million * 1000000) / 1000;
    int rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000;

    String result = "";
    if (billion != 0) result = three(billion) + " Billion";
    if (million != 0) {
      if (!result.isEmpty()) result += " ";
      result += three(million) + " Million";
    }
    if (thousand != 0) {
      if (!result.isEmpty()) result += " ";
      result += three(thousand) + " Thousand";
    }
    if (rest != 0) {
      if (!result.isEmpty()) result += " ";
      result += three(rest);
    }
    return result;
  }
}

// 	15. Move zeros: move all zeros to end of array
// 				Approach: basically, have two pointers, one keeps track of the last non-zero number, the other keeps track of current number. If we find a non zero number we put put it in the place of last non-zero number.
void moveZeroes(vector < int > &nums) {
  int lastNonZeroFoundAt = 0;
  // If the current element is not 0, then we need to
  // append it just in front of last non 0 element we found. 
  for (int i = 0; i < nums.size(); i++) {
    if (nums[i] != 0) {
      nums[lastNonZeroFoundAt++] = nums[i];
    }
  }
  // After we have finished processing new elements,
  // all the non-zero elements are already at beginning of array.
  // We just need to fill remaining array with 0ith.
  for (int i = lastNonZeroFoundAt; i < nums.size(); i++) {
    nums[i] = 0;
  }
}

// 				A slight imorvement would be something like this
void moveZeroes(vector < int > &nums) {
  for (int lastNonZeroFoundAt = 0, cur = 0; cur < nums.size(); cur++) {
    if (nums[cur] != 0) {
      swap(nums[lastNonZeroFoundAt++], nums[cur]);
    }
  }
}

// 	16. Longest Substring with At Most K Distinct Characters: Given a string, find the length of the longest substring T that contains at most k distinct characters.
// 				Example 1:
// 				Input: s = "eceba", k = 2
// 				Output: 3
// 				Explanation: T is "ece" which its length is 3.
// 				Example 2:
// 				Input: s = "aa", k = 1
// 				Output: 2
// 				Explanation: T is "aa" which its length is 2.
// 				Approach: use two pointer, and a set to keep track of already found items
// 				The idea is to set both pointers in the position 0 and then move right pointer to the right while the window contains not more than k distinct characters. If at some point weve got k + 1 distinct characters, letith move left pointer to keep not more than k + 1 distinct characters in the window. Note: we would keep the right most index of an item in the hashmap. When we are done with an item, remove it from the map. Keep a variable with max_length
class Solution {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int n = s.length();
    if (n * k == 0) return 0;

    // sliding window left and right pointers
    int left = 0;
    int right = 0;
    // hashmap character -> its rightmost position 
    // in the sliding window
    HashMap < Character,
    Integer > hashmap = new HashMap < Character,
    Integer > ();

    int max_len = 1;

    while (right < n) {
      // add new character and move right pointer
      hashmap.put(s.charAt(right), right++);

      // slidewindow contains 3 characters
      if (hashmap.size() == k + 1) {
        // delete the leftmost character
        int del_idx = Collections.min(hashmap.values());
        hashmap.remove(s.charAt(del_idx));
        // move left pointer of the slidewindow
        left = del_idx + 1;
      }

      max_len = Math.max(max_len, right - left);
    }
    return max_len;
  }
}

// 				Improvement: To improve this, notice an issue. When we increase the right pointer and if this causes out set to have more than k distinct integers, then we have to move the left pointer up and consequently remove an item from the hashmap. But what if that left value exists somewhere else in the current window? In that case, as in the above code, we look at each item in the hashmap and find which item has the left most element, then we remove that element only (then update left to one after that guys position). So we have to iterate the hashmap to find the item with left most element, this can be improved. If we use a LinkedHashMap, instead of a normal HashMap, the oldest value added/updated will always be at the front, so instead of looking for the leftmost item, we can just pop the first one from the LinkedHashMap.
class Solution {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int n = s.length();
    if (n * k == 0) return 0;

    // sliding window left and right pointers
    int left = 0;
    int right = 0;
    // hashmap character -> its rightmost position 
    // in the sliding window
    LinkedHashMap < Character,
    Integer > hashmap = new LinkedHashMap < Character,
    Integer > (k + 1);

    int max_len = 1;

    while (right < n) {
      Character character = s.charAt(right);
      // if character is already in the hashmap -
      // delete it, so that after insert it becomes
      // the rightmost element in the hashmap
      if (hashmap.containsKey(character)) hashmap.remove(character);
      hashmap.put(character, right++);

      // slidewindow contains k + 1 characters
      if (hashmap.size() == k + 1) {
        // delete the leftmost character
        Map.Entry < Character,
        Integer > leftmost = hashmap.entrySet().iterator().next();
        hashmap.remove(leftmost.getKey());
        // move left pointer of the slidewindow
        left = leftmost.getValue() + 1;
      }

      max_len = Math.max(max_len, right - left);
    }
    return max_len;
  }
}

// 	17. Validate IP Address: Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
// 				Approach: Use String.split, Integer.parseInt, Character.isDigit, String.toCharArray
class Solution {
  private boolean isValidHexa(char a) {
    return (a >= a && a <= f) || (a >= A && a <= F);
  }
  private boolean isValidIPV4(String IP) {
    String[] a = IP.split("\\.");
    if (a.length != 4) return false;
    for (String p: a) {
      if (p.isEmpty() || p.length() > 1 && p.charAt(0) == 0 || p.length() > 3) return false;
      for (char c: p.toCharArray()) if (!Character.isDigit(c)) return false;
      int value = Integer.parseInt(p);
      if (value < 0 || value > 255) return false;
    }
    return IP.charAt(IP.length() - 1) != .;
  }
  private boolean isValidIPV6(String IP) {
    String[] a = IP.split(":");
    if (a.length != 8) return false;
    for (String p: a) {
      if (p.length() > 4 || p.isEmpty()) return false;
      for (char c: p.toCharArray()) if (!Character.isDigit(c) && !isValidHexa(c)) return false;
    }
    return IP.charAt(IP.length() - 1) != :;
  }
  public String validIPAddress(String IP) {
    return IP.contains(".") ? isValidIPV4(IP) ? "IPv4": "Neither": isValidIPV6(IP) ? "IPv6": "Neither";
  }
}

// 	18.  Subarray Sum Equals K: Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
// 				Example 1:
// 				Input:nums = [1,1,1], k = 2
// 				Output: 2
// 				Note:
// 				The length of the array is in range [1, 20,000].
// 				The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
// 				Approach 1:
// 				- we can try to compute all beginning and end pairs(i.e. using a double for loop), but thats slow. Another trick is compute the cummulative sum for each i (the sum from 0-i in the array). then if we want to calculate the sum between i and j we can do something like cummSum[j+1] - cummSum[i]. Another improvement is to not use additional space. So for each beginning index, we start a sum from 0, and for each endIndex for that beginning index we add onto 0 or the last sum for the beginning index.
public class Solution {
  public int subarraySum(int[] nums, int k) {
    int count = 0;
    for (int start = 0; start < nums.length; start++) {
      int sum = 0;
      for (int end = start; end < nums.length; end++) {
        sum += nums[end];
        if (sum == k) count++;
      }
    }
    return count;
  }
}

// 				An even better approach using a hashmap is the following. Note that if you are going through the array and keeping track of cumulative sum, if you run into the same sum that you calculated previously, that means that if you started from the first time you saw that sum and added the numbers up to the second time you saw that sum, the total sum will be zero. Using this idea, we can keep track of all different sums that we encounter in a hashmap and the index we saw them at. If you find there is a sum you previously encountered such that the (current sum - k) is equal to it, then that means that the current index to that index is a subarry whose elements sum to k.
public class Solution {
  public int subarraySum(int[] nums, int k) {
    int count = 0,
    sum = 0;
    HashMap < Integer,
    Integer > map = new HashMap < >();
    map.put(0, 1);
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (map.containsKey(sum - k)) count += map.get(sum - k);
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    return count;
  }
}

// 	19.  Valid Palindrome II: Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
// 				Example 1:
// 				Input: "aba"
// 				Output: True
// 				Example 2:
// 				Input: "abca"
// 				Output: True
// 				Explanation: You could delete the character c.
// 				Note:
// 				The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
// 				Approach: Suppose we want to know whether s[i], s[i+1], ..., s[j] form a palindrome. If i >= j then we are done. If s[i] == s[j] then we may take i++; j--. Otherwise, the palindrome must be either s[i+1], s[i+2], ..., s[j] or s[i], s[i+1], ..., s[j-1], and we should check both cases.
class Solution {
  public boolean isPalindromeRange(String s, int i, int j) {
    for (int k = i; k <= i + (j - i) / 2; k++) {
      if (s.charAt(k) != s.charAt(j - k + i)) return false;
    }
    return true;
  }
  public boolean validPalindrome(String s) {
    for (int i = 0; i < s.length() / 2; i++) {
      if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
        int j = s.length() - 1 - i;
        return (isPalindromeRange(s, i + 1, j) || isPalindromeRange(s, i, j - 1));
      }
    }
    return true;
  }
}

// Linked Lists:
// 	1. You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
// 		You may assume the two numbers do not contain any leading zero, except the number 0 itself.
// 		Example:
// 		Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
// 		Output: 7 -> 0 -> 8
// 		Explanation: 342 + 465 = 807.
// 		Approach: just go through the linked list till the end. each iteration keep track of the carry

public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
  ListNode dummyHead = new ListNode(0);
  ListNode p = l1,
  q = l2,
  curr = dummyHead;
  int carry = 0;
  while (p != null || q != null) {
    int x = (p != null) ? p.val: 0;
    int y = (q != null) ? q.val: 0;
    int sum = carry + x + y;
    carry = sum / 10;
    curr.next = new ListNode(sum % 10);
    curr = curr.next;
    if (p != null) p = p.next;
    if (q != null) q = q.next;
  }
  if (carry > 0) {
    curr.next = new ListNode(carry);
  }
  return dummyHead.next;
}

// 	2. Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
// 		Example:
// 		Input: 1->2->4, 1->3->4
// 		Output: 1->1->2->3->4->4
public Node merge(Node l, Node r) {
  if (l1 == null && l2 null) {
    return null;
  }

  if (l2 == null) {
    return l1;
  } else {
    return l2;
  }

  // will need to return the head of one of these nodes
  Node head = null;
  Node tail = null;
  if (l1.value >= l2.value) {
    head = l1;
  } else {
    head = l2;
  }
  tail = head;

  while (l1 != null && l2 != null) {
    // compare
    if (l1.value >= l2) {
      tail.next = l1;
      l1 = l1.next;
      tail = tail.next;
      tail.next = null
    } else {
      tail.next = l2;
      l2 = l2.next;
      tail = tail.next;
      tail.next = null
    }

  }

  // here check if l1 or l2 is null, and if any of them are not null, then add them to the
  // end of tail.
  return head;
}

// 	3. Copy List with Random Pointer: A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
// 		Return a deep copy of the list.
Map < Node,
Node > visited = new HashMap < >();

public Node getCopy(Node node) {
  Node copy = visited.get(node);
  if (copy == null) {
    Node temp = new Node(node.value);
    visited.put(node, temp);
    copy = temp;
  }

  return copy;
}

public Node clone(Node n) {
  if (head == null) {
    return null;
  }

  Node head = getCopy(n);
  Node tail = head;
  tail.next = getCopy(n.next);
  tail.random = getCopy(n.random);
  tail.next = null;
  n = n.next;

  while (n != null) {
    Node temp = getCopy(n);
    tamp.next = getCopy(n.next);
    tamp.random = getCopy(n.random);
    n = n.next;

    tail = temp;
    tail.next = null;
  }

  return heads;
}

// 	4. Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
// 		You may not modify the values in the listith nodes, only nodes itself may be changed.
// 		Example 1:
// 		Given 1->2->3->4, reorder it to 1->4->2->3.
// 		Example 2:
// 		Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
// 		Approach: traverser to the middle of the linked list,
public class Solution {

  public void reorderList(ListNode head) {
    if (head == null || head.next == null) return;

    // step 1. cut the list to two halves
    // prev will be the tail of 1st half
    // slow will be the head of 2nd half
    ListNode prev = null,
    slow = head,
    fast = head,
    l1 = head;

    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }

    prev.next = null;

    // step 2. reverse the 2nd half
    ListNode l2 = reverse(slow);

    // step 3. merge the two halves
    merge(l1, l2);
  }

  ListNode reverse(ListNode head) {
    ListNode prev = null,
    curr = head,
    next = null;

    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    return prev;
  }

  void merge(ListNode l1, ListNode l2) {
    while (l1 != null) {
      ListNode n1 = l1.next,
      n2 = l2.next;
      l1.next = l2;

      if (n1 == null) break;

      l2.next = n1;
      l1 = n1;
      l2 = n2;
    }
  }

}

Practie: reverse recursive: publiv Node reverse(Node n, Node prev) {
  if (n == null) {
    return null;
  }

  if (n.next = null) {
    // this will be the new head, so we shall return it
    n.next = prev;
    return n;
  }

  n.next = prev;
  return reverse(n, n.next);

}

another recursive way: public Node reverse(Node n) {
  if (n == null || n.next == null) {
    return n;
  }

  Node head = reverse(n.next);

  // the current nodes next node should point to the current node
  n.next.next = n;
  // the current nodeith next should point to null
  n.next = null;

  // return the new head back upto the chain
  return head;
}

iterative: public Node reverse(Node n) {
  Node prev = null;
  Node curr = n;
  Node next = n.next;

  while (curr != null) {
    curr.next = prev;
    prev = curr;
    curr = next;
    next = next.next;
  }

  return prev;
}

// Trees/Graphs
// 	1. Validate Binary Search TreeGiven a binary tree, determine if it is a valid binary search tree (BST).
// 		Assume a BST is defined as follows:
// 		The left subtree of a node contains only nodes with keys less than the nodeith key.
// 		The right subtree of a node contains only nodes with keys greater than the nodeith key.
// 		Both the left and right subtrees must also be binary search trees.

// 		Example 1:
// 		    2
// 		   / \
// 		  1   3
// 		Input: [2,1,3]
// 		Output: true
// 		Example 2:
// 		    5
// 		   / \
// 		  1   4
// 		     / \
// 		    3   6
// 		Input: [5,1,4,null,null,3,6]
// 		Output: false
// 		Explanation: The root nodeith value is 5 but its right childith value is 4.
// 		Approach: need to make sure that every node to the right is smaller/ greater than ALL the node node above it
// 		// at each node, we pass the range the next node must be within i.e recursing to a right node, all nodes beneath it must be greater than the current node; recursing to the left node, all nodes beneath it must be less than the current node
boolean isValidBST(Node head) {
  isValidHelper(head, Integer.MAX, Integer.MIN);
}

boolean isValidBST(Node head, int upperLimit, int lowerLimit) {
  if (head == null) {
    return true;
  }

  if (head.value >= upperLimit || head.value <= lowerLimit) {
    return false;
  }

  return isValidBST(head.left, head.value, lowerLimit) && isValidBST(head.right, upperLimit, head.value);
}

// 	2. Given a binary tree, flatten it to a linked list in-place.
// 		For example, given the following tree:
// 		    1
// 		   / \
// 		  2   5
// 		 / \   \
// 		3   4   6
// 		The flattened tree should look like:
// 		1
// 		 \
// 		  2
// 		   \
// 		    3
// 		     \
// 		      4
// 		       \
// 		        5
// 		         \
// 		          6
// 		Approach: We will tackle this recursively. At each node what we need to do, is get the flattened left subtree of that node, append the flattened right subtree to the end of the flattened left subtree, then add the flattened left subtree to the tail of the current node. Base case is if the current node is null, return null.
// Answer from LeetCode:
public void flatten(TreeNode root) {
  if (root != null) return;
  flatten(root.right);
  if (root.left != null) {
    flatten(root.left);
    TreeNode node = root.left;
    while (node.right != null) {
      node = node.right;
    }
    node.right = root.right;
    root.right = root.left;
    root.left = null;
  }
}

// 	3. Given a non-empty binary tree, find the maximum path sum.
// 			For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
// 			//Example 1:
// 			Input: [1,2,3]
// 			       1
// 			      / \
// 			     2   3
// 			//Output: 6
// 			//Example 2:
// 			Input: [-10,9,20,null,null,15,7]
// 			   -10
// 			   / \
// 			  9  20
// 			    /  \
// 			   15   7
// 			//Output: 42
class Solution {
  int max_sum = Integer.MIN_VALUE;

  public int max_gain(TreeNode node) {
    if (node == null) return 0;

    // max sum on the left and right sub-trees of node
    int left_gain = Math.max(max_gain(node.left), 0);
    int right_gain = Math.max(max_gain(node.right), 0);

    // the price to start a new path where `node` is a highest node
    int price_newpath = node.val + left_gain + right_gain;

    // update max_sum if its better to start a new path
    max_sum = Math.max(max_sum, price_newpath);

    // for recursion :
    // return the max gain if continue the same path
    return node.val + Math.max(left_gain, right_gain);
  }

  public int maxPathSum(TreeNode root) {
    max_gain(root);
    return max_sum;
  }
}

// 	4. Clone a graph: Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
// 		Note:
// 		The number of nodes will be between 1 and 100.
// 		The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
// 		Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
// 		You must return the copy of the given node as a reference to the cloned graph.
// 		Approach: can do this using DFS or BFS, good practice to try both.
// 		DFS:
Set < Node > visited = new HasSet < Node > ();
Map < Node,
Node > map = new HashMap < Node,
Node > ();

public Node clone(Node node) {
  Node n = new Node(node.value)
  cloneHelper(node);
  return n;
}

private Node cloneHelper(Node node) {
  if (node == null) {
    return;
  }

  visited.add(node);
  // add this node into the map
  Node temp = getNode(node);

  for (Node n: node.list) {
    temp.list.add(n);
    if (!visited.contains(n)) {
      cloneHelper(n);
    }
  }
}

private void createNode(Node original) {
  if (!map.get(original)) {
    Node temp = new Node(original.value);
    map.put(original, temp);
  }

  return map.get(original);
}

// 		BFS:

/// TODO: complete this later
// 	5. Binary Tree Right view: Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
// 		Example:
// 		Input: [1,2,3,null,5,null,4]
// 		Output: [1, 3, 4]
// 		Explanation:
// 		   1            <---
// 		 /   \
// 		2     3         <---
// 		 \     \
// 		  5     4       <---
// 		Approach: This can be solved either with DFS or BFS. With DFS, always traverse to the right child first and keep track of the level in the binary tree. Whenever you move to a level that you have not been to before, then that node is the first node on the right to be seen. 
// 		DFS:
Set < Integer > levelsDiscovered = new HashSet < >();
List < Node > rightNodes = new ArrayList < >();

public List < Node > helper(Node head) {
  levelsDiscovered.add(0);
  rightNodes.add(head);
  rightViewHelper(head.right, 1);
  rightViewHelper(head.left, 1);

  return rightNodes;

}

private void rightViewHelper(Node head, int level) {
  if (head == null) {
    return null;
  }

  if (!levelsDiscovered.contains(level)) {
    rightNodes.add(node);
    levelsDiscovered(level);
  }

  rightViewHelper(head.right, level + 1);
  rightViewHelper(head.left, level + 1)

}

// 		BFS: // TODO: do later
// 			Level order traversal:
public void levelOrderQueue(Node root) {
  Queue q = new LinkedList();
  int levelNodes = 0;
  if (root == null) return;
  q.add(root);
  while (!q.isEmpty()) {
    levelNodes = q.size();
    while (levelNodes > 0) {
      Node n = (Node) q.remove();
      System.out.print(" " + n.data);
      if (n.left != null) q.add(n.left);
      if (n.right != null) q.add(n.right);
      levelNodes--;
    }
    System.out.println("");
  }
}

// 	6. Number of islands:
// 		Given a 2d grid map of 1ith (land) and 0ith (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
// 		Example 1:
// 		Input:
// 		11110
// 		11010
// 		11000
// 		00000
// 		Output: 1
// 		Example 2:
// 		Input:
// 		11000
// 		11000
// 		00100
// 		00011
// 		Output: 3
// 		Can accomplish with DFS or BFS.
// 		BFS:
// 			Basically traverse through the entire array, and whenever you see a 1 (during traversal), start a BFS and increase the number_of_islands variable by one using a queue. Whenever you see a one, during the BFS, then add it to the queue and mark it with a 0 instead of a 1 (so we know its visited). 
public int numberOfIslands(char[][] map) {

  int numIslands = 0;
  int rows = map.length;
  int cols = map[0].length;
  for (int row = 0; row < rows; row++) {
    for (int col = 0; col < cols; coll++) {
      // if its a one then start BFS from here
      if (map[row][col] == 1) {++numIslands;
        map[row][col] = 0;
        // trick here is that we are storing the index of the 2d array as a 1d array index
        // this is accomplished by imagining the 2d array is one long 1d array
        // it is a long 1d array in the sense that the rows of the 2d array 
        // are concatenated one after the other
        // so to calculate a 1d index from a 2d index use this formula
        // 1dIndex = rowImOn * lengthOfRows + colImOn;
        Queue < Integer > q = new LinkedList < Integer > ();
        // add current position to the queue to start the BFS from their
        int position = row * rows + col;
        q.add(position)
        while (!q.isEmpty()) {
          int index2d = q.remove();
          int curRow = index2d / rows;
          int curCol = index2d % rows;
          // now check all 4 directions around this point
          // i.e.
          if (curRow - 1 > -1 && map[curRow - 1][curCol] == 1) {
            map[curRow - 1][curCol] = 0;
            q.add((curRow - 1) * rows + curCol);
          }
          // .. 
          // ..
          // .. And like so for the rest of the directions
        }
      }
    }

  }

  return numIslands;

}

// 		DFS:
class Solution {
  void dfs(char[][] grid, int r, int c) {
    int nr = grid.length;
    int nc = grid[0].length;

    if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == 0) {
      return;
    }

    grid[r][c] = 0;
    dfs(grid, r - 1, c);
    dfs(grid, r + 1, c);
    dfs(grid, r, c - 1);
    dfs(grid, r, c + 1);
  }

  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int nr = grid.length;
    int nc = grid[0].length;
    int num_islands = 0;
    for (int r = 0; r < nr; ++r) {
      for (int c = 0; c < nc; ++c) {
        if (grid[r][c] == 1) {++num_islands;
          dfs(grid, r, c);
        }
      }
    }

    return num_islands;
  }
}

// 	7. Lowest Common Ancestor of a Binary Tree: Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
// 		According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
// 		Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
// 		Example 1:
// 		Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
// 		Output: 3
// 		Explanation: The LCA of nodes 5 and 1 is 3.
// 		Example 2:
// 		Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
// 		Output: 5
// 		Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

// 		Note:
// 		All of the nodes values will be unique.
// 		p and q are different and both values will exist in the binary tree.
// 		Approach: Basically, traverse both the left and right subtree. If you are returned one of the given nodes, then that node where you were returned one of the desired nodes should return the node that was returned to it. If a node is returned both nodes, then that means it is the node that is the common ancester. Hereafter it will return itself. If a node does not receive any of the desired nodes, then it will simply return null.
class Solution {

  private TreeNode ans;

  public Solution() {
    // Variable to store LCA node.
    this.ans = null;
  }

  private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {

    // If reached the end of a branch, return false.
    if (currentNode == null) {
      return false;
    }

    // Left Recursion. If left recursion returns true, set left = 1 else 0
    int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;

    // Right Recursion
    int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;

    // If the current node is one of p or q
    int mid = (currentNode == p || currentNode == q) ? 1 : 0;

    // If any two of the flags left, right or mid become True
    if (mid + left + right >= 2) {
      this.ans = currentNode;
    }

    // Return true if any one of the three bool values is True.
    return (mid + left + right > 0);
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // Traverse the tree
    this.recurseTree(root, p, q);
    return this.ans;
  }
}

// 	8. Given a binary tree, return all root-to-leaf paths.
// 		Note: A leaf is a node with no children.
// 		Example:
// 		Input:
// 		   1
// 		 /   \
// 		2     3
// 		 \
// 		  5
// 		Output: ["1->2->5", "1->3"]
// 		Explanation: All root-to-leaf paths are: 1->2->5, 1->3
// 		Approach: for each node, we add it to the list, when we return from both left and right iterations, then we pop the element from the list. If a node is null, then we take all elements in the list and create a deep copy and add it to a new list which will be stored in a list of lists.
List < List < Nodes >> allPaths = new ArrayList < >();
public List < List < Nodes >> getAllRootPaths(Node head) {
  helper(head, new ArrayList < Node > ());
  return allPaths;
}

private void helper(Node node, List < Node > currentList) {
  if (node == null) {
    List < Node > temp = new ArrayList < Node > ();
    temp.addAll(currentList);
    allPaths.add(temp);
    return;
  }

  currentList.add(node);
  helper(node, currentList);
  helper(node, currentList);
  currentList.remove(node);
}

// 	9. Alien Dictionary: There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
// 		NOTE: I dont think this will be asked on phone
// 		Example 1:
// 		Input:
// 		[
// 		  "wrt",
// 		  "wrf",
// 		  "er",
// 		  "ett",
// 		  "rftt"
// 		]
// 		Output: "wertf"
// 		Example 2:
// 		Input:
// 		[
// 		  "z",
// 		  "x"
// 		]
// 		Output: "zx"
// 		Example 3:
// 		Input:
// 		[
// 		  "z",
// 		  "x",
// 		  "z"
// 		] 
// 			Output: "" 
// 		Explanation: The order is invalid, so return "". 
// 		Approach: There are two problems to this problem. First we need to find the orderings (i.e. which letter comes before which other letter). The end result of the orderings is a graph and a set of nodes. The other problem is that we need a way to go through the words to create the above mentioned orderings. In order to make an ordering between two letter (from a 2 different words); we need to have two words which are not the same (obviously). We need a set of words with the same prefix. Then the letters in the words after the prefix will give us the orderings (top to bottom). So we must collect words of the same prefix. The first most obvious collection of words is when we consider the zero prefix. From this we can deduce that all first letters are in order. We make a tree from this going from the bottom to top. Next, we scan the list of words, we prefix of size 1 (and the letter after the prefix of size one is not the same). The we look at this set of words and go from bottom to top to create a graph of dependency by looking at the 1th letter. We keep doing this for all prefix lengths. This will give us a bunch of dependency graphs.
// 		Then we start at any node, and do a depth first search. When we get to a node that doesnt have and neighbours, then we remove that node from the graphs and set and print it out. We continue this process until all nodes are completed.
// 		Here is a way to build a graph: goes through the list of words 2 at a time. When the first time we see a letter, that is not equal between the two words, we create a dependency.
private final int N = 26;
public String alienOrder(String[] words) {
  boolean[][] adj = new boolean[N][N];
  int[] visited = new int[N];
  buildGraph(words, adj, visited);

  StringBuilder sb = new StringBuilder();
  for (int i = 0; i < N; i++) {
    if (visited[i] == 0) { // unvisited
      if (!dfs(adj, visited, sb, i)) return "";
    }
  }
  return sb.reverse().toString();
}

public boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
  visited[i] = 1; // 1 = visiting
  for (int j = 0; j < N; j++) {
    if (adj[i][j]) { // connected
      if (visited[j] == 1) return false; // 1 => 1, cycle   
      if (visited[j] == 0) { // 0 = unvisited
        if (!dfs(adj, visited, sb, j)) return false;
      }
    }
  } // once you are done visiting all of the dependencies for a letter, then this char has been visited
  // now you mark it visited and add it to the result list
  visited[i] = 2; // 2 = visited
  sb.append((char)(i + a));
  return true;
}

public void buildGraph(String[] words, boolean[][] adj, int[] visited) {
  Arrays.fill(visited, -1); // -1 = not even existed
  for (int i = 0; i < words.length; i++) {
    for (char c: words[i].toCharArray()) visited[c - a] = 0;
    if (i > 0) {
      String w1 = words[i - 1],
      w2 = words[i];
      int len = Math.min(w1.length(), w2.length());
      for (int j = 0; j < len; j++) {
        char c1 = w1.charAt(j),
        c2 = w2.charAt(j);
        if (c1 != c2) {
          adj[c1 - a][c2 - a] = true;
          break;
        }
      }
    }
  }
}

// 	10. Shortest Distance from All Buildings: You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
// 		Each 0 marks an empty land which you can pass by freely.
// 		Each 1 marks a building which you cannot pass through.
// 		Each 2 marks an obstacle which you cannot pass through.
// 		Example:
// 		Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
// 		1 - 0 - 2 - 0 - 1
// 		|   |   |   |   |
// 		0 - 0 - 0 - 0 - 0
// 		|   |   |   |   |
// 		0 - 0 - 1 - 0 - 0
// 		Output: 7 
// 		Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
// 		             the point (1,2) is an ideal empty land to build a house, as the total 
// 		             travel distance of 3+3+1=7 is minimal. So return 7.
// 		Note:
// 		There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

// 		Approach: Inspired by previous solution.
// 			The main idea is the following:
// 			Traverse the matrix. For each building, use BFS to compute the shortest distance from each 0 to
// 			this building. After we do this for all the buildings, we can get the sum of shortest distance
// 			from every 0 to all reachable buildings. This value is stored
// 			in distance[][]. For example, if grid[2][2] == 0, distance[2][2] is the sum of shortest distance from this block to all reachable buildings.
// 			Time complexity: O(number of 1)O(number of 0) ~ O(m^2n^2)
// 			We also count how many building each 0 can be reached. It is stored in reach[][]. This can be done during the BFS. We also need to count how many total buildings are there in the matrix, which is stored in buildingNum.
// 			Finally, we can traverse the distance[][] matrix to get the point having shortest distance to all buildings. O(m*n)
// 			The total time complexity will be O(m^2*n^2), which is quite high!. Please let me know if I did the analysis wrong or you have better solution.
// 			The distance matrix will be updated each iteration of BFS (on each Building). This will in effect add the distance to from each building to the cell. At the end, each empty cell will hold the sum of all distances to it from each building.

// 		Solution from leet:
public class Solution {
  public int shortestDistance(int[][] grid) {
    if (grid == null || grid[0].length == 0) return 0;
    final int[] shift = new int[] {
      0,
      1,
      0,
      -1,
      0
    };

    int row = grid.length,
    col = grid[0].length;
    int[][] distance = new int[row][col];
    int[][] reach = new int[row][col];
    int buildingNum = 0;

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == 1) {
          buildingNum++;
          Queue < int[] > myQueue = new LinkedList < int[] > ();
          myQueue.offer(new int[] {
            i,
            j
          });

          // this is used to demarcate for each building, whether the BFS from this build has visited this position
          boolean[][] isVisited = new boolean[row][col];
          // BFS grows outward radially, level can be thought of as radial distance
          int level = 1;

          while (!myQueue.isEmpty()) {
            int qSize = myQueue.size();
            for (int q = 0; q < qSize; q++) {
              int[] curr = myQueue.poll();

              for (int k = 0; k < 4; k++) {
                int nextRow = curr[0] + shift[k];
                int nextCol = curr[1] + shift[k + 1];

                if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col && grid[nextRow][nextCol] == 0 && !isVisited[nextRow][nextCol]) {
                  //The shortest distance from [nextRow][nextCol] to thic building
                  // is level.
                  distance[nextRow][nextCol] += level;
                  reach[nextRow][nextCol]++;

                  isVisited[nextRow][nextCol] = true;
                  myQueue.offer(new int[] {
                    nextRow,
                    nextCol
                  });
                }
              }
            }
            level++;
          }
        }
      }
    }

    int shortest = Integer.MAX_VALUE;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
          shortest = Math.min(shortest, distance[i][j]);
        }
      }
    }

    return shortest == Integer.MAX_VALUE ? -1 : shortest;

  }
}

// 	11. Diameter of Binary Tree: Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
// 		Example:
// 		Given a binary tree 
// 		          1
// 		         / \
// 		        2   3
// 		       / \     
// 		      4   5    
// 		Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
// 		Note: The length of path between two nodes is represented by the number of edges between them.
// 		Approach: For each node, just get the number of nodes to the left and right and see if it is the greatest sum. 
class Solution {
  int ans;
  public int diameterOfBinaryTree(TreeNode root) {
    ans = 1;
    depth(root);
    return ans - 1;
  }
  public int depth(TreeNode node) {
    if (node == null) return 0;
    int L = depth(node.left);
    int R = depth(node.right);
    ans = Math.max(ans, L + R + 1);
    return Math.max(L, R) + 1;
  }
}

// 	12. Accounts Merge: Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
// 		Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
// 		After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
// 		Example 1:
// 		Input: 
// 		accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
// 		Output: [["John", john00@mail.com, john_newyork@mail.com, johnsmith@mail.com],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
// 		Explanation: 
// 		The first and third Johnith are the same person as they have the common email "johnsmith@mail.com".
// 		The second John and Mary are different people as none of their email addresses are used by other accounts.
// 		We could return these lists in any order, for example the answer [[Mary, mary@mail.com], [John, johnnybravo@mail.com], 
// 		[John, john00@mail.com, john_newyork@mail.com, johnsmith@mail.com]] would still be accepted.
// 		Note:
// 		The length of accounts will be in the range [1, 1000].
// 		The length of accounts[i] will be in the range [1, 10].
// 		The length of accounts[i][j] will be in the range [1, 30].
// 		Approach: Basically this is a disjoint set problem/ union find, but it can also be solved with dfs. The way that it works is this, we create a graph. Each item in the graph has a reference to its root node. The root node is the first email in an account. This can be implemented with a map<String, List<String>>. Every root node will have a reference to each other email in the account. So in effect, every email will have a reference to its node, and every root email will have references to all of the emails in its set. Now say we have something like this.
// 		[set1, emailA, emailB, emailC], [set3, emailX, emailY, emailZ], [set2, emailD, emailF, emailB] 
// 		So now we have a map that maps each email to a list of emails. A root email will map to all children emails. A non root email will map to its root email.
// 		Now for each item in the hasmap, perform a DFS (using a stack and marking as visited).
// 		Basically, every email will have an entry in the HashMap<String, List<String>>. If the email is a root email, it will have a reference to all emails in the account. If it is not an email, it will have a reference to just the root email in its account.
// 		Now, to combine email, all we have to do is go through each email in the HashMap, and perform a DFS starting from the current email. Every new email we encounter in the DFS, we will add it to the current set. When the DFS finishes (empty stack), we add all of the emails we accumulated as one group to a return set.
class Solution {
  public List < List < String >> accountsMerge(List < List < String >> accounts) {
    Map < String,
    String > emailToName = new HashMap();
    Map < String,
    ArrayList < String >> graph = new HashMap();
    for (List < String > account: accounts) {
      String name = "";
      for (String email: account) {
        if (name == "") {
          name = email;
          continue;
        }
        graph.computeIfAbsent(email, x ->new ArrayList < String > ()).add(account.get(1));
        graph.computeIfAbsent(account.get(1), x ->new ArrayList < String > ()).add(email);
        emailToName.put(email, name);
      }
    }

    Set < String > seen = new HashSet();
    List < List < String >> ans = new ArrayList();
    for (String email: graph.keySet()) {
      if (!seen.contains(email)) {
        seen.add(email);
        Stack < String > stack = new Stack();
        stack.push(email);
        List < String > component = new ArrayList();
        while (!stack.empty()) {
          String node = stack.pop();
          component.add(node);
          for (String nei: graph.get(node)) {
            if (!seen.contains(nei)) {
              seen.add(nei);
              stack.push(nei);
            }
          }
        }
        Collections.sort(component);
        component.add(0, emailToName.get(email));
        ans.add(component);
      }
    }
    return ans;
  }
}

// 	13. Convert Binary Search Tree to Sorted Doubly Linked List: Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
// 		We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
// 		Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.
// 		The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
// 		Approach: At each node we want to get the left subtree converted into a DLL and the right subtree converted to a DLL. and then we attach the end of the left to my left pointer and the beginning of the right to my right pointer.
public Node toDLL(Node head) {
  if (head == null) {
    return null;
  }

  Node leftLast = getLast(toDLL(head.left));
  Node rightFirst = getFirst(toDLL(head.right));

  head.left = leftLast;
  head.right = rightFirst;
  leftLast.right = head;
  rightFirst.left = head;

  return head;
}

// 		Solution from LeetCode:
// 		Approach: we only need to set the first pointer once, the first time we encounter a valid node i.e. last == null at that time. At each node we will make it the new last pointer node. We can do this because 
// 		To understand this code we must understand the abstraction that this is using. Namely, it is in-order traversal. The way that in order traversal works is that it starts from a root, a node, and visits the left tree. Only after it has completed visiting the left subtree for the current node, will it consider the current node done. i.e. it can be printed out. In our case, we would like to attach it to the end of a linked list.
// 		To attach it to the end of a linked list, we must have the last node of the linked list available. This is accomplished, by noting that whenever a nodeith left subtree is traversed, we can mark that node as the last node in the linked list
class Solution {
  // the smallest (first) and the largest (last) nodes
  Node first = null;
  Node last = null;

  public void helper(Node node) {
    if (node != null) {
      return;
    }
    // left
    helper(node.left);
    // node 
    if (last != null) {
      // link the previous node (last)
      // with the current one (node)
      last.right = node;
      node.left = last;
    }
    else {
      // keep the smallest node
      // to close DLL later on
      first = node;
    }
    last = node;
    // right
    helper(node.right);
  }

  public Node treeToDoublyList(Node root) {
    if (root == null) return null;

    helper(root);
    // close DLL
    last.right = first;
    first.left = last;
    return first;
  }
}

// if you are unable to understand this, then use the iterative method
public void inOrder(Node node) {
  Stack < Node > stack = new Stack < >();

  stack.push(node);
  Node current = node;
  Node beginning = null;
  Node end = null;

  while (!stack.isEmpty()) {
    // add all of its left descendants to the stack
    while (current != null) {
      stack.push(current.next());
      current = current.left;
    }

    // current should now be null, pop the last guy of the stack it is the next item to be deal in iteration
    Node last = stack.pop();

    // last can now be dealt with in whatever manner neccessary as it is its turn now
    // i.e. we will add it to the end of a linked list
    // add to end of linked list
    if (first == null) {
      first = last;
    } else {
      // add the item to the end of the linked list
      end.right = last;
      last.left = end;
    }

    current = last.right;
    end = last;
    end.right = beginning;
  }

}

// 	15. Given a binary tree, return the vertical order traversal of its nodes values. (ie, from top to bottom, column by column).
// 			If two nodes are in the same row and column, the order should be from left to right.
// 			Examples 1:
// 			Input: [3,9,20,null,null,15,7]
// 			3
// 			/\
// 			/  \
// 			9  20
// 				/\
// 			/  \
// 			15   7 
// 			Output:
// 			[
// 			[9],
// 			[3,15],
// 			[20],
// 			[7]
// 			]
// 			Examples 2:
// 			Input: [3,9,8,4,0,1,7]
// 				3
// 				/\
// 			/  \
// 			9   8
// 			/\  /\
// 			/  \/  \
// 			4  01   7 
// 			Output:
// 			[
// 			[4],
// 			[9],
// 			[3,0,1],
// 			[8],
// 			[7]
// 			]
// 			Examples 3:
// 			Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0ith right child is 2 and 1ith left child is 5)
// 				3
// 				/\
// 			/  \
// 			9   8
// 			/\  /\
// 			/  \/  \
// 			4  01   7
// 				/\
// 			/  \
// 			5   2
// 			Output:
// 			[
// 			[4],
// 			[9,5],
// 			[3,0,1],
// 			[8,2],
// 			[7]
// 			]
// 			Solution from leetcode:
// 				The following solution takes 5ms.
// 				BFS, put node, col into queue at the same time
// 				Every left child access col - 1 while right child col + 1
// 				This maps node into different col buckets
// 				Get col boundary min and max on the fly
// 				Retrieve result from cols
// 				Note that TreeMap version takes 9ms.
public List < List < Integer >> verticalOrder(TreeNode root) {
  List < List < Integer >> res = new ArrayList < >();
  if (root == null) {
    return res;
  }

  Map < Integer,
  ArrayList < Integer >> map = new HashMap < >();
  Queue < TreeNode > q = new LinkedList < >();
  Queue < Integer > cols = new LinkedList < >();

  q.add(root);
  cols.add(0);

  int min = 0;
  int max = 0;

  while (!q.isEmpty()) {
    TreeNode node = q.poll();
    int col = cols.poll();

    if (!map.containsKey(col)) {
      map.put(col, new ArrayList < Integer > ());
    }
    map.get(col).add(node.val);

    if (node.left != null) {
      q.add(node.left);
      cols.add(col - 1);
      min = Math.min(min, col - 1);
    }

    if (node.right != null) {
      q.add(node.right);
      cols.add(col + 1);
      max = Math.max(max, col + 1);
    }
  }

  for (int i = min; i <= max; i++) {
    res.add(map.get(i));
  }

  return res;
}

// can do this with bfs also
class Solution {

  // resulting map that maps column number to list of nodes in that column
  Map < Integer,
  List < Node >> res = new HashMap < >();
  public void columns(Node head) {
    helper(head, 0);
  }

  private void helper(Node node, int col) {
    if (node == null) {
      return;
    }

    if (res.get(col) == null) {
      res.add(col, new ArrayList < Node > ());
    }
    res.get(col).add(node);

    helper(node.left, --col);
    helper(node.right, ++col);
  }
}

// 	16. Is Graph Bipartite?
// 			Solution
// 			Given an undirected graph, return true if and only if it is bipartite.
// 			Recall that a graph is bipartite if we can split its set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
// 			The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesnt contain any element twice.
// 			Example 1:
// 			Input: [[1,3], [0,2], [1,3], [0,2]]
// 				i.e. node 0 has an edge from 0 to 1 and an edge from 0 to 3
// 					 node 1 has an edge from 1 to 0 and an edge from 1 to 2
// 			Output: true
// 			Explanation: 
// 			The graph looks like this:
// 			0----1
// 			|    |
// 			|    |
// 			3----2
// 			We can divide the vertices into two groups: {0, 2} and {1, 3}.
// 			Example 2:
// 			Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
// 			Output: false
// 			Explanation: 
// 			The graph looks like this:
// 			0----1
// 			| \  |
// 			|  \ |
// 			3----2
// 			We cannot find a way to divide the set of nodes into two independent subsets.

// 			Note:
// 			graph will have length in range [1, 100].
// 			graph[i] will contain integers in range [0, graph.length - 1].
// 			graph[i] will not contain i or duplicate values.
// 			The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
// 			Answer:
// 				Approach:
// 					Use the vertex colouring algorithm:
// 					1) Colour any one of your vertices red.
// 					2) Identify all uncoloured vertices that are adjacent to a red vertex. Colour them blue.
// 					3) Identify all uncoloured vertices that are adjacent to a blue vertex. Colour them red.
// 					4) Repeat steps 2 and 3 until all the vertices are coloured red or blue.
// 					5) If there are any two vertices adjacent of the same colour, then your graph is not bipartite, otherwise it is bipartite.
// 					6) If the graph is bipartite, the colouring algorithm will have created the two required sets of points (one red and one blue).
// 					Our goal is trying to use two colors to color the graph and see if there are any adjacent nodes having the same color.
// 					Initialize a color[] array for each node. Here are three states for colors[] array:
// 					0: Havent been colored yet.
// 					1: Blue.
// 					-1: Red.
// 					For each node,
// 					If it hasnt been colored, use a color to color it. Then use the other color to color all its adjacent nodes (DFS).
// 					If it has been colored, check if the current color is the same as the color that is going to be used to color it. (Please forgive my english... Hope you can understand it.)
// 					Why does this work?
// 						- lets think about the abstraction
// 						- we start at a random node i and colour it blue. then we look at all of its neighbours. for each neighbour, if it is uncoloured, we mark its colour as the opposite of the current colour (i.e. red in this case). If the neighour is already coloured, we make sure that it is red, if its not, then we return false. we do a depth first search in this manner and make sure we iterate over all nodes since the graph could be disconnected.
// 				Code:
// 					DFS:
class Solution {
  public boolean isBipartite(int[][] graph) {
    int n = graph.length;
    int[] colors = new int[n];

    for (int i = 0; i < n; i++) { //This graph might be a disconnected graph. So check each unvisited node.
      if (colors[i] == 0 && !validColor(graph, colors, 1, i)) { // ith node has not been //visited, mark it blue and start DFS at it ( it hasn't been visited since its 
        // dosconnected, or its the first node)
        return false;
      }
    }
    return true;
  }

  public boolean validColor(int[][] graph, int[] colors, int color, int node) {
    if (colors[node] != 0) {
      return colors[node] == color;
    }
    colors[node] = color;
    for (int next: graph[node]) {
      if (!validColor(graph, colors, -color, next)) {
        return false;
      }
    }
    return true;
  }
}
BFS: class Solution {
  public boolean isBipartite(int[][] graph) {
    int len = graph.length;
    int[] colors = new int[len];

    for (int i = 0; i < len; i++) {
      if (colors[i] != 0) continue;
      Queue < Integer > queue = new LinkedList < >();
      queue.offer(i);
      colors[i] = 1; // Blue: 1; Red: -1.
      while (!queue.isEmpty()) {
        int cur = queue.poll();
        for (int next: graph[cur]) {
          if (colors[next] == 0) { // If this node hasn't been colored;
            colors[next] = -colors[cur]; // Color it with a different color;
            queue.offer(next);
          } else if (colors[next] != -colors[cur]) { // If it is colored and its color is different, return false;
            return false;
          }
        }
      }
    }

    return true;
  }
}

// Recursion:
// 	1. Letter Combinations of a Phone Number
// 		Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
// 		A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
// 		Example:
// 		Input: "23"
// 		Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 		Note:
// 		Although the above answer is in lexicographical order, your answer could be in any order you want.
// 		Approach: store a mapping of numbers to letters (Map<Integer, List<Char>>). perform peurmutation, DFS. 
// 			Map<Chracter, List<Character>> map = new HashMap<>()
// 			// init the map before running algo below
public void permuteNumbers(String num, String current) {
  if (str == null || str.equals("")) {
    print(current)
    return;
  }

  char c = num.chartAt(0);

  // for each character that this number maps to, we will permute a string
  for (Char a: phoneMap.get(c)) {
    //current.append(a);
    permuteNumbers(num.substring(1), current + a);
  }
}

Solution from leetCode: class Solution {
  Map < String,
  String > phone = new HashMap < String,
  String > () {
    {
      put("2", "abc");
      put("3", "def");
      put("4", "ghi");
      put("5", "jkl");
      put("6", "mno");
      put("7", "pqrs");
      put("8", "tuv");
      put("9", "wxyz");
    }
  };

  List < String > output = new ArrayList < String > ();

  public void backtrack(String combination, String next_digits) {
    // if there is no more digits to check
    if (next_digits.length() == 0) {
      // the combination is done
      output.add(combination);
    }
    // if there are still digits to check
    else {
      // iterate over all letters which map 
      // the next available digit
      String digit = next_digits.substring(0, 1);
      String letters = phone.get(digit);
      for (int i = 0; i < letters.length(); i++) {
        String letter = phone.get(digit).substring(i, i + 1);
        // append the current letter to the combination
        // and proceed to the next digits
        backtrack(combination + letter, next_digits.substring(1));
      }
    }
  }

  public List < String > letterCombinations(String digits) {
    if (digits.length() != 0) backtrack("", digits);
    return output;
  }
}

// 	2. Permutations
// 		Given a collection of distinct integers, return all possible permutations.
// 		Example:
// 		Input: [1,2,3]
// 		Output:
// 		[
// 		  [1,2,3],
// 		  [1,3,2],
// 		  [2,1,3],
// 		  [2,3,1],
// 		  [3,1,2],
// 		  [3,2,1]
// 		]
// 		Approach: for each position(recurring for subsequent positions), swap each element (by iterating over all elements after the position) in the array with the element at the position. Every time a recurrance is done, reverse the previous swap
public void permute(int[] nums, int position) {
  if (position >= nums.length) {
    // just print out the current state of the nums array
    for (int i: nums) {
      System.out.println(i + ",");
      return;
    }

    // swap every item with the current position
    for (int i = 0; i < nums.length; i++) {
      Collections.swap(nums, position, i);
      permute(nums, ++position);
      Collections.swap(nums, i, position]);
    }
  }
}
// 		From leet code:
// 		    // this method is better because it is in-place
// 		    // the way that this works is, iterating over the positions and swapping positions with every index after the current position
// 		    // every recurse will have look at a new position. Each recurse will swap with all elements after it.
// 		    // why dont we look at the elements before the position? i.e. each time we recurse, we iterate over less elements
// 		    // why? dont we need to put each number at each position? why arent we considering numbers prior for current position?
// 		    // this is because (consider the last position). Each recurse iterates till the last position.
// 		    // this means each item at some point gets swapped with the last position.
// 		    // this means that each number already shows up in the last position
class Solution {
  public void backtrack(int n, ArrayList < Integer > nums, List < List < Integer >> output, int first) {
    // if all integers are used up
    if (first == n) output.add(new ArrayList < Integer > (nums));
    for (int i = first; i < n; i++) {
      // place i-th integer first 
      // in the current permutation
      Collections.swap(nums, first, i);
      // use next integers to complete the permutations
      backtrack(n, nums, output, first + 1);
      // backtrack
      Collections.swap(nums, first, i);
    }
  }

  public List < List < Integer >> permute(int[] nums) {
    // init output list
    List < List < Integer >> output = new LinkedList();

    // convert nums into list since the output is a list of lists
    ArrayList < Integer > nums_lst = new ArrayList < Integer > ();
    for (int num: nums)
    nums_lst.add(num);

    int n = nums.length;
    backtrack(n, nums_lst, output, 0);
    return output;
  }
}

// 	3. Permutations II
// 		Given a collection of numbers that might contain duplicates, return all possible unique permutations.
// 		Example:
// 		Input: [1,1,2]
// 		Output:
// 		[
// 		  [1,1,2],
// 		  [1,2,1],
// 		  [2,1,1]
// 		]
// 		Approach: use a set
// 	4.  Remove Invalid Parentheses
// 		Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
// 		Note: The input string may contain letters other than the parentheses ( and ).
// 		Example 1:
// 		Input: "()())()"
// 		Output: ["()()()", "(())()"]
// 		Example 2:
// 		Input: "(a)())()"
// 		Output: ["(a)()()", "(a())()"]
// 		Example 3:
// 		Input: )(
// 		Output: [""]
// 		Approach: try keeping and removing each bracket then verifying if everything is fine. An optimization: However, for a closing bracket, if we decide to keep it as a part of our final expression (remember for every bracket we have two options, either to keep it or to remove it and recurse further) and there is no corresponding opening bracket to match it in the expression till now, then it will definitely lead to an invalid expression no matter what we do afterwards.
class Solution {

  private Set < String > validExpressions = new HashSet < String > ();
  private int minimumRemoved;

  private void reset() {
    this.validExpressions.clear();
    this.minimumRemoved = Integer.MAX_VALUE;
  }

  private void recurse(
  String s, int index, int leftCount, int rightCount, StringBuilder expression, int removedCount) {

    // If we have reached the end of string.
    if (index == s.length()) {

      // If the current expression is valid.
      if (leftCount == rightCount) {

        // If the current count of removed parentheses is <= the current minimum count
        if (removedCount <= this.minimumRemoved) {

          // Convert StringBuilder to a String. This is an expensive operation.
          // So we only perform this when needed.
          String possibleAnswer = expression.toString();

          // If the current count beats the overall minimum we have till now
          if (removedCount < this.minimumRemoved) {
            this.validExpressions.clear();
            this.minimumRemoved = removedCount;
          }
          this.validExpressions.add(possibleAnswer);
        }
      }
    } else {

      char currentCharacter = s.charAt(index);
      int length = expression.length();

      // If the current character is neither an opening bracket nor a closing one,
      // simply recurse further by adding it to the expression StringBuilder
      if (currentCharacter != ( && currentCharacter != )) {
        expression.append(currentCharacter);
        this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
        expression.deleteCharAt(length);
      } else {

        // Recursion where we delete the current character and move forward
        this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
        expression.append(currentCharacter);

        // If its an opening parenthesis, consider it and recurse
        if (currentCharacter == () {
          this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
        } else if (rightCount < leftCount) {
          // For a closing parenthesis, only recurse if right < left
          this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
        }

        // Undoing the append operation for other recursions.
        expression.deleteCharAt(length);
      }
    }
  }

  public List < String > removeInvalidParentheses(String s) {

    this.reset();
    this.recurse(s, 0, 0, 0, new StringBuilder(), 0);
    return new ArrayList(this.validExpressions);
  }
}

// 	5.  Regular Expression Matching
// 		Given an input string (s) and a pattern (p), implement regular expression matching with support for . and *.
// 		. Matches any single character.
// 		* Matches zero or more of the preceding element.
// 		The matching should cover the entire input string (not partial).
// 		Note:
// 		s could be empty and contains only lowercase letters a-z.
// 		p could be empty and contains only lowercase letters a-z, and characters like . or *.
// 		Example 1:
// 		Input:
// 		s = "aa"
// 		p = "a"
// 		Output: false
// 		Explanation: "a" does not match the entire string "aa".
// 		Example 2:
// 		Input:
// 		s = "aa"
// 		p = "a*"
// 		Output: true
// 		Explanation: * means zero or more of the preceding element, a. Therefore, by repeating a once, it becomes "aa".
// 		Example 3:
// 		Input:
// 		s = "ab"
// 		p = ".*"
// 		Output: true
// 		Explanation: ".*" means "zero or more (*) of any character (.)".
// 		Example 4:
// 		Input:
// 		s = "aab"
// 		p = "c*a*b"
// 		Output: true
// 		Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
// 		Example 5:
// 		Input:
// 		s = "mississippi"
// 		p = "mis*is*p*."
// 		Output: false
// 		Solution from LeetCode:
class Solution {
  public boolean isMatch(String text, String pattern) {
    if (pattern.isEmpty()) return text.isEmpty();
    boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == .));

    if (pattern.length() >= 2 && pattern.charAt(1) == *) {
      return (isMatch(text, pattern.substring(2)) || (first_match && isMatch(text.substring(1), pattern)));
    } else {
      return first_match && isMatch(text.substring(1), pattern.substring(1));
    }
  }
}

// 		Leet code DP solution:
enum Result {
  TRUE,
  FALSE
}

class Solution {
  Result[][] memo;

  public boolean isMatch(String text, String pattern) {
    memo = new Result[text.length() + 1][pattern.length() + 1];
    return dp(0, 0, text, pattern);
  }

  public boolean dp(int i, int j, String text, String pattern) {
    if (memo[i][j] != null) {
      return memo[i][j] == Result.TRUE;
    }
    boolean ans;
    if (j == pattern.length()) {
      ans = i == text.length();
    } else {
      boolean first_match = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == .));

      if (j + 1 < pattern.length() && pattern.charAt(j + 1) == *) {
        ans = (dp(i, j + 2, text, pattern) || first_match && dp(i + 1, j, text, pattern));
      } else {
        ans = first_match && dp(i + 1, j + 1, text, pattern);
      }
    }
    memo[i][j] = ans ? Result.TRUE: Result.FALSE;
    return ans;
  }
}

// 	6.  Subsets
// 			Given a set of distinct integers, nums, return all possible subsets (the power set).
// 			Note: The solution set must not contain duplicate subsets.
// 			Example:
// 			Input: nums = [1,2,3]
// 			Output:
// 			[
// 			  [3],
// 			  [1],
// 			  [2],
// 			  [1,2,3],
// 			  [1,3],
// 			  [2,3],
// 			  [1,2],
// 			  []
// 			]
// 			Approach:
// 				Recursive:
public List < List < Integer >> subsets(int[] arr, List < Integer > current, int index) {
  if (index == arr.length) {
    // copy all items from current into a list and add them to the return list
  }
  // either include the item or not
  subsets(arr, current.add(0, arr[i]), i + 1);
  current.remove(0)
  subsets(arr, current, i + 1);

}

// 				Iterative with Queue:
// add empty item (empty list) to queue
// for each item in arr, we pop each item in the queue, then we do two things
// add the current item (from the array) to the item we go from the queue and push it back into the queue
// then, push back the original item we got from the queue back into the queue
// we actually need a temporary queue
pudlic void subsets(int[] arr) {
  Queue < Integer > q = new LinkedList < >();

  List < Integer > empty = new ArrayList < >();
  q.push();
  for (int i = 0; i < arr.length; i++) {

    // we need to pair the current item with everything in the queue
    Queue < Integer > temp = new LinkedList < >();
    while (!q.isEmpty()) {
      List < Integer > item = q.remove();
      temp.add(item.add(arr[i]));
      temp.add
    }

    // now pair the item with an item from the arr
    item.add()
  }
}

// see solution in intellij, above is wrong
// 	7. Strobogrammatic Number II
// 		A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
// 		Find all strobogrammatic numbers that are of length = n.
// 		Example:
// 		Input:  n = 2
// 		Output: ["11","69","88","96"]
// 		Solution: To solve this problem we must understand it first as it is not simple. The result is a set of strings that are of size n. Notice that a number can be Strobogrammatic if it only consists of 1,6,9 and 8 (alos zero but only if there is 1 element required, or an odd element required). Also, the number must be palindromic (with the addition that 6 and 9 can be treated equal). i.e. the number read forward is the same as being read backwards. So in order to create such a palindrome with the required set of numbers and the required size, we add two numbers at a time (at the front and back). The numbers must be the same or they can be 6,9 pairs. We can recursively build each number by adding on to prevvious set of generated numbers. We will keep adding two at a time until the string is the desired size. In the case the required size is 1 we will return 0,8 and 1. In the case the number is add we add one sized string into the list of strings to build upon, i.w. 0,8,1
public List < String > findStrobogrammatic(int n) {
  return helper(n, n);
}

List < String > helper(int n, int m) {
  if (n == 0) return new ArrayList < String > (Arrays.asList(""));
  if (n == 1) return new ArrayList < String > (Arrays.asList("0", "1", "8"));

  List < String > list = helper(n - 2, m);

  List < String > res = new ArrayList < String > ();

  for (int i = 0; i < list.size(); i++) {
    String s = list.get(i);

    if (n != m) res.add("0" + s + "0");

    res.add("1" + s + "1");
    res.add("6" + s + "9");
    res.add("8" + s + "8");
    res.add("9" + s + "6");
  }

  return res;
}

// Dynamic Programming:
// 	1. Longest Palindromic Substring
// 		Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
// 		Example 1:
// 		Input: "babad"
// 		Output: "bab"
// 		Note: "aba" is also a valid answer.
// 		Example 2:
// 		Input: "cbbd"
// 		Output: "bb"
// 			Solution:
// 			dp(i, j) represents whether s(i ... j) can form a palindromic substring, dp(i, j) is true when s(i) equals to s(j) and s(i+1 ... j-1) is a palindromic substring. When we found a palindrome, check if its the longest one. Time complexity O(n^2).
// 			Explanatoin of code: start at the end of the string -1 = i, and j = i. iterate j till the end of the string. i will grow backwards towards the beginning of the string. this way, when we want to verify if a substring is a valid palindrome, all we have to do is look at our values we already computed in dp. we keep track of the greatest palindrome length.
public String longestPalindrome(String s) {
  int n = s.length();
  String res = null;

  boolean[][] dp = new boolean[n][n];

  for (int i = n - 1; i >= 0; i--) {
    for (int j = i; j < n; j++) {
      dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

      if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
        res = s.substring(i, j + 1);
      }
    }
  }

  return res;
}

// 			Approach two:
// 				another way to do it is to consider every item, or every consecutive pair the middle of a possible palindromic sequence. then grow each middle till you hit a non-palindromic sequence.
public String longestPalindrome(String s) {
  if (s == null || s.length() < 1) return "";
  int start = 0,
  end = 0;
  for (int i = 0; i < s.length(); i++) {
    int len1 = expandAroundCenter(s, i, i);
    int len2 = expandAroundCenter(s, i, i + 1);
    int len = Math.max(len1, len2);
    if (len > end - start) {
      start = i - (len - 1) / 2;
      end = i + len / 2;
    }
  }
  return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
  int L = left,
  R = right;
  while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
    L--;
    R++;
  }
  return R - L - 1;
}

// 	2. Longest Valid Parentheses
// 		Given a string containing just the characters ( and ), find the length of the longest valid (well-formed) parentheses substring.
// 		Example 1:
// 		Input: "(()"
// 		Output: 2
// 		Explanation: The longest valid parentheses substring is "()"
// 		Example 2:
// 		Input: ")()())"
// 		Output: 4
// 		Explanation: The longest valid parentheses substring is "()()"
// 		Solution Explanation:
// 			This problem can be solved by using Dynamic Programming. We make use of a dp array where iith element of dp represents the length of the longest valid substring ending at iith index. We initialize the complete dp array with 0ith. Now, its obvious that the valid substrings must end with ‘)’. This further leads to the conclusion that the substrings ending with ‘(’ will always contain 0 at their corresponding dp indices. Thus, we update the dp array only when ‘)’ is encountered.
// 		To fill dp array we will check every two consecutive characters of the string and if
// 		s[i] = ‘)’ and s[i - 1] = ‘(’, i.e. string looks like "....()"
// 		dp[i]=dp[i−2]+2
// 		We do so because the ending "()" portion is a valid substring anyhow and leads to an increment of 2 in the length of the just previous valid substringith length.
// 		s[i]=‘)’ and s[i−1]=‘)’, i.e. string looks like .......))
// 		if s[i−dp[i−1]−1]=‘(’ then
// 		dp[i]=dp[i-1] + dp[i - dp[i-1] - 2] + 2
// 		The reason behind this is that if the 2nd last ‘)’ was a part of a valid substring (say sub_ssub_s), for the last ‘)’ to be a part of a larger substring, there must be a corresponding starting ‘(’ which lies before the valid substring of which the 2nd last ‘)’ is a part (i.e. before sub_ssub_s ). Thus, if the character before sub_ssub_s happens to be ‘(’, we update the dp[i] as an addition 2 in the length of sub_ssub_s which is dp[i−1]. To this, we also add the length of the valid substring just before the term "(,sub_s,)" , i.e. dp[i−dp[i−1]−2].
public class Solution {
  public int longestValidParentheses(String s) {
    int maxans = 0;
    int dp[] = new int[s.length()];
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) == )) {
        if (s.charAt(i - 1) == () {
          dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
        } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == () {
          dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
        }
        maxans = Math.max(maxans, dp[i]);
      }
    }
    return maxans;
  }
}

// 		Another approach is to use a stack:
// 			Explanation:
// 				Instead of finding every possible string and checking its validity, we can make use of stack while scanning the given string to check if the string scanned so far is valid, and also the length of the longest valid string. In order to do so, we start by pushing −1 onto the stack.
// 				For every ‘(’ encountered, we push its index onto the stack.
// 				For every ‘)’ encountered, we pop the topmost element and subtract the current elementith index from the top element of the stack, which gives the length of the currently encountered valid string of parentheses. If while popping the element, the stack becomes empty, we push the current elementith index onto the stack. In this way, we keep on calculating the lengths of the valid substrings, and return the length of the longest valid string at the end.
// 				Simpler terms: basically, intilize the stack with a -1. then, for each bracket, add its index to the stack. If the current bracket is a closing bracket, then check what type of bracket is on the top of the stack. If it is an opening bracket, then pop it off, then subtract look at what is now on the top of the stack. Subtract the current index with the index at the top of the stack (compare this for max).
// go over this later
public class Solution {

  public int longestValidParentheses(String s) {
    int maxans = 0;
    Stack < Integer > stack = new Stack < >();
    stack.push( - 1);
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == () {
        stack.push(i);
      } else {
        stack.pop();
        if (stack.empty()) {
          stack.push(i);
        } else {
          maxans = Math.max(maxans, i - stack.peek());
        }
      }
    }
    return maxans;
  }
}

public int longestValidParanthesis(String parens) {
  Stack < integer > stack = new Stack < >();
  stack.push( - 1);
  int max = Integer.MIN_VALUE;
  for (int i = 0; i < parens.length; i++) {
    if (parens.charAt(i) == ()) {
      stack.push(i);
    } else {
      // check the last item on the stack if it is an open bracket, then pop if it is
      if (parens.charAt(stack.peek()) == )) {
        // pop it off, 
        stack.pop();
        int last = stack.peek();
        max = Math.max(max, i - last);
      } else {
        stack.push(i);
      }
    }
  }
}

// 	3. Decode Ways
// 		A message containing letters from A-Z is being encoded to numbers using the following mapping:
// 		A -> 1
// 		B -> 2
// 		...
// 		Z -> 26
// 		Given a non-empty string containing only digits, determine the total number of ways to decode it.
// 		Example 1:
// 		Input: "12"
// 		Output: 2
// 		Explanation: It could be decoded as "AB" (1 2) or "L" (12).
// 		Example 2:
// 		Input: "226"
// 		Output: 3
// 		Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
// 		I used a dp array of size n + 1 to save subproblem solutions. dp[0] means an empty string will have one way to decode, dp[1] means the way to decode a string of size 1. I then check one digit and two digit combination and save the results along the way. In the end, dp[n] will be the end result.
// 		- very similar to fibonachi	
public class Solution {
  public int numDecodings(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int n = s.length();
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = s.charAt(0) != 0 ? 1 : 0;
    for (int i = 2; i <= n; i++) {
      int first = Integer.valueOf(s.substring(i - 1, i));
      int second = Integer.valueOf(s.substring(i - 2, i));
      if (first >= 1 && first <= 9) {
        dp[i] += dp[i - 1];
      }
      if (second >= 10 && second <= 26) {
        dp[i] += dp[i - 2];
      }
    }
    return dp[n];
  }
}

// 	4. Best Time to Buy and Sell Stock
// 		Say you have an array for which the ith element is the price of a given stock on day i.
// 		If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
// 		Note that you cannot sell a stock before you buy one.
// 		Example 1:
// 		Input: [7,1,5,3,6,4]
// 		Output: 5
// 		Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
// 		             Not 7-1 = 6, as selling price needs to be larger than buying price.
// 		Example 2:
// 		Input: [7,6,4,3,1]
// 		Output: 0
// 		Explanation: In this case, no transaction is done, i.e. max profit = 0.
// 		Solution:
// 			The points of interest are the peaks and valleys in the given graph. We need to find the largest peak following the smallest valley. We can maintain two variables - minprice and maxprofit corresponding to the smallest valley and maximum profit (maximum difference between selling price and minprice) obtained so far respectively.
public class Solution {
  public int maxProfit(int prices[]) {
    int minprice = Integer.MAX_VALUE;
    int maxprofit = 0;
    for (int i = 0; i < prices.length; i++) {
      if (prices[i] < minprice) minprice = prices[i];
      else if (prices[i] - minprice > maxprofit) maxprofit = prices[i] - minprice;
    }
    return maxprofit;
  }
}

// 	5. Word Break
// 		Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
// 		Note:
// 		The same word in the dictionary may be reused multiple times in the segmentation.
// 		You may assume the dictionary does not contain duplicate words.
// 		Example 1:
// 		Input: s = "leetcode", wordDict = ["leet", "code"]
// 		Output: true
// 		Explanation: Return true because "leetcode" can be segmented as "leet code".
// 		Example 2:
// 		Input: s = "applepenapple", wordDict = ["apple", "pen"]
// 		Output: true
// 		Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
// 		             Note that you are allowed to reuse a dictionary word.
// 		Example 3:
// 		Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
// 		Output: false
// 		My try: the brute force recusrion for each stack trace would be to start at the beginning index of string as given by method, then iterate for each end point upto end of the string. For each end point in the iteration, check if the substring from the beginning index to the ending index is a valid word. if it is, then recurse and pass the index after the last index as the beginning of the new recursion.
// 		The dynamic programming approach would be to start from the end of the string and iteratetowards the beginning of the string. For each character, check if it is a valid word, and also check (by iterating over all characters till the end of the string) if it is the beginning of a substring. for each end point, all you need to check is if the substring is a valid word and if dp[endindex + 1] also has a valid word breaking
// 		Solution from LeetCode:
// 			- basically what happens here is that similar to above 2nd paragraph, except we start from the beginning of the string. i will iterate through the string and act as the end point of the string at each pf its iteration. j will iterate inside of i from the beginning of the string (0) uptill i. at each iteration of j, we basically check if dp[0-j] is valid AND if substring(0,j). If dp[0-j] is valid then that means that there is some combination of word break from 0-j is valid such that each word is in the dictionary. Then we set dp[i] to true. dp[i] basically means string(0,1) has a valid word breaking.
public class Solution {
  public boolean wordBreak(String s, List < String > wordDict) {
    Set < String > wordDictSet = new HashSet(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[s.length()];
  }
}

// 	6. Range Sum Query 2D - Immutable
// 		Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
// 		Range Sum Query 2D
// 		The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
// 		Example:
// 		Given matrix = [
// 		  [3, 0, 1, 4, 2],
// 		  [5, 6, 3, 2, 1],
// 		  [1, 2, 0, 1, 5],
// 		  [4, 1, 0, 1, 7],
// 		  [1, 0, 3, 0, 5]
// 		]
// 		sumRegion(2, 1, 4, 3) -> 8
// 		sumRegion(1, 1, 2, 2) -> 11
// 		sumRegion(1, 2, 2, 4) -> 12
// 		Note:
// 		You may assume that the matrix does not change.
// 		There are many calls to sumRegion function.
// 		You may assume that row1 ≤ row2 and col1 ≤ col2.
// 		Approach: there are two ways to go about doing this. one way is to store the running sum of each row. Then iterate from the first row of the desired submatrix to the last row of the desired submatrix. At each iteration we calculate the
// 		i.e. here is how to get the running sum of each row:
class NumArray {

  int[] runningSumCache;

  public NumArray(int[] nums) {

    runningSumCache = new int[nums.length + 1];

    for (int i = 0; i < nums.length; i++) {
      runningSumCache[i + 1] += nums[i] + runningSumCache[i];
    }

  }

  public int sumRange(int i, int j) {
    return runningSumCache[j + 1] - runningSumCache[i];
  }

}

// and her is complete solution from leetcode
private int[][] dp;

public NumMatrix(int[][] matrix) {
  if (matrix.length == 0 || matrix[0].length == 0) return;
  dp = new int[matrix.length][matrix[0].length + 1];
  for (int r = 0; r < matrix.length; r++) {
    for (int c = 0; c < matrix[0].length; c++) {
      dp[r][c + 1] = dp[r][c] + matrix[r][c];
    }
  }
}

public int sumRegion(int row1, int col1, int row2, int col2) {
  int sum = 0;
  for (int row = row1; row <= row2; row++) {
    sum += dp[row][col2 + 1] - dp[row][col1];
  }
  return sum;
}

// the other method is a little bit more clever.
// for this, we keep the running sum for each index. the running sum for each index basically translates
// into the running sum from the top left of the matrix all the way to the current index i.e. to fille this kind of array, we will iterate through the matrix, the total sum to this current point will be
// dp[r][c] = dp[r][c-1] + dp[r-1][c] + matrix[r][c] - dp[r - 1][c - 1]
// here we add the value at the current point plus the cumulative sum above and to the left of the current index. Since we added the cumulative sum above and below notice that we have an extra amount we are adding which is common to both cumulative sums. Therefore we need to subtract that amount once as it was added twice. It is the cumulative sum to the left digonal up from the current index.
// i.e this is the dp[r - 1][c - 1] term in the above sum
// now we have a cumulative sum array for the matrix, where mat[row][col] represents the bottom right of the rectangular cumulative sum
// how do we calculate for a specific submatrix?
// we will need to subtract everything above the top row and every to the left of the left most column
// then since we subtract a common portion twice, we have to add that back in. that is namely the cumulative sum left diagonal to the top right of the matrix
// here is the code
private int[][] dp;

public NumMatrix(int[][] matrix) {
  if (matrix.length == 0 || matrix[0].length == 0) return;
  dp = new int[matrix.length + 1][matrix[0].length + 1];
  for (int r = 0; r < matrix.length; r++) {
    for (int c = 0; c < matrix[0].length; c++) {
      dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
    }
  }
}

public int sumRegion(int row1, int col1, int row2, int col2) {
  return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
}

// 	7. Continuous Subarray Sum
// 		Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
// 		Example 1:
// 		Input: [23, 2, 4, 6, 7],  k=6
// 		Output: True
// 		Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
// 		Example 2:
// 		Input: [23, 2, 6, 4, 7],  k=6
// 		Output: True
// 		Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.

// 		Note:
// 		The length of the array wont exceed 10,000.
// 		You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
// 		Approach:
// 			In this solution, we make use of a HashMap that is used to store the cumulative sums upto the ith index after some processing along with the index ii. The processing done is taking the modulus of the the sum upto the ith index with the given kk. The reasoning behind this will become clear soon.
// 			We traverse over the given array, and keep on calculating the sum%ksum values upto the current index. Whenever we find a new sum%ksum value, which isnt present in the HashMap already, we make an entry in the HashMap of the form, (sum%k, i)(sum.
// 			Now, assume that the given sum%ksum value at the ith index be equal to rem. Now, if any subarray follows the ith element, which has a sum equal to the integer multiple of kk, say extending upto the jth index, the sum value to be stored in the HashMap for the jth index will be: (rem + n*k)%k(rem+n∗k), where nn is some integer > 0. We can observe that (rem + n*k)%k = rem(rem+n∗k), which is the same value as stored corresponding to the ith index.
// 			From this observation, we come to the conclusion that whenever the same sum%ksum value is obtained corresponding to two indices ii and jj, it implies that sum of elements betweeen those indices is an integer multiple of kk. Thus, if the same sum%ksum value is encountered again during the traversal, we return a True directly.
public class Solution {
  public boolean checkSubarraySum(int[] nums, int k) {
    int sum = 0;
    HashMap < Integer,
    Integer > map = new HashMap < >();
    map.put(0, -1);
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (k != 0) sum = sum % k;
      if (map.containsKey(sum)) {
        if (i - map.get(sum) > 1) return true;
      } else map.put(sum, i);
    }
    return false;
  }
}

// Searching and sorting
// 	1. Divide Two Integers
// 		i.e. 20 / 2 = 10
// 			in this example, 20 is the dividend, 2 is the divisor and 10 is the quotient
// 		Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
// 		Return the quotient after dividing dividend by divisor.
// 		The integer division should truncate toward zero.
// 		Example 1:
// 		Input: dividend = 10, divisor = 3
// 		Output: 3
// 		Example 2:
// 		Input: dividend = 7, divisor = -3
// 		Output: -2
// 		Note:
// 		Both dividend and divisor will be 32-bit signed integers.
// 		The divisor will never be 0.
// 		Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
// 		Solution:
// 			We eastablish variables sum and quotient with the invariant quotient * divisor = sum.
// 			Take dividend = 20, divisor = 3 for example,
// 			sum  quotient  target
// 			3     1        17
// 			6     2        14
// 			9     3        11
// 			12    4        8
// 			15    5        5
// 			18    6        2
// 			 ^
// 			 18 + 3 > 20, so the answer is 6
// 			 Approach: basically keep up adding to the divisor and keep track of how many times you added to the divisor sum until the sume goes above the dividend (target)
public int divide(int dividend, int divisor) {
  // Corner cases
  if (dividend == 0) return 0;
  if (divisor == 1) return dividend;
  if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

  long ldividend = (long) dividend; // To avoid integer overflow in calculations after
  if (ldividend < 0) ldividend = -ldividend;
  long ldivisor = (long) divisor; // To avoid integer overflow in calculations after
  if (ldivisor < 0) ldivisor = -ldivisor;

  // sum = ldivisor * lquotient
  long lquotient = 1,
  sum = ldivisor;
  while (sum < ldividend) {
    lquotient++;
    sum += ldivisor;
  }

  if (sum > ldividend) lquotient--;
  boolean isPositive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);

  return isPositive ? (int) lquotient: -(int) lquotient;
}

// 		    Imporovement: Instead of adding just one divisor(the number you are using to dividei.e. the smaller number) at a time, we can multiply the divisor by 2. When we see that doubling the current sum will go over our target, we recur and use the original divisor along with the difference between the dividend (the original number being divided) and the sum

public int divide(int dividend, int divisor) {

  long ldividend = (long) dividend,
  ldivisor = (long) divisor;

  boolean signNegative = false;
  if (ldividend < 0) {
    signNegative = !signNegative;
    ldividend = -ldividend;
  }
  if (ldivisor < 0) {
    signNegative = !signNegative;
    ldivisor = -ldivisor;
  }

  long result = divideRecur(ldividend, ldivisor);

  if (result > Integer.MAX_VALUE && !signNegative) {
    result = Integer.MAX_VALUE;
  } else if (result < Integer.MIN_VALUE) {
    result = Integer.MIN_VALUE;
  }

  return signNegative ? (int) - result: (int) result;
}

private long divideRecur(long dividend, long divisor) {

  if (dividend < divisor) return 0;

  long sum = divisor,
  quotient = 1;
  while (sum + sum < dividend) {
    sum += sum;
    quotient += quotient;
  }

  return quotient + divideRecur(dividend - sum, divisor);
}

// 	2. Search in Rotated Sorted Array
// 			Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
// 			(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
// 			You are given a target value to search. If found in the array return its index, otherwise return -1.
// 			You may assume no duplicate exists in the array.
// 			Your algorithms runtime complexity must be in the order of O(log n).
// 			Example 1:
// 			Input: nums = [4,5,6,7,0,1,2], target = 0
// 			Output: 4
// 			Example 2:
// 			Input: nums = [4,5,6,7,0,1,2], target = 3
// 			Output: -1
// 			Solution from leetcode:
// 				Approach: find the pivot with binary search. it is that element whose next element is larger. check if the first element is pivot, in that case, the entire array is sorted and we would binary search over the entire array. If the pivot is not at index 0, then we have to check two things. If the target number number is a number less than the number at nums[0], then that means we cant use nums[0] as a starting point or left index. We have to use the real beginning of the array i.e. the rotation pivot. so we make the left index point to the rotation pivot, and the right index point to the end of the array and just search that. Otherwise, if the first index is smaller than the target, we can use it as left index, and we can use the end of the original array (i.e. the rotation pivot) as the right index. and perform BS on that range
class Solution {
  int[] nums;
  int target;

  public int find_rotate_index(int left, int right) {
    if (nums[left] < nums[right]) return 0;

    while (left <= right) {
      int pivot = (left + right) / 2;
      // the pivot is that place in the array where the number right after it is smaller than // it this is because, no element in a sorted rray should be larger than its next, // unless its the point of rotation
      if (nums[pivot] > nums[pivot + 1]) return pivot + 1;
      else {
        // the left element should always be smaller than the middle element in a sorted array
        // if the left element is bigger, then that means there is a pivot somewhere between // the left and middle (i.e. pivot)
        if (nums[pivot] < nums[left]) right = pivot - 1;
        else left = pivot + 1;
      }
    }
    return 0;
  }

  public int search(int left, int right) {
    /*
				    Binary search
				    */
    while (left <= right) {
      int pivot = (left + right) / 2;
      if (nums[pivot] == target) return pivot;
      else {
        if (target < nums[pivot]) right = pivot - 1;
        else left = pivot + 1;
      }
    }
    return - 1;
  }

  public int search(int[] nums, int target) {
    this.nums = nums;
    this.target = target;

    int n = nums.length;

    if (n == 0) return - 1;
    if (n == 1) return this.nums[0] == target ? 0 : -1;

    int rotate_index = find_rotate_index(0, n - 1);

    // if target is the smallest element
    if (nums[rotate_index] == target) return rotate_index;
    // if array is not rotated, search in the entire array
    if (rotate_index == 0) return search(0, n - 1);
    if (target < nums[0])
    // search in the right side
    return search(rotate_index, n - 1);
    // search in the left side
    return search(0, rotate_index);
  }
}

// 	3. Find First and Last Position of Element in Sorted Array:
// 		Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
// 		Your algorithmith runtime complexity must be in the order of O(log n).
// 		If the target is not found in the array, return [-1, -1].
// 		Example 1:
// 		Input: nums = [5,7,7,8,8,10], target = 8
// 		Output: [3,4]
// 		Example 2:
// 		Input: nums = [5,7,7,8,8,10], target = 6
// 		Output: [-1,-1]
// 		Approach: Basically, do a binary search for the first instance of the number. This can be accomplished by finding the number which matches the target and the number left of it is not the target. Likewise, we can use binary search to find the right index. It is the number that matches target and the 
// iterative
public int findLeft(int target, int[] arr) {
  int left = 0;
  int right = arr.length - 1;

  while (left < right) {
    int mid = left + ((r - l) / 2);
    if (arr[mid] == target) {
      if (arr[mid - 1] != target) { // check for bounds here
        return mid;
      } else {
        right = mid;
      }
    } else if (arr[mid] > target) { // search the left half
      right = mid;
    } else {
      left = mid + 1;
    }
  }
  return - 1
}

// recursive find left
public int findLeft(int target, int[] nums, int left, int right) {
  if (left > right) {
    return - 1;
  }

  int mid = (r + l) / 2;

  if (arr[mid] == target) {
    if (arr[mid - 1] != taget) {
      return mid;
    } else right = mid;
  } else if (arr[mid] > target) {
    findLeft(target, nums, left, mid);
  } else {
    //left = mid;
    findLeft(target, nums, mid + 1, right);
  }

  return - 1;
}

// 		Solution from LeetCode:
class Solution {
  // returns leftmost (or rightmost) index at which `target` should be
  // inserted in sorted array `nums` via binary search.
  private int extremeInsertionIndex(int[] nums, int target, boolean left) {
    int lo = 0;
    int hi = nums.length;

    while (lo < hi) {
      int mid = (lo + hi) / 2;
      if (nums[mid] > target || (left && target == nums[mid])) {
        hi = mid;
      }
      else {
        lo = mid + 1;
      }
    }

    return lo;
  }

  public int[] searchRange(int[] nums, int target) {
    int[] targetRange = { - 1,
      -1
    };

    int leftIdx = extremeInsertionIndex(nums, target, true);

    // assert that `leftIdx` is within the array bounds and that `target`
    // is actually in `nums`.
    if (leftIdx == nums.length || nums[leftIdx] != target) {
      return targetRange;
    }

    targetRange[0] = leftIdx;
    targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;

    return targetRange;
  }
}

// 	4. Pow(x, n) 
// 				Implement pow(x, n), which calculates x raised to the power n (xn).
// 				Example 1:
// 				Input: 2.00000, 10
// 				Output: 1024.00000
// 				Example 2:
// 				Input: 2.10000, 3
// 				Output: 9.26100
// 				Example 3:
// 				Input: 2.00000, -2
// 				Output: 0.25000
// 				Explanation: 2-2 = 1/22 = 1/4 = 0.25
// 				Note:
// 				-100.0 < x < 100.0
// 				n is a 32-bit signed integer, within the range [−231, 231 − 1]
// 				Approach: Basically, x to the power of n is x times itself n times. in another way you can do something like this:
// 				x^n = x^[(n/2) + (n/2)] = x^(n/2) * x^(n/2)
// 				and if n is odd then 
// 				x^n = x^[(n/2) + (n/2) + 1] = x^(n/2) * x^(n/2) * x
// 				Now if we do x*x it is the same as x^2
// 				now, if we now x^2 we can do x^2*x^2 = x^4
// 				so basically we keep multiplying the base by itself and for each iteration, we divide the exponent by 2 until it hits 0. 
// 				Also, if the exponent is odd, then we need to multiply by an extra base at some point in the iteration
// 				Recursive:
class Solution {
  private double fastPow(double x, long n) {
    if (n == 0) {
      return 1.0;
    }
    double half = fastPow(x, n / 2);
    if (n % 2 == 0) {
      return half * half;
    } else {
      return half * half * x;
    }
  }
  public double myPow(double x, int n) {
    long N = n;
    if (N < 0) {
      x = 1 / x;
      N = -N;
    }

    return fastPow(x, N);
  }
}
// 				Iterative:
class Solution {
  public double myPow(double x, int n) {
    long N = n;
    if (N < 0) {
      x = 1 / x;
      N = -N;
    }
    double ans = 1;
    double current_product = x;
    for (long i = N; i > 0; i /= 2) {
      if ((i % 2) == 1) {
        ans = ans * current_product;
      }
      current_product = current_product * current_product;
    }
    return ans;
  }
};

// 	5. Merge Intervals
// 				Given a collection of intervals, merge all overlapping intervals.
// 				Example 1:
// 				Input: [[1,3],[2,6],[8,10],[15,18]]
// 				Output: [[1,6],[8,10],[15,18]]
// 				Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
// 				Example 2:
// 				Input: [[1,4],[4,5]]
// 				Output: [[1,5]]
// 				Explanation: Intervals [1,4] and [4,5] are considered overlapping.
// 				NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

// 				Approach: Intuition
// 				If we sort the intervals by their start value, then each set of intervals that can be merged will appear as a contiguous "run" in the sorted list.
// 				Algorithm
// 				First, we sort the list as described. Then, we insert the first interval into our merged list and continue considering each interval in turn as follows: If the current interval begins after the previous interval ends, then they do not overlap and we can append the current interval to merged. Otherwise, they do overlap, and we merge them by updating the end of the previous interval if it is less than the end of the current interval.
class Solution {
  private class IntervalComparator implements Comparator < Interval > {@Override
    public int compare(Interval a, Interval b) {
      return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
    }
  }

  public List < Interval > merge(List < Interval > intervals) {
    Collections.sort(intervals, new IntervalComparator());

    LinkedList < Interval > merged = new LinkedList < Interval > ();
    for (Interval interval: intervals) {
      // if the list of merged intervals is empty or if the current
      // interval does not overlap with the previous, simply append it.
      if (merged.isEmpty() || merged.getLast().end < interval.start) {
        merged.add(interval);
      }
      // otherwise, there is overlap, so we merge the current and previous
      // intervals.
      else {
        merged.getLast().end = Math.max(merged.getLast().end, interval.end);
      }
    }

    return merged;
  }
}

// 	6. Find Peak Element
// 			A peak element is an element that is greater than its neighbors.
// 			Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
// 			The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
// 			You may imagine that nums[-1] = nums[n] = -∞.
// 			Example 1:
// 			Input: nums = [1,2,3,1]
// 			Output: 2
// 			Explanation: 3 is a peak element and your function should return the index number 2.
// 			Example 2:
// 			Input: nums = [1,2,1,3,5,6,4]
// 			Output: 1 or 5 
// 			Explanation: Your function can return either index number 1 where the peak element is 2, 
// 			             or index number 5 where the peak element is 6.
// 			Note:
// 			Your solution should be in logarithmic complexity.We can view any given sequence in numsnums array as alternating ascending and descending sequences. By making use of this, and the fact that we can return any peak as the result, we can make use of Binary Search to find the required peak element.
// 			Approach: a number is a peak if both its left neighbour and right are less than it. Note that, any number we select, it is part of a sequency that is increasing or decreasing. Say we choose a number; if that numberith right neigbour is smaller, then the current number is on a decreasing sequence. And that means, that the peak must be to the left of the current number. So we recurse left. 
// 			Likewise, if the current selected numberith right neighbour is greater, then we are on an increasing sequence. So the peak must be somewhere to the right, so we recurse to the right. We keep on updating our bounds with the above 2 rules until we are left with only one element. This element will be the peak
public int peak(int[] nums) {
  int left = 0;
  int right = mums.length - 1;

  while (l < r) {
    int mid = l + (r - l) / 2

    if (nums[mid] < nums[mid + 1]) { // an element is in an increasing sequence if the right is bigger
      // search the right side
      left = mid + 1;
    } else { // else its decreasing since we cant have equal adjecent numbers
      right = mid;
    }
  }

  return nums[l]
}

// 			Iterative:
public class Solution {
  public int findPeakElement(int[] nums) {
    int l = 0,
    r = nums.length - 1;
    while (l < r) {
      int mid = (l + r) / 2;
      if (nums[mid] > nums[mid + 1]) r = mid;
      else l = mid + 1;
    }
    return l;
  }
}

// 			Recursive:
public class Solution {
  public int findPeakElement(int[] nums) {
    return search(nums, 0, nums.length - 1);
  }
  public int search(int[] nums, int l, int r) {
    if (l == r) return l;
    int mid = (l + r) / 2;
    if (nums[mid] > nums[mid + 1]) return search(nums, l, mid);
    return search(nums, mid + 1, r);
  }
}

// 	7. First Bad Version
// 			You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
// 			Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
// 			You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
// 			Example:
// 			Given n = 5, and version = 4 is the first bad version.
// 			call isBadVersion(3) -> false
// 			call isBadVersion(5) -> true
// 			call isBadVersion(4) -> true
// 			Then 4 is the first bad version. 
// 			Approach: use Binary search. if you get "true", then that means everything after it is also bad. So we consider everything from the left upto current (mid) -1
public int firstBadVersion(int n) {
  int l = 0;
  int r = n;

  while (l < r) {
    int mid = l + (r - l) / 2;

    if (isBadVersion(mid) == false) { // everything after it is also bad, so search left
      right = mid - 1;
    } else { // everything before it is good, including current number, so search right
      left = mid;
    }

  }

  isBadVersion(l) ? l: -1;

}

// 	8. Intersection of Two Arrays
// 			Given two arrays, write a function to compute their intersection.
// 			Example 1:
// 			Input: nums1 = [1,2,2,1], nums2 = [2,2]
// 			Output: [2]
// 			Example 2:
// 			Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// 			Output: [9,4]
// 			Note:
// 			Each element in the result must be unique.
// 			The result can be in any order.
// 			Approach: just use a set and iterate through both arrays.
// 			Solution:
class Solution {
  public int[] set_intersection(HashSet < Integer > set1, HashSet < Integer > set2) {
    int[] output = new int[set1.size()];
    int idx = 0;
    for (Integer s: set1)
    if (set2.contains(s)) output[idx++] = s;

    return Arrays.copyOf(output, idx);
  }

  public int[] intersection(int[] nums1, int[] nums2) {
    HashSet < Integer > set1 = new HashSet < Integer > ();
    for (Integer n: nums1) set1.add(n);
    HashSet < Integer > set2 = new HashSet < Integer > ();
    for (Integer n: nums2) set2.add(n);

    if (set1.size() < set2.size()) return set_intersection(set1, set2);
    else return set_intersection(set2, set1);
  }
}

// 	9. Intersection of Two Arrays II
// 			Given two arrays, write a function to compute their intersection.
// 			Example 1:
// 			Input: nums1 = [1,2,2,1], nums2 = [2,2]
// 			Output: [2,2]
// 			Example 2:
// 			Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// 			Output: [4,9]
// 			Note:
// 			Each element in the result should appear as many times as it shows in both arrays.
// 			The result can be in any order.
// 			Follow up:
// 			What if the given array is already sorted? How would you optimize your algorithm?
// 			What if nums1ith size is small compared to nums2ith size? Which algorithm is better?
// 			What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
// 			Approach: use a hashmap with a count, to count m repeated characters.
public int[] intersect(int[] nums1, int[] nums2) {
  if (nums1.length > nums2.length) {
    return intersect(nums2, nums1);
  }
  HashMap < Integer,
  Integer > m = new HashMap < >();
  for (int n: nums1) {
    m.put(n, m.getOrDefault(n, 0) + 1);
  }
  int k = 0;
  for (int n: nums2) {
    int cnt = m.getOrDefault(n, 0);
    if (cnt > 0) {
      nums1[k++] = n;
      m.put(n, cnt - 1);
    }
  }
  return Arrays.copyOfRange(nums1, 0, k);
}

// Others:
// 	1. Expression Add Operators
// 		Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
// 		Example 1:
// 		Input: num = "123", target = 6
// 		Output: ["1+2+3", "1*2*3"] 
// 		Example 2:
// 		Input: num = "232", target = 8
// 		Output: ["2*3+2", "2+3*2"]
// 		Example 3:
// 		Input: num = "105", target = 5
// 		Output: ["1*0+5","10-5"]
// 		Example 4:
// 		Input: num = "00", target = 0
// 		Output: ["0+0", "0-0", "0*0"]
// 		Example 5:
// 		Input: num = "3456237490", target = 9191
// 		Output: []
// 			Definitions: 
// 				Operand: the number upon which a mathematical operation is performed
// 				Operator: i.e. +,-,*...
// 			Approach: we need to recurse over the number size (or as in the case of the leet code solution do a no-op), and then recurse for each operation +,- and *. at each recurse we keep track of the total for the current expression. When we recurse for multiplication, we need to look at the operand and operation that was executed before the recurse to multiplication. then we need to reverse its effect, then carry out the multiplication.
// 			It is important to understand the no-op operation. It replaces the need to iterate over different number lengths. At each recurse we need to pass the current operand we are working with. Then at each recurse we will either do a no-op (extend the current operand), do an addition, subtraction or multiplication.
// 			We also pass the previous operand and operator. The reaspn for this is that when we do a multiplication, we need to undo the previous operation if it was not multiplication also.
// 			Also note, when we actually apply the current operand with a +, - or *, we need to reset the current operand to zero for the following recurances that it creates
class Solution {

  public ArrayList < String > answer;
  public String digits;
  public long target;

  public void recurse(
  int index, long previousOperand, long currentOperand, long value, ArrayList < String > ops) {
    String nums = this.digits;

    // Done processing all the digits in num
    if (index == nums.length()) {
      // If the final value == target expected AND
      // no operand is left unprocessed
      if (value == this.target && currentOperand == 0) {
        StringBuilder sb = new StringBuilder();
        ops.subList(1, ops.size()).forEach(v ->sb.append(v));
        this.answer.add(sb.toString());
      }
      return;
    }

    // Extending the current operand by one digit
    currentOperand = currentOperand * 10 + Character.getNumericValue(nums.charAt(index));
    String current_val_rep = Long.toString(currentOperand);
    int length = nums.length();

    // To avoid cases where we have 1 + 05 or 1 * 05 since 05 wont be a
    // valid operand. Hence this check
    if (currentOperand > 0) {

      // NO OP recursion: this is to try different length of a number starting at index
      recurse(index + 1, previousOperand, currentOperand, value, ops);
    }

    // ADDITION
    ops.add("+");
    ops.add(current_val_rep);
    recurse(index + 1, currentOperand, 0, value + currentOperand, ops);
    ops.remove(ops.size() - 1);
    ops.remove(ops.size() - 1);

    if (ops.size() > 0) {

      // SUBTRACTION
      ops.add("-");
      ops.add(current_val_rep);
      recurse(index + 1, -currentOperand, 0, value - currentOperand, ops);
      ops.remove(ops.size() - 1);
      ops.remove(ops.size() - 1);

      // MULTIPLICATION
      ops.add("*");
      ops.add(current_val_rep);
      recurse(
      index + 1, currentOperand * previousOperand, 0, value - previousOperand + (currentOperand * previousOperand), ops);
      ops.remove(ops.size() - 1);
      ops.remove(ops.size() - 1);
    }
  }

  public List < String > addOperators(String num, int target) {

    if (num.length() == 0) {
      return new ArrayList < String > ();
    }

    this.target = target;
    this.digits = num;
    this.answer = new ArrayList < String > ();
    this.recurse(0, 0, 0, 0, new ArrayList < String > ());
    return this.answer;
  }
}

// 	2. Find All Anagrams in a String
// 			Given a string s and a non-empty string p, find all the start indices of ps anagrams in s.
// 			Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
// 			The order of output does not matter.
// 			Example 1:
// 			Input:
// 			s: "cbaebabacd" p: "abc"
// 			Output:
// 			[0, 6]
// 			Explanation:
// 			The substring with start index = 0 is "cba", which is an anagram of "abc".
// 			The substring with start index = 6 is "bac", which is an anagram of "abc".
// 			Example 2:
// 			Input:
// 			s: "abab" p: "ab"
// 			Output:
// 			[0, 1, 2]
// 			Explanation:
// 			The substring with start index = 0 is "ab", which is an anagram of "ab".
// 			The substring with start index = 1 is "ba", which is an anagram of "ab".
// 			The substring with start index = 2 is "ab", which is an anagram of "ab".
// 			Solution: 
// 			Approach: Initialize counter = p.length. Have two pointers, b (for beginning) and e (for ending). They both start at 0th index. we check if e is a character that exists in p, if it does, then we subtract one from counter and increase e by one. If counter equals zero, then this means that we have found an anagram. We store b as it holds the beginning index of the anagram. Then, if b - e is equal to p.length, we increment b by one and if it was pointing to a character that was in the patter, we need to increment counter by one. 
public List < Integer > findAnagrams(String s, String p) {
  List < Integer > list = new ArrayList < >();
  if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
  int[] hash = new int[256]; //character hash
  //record each character in p to hash
  for (char c: p.toCharArray()) {
    hash[c]++;
  }
  //two points, initialize count to pith length
  int left = 0,
  right = 0,
  count = p.length();
  while (right < s.length()) {
    //move right everytime, if the character exists in pith hash, decrease the count
    //current hash value >= 1 means the character is existing in p
    if (hash[s.charAt(right++)]-->=1) count--;

    //when the count is down to 0, means we found the right anagram
    //then add windowith left to result list
    if (count == 0) list.add(left);

    //if we find the windowith size equals to p, then we have to move left (narrow the window) to find the new match window
    //++ to reset the hash because we kicked out the left
    //only increase the count if the character is in p
    //the count >= 0 indicate it was original in the hash, cuz it wont go below 0
    if (right - left == p.length() && hash[s.charAt(left++)]++>=0) count++;
  }
  return list;
}

// 	3. Permutation in String
// 				Solution
// 				Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first strings permutations is the substring of the second string.
// 				Example 1:
// 				Input: s1 = "ab" s2 = "eidbaooo"
// 				Output: True
// 				Explanation: s2 contains one permutation of s1 ("ba").
// 				Example 2:
// 				Input:s1= "ab" s2 = "eidboaoo"
// 				Output: False

// 				Note:
// 				The input strings only contain lower case letters.
// 				The length of both given strings is in range [1, 10,000].

// 						Solution from leet code:
// 							Explanation: Basically use an array of 26 items to store the count of each character in sMap1.
// 							To acces an index use smap1[char - a] Then, we will iterate through s2 with a window size of s1.length. until the beginnin of the window reaches s2.length - s1.length. everytime you move the window, one letter from the taken out and one new letter will be added to the smap2. smap2 is basicallyy a mapping of each 26 chars to their frequency. each time smap2 is updated, we compare the arrays smap1 and smap2.
// i don't think solution will work if there are duplicates
public class Solution {
  public boolean checkInclusion(String s1, String s2) {
    if (s1.length() > s2.length()) return false;
    int[] s1map = new int[26];
    int[] s2map = new int[26];
    for (int i = 0; i < s1.length(); i++) {
      s1map[s1.charAt(i) - a]++;
      s2map[s2.charAt(i) - a]++;
    }
    for (int i = 0; i < s2.length() - s1.length(); i++) {
      if (matches(s1map, s2map)) return true;
      s2map[s2.charAt(i + s1.length()) - a]++;
      s2map[s2.charAt(i) - a]--;
    }
    return matches(s1map, s2map);
  }
  public boolean matches(int[] s1map, int[] s2map) {
    for (int i = 0; i < 26; i++) {
      if (s1map[i] != s2map[i]) return false;
    }
    return true;
  }
}

// 	4. Verify Alien dictionary:
// 		  Verifying an Alien Dictionary
// 			In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
// 			Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
// 			Example 1:
// 			Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
// 			Output: true
// 			Explanation: As h comes before l in this language, then the sequence is sorted.
// 			Example 2:
// 			Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
// 			Output: false
// 			Explanation: As d comes after l in this language, then words[0] > words[1], hence the sequence is unsorted.
// 			Example 3:
// 			Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
// 			Output: false
// 			Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because l > ∅, where ∅ is defined as the blank character which is less than any other character (More info).
// 			Solution: Basically compare two words at a time. If all pairs of adjacent words are in the correct order then the entire list of words (dictionary) is in the correct order. 
// 			 Also, the characts in the order list are stored in an array int[26] where the value denotes the actual order in the alien dictionary. When comparing two words, we skip over all of the equal letters and just compare the first non-equal letter.
class Solution {
  public boolean isAlienSorted(String[] words, String order) {
    int[] index = new int[26];
    for (int i = 0; i < order.length(); ++i)
    index[order.charAt(i) - a] = i;

    search: for (int i = 0; i < words.length - 1; ++i) {
      String word1 = words[i];
      String word2 = words[i + 1];

      // Find the first difference word1[k] != word2[k].
      for (int k = 0; k < Math.min(word1.length(), word2.length()); ++k) {
        if (word1.charAt(k) != word2.charAt(k)) {
          // If they compare badly, its not sorted.
          if (index[word1.charAt(k) - a] > index[word2.charAt(k) - a]) return false;
          continue search;
        }
      }

      // If we didnt find a first difference, the
      // words are like ("app", "apple").
      if (word1.length() > word2.length()) return false;
    }

    return true;
  }
}

// 	5. Interval List Intersections
// 			Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
// 			Return the intersection of these two interval lists.
// 			(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
// 			Note: an itersection will happen between two intervals if one of the intervals beginning time is before the other others end time.
// 				So all we need to check if that if the latest beginning time interval is before the earliest end time interval	
// 			Example 1:
// 			Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
// 			Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
// 			Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
// 			Note:
// 			0 <= A.length < 1000
// 			0 <= B.length < 1000
// 			0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
// 			NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
// 			Approach: Note that intervals in the same set do not intersect. Also note, that the when comparing, we can gradually get rid of intervals that need to be compared. For example, we start from the beginning of both lists A and B. Which ever one has the earlier stat date, we look at that first. Then we compare it with the first element in B, if 'a' has an endpoint less than bs, then we get the intersecion from bs start point to as and point and add that to the list. Now we can discard 'a' because it wont intersect with anything else. What if 'a' endpoint is more than bs? then this means all of b is an intersection, so we can add b to the list and dont have to consider it with any other interval in A.
// 			1. compare both intervals
// 			2. for the earlier beginning point, check is that interval is less than ending point of other
// 				- if yes, include b.beginning to a.ending in interval list, discard a
// 				- if no, then include all of b, discard b
// 			3. take next 2 items available from both lists.
// 			// NOTE: the code below has a more efficient solution although it is similar to the above
// 			1. 
// 			while(a < A.length && b < b.length) {
// 				//choose smaller one
// 			}
// 				Soltion from LeetCode:
class Solution {
  public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
    List < Interval > ans = new ArrayList();
    int i = 0,
    j = 0;

    while (i < A.length && j < B.length) {
      // Letith check if A[i] intersects B[j].
      // lo - the startpoint of the intersection
      // hi - the endpoint of the intersection
      int lo = Math.max(A[i].start, B[j].start);
      int hi = Math.min(A[i].end, B[j].end);
      if (lo <= hi) ans.add(new Interval(lo, hi));

      // Remove the interval with the smallest endpoint
      if (A[i].end < B[j].end) i++;
      else j++;
    }

    return ans.toArray(new Interval[ans.size()]);
  }
}

// Question form youtube:
// 		1. Kadanes algorithm (subarray with the largest sum):
// 			the brute force way involves generating all sub arrays, which is not ideal
// 			- a much better way to do this is to notice a recursive approach
// 				- for each element in the array, we can make one of two decisions
// 					1. should this element be the start of a new max subarray 
// 					2. or should this element be added to the the previous maximum subarry
// 				- we basically need to figure out the following: 
// 				maxSumEndingAtIndex[i] = Math.max(arr[i], maxSumEndingAtIndex[i - 1] + arr[i]);
// 			- this basically stems from the idea of making subsets, as the problem reduces to making subsets, choosing sum elements and not choosing others
// 			- usually when making subsets, we would run from the first to last index recurring on whether to keep or discard the current element. the DP solution is derived here by running the iteration through the array and looking at the items before us
public int maxSubArray(int[] nums) {
  int maxSoFar = nums[0];
  int maxEndingHere = nums[0];

  for (int i = 1; i < nums.length; i++) {

    maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);

    maxSoFar = Math.max(maxSoFar, maxEndingHere);
  }

  return maxSoFar;
}

// 		2. Search a 2d array for an item:
// 			you can have two variations:
// 				 1, 2, 3, 4
// 				 5, 6, 7, 8
// 				 9, 10,11,12 
// 				// here we have all of the rows sorted and the last element of each row comes before the beginning of the next row. We can think of this like a long 1D array with each subsequent row after each other. i.e.
// 				1,2,3,4,5,6,7,8,9,10,11,12
// 				Then we can perform a simple binary search and find the desired element.
// 				Question: how do we handle boundraies between rows?
// 					- for this we will have a mapping function
// 					- we will work as if the 2D array is a 1d array of length rows*cols
// 					- then the first itemith index is 0 and the last items index is 11 (rows*cols - 1)
// 						- so how do we get row and column from an index?
// 							row = index / numberOfRows
// 							col = index % numberOfRows
// 				// now run bunary search
public boolean search(int[][] mat, int item) {
  int rows = mat.length;
  int cols = mat[0].length;
  int low = 0;
  int high = rows * cols - 1;

  while (l < h) {
    int mid = l + (r - l) / 2;
    int current = mat[getRow(l)][getCol(l)];

    if (current == item) {
      return true;
    }

    if (current < item) { // search the right side
      left = mid + 1;
    } else { // search left side
      right = mid;
    }
  }

  return false;
}

// the other case is when the last item in row is not less than or equal to the next item in the next row
// 				1, 4, 7, 11
// 				8, 9,10, 20
// 				11,12,17,30
// now we cant think of this as a 1d array
// notice something about the current matrix
// the rows are sorted and the columns are sorted
// this implies that if you go down or right from any position the value will increase
// and vice versa
// our goal is to narrow the search space for every iteration of the algorithm
// notice that if you are looking for the number 10, and you start at the top right corner of the matrix
// or the bottom left of the matrix you can either go to a value larger than the curren value or smaller 
// i.e. top,right: 11->7 and 11->20 or bottom left 11->8 or 11->12
// we can reduce our decision space if we started at one of these points
// on the contrary, if we start at the top left (1) or the bottom right, we cant decrease our decision space i.e. 1->4 or 1->8 and 30->17, or 30->20
// in this case we did not decrease the decision space
// so basically the algorithm would be
// start at the bottom left or top right
// (letith choose top right) while we are still in the bounds of the matrix, 
// if the current item is greater than the target, go left, col--
// if the item is less than the target move down row++
// keep doing this while the row,col are valid or until you have found the value
// 		3. Give a node, find all nodes from the current node that are k distance away in a binary tree
// First we must understand that this question is not that easy. We need to notice that nodes that are k distance away might also be up before the current nodeith parent
// so how to do we traverse a nodes parent if a node doesnt have a reference to its parent.
// we can do some preprocessing by going through the binary tree and creating a hashmap.
// each entry in the hashmap will have a key being a node, and the value being its parent node
// perhaps we can form such a tree like this:
//iterate through all nodes and pass its parent to each iteration
private Map < Node,
Node > get parentMaps(Node root, Map parents, Node myParent) {
  if (root == null) {
    return current;
  }

  parents.set(root, myParent);
  parentMaps(root.left, parents, root);
  parentMaps(root.right, parents, root);
}
// now all we need to do is perform a BFS
public List < Integer > distanceK(TreeNode treeRoot, TreeNode startNode, int targetDistance) {

  Map < TreeNode,
  TreeNode > nodeToParentMap = new HashMap();
  populateNodeToParentMap(nodeToParentMap, treeRoot, null);

  Queue < TreeNode > queue = new LinkedList();
  queue.add(startNode);

  Set < TreeNode > seen = new HashSet();
  seen.add(startNode);

  int currentLayer = 0;

  while (!queue.isEmpty()) {
    if (currentLayer == targetDistance) {
      return extractLayerFromQueue(queue);
    }

    int layerSize = queue.size();
    for (int i = 0; i < layerSize; i++) {

      TreeNode currentNode = queue.poll();

      if (currentNode.left != null && !seen.contains(currentNode.left)) {
        seen.add(currentNode.left);
        queue.offer(currentNode.left);
      }

      if (currentNode.right != null && !seen.contains(currentNode.right)) {
        seen.add(currentNode.right);
        queue.offer(currentNode.right);
      }

      TreeNode parentOfCurrentNode = nodeToParentMap.get(currentNode);
      if (parentOfCurrentNode != null && !seen.contains(parentOfCurrentNode)) {
        seen.add(parentOfCurrentNode);
        queue.offer(parentOfCurrentNode);
      }

    }

    currentLayer++;

  }

  return new ArrayList < Integer > ();
}

private List < Integer > extractLayerFromQueue(Queue < TreeNode > queue) {
  List < Integer > extractedList = new ArrayList();
  for (TreeNode node: queue) {
    extractedList.add(node.val);
  }
  return extractedList;
}

// 		4. Change making problem - given a set of coins, what is the least amount of coins needed to make a given amount
// 			i.e. coins = [1,2,4] 
// 				 amount = 11, answer: 3 coins ==> 5,5,1
// 			To recursively solve this problem from a top down approach, we would basically iterate over each coin and subtract that coins value from the given amount. Then we would recur for the new lesser amount

public int leastCoins(int[] coins, int amount) {
  if (amount == 0) {
    return 0;
  }
  int min = Integer.MAX_VALUE;
  for (int coin: coins) {
    if (coin <= amount) {
      // min = Math.min(min, leastCoins(coins, amount - coin) + 1);
      // top down DP
      // int numberOfCoinsForAmount = leastCoins(coins, amount - coin);
      // first check if a value exists, otherwise,compute it and add it to the list
    }
  }
}

// this can be made to a dynamic programming top down easily by storing the return value of an amount in an array
// 
// imagine doing this problem backwards, i.e. starting with sum = 0, and working your way up to the desired amount
public int leastCoins(int[] coins, int amount) {
  int[] leastCoinsForAmount = new int[amount + 1]; // need one for zero
  leastCoinsForAmount[0] = 0;

  // need to initialize each element in the array with a high value
  // now just go through the array "leastCoinsForAmount",
  // for each "amount", try each coin and reuse the array
  for (int i = 1; i < leastCoinsForAmount.length; i++) {

    // check for each coin
    for (int coin: coins) {
      // make sure that the coin is smaller than the current amount
      if (coin <= i) {
        leastCoinsForAmount[i] = Math.min(leastCoinsForAmount[i], leastCoinsForAmount[i - coin] + 1);
      }
    }
  }
  return leastCoinsForAmount[amount + 1];
}

// 		5. Add two numbers without using the "+" sign
// 			- notice that numbers are actually represented in the computer as bits with base 2
// 			- in order to add two numbers using bits, we can do the following
// 				- first find the sum of the two numbers without the carry overs by use XOR operator
// 					i.e. xor = a ^ b
// 					- and we will store the result in a
// 				- use the "&" operator to find all of the carries
// 					b = a & b
// 					- but notice something very important, in order to "apply" the carryovers, the result must be shifted by one
// 					i.e. b<<1
// 				- in the next iteration we will do the same thing but notice that b holds the carrovers
// 				- we will keep on iterating until the carry overs are zero
public int getSum(int a, int b) {

  while (b != 0) {
    int carry = a & b;

    a = a ^ b;

    b = carry << 1;
  }
  return a;
}

// 		 6. Valid parentheses: make sure parenthesis are balanced
// 		 		Approach: we keep track of all of the opening paranthesis as we encounter them in a stack. Whenever we encounter a closing paranthesis, we need to make sure that it matches the last opening paranthesis. If it doesnt, then we know that the current parenthesization is incorrect. If we reach the end of the string, and the stack is empty, then we know that the expression is balananced.
// 		 			" [ () () () ] { } { [ ( ) ] } " valid
// 					" { } { ) [ ] " invalid
// 				my code:
public boolean inValid(String exp) {

  Stack < Character > openBracketStack = new Stack < >();

  for (int i = 0; i < exp.length; i++) {
    // if open bracket then add it to the stack
    if (openBracketStack()) {
      openBracket.push(exp.charAt(i));
    }

    // if closed bracket then make sure the last guy on the stack is the same type but opening
    else if (matchesLastOpening(e, openBracketStack)) {
      openBracketStack.pop();
    } else {
      return false;
    }

  }

  // if there is somehting in the stack return false
  if (openBracketStack.isEmpty()) {
    return true;
  }
  return false;
}

// 		7. Partition array into k subsets with equal sum.
// 			- given an array, see if it can be divided up into k subsets where each subset equals the same value
// 				i.e. arr = 4,3,2,3,5,2,1 k = 4
// 				in this case, each bucket would have to equal 5
// 			- Approach, this is a backtracking recursive problem
// 			- we will try to add and not add each item to each bucket
// 			- when a bucket reaches the expected value, we go onto to the next bucket
// 			- we keep going until we covered k buckets
// 			// base cases
// 				// 1. if there is one bucket left, that means k-1 buckets were successfully portioned elements such thatthere sum equals the desired sum
// 					- so that means the last bucket will automatically have the desired remaining sum, so we return true
// 				// 2. this is technically not a base case, but if the current bucket sum equals the target sum for each bucket, then we recurse to the next bucket with a zeroed sum
// 			// otherwise, we will try to put each item in the current bucket and recur
public boolean canPartitionKSubsets(int[] arr, int k) {

  int sumOfAllArrayItems = 0;
  for (int num: arr) {
    sumOfAllArrayItems += num;
  }

  if (k <= 0 || k > arr.length || sumOfAllArrayItems % k != 0) {
    return false;
  }

  return canPartition(0, arr, new boolean[arr.length], k, 0, sumOfAllArrayItems / k);
}

boolean canPartition(int iterationStart, int[] arr, boolean[] used, int k, int inProgressBucketSum, int targetBucketSum) {

  if (k == 1) {
    return true;
  }

  if (inProgressBucketSum == targetBucketSum) {
    return canPartition(0, arr, used, k - 1, 0, targetBucketSum);
  }

  for (int i = iterationStart; i < arr.length; i++) {
    if (!used[i] && inProgressBucketSum + arr[i] <= targetBucketSum) {
      used[i] = true;

      if (canPartition(i + 1, arr, used, k, inProgressBucketSum + arr[i], targetBucketSum)) {
        return true;
      }
      used[i] = false;
    }
  }

  return false;
}

// 		8. Generate all palindromic decompositions of a string:
// 		 		i.e. str = "aab"
// 		 			ret = [
// 		 					[a, a, b]
// 		 					[aa, b]
// 		 				  ]
// 		 		Approach: basically we will begin each recursion with a beginning index
// 		 					- from this beginning index we iterate from this beginning index to and ending index (substring)
// 		 						- we will check if this current substring is a palindrome, then we will recur, otherwise we wont recur over this substring
public List < List < String >> partition(String s) {
  List < List < String >> decompositions = new ArrayList();
  decomposeString(0, s, new ArrayList < >(), decompositions);
  return decompositions;
}

private void decomposeString(
int workingIndex, String s, List < String > partialDecomposition, List < List < String >> decompositions) {
  /*
						    If we have decomposed the whole string then reap the
						    'partialDecomposition', it is now complete.
						  */
  if (workingIndex == s.length()) {
    decompositions.add(new ArrayList < >(partialDecomposition));
    return;
  }

  /*
						    Take every snippet take from the 'workingIndex' to the end of the
						    string. This is out 'possibility space' that we can recurse into.
						  */
  for (int i = workingIndex; i < s.length(); i++) {
    /*
						      Only recurse if the snippet from 'workingIndex' (inclusive) to
						      s.length() (inclusive) is a palindrome
						    */
    if (isPalindrome(workingIndex, i, s)) {

      // 1.) Choose - Take the snippet & add it to our decomposition 'path'
      String palindromicSnippet = s.substring(workingIndex, i + 1);
      partialDecomposition.add(palindromicSnippet);

      /*
						        2.) Explore - Recurse and advance progress 1 past right bound of 
						        the 'palindromicSnippet' which is 'i + 1'
						      */
      decomposeString(i + 1, s, partialDecomposition, decompositions);

      /*
						        3.) Unchoose - We are done searching, remove the snippet from our
						        'path'. Next loop iteration will try another snippet in this stack
						        frame.
						      */
      partialDecomposition.remove(partialDecomposition.size() - 1);
    }
  }
}

/*
						  Checks if the region from left (inclusive) to right (inclusive) is
						  a palindromic.
						*/
public boolean isPalindrome(int left, int right, String s) {
  while (left < right) {
    if (s.charAt(left) != s.charAt(right)) {
      return false;
    }

    left++;
    right--;
  }

  return true;
}

// 		9. Max sum in a 2d array:
// 		 		Approach: to solve this problem we will use kadanes algorithm. we will have two pointers L and R.
// 		 		L is the value of the left column and R is the value of thr right column. We will start L at 0 and R at 0. Then we will iterate R till the last column. Each iteration of R, we update the running sum of each row i.e. we have a seperate 1d array that holds the current sum for each row. After updating all of the current sums for each row, we run kadanes algorithm to find the max contiguous sum in the currentSumforRow arrays. We store the max sum and update it when a larger value is encountered.
public static void main(String args[]) {

  int matrix[][] = {
    {
      6,
      -5,
      -7,
      4,
      -4
    },
    { - 9,
      3,
      -6,
      5,
      2
    },
    { - 10,
      4,
      7,
      -6,
      3
    },
    { - 8,
      9,
      -3,
      3,
      -7
    }
  };

  Rectangle maxSumRectangle = maxSum(matrix);

  System.out.println("Rectangle Sum: " + maxSumRectangle.interiorSum);

  System.out.println("Left Index: " + maxSumRectangle.leftBorderIndex);
  System.out.println("Right Index: " + maxSumRectangle.rightBorderIndex);
  System.out.println("Top Index: " + maxSumRectangle.topBorderIndex);
  System.out.println("Bottom Index: " + maxSumRectangle.bottomBorderIndex);
}

private Rectangle maxSum(int matrix[][]) {

  /*
				    Record the total amount of rows and columns
				  */
  int rows = matrix.length;
  int cols = matrix[0].length;

  /*
				    Create an array that will be a "vertical" array and record
				    the running sums for each row in the each iteration of the
				    left bound
				  */
  int runningRowSums[] = new int[rows];

  /*
				    This is the max rectangle that we will update along the way
				  */
  Rectangle maxRectangle = new Rectangle();

  /*
				    We will try all left bound plantings from index 0
				    to index cols - 1
				  */
  for (int left = 0; left < cols; left++) {

    /*
				      We will reset the running row sums all to 0 since
				      this is a new planting of the left bound
				    */
    for (int i = 0; i < rows; i++) {
      runningRowSums[i] = 0;
    }

    /*
				      For each left bound, we will try all of the right bounds
				      starting at the left bound we are planted at.
				    */
    for (int right = left; right < cols; right++) {

      /*
				        Add all items in column right to their respective
				        rows running sum
				      */
      for (int i = 0; i < rows; i++) {
        runningRowSums[i] += matrix[i][right];
      }

      /*
				        Perform Kadanes algorithm on the running sum array
				        so that we can determine the best top and bottom
				        bound to choose for the rectangle.
				      */
      KadaneResult kadaneResult = kadane(runningRowSums);

      /*
				        If we notice that this rectangle can achieve a better
				        maxSum than the best we have done so far then we need
				        to adjust our new best
				      */
      if (kadaneResult.maxSum > maxRectangle.interiorSum) {

        /*
				          Set a new interiorSum for our maxRectangle
				        */
        maxRectangle.interiorSum = kadaneResult.maxSum;

        /*
				          The left and the right of the maxRectangle become
				          the left and right where our for loop pointers
				          are sitting
				        */
        maxRectangle.leftBorderIndex = left;
        maxRectangle.rightBorderIndex = right;

        /*
				          Our top and bottom bounds for the max rectangle are
				          going to be the startIndex and endIndex of the max
				          subarray region in the runningRowSums sum cache
				          (respectively).
				        */
        maxRectangle.topBorderIndex = kadaneResult.startIndex;
        maxRectangle.bottomBorderIndex = kadaneResult.endIndex;
      }

    }

  }

  return maxRectangle;
}

/*
				  An implementation of Kadanes algorithm that remembers the
				  start and end of the Max Contiguous Subarray Sum region
				  in the KadaneResult object returned

				  This video explains Kadanes algorithm: https://www.youtube.com/watch?v=2MmGzdiKR9Y
				*/
private KadaneResult kadane(int arr[]) {

  /*
				    The best sum achieved for a region so far
				  */
  int bestMaxSoFar = 0;

  /*
				    maxRegionStart: start index of the max sum region
				    maxRegionEnd: end index of the max sum region
				  */
  int maxRegionStart = -1;
  int maxRegionEnd = -1;

  int currentStart = 0;
  int bestMaxAtThisIndex = 0;

  /*
				    We will process every
				  */
  for (int i = 0; i < arr.length; i++) {

    /*
				      Add this item to the best subarray achieved at
				      index i - 1. Then we will decided what to do
				      after this.
				    */
    bestMaxAtThisIndex += arr[i];

    /*
				      If bestMaxAtThisIndex is < 0 then we will
				      decide to not extend the best subarray at i - 1.

				      We will just set the best we can achieve for subarrays
				      ending at this index i to 0.

				      The new currentStart to the max subarray region becomes
				      i + 1
				    */
    if (bestMaxAtThisIndex < 0) {
      bestMaxAtThisIndex = 0;
      currentStart = i + 1;
    }

    /*
				      If the best max (now the best max at this index) beats the
				      best global max achieved so far then we need to adjust:

				      maxRegionStart becomes the currentStart we were keeping track
				      of all along.

				      maxRegionEnd becomes the index we are sitting at i.

				      The bestMaxSoFar becomes the bestMaxAtThisIndex.
				    */
    if (bestMaxAtThisIndex > bestMaxSoFar) {
      maxRegionStart = currentStart;
      maxRegionEnd = i;
      bestMaxSoFar = bestMaxAtThisIndex;
    }

  }

  return new KadaneResult(bestMaxSoFar, maxRegionStart, maxRegionEnd);
}

/*
				  Holds the result of running Kadans algorithm

				  maxSum: the actual sum of the Max Contiguous Subarray Sum region
				  startIndex: start of Max Contiguous Subarray Sum region
				  endIndex: end of Max Contiguous Subarray Sum region
				*/
private class KadaneResult {

  int maxSum;
  int startIndex;
  int endIndex;

  public KadaneResult(int maxSum, int startIndex, int endIndex) {
    this.maxSum = maxSum;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

}

/*
				  A rectangle with left, right, top, and bottom bounds. The sum
				  of all items contained within the rectangle are also recorded
				  in the interiorSum variable.
				*/
private class Rectangle {

  int interiorSum;

  int leftBorderIndex;
  int rightBorderIndex;
  int topBorderIndex;
  int bottomBorderIndex;

}

// 		10. Sort K sorted arrays. i.e. each item in the given array is at most K distance from its final/ sorted position.
// 			Approach: basically, start from the beginning of the array and add all items that can possibly occupy the position (i.e. any item at most 3 indices away including the item at the current position and has not been marked as visited) to a min heap. Then choose the smallest item and mark the item you just chose as visited.
// 			At each index we do 2K adds to a heap (that equls k*logk). And we do this for each item in the array. So our runtime is (k*logk*n). this is much better than nlogn given that k would be small.
// 		11. Merge k sorted arrays.
// 			// for this, we need to use a Heap/ Priority queue. We will use a helper class, to wrap items from the list. 
// 			// the helper class is to help us identify the list from which an item came from, so we know which list to take from when an item is remove from heap
// 			// then we will add the first item from each list into the heap.
// 			// then we will remove the top most item from the heap, and whatever list that item belogned to, we will add a new item to the heap from that list
// 			// thereafter, we will continue this process until all lists are empty (perhaps we can track this with a counter and compare the result list with the size of all lists combined)
class Solution {
  class Helper {
    public Integer item;
    public int listId;
  }

  public List < Integer > mergeKLists(List < List < Integer >> lists) {
    // result
    List < Integer > result = new ArrayList < >();

    // create the comparator
    Comparator comp = new Comparator(@Override
    public void compare(Integer first, Integer second) {
      return Integer.compare(first, second);
    });
    // create the heap
    PriorityQueue heap = new PrioirityQueue(comp);

    // add all of the first items to the heap
    // also compute total size
    int index = 0;
    int totalSize = 0;
    for (List list: lists) {
      heap.offer(Helper(index++, list.get(0)));
      totalSize += list.size();
    }

    while (result.size() < totalSize) {
      // grab the top most item 
      Helper h = heap.poll;
      result.add(h.item);

      // add a new item from the list we just removed from 
      if (lists.get(h.listId).size() > 0) {
        heap.offer(lists.get(h.listId).get(0));
      }

      // 
    }

    return result;
  }
}

// 		12. Find the k most frequent items in the list
// 				- basically go through the list and put each item in a hashmap with the value being the frequency
// 				- then create an array of Lists
// 					- the index of the array will be frequency and the list will hold all of the items at that frequency
// 				- then just go through the array from the highest frequency and collect the first k items you encounter during the traversal
// Design questions LeetCode:
//  	1. LRU Cache:
public class LRUCache {

  class DLinkedNode {
    int key;
    int value;
    DLinkedNode prev;
    DLinkedNode next;
  }

  private void addNode(DLinkedNode node) {
    /**
		     * Always add the new node right after head.
		     */
    node.prev = head;
    node.next = head.next;

    head.next.prev = node;
    head.next = node;
  }

  private void removeNode(DLinkedNode node) {
    /**
		     * Remove an existing node from the linked list.
		     */
    DLinkedNode prev = node.prev;
    DLinkedNode next = node.next;

    prev.next = next;
    next.prev = prev;
  }

  private void moveToHead(DLinkedNode node) {
    /**
		     * Move certain node in between to the head.
		     */
    removeNode(node);
    addNode(node);
  }

  private DLinkedNode popTail() {
    /**
		     * Pop the current tail.
		     */
    DLinkedNode res = tail.prev;
    removeNode(res);
    return res;
  }

  private Map < Integer,
  DLinkedNode > cache = new HashMap < >();
  private int size;
  private int capacity;
  private DLinkedNode head,
  tail;

  public LRUCache(int capacity) {
    this.size = 0;
    this.capacity = capacity;

    head = new DLinkedNode();
    // head.prev = null;
    tail = new DLinkedNode();
    // tail.next = null;
    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    DLinkedNode node = cache.get(key);
    if (node == null) return - 1;

    // move the accessed node to the head;
    moveToHead(node);

    return node.value;
  }

  public void put(int key, int value) {
    DLinkedNode node = cache.get(key);

    if (node == null) {
      DLinkedNode newNode = new DLinkedNode();
      newNode.key = key;
      newNode.value = value;

      cache.put(key, newNode);
      addNode(newNode);

      ++size;

      if (size > capacity) {
        // pop the tail
        DLinkedNode tail = popTail();
        cache.remove(tail.key); --size;
      }
    } else {
      // update the value.
      node.value = value;
      moveToHead(node);
    }
  }
}

// 	2. Binary search tree iterator:
// 		Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
// 		Calling next() will return the next smallest number in the BST.
// 		Example:
// 		BSTIterator iterator = new BSTIterator(root);
// 		iterator.next();    // return 3
// 		iterator.next();    // return 7
// 		iterator.hasNext(); // return true
// 		iterator.next();    // return 9
// 		iterator.hasNext(); // return true
// 		iterator.next();    // return 15
// 		iterator.hasNext(); // return true
// 		iterator.next();    // return 20
// 		iterator.hasNext(); // return false
// 		Note:
// 		next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
// 		You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
// 		Approach 2: Controlled Recursion
// 		Intuition
// 		The approach we saw earlier uses space which is linear in the number of nodes in the binary search tree. However, the reason we had to resort to such an approach was because we can control the iteration over the array. We cant really pause a recursion in between and then start it off sometime later.
// 		However, if we could simulate a controlled recursion for an inorder traversal, we wouldnt really need to use any additional space other than the space used by the stack for our recursion simulation.
// 		So, this approach essentially uses a custom stack to simulate the inorder traversal i.e. we will be taking an iterative approach to inorder traversal rather than going with the recursive approach and in doing so, we will be able to easily implement the two function calls without any other additional space.
// 		Things however, do get a bit complicated as far as the time complexity of the two operations is concerned and that is where we will spend a little bit of time to understand if this approach complies with all the asymptotic complexity requirements of the question. Lets move on to the algorithm for now to look at this idea more concretely.
// 		Algorithm
// 		Initialize an empty stack S which will be used to simulate the inorder traversal for our binary search tree. Note that we will be following the same approach for inorder traversal as before except that now we will be using our own stack rather than the system stack. Since we are using a custom data structure, we can pause and resume the recursion at will.
// 		Lets also consider a helper function that we will be calling again and again in the implementation. This function, called _inorder_left will essentially add all the nodes in the leftmost branch of the tree rooted at the given node root to the stack and it will keep on doing so until there is no left child of the root node. Something like the following code:
// 		def inorder_left(root):
// 		   while (root):
// 		     S.append(root)
// 		     root = root.left
// 		For a given node root, the next smallest element will always be the leftmost element in its tree. So, for a given root node, we keep on following the leftmost branch until we reach a node which doesnt have a left child and that will be the next smallest element. For the root of our BST, this leftmost node would be the smallest node in the tree. Rest of the nodes are added to the stack because they are pending processing. Try and relate this with a dry run of a simple recursive inorder traversal and things will make a bit more sense.
// 		The first time next() function call is made, the smallest element of the BST has to be returned and then our simulated recursion has to move one step forward i.e. move onto the next smallest element in the BST. The invariant that will be maintained in this algorithm is that the stack top always contains the element to be returned for the next() function call. However, there is additional work that needs to be done to maintain that invariant. its very easy to implement the hasNext() function since all we need to check is if the stack is empty or not. So, we will only focus on the next() call from now.
// 		Initially, given the root node of the BST, we call the function _inorder_left and that ensures our invariant holds. Letith see this first step with an example.
// 		Suppose we get a call to the next() function. The node which we have to return i.e. the next smallest element in the binary search tree iterator is the one sitting at the top of our stack. So, for the example above, that node would be 2 which is the correct value. Now, there are two possibilities that we have to deal with:
// 		One is where the node at the top of the stack is actually a leaf node. This is the best case and here we dont have to do anything. Simply pop the node off the stack and return its value. So, this would be a constant time operation.
// 		Second is where the node has a right child. We dont need to check for the left child because of the way we have added nodes onto the stack. The topmost node either wont have a left child or would already have the left subtree processed. If it has a right child, then we call our helper function on the nodeith right child. This would comparatively be a costly operation depending upon the structure of the tree.
// 		We keep on maintaining the invariant this way in the function call for next and this way we will always be able to return the next smallest element in the BST from the top of the stack. Again, its important to understand that obtaining the next smallest element doesnt take much time. However, some time is spent in maintaining the invariant that the stack top will always have the node we are looking for.
// 		Code:
/**
			 * Definition for a binary tree node.
			 * public class TreeNode {
			 * int val;
			 * TreeNode left;
			 * TreeNode right;
			 * TreeNode(int x) { val = x; }
			 * }
			 */
class BSTIterator {

  Stack < TreeNode > stack;

  public BSTIterator(TreeNode root) {

    // Stack for the recursion simulation
    this.stack = new Stack < TreeNode > ();

    // Remember that the algorithm starts with a call to the helper function
    // with the root node as the input
    this._leftmostInorder(root);
  }

  private void _leftmostInorder(TreeNode root) {

    // For a given node, add all the elements in the leftmost branch of the tree
    // under it to the stack.
    while (root != null) {
      this.stack.push(root);
      root = root.left;
    }
  }

  /**
			     * @return the next smallest number
			     */
  public int next() {
    // Node at the top of the stack is the next smallest element
    TreeNode topmostNode = this.stack.pop();

    // Need to maintain the invariant. If the node has a right child, call the 
    // helper function for the right child
    if (topmostNode.right != null) {
      this._leftmostInorder(topmostNode.right);
    }

    return topmostNode.val;
  }

  /**
			     * @return whether we have a next smallest number
			     */
  public boolean hasNext() {
    return this.stack.size() > 0;
  }
}

// 	3. Add and Search Word - Data structure design
// 		Design a data structure that supports the following two operations:
// 		void addWord(word)
// 		bool search(word)
// 		search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
// 		Example:
// 		addWord("bad")
// 		addWord("dad")
// 		addWord("mad")
// 		search("pad") -> false
// 		search("bad") -> true
// 		search(".ad") -> true
// 		search("b..") -> true
// 		Note:
// 		You may assume that all words are consist of lowercase letters a-z.
// 		Approach: implement a trie
class WordDictionary {
  TrieNode root;

  public WordDictionary() {
    root = new TrieNode();
  }

  public void addWord(String word) {
    TrieNode node = root;
    for (char c: word.toCharArray()) {
      int i = c - a;
      if (node.children[i] == null) node.children[i] = new TrieNode();
      node = node.children[i];
    }
    node.isWord = true;
  }

  public boolean search(String word) {
    return search(root, word, 0);
  }

  private boolean search(TrieNode node, String w, int pos) {
    // bug - 1 => handle all special cases at the very beginning will ease lots of burden;
    if (node == null) return false;
    if (pos == w.length()) return node.isWord;
    char c = w.charAt(pos);
    if (c == .) {
      for (int i = 0; i < node.children.length; ++i) {
        if (search(node.children[i], w, pos + 1)) return true;
      }
      return false; // bug - 1 => easy to forget but a must;
    }
    return search(node.children[c - a], w, pos + 1);
  }

  static class TrieNode {
    boolean isWord;
    TrieNode[] children = new TrieNode[26];
  }
}

// 	4. Serialize and Deserialize Binary Tree
// 		Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
// 		Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
// 		Example: 
// 		You may serialize the following tree:
// 		    1
// 		   / \
// 		  2   3
// 		     / \
// 		    4   5
// 		as "[1,2,3,null,null,4,5]"
// 		Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
// 		Example: Let the marker for NULL pointers be '-1'
// 			Input:
// 			     12
// 			    /
// 			  13
// 			Output: 12 13 -1 -1 -1
// 			Input:
// 			      20
// 			    /   \
// 			   8     22 
// 			Output: 20 8 -1 -1 22 -1 -1 
// 			Input:
// 			         20
// 			       /    
// 			      8     
// 			     / \
// 			    4  12 
// 			      /  \
// 			     10  14
// 			Output: 20 8 4 -1 -1 12 10 -1 -1 14 -1 -1 -1 
// 			Input:
// 			          20
// 			         /    
// 			        8     
// 			      /
// 			    10
// 			    /
// 			   5
// 			Output: 20 8 10 5 -1 -1 -1 -1 -1 
// 			Input:
// 			          20
// 			            \
// 			             8
// 			              \   
// 			               10
// 			                 \
// 			                  5   
// 			Output: 20 -1 8 -1 10 -1 5 -1 -1 
// 		Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
// 		- to understand this, understand that we abstract the problem recursively. For deserialization, we do the current node, then the left subtree then the right subtree. How do we know the right subtree recursion will pick up the correct chracters from the encoded string when it starts. We know this because the left subtree recursion will run as it is running on a complete binary tree (since there are # characters to replace null.). Every left recursion will end up
/* Definition for a binary tree node. */
public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

// Serialization
public class Codec {
  public String rserialize(TreeNode root, String str) {
    // Recursive serialization.
    if (root == null) {
      str += "null,";
    } else {
      str += str.valueOf(root.val) + ",";
      str = rserialize(root.left, str);
      str = rserialize(root.right, str);
    }
    return str;
  }

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    return rserialize(root, "");
  }
};

public class Codec {
  public TreeNode rdeserialize(List < String > l) {
    // Recursive deserialization.
    if (l.get(0).equals("null")) {
      l.remove(0);
      return null;
    }

    TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
    l.remove(0);
    root.left = rdeserialize(l);
    root.right = rdeserialize(l);

    return root;
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    String[] data_array = data.split(",");
    List < String > data_list = new LinkedList < String > (Arrays.asList(data_array));
    return rdeserialize(data_list);
  }
};

// Questions from Glassdoor:
// 	 - Diameter of a binary tree
// 	 - Number of islands (present above)
// 	 - Meeting room 
// 	 - How to check if there is a path for a string in a matrix of characters (can move left, up, right and down). Can start froom any point in matrix
// 	 - regex expression matching
// 	 - balanace paranthesis for a string
// 	 - iterative in-order traversal
// 	 - infinite chess board knight pawn problem?
// 	 - techinterviewerclub.com
// 	 - longest consecutive sorted sequence
// 	 - find inversion of array
// 	 - merge two sorted arrays in place
// 	 - given a string like ((A9a)))()()()) <- O(n) time??? and O(1) space
// 	 - regex expression matching
// 	 - count number of occurances in an array
// 	 - evaluate a mathematical expression like: (+5(*78)9 10 33)
// 	 - given a binary tree find shortest path from given node to nearest leaf. Possible traveser backwards up tree
// 	 - Given a listlist of tax brackets and what taxes are for each, and an income, determine what thtat person owes in taxes
// 	 - Given a blank nyt crossword, output where all the clues would be
// 	 - Add two binary strings represented as String
// 	 - Design simplified instagram
// 	 - print all DISTINCT permutation of a string
// 	 - given a list of points and a number k, find k closest points to the origin
// 	 - number is strobgromattic
// 	 - Give one string to sort, and another that defines the order, sort the first string in the order the second string defines
// 	 - return all subsets within a set
// 	 - what is in-order traversal of binary tree
// 	 - multiply two strings together given numbers as chars in array
// 	 - coins problem - DP
// 	 - reverse linked list
// 	 - divide 2 numbers without useing /
// 	 - merge intervals
// 	 - expression evaluation
// 	 - find k closest points to the origin in 2D plane, given an array containing /n points
// 	 - sum of node values at each level of binary tree
// 	 - read4 type question
// 	 - given dictionary and 2 words, find shortest path between 2 words by modifying only one character at a time and using obly dictionary words
// 	 - find connected components in a graph
// 	 - print diagonals of matrix
// 	 - word break
// 	 - move zeroes
// 	 - Total ways to decode a string
// 	 - edit distance
// 	 - palindromes possible from string without extra space
// 	 - given a scrabble board with scores and a series of random letters drawn, find a way to maximize the point value for a valid word and pplay it on the board if there is space and fits with the other words
// 	 - given m*n grid, and one is allowed to move up or right, find the number of paths between two grids?
// 	 - sort graph points???
// 	 - given a set of objects and a funciton, pass two objects to that function and it can tell whether one object points to another one, find oneobject that is pointed by all other objects
// 	 - compare two binar tree if they are the same
// 	 - longest increasing sub array in array
// 	 - egg drop question
// 	 - print matrix in clockwise from first element to inner element
// 	 - find the minimum depth of binary search tree
// 	 - 
// 	 There are 139 pages of questions =

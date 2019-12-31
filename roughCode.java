LRU:
class LRU {
	// interface
	// get an item
		// use hasmap to get item
		// then remove the item from its location and add it to the ebginning of the list
	// put an item
		// to put an item in the LRU, we mustfirst check if it exists, then just add it to the front.
		// otherwise create a new element and add it to the front of the linked list and a reference to it to the hashmap
	// pop from the tail

	public void put() {

	}
}

Trie implmentation:
	1. add a word
	2. retrieve a word
	as fast as possible

// have a root TrieNode
	class TrieNode {
		// current letter
		char val;
		boolean isWord = false;
		TrieNode[] next = new TrieNode[26];
	}

// to add a word we are given a word and the root TrieNode
	public void add(String word, TrieNode current, int pos) {
		if(pos == word.length) {
			curr.isWord = true;
			return; // completed adding the word
		}

		// check if the next letter is present at the current nodes next nodes
		if(current.next[word.charAt(pos)] != null) { // it is present, just need to recurse to it
			add(word, current.next[word.charAt(pos) - 'a'], i+1);
		} else {
			// we need to create a TrieNode for it at the current.next correct position, then recurse there
			TrieNode tn = new TrieNode(word.charAt(pos));
			current.next[word.charAt(pos) - 'a'] = tn;
			recurse(word, tn, word.charAt(pos));
		}

	}

	public boolean exists(String word, TrieNode node) {

	}

// serialize and deserialize a binary tree
	// serialize
	// to serialize a tree we work with the idea of operating at each node individually
	// 
	// first we will add the current node to the string, if the current node is null we add # and we return
	// then we want to get the left subtree and attach it to the end, 
	// then the right subtree
	// currentNode-LeftSubtree-rightSubtree

	// deserialize
	// the thinking is this. We use the current item to make a node. The we recursively make the left subtree and the right subtree
	// if the current item is null we return. this means we are done with the current subtree for the parent
	// for this we need to tokenize the string into a list or array first
	// then we go through the list, 
	// if the current item is null 
	// root-leftSubtree-rightSubtree




// copy list with random pointer
	Map<Node, Node> map = new HashMap<>();

	private Node getNode(Node node) {
		if(!map.get(node)) {
			Node temp = new Node(node.value);
			map.add(node, temp)
		}
		return map.get(node);
	}

	public Node copy(Node head) {

		// just iterate through the original array and update the copied array on demand
		Node run= head;

		while(run != null) {
			Node temp = getNode(node);
			temp.next = getNode(node.next);
			temp.random(node.random);
			run = run.next;
		}

		return getNode(head);

	}

// get middle of linked list
	// recursive
	// this is wrong
	public Node reverse(Node head) {
		if(head.next == null) {
			return head;
		}

		Node ret = reverse(head.next);
		head.next = head;
		return ret;
	}


// number of islands
	// double for loop
		// if a one, increment count, run BFS and flip each adjacent 1 to 0

	int count = 0;
	for(int row = 0; row < rows; row++) {
		for(int col = 0; col < cols; col++) {
			if(mat[row][col] == '1') {
				count++;
				// perform bfs starting from this node
				Queue<Integer> q = new LinkedList<Integer>();
				int location = row*row + col;
				q.add(location);
				mat[row][col] = '0';

				while(!q.isEmpty()) {
					// check up, left right and bottom each, if they are 1, then add to queue and set ot zero
					// also need to check bounds
					int loc = q.offer();
					int curRow = loc/row;
					int curCol = loc%row;
					// top
					if(row > 0 && mat[curRow - 1][curCol] == '1') {
						mat[curRow - 1][col] = '0';
						q.add((curRow - 1)*rows + curCol);
					}

					// do the same for right, left and bottom
				}

			}
		}
	}

// iterative inorder traversal of binary tree

	// we will use a stack
	public void inOrder(Node node) {
		Stack<Node> stack = new Stack<>();

		stack.push(node);
		Node current = node;
		Node beginning = null;
		Node end = null;

		while(!stack.isEmpty()) {
			// add all of its left descendants to the stack
			while(current != null) {
				stack.push(current.next());
				current = current.left;
			}

			// current should now be null, pop the last guy of the stack it is the next item to be deal in iteration
			Node last = stack.pop();

			// last can now be dealt with in whatever manner neccessary as it is its turn now
			// i.e. we will add it to the end of a linked list
			// add to end of linked list
			if(first == null) {
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

// merge sort
	public void mergeSort(int[] list, int left, int right) {
		if(left <= right) {
			return;
		}

		//split the list into two
		int mid = left + (right - left)/2;

		mergeSort(list, left, mid);
		mergeSort(list, mid+1, right);

		merge(list, left, right);
	}

	public void merge(int[] list, int left, int right) {
		// to merge these two partitions, copy both halves to temporary arrays, then merge to th resultant array
		int mid = left + (mid - left)/2;
		int[] firstHalf = new int[left - mid];
		int[] secondHalf = new int[mid - right];

		for(int i = left; i < mid; i++) {
			firstHalf[i] = list[i];
		}

		for(int i = mid; i < right; i++) {
			secondHalf[i] = list[i];
		}

		// now go through both arrays and append to resultant array "list"
		int k = left;
		int i,j = 0;
		while(i < firstHalf.length && j < secxondHalf.length) {
			if(firstHalf[i] < secondHalf[j]) {
				list[k] = firstHalf[i];
				i++;
			} else {
				list[k] = secondHalf[j];
				j++;
			}
			k++;
		}

		// need to make sure that both arrays have been added
		while(i<firstHalf.length) {
			list[k] = firstHalf[i];
			i++;
			k++;
		}
		while(i<secondHalf.length) {
			list[k] = secondHalf[j];
			j++;
			k++
		}
	}

// permutations:
		public void permute(int[] arr, int pos) {
			// recur over each position

			// if last position in array, then return
			if(pos == arr.length) {
				return;
			}

			// iterate and swap each item after me
			for(int i = pod; i < arr.length; i++) {
				Collection.swap(arr, pos, i);
				permute(arr, pos+1);
				Collection.swap(arr, pos, i);
			}
		}

// remove invalid paranthes
		// basically, for each bracket, try removing and not removing
		// when you get to the end of the brackets, make sure the current paranthesization is valid
			// how to check this quickly, every time we add a closing bracket, decrease opening amount
			// every time we add an opening bracket, increase opening amount
			// can't add a closing bracket if the current opening bracket count is 0

		// state that we pass for each recurse
			// int bracketsRemoved
			// String currentParanthesization
			// int openedBrackets
			// int index

// range query 2d
	// create a sum matrix that holds the sum of each sub matrix such that the element mat(i,j) is the bottom right corner of a square/ rectangle
	// how to create this sum matrix?
		// for each element in the matrix, to find the sum of the rectangle 

	int[][] sum = new int[mat.length][mat[0].length];

	public void initAccumulatedSum(int[][] mat) {
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[0].length; j++) {
				// for the first item, the accumulated sum is the value at the element itself
				if(i == 0 && j == 0) {
					sum[i][j] = mat[i][j];
					continue;
				}

				// if it is the top row just look to the left
				if(i == 0) {
					sum[i][j] = mat[i][j] + sum[i][j - 1];
					continue;
				}

				// if its the first column, just look top
				if(j == 0) {
					sum[i][j] = mat[i][j] + sum[i - 1][j];
					continue;
				}

				// otherwise, add the sum above, sum to the left and subtract the left top adjacent to the current element
				sum[i][j] = mat[i][j] + mat[i-1][j] + mat[i][j-1] - mat[i-1][j-1];
			}	
		}
	}
	

	// now to get the sum of a sub matrix, take the right bottom corner's cumulated sum, subtract left, top, and add top right corner adjacent
	public int getSum(int[][] topRightCorner, int[][] bottomCorner) {

	}


// Expression Add Operators
	 // for this problem, note that we must also consider multi-digit numbers, not just one digit
	 // we accomplish this with a no-op
	 // every stack frame we will pass some state.
	 // at each stack frame we need to know the current total sum, the last made operation, the current accumulated number
	 // how do we actually apply the operation? we take the current accumulated number and add it to the current digit

// find all substrings in s2 that are anagrams of s2, return start indices
	// sliding window: start with both left and right pointers at beginning
	// while (j < s1.length)
	// check count: if count == 0; retList.add(i)
	// if j-i < s2.size
		// incrment j
		// if charsInS2.exists(s.chartAt(right)); count--
	// else
		// increment both i,j and adjust count accordingly
		// if(charsInS2.exists(s.charAt(i++))) => count++
		// if(charsInS2.exists(s.charAt(++j))) => count--


// kadanes algorithm, find the largest continuous sum in an array
	// we can choose to do this recursively by using decision graph: choose current, or don't choose current
		// and then make sure that all next ones are contiguous
	// or we can solve this using kadanes, we keep the max sum at each element. Then for every new element, we see if it is better to add the last known max sum to the current element, or just start a new sequence from the current element

	public int maxSum(int[] arr) {
		int[] maxSum = int[arr.length + 1];
		maxSum[0] = 0;
		int greatest = Integer.MIN_VALUE;
		for(int i = 1; i < arr.length; i++) {
			// is the current value greater than if i added the previosly known greatest sum
			if(arr[i] < arr[i] + arr[i+1]) {
				maxSum[i] = arr[i] + arr[i+1]
			} else {
				maxSum[i] = arr[i];
			}

			if(maxSum[i] > greatest) {
				greatest = maxSum[i];
			}
		}

		// return the greatest
	}
// Search a 2d array for an item
	// for this, if the rows are also ordered, then we can do a binary search  but use the conversion of a 2d array to a 1d array method
		// i.e. element i,j can be converted as such
		int position = row*rows + col;
		int row = position/rows;
		int col = position%col;

		// then binary search
		public boolean search(int[] arr, int item) {
			int left = 0;
			int right = arr.legnth;

			while(left < right) {
				int mid = left + (right - left)/2
				if(mid == item) {
					return true;
				} else if(mid < item) {
					right = mid;
				} else {
					left = mid;
				}
				mid
			}
		}
	// now if the arrar is not row level sorted, then we have to do something else!
	// notice that if we start at the bottom left or top right, we can make a decision that will decrease the problem space
	// if we start at the bottom left, notice that if the current element is smaller, then we can move right by one,
		// if the current element is greater than what we are searching, then we would go up one

		public boolean search2d(int[][] mat, int item) {
			int row = mat.length;
			int col = mat[0].length;

			while(row >= 0 && col <= mat[0].length) {
				if(mat[row][col] == item) {
					return true;
				} if(mat[row][col] > item) { // move right
					col++;
				} else {
					row--;
				}
			}
			return false;
		}
// Given a node, find all nodes from the current node that are k distance away in a binary tree

	// go through the tree starting from the root and create a mapping for each node to its parent

		Map<Node, Node> parent = new HashMap<>();

		public void createParentMapping(Node parent, Node node) {
			if(node == null)
				return;

			if(parent != null) {
				parent.add(node, parent);
			}
			createParentMapping(node, node.left);
			createParentMapping(node, node.right);
		}

		// perform bfs while keeping account of the currene level we are at
		public List<Node> findNodesAtK(Node node, int k) {
			Queue<Node> q = new LinkedList<>();
			Set<Node> visited = new HashSet<>();
			visited.add(node);
			q.push(node);
			int level = 0;
			while(!q.isEmpty()) {
				int countAtLevel = q.size();

				// if level = k, print everything out from the queue and then return
				if(level == k) {
					while(!q.isEmpty()) {
						System.out.print(q.remove().value + "#");
					}
					return;
				}

				while(countAtLevel > 0) {
					Node cur = q.remove();
					if(!visited.get(cur.left)) {
						q.add(cur.left);
					}

					if(!visited.get(cur.right)) {
						q.add(cur.right);
					}

					if(!visited.get(parent.get(cur))) {
						q.add(parent.get(cur))
					}
					countAtLevel--;
				}
				level++;
			}

		}

// tracking if current window contains all of the desired items
	// desired window
	class SmallestSubArrayConataining { 
		// desrired window
		Map<Character, Integer> targetMap = new HashMap<>();

		// current window
		Map<Character, Integer> currentMap = new HashMap<>();

		// counter to identify if current window equals target space
		int counter = 0;

		public int getSmallestWindow(String source, String pattern) {
			int maxLength = Integer.MAX_VALUE;

			// intialize targer map
			for(Character c: patter) {
				targetMap.set(c, targetMap.getOrDefault(c, 0)+1);
				count++;
			}

			// now go through the source string and update counter accordingly
			int i,j = 0;
			while(j < source.length) {
				// if source[j] is a match in the target and the count of current window is less than the count of target, incrment counter
				char temp = source.charAt(j);

				if(targetMap.contains(temp) && tagetMap.get(temp) < currentMap.get(temp)) {
					counter--;
				}

				// add the item to the current window
				currentMap.set(c, currentMap.getOrDefault(c, 0)+1);

				// update the max length if applicable
				if(counter == 0 && (j-i) < maxLength)
					maxLength = j-1;

				// if we have a valid window then try decreasing until we can't decrease anymore
				while(counter == 0 && i <= j) {
					// remove the character at the beginning, but first check if it contributed to count
					if (source.charAt()) {

					}

				}

			}

		}
	}

// valid palindrome, only considering characters
	public boolean isValidPalindrome(String str) {
		int i = 0;
		int j = str.length -1;
		while(i < j) {
			// make sure we are dealing with a character
			while(!str.charAt(i).isCharacter)i++;
			while(!str.charAt(j).isCharacter)j--;

			if(i < j && )

		}
	}

// one edit distance
	// 2 strings can be 1 edit distance apart if either one of them is missing a letter, or if they are one char off
	// check the size to determine if there is a missing character
		// if there is a missing character, iterate through both strings until you find the first mismatch
		// skip the mismatched letter in the longer string (i.e. need a way to identify the longer string)

	public boolean isOneEdit(String longer, String shorter) {
		// check if they are the same size
		if(longer.length == shorter.length) {
			// go through until the first mismatch, then skip 1 element in both strings
			int i = 0;
			int mismatches = 0;
			while(i < longer.length) {
				if(longer.charAt(i) != shorter.charAt(i))
					mismatches++;
			}
			if(mismatches>1) {
				return flse;
			}
		} else {
			
		}	
	}


// Longest Substring with At Most K Distinct Characters
	// create a sliding window
	// if there are <=k characters present in the current window, then we need grow the window
	// if, by growing the window, there are >k distinct chracters then do the following
		// save the current size of the window if it is the largest seen so far
		// remove 1 character (all of its occurances from the window)
			// how to accomplish this?
				// we want to remove as less characters as possible (to get the largest window)
					// to accomplish this we must remove the character that was the oldest added character
					// for this we can use a linked hashmap
	//code
	public int largestWindow(String pattern, int k) {
		int longestWindow = Integer.MIN_VALUE;
		int start, end = 0;

		int distinctCount = 0; // <--- can use the linkedHashmap size instead of creating an extra variable
		Map<Character, Integer> window = new LinkedHashMap<>();
		// while
		while(end < pattern.length) {
			// check if current end contains a new char or an existing char
			if(window.get(pattern.charAt(end))) {
				window.put(pattern.charAt(end), window.get(pattern.charAt(end))+1);

			} else { // increment distince counter
				distinctCount++;
				window.put(pattern.charAt(end), 1);
			}

			// check if distinct count is greater than k
			if(distinctCount > k) { // then remove the oldest added (leftmost character) character
				window.removeLast();
				distinctCount--;
				// update the indices
				int lastItemPosition = window.getLast();
				start = lastItemPosition;
			}

			// now check if the window is the biggest we've seen so far
			if(end - start + 1 > longestWindow) {
				longestWindow = end - start + 1;
			}

		}
		return longestWindow;
	}

// validate IP address IPV6 o IPV4 from a string
	// use the split method to split the items up into parts for ipv6 and ipv4
	// ipv6 is split using ':' and ipv4 is split using '.'
	// checks
		// 1. the number of components is correct: ipv6: 8, ipv4: 4
		// 2. each component contains valid chracters: ipv4: only digits 0-256, and no starting zeros ipv6: ?
		// 3. each component contains
	public boolean validateIP() {

	}
// Subarray Sum Equals K: Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
	// Approach: iterate through array; and accumulate sum
		// at each iteration, see if (currentSum - k) exists in the set
		// add accumulated sum to a set
		// carry on till end of arry
		public int countOfDesiredContinuousSum(int[] arr, int k) {
			Set<Integer> accumulatedSums = new Set<>();
			int currentSum = 0;
			int count = 0;

			for(int i = 0; i < arr.length; i++) {
				currentSum += arr[i];
				if(accumulatedSums.contains(currentSum - k)) count++;
				accumulatedSums.add(currentSum);
			}

			return count;
		}
	// mistakes/ improvements: there can be more than one isntance of a sum. therefore, you should use a hashmap, and store the number of times you have seen a sum. Then when you have to update count, use the count from the hashmap.
		// start from the two ends, compare both items. if there is a mismatch, try recursing by skipping either the left or right

		public boolean isOneAwayFromPalindrome(String pattern) {
			int i = 0;
			int j = pattern.length;
			while(i < j) {
				if(pattern.charAt(i) != pattern.charAt(j)) {
					// 1. skip the left character
					// 2. skip the right character
					return isPalindrome(pattern.substring()) || isPalindrome(pattern.substring());
				}
			}
			return true;
		}

		public boolean isPalindrome(String s) {
			int i = 0;
			int j = s.lenght;
			while(i < j) {
				if(s.charAt(i) != s.charAt(j)) {
					return false;
				}
				i++;
				j--;
			}
			return true;
		}



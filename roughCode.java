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

	public void serialize(Node node, List<Character> list) {
		if(node == null) {
			list.add('#');
			return;
		}

		// place current item in the list, 
		list.add(node.value);
		// then the left subtree
		serialize(node.left, list);
		// then the right subtree
		serialize(node.right, list);
	}

	public Node deserialize(List<Chracter> list) {
		if(list.remove(0) == '#' || list.isEmpty()) {
			return null;
		}

		// create the current node from the top of the list
		Node temp = new Node(list.get(0));
		// now append the left subtree
		temp.left = deserialize(list, node.left);
		// and right
		temp.right = deserialize(list, node.right);

		return temp;
	}




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

// Alien dicitonary
	// given a dictionary of words, create a adjacency graph
	// go through all of the words, 2 at a time
		// for each pair, while the letters are equal, go through them until you find an unequal letter, 
			// for that pair, create an entry in adjacency matrix for the second letter, having a 1 in the column of first letter
		int[][] mat = new int[26][26];
		public void createGraph(String[] dict) {
			for(int i = 1; i < dict.length; i++) {
				// compare the current and previous character
				int j = 0;
				while(/*check bounds*/ && dict[i].charAt(j) == dict[i].charAt(i-1) ) {
					j++;
				}
				// create a dependency: for the letter at dict[i].charAt(j) to the character dict[i-1].charAt(j)
				mat[dict[i].charAt(j) - 'a'][dict[i-1].charAt(j) - 'a'] = 1;
			}
		}

		// code to go through each letter in the adjacency graph and perform a depth first search
		// note, a character will be in either non-visited state (doesn't exist in map), the visisted state or visiting state
		Map<Chracter, Integer> visited = new HashMap<>();
		public void order() {

			for(int letter = 0; letter < mat.length; letter++) {
				if(visited.get(letter) == null) { // only if the current letter hasn't already been touched
					if(!dfs(letter)) return false;
				} 
			}
		}

		public boolean dfs(char letter) {
			// check if this isn't already being visited, in that case we are trying to visit something twice, a cycle!!
			if(visted.get(letter) == 1) {
				return false;
			}

			visited.put(letter, 1); // visiting
			// check all of its dependencies
			for(int i = 0 ) {

			}
		}	

// shortest distance from all buildingds
	// run through the grid with a double for loop
	// will have two meta data matrices
		// 1. one for showing if its possible to reach a position by how many buildings
		// 2. one for tracking the distance sum from each building to each position
	// for each building run a BFS, and for each level update the reachability metrix and distance matrix with the level
	// at the end, go through the reachability matrix and and for each element that can is building number reachable,
		// store the minimum distance

// diameter of binary tree, -> longest path between any two nodes
	// approach: go through each node and compute the lenght of the longest left path and the longest riht path
	// we can either return the longest left path of longest right path
	// also need to check, if current node is the pivot for longest path
		int diameter = Integer.MIN_VALUE;
		public int longestPath(Node node) {
			if(node == null) {
				return 0;
			}

			// get the longest path from the left and right
			int leftPath = longestPath(node.left);
			int rightPath = longestPath(node.right);

			diameter = Math.max(leftPath + rightPath + 1, diameter);

			return Math.max(leftPath + 1, rightPath + 1);
		}
		
// merge accounts
	// iterate through the accounts
		// for each account hold the first email (root email)
		// for each email in account, add it to root email's list
		// for each email, make a reference to the root email
	// now we have a dependency graph

	// then we go through each email, and if unvisited we perform a DFS on it while maintining a set and adding to the set
	// the trick here is also that we can perform the DFS iteratively instead of using recursion to make it inline

		// create a graph
		public void createGraph() {

		}

// Convert Binary Search Tree to Sorted Doubly Linked List

	// with in order traversal, we print the left most sub tree first, then the current node, then the right subtree
	// update the first pointer the first time that we see left is null, i.e. return from the left subtree recursion
		// this doesn't change afterwards
	// now, we are done with the left subtree and the first node is set
	// but what about when we return to the current node.
		// we need to add it to the end of the left subtree
		// how do we get the last node in the left subtree (after its been converted to a DLL)?
		// notice, that when we have completed the left subtree for a node, then that means there is no node before the current node that hasn't already been touched
		// this implies, that the current node, will always be the last node of the linked list
		// so when the left subtree is done, we need to attach the current node to the end of the linked list (and last will be pointint to it)
		// then we update last to the current node
	// now current is the last node
	// what about the right subtree
	// we just need to do the same recursion on it, to transform it into a DLL (we don't need to worry about the first node)
		// then use the current node to connect it to the end

	// in order traversal
	Node first = null;
	Node last = null;
	public Node toDLL(Node node) {
		if(node == null) {
			return null;
		}

		toDLL(node.left);
		if(first == null) {
			first = node;
		} else {
			// otherwise get the last node and point it to current, and current's left to it
			last.right = current;
			current.left = last;
		}

		// the left subtree is done, that means that the current node is the last node of the DLL so far
		last = current;
		toDLL(node.right); // last is already connected to the right tree
	}

	// how to do it iteratively as above
	public Node toDLL(Node node) {
		Stack<Node> stack = new Stack<>();
		stack.push(node);
		Node current = node;

		Node first = null;//= current;
		Node last = null;//= current;
		while(!stack.isEmpty()) {
			while(current != null) {
				stack.push(current);
				current = current.left;
			}

			Node temp = stack.pop();
			current = temp.right;
			// System.out.println("it is this nodes turn: " + temp.value);
			// it is temp's turn to get added to the DLL
			if(first == null) {
				first = temp;
			} else {
				// otherwise just add this node to the end of the list
				last.right = temp;
				temp.left = last;
			}

			last = temp;
		}

		// now connect the last and the first nodes
		first.left = last;
		last.right = first;

		return first;
	}

// is graph bi-partite
	// node colouring
	// for each node, need to perform DFS
	// for each neighbour, we need to make sure the node is the opposite colour
	// basically, we will need to colour each adjacent node, and also mark if it has been visited or not
	// do we need a visited map? when do we check if its visited or not? 

// remove least number of brackets to make brackets valid
	//basically, we will try to leave in, and remove each bracket
	// but to make it more efficient we will also keep track of open and closed bracket count
	// we will also have a count of how many removed
	// also when we finish considering each bracket, we will to closed and open bracket count to see if its valud
	// we will never add a closed bracket if the count of open brackets is less

	public int leastRemoval(String expression) {

	}

	int minRemoval = Integer.MAX_VALUE;
	public void helper(String expression, int index, int openCount, int closedCount, StringBuilder current, int removedCount) {
		if(index == expression.length) {
			// check if the counts are equal
			if(openCount == closedCount) {
				// see if removedCount is less than global min
				minRemoval = Math.min(removedCount, minRemoval);
				// do something with the created expression
			}
		} else {
			// otherwise we need to try keeping and removing the current bracket
			// but first, make sure there are no non-bracket character
			if(expression.charAt(index) != '(' || ')') {
				current.append(expression.charAt(index));
				helper(expression, ++index,  openCount, closedCount, current, removedCount);
			} else {
				// now try removing the current char
				helper(expression, ++index,  openCount, closedCount, current, ++removedCount);

				// now we will try adding the current bracket
				current.append(expression.charAt(index))
				helper(expression, ++index,  openCount, closedCount, current, removedCount);
				current.removeLast(expression.charAt(index))
			}
		}
	}

// binary search tree iterator
	// what is the interface, getNext(), init(Node head)
	class BSTIterator {
		Stack<Node> stack = new Stack<Node>();
		public init(Node head) {
			while(head != null) {
				stack.push(head);
				head = head.next;
			}
		}

		public Node getNext() {
			// get the top most node, then add its right tree to the stack
			// then return the top most node you had got before
			Node ret = stack.pop()
			Node current = ret.right;
			while(current != null) {
				this.stack.push(current);
				current = current.left;
			}
			return ret;
		}
	}

// merge sort

	public int mergeSortInversionCount(int[] arr, int left, int right) {
		if(left >= right) {
			return;
		}
		int mid = left + (r - l)/2;

		// get the left array sorted
		mergeSortInversionCount(arr, left, mid);

		// get the right array sorted
		mergeSortInversionCount(arr, mid + 1, right);

		// merge the two array
		return mergeCount(arr, left, right);
	}

	// merge the halves of the array
	private int mergeCount(int[] arr, int left, int right) {
		int mid = left + (right - left)/2;
		// create two temporary copies of the left and right array
		int[] arrLeft = new int[mid - left];
		int[] arrRight = new int[right - mid];

		// copy the left array into left and right into right
		for(int i = left; i < mid; i++) {
			arrLeft[] = arr[i];
		}	

		for(int i = mid; i < right; i++) {
			arrRight[i] = arr[i];
		}

		int first, second = 0;
		int last = left;

		while(first < arrLeft.l) {

		}
	}


// permtations in a string
	// start with two pointers in the beginning of the string

	/**
	 * return true if s2 contains the permutation of s1
	*/
	public boolean containsPermutation(String s1, String s2) {
		int i,j = 0;

		//Map<Character, Integer> map = new HAshMap<>
		int[] charCount = new int[26];
		int[] windowCount = new int[26];
		// 
		int required = 0;
		for(Character c: s1) {
			charCount[c - 'a']++;
			required++;
		}

		// now go through the array and just make sure
		while(j < s1.length) {

			if(windowCount[s1.charAt(j)]++ < charCount[s1.charAt(j)]) { // we also need to do an increment
				required--;
			}	

			if(required == 0) {
				return true;
			} 

			// check if the window is too big
			if(j - 1 > s2.length) {
				// need to remove a character and possible increment required count
				if(windowCount[s1.charAt(i)] <= charCount[s1.charAt(i)]) {
					required--;
				}
				i++;
			}
			j++;
		}
	}

// add expression
	// Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.


	public boolean evaluteToTarget(int target, String givenExpression, int currentExpression, String lastExpression, int currentResult) {


		// 4 branches we can make
		// 1. no-op: in order to increase currentExpression
		// 2. add
		// 3. subtract
		// 4. multiply
			// for this we will need to undo the last operation (if it was not multiplication), and then apply the multiplication

		// return when there are no more givenExpression characters we can look at
		if(givenExpression == null || givenExpression.equals("")) {
			if(currentResult == target) {
				return true;
			} else {
				return false;
			}
		}

		// add to the current expression
		int currentDigit = Integer.valueOf(givenExpression.charAt(0));
		evaluteToTarget(target, givenExpression.substring(1), (currentExpression*10) + currentDigit, lastExpression, currentResult);

		// we will add the current expression to the currentResult
		evaluteToTarget(target, givenExpression, 0, -1*currentExpression, currentResult + currentResult);

		// subtract
		evaluteToTarget(target, givenExpression, 0, 1*currentExpression, currentResult - currentResult);

		// multiply 
			// first we need to undo the last operation
		currentResult = currentResult + lastExpression;
		evaluteToTarget(target, givenExpression, 0, 0, (currentResult*currentExpression) - lastExpression);
	}

// find bad version
	public int firstBadVersion(int n) {
		int left = 0;
		int right = n;

		while(left < right) {
			int mid = left + (right - left)/2;
			boolean current = isBad(mid);
			boolean prev = !isBad(mid - 1);
			if(current && prev) {
				return mid;
			} else if(!current) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
	}

// peak element
	// at the curren element, you can either be increasing or decreasing
		// incresing: if the element after it is greater
		// if the element before it is greater
	// if we are increasing, then that means we are headed to a peak and should search right
	// if decreasing then we should head left, to find the peak

// merge intervals
	// Merge Intervals
				// Given a collection of intervals, merge all overlapping intervals

				// Example 1:

				// Input: [[1,3],[2,6],[8,10],[15,18]]
				// Output: [[1,6],[8,10],[15,18]]

	public List<int[]> mergeIntervals(int[] intervals) {
		// assert null/ empty checks

		List<int[]> result = new ArrayList<>();
		Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
		result.add(intervals[0]);
		for(int i = 1; i < intervals.length; i++) {
			int[] interval = intervals[i];
			int[] current = result.getLast();

			if(current[1] > interval[0]) {
				result.removeLast();
				result.add(new int[])
			}
		}
	}

// pow
	public int pow(int base, int exp) {
		if(exp == 0) 
			return 1;

		int halfPow = pow(base, exp/2);
		int money;
		if(exp%2 == 0) {
			money = halfPow*halfPow;
		} else {
			money = halfPow*halfPow*base;
		}
		return money;
	}

// divide two integers
	// actors: dividend, divisor, quotient
	public int divide(int dividend, int divisor) {
		if(dividend < divisor) {
			return 0;
		}

		int quotient = 1;
		int sum = divisor;
		while(sum < (dividend - sum)) {
			sum += sum;
			quotient += quotient;
		}

		return quotient + divide(dividend - sum, divisor);
	}

// root leaf paths

	public void paths(Node node, List<List<Node>> paths, List<Node> path) {
		if(node == null) {
			// add the current path to the paths
			paths.add(path);
			return;
		}

		path.add(node);
		paths(node.left. paths, path);
		paths(node.right, paths, path);
		path.remove(0);
	}

// alien dicitonary
	// create a graph from the array of words
		// - go two at a time form the second to the last
			// - compare each letter till you get a mismatch, make an edge from the second to the first letter

	public int[][] buildGraph(int[][] deps, String[] dict) {
		for(int i = 1; i < dict.length; i++) {
			String wrd1 = dict[i-1];
			String wrd2 = dict[i];
			int j = 0;
			while(wrd1.charAt(j) == wrd2.charAt(j)) {
				j++;
			}
			// now we have the index of mismatch, create an edge
			deps[wrd2.charAt(j)][wrd1.charAt(j)] = 1;
		}

		return deps;
	}

	// now go through the dep graph with DFS,
	// for each letter you can be either, unvisited, visiting or visited
	// if you run into another visiting while visiting another letter, then we have a circular dependency
	public String alien(int[][] deps) {
		int[] visited = new int[26];

		// go through each character
		for(int i = 1; i < deps.lenght; i++) {
			if(visited[i] == 0) { // unvisited
				dfs(deps, i);
			}
		}

	}

	public boolean dfs(int[][] deps, int current) {
		// mark as visiting
		visited[current] = 1;

		// now go through all of currents neighbours
		for(int i = 0; i < deps[0].length; i++) {
			// skip my self
			if(i == current) {
				continue;
			}

			if(deps[current][i] == 1) { // we have an edge
				// just make sure its not being visited also
				if(visited[i] == 1) {
					return false;
				} else if( dfs(deps, i)) {
					continue;
				}
			}
		}

		// visited all of its neighbours, mark it as done, and add to the string builder
		visited[current] = 2;
		builder.append(Character.valueOf(current));
	}

// shortest distance from all buildings
	// get count of all buildings
	// double for loop to go through the map
	// if element equals 1, perform bfs layer and update distance meta matric, reachability meta matrix also
	// at the end, search the reachability matrix and choose the highest value 
	// note, for each bfs starting from a building, we need to have a new visited matrix

// accounts merge
	// creating a graph
		// - can be accomplished using a hasmap
			// map each email to its root email, i..e the first email in the group

	// then perform DFS on each item in the hashmap and using a visited set to mark the ones we already visited
		// for each DFS traversal, we have a list/set that where we will add all encountered emails to
	// return all such sets

// Quick sort
	public void quickSort(int[] arr, int left, int right){
		// partition
		int partIndex = partition(arr, left, right);

		// recurse left 
		quickSort(arr, left, partIndex);

		// recurse right
		quickSort(arr, partIndex + 1, right);

	}

	public int partition(int[] arr, int left, int right) {
		// use the last element in the array as the pivot
		int pivot = arr[right];

		// start one before the array
		int beg = (left - 1);

		for(int i = left; i < right; i++) {
			if(arr[i] < pivot) {
				beg++;
				swap(arr, i, beg);
			}
		}

		// at the end we want to swap the pivot and the ending list
		beg++;
		swap(arr, beg, right);

		return beg;
	}

// merge sort
	public void mergeSort(int[] arr, int left, int right) {
		if(left == right) { 
			return arr;
		}

		int mid = left + (right - left)/2;
		// sort the left side, 
		mergeSort(arr, left, mid);
		// and the right
		mergeSort(arr, mid+1, right);

		// now merge them
		merge(arr, left, right);
	}

	public void merge(int[] arr, int left, int right) {
		int mid = left + (right - left)/2;
		// copy both halves into an array
		int[] leftSide = new int[mid - left + 1];
		int[] rightSide = ned int[right - mid];

		// initialize both arrays
		for(int i = left; i <= mid; i++) {
			leftSide[i] = arr[i];
		}

		for(int i = mid + 1; i < right; i++) {
			rightSide[i] = arr[i];
		}

		int i, j = 0;
		int k = left;
		while(i < leftSide.lenght && j < rightSide.length) {
			// for the inversion count stuff, need to perfrom the calculation here
			// so if right < left, then it is also smaller than everything after current left
				// so increment count with size of left from i to its end
				// i.e. inversions += left.length - i
			if(leftSide[i] <= rightSide[j]) {	
				arr[k] = leftSide[i];
				i++;
			} else {
				arr[k] = rightSide[j];
				j++;
			}
			k++;
		}

		// if any of the arrays are still remaining add them to the end of the list


	}



// 3 sum
	// sort the array
	// for each item in the sorted array

	public boolean 3Sum(int[] arr) {
		Arrays.sort(arr);
		for(int i = 0; i < arr.length - 2; i++) {
			int left = i + 1;
			int right = arr.length;
			int target = arr[i];
			int sum = arr[left] + arr[right];
			while(left < right) {
				if (sum == target) {
					return true;
				} else if(sum < target) {
					// then make number bigger by moving left up
					left++;
				} else {
					right--;
				}
			}
		}

		return false;

	}
// remove dupliucate from sorted array
	// pointer at beginning 
	// runner pointer also
	// if runner doesn't equal arr[beg], increment beginning and make that value equal to arr[runner]
	// return index of end of non-dup array 

	public int removeDups(int[] arr) {
		int endOfNonDupsArra = 0;
		for(int i = 1; i < arr.length; i++) {	
			if(arr[endOfNonDupsArra] != arr[i]) {
				endOfNonDupsArra++;
				arr[endOfNonDupsArra] = arr[i];
			}
		}

		return endOfNonDupsArra+1;
	}

// next permutation
	// a slot must go from the smallest number to the highest number incrmentally
	// there is always one slot that is at its highest number
	// to find that slot, start from the end of the array and run backwards until we find a non decreasing point
	// that point we need to increment
		// to increment, we look to the right for the next larger element, and swap 
		// then, we reverse the array from the pivot

	public void reverese(int[] arr, int from) {
		int i = from;
		int j = arr.length;
		while(i > j) {
			swap(arr, i, j);
		}
	}

	public void swap(int arr[], int first, int second) {
		int temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}

	public void incrementNextPermutation(int arr[]) {
		// find the slot to increment
		int pivot = -1;
		for(int i = arr.length - 2; i >= 0; i--) {
			if(arr[i] < arr[i+1]) {
				pivot = i;	
			}
		}
		// do check if pivot is valid

		// now find the next item greater than the pivot
		int greater;
		for(int i = arr.length - 1; i > pivot; i++) {
			if(arr[i] > pivot)
				greater = i;
		}
		swap(arr, pivot, greater);

		// now reverse the array after pivot
		reverse(arr, pivot+1);
	}






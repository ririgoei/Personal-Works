Riadiani Marcelita and Bendik Svalastog
CS 146
Anna Shaverdian
Project 3: README.txt

1. Who is in your group?
- Our group consists of two members, Riadiani Marcelita and Bendik Svalastog.

2. How long did the project take?
- The project takes approximately two weeks, from September 21 to October 5.

3. Before you started, which data structure did you expect would be the fastest?
- We expected the Hash Table to be the fastest data structure.

4. Which data structure is the fastest? Why were you right or wrong?
- We received the following results as to the time it took each data structure to complete the entire runtime:
1) BST: 0.11309341 seconds
2) AVL: 7.739418316 seconds
3) Hashtable: 0.712809658 seconds
Our prediction that hashtable is faster than BST and AVL turns out to be wrong, although BST and Hashtable's runtime are not that different.

5. In general, we think that hash table implementation is better, due to several reasons:

	1) Ease of code: coding a hash table is much easier because it is straightforward and has a simple enough formula (hash % default array size).
	It is also very much like implementing an array, something that is considered relatively simple. We had no trouble inserting things into the
	array. As for BST and AVL, they are slightly more complicated because we have to make an algorithm to compare the values we insert to determine
	whether to put them on the left or right subtree. Additionally, for AVL tree, we needed to make an algorithm to balance the tree every time it
	is imbalanced. This process of balancing actually took us a while to solve, since we encountered problems with implementing the rotations, so it
	took us longer to actually solve for the AVL tree than it did for us to create the hash tables.

	2) Ease of debugging: hash table is easier to debug because the elements are stored in the array, and we could easily go through each index in the array
	either by normal debugging or by printing out each index of the array, using a simple for loop. To debug the BST and AVL tree, we had to go through
	the entire tree from root to leaf, and it did get confusing, for we had to visualize the tree on our own to make sure we were inserting and rotating
	things correctly. This process of having to debug the tree and visualizing what it looked like was quite difficult to do with a large amount of data.

	3) Runtime for average input: hash tables and BST are close when it comes to runtime for average input, they are about 0.6 seconds apart in implementation.
	However, we do still think hash table is better to implement, because the runtime difference is not too significant compared to our BST's. We think that
	if we find a different way/formula to create our hash code, we might get a faster runtime than we currently do. Hash table is definitely and significantly
	much faster in runtime than AVL, so it is definitely better compared to the two.

6. In the correlator, the hash table performs relatively better than the other two data structures. We decided which data structure is better in correlator by
	measuring the total runtime in seconds. The data we received is as following:

	- BST: 4,736,701 seconds.
	- AVL: 3,743,884 seconds.
	- Hash table: 3,286,360 seconds.
	
	Based on the data we found, hash table performs relatively better than BST and AVL for texts as large as Hamlet and The New Atlantis. 
	This might be because the hash table is already in the form of an array when we correlate them, while we need to retrieve the nodes of the BST and AVL trees.
	However, we also did an analysis of runtime by correlating two very short documents (not more than 8 words long), and our data is as follows:
	
	- BST: 1,177,563 seconds.
	- AVL: 1,243,883 seconds.
	- Hash table: 1,939,051 seconds.
	
	Correlating two relatively short documents shows that our hash table has a longer runtime than our BST and AVL, which is a very interesting found for us.
	We reckon this is because with shorter documents, the BST and AVL trees do not have to insert and balance many words, thus taking lesser time to do the entire
	operation than they do with larger/longer documents. However, we believe that the reason our hash table is faster than our BST and AVL trees for larger documents
	is because for hash tables, the data is already stored in an array, and it is easy to get through the indexes of the array and getting each element. Whereas for BST
	and AVL trees, we have to put them in nodes, compare the data with each node to decide which subtree it should go to, put them into an array in traversed order, then 
	correlate it. That might be why they take a longer time than the hash table.
	
	We also measured the data structures' memory usage for correlator, and this is the data we got:
	
	- BST: 6,134,384 bytes.
	- AVL: 6,134,464 bytes.
	- Hash table: 7,497,656 bytes.
	
	Based on memory usage, hash table performs the worst, while BST performs the best. We reckon this is because in hash table's array, we have some additional spaces/
	slots that are empty, since every time the array is full we perform a rehash that expands the size of the current array. However, each time we rehash, there might be
	more spaces available than there are words, so it results in empty slots, which takes up additional memory and causing it to have a higher memory usage than the BST
	and AVL tree.

7. We think Shakespeare's stories were written by Francis Bacon, due to the relatively small frequency a comparison of their two works gave us. We received a
	frequency of roughly 4.564351156875463E-4 between Hamlet and The New Atlantis, giving us a reason to believe that the two works are quite similar.
	
8. Benchmarking is done in a separate file.

9. For this project, we liked learning about hash table implementations, and being able to explore more with BST and AVL trees. We got a better grasp on how to
	implement hash tables, though it is still sometimes a very abstract concept for us. One part we did not enjoy was to make sure all our data were inserted correctly
	in all three of our data structures, then comparing their performances. We did not enjoy it mainly because we were handling a large portion of data/words, and
	we second-doubted our results many times. We did run tests with smaller portions of data, which was relatively simpler for us, but once we got to the main texts for
	Hamlet and The New Atlantis, it was rather tricky and at times tedious to test and compare the three data structures. Other than that, the assignment was quite
	enjoyable if not challenging, and taught us a lot about the three data structures used.
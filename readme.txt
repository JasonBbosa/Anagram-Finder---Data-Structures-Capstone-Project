time java AnagramFinder least words.txt bst =
    (1.519s + 1.531s + 1.370s + 1.446s + 1.395s) / 5 = 1.4522s

time java AnagramFinder least words.txt avl =
    (0.657s + 0.720s + 0.672s + 0.585s + 0.555s) / 5 = 0.6378s

time java AnagramFinder least words.txt hash =
    (0.392s + 0.286s + 0.281s + 0.280s + 0.269s) / 5 = 0.3016s

What data structure do you expect to have the best (fastest) performance? Which one do you expect
to be the slowest? Do the results of timing your program’s execution match your expectations? If so,
briefly explain the correlation. If not, what run times deviated and briefly explain why you think this
is the case.
Answer: Before timing my program and calculating the average execution time, I initially expected that the hash map data structure would perform the best or the fastest because its insertion and lookup time complexity is O(1). On the other hand, I expected the BST data structure to be slowest as its insertion and lookup time complexity is O(n) compared to AVL's time complexity of O(lg n). To no surprise, the results of timing my program’s execution match my expectations since the runtime for each data structure in increasing order is hash map, then AVL, then finally BST.
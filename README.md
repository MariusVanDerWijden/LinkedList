# LinkedList

I noticed, that the Java.util.LinkedList.addAll runs in O(n), but it could run in O(1)
rewrote it, so that it runs in O(1) if two LinkedLists are and O(n) if a different Type of List is added

It used to work by taking every element of the second list and adding it to the first.
My implementation takes advantage of the LinkedList properties, by appending the first list to the second one.

I provided a variety of TestCases to ensure the Correctness of my Implementation.

list.AddAll();

Measured times with lists of size: 1000 (average of 5000 trials)
Java.util.LinkedList:   17272 ns
New Implementation:     338 ns

Measured times with lists of size: 10000 (average of 5000 trials)

Java.util.LinkedList:   104362 ns
New Implementation:     472 ns

Measured times with lists of size: 100000 (average of 5000 trials)

Java.util.LinkedList:   1041257 ns
New Implementation:     1880 ns




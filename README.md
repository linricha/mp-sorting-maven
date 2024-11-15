# mp-sorting-maven

An exploration of sorting in Java.

Authors

* Richard Lin
* Samuel A. Rebelsky (starter code)
* ChatGPT (LinRichardSort.java)

Acknowledgements

* Chat GPT for providing LinRichardSort.

This code may be found at <https://github.com/linricha/mp-sorting-maven.git>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-sorting-maven>.

Description of custom sorting algorithm
It is just TimSort. Basically, checking when it would be better to do insertion sort vs merge sort.

Notes on using Copilot (or other AI)
------------------------------------

Used Deep AI to start. I asked it to create a worst time complexity sorting algorithm of O(n). Told me that
there were three fast sorting algorithms and modified one of them for me. I ask it with restraints like writing it in
Java 17, be given a comparator for sorting, etc. and it gave me back bad code. I prompted it to give me code that would fit
the test, but it instead ignored my previous requests and made it only work for integers and strings, supposedly.
Tried to make it for objects as well and added prompt of keeping previous considerations / requirements and it gave me wrong
code 3 times in a row before I decided to stop. I went to ChatGPT. It told me it was impossible to do. I asked it for the best sort
for objects. It gave me TimSort. I asked it with similar prompts and it gave me code that passed my tests.

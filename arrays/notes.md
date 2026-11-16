# Array Fundamentals: my learning notes

Learned step by step, one idea at a time. All notes are below, and the complete runnable program is at the end of this file.

Run the program (Java 11+): save the code at the bottom as `ArrayFundamentals.java`, then `java ArrayFundamentals.java`

---

## 1. What an array is

A row of boxes in memory, side by side, numbered from **0**.

```
index:   0   1   2   3   4
value: [ 4 | 2 | 7 | 1 | 9 ]
```

- Same type in every box, fixed size, boxes are next to each other in memory.
- Java finds `arr[i]` by arithmetic: `start + i * size of one element`. That is why access by index is **O(1)**.
- The last valid index is `length - 1`. Going past it throws `ArrayIndexOutOfBoundsException`.
- `arr.length` (field), `str.length()` (method), `list.size()` (method).
- An array variable holds a **reference**. `int[] b = a;` does not copy, both names point to the same array.

| Operation | Time |
|---|---|
| Access / update by index | O(1) |
| Search (unsorted) | O(n) |
| Insert / delete at the end | O(1) |
| Insert / delete at the start or middle | O(n) (shifting) |

`ArrayList` is a growing array: when full, it makes a bigger array (about double) and copies. Adding at the end is **O(1) amortized**.

Reading code: `arr[3] = arr[2];` means *copy the value in box 2 into box 3*. In programming `=` means "put the right side into the left box".

---

## 2. Insert in the middle

Chairs picture: to seat X in chair 2, the last person moves first into the empty chair, then the one before, and so on.

```java
for (int i = size; i > index; i--) {
    arr[i] = arr[i - 1];
}
arr[index] = value;
```

Trace: `{4, 5, 6, 0}`, size 3, index 1, value 9

| Round | i | `i > 1`? | Line | Array after |
|---|---|---|---|---|
| 1 | 3 | yes | `arr[3] = arr[2]` | {4, 5, 6, 6} |
| 2 | 2 | yes | `arr[2] = arr[1]` | {4, 5, 5, 6} |
| 3 | 1 | no | stop | {4, 5, 5, 6} |
| after | | | `arr[1] = 9` | {4, 9, 5, 6} |

Rule: **shifting right starts from the end** (otherwise you overwrite values you have not moved yet). O(n) time, O(1) space.

## 3. Delete from the middle

Everyone after the gap moves one step left, starting with the person right after the gap.

```java
for (int i = index; i < size - 1; i++) {
    arr[i] = arr[i + 1];
}
return size - 1;      // the last box is a leftover we ignore
```

Rule: **shifting left starts from the gap**. The array keeps its length, so we track a separate logical `size`. O(n) time, O(1) space.

## 4. Swap and reverse

Swapping needs a spare cup:
```java
int temp = arr[a];
arr[a] = arr[b];
arr[b] = temp;
```

Reverse with two pointers moving inward:
```java
while (left < right) {
    // swap arr[left] and arr[right]
    left++;
    right--;
}
```

Trace: `{1, 2, 3, 4}`

| Round | left | right | `left < right`? | Array after |
|---|---|---|---|---|
| 1 | 0 | 3 | yes | {4, 2, 3, 1} |
| 2 | 1 | 2 | yes | {4, 3, 2, 1} |
| 3 | 2 | 1 | no | stop |

`reverse(arr, left, right)` can reverse just a part of the array. O(n) time, O(1) space.

## 5. Rotate right by k

The last `k` elements walk to the front, both groups keep their inner order.
`{1, 2, 3, 4, 5, 6, 7}` rotated by 3 is `{5, 6, 7, 1, 2, 3, 4}`.

Three reversals:
```java
reverse(arr, 0, n - 1);   // whole array
reverse(arr, 0, k - 1);   // first k
reverse(arr, k, n - 1);   // the rest
```

```
{1,2,3,4,5,6,7} -> {7,6,5,4,3,2,1} -> {5,6,7,4,3,2,1} -> {5,6,7,1,2,3,4}
```

If `k` is bigger than `n`, full laps change nothing, so use `k = k % n` first (`8 % 5 = 3`). O(n) time, O(1) space.

## 6. Remove a value with read and write

Goal: remove every `val`. The array cannot shrink, so keep the good numbers at the front and return how many.

- `read` visits every box.
- `write` is the box where the next kept number goes. It moves only when we keep a number.

```java
int write = 0;
for (int read = 0; read < arr.length; read++) {
    if (arr[read] != val) {
        arr[write] = arr[read];
        write++;
    }
}
return write;
```

Trace: `{1, 0, 2}`, remove 0

| read | arr[read] | `!= 0`? | Copy | Array after | write after |
|---|---|---|---|---|---|
| 0 | 1 | yes | `arr[0] = arr[0]` | {1, 0, 2} | 1 |
| 1 | 0 | no | none | {1, 0, 2} | 1 |
| 2 | 2 | yes | `arr[1] = arr[2]` | {1, 2, 2} | 2 |

Answer: first `write` boxes = `{1, 2}`. When we skip a number, `write` falls behind `read`, and that gap lets later numbers slide left. This is the first **two-pointer** idea. O(n) time, O(1) space.

## 7. Sorting and printing

```java
Arrays.sort(arr);                       // smallest to largest, in place, O(n log n)
System.out.println(Arrays.toString(arr));
```

- `println(arr)` prints an address like `[I@1b6d3586`. Always use `Arrays.toString(arr)` (or `Arrays.deepToString(grid)` for 2D).
- Descending for `int[]`: sort, then reverse.

## 8. Copying arrays

```java
int[] b = a;                                  // NOT a copy, same array
int[] c = Arrays.copyOf(a, a.length);         // real copy
Arrays.copyOf(a, 2);                          // shorter: first 2 numbers
Arrays.copyOf(a, 5);                          // longer: extra boxes filled with 0
Arrays.copyOfRange(a, 1, 4);                  // boxes 1, 2, 3 ('to' is NOT included)
```

`copyOfRange(a, from, to)` copies `to - from` numbers.

## 9. 2D arrays

A table of rows and columns. **Row first, then column.**

```java
int[][] grid = { {1, 2, 3}, {4, 5, 6} };
grid[1][2]        // 6
grid.length       // 2  (rows)
grid[0].length    // 3  (columns)
```

Visit every box with two loops (outer = rows, inner = columns):
```java
for (int r = 0; r < grid.length; r++) {
    for (int c = 0; c < grid[0].length; c++) {
        // use grid[r][c]
    }
}
```
The inner loop finishes completely before the outer loop moves on. Cost: O(rows x cols).

## 10. Edge cases

| Case | What goes wrong | Fix |
|---|---|---|
| Empty array | `arr[0]` crashes | `if (arr.length == 0)` return early |
| One element | loops starting at `i = 1` do not run | usually fine, but trace it |
| Negative numbers | `int max = 0;` can return a number not in the array | start with `int max = arr[0];` |
| Duplicates | (next topic) | |
| Overflow | (next topic) | |

Note: `==` asks "equal?", a single `=` puts a value in a box.

---

## Habit
Before trusting a loop, trace it on a tiny example: write the value of every variable after every round, and check the loop condition with the **current** value each time.

---

## Complete program (all operations in one file)

Save as `ArrayFundamentals.java`. Expected output is in the comments next to each print.

```java
import java.util.Arrays;

/**
 * Array fundamentals: every operation from my notes in one runnable file.
 * Run: java ArrayFundamentals.java
 */
public class ArrayFundamentals {

    // 1. Insert in the middle. Shift right starting from the END. O(n) time, O(1) space.
    static void insert(int[] arr, int size, int index, int value) {
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = value;
    }

    // 2. Delete from the middle. Shift left starting from the gap. Returns new size.
    static int delete(int[] arr, int size, int index) {
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        return size - 1;
    }

    // 3. Reverse a part of an array with two pointers. O(n) time, O(1) space.
    static void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // 4. Rotate right by k using three reversals. k % n drops full laps.
    static void rotate(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return;
        k = k % n;
        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
    }

    // 5. Remove every 'val' using read/write pointers. Returns how many were kept.
    static int removeValue(int[] arr, int val) {
        int write = 0;
        for (int read = 0; read < arr.length; read++) {
            if (arr[read] != val) {
                arr[write] = arr[read];
                write++;
            }
        }
        return write;
    }

    // 9. Sum of a 2D array. O(rows * cols).
    static int sumAll(int[][] grid) {
        int sum = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                sum = sum + grid[r][c];
            }
        }
        return sum;
    }

    // 10. Edge cases.
    static int firstElement(int[] arr) {
        if (arr.length == 0) {
            return -1;                        // the problem decides what to return
        }
        return arr[0];
    }

    static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    static int findMax(int[] arr) {           // assumes a non-empty array
        int max = arr[0];                     // NOT 0, so negatives work
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // 1 Insert
        int[] a1 = {4, 5, 6, 0};
        insert(a1, 3, 1, 9);
        System.out.println("insert   : " + Arrays.toString(a1));            // [4, 9, 5, 6]

        // 2 Delete
        int[] a2 = {3, 8, 1, 6, 2};
        int size = delete(a2, 5, 2);
        System.out.println("delete   : " + Arrays.toString(Arrays.copyOf(a2, size)));  // [3, 8, 6, 2]

        // 3 Reverse
        int[] a3 = {1, 2, 3, 4, 5};
        reverse(a3, 0, a3.length - 1);
        System.out.println("reverse  : " + Arrays.toString(a3));            // [5, 4, 3, 2, 1]

        // 4 Rotate
        int[] a4 = {1, 2, 3, 4, 5, 6, 7};
        rotate(a4, 3);
        System.out.println("rotate 3 : " + Arrays.toString(a4));            // [5, 6, 7, 1, 2, 3, 4]
        int[] a4b = {1, 2, 3, 4, 5};
        rotate(a4b, 8);
        System.out.println("rotate 8 : " + Arrays.toString(a4b));           // [3, 4, 5, 1, 2]

        // 5 Remove a value
        int[] a5 = {5, 0, 7, 0, 9};
        int count = removeValue(a5, 0);
        System.out.println("remove 0 : " + Arrays.toString(Arrays.copyOf(a5, count)));  // [5, 7, 9]

        // 7 Sorting (descending = sort, then reverse)
        int[] a7 = {5, 2, 9, 1};
        Arrays.sort(a7);
        System.out.println("sort     : " + Arrays.toString(a7));            // [1, 2, 5, 9]
        reverse(a7, 0, a7.length - 1);
        System.out.println("sort desc: " + Arrays.toString(a7));            // [9, 5, 2, 1]

        // 8 Copying
        int[] orig = {1, 2, 3};
        int[] alias = orig;                   // same array, two names
        alias[0] = 99;
        System.out.println("alias    : " + Arrays.toString(orig));          // [99, 2, 3]
        int[] src = {1, 2, 3};
        int[] copy = Arrays.copyOf(src, src.length);
        copy[0] = 99;
        System.out.println("copyOf   : " + Arrays.toString(src) + " " + Arrays.toString(copy));  // [1, 2, 3] [99, 2, 3]
        int[] big = {10, 20, 30, 40, 50};
        System.out.println("range 1-4: " + Arrays.toString(Arrays.copyOfRange(big, 1, 4)));       // [20, 30, 40]

        // 9 2D array
        int[][] grid = {{2, 5}, {1, 3}, {4, 0}};
        System.out.println("grid sum : " + sumAll(grid));                    // 15

        // 10 Edge cases
        System.out.println("first {}  : " + firstElement(new int[]{}));     // -1
        System.out.println("sorted {5,2}: " + isSorted(new int[]{5, 2}));   // false
        System.out.println("max negatives: " + findMax(new int[]{-5, -2, -8}));  // -2
    }
}
```

---


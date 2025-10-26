# How to Start Recursion in Python

Recursion is a technique where a **function calls itself** to solve a problem by breaking it down (**divide-and-conquor**) into smaller subproblems. It can simplify the logic for problems that have repetitive or self-similar structure. Below are some classic recursion examples.

---

### 1. Fibonacci Sequence using Recursion
- **Purpose:** Computes the nth Fibonacci number where each number is the sum of the two preceding ones.
- **Usage:** Useful for educational purposes to understand recursion, though iterative or memoized solutions are more efficient for large inputs.
- **Example:**

```python
def fibonacci(n):
    if n < 0: raise ValueError("n cannot be negative.")
    if n == 0 or n == 1: return 1
    return fibonacci(n - 1) + fibonacci(n - 2)

print(fibonacci(5))   # Outputs: 
print(fibonacci(10))  # Outputs: 
```

---

### 2. Stair Problem using Recursion
- **Purpose:** Solves the classic staircase problem: finding the number of distinct ways to climb a staircase when you can take either 1 or 2 steps at a time.
- **Usage:** This problem is similar to the Fibonacci sequence since the number of ways to climb `n` stairs equals the sum of ways to climb `n-1` and `n-2` stairs.
- **Example:**

```python
def climb_stairs(n):
    if n <= 0: return 0
    if n == 1: return 1
    if n == 2: return 2
    return climb_stairs(n - 1) + climb_stairs(n - 2)

print(climb_stairs(4))  # Outputs: 5 (ways to climb 4 stairs)
print(climb_stairs(5))  # Outputs: 8
```

---

### 3. Sorting Algorithms using Recursion

#### a. Quick Sort
- **Purpose:** Sorts a list of elements by selecting a pivot element and partitioning the list into sub-lists of elements less than, equal to, and greater than the pivot.
- **Usage:** Recursively applies the sorting process to the sub-lists.
- **Example:**

```python
def quick_sort(arr):
    if len(arr) <= 1: return arr
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    return quick_sort(left) + middle + quick_sort(right)

print(quick_sort([3, 6, 8, 10, 1, 2, 1]))
```

---

#### b. Merge Sort
- **Purpose:** Sorts a list of elements by dividing the list into halves, recursively sorting each half, and then merging the sorted halves.
- **Usage:** Particularly useful for larger datasets due to its predictable performance.
- **Example:**

```python
def merge_sort(arr):
    # design your own
    return merge(left, right)

def merge(left, right):
    result = []
    # design your own
    return result

print(merge_sort([38, 27, 43, 3, 9, 82, 10]))
```

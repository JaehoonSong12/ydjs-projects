# Exponentiation by Squaring

## Introduction
Exponentiation by squaring is an efficient algorithm to compute large powers of a number, reducing the number of multiplications from \( O(n) \) (naive approach) to \( O(\log n) \).

## Algorithm Explanation
Instead of multiplying the base \( n \) times, the exponent is repeatedly divided by 2:

1. **If \( n \) is even:**
   \[
   a^n = (a^{n/2}) \times (a^{n/2})
   \]
2. **If \( n \) is odd:**
   \[
   a^n = a \times (a^{(n-1)/2}) \times (a^{(n-1)/2})
   \]
3. The recursion continues until \( n \) reaches 1 or 0.

## Time Complexity
Since the exponent is halved in each step, the depth of recursion is \( O(\log n) \), making the overall time complexity:

\[
O(\log n)
\]

## Applications
- **Modular exponentiation** (used in cryptography)
- **Fast matrix exponentiation** (for solving recurrence relations)
- **Optimizing computations in numerical methods**


def factorial(n: int) -> int: # statical typing for functions specifically designed to certain algo.
    """
    Description:
        Given n of 1 or more, return the factorial of n, which is 
        n * (n-1) * (n-2) ... 1. Compute the result recursively 
        (without loops).

    Examples:
        factorial(1) -> 1
        factorial(2) -> 2
        factorial(3) -> 6

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q01.py`

    Args:
        n (int): A positive integer for which to compute the factorial.

    Returns:
        int: The factorial of n.
    """
    # Base case: 0! = 1
    if (n == 0): return 1 
    # Recursive case: n! = n x (n-1)!
    return n * factorial(n - 1)
    
    




# Unit tests for the factorial function
import unittest

class TestFactorialFunction(unittest.TestCase):
    def test_factorial(self):
        self.assertEqual(factorial(1), 1) # this one!
        self.assertEqual(factorial(2), 2)
        self.assertEqual(factorial(3), 6)
        self.assertEqual(factorial(4), 24)
        self.assertEqual(factorial(5), 120)
        self.assertEqual(factorial(6), 720)
        self.assertEqual(factorial(7), 5040)
        self.assertEqual(factorial(8), 40320)
        self.assertEqual(factorial(12), 479001600)

if __name__ == '__main__':
    unittest.main()

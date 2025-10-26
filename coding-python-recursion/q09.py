def powerN(base: int, n: int) -> int:
    """
    Description:
        Given base and n that are both 1 or more, compute recursively (no loops) the value 
        of base to the n power. For example, powerN(3, 2) is 9 (3 squared).

    Examples:
        powerN(3, 1) -> 3
        powerN(3, 2) -> 9
        powerN(3, 3) -> 27

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q09.py`

    Args:
        base (int): The base number (1 or more).
        n (int): The exponent (1 or more).

    Returns:
        int: The result of raising base to the power n.
    """
    # Base case: your implementation and comment here.
    if n == 0: return 1 # `n` means exponent.
    # Recursive case: your implementation and comment here.
    is_odd_exp = n % 2 # == 1, odd      == 0, even
    temp = powerN(base, n // 2) # n // 2 -> quotient only
    if (is_odd_exp == 1): return base * temp * temp # odd exp
    return temp * temp # even exp

# Unit tests for the powerN function
import unittest

class TestPowerNFunction(unittest.TestCase):
    def test_powerN(self):
        self.assertEqual(powerN(3, 1), 3)   # powerN(3,1) → 3
        self.assertEqual(powerN(3, 2), 9)   # powerN(3,2) → 9
        self.assertEqual(powerN(3, 3), 27)  # powerN(3,3) → 27
        self.assertEqual(powerN(2, 1), 2)   # powerN(2,1) → 2
        self.assertEqual(powerN(2, 2), 4)   # powerN(2,2) → 4
        self.assertEqual(powerN(2, 3), 8)   # powerN(2,3) → 8
        self.assertEqual(powerN(2, 4), 16)  # powerN(2,4) → 16
        self.assertEqual(powerN(2, 5), 32)  # powerN(2,5) → 32
        self.assertEqual(powerN(10, 1), 10)  # powerN(10,1) → 10
        self.assertEqual(powerN(10, 2), 100)  # powerN(10,2) → 100
        self.assertEqual(powerN(10, 3), 1000)  # powerN(10,3) → 1000

if __name__ == '__main__':
    unittest.main()

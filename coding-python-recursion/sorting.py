"""
Description:
    This is a test code to test all the sort algorithms.

Instructions to run the tests via the CLI:
    1. Open your terminal or command prompt.
    2. Run the tests by executing: `python async-recursion/sorting.py`
"""

import random

def bubble_sort(arr):
    n = len(arr)
    for i in range(n):
        for j in range(n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]

def selection_sort(arr):
    n = len(arr)
    for i in range(n):
        min_index = i
        for j in range(i + 1, n):
            if arr[j] < arr[min_index]:
                min_index = j
        arr[i], arr[min_index] = arr[min_index], arr[i]

def insertion_sort(arr):
    n = len(arr)
    for i in range(1, n):
        key = arr[i]
        j = i - 1
        while j >= 0 and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key




def quick_sort(arr):
    if len(arr) <= 1: return arr
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    return quick_sort(left) + middle + quick_sort(right)



# print(quick_sort([3, 6, 8, 10, 1, 2, 1]))
# print(quick_sort([38, 27, 43, 3, 9, 82, 10]))


def merge_sort(arr):
    ### base-case
    # reference: https://www.programiz.com/dsa/merge-sort
    if len(arr) == 0:
        return []
    if len(arr) == 1:
        return arr
    
    ### recursive-case
    arr_left, arr_right = arr[:len(arr) // 2], arr[len(arr) // 2:]
    arr_left = merge_sort(arr_left) # recursive call, make sure arrays are sorted
    arr_right = merge_sort(arr_right) # recursive call, make sure arrays are sorted

    # cost-function: merge
    arr_sorted = []

    while (len(arr_left) != 0 and len(arr_right) != 0):
        if (arr_left[0] > arr_right[0]):
            smallest_right, arr_right = [arr_right[0]], arr_right[1:] # [1,2,3,4] -> [1], [2,3,4]
            arr_sorted += smallest_right
        else:
            smallest_left, arr_left = [arr_left[0]], arr_left[1:]
            arr_sorted += smallest_left
    
    if (len(arr_left) == 0): arr_sorted += arr_right
    if (len(arr_right) == 0): arr_sorted += arr_left

    return arr_sorted


# Generate a list of 10000 random integers
arr = [random.randint(1, 10000) for i in range(9999)]








# Sort the list using each algorithm and time it
import time


start_time = time.time()
bubble_sort(arr.copy())
bubble_sort_time = time.time() - start_time


start_time = time.time()
selection_sort(arr.copy())
selection_sort_time = time.time() - start_time



start_time = time.time()
insertion_sort(arr.copy())
insertion_sort_time = time.time() - start_time



start_time = time.time()
quick_sort(arr.copy())
quick_sort_time = time.time() - start_time



start_time = time.time()
merge_sort(arr.copy())
merge_sort_time = time.time() - start_time



print("---------------------------- O(n^2)")
print("Bubble Sort time:", bubble_sort_time)
print("Selection Sort time:", selection_sort_time)
print("Insertion Sort time:", insertion_sort_time)
print("---------------------------- O(n log n)")
print("Quick Sort time:", quick_sort_time)
print("Merge Sort time:", merge_sort_time)
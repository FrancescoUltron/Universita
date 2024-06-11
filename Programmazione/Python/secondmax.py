def second_max(a):
    if len(a) < 2:
        return None
    
    first_max = second_max = None
    for number in a:
        if first_max is None or number > first_max:
            second_max = first_max
            first_max = number
        elif second_max is None or (number > second_max and number != first_max):
            second_max = number

    return second_max

# Example usage
print(second_max([4, 1, 7, 7, 3, 4, 2]))  # Output should be 4
import random
import math

lower = 0
upper = 0
x = 0
count=0

def main1(low, upp):
    global lower, upper, x, count# Use global keyword to access global variables
    lower = int(low)
    upper = int(upp)
    x = random.randint(lower, upper)
    count = round(math.log(upper - lower + 1, 2))
    return count



# def check(userguess):
#     global count  # Use global keyword to access the global count variable
#     while count < math.log(upper - lower + 1, 2):
#         count += 1
#         guess = int(userguess)
#         if x == guess:
#             return count, "win"
#         elif x > guess:
#             return count, "small"
#         elif x < guess:
#             return count, "high"
#
#     if count >= math.log(upper - lower + 1, 2):
#         return x, "lose"

def check(userguess):
    global count
    if count == 0:
        return x, "lose"
#     count -= 1
    guess = int(userguess)
    if x == guess:
        return count, "win"
    elif x > guess:
        count -= 1
        if count==0:
            return x,"lose"
        else:
            return count ,"small"
    elif x < guess:
        count -= 1
        if count==0:
            return x,"lose"
        else:
            return count ,"high"



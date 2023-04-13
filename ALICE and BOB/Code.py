import numpy as np
import random
n = 5 + 11                      #day + month
final = 5 +  11 + 2001        # day + month + year
initial = 0
pa=[]
dp=[]
w1 = [[]]
win_strategy=[]
k = []
def back_tracking(position , is1):        # applied this algorithm based on back tracking algorithm with appling dynamic programming algorithm to reduce the complexity
    global n, final, initial
    if dp[position][is1] != -1:
        return
    if position == final:
        dp[position][is1] = 0
        pa[position][is1] = position
        return
    for i in range(1,n+1):
        if position + i > final:
            break
        back_tracking(position + i, not is1)
        if dp[position+i][not is1] == 0:
            dp[position][is1] = 1
            pa[position][is1]= position + i
    if dp[position][is1] == -1:
        dp[position][is1] = 0
        pa[position][is1] = position
def reseting_the_variable(): # this function only to reset the variable because it is global
    win = [-1, -1]
    for i in range(1, final + 10):
        dp.append(win.copy())
        pa.append(win.copy())
        win_strategy.append(-1)
def check_input_validity(input1, pre): # to check if input is valid or not
    global n, final, initial
    if input1 > pre + n:
        print("this move is too big please try again")
        return False
    if input1 <= pre:
        print("This move is not valid the input is less than last step")
        return False
    return True
def make_input():   # to make the moves
    global n, final, initial
    input1 = input('please make your move: ')
    input1 = int(input1)
    return input1
def game_human_vs_intelegence(advisor_var): # this is the function of the game against smart bot almost never lose
    global n, final, initial
    p = initial
    while True:
        if advisor_var:
            advisor(p)
        input1 = make_input()
        if not check_input_validity(input1, p):
            continue
        p = input1
        if p == final:
            print ("You won thank you for playing")
            break
        back_tracking(p,0)
        ans = dp[p][0]
        q = pa[p][0]
        if q == final:
            print ("Bot won")
            break
        if dp[p][0] == 0:
            q = min(final , p + 1)
            print("BOT Turn He know he will lose",q)
            p = q
        elif dp[p][0] == 1:
            print("BOT Turn he know he will win",q)
            p = q
        else:
            print ("recieved -1 at", p)
            break
    print ("Game Over")
def check_input_validity_1(input1):   # this function to check the validity of input before the game start
    global n, final, initial
    for i in input1:
        if i >'9' or i < '0':
            print ("initial postion is invalid")
            return False
    return True
def game_human_vs_random_bot():     # function of the game against program makes random moves
    global n, final, initial
    p = initial
    while True:
        input1 = make_input()
        if not check_input_validity(input1, p):
            continue
        p = input1
        if p == final:
            print("You won thank you for playing")
            break
        q = random.randint(p+1, min(p + n, final))
        print ("Random bot move is:", q)
        if q == final:
            print("Bot won")
            break
        p = q
    print("Game Over")
def advisor(position):  # this function related to the advicor it show the advices
    if final - position <=n:
        print("I advice you to choose ", final)
        return
    for i in range(position+1,final):
        if i not in win_strategy and i - position <= n:
            print ("I advice you to choose ", i)
            return
    print("you will lose any way unfotintly")
def game_human_vs_advisor():    # this function related to the game against smart bot but with adviser and almost the adviser never miss
    global n, final, initial, w1, win_strategy, k
    for i in range(final-n, final):
        w1[0].append(i)
    for i in range(1, n + 1):
        k.append(i)
    i = 0

    while True:
        w1.append(w1[i].copy())
        for x in w1[i]:
            for y in k:
                postion1 = x - y
                if postion1 < 0 or postion1 in w1[i+1] :
                    continue
                is1 = 0
                for check in k:
                    if postion1 + check not in w1[i+1]:
                        is1=1
                if is1:
                    continue
                for check in k:
                    postion = postion1 - check
                    if postion < 0:
                        continue
                    if postion not in w1[i+1]:
                        w1[i+1].append(postion)
        if w1[i] == w1[i + 1]:
            break
        i = i + 1
    for i in w1[len(w1)-1]:
        win_strategy.append(i)
    #print(sorted(win_strategy))
    game_human_vs_intelegence(1)
def main():
    global initial
    while True:
        reseting_the_variable()
        x = input("Please write 1 if want the game to start at random or 0 if you want to specifie the first position: ")
        if x == "0":
            initial = input("write the initial porsition you like to start from: ")
            if not check_input_validity_1(initial):
                continue
            initial = int(initial)
        elif x=="1":
            initial = random.randint(0, final - 1)
            print("initial position is :", initial)
        else:
            print("invalid choose")
            continue
        x = input("please write 0 if you want smart mode or 1 if you want random mode or 2 if you want advisor mode: ")
        if x =="0":
            game_human_vs_intelegence(0)
        elif x =="1":
            game_human_vs_random_bot()
        elif x =="2":
            game_human_vs_advisor()
        else:
            print("invalid choose :(")
            continue


if __name__ == "__main__":
    main()

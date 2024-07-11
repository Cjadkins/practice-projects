'''
Tic Tac Toe game vs. a random number generator
Cort Adkins - 2024
'''

import random


def player_marker():

    '''
    Players pick either 'X' or 'O'
    '''

    marker = ""
    player1_marker = ""
    player2_marker = ""

    while marker not in ["X", "O"]:
        marker = input("Player 1, pick X or O: ").upper()
    player1_marker = marker
    if player1_marker == "X":
        player2_marker = "O"
    else:
        player2_marker = "X"
    return player1_marker, player2_marker


def user_choice(places):

    '''
    Gather input from user on where to place marker on board
    '''

    choice = ""
    while choice not in places:
        choice = input("Choose your next position: (1-9): ")
        if choice.isdigit():
            choice = int(choice)
        if choice not in places:
            print("Try again...")
    return choice


def display_game(board):
    print(board[1] + "|" + board[2] + "|" + board[3])
    print("-|-|-")
    print(board[4] + "|" + board[5] + "|" + board[6])
    print("-|-|-")
    print(board[7] + "|" + board[8] + "|" + board[9])


def game_on_choice():

    '''
    Checks if user wants to play again.
    '''

    game_on = False
    game_choice = ""
    while game_choice not in ["Y", "N"]:
        game_choice = input("\nWould you like to play again? (Y or N) ").upper()
    if game_choice == "Y":
        game_on = True
    else:
        game_on = False
        print('\nSee ya!')
    return game_on


def place_marker(board, marker_choice, position):

    '''
    Place either 'X' or 'O' on the game board.
    '''

    game_complete = False
    board[position] = marker_choice
    return display_game(board)


def win_condition(board, marker):

    '''
    Check if game has been won
    '''

    win_check = False
    if board[1] == board[2] == board[3] == marker:
        win_check = True
    elif board[4] == board[5] == board[6] == marker:
        win_check = True
    elif board[7] == board[8] == board[9] == marker:
        win_check = True
    elif board[1] == board[5] == board[9] == marker:
        win_check = True
    elif board[7] == board[5] == board[3] == marker:
        win_check = True
    elif board[1] == board[4] == board[7] == marker:
        win_check = True
    elif board[2] == board[5] == board[8] == marker:
        win_check = True
    elif board[3] == board[6] == board[9] == marker:
        win_check = True
    else:
        win_check = False
    return win_check


def who_first():

    '''
    Pick a random player to go first
    '''

    rand_bit = bool(random.getrandbits(1))
    turn = ''
    if rand_bit == True:
        turn = 'Player1'
    else:
        turn = 'Player2'
    return turn


def full_board_check(board):

    '''
    Check if game board is full
    '''

    count = 0
    for i in range(1, len(board)):
        if board[i] == "X" or board[i] == "O":
            count += 1
        if count == 9:
            return True


game_on = True

while game_on:
    print('Welcome to Tic-Tac-Toe!\n')
    board = [" "] * 10
    places = [1, 2, 3, 4, 5, 6, 7, 8, 9]
    game_complete = False
    player1_marker, player2_marker = player_marker()

    turn =  who_first()
    if turn == 'Player1':
        print('Player 1 goes first.')
    else:
        print('Player 2 goes first.')

    while not game_complete:

        #Human player 1 takes a turn
        if turn == 'Player1':
            print("Player 1")
            position = user_choice(places)
            places.remove(position)
            place_marker(board, player1_marker, position)
            game_complete = win_condition(board, player1_marker)
            turn = 'Player2'
            print()
        #Computer player 2 takes a turn from the available spaces in 'places' list
        elif turn == 'Player2':
            print("Player 2")
            position = int(random.choice(places))
            places.remove(position)
            place_marker(board, player2_marker, position)
            game_complete = win_condition(board, player2_marker)
            turn = 'Player1'
            print()
        if full_board_check(board):
            game_complete = True
            break
    if game_complete:
        print("Good Game!")
        if win_condition(board, player1_marker):
            print("Player 1 WINS!!")
        elif win_condition(board, player2_marker):
            print("Player 2 WINS!!")
        else:
            print("It's a tie!")
        game_on = game_on_choice()

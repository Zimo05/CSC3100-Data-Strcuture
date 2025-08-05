import heapq

# Goal state
GOAL = (1, 2, 3, 4, 5, 6, 7, 8, 0)

# Possible moves for each position
MOVES = [
    [1, 3],        # 0: right, down
    [0, 2, 4],     # 1: left, right, down
    [1, 5],        # 2: left, down
    [0, 4, 6],     # 3: up, right, down
    [1, 3, 5, 7],  # 4: up, left, right, down
    [2, 4, 8],     # 5: up, left, down
    [3, 7],        # 6: up, right
    [4, 6, 8],     # 7: up, left, right
    [5, 7]         # 8: up, left
]

def read_board(file_name):
    """Read the initial board from a file."""
    with open(file_name, 'r') as f:
        lines = f.readlines()
    board = []
    for line in lines:
        row = list(map(int, line.strip().split()))
        board.extend(row)
    return tuple(board)

def write_solution(file_name, path):
    """Write the solution to a file."""
    with open(file_name, 'w') as f:
        f.write(str(len(path) - 1) + '\n')  # Number of moves
        for state in path:
            for i in range(3):
                row = state[i*3 : i*3 + 3]
                f.write(' '.join(map(str, row)) + '\n')
            f.write('\n')

def h(state):
    """Compute the Manhattan distance heuristic."""
    distance = 0
    for i in range(9):
        tile = state[i]
        if tile != 0:
            goal_index = tile - 1
            current_row, current_col = i // 3, i % 3
            goal_row, goal_col = goal_index // 3, goal_index % 3
            distance += abs(current_row - goal_row) + abs(current_col - goal_col)
    return distance

def get_neighbors(state):
    """Generate all possible next states."""
    blank_pos = state.index(0)
    neighbors = []
    for pos in MOVES[blank_pos]:
        new_state = list(state)
        new_state[blank_pos], new_state[pos] = new_state[pos], new_state[blank_pos]
        neighbors.append(tuple(new_state))
    return neighbors

def a_star(initial, goal):
    """Run A* search to find the shortest path."""
    pq = []
    counter = 0
    heapq.heappush(pq, (h(initial), counter, 0, initial))  # (f(n), counter, g(n), state)
    explored = set()
    parent = {initial: None}
    
    while pq:
        f_score, _, moves, state = heapq.heappop(pq)
        if state in explored:
            continue
        explored.add(state)
        if state == goal:
            path = []
            current = state
            while current is not None:
                path.append(current)
                current = parent[current]
            path.reverse()
            return path
        for neighbor in get_neighbors(state):
            if neighbor not in explored:
                counter += 1
                new_moves = moves + 1
                new_f = new_moves + h(neighbor)
                heapq.heappush(pq, (new_f, counter, new_moves, neighbor))
                parent[neighbor] = state
    return None  # No solution found

def main():
    """Main function to run the solver."""
    input_file = input("Enter the input file name: ")
    output_file = input("Enter the output file name: ")
    initial = read_board(input_file)
    path = a_star(initial, GOAL)
    if path:
        write_solution(output_file, path)
    else:
        print("No solution found.")

if __name__ == "__main__":
    main()
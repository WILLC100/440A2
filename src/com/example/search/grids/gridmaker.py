"""
generates 100 x 50 grid
For any cell 50% - N, 20% - H, 20% - T, 10% - Blocked
"""

import random

#running distribution across all cells
cells = []
for i in range(5000):
    cellType = "B"
    cellSelect = random.randint(1,100)
    if(cellSelect <= 50):
        cellType = "N"
    if(cellSelect > 50 and cellSelect <= 70):
        cellType = "H"
    if(cellSelect > 70 and cellSelect <= 90):
        cellType = "T"
    cells.append(cellType)
    
    
#selecting nonblocked initial cell
while True:
    cellSelectRow = random.randint(1,100)
    cellSelectCol = random.randint(1,50)
    cellSelect = cellSelectRow * 50 + cellSelectCol - 50
    if cells[cellSelect] == "B":
        continue
    start = str(cellSelectRow) + " " + str(cellSelectCol)
    break

#random 100 actions
actions = []
for i in range(100):
    actionSelect = random.randint(1,4)
    if actionSelect == 1:
        actions.append("U")
    elif actionSelect == 2:
        actions.append("L")
    elif actionSelect == 3:
        actions.append("D")
    else:
        actions.append("R")
                        
#file-creation
file_name = "grid1.txt"
with open(file_name, 'w') as f:
    #intial point tbd
    f.write("100 50\n") #grid size
    f.write(start + "\n") #random start
    """
    for i in range(100):
        f.write(actions[i])
    f.write("\n")
    """
    #assigning terrain types
    cellCount = 0
    for x in range(100):
        for y in range(50):
            f.write(str(x+1) + " " + str(y+1) + " " + cells[cellCount] + "\n")
            cellCount += 1

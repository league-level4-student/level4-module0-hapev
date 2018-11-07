package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		int randomX = (int) Math.floor(Math.random() * w);
		int randomY = (int) Math.floor(Math.random() * w);

		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.c[randomX][randomY]);

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);

		// B. check for unvisited neighbors using the cell
		boolean right = false;
		boolean left = false;
		boolean up = false;
		boolean down = false;

		if (maze.getCell(currentCell.getX() + 1, currentCell.getY()).hasBeenVisited()) {
			right = true;
		}
		if (maze.getCell(currentCell.getX() - 1, currentCell.getY()).hasBeenVisited()) {
			left = true;
		}
		if (maze.getCell(currentCell.getX(), currentCell.getY() + 1).hasBeenVisited()) {
			down = true;
		}
		if (maze.getCell(currentCell.getX(), currentCell.getY() - 1).hasBeenVisited()) {
			up = true;
		}

		// C. if has unvisited neighbors,
		if (right || left || up || down) {
			boolean done = false;
			while (!done) {
				Random r = new Random();
				int r2 = r.nextInt(4);
				if (r2 == 0) {
					if (right) {
						uncheckedCells.push(maze.getCell(currentCell.getX() + 1, currentCell.getY()));
						currentCell.setEastWall(false);
						currentCell = maze.getCell(currentCell.getX() + 1, currentCell.getY());
						currentCell.setBeenVisited(true);
						selectNextPath(currentCell);

					}
				}
				if (r2 == 1) {
					if (left) {
						uncheckedCells.push(maze.getCell(currentCell.getX() - 1, currentCell.getY()));
						currentCell.setWestWall(false);
						currentCell = maze.getCell(currentCell.getX() - 1, currentCell.getY());
						currentCell.setBeenVisited(true);
						selectNextPath(currentCell);

					}
				}
				if (r2 == 2) {
					if (up) {
						uncheckedCells.push(maze.getCell(currentCell.getX(), currentCell.getY() - 1));
						currentCell.setNorthWall(false);
						currentCell = maze.getCell(currentCell.getX(), currentCell.getY() - 1);
						currentCell.setBeenVisited(true);
						selectNextPath(currentCell);

					}
				}
				if (r2 == 3) {
					if (down) {

						uncheckedCells.push(maze.getCell(currentCell.getX(), currentCell.getY() + 1));
						currentCell.setSouthWall(false);
						currentCell = maze.getCell(currentCell.getX(), currentCell.getY() + 1);
						currentCell.setBeenVisited(true);
						selectNextPath(currentCell);

					}
				}
			}

		}

		// C1. select one at random.

		// C2. push it to the stack

		// C3. remove the wall between the two cells

		// C4. make the new cell the current cell and mark it as visited

		// C5. call the selectNextPath method with the current cell

		else {
			if (!uncheckedCells.isEmpty()) {
			currentCell = uncheckedCells.pop();
			selectNextPath(currentCell);
			}

		}
		// D. if all neighbors are visited

		// D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

		// D1c. call the selectNextPath method with the current cell

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (Math.abs(c1.getX() - c2.getX()) == 1) {
			if (c1.getX()>c2.getX()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
			else {
				c2.setWestWall(false);
				c1.setEastWall(false);
			}
		}
		if (Math.abs(c1.getY() - c2.getY()) == 1) {
			if (c1.getY()>c2.getY()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
			else {
				c2.setNorthWall(false);
				c1.setSouthWall(false);
			}
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		return null;
	}
}

import java.util.*;

// Name : Jimin Kim
// Student ID : 20181597

// offset

/**
 * Maze Class
 * 
 */

class Maze {

	private int[][] maze;	// 2 dim array for maze
	private int[][] mark;	// 2 dim array for visit marking, 이미 지난 길을 1로 표시하는 배열. 시도한 위치 표시.
	
	offsets[] move = { 
		// 동, 남동, 북동, 북, 북서, 서, 남서, 남 
		new offsets(0, 1),
		new offsets(1, 1),
		new offsets(-1, 1),
		new offsets(-1, 0),
		new offsets(-1, -1),
		new offsets(0, -1),
		new offsets(1, -1),
		new offsets(1, 0)
	};
	
	public Maze(int m, int p) {
		maze = new int[m + 2][p + 2];
		mark = new int[m + 2][p + 2];
		for(int i = 0; i < m + 2; i++)
			for(int j = 0; j < p + 2; j++) {
				maze[i][j] = 1;
				mark[i][j] = 0;
			}
	}

	// create the maze structure
	public void SetWall(int i, int j, int val) {
		maze[i][j] = val;
	}


	public void Path(int m, int p) {
		Stack<History> history = new Stack<>();
		boolean find = false;
		int x = 1, y = 1;
		
		history.push(new History(x, y));
		
		while (history.empty() == false) {
			for (int i = 0; i < 8; i++) {
				// 만약 move[i] 방향으로 움직일 수 있다면			
				if (canMove(x, y, move[i])) {
					// x, y 방향으로 움직이고, history 스택에 좌표 추가
					x += move[i].x;
					y += move[i].y;
					maze[x][y] = 1;
					history.push(new History(x, y));		
					// 그 다음 다시 좌표 검사 시작			
					break;				
				}
				// 만약 움직일 방향이 없다면
				if (i == 7) {
					// 백트래킹 시작
					History bt = history.pop();
					bt = history.peek();
					x = bt.x;
					y = bt.y;
					maze[x][y] = 0;
					mark[x][y] = 1;	
					i = 0;
				}
			}
			// 도착지에 갔을 경우
			if ((x == m) && (y == p)) {
				find = true;				
				break; // while문 종료
			}
		}

		// 여기서 stack 출력
		Stack<History> tmp = new Stack<>();

		while (history.empty() == false) {
			tmp.push(history.pop());	
		}

		while (tmp.empty() == false) {
			History h = tmp.pop();
			System.out.println("(" + h.x + "," + h.y + ")");		
		}
	}

	boolean canMove(int x, int y, offsets o) {
		// 다음 좌표의 maze가 1이거나 mark값이 1이면 움직일 수 없다.
		if (maze[x+o.x][y+o.y] == 1 || mark[x+o.x][y+o.y] == 1)
			return false;
		return true;		
	}
}

class History {
	public int x;
	public int y;
	public History(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class offsets {
	int x;
	int y;
	public offsets(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

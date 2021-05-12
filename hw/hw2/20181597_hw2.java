import java.util.*;

// Name : Jimin Kim
// Student ID : 20181597

// offset

/**
 * SparseMatrix Class
 * 
 */
// ---------- SparseMatrix class -------------
class SparseMatrix extends LinkedMatrix {
	SparseMatrix() {
	} // SparseMatrix constructor.

	boolean AddOneElement(final int r, final int c, final int v) {
		// create MatrixNode
		MatrixNode m = new MatrixNode(r, c, v);
		
		// v가 0이면 true리턴
		if (v == 0) {
			return true;
		}
		// row기준 first 
		// row가 null이면 m에 추가
		if (row[r] == null || row[r].col > c) {
			m.next = row[r];
			row[r] = m;
		}
		// 그 외, 들어갈 위치 찾아서 추가(삽입).
		else {
			MatrixNode tmp = row[r];
			while (tmp.next != null && tmp.next.col <= c) {
				tmp = tmp.next;
			}
			m.next = tmp.next;
			tmp.next = m;
		}

		// 그 다음으로 col 기준
		// col은 down.
		if (col[c] == null || col[c].row > r) {
			m.down = col[c];
			col[c] = m;
		} else {
			MatrixNode tmp = col[c];
			while (tmp.down != null && tmp.down.row <= r) {
				tmp = tmp.down;
			}
			m.down = tmp.down;
			tmp.down = m;
		}
		return true;

	}

	boolean Delete(final int r, final int c) {

		if (row[r] == null || col[c] == null) { return false; }

		MatrixNode m = row[r];
		MatrixNode delete = null;

		// 먼저 row 기준으로.
		if (row[r] == m && m.col == c) {
			row[r] = m.next;
		} else {
			while (m.next != null && m.next.col < c) {
				if (m.next.col == c)
					break;
				m = m.next;
			}

			delete = m.next;
			if (delete == null || (delete != null && delete.col != c)) { return false; }
			if (m.next != null)
				m.next = delete.next;

		}

		// 이번에는 col 기준으로.
		MatrixNode n = col[c];
		if (col[c] == n && n.row == r) {
			col[c] = n.down;
		} else {
			while (n.down != null) {
				if (n.down.row == r)
					break;
				n = n.down;
			}
			// tmp가 곧 삭제할 대상의 전임.
			delete = n.down;
			if (delete == null) { return false; }
				
			if (n.down != null)
				n.down = delete.down;
		}
		delete = null;
		return true;
	}

	SparseMatrix Add(SparseMatrix b) {
		
		SparseMatrix c = new SparseMatrix();
		c.Init(rows, cols);

		for (int i = 0; i < rows + 1; i++) {
			MatrixNode m = this.row[i];
			MatrixNode n = b.row[i];

			int aPos = 0, bPos = 0;

			if (m != null)
				aPos = m.col;
			else
				aPos = cols + 1;

			if (n != null)
				bPos = n.col;
			else 
				bPos = cols + 1;

			while ((aPos < cols + 1) && (bPos < cols + 1)) {

				if (aPos == bPos) {
					int v = m.value + n.value;

					// v가 0이 아니면 c에 추가(삽입).
					if (v != 0)
						c.AddOneElement(m.row, m.col, v);
					
					m = m.next;
					n = n.next;
					if (m != null)
						aPos = m.col;
					else {
						aPos = cols + 1;
					}
					if (n != null)
						bPos = n.col;
					else {
						bPos = cols + 1;
					}
					
				} 

				else if (bPos > aPos) {
					c.AddOneElement(m.row, m.col, m.value);
					m = m.next;
					if (m != null)
						aPos = m.col;
					else {
						aPos = cols + 1;
					}
				}
				else if (aPos > bPos) {
					c.AddOneElement(n.row, n.col, n.value);
					n = n.next;
					if (n != null)
						bPos = n.col;
					else {
						bPos = cols + 1;
					}
				}
			}
			
			// Polynomial Add 참고!
			// 다 끝내고 남은 행렬 추가.
			while (aPos < cols + 1) {
				c.AddOneElement(m.row, m.col, m.value);
				m = m.next;
				if (m != null)
					aPos = m.col;
				else {
					aPos = cols + 1;
				}
			}
			while (bPos < cols + 1) {
				c.AddOneElement(n.row, n.col, n.value);
				n = n.next;
				if (n != null)
					bPos = n.col;
				else {
					bPos = cols + 1;
				}
			}

		}
		return c;
	}
}

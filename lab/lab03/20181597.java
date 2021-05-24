import java.util.*;
import java.lang.*;

// Name : Jimin Kim
// Student ID : 20181597

class SparseMatrix {
	MatrixTerm[] smArray;
	int rows, cols;
	int capacity;   // size of smArray
	int terms;  // number of nonzero terms

	class MatrixTerm {
		int row;
		int col;
		int value;
	};

	// constructor
	SparseMatrix(int r, int c, int t) {
		capacity = 128;
		smArray = new MatrixTerm[capacity];
		terms = t;
		rows = r;
		cols = c;
	}

	/**
	*  Add a new term to the end of smArray
	*/
	void NewTerm(int row, int col, int value) {   
		if(terms == capacity)
		{// double capacity of smArray
			try {
				ChangeSize1D (capacity * 2);
			} catch (Exception e) {
				System.out.println("not enough capacity");
			}
		}
		smArray[terms] = new MatrixTerm();
		smArray[terms].row = row;
		smArray[terms].col = col;
		smArray[terms++].value = value;
	}

	int Compare(MatrixTerm a, MatrixTerm b) {
		// if a is smaller than b in row major order, return negative value
		// if a is the same as b in row major order, return 0
		// if b is smaller than a in row major order, return positive value
		if(a.row == b.row)
			return a.col - b.col;
		else
			return a.row - b.row;
	}



	int StoreSum (int sum, int r, int c) {
		//If sum!= 0, then it along with its row and column 
		 //position are stored as the last term in *this
		if (sum != 0) { 
			if (terms == capacity) {
				try {
					ChangeSize1D (capacity * 2);
				} catch (Exception e) {
					System.out.println("not enough capacity");
				}
			}
			smArray[terms] = new MatrixTerm();
			smArray[terms].row = r;
			smArray[terms].col = c;
			smArray[terms++].value = sum;
			return 0;
		}
		return 0;
	}


	void ChangeSize1D (final int newSize) throws Exception 
	{//Change the size of smArray to newSize
		if(newSize < terms) 
			throw new Exception("New size must be >= number of terms");

		capacity = newSize;
		MatrixTerm[] temp = new MatrixTerm[capacity]; // new array
		System.arraycopy(smArray, 0, temp, 0, terms);
		smArray = temp;
	}

	// init the polynomial
	public String toString() {
		String a = new String();
		// show the content of smArray
		a = "Content of smArray :\n";
		for(int i = 0; i < terms; i++) {
			a += "[" + i + "] " + smArray[i].row + " " + smArray[i].col + " "  
				+ smArray[i].value + "\n";
		}
		a += "=========" + "\n";

		return a;
	}
	// 자기 자신과 파라미터로 주어진 b를 더해서 c를 구하는..
	// 다항식 Add 카피해오고, (그건 지수만 비교, 이건 행 번호랑 열 번호 비교)
	SparseMatrix Add(SparseMatrix b) {
		// Return the sum of the Sparse Matrices *this and b
 
		SparseMatrix c = new SparseMatrix(rows, cols, 0);

		if((rows != b.rows) || (cols != b.cols)) {
			System.err.println("The dimension mismatch");
			return c;
		}
		
		int aPos = 0, bPos = 0;

		// NEED TO IMPLEMENT
		while ((aPos < terms) && (bPos < b.terms)) {
			//System.out.println(Compare(smArray[terms], smArray[b.terms]));
			if (Compare(smArray[aPos], b.smArray[bPos]) == 0) {
				if (smArray[aPos].value + b.smArray[bPos].value == 0) {
					aPos++; bPos++;
					continue;
				}
				c.NewTerm(smArray[aPos].row, smArray[aPos].col, smArray[aPos].value + b.smArray[bPos].value);
				aPos++; bPos++;
			}
			else if (Compare(smArray[aPos], b.smArray[bPos]) < 0) {
				c.NewTerm(smArray[aPos].row, smArray[aPos].col, smArray[aPos].value);
				aPos++;
			}			
			else { // Compare(smArray[aPos], b.smArray[bPos]) > 0
				c.NewTerm(b.smArray[bPos].row, b.smArray[bPos].col, b.smArray[bPos].value);
				bPos++;
			}
		}
		if (aPos == terms) {
			while (bPos < b.terms) {
				c.NewTerm(b.smArray[bPos].row, b.smArray[bPos].col, b.smArray[bPos].value);
				bPos++;
			}
		}
		else if (bPos == b.terms) {
			while (aPos < terms) {
				c.NewTerm(smArray[aPos].row, smArray[aPos].col, smArray[aPos].value);
				aPos++;
			}
		}

		// return the sum, c
		return c;				

	}		

}


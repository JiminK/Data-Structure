// student ID: 20181597
// name: 김지민

import java.util.*;
import java.lang.*;

class Polynomial {
	private Term[] termArray;
	private int terms;  // number of nonzero terms

	class Term {
		private double coef; //coefficient 계수
		private int exp; //exponent 지수
	};

	// constructor
	Polynomial(int cap) {
		termArray = new Term[cap];
		terms = 0;
	}

	/**
	*  Add a new term to the end of termArray
	*/
	public void NewTerm(double theCoeff, int theExp) {	
		if(terms == termArray.length)
		{// double capacity of termArray
			termArray = Arrays.copyOf(termArray , termArray.length * 2);
		}
		termArray[terms] = new Term();
		termArray[terms].coef = theCoeff;
		termArray[terms++].exp = theExp;
	}

	public Polynomial Add(Polynomial b) {
		// Return the sum of the polynomials this and b
		Polynomial c = new Polynomial(128);
		int aPos = 0, bPos = 0;
		while((aPos < terms) && (bPos < b.terms))
			if(termArray[aPos].exp == b.termArray[bPos].exp){
				double t = termArray[aPos].coef + b.termArray[bPos].coef;
				if (t != 0.0) c.NewTerm(t, termArray[aPos].exp); // c.NewTerm => c에다가 새로운 term(항)을 추가한다는 것임.
				aPos++; bPos++;
			}
			else if(termArray[aPos].exp < b.termArray[bPos].exp){
				c.NewTerm(b.termArray[bPos].coef, b.termArray[bPos].exp);
				bPos++;
			}
			else {
				c.NewTerm(termArray[aPos].coef, termArray[aPos].exp);
				aPos++;
			}

		// add in remaining terms of *this
		for( ; aPos < terms; aPos++)
			c.NewTerm(termArray[aPos].coef, termArray[aPos].exp);
		for( ; bPos < b.terms; bPos++)
			c.NewTerm(b.termArray[bPos].coef, b.termArray[bPos].exp);
		return c;
	}		

	// x값이 f일 경우, 주어진 다항식의 값을 계산하는 함수
	double Evaluate(double f) {
		double eval = 0.0;

		for (int i=0; i<terms; i++)
			eval += termArray[i].coef * Math.pow(f, termArray[i].exp);
		return eval;
	}

	// check whether the two polynomials are the same
	// loop돌면서, 지수도 같고 계수도 같은지, 다 같으면 추가로.. 하나라도 틀리면 그 시점에서 false 리턴.
	// term의 개수 같으니까 terms만큼 돌면서 termArray의 현재 위치의 지수, 계수 비교
	
	boolean Equals(Polynomial p) {
		// term의 개수 같은지 비교
		if (terms != p.terms) return false;
	
		else if (terms == p.terms) {
			for (int i=0;i<terms;i++) {
				if (termArray[i].exp != p.termArray[i].exp) return false;
			}

			for (int i=0;i<terms;i++) {
				if (termArray[i].coef != p.termArray[i].coef) return false;
			}
		}
		return true;
	}
}

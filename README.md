# Data-Structure
KMU 2-1

## 프로그램 테스트

**컴파일**    
$ javac lab.java LabTest.java    

**실행**    
$ java LabTest    

**주어진 input으로 실행**    
$ java LabTest < lab.in    

**주어진 output과 비교**    
$ java LabTest < lab.in > abc    
$ diff abc lab.out 또는 $ diff -i --strip-trailing-cr -w abc lab.out    
 
 
## lab

 **1. lab 01**    
 
 **2. lab 02**    
다항식 자료구조가 정의된 java파일에서 아래 두 함수를 구현하는 과제    

* **double evaluate(double f);**    
x값이 f 일 경우, 주어진 다항식의 값을 계산하는 함수.    
* **boolean Equals(Polynomial p);**    
자기 자신과 주어진 다항식 p가 같은 다항식인지를 알아보는 함수.    
같으면 true, 다르면 false 리턴.    

 **3. lab 03**    
희소행렬(Sparse Matrix) 자료구조가 정의된 java파일에서 추가로 아래 함수를 구현하는 과제    
    
* **SparseMatrix Add(SparseMatrix b);**    
Polynomial ADD 참조.    
int Compare(MatrixTerm a, Matrixterm b) 를 활용하여 **두 개의 원소 중 누가 앞에 나와야 하는지 알아내면** 쉽게 구현 가능.    

**4. lab 04**    
원형큐(Circular Queue) 구현하는 과제    

push 명령 입력하면 원소가 push 되는데, 그 명령을 보여주고 현재 queue 상태를 보여준 뒤,    
마지막으로 **rear**와 **front** 변수의 값을 보여준다.    
그리고 다음 명령을 입력 받을 준비를 한다.    
명령어로 quit를 치면 프로그램에서 빠져나온다.    

이를 위해 추가로 아래 4개의 함수를 구현    

* **boolean IsEmpty() {}**    
원소의 수가 0이면 true, 아니면 false 리턴.
* **void Push (T x) throws Exception {}**    
Queue의 rear에 원소 추가.
* **T Pop() throws Exception {}**    
Queue의 front에서 원소를 제거하고 제거된 원소 return.
* **public String toString() {}**    
Queue의 내용을 front부터 rear까지 차례대로 표시.    
front 변수와 rear 변수 값 출력.    
실제로는 출력된 내용을 String에 담아서 이를 리턴함.    
*ex. Queue : 1 2    
 rear=2, front=0*

**5. lab 05**    
Circular List를 이용하여 SortedList 클래스 구현하는 과제    
SortedList는 Circular List의 일종이고 데이터는 **오름차순**으로 정렬되어 있음    

사용자가 사용하는 명령어의 syntax는 아래와 같다. (LabTest 클래스의 메인함수에 정의됨)    
* **put integervalue**    
정수인 integervalue 값을 가지는 노드를 정렬이 되는 위치를 찾아 삽입.
* **clear**    
리스트를 모두 av로 리턴.    

이를 위해 추가로 아래 3개의 함수를 구현    
* **void Insert(T e);**    
* **void Clear();**
* **SortedListNode< T > GetNode();**    


## hw    

 **1. hw 01**    
미로 찾기 알고리즘을 구현    

"maze" 입력하여 시작.    
미로의 크기인 m(행 개수), p(열 개수)를 입력 받음.    
그리고 미로의 내용을 입력, 이때 막힌 곳은 1, 열린 곳은 0으로 표시.    
입력받은 미로의 맨 왼쪽 맨 위 행이 (1, 1)에 해당.    
여덟 방향 탐색은    
**E, SE, NE, N, NW, W, SW, S** 의 순서로 함.    
탐색의 시작은 (1, 1), 끝은 (m, p)    
    
자세한 내용은 hw1.pdf를 참고한다.    

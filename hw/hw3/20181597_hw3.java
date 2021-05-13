import java.util.*;

// Name : Jimin Kim
// Student ID : 20181597

@SuppressWarnings("unchecked")
class BST <T extends KeyValue> {

	class TreeNode <U extends KeyValue> {
		U data;	// storage for data : in HW 3, T will be Item
		TreeNode<U> leftChild;	// link to the left Child
		TreeNode<U> rightChild;	// link to the right Child

		// constructors come here
		TreeNode() {
			leftChild = rightChild = null;
		}
		TreeNode(U d) {
			// data is given
			data = d;
			// the leftChild and rightChild field are null
			leftChild = rightChild = null;
		}
	};

	TreeNode <T> root;// the reference to the root node

	BST() { 
		// BST constructor. 
		root = null;
	}

	void Show() {
		System.out.print( "Pre  Order : ");
		PreOrder(root);
		System.out.println("");
		System.out.print("In   Order : ");
		InOrder(root);
		System.out.println("");
		System.out.print("Post Order : ");
		PostOrder(root);
		System.out.println("");
		System.out.print("Number of Nodes : ");
		System.out.println( Count(root));
		System.out.print("Height : ");
		System.out.println( Height(root));
		System.out.println("");
	}

	// IMPLEMENT THE FOLLOWING FUNCTIONS

	boolean  Insert(T item)  {
		// first search the key

		if(root == null) {
			root = new TreeNode<T>(item);
			return true;

		}

		TreeNode<T> ptr, parent;
		ptr = root;
		parent = root;
		while (item.GetKey() != ptr.data.GetKey()) {
			if (item.GetKey() < ptr.data.GetKey()) { 
				parent = ptr; 
				if (ptr.leftChild == null) { 
					ptr.leftChild = new TreeNode<T>(item);	
					break;
				} 
				ptr = ptr.leftChild;
			} else if (item.GetKey() > ptr.data.GetKey()) { 
				parent = ptr;	
				if (ptr.rightChild == null) {	 
					ptr.rightChild = new TreeNode<T>(item);
					break;
				}
				ptr = ptr.rightChild;
			} 
		}

		if (item.GetKey() == ptr.data.GetKey()) {
			return false;
		}
 
		return true;
	}

	T Get(T item)  {
		// use the key field of item and find the node
		// do not use val field of item
		TreeNode<T> ptr;
		ptr = root;

		if (root == null) {
			return null;
		}

		while (item.GetKey() != ptr.data.GetKey()) {
			if (item.GetKey() < ptr.data.GetKey()) {  
				if (ptr.leftChild == null) { 
					break;
				} 
				ptr = ptr.leftChild;
			} else if (item.GetKey() > ptr.data.GetKey()) { 	
				if (ptr.rightChild == null) {	 
					break;
				}
				ptr = ptr.rightChild;
			} 
		}


		if (item.GetKey() == ptr.data.GetKey()) {
			return ptr.data;
		}
		
		return ptr.data;
	}


	boolean Delete(T item)  {
		if (root == null)
        	return false;    // non existing key
       
        TreeNode<T> p, q, r;
        p = root;
        q = null;
        r = null;
     
        while (p != null) {
            if (p.data.GetKey() == item.GetKey())
                break;
            else
                q = p;
            if (item.GetKey() < p.data.GetKey())
                p = p.leftChild;
            else
                p = p.rightChild;
        }

        if (p == null) return false;
        if (q == null && p.leftChild == null && p.rightChild == null) {
            root = null;
            return true;
        }
        if (p.leftChild == null && p.rightChild == null) {
            if (q.leftChild == p)
                q.leftChild = null;
            else
                q.rightChild = null;
        }
        else if (p.leftChild == null || p.rightChild == null) {
            if (root.data.GetKey() == item.GetKey() && root.leftChild != null)
                root = root.leftChild;
            else if (root.data.GetKey() == item.GetKey() && root.leftChild != null)
                root = root.rightChild;
            else if (p.leftChild != null) {
                if (q.leftChild == p)
                    q.leftChild = p.leftChild;
                else
                    q.rightChild = p.leftChild;
            }
            else {
                if (q.leftChild == p)
                    q.leftChild = p.rightChild;
                else
                    q.rightChild = p.rightChild;
            }
        }
        else if (p.leftChild != null && p.rightChild != null) {
            TreeNode<T> leftMax = p.leftChild;
            TreeNode<T> leftMax_parent = p;
            while (leftMax.rightChild != null) {
                leftMax_parent = leftMax;
                leftMax = leftMax.rightChild;
            }
            if (leftMax.leftChild != null) {
                p.data = leftMax.data;
                leftMax_parent.rightChild = leftMax.leftChild;
            }
            else {
                p.data = leftMax.data;
                leftMax = null;
            }
        }
        return true;
	}

	void  PreOrder(TreeNode<T> t)  {
		if (t == null)
			return;

		else if (t != null && t.data != null) {
			System.out.print("[" + t.data.GetKey() + "(" + t.data.GetValue() + ")]");
			PreOrder(t.leftChild);
			PreOrder(t.rightChild);
		}
	}

	void  InOrder(TreeNode<T> t)  {
		if (t == null)
			return;
			
		else if (t != null && t.data != null) {
			InOrder(t.leftChild);
			System.out.print("[" + t.data.GetKey() + "(" + t.data.GetValue() + ")]");
			InOrder(t.rightChild);
		}
	}

	void  PostOrder(TreeNode<T> t)  {
		if (t == null)
			return;

		else if (t != null && t.data != null) {
			PostOrder(t.leftChild);
			PostOrder(t.rightChild);
			System.out.print("[" + t.data.GetKey() + "(" + t.data.GetValue() + ")]");
		}
	}

	int  Count(TreeNode<T> t)  {
		if (t == null) 
            return 0; 
        else
            return Count(t.leftChild) + Count(t.rightChild) + 1;

	}

	int  Height(TreeNode<T> t)  {        
		if (t == null)
			return 0;
		else {
			int lHeight = Height(t.leftChild); 
		    int rHeight = Height(t.rightChild); 

            if (lHeight > rHeight) 
                return (lHeight + 1); 
            else 
                return (rHeight + 1);
		}
	}
}



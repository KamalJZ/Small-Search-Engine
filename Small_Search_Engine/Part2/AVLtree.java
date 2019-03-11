import java.util.*;
public class AVLtree{
	tNode root;
	MyLinkedList<Integer> Inlist = new MyLinkedList<Integer>();
	AVLtree(){
		root = new tNode();
		root.parent =null;
		root.key = 0;
	}
	AVLtree(Position value){
		root = new tNode(value);
		root.parent =null;
	}
	public void addright(tNode node,Position k){
		node.right = new tNode(k);
		node.right.parent = node;
	}
	public void addleft(tNode node,Position k){
		node.left = new tNode(k);
		node.left.parent = node;
	}
	public tNode add(Position data){
		tNode temp = this.root;
		tNode newnode = this.root;;
		if(root.value==null){
			root.value = data;
			root.key = data.getWordIndex();
		}
		else{
			int flag = 0;
			/*Binary Tree Addition*/
			while(flag==0){
				if(temp.key < data.getWordIndex()){
					if(temp.right !=null){
						temp = temp.right;
					}
					else{
						this.addright(temp,data);
						flag= 1;
						newnode =temp.right;
					}
				}
				else if(temp.key > data.getWordIndex()){
					if(temp.left !=null){
						temp = temp.left;
					}
					else{
						this.addleft(temp,data);
						flag = 1;
						newnode = temp.left;
					}
				}
				else {
					if(data.getPageEntry().name.equals(temp.value.getPageEntry().name))
						flag =1;
					else{
						if(temp.left !=null){
						temp = temp.left;
						}
						else{
							this.addleft(temp,data);
							flag = 1;
							newnode = temp.left;
						}
					}
				}
			}
			/*Rearrangement For AVL tree property*/
			temp = newnode.parent;
			while(temp!=root){
				if(IsBalanced(temp)==2){
					tNode xtemp = temp;
					if(IsBalanced(xtemp.left)==1){
						rightrotate(xtemp);
					}
					else if(IsBalanced(xtemp.left)==-1){
						leftrotate(xtemp.left);
						rightrotate(xtemp);
					}
				}
				else if(IsBalanced(temp)==-2){
					tNode xtemp = temp;
					if(IsBalanced(xtemp.right)==-1){
						leftrotate(xtemp);
					}
					else if(IsBalanced(xtemp.right)==1){
						rightrotate(xtemp.right);
						leftrotate(xtemp);
					}
				}
		
				temp = temp.parent;
			}
		}
		return root;
	}
	public void print(tNode root){
		if(root!=null){
			print(root.left);
			System.out.println(root.key);
			print(root.right);
		}
	}
	public int getheight(tNode node){
		if(node!=null){
			if(getheight(node.left)>getheight(node.right))
				return getheight(node.left)+1;
			else
				return getheight(node.right)+1;
		}
		else return -1;
	}
	public int IsBalanced(tNode node){
		int x = getheight(node.left)-getheight(node.right);
		return x;
	}
	public void rightrotate(tNode node){
		tNode papa = node.parent;
		tNode newmain = node.left;
		tNode beta = newmain.right;
		newmain.right =node;
		node.left = beta;
		newmain.parent = papa;
		node.parent = newmain;
		if(papa.left==node)
			papa.left = newmain;
		else if(papa.right == node)
			papa.right = newmain;
	}
	public void leftrotate(tNode node){
		tNode papa = node.parent;
		tNode newmain = node.right;
		tNode beta = newmain.left;
		newmain.left =node;
		node.right = beta;
		newmain.parent = papa;
		node.parent = newmain;
		if(papa.left==node)
			papa.left = newmain;
		else if(papa.right == node)
			papa.right = newmain;
	}
	public Boolean Find(tNode root,int searchKey){
		if(root!=null){
			if(searchKey<root.key){
				return Find(root.left,searchKey);
			}
			else if(searchKey>root.key){
				return Find(root.right,searchKey);
			}
			else if(searchKey==root.key){
				return true;
			}
		}
			return false;
	}
	public MyLinkedList<Integer> InorderList(tNode root){
		MyLinkedList store = new MyLinkedList();
		Inorder(root,store);
		return store;
	}
	public void Inorder(tNode root,MyLinkedList store){
		if(root!=null){
			Inorder(root.left,store);
			store.Insert(root.key);
			Inorder(root.right,store);
		}  
	}
}
class tNode{
	int key;
	Position value;
	int height;
	tNode right,left,parent;
	tNode(){
		right =null;
		left = null;
	}
	tNode(Position pos){
		key = pos.getWordIndex();
		value = pos;
		right =null;
		left = null;
	}
	
}

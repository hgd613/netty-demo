package com.scott.test.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by huo on 2018/7/25.
 */
public class BinarySortTree {

    static BinaryTree f;
    static BinaryTree root;

    //二叉排序树的创建
    public static void createBST(BinaryTree root, int key){
        BinaryTree newNode = new BinaryTree(key);
        if(key > root.val){
            if(root.right == null)
                root.right = newNode;
            else
                createBST(root.right, key);
        }
        else if(key < root.val){
            if(root.left == null)
                root.left = newNode;
            else
                createBST(root.left, key);
        }

    }

    //二叉排序树的查找
    //如果查找成功，则f指向查找成功的节点；如果查找失败，f指向查找路径上的最后一个节点，也即待插入节点
    public static boolean sort(BinaryTree root, int key, BinaryTree p){
        if(root == null){
            f = p;
            System.out.println("查找失败！");
            return false;
        }
        else if(root.val == key){
            f = root;
            System.out.println("查找成功！");
            return true;
        }
        else if(root.val >= key)
            return sort(root.left, key, root);
        else
            return sort(root.right, key, root);
    }

    //二叉排序树的插入
    public static void insert(BinaryTree rt, int key){
        if(sort(root, 100, null) == false){
            BinaryTree newNode = new BinaryTree(100);
            if(f == null)
                root = newNode;
            else if(key > f.val)
                f.right = newNode;
            else
                f.left = newNode;
            System.out.println("插入成功！");
            return;
        }
        System.out.println("不允许插入重复元素！");
    }


    //二叉树的先序遍历
    public static void preOrder(BinaryTree rt){
        if(rt != null){
            System.out.print(rt.val + " ");
            preOrder(rt.left);
            preOrder(rt.right);
        }
    }

    //二叉树的中序遍历
    public static void inOrder(BinaryTree rt){
        if(rt != null){
            inOrder(rt.left);
            System.out.print(rt.val + " ");
            inOrder(rt.right);
        }
    }

    //二叉树的后序遍历
    public static void postOrder(BinaryTree rt){
        if(rt != null){
            postOrder(rt.left);
            postOrder(rt.right);
            System.out.print(rt.val + " ");
        }
    }

    //二叉树的层次遍历
    //用队列实现
    public static void levelOrder(BinaryTree rt){
        if(rt == null)
            return;
        Queue<BinaryTree> queue = new LinkedList<BinaryTree>();
        queue.add(rt);
        while(!queue.isEmpty()){
            BinaryTree temp = queue.remove();
            System.out.print(temp.val + " ");
            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
        }
    }
}

package com.example.demo.算法;

/**
 * @Author: lzj
 * @Date: 2021/7/8 23:22
 * @Description:
 */
public class NodeCopy {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    static class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            Node curNode = head;

        /*
            在 原链表每一个节点后，续接 一个新节点
        */
            while (curNode != null) {
                Node tempNode = new Node(curNode.val);
                tempNode.next = curNode.next;
                curNode.next = tempNode;    // 将 当前节点的next 指向 新new节点
                curNode = tempNode.next;    // 将 当前节点 指向 原链表的下一个节点
            }

        /*
            为 当前链表 的 每一个新new节点的random属性 赋值
        */
            curNode = head;
            while (curNode != null) {
            /*
                由于我们让curNode首先指向的是head节点，
                因此，我们让curNode一直指向 原链表的节点 即可！
            */
                if (curNode.random != null) {
                    curNode.next.random = curNode.random.next;  // 为 新new节点的random 赋值为 原链表中应该的random指向的节点的相应的新new节点
                }
                curNode = curNode.next.next;
            }

        /*
            将 当前链表，按照一个间隔一个的顺序 拆分开
                1、将新new节点，串成一个 新链表
                2、将原链表的节点，拆出来并组合成 原链表
        */
            curNode = head.next;
            Node preNode = head;
            Node result = head.next;
            while (curNode.next != null) {
                preNode.next = preNode.next.next;
                curNode.next = curNode.next.next;
                preNode = preNode.next;
                curNode = curNode.next;
            }
            preNode.next = null;    // 将 原链表的最后一个节点 的 next指针，重新指向null
            return result;
        }
    }

    public static void main(String[] args) {

    }
}

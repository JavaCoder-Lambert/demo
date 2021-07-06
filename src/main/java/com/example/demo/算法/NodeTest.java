package com.example.demo.算法;

import com.google.gson.Gson;

import java.util.Objects;

/**
 * 反转链表---
 * 链表(linked-list)：链表就是线性表的链式存储方式。链表的内存是不连续的，
 * 前一个元素存储地址的下一个地址中存储的不一定是下一个元素。链表通过一个指向下一个元素地址的引用将链表中的元素串起来。
 * 其实为了简便，通常我们也直接将list看做是链表
 *
 * @Author: lzj
 * @Date: 2021/7/6 22:22
 * @Description:
 */
public class NodeTest {
    /**
     * 定义链表节点
     */
    static class Node {
        Integer data;
        Node next;
    }

    /**
     * 准备链表信息 首尾相连
     *
     * @return
     */
    static Node readyNode() {
        Node linkNode1 = new Node();
        linkNode1.data = 1;
        Node linkNode2 = new Node();
        linkNode2.data = 2;
        Node linkNode3 = new Node();
        linkNode3.data = 3;
        Node linkNode4 = new Node();
        linkNode4.data = 4;
        Node linkNode5 = new Node();
        linkNode5.data = 5;
        Node linkNode6 = new Node();
        linkNode6.data = 6;
        linkNode1.next = linkNode2;
        linkNode2.next = linkNode3;
        linkNode3.next = linkNode4;
        linkNode4.next = linkNode5;
        linkNode5.next = linkNode6;
        return linkNode1;
    }

    /**
     * 反转链表信息 如果都为空则返回当前链表
     *
     * @param node
     * @return
     */
    static Node reverseLinkedList(Node node) {
        if (Objects.isNull(node) || Objects.isNull(node.next)) {
            return node;
        }
        //链表指向下一个
        Node headNode = reverseLinkedList(node.next);
        node.next.next = node;
        node.next = null;
        return headNode;
    }

    public static void main(String[] args) {
        Node node = reverseLinkedList(readyNode());
        Gson gson = new Gson();
        System.out.println(gson.toJson(node));
    }

}

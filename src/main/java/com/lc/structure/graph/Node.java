package com.lc.structure.graph;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gujixian
 * @since 2022/11/25
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Node<E> {
    public E value;
    public int in;
    public int out;
    public List<Node<E>> nexts = new ArrayList<>();
    public List<Edge<E>> edges = new ArrayList<>();

    public Node(E value) {
        this.value = value;
    }
}

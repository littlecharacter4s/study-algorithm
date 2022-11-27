package com.lc.structure.tree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gujixian
 * @since 2022/11/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode<E> {
    public E value;
    public TreeNode<E> left;
    public TreeNode<E> right;
    public TreeNode<E> parent;
    public List<TreeNode<E>> children;
    public int color;
    public int height;

    public TreeNode(E value) {
        this.value = value;
    }
}
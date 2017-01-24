package com;

import com.steveq.BST;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by SteveQ on 2017-01-24.
 */
public class BSTTest {

    private BST<Integer> bst;
    private int[] data = new int[]{5, 8, 10, 12, 18, 24, 27};

    @Before
    public void setUp() throws Exception {
        bst = new BST();
        for(int el : data){
            bst.add(el);
        }
    }

    @Test
    public void addingElementToBSTChangesSize() throws Exception {
        assertEquals(7, bst.size());
    }

    @Test
    public void toArrayGivesElementsInAscendingOrder() throws Exception {
        assertArrayEquals(data, Arrays.stream(bst.toArray()).mapToInt(o -> (int)((BST.Node)o).getData()).toArray());
    }

    @Test
    public void IteratorReturnsProperValue() throws Exception {
        Iterator it = bst.iterator();

        it.next();

        assertEquals(8, (int)((BST.Node)it.next()).getData());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void IteratorThrowsExceptionWhenReachLastElementAndTryToMoveNext() throws Exception {
        Iterator it = bst.iterator();
        for(int i=0; i < data.length; i++){
            it.next();
        }

        it.next();
    }
}
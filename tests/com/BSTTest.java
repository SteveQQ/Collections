package com;

import com.steveq.BST;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by SteveQ on 2017-01-24.
 */
public class BSTTest {

    private BST<Integer> bst;
    private int[] data = new int[]{10, 8, 12, 5, 24, 18, 27};

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
        int[] test = new int[]{5, 8, 10, 12, 18, 24, 27};
        assertArrayEquals(test, Arrays.stream(bst.toArray()).mapToInt(o -> (int)((BST.Node)o).getData()).toArray());
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

    @Test
    public void ReturnFalseIfElementIsNotPresentInBST() throws Exception {
        assertEquals(false, bst.remove(99));
    }

    @Test
    public void ReturnTrueIfElementRemovedFromBST() throws Exception {
        assertEquals(true, bst.remove(5));
    }

    @Test
    public void SizeDecreasedWhenLeafElementDeletedFromBST() throws Exception {
        bst.remove(5);
        assertEquals(data.length-1, bst.size());
    }

    @Test
    public void SizeDecreasedWhenMidRootElementDeletedFromBST() throws Exception {
        bst.remove(12);
        assertEquals(data.length-1, bst.size());
    }

    @Test
    public void SizeDecreasedWhenRootElementDeletedFromBST() throws Exception {
        bst.remove(10);
        assertEquals(data.length-1, bst.size());
    }

    @Test
    public void OrderOfElementsIsCorrectAfterDeletingLeafFromBST() throws Exception {
        bst.remove(18);
        int[] test = new int[]{5,8,10,12,24,27};

        assertArrayEquals(test, Arrays.stream(bst.toArray()).mapToInt(o -> (int)((BST.Node)o).getData()).toArray());
    }

    @Test
    public void OrderOfElementsIsCorrectAfterDeletingElementWithOneChild() throws Exception {
        bst.remove(8);
        int[] test = new int[]{5, 10, 12, 18, 24, 27};

        assertArrayEquals(test, Arrays.stream(bst.toArray()).mapToInt(o -> (int)((BST.Node)o).getData()).toArray());
    }

    @Test
    public void OrderOfElementsIsCorrectAfterDeletingElementWithTwoChildren() throws Exception {
        bst.remove(24);
        int[] test = new int[]{5, 8, 10, 12, 18, 27};

        assertArrayEquals(test, Arrays.stream(bst.toArray()).mapToInt(o -> (int)((BST.Node)o).getData()).toArray());
    }

    @Test
    public void ReturnProperOutputWhenElementExistsInTree() throws Exception {
        assertEquals(true, bst.contains(8));
        assertEquals(false, bst.contains(99));
    }

    @Test
    public void ReturnClosestRootForNotFoundElement() throws Exception {
        BST.Node node = bst.findNode(22);
        assertEquals(18, node.getData());
    }

    @Test
    public void FindElementsWhenNoElementsInsideBST() throws Exception {
        BST<Integer> bst2 = new BST<>();
        BST.Node node = bst2.findNode(10);
        assertEquals(null, node);
    }

    @Test
    public void AllElementsFromCollectionAddedInCorrectOrder() throws Exception {
        ArrayList<Integer> testCollection = new ArrayList<>();
        testCollection.add(3);
        testCollection.add(11);
        testCollection.add(19);
        testCollection.add(30);
        bst.addAll(testCollection);

        int[] test = new int[]{3, 5, 8, 10, 11, 12, 18, 19, 24, 27, 30};
        assertArrayEquals(test, Arrays.stream(bst.toArray()).mapToInt(o -> (int)((BST.Node)o).getData()).toArray());
    }

    @Test
    public void MinimumValueFound() throws Exception {
        assertEquals(5, (int)bst.findMinimum());
    }

    @Test
    public void FindMinimumReturnsRootWhenNoLowerValue() throws Exception {
        assertEquals(5, (int)bst.findMinimum(bst.findNode(5)).getData());
    }

    @Test
    public void ReturnedCelilingHasCorrectValue() throws Exception {
        ArrayList<Integer> testCollection = new ArrayList<>();
        testCollection.add(3);
        testCollection.add(11);
        testCollection.add(19);
        testCollection.add(30);
        bst.addAll(testCollection);

        BST<Integer> bst2 = new BST<>();
        bst2.add(10);
        bst2.add(8);
        bst2.add(5);

        assertEquals(10, (int)bst.ceiling(8));
        assertEquals(27, (int)bst.ceiling(24));
        assertEquals(12, (int)bst.ceiling(10));
        assertEquals(10, (int)bst2.ceiling(10));
    }

    @Test
    public void ReturnedFloorHasCorrectValue() throws Exception {
        ArrayList<Integer> testCollection = new ArrayList<>();
        testCollection.add(3);
        testCollection.add(11);
        testCollection.add(19);
        testCollection.add(30);
        bst.addAll(testCollection);

        BST<Integer> bst2 = new BST<>();
        bst2.add(10);
        bst2.add(12);
        bst2.add(16);

        assertEquals(5, (int)bst.floor(8));
        assertEquals(19, (int)bst.floor(24));
        assertEquals(8, (int)bst.floor(10));
        assertEquals(27, (int)bst.floor(27));
        assertEquals(10, (int)bst2.floor(10));
    }

    @Test
    public void SizeIsZeroAfterClearingBST() throws Exception {
        bst.clear();
        assertTrue(bst.size() == 0);
    }

    @Test
    public void AbsRootNullAfterClear() throws Exception {
        bst.clear();

        assertTrue(bst.absRoot == null);
    }

    @Test
    public void RemoveWhenThereIsOnlyOneElement() throws Exception {
        BST<Integer> bst2 = new BST<>();
        bst2.add(19);
        bst2.remove(19);
        assertTrue(bst2.absRoot == null);
        assertEquals(0, bst2.size());
    }

    @Test
    public void RemoveNotExistingElementReturnFalse() throws Exception {
        assertEquals(false, bst.remove(88));
    }

    @Test
    public void AddThreeSameValuesToBST() throws Exception {
        BST<Integer> bst2 = new BST<>();
        bst2.add(10);
        bst2.add(10);
        bst2.add(10);
        bst2.add(4);

        int[] test = new int[]{4, 10, 10, 10};
        assertArrayEquals(test, Arrays.stream(bst2.toArray()).mapToInt(o -> (int)((BST.Node)o).getData()).toArray());
    }

    @Test
    public void RootSubtreeSizeValidation() throws Exception {
        assertEquals(7, bst.absRoot.getSubtreeSize());
    }

    @Test
    public void LeftAndRightSubtreeSizeValidation() throws Exception {
        assertEquals(2, bst.absRoot.getLeftJoin().getSubtreeSize());
        assertEquals(4, bst.absRoot.getRightJoin().getSubtreeSize());
        assertEquals(1, bst.findNode(5).getSubtreeSize());
    }
}
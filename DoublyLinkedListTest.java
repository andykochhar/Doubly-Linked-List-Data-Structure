import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {




    @Test
    public void testAppend()
    {
        DoublyLinkedList <Album> dll = new DoublyLinkedList();

        assertEquals("NULL", dll.toString());

        ArrayList <String> artists = new ArrayList<>();
        Album a1 = new Album(1, artists, "Title 1", 1);
        Album a2 = new Album(2, artists, "Title 2", 2);
        Album a3 = new Album(3, artists, "Title 3", 3);

        dll.append(a1);
        dll.append(a2);
        dll.append(a3);

        assertEquals( a1.toString() + " -> " + a2.toString() + " -> " + a3.toString() + " -> NULL", dll.toString());

        assertEquals(a3.toString(), dll.append(a3).toString());
    }

    @Test
    public void testInsert()
    {
        DoublyLinkedList <Album> dll = new DoublyLinkedList();

        // Creating a new ArrayList of artists and a new Album
        ArrayList <String> artists = new ArrayList<>();
        Album a1 = new Album(1, artists, "Title 1", 1);

        // Testing edge case 1 with location equal to 0
        dll.insert(0, a1);
        assertEquals(a1.toString() + " -> NULL", dll.toString());


        // Creating more Album objects
        Album a2 = new Album(3, artists, "Title 2", 2);
        Album a3 = new Album(3, artists, "Title 3", 3);

        // Refreshing dll
        dll = new DoublyLinkedList<>();

        dll.append(a1);
        dll.append(a2);
        dll.append(a3);

        // Creating a new Album object
        Album a4 = new Album(4, artists, "Title 4", 4);

        // Testing main case with location in bounds
        dll.insert(1, a4);
        assertEquals(a1.toString() + " -> " + a4.toString() + " -> " + a2.toString() + " -> " + a3.toString() + " -> NULL", dll.toString());

        Album a5 = new Album(5, artists, "Title 5", 5);

        // Testing edge case 2 (where node is to be inserted at the beginning of the list)
        dll.insert(0, a5);

        assertEquals(a5.toString() + " -> " + a1.toString() + " -> " + a4.toString() + " -> " + a2.toString() + " -> " + a3.toString() + " -> NULL", dll.toString());

        // Refreshing dll
        dll = new DoublyLinkedList<>();

        dll.append(a1);
        dll.append(a2);
        dll.append(a3);
        dll.append(a4);

        // Testing edge case 3 (where node is to be inserted at the end of the list)
        dll.insert(4, a5);
        assertEquals(a1.toString() + " -> " + a2.toString() + " -> " + a3.toString() + " -> " + a4.toString() + " -> " + a5.toString() + " -> NULL", dll.toString());

        // Refreshing dll
        dll = new DoublyLinkedList<>();

        dll.append(a1);
        dll.append(a2);
        dll.append(a3);
        dll.append(a4);

        // Testing main case again
        dll.insert(2, a5);
        assertEquals(a1.toString() + " -> " + a2.toString() + " -> " + a5.toString() + " -> " + a3.toString() + " -> " + a4.toString() + " -> NULL", dll.toString());
    }

    @Test
    public void testDelete()
    {
        DoublyLinkedList dll = new DoublyLinkedList();
        ArrayList <String> artists = new ArrayList<>();

        // Creating an Album object
        Album a1 = new Album(1, artists, "Title 1", 1);
        dll.append(a1);

        // Testing edge case 2: Only one node exists
        dll.delete(0);
        assertEquals("NULL", dll.toString());

        // Creating more Album classes
        Album a2 = new Album(2, artists, "Title 2", 2);
        Album a3 = new Album(3, artists, "Title 3", 3);
        Album a4 = new Album(4, artists, "Title 4", 4);
        Album a5 = new Album(5, artists, "Title 5", 5);

        // Refreshing dll
        dll = new DoublyLinkedList();
        dll.append(a1);
        dll.append(a2);
        dll.append(a3);
        dll.append(a4);
        dll.append(a5);

        // Testing edge case 3: first node of the list gets deleted and list has more than 1 node
        dll.delete(0);
        assertEquals(a2.toString() + " -> " + a3.toString() + " -> " + a4.toString() + " -> "+ a5.toString() + " -> NULL", dll.toString());

        // Refreshing dll
        dll = new DoublyLinkedList();
        dll.append(a1);
        dll.append(a2);
        dll.append(a3);
        dll.append(a4);
        dll.append(a5);

        // Testing edge case 5: Location is the last node in list and list has more than 1 node
        dll.delete(4);
        assertEquals(a1.toString() + " -> " + a2.toString() + " -> " + a3.toString() + " -> " + a4.toString() + " -> NULL", dll.toString());

        // Refreshing dll
        dll = new DoublyLinkedList();
        dll.append(a1);
        dll.append(a2);
        dll.append(a3);
        dll.append(a4);
        dll.append(a5);

        // Testing main case: Location is in bounds (without being first or last node) and list has more than 1 node
        dll.delete(3);
        assertEquals(a1.toString() + " -> " + a2.toString() + " -> " + a3.toString() + " -> " + a5.toString() + " -> NULL", dll.toString());
    }

    @Test
    public void testGetIndex()
    {
        DoublyLinkedList dll = new DoublyLinkedList();
        ArrayList <String> artists = new ArrayList<>();

        int index = -1;

        Album a1 = new Album(1, artists, "Title 1", 1);
        Album a2 = new Album(2, artists, "Title 2", 2);
        Album a3 = new Album(3, artists, "Title 3", 3);
        Album a4 = new Album(4, artists, "Title 4", 4);
        Album a5 = new Album(5, artists, "Title 5", 5);
        Album a6 = new Album(6, artists, "Title 6", 6);

        dll.append(a1);
        dll.append(a2);
        dll.append(a3);
        dll.append(a4);
        dll.append(a5);
        dll.append(a5);
        dll.append(a5);

        // Testing edge case 1: no such index exists for data
        index = dll.getIndex(a6);
        assertEquals(-1, index);

        // Testing main case: index exists for data, but there is only one index
        index = dll.getIndex(a3);
        assertEquals(2, index);

        // Testing main case: multiple indices for data, for only first index returned
        index = dll.getIndex(a5);
        assertEquals(4, index);
    }


    @Test
    public void testShuffle()
    {
        DoublyLinkedList dll = new DoublyLinkedList();

        ArrayList <String> artists = new ArrayList<>();

        Album a1 = new Album(1, artists, "Title 1", 1);
        Album a2 = new Album(2, artists, "Title 2", 2);
        Album a3 = new Album(3, artists, "Title 3", 3);
        Album a4 = new Album(4, artists, "Title 4", 4);
        Album a5 = new Album(5, artists, "Title 5", 5);
        Album a6 = new Album(6, artists, "Title 6", 6);

        // Testing edge case 1: list is empty
        dll.shuffle();
        assertEquals("NULL", dll.toString());

        dll.append(a1);

        // Testing edge case 2: list has one node
        dll.shuffle();
        assertEquals(a1.toString() + " -> NULL", dll.toString());


        dll.append(a2);
        dll.append(a3);
        dll.append(a4);
        dll.append(a5);

        // Testing main case: list has an odd number of nodes
        dll.shuffle();
        assertEquals(a2.toString() + " -> " + a1.toString() + " -> " + a4.toString() +
                " -> " + a3.toString() + " -> " + a5.toString()
                + " -> NULL", dll.toString());


        // Refreshing dll
        dll = new DoublyLinkedList();
        dll.append(a1);
        dll.append(a2);
        dll.append(a3);
        dll.append(a4);
        dll.append(a5);
        dll.append(a6);

        // Testing main case: list has an even number of nodes
        dll.shuffle();
        assertEquals(a2.toString() + " -> " + a1.toString() + " -> " + a4.toString() +
                " -> " + a3.toString() + " -> " + a6.toString()
                + " -> " + a5.toString() + " -> NULL", dll.toString());

    }

    @Test
    public void testPartition()
    {
        DoublyLinkedList dll = new DoublyLinkedList();

        ArrayList <String> artists = new ArrayList<>();

        int index = -1;

        Album a1 = new Album(1, artists, "Title 1", 1);
        Album a2 = new Album(2, artists, "Title 2", 2);
        Album a3 = new Album(3, artists, "Title 3", 3);
        Album a4 = new Album(4, artists, "Title 4", 4);
        Album a5 = new Album(5, artists, "Title 5", 5);
        Album a6 = new Album(6, artists, "Title 6", 6);

        // Testing edge case 1: list is empty
        assertEquals("NULL", dll.partition(a1).toString());

        dll.append(a1);
        dll.append(a2);
        dll.append(a3);
        dll.append(a4);
        dll.append(a5);
        dll.append(a5);
        dll.append(a5);
        dll.append(a6);

        // Testing edge case 2: list is not empty
        assertEquals(a4.toString() + " -> " + a5.toString() + " -> " +
                a5.toString() + " -> " + a5.toString() + " -> " +
                a6.toString() + " -> NULL", dll.partition(a4).toString());
    }
}

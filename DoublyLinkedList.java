
public class DoublyLinkedList <T extends Comparable <T>>
{
    Node  head;
    Node  tail;

    private int lastLocation;

    public DoublyLinkedList()
    {
        this.head = null;
        this.tail = null;
        lastLocation = -1;
    }

    public void clear()
    {
        this.head = null;
        this.tail = null;

        lastLocation = -1;
    }

    public void addDoublyLinkedList(DoublyLinkedList list)
    {
        Node tempNode = list.head;

        while (tempNode != null)
        {
            this.append(tempNode.data);
            tempNode = tempNode.next;
            lastLocation++;
        }
    }

    public Node append(Album data)
    {
        Node newNode = new Node(data);

        if (this.head == null)
        { // the list is empty
            this.head = newNode;
            this.tail = this.head;
            lastLocation = 0;
        }

        else
        { // the list is not empty
            this.tail.next = newNode;
            newNode.previous = this.tail;

            this.tail = newNode;
            this.tail.next = null;
            lastLocation = lastLocation + 1;
        }

        return this.tail;
    }

    public Node insert(int location, Album data) throws IllegalArgumentException
    {
        Node insertNode = new Node(data);

        // edge case 1: list is empty
        if (this.head == null)
        {
            if (location == 0)
            {
                this.head = insertNode;
                lastLocation = lastLocation + 1;
            }

            else
            {
                throw new IllegalArgumentException("Location is out of bounds");
            }
        }


        // edge case 1 is not true, therefore list is not empty
        else
        {
            // edge case 2: insertNode is to be inserted at the beginning of the list
            if (location == 0)
            {
                this.head.previous = insertNode;
                insertNode.next = this.head;

                this.head = insertNode;

                lastLocation = lastLocation + 1;
            }

            // edge case 3: node is to be inserted at the end of the list
            else if (location == lastLocation + 1)
            {
                this.append(insertNode.data);
                lastLocation = lastLocation + 1;
            }

            // edge case 4: location is out of bounds
            else if (location < 0 && location > lastLocation + 1)
            {
                throw new IllegalArgumentException("Location is out of bounds");
            }

            // location is in bounds, but is neither first nor last, and node is not to be inserted at the end
            else
            {
                int counter = 0;
                Node nodeBefore = new Node();
                Node nodeAfter = new Node();

                Node tempNode = this.head;

                while (tempNode.next != null)
                {
                    if (counter == location - 1)
                    {
                        nodeBefore = tempNode;
                    }

                    else if (counter == location)
                    {
                        nodeAfter = tempNode;
                    }
                    tempNode = tempNode.next;
                    counter++;
                }

                // connecting beforeNode to insertNode
                nodeBefore.next = insertNode;
                insertNode.previous = nodeBefore;

                // connecting afterNode to insertNode
                insertNode.next = nodeAfter;
                nodeAfter.previous = insertNode;

                lastLocation = lastLocation + 1;
            }
        }

        return insertNode;
    }

    public Node delete(int location) throws IllegalArgumentException
    {
        Node deleteNode = new Node();

        // edge case 1: list is empty
        if (this.head == null)
        {
            throw new IllegalArgumentException("List is empty");
        }

        // edge case 2: list has only one node
        else if (this.head.next == null)
        {
            // delete the only node there
            if (location == 0)
            {
                deleteNode = this.head;
                this.head = null;
                lastLocation = lastLocation - 1;
            }

            // edge case 5: Location is out of bounds
            else
            {
                throw new IllegalArgumentException("Location is out of bounds");
            }
        }

        // list has more than one node
        else
        {
            // edge case 3: first node is the one getting deleted
            if (location == 0)
            {
                this.head = this.head.next;
                lastLocation = lastLocation - 1;
            }

            // edge case 4: last node is the one getting deleted
            else if (location == lastLocation)
            {
                this.tail = this.tail.previous;
                this.tail.next = null;
                lastLocation = lastLocation - 1;
            }

            else
            {
                // edge case 5: Location is out of bounds
                if (location < 0 || location > lastLocation)
                {
                    throw new IllegalArgumentException("Location is out of bounds");
                }

                // main case: location is in bounds, is neither first nor last, and list has more than 1 node
                else
                {
                    Node beforeDelete = new Node();
                    Node afterDelete = new Node();

                    Node tempNode = this.head;

                    int counter = 0;

                    while (tempNode != null)
                    {
                        if (counter == location - 1)
                        {
                            beforeDelete = tempNode;
                        }

                        else if (counter == location)
                        {
                            deleteNode = tempNode;
                        }

                        else if (counter == location + 1)
                        {
                            afterDelete = tempNode;
                        }
                        tempNode = tempNode.next;
                        counter++;
                    }

                    beforeDelete.next = afterDelete;
                    afterDelete.previous = beforeDelete;

                    lastLocation = lastLocation - 1;
                }
            }
        }

        return deleteNode;
    }

    public int getIndex(Album data)
    {
        int index = -1;

        Node tempNode = this.head;
        int counter = 0;
        while (tempNode != null)
        {
            if (tempNode.data == data)
            {
                index = counter;
                break;
            }

            counter++;
            tempNode = tempNode.next;
        }

        return index;
    }

    public Node shuffle() throws IllegalArgumentException
    {
        Node returnNode = new Node();

        // edge case 1: list is empty
        if (this.head == null)
        {
            // throw new IllegalArgumentException("List is empty");
            returnNode =  this.head;
        }

        // edge case 2: list has one node
        else if (this.head.next == null)
        {
            //throw new IllegalArgumentException("List has only one node");
            returnNode = this.head;
        }

        // main case: list has more than one node
        else
        {
            int counter = 0;
            Node tempNode = this.head;
            DoublyLinkedList newList = new DoublyLinkedList();

            // Even number of nodes in list
            if ((lastLocation + 1) % 2 == 0)
            {
                // load nodes in newList
                while (tempNode != null)
                {
                    // detect first node of each pair
                    if (counter % 2 == 0)
                    {
                        newList.append(tempNode.next.data);
                        newList.append(tempNode.data);
                    }

                    counter++;
                    tempNode = tempNode.next;
                }
            }

            // Odd number of nodes in list
            else
            {
                // load nodes in new list
                while (tempNode != null)
                {
                    // detect first node of each pair, but also making sure to leave out the last node of list
                    if (counter % 2 == 0 && counter < lastLocation)
                    {
                        newList.append(tempNode.next.data);
                        newList.append(tempNode.data);
                    }

                    // detecting the last node of the list
                    else if (counter == lastLocation)
                    {
                        newList.append(tempNode.data);
                    }

                    counter++;
                    tempNode = tempNode.next;
                }
            }

            returnNode = newList.head;

            // clearing this list
            this.clear();

            // refilling this list with the new order of nodes from newList
            this.addDoublyLinkedList(newList);
        }

        return returnNode;
    }

    public DoublyLinkedList partition(Album data)
    {
        DoublyLinkedList newList = new DoublyLinkedList();

        Node tempNode = this.head;

        // edge case 1: list is empty
        if (this.head == null)
        {
            return newList;
        }

        // main case: list is not empty
        else
        {
            while (tempNode != null)
            {
                if (tempNode.data.compareTo(data) >= 0)
                {
                    newList.append(tempNode.data);
                }

                tempNode = tempNode.next;
            }
        }

        return newList;
    }

    @Override
    public String toString(){
        StringBuilder stb = new StringBuilder();
        if(this.head == null){
            stb.append("");
        }
        else {
            Node toPrint = head;
            while (toPrint != null) {
                stb = stb.append(toPrint.data);
                stb = stb.append(" -> ");
                toPrint = toPrint.next;
            }
        }
        stb = stb.append("NULL");
        return stb.toString();
    }
}

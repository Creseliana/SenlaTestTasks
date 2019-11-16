package Task6;

import java.util.ArrayList;

public class Backpack {
    private int capacity;
    private ArrayList<Item> itemsInBackpack = new ArrayList<>();

    public Backpack(int capacity)
    {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void putInBackpack(Item item)
    {
        itemsInBackpack.add(item);
    }

    public void showContentInBackpack()
    {
        for (int i = 0; i < itemsInBackpack.size(); i++)
        {
            System.out.println("Item weight " + itemsInBackpack.get(i).getWeight()
                    + " cost " + itemsInBackpack.get(i).getCost() + ".");
        }
    }
}

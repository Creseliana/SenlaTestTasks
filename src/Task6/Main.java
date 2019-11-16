package Task6;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Backpack backpack = new Backpack(10);
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1, 100));
        items.add(new Item(2, 50));
        items.add(new Item(5, 300));
        items.add(new Item(1, 50));
        items.add(new Item(1, 30));
        items.add(new Item(3, 40));
        items.add(new Item(3, 20));
        items.add(new Item(4, 400));
        items.add(new Item(1, 1));

        fillBackpack(backpack, items);
        backpack.showContentInBackpack();
    }

    private static void fillBackpack(Backpack backpack, ArrayList<Item> items)
    {
        double index;
        HashMap<Integer, Double> itemIndex = new HashMap<>();

        for (int i = 0; i < items.size(); i++)
        {
            index = items.get(i).getCost() / items.get(i).getWeight();
            itemIndex.put(i, index);
        }

        itemIndex = sortByValue(itemIndex);

        for (Map.Entry<Integer, Double> entry : itemIndex.entrySet()) {
            if (items.get(entry.getKey()).getWeight() <= backpack.getCapacity())
            {
                backpack.putInBackpack(items.get(entry.getKey()));
                backpack.setCapacity(backpack.getCapacity() - items.get(entry.getKey()).getWeight());
            }
        }
    }

    private static HashMap<Integer, Double> sortByValue (HashMap<Integer, Double> unsortedMap)
    {
        List<Map.Entry<Integer, Double>> entryList = new LinkedList<>(unsortedMap.entrySet());
        entryList.sort(Comparator.comparing(Map.Entry::getValue));
        HashMap<Integer, Double> sortedMap = new LinkedHashMap<>();
        for (int i = entryList.size() - 1; i >= 0; i--)
        {
            sortedMap.put(entryList.get(i).getKey(), entryList.get(i).getValue());

        }
        return sortedMap;
    }
}

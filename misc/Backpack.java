package misc;


import java.util.Random;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;


public class Backpack {
  public static class Item {
    public final int price;
    public final int weight;


    public Item(int price, int weight) {
      this.price = price;
      this.weight = weight;
    }

    public String toString() {
      return "$" + this.price + " " + this.weight + "kg";
    }
  }


  public static class SetOfItems {
    public int totalPrice = 0;
    public int totalWeight = 0;
    HashSet<Item> items = new HashSet<Item>();

    public SetOfItems() {};

    public SetOfItems(SetOfItems before, Item newElem) {
      Iterator it = before.items.iterator();
      while (it.hasNext()) {
        this.add((Item)it.next());
      }
      this.add(newElem);
    }
    
    public void add(Item i) {
      this.totalPrice += i.price;
      this.totalWeight += i.weight;
      this.items.add(i);
    }

    public String toString() {
      return "$" + totalPrice + " " + totalWeight + "kg " +
        items;
    }
  }


  public static Random seed = new Random();
  public static final int CAPACITY = 10;
  public static final int ITEMS = 5;
  public static final int MAX_PRICE = 100;

  public static HashMap<Integer, SetOfItems> setPerWeight = new HashMap<Integer, SetOfItems>();


  public static void main(String[] args) {
    System.out.println("Items:");
    LinkedList<Item> items = new LinkedList<Item>();
    for (int i = 0; i < ITEMS; i++) {
      Item it = new Item(seed.nextInt(MAX_PRICE) + 1,
          seed.nextInt(CAPACITY) + 1);
      items.add(it);
    }
    for (int i = 0; i < 10; i++) {
      items.add(new Item(10, 1));
    }
    System.out.println(items);
    SetOfItems taken = getItemsToTake(items);
    System.out.println("I decided to take: " + taken);
  }


  public static SetOfItems getItemsToTake(LinkedList<Item> items) {
    Collections.sort(items, new Comparator<Item>() {
      @Override
      public int compare(Item a, Item b) {
        return a.weight < b.weight ? -1 :
          a.weight == b.weight ? 0 : 1;
      }
    });
    Iterator it = items.iterator();
    while (it.hasNext()) {
      getItemsToTake(items, ((Item)it.next()).weight);
    }
    return getItemsToTake(items, CAPACITY);
  }


  public static SetOfItems getItemsToTake(LinkedList<Item> items, int capacity) {
    SetOfItems result = setPerWeight.get(capacity);
    if (result != null) {
      return result;
    }
    Iterator it = items.iterator();
    while (it.hasNext()) {
      Item cur = (Item)it.next();
      if (cur.weight <= capacity) {
        SetOfItems tmp = getItemsToTake(items, capacity - cur.weight);
        if (result == null ||
            result.totalPrice < tmp.totalPrice + cur.price) {
            result = new SetOfItems(tmp, cur);
        }
      }
    }
    if (result == null) {
      result = new SetOfItems();
    }
    System.out.println(">" + capacity + "kg: " + result);
    setPerWeight.put(result.totalWeight, result);
    return result;
  }
}

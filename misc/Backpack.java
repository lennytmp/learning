package misc;


import java.util.Random;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;


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
    LinkedList<SetOfItems> options = new LinkedList<SetOfItems>();
    Iterator it = items.iterator();
    while (it.hasNext()) {
      SetOfItems tmp = new SetOfItems();
      tmp.add((Item)it.next());
      options.add(tmp);
    }
    boolean added = true;
    while (added) {
      it = options.iterator();
      added = false;
      while (it.hasNext()) {
        SetOfItems variant = (SetOfItems)it.next();
        Item next = getNextItem(items, variant);
        if (next != null) {
          variant.add(next);
          added = true;
        }
      }
    }
    it = options.iterator();
    SetOfItems result = null;
    while (it.hasNext()) {
      SetOfItems tmp = (SetOfItems)it.next();
      if (result == null || result.totalPrice < tmp.totalPrice) {
        result = tmp;
      }
    }
    return result;
  }


  private static Item getNextItem(LinkedList<Item> items, SetOfItems taken) {
    Iterator it = items.iterator();
    Item maxFitValue = null;
    while (it.hasNext()) {
      Item current = (Item)it.next();
      if (current.weight > CAPACITY - taken.totalWeight ||
          taken.items.contains(current)) {
        continue;
      }
      if (maxFitValue == null || maxFitValue.price < current.price) {
        maxFitValue = current;
      }
    }
    return maxFitValue;
  }
}

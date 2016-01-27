package misc;


import java.util.Random;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Arrays;


public class Backpack {
  public static class Item {
    public final int price;
    public final int weight;


    public Item(int price, int weight) {
      this.price = price;
      this.weight = weight;
    }


    @Override
    public String toString() {
      return "$" + this.price + " " + this.weight + "kg";
    }
  }


  public static class SetOfItems {
    public int price = 0;
    public int weight = 0;
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
      this.price += i.price;
      this.weight += i.weight;
      this.items.add(i);
    }


    @Override
    public String toString() {
      return "$" + price + " " + weight + "kg " +
        items;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof SetOfItems)) {
        return false;
      }
      SetOfItems objCasted = (SetOfItems)obj;
      return objCasted.price == this.price &&
        objCasted.weight == this.weight &&
        objCasted.items.equals(this.items);
    }
    

    @Override
    public int hashCode() {
        return items.hashCode();
    }
  }


  public static Random seed = new Random();
  public static final int CAPACITY = 10;
  public static final int ITEMS = 5;
  public static final int MAX_PRICE = 100;

  public static HashMap<Integer, SetOfItems> setPerWeight = new HashMap<Integer, SetOfItems>();


  public static void main(String[] args) {
    System.out.println("Items:");
    Item[] items = new Item[ITEMS + 10];
    for (int i = 0; i < ITEMS; i++) {
      Item it = new Item(seed.nextInt(MAX_PRICE) + 1,
          seed.nextInt(CAPACITY) + 1);
      items[i] = it;
    }
    for (int i = 0; i < 10; i++) {
      items[ITEMS + i] = new Item(10, 1);
    }
    System.out.println(Arrays.toString(items));
    SetOfItems taken = getItemsToTake(items, CAPACITY);
    System.out.println("I decided to take: " + taken);
  }

  
  public static SetOfItems getItemsToTake(Item[] items, int limit) {
    HashMap<Integer, HashSet<SetOfItems>> variants = new HashMap<Integer, HashSet<SetOfItems>>();
    HashSet<SetOfItems> zeroWeight = new HashSet<SetOfItems>();
    SetOfItems nothing = new SetOfItems();
    zeroWeight.add(nothing);
    variants.put(0, zeroWeight);
    SetOfItems maxSet = nothing;
    for (int i = 1; i <= limit; i++) {
      HashSet<SetOfItems> curWeightOptions = new HashSet<SetOfItems>();
      for (Item it : items) {
        HashSet<SetOfItems> tmp = variants.get(i - it.weight);
        if (tmp != null) {
          for (SetOfItems curSet : tmp) {
            if (curSet.items.contains(it)) {
              continue;
            }
            SetOfItems newSet = new SetOfItems(curSet, it);
            curWeightOptions.add(newSet);
            if (newSet.price > maxSet.price) {
              maxSet = newSet;
            }
          }
        }
      }
      variants.put(i, curWeightOptions);
      System.out.println("Weight " + i + ": options added: " +  curWeightOptions.size());
    }
    return maxSet;
  }
}

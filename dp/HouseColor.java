/**
 * Implements the algorithm to find colors for houses with
 * minimum price, where each house has it's own price for
 * each color and two adjacent houses can't have the same
 * color.
 */

package dp;

import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;


class HouseColor {
  // Current price class, contains colors and current price.
  private static class CurrentPrice {

    // Colors of all houses before the current house, inclusive.
    public int[] colors;

    // Current price of all paintings done.
    public int price;


    /**
     * Sets current price, initialises colors array by copying
     * colors before and adding a new color for current house.
     * @param price The price of all paintings done including current house.
     * @param colorsBefore The colors of houses before the current one.
     * @param newColor The current house color.
     */
    public CurrentPrice(int price, int[] colorsBefore, int newColor) {
      this.price = price;
      colors = new int[colorsBefore.length + 1];
      for (int i = 0; i < colorsBefore.length; i++) {
        colors[i] = colorsBefore[i];
      }
      colors[colorsBefore.length] = newColor;
    }
  }


  /**
   * Sets the prices for the houses, calls paint function and prints the result.
   * @param args Program arguments, currently ignored.
   */
  public static void main(String[] args) {
    int[][] houses = {
      {10, 12, 13, 100},
      {12, 20, 80, 10},
      {20, 1, 5, 88},
    };
    System.out.println(Arrays.toString(paint(houses)));
  }


  /**
   * Finds the minimum current color price in the matrix at a certain level
   * excluding the given color. 
   * @param pricesMatrix The matrix of the prices to search in.
   * @param level The level where min pirce should be found.
   * @param exceptClr The color to exclude.
   * @return CurrentPrice object with minimum price at the given level.
   */
  private static CurrentPrice findMinPrice(final CurrentPrice[][] pricesMatrix,
                                  final int level,
                                  final int exceptClr) {
    CurrentPrice minPriceBefore = null;
    for (int clr = 0; clr < pricesMatrix[level].length; clr++) {
      if (clr == exceptClr) {
        continue;
      }
      if (minPriceBefore == null ||
          minPriceBefore.price > pricesMatrix[level][clr].price) {
        minPriceBefore = pricesMatrix[level][clr];
      }
    }
    return minPriceBefore;
  }


  /**
   * Calculates the colors to apply to have minimum price.
   * @param houses Array with prices for each color for each house.
   * @param colors Array with colors to apply to houses.
   */
  public static int[] paint(int[][] houses) {
    int colorsNum = houses[0].length;
    
    /**
     * Each house can be colored in 3 colors, this variable holds
     * the minumum price for all houses before this one including it.
     */
    CurrentPrice[][] currentPrices = new CurrentPrice[houses.length][colorsNum];
    for (int clr = 0; clr < colorsNum; clr++) {
      currentPrices[0][clr] = new CurrentPrice(houses[0][clr], new int[0], clr);
    }

    for (int i = 1; i < houses.length; i++) {
      for (int clr = 0; clr < colorsNum; clr++) {
        CurrentPrice minPricebefore = findMinPrice(currentPrices, i - 1, clr);
        int newPrice = houses[i][clr] + minPricebefore.price;
        currentPrices[i][clr] = new CurrentPrice(newPrice, minPricebefore.colors, clr);
      }
    }
    CurrentPrice result = findMinPrice(currentPrices, houses.length - 1, -1);

    System.out.println("Painted for " + result.price);
    return result.colors; 
  }
}

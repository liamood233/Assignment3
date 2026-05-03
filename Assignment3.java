import java.util.Scanner;

public class Assignment3 {

    private static final Scanner inputReader = new Scanner(System.in);
    // Manual sorting algorithms
    private static void sortIntegerArrayWithBubble(int[] values) {
        int length = values.length;
        for (int pass = 0; pass < length - 1; pass++) {
            boolean swapped = false;
            for (int index = 0; index < length - pass - 1; index++) {
                if (values[index] > values[index + 1]) {
                    int temp = values[index];
                    values[index] = values[index + 1];
                    values[index + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private static void sortCharacterArrayWithBubble(char[] characters) {
        int length = characters.length;
        for (int pass = 0; pass < length - 1; pass++) {
            boolean swapped = false;
            for (int index = 0; index < length - pass - 1; index++) {
                if (characters[index] > characters[index + 1]) {
                    char temp = characters[index];
                    characters[index] = characters[index + 1];
                    characters[index + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    //Task 1:Anagram Checker
    private static void checkIfStringsAreAnagrams() {
        System.out.print("Enter first string: ");
        String firstInput = inputReader.nextLine();

        System.out.print("Enter second string: ");
        String secondInput = inputReader.nextLine();

        char[] firstArray = firstInput.toCharArray();
        char[] secondArray = secondInput.toCharArray();

        sortCharacterArrayWithBubble(firstArray);
        sortCharacterArrayWithBubble(secondArray);

        String sortedFirst = new String(firstArray);
        String sortedSecond = new String(secondArray);

        if (sortedFirst.equals(sortedSecond)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    //Task 2:K-th Smallest Element
    private static void findKthSmallestValue() {
        System.out.print("Enter array size: ");
        int size = readPositiveInteger();

        int[] numbers = readIntegerArray(size);

        System.out.print("Enter k (1-based): ");
        int position = readPositiveInteger();

        if (position <= 0 || position > size) {
            System.out.println("Invalid k value.");
            return;
        }
        sortIntegerArrayWithBubble(numbers);
        int result = numbers[position - 1];
        System.out.println("K-th smallest element: " + result);
    }

    //Task 3:Median
    private static void findMedianValue() {
        System.out.print("Enter array size: ");
        int size = readPositiveInteger();
        int[] numbers = readIntegerArray(size);
        sortIntegerArrayWithBubble(numbers);
        int medianIndex = size / 2;
        int median = numbers[medianIndex];
        System.out.println("Median value: " + median);
    }

    //Task 4:Shipping Capacity (Binary Search)
    private static boolean isCapacitySufficient(int capacity, int[] weights, int maxDays) {
        int daysUsed = 1;
        int currentLoad = 0;
        for (int weight : weights) {if (currentLoad + weight > capacity) {
            daysUsed++;
            currentLoad = weight;

            if (daysUsed > maxDays) {
                return false;
            }
        } else {
            currentLoad += weight;
        }
        }
        return true;
    }
    private static void calculateMinimumShippingCapacity() {
        System.out.print("Enter number of packages: ");
        int size = readPositiveInteger();

        int[] weights = readIntegerArray(size);

        int totalWeight = 0;
        int maxWeight = 0;

        for (int weight : weights) {
            totalWeight += weight;
            if (weight > maxWeight) {
                maxWeight = weight;
            }
        }
        System.out.print("Enter number of days: ");
        int days = readPositiveInteger();
        int left = maxWeight;
        int right = totalWeight;
        int answer = totalWeight;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (isCapacitySufficient(middle, weights, days)) {
                answer = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        System.out.println("Minimum capacity: " + answer);
    }
    // Input helpers
    private static int readPositiveInteger() {
        while (true) {
            if (inputReader.hasNextInt()) {
                int value = inputReader.nextInt();
                inputReader.nextLine();

                if (value > 0) {
                    return value;
                }
            } else {
                inputReader.nextLine();
            }

            System.out.print("Enter a positive integer: ");
        }
    }

    private static int[] readIntegerArray(int size) {
        int[] array = new int[size];

        System.out.println("Enter " + size + " integers:");

        for (int i = 0; i < size; i++) {
            while (!inputReader.hasNextInt()) {
                inputReader.nextLine();
                System.out.print("Invalid input. Enter an integer: ");
            }
            array[i] = inputReader.nextInt();
        }

        inputReader.nextLine();
        return array;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Check Anagrams");
            System.out.println("2 - Find K-th Smallest");
            System.out.println("3 - Find Median");
            System.out.println("4 - Shipping Capacity");
            System.out.println("0 - Exit");

            System.out.print("Choose option: ");

            int choice;
            if (inputReader.hasNextInt()) {
                choice = inputReader.nextInt();
                inputReader.nextLine();
            } else {
                inputReader.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    checkIfStringsAreAnagrams();
                    break;
                case 2:
                    findKthSmallestValue();
                    break;
                case 3:
                    findMedianValue();
                    break;
                case 4:
                    calculateMinimumShippingCapacity();
                    break;
                case 0:
                    System.out.println("Program finished.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
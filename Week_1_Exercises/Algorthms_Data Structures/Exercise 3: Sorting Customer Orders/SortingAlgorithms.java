class Order {
    private String orderId;
    private String customerName;
    private double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}    
}

public class SortingAlgorithms{

    public static void bubbleSort(Order[] orders){
        int n = orders.length;
        boolean swapped;
        for (int i=0;i<n-1;i++){
            swapped = false;
            for (int j=0;j<n-i-1;j++){
                if (orders[j].getTotalPrice()>orders[j+1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j+1];
                    orders[j+1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
    public static void display_array(Order orders) {
    	System.out.println("Order details:-");
    	System.out.println("Id: "+ orders.getOrderId());
    	System.out.println("Customer Name: "+orders.getCustomerName());
    	System.out.println("Total Price: "+orders.getTotalPrice());
    	System.out.println();
    	
    }
    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        Order[] orders = {
            new Order("01", "Aditya",1018),
            new Order("02", "Pragnya",6217),
            new Order("03", "Sashi", 9217),
            new Order("04", "Sagra",2118)
        };

        System.out.println("Original Orders:");
        for (int i=0;i<orders.length;i++) {
            display_array(orders[i]);
        }

        bubbleSort(orders);
        System.out.println("\nOrders after Bubble Sort:");
        for (int i=0;i<orders.length;i++) {
            display_array(orders[i]);
        }
        quickSort(orders, 0, orders.length - 1);
        System.out.println("\nOrders after Quick Sort:");
        for (int i=0;i<orders.length;i++) {
            display_array(orders[i]);
        }
    }
}
import java.util.Arrays;
import java.util.Comparator;

 class Product {
    private String productId;
    private String productName;
    private String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}

public class SearchAlgorithms {

    public static Product linearSearch(Product[] products, String targetName) {
        for(int i=0;i<products.length;i++) {
            if (products[i].getProductName().equalsIgnoreCase(targetName)){
                return products[i];
            }
        }
        return null;
    }
    public static void Displayproduct(Product product) {
    	System.out.println("Product details:-");
    	System.out.println("ID: "+ product.getProductId());
    	System.out.println("Name: "+ product.getProductName());
    	System.out.println("Category: "+product.getCategory());
    	System.out.println();
    }
    public static Product binarySearch(Product[] products, String targetName) {
        int left = 0;
        int right = products.length-1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductName().compareToIgnoreCase(targetName);

            if (comparison == 0) {
                return products[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
    	
        Product[] products ={
            new Product("01", "Heater", "Electronics"),
            new Product("02", "Camera", "Electronics"),
            new Product("03", "Jacket", "Clothing"),
            new Product("04", "Sliders", "Footwear")
        };

        System.out.println("Linear Search:-");
        Product result1 = linearSearch(products, "Camera");
        if (result1 != null) {
        	System.out.println("Product Found!!");
            Displayproduct(result1);
        } else {
            System.out.println("Product not found.\n");
        }

        Arrays.sort(products, Comparator.comparing(Product::getProductName));
        System.out.println("Binary Search:-");
        Product result2 = binarySearch(products, "Umbrella");
        if (result2 != null) {
            System.out.println("Product Found!!");
            Displayproduct(result2);	
        } else {
            System.out.println("Product not found.");
        }
    }
}

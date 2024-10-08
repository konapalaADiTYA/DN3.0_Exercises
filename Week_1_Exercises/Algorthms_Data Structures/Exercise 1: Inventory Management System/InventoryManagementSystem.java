import java.util.HashMap;

class Product_1 {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

class InventoryManagementSystem {
	private HashMap<String, Product> inventory;

    public InventoryManagementSystem() {
        inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    public void updateProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            inventory.put(product.getProductId(), product);
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
        	inventory.remove(productId);
            System.out.println("Product removed");
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    public void displayProducts(Product product) {
            System.out.println("Product details:- ");
            System.out.println("Id : "+product.getProductId());
            System.out.println("Name : "+product.getProductName());
            System.out.println("Quantity : "+product.getQuantity());
            System.out.println("Price : "+product.getPrice());
            System.out.println();

        }

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        Product p1 = new Product("01", "Bread", 10, 99.99);
        Product p2 = new Product("02", "Phone case", 9, 1000.00);
        ims.addProduct(p1);
        ims.addProduct(p2);
        ims.displayProducts(p1);
        ims.displayProducts(p2);
        p1.setPrice(100.00);
        ims.updateProduct(p1);
        ims.displayProducts(p1);
        ims.deleteProduct("01");
        ims.deleteProduct("01");
    }
}

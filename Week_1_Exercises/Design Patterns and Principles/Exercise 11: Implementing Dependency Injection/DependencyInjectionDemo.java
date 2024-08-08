

public class DependencyInjectionDemo {
    public static void main(String[] args) {
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CustomerService customerService = new CustomerService(customerRepository);

        Customer customer = customerService.getCustomerById("2141001018");
        System.out.println(customer);
    }
}

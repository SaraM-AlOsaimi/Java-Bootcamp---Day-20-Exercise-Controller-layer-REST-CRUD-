package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.ApiResponse;
import com.example.bankmanagementsystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/customers")
public class BankController {

    ArrayList<Customer> customers= new ArrayList<>();

    // Get all the customers
    @GetMapping("/get")
    public ArrayList<Customer> getCustomers(){
        return customers;
    }

    // Add new customers
    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customer customer){
       customers.add(customer);
        return new ApiResponse("Customer added successfully");
    }

    //Update
    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index , @RequestBody Customer customer){
        customers.set(index , customer);
        return new ApiResponse("Customer updated successfully");
    }


    //Delete
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index){
        customers.remove(index);
        return new ApiResponse("Customer deleted successfully");
    }

    // Deposit money to customer
    @PutMapping("/deposit/{id}")
    public String depositMoney(@PathVariable int id, @RequestParam double amount) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                if (amount > 0) {
                    customer.setBalance(customer.getBalance() + amount);
                    return "Deposit successful. New balance: " + customer.getBalance();
                } else {
                    return "Amount must be positive.";
                }
            }
        }
        return "Customer not found!";
    }

    // Withdraw money from customers
    @PutMapping("/withdraw/{id}")
    public String withdrawMoney(@PathVariable Long id, @RequestParam double amount) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                if (amount > 0 && amount <= customer.getBalance()) {
                    customer.setBalance(customer.getBalance() - amount);
                    return "Withdrawal successful. New balance: " + customer.getBalance();
                } else {
                    return "Invalid amount or insufficient balance.";
                }
            }
        }
        return "Customer not found!";
    }

}

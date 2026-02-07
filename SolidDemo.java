public class SolidDemo {
    public static void main(String[] args) {

        Order order = new Order("Laptop", 2, 1000);
        PriceCalculator calculator = new PriceCalculator();
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        NotificationServiceSRP notifier = new NotificationServiceSRP();

        double total = calculator.calculateTotalPrice(order);
        System.out.println("Total price: " + total);
        paymentProcessor.processPayment("Card");
        notifier.sendConfirmation("user@mail.com");

        Employee employee = new Employee("Alice", 1000);
        SalaryCalculator salaryCalculator = new PermanentEmployeeSalary();
        System.out.println("Salary: " + salaryCalculator.calculate(employee));

        Printable printer = new BasicPrinter();
        printer.print("Hello");

        AllInOnePrinter allInOne = new AllInOnePrinter();
        allInOne.print("Doc");
        allInOne.scan("Doc");
        allInOne.fax("Doc");

        MessageSender emailSender = new EmailSenderDIP();
        MessageSender smsSender = new SmsSenderDIP();

        NotificationServiceDIP service1 = new NotificationServiceDIP(emailSender);
        NotificationServiceDIP service2 = new NotificationServiceDIP(smsSender);

        service1.send("Hello via Email");
        service2.send("Hello via SMS");
    }
}

class Order {
    private String productName;
    private int quantity;
    private double price;

    public Order(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}

class PriceCalculator {
    public double calculateTotalPrice(Order order) {
        return order.getQuantity() * order.getPrice() * 0.9;
    }
}

class PaymentProcessor {
    public void processPayment(String paymentDetails) {
        System.out.println("Payment processed using: " + paymentDetails);
    }
}

class NotificationServiceSRP {
    public void sendConfirmation(String email) {
        System.out.println("Confirmation email sent to: " + email);
    }
}

class Employee {
    private String name;
    private double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
}

interface SalaryCalculator {
    double calculate(Employee employee);
}

class PermanentEmployeeSalary implements SalaryCalculator {
    public double calculate(Employee employee) {
        return employee.getBaseSalary() * 1.2;
    }
}

class ContractEmployeeSalary implements SalaryCalculator {
    public double calculate(Employee employee) {
        return employee.getBaseSalary() * 1.1;
    }
}

class InternEmployeeSalary implements SalaryCalculator {
    public double calculate(Employee employee) {
        return employee.getBaseSalary() * 0.8;
    }
}

interface Printable {
    void print(String content);
}

interface Scannable {
    void scan(String content);
}

interface Faxable {
    void fax(String content);
}

class BasicPrinter implements Printable {
    public void print(String content) {
        System.out.println("Printing: " + content);
    }
}

class AllInOnePrinter implements Printable, Scannable, Faxable {
    public void print(String content) {
        System.out.println("Printing: " + content);
    }

    public void scan(String content) {
        System.out.println("Scanning: " + content);
    }

    public void fax(String content) {
        System.out.println("Faxing: " + content);
    }
}


interface MessageSender {
    void sendMessage(String message);
}

class EmailSenderDIP implements MessageSender {
    public void sendMessage(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SmsSenderDIP implements MessageSender {
    public void sendMessage(String message) {
        System.out.println("SMS sent: " + message);
    }
}

class NotificationServiceDIP {
    private MessageSender sender;

    public NotificationServiceDIP(MessageSender sender) {
        this.sender = sender;
    }

    public void send(String message) {
        sender.sendMessage(message);
    }
}

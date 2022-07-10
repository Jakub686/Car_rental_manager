package console;


import repository.car.Car;
import repository.car.CarDaoImpl;
import repository.company.Company;
import repository.company.CompanyDaoImpl;
import repository.customer.Customer;
import repository.customer.CustomerDaoImpl;


import java.util.Scanner;

public class Console implements ConsoleBasicActions, ConsoleManagerActions, ConsoleCustomerActions {

    static public boolean stopApp = false;

    static String currentMenuCode;

    static Integer companyId = 1;
    static Integer customerId;
    static Integer carId;

    CompanyDaoImpl companyDao = new CompanyDaoImpl();
    CarDaoImpl carDao = new CarDaoImpl();
    CustomerDaoImpl customerDao = new CustomerDaoImpl();

    public Console() {
        CompanyDaoImpl companyDao = new CompanyDaoImpl();
        CarDaoImpl carDao = new CarDaoImpl();
        CustomerDaoImpl customerDao = new CustomerDaoImpl();

        showMainMenu();
        companyDao.createTable();
        carDao.createTable();
        customerDao.createTable();
    }

    @Override
    public void run(String command) {

        command = currentMenuCode + command;

        switch (command) {
            case "00":
                exit();
                break;
            case "0":
            case "010":
            case "020":
            case "030":
                showMainMenu();
                break;
            case "01":
            case "0110":
            case "01110":
                showManagerMenu();
                break;
            case "02":
            case "021":
            case "0210":
                showCustomerList();
                break;
            case "02110":
                showCustomerMenu();
                break;
            case "0211":
                customerChooseCompany();
                break;
            case "0212":
                customerReturnCar();
                break;
            case "0213":
                customerShowMyRentedCar();
                break;
            case "03":
                createNewCustomer();
                showMainMenu();
                break;
            case "011":
                showCompaniesList();
                break;
            case "012":
                createNewCompany();
                showManagerMenu();
                break;
            case "0111":
                showCompanyMenu();
                break;
            case "01111":
                showCarsList();
                break;
            case "01112":
                createNewCar();
                showCompanyMenu();
                break;
            default:
                wrongInputValueMessage();
                break;
        }
    }

    @Override
    public void showMainMenu() {
        currentMenuCode = "0";
        System.out.println("\n1. Log in as a manager\n2. Log in as a customer\n" +
                "3. Create a customer\n0. Exit");
    }

    @Override
    public void showManagerMenu() {
        currentMenuCode = "01";
        System.out.println("\n1. Company list\n2. Create a company\n0. Back");
    }

    @Override
    public void showCompaniesList() {

        CompanyDaoImpl.companies.clear();
        companyDao.getAllCompanies();
        System.out.println();

        if (CompanyDaoImpl.companies.isEmpty()) {
            System.out.println("The company list is empty!");
            showManagerMenu();
        } else {
            System.out.println("Choose the company:");
            for (Company company : CompanyDaoImpl.companies) {
                System.out.println(company.getId() + ". " + company.getName());
            }
            System.out.println("0. Back");

            while (true) {
                Scanner scanner = new Scanner(System.in);
                int value = -1;
                try {
                    value = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                }

                if (value > 0 && value <= CompanyDaoImpl.companies.size()) {
                    companyId = value;
                    showCompanyMenu();
                    break;
                } else if (value == 0) {
                    showManagerMenu();
                    break;
                } else {
                    System.out.println("Incorrect value. Please, enter again");
                }
            }
        }
    }

    @Override
    public void showCompanyMenu() {
        currentMenuCode = "0111";
        System.out.println("\n'" + CompanyDaoImpl.companies.get(companyId - 1).getName() + "' company");
        System.out.println("1. Car list\n2. Create a car\n0. Back");
    }

    @Override
    public void showCustomerList() {
        currentMenuCode = "02";
        CustomerDaoImpl.customers.clear();
        customerDao.getAllCustomers();
        System.out.println();

        if (CustomerDaoImpl.customers.isEmpty()) {
            System.out.println("The customer list is empty!");
            showMainMenu();
        } else {
            System.out.println("Customer list:");
            for (Customer customer : CustomerDaoImpl.customers) {
                System.out.println(customer.getId() + ". " + customer.getName());
            }
            System.out.println("0. Back");

            while (true) {
                Scanner scanner = new Scanner(System.in);
                int value = -1;
                try {
                    value = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                }

                if (value > 0 && value <= CustomerDaoImpl.customers.size()) {
                    customerId = value;
                    showCustomerMenu();
                    break;
                } else if (value == 0) {
                    showMainMenu();
                    break;
                } else {
                    System.out.println("Incorrect value. Please, enter again");
                }
            }
        }
    }

    @Override
    public void showCustomerMenu() {
        currentMenuCode = "021";
        System.out.println("\n1. Rent a car\n" +
                "2. Return a rented car\n" +
                "3. My rented car\n" +
                "0. Back");
    }

    @Override
    public void customerChooseCompany() {
        currentMenuCode = "0211";

        if (customerDao.getCar(customerId) != 0) {
            System.out.println(customerDao.getCar(customerId));
            System.out.println("You've already rented a car!");
            showCustomerMenu();

        } else {

            CompanyDaoImpl.companies.clear();
            companyDao.getAllCompanies();

            if (CompanyDaoImpl.companies.isEmpty()) {
                System.out.println("The company list is empty!");
                showCustomerMenu();
            } else {
                System.out.println("\nChoose a company:");
                for (Company company : CompanyDaoImpl.companies) {
                    System.out.println(company.getId() + ". " + company.getName());
                }
                System.out.println("0. Back");

                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    int value = -1;
                    try {
                        value = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                    }

                    if (value > 0 && value <= CompanyDaoImpl.companies.size()) {
                        companyId = value;
                        customerChooseCar();
                        break;
                    } else if (value == 0) {
                        showCustomerMenu();
                        break;
                    } else {
                        System.out.println("Incorrect value. Please, enter again");
                    }
                }

            }
        }
    }

    @Override
    public void customerChooseCar() {

        CarDaoImpl.cars.clear();
        carDao.getCarList(companyId);

        if (CarDaoImpl.cars.isEmpty()) {
            System.out.println("\nThe car list is empty!");
            customerChooseCompany();
        } else {

            System.out.println("\n'" + CompanyDaoImpl.companies.get(companyId - 1).getName() + "' cars:");
            for (Car car : CarDaoImpl.cars) {
                System.out.println(car.getId() + ". " + car.getName());
            }
            System.out.println("0. Back");

            while (true) {
                Scanner scanner = new Scanner(System.in);
                int value = -1;
                try {
                    value = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                }

                if (value > 0 && value <= CarDaoImpl.cars.size()) {
                    carId = value;
                    customerDao.addCar(customerId, carId);
                    System.out.println("\nYou rented '" + CarDaoImpl.cars.get(value - 1).getName() + "'");
                    showCustomerMenu();
                    break;
                } else if (value == 0) {
                    customerChooseCompany();
                    break;
                } else {
                    System.out.println("Incorrect value. Please, enter again");
                }
            }
        }
    }

    @Override
    public void customerReturnCar() {
        carId = customerDao.getCar(customerId);
        if (carId == 0) {
            System.out.println("You didn't rent a car!");
        } else {
            System.out.println(carId);
            customerDao.removeCar(carId);
            carId = 0;
            System.out.println("\nYou've returned a rented car!");
        }
        showCustomerMenu();
    }

    @Override
    public void customerShowMyRentedCar() {

        int carDBId = customerDao.getCar(customerId);
        System.out.println(carDBId);

        if (carDBId == 0) {
            System.out.println("You didn't rent a car!");
        } else {
            System.out.println("Your rented car:\n" +
                    carDao.getCarName(carDBId) +
                    "\nCompany:\n" +
                    companyDao.getCompanyName(companyId)
            );
        }
        showCustomerMenu();
    }

    @Override
    public void showCarsList() {

        CarDaoImpl.cars.clear();
        carDao.getCarList(companyId);
        System.out.println();

        if (CarDaoImpl.cars.isEmpty()) {
            System.out.println("\nThe car list is empty!\n");
        } else {
            System.out.println("'" + CompanyDaoImpl.companies.get(companyId - 1).getName() + "' cars:");
            for (Car car : CarDaoImpl.cars) {
                System.out.println(car.getId() + ". " + car.getName());
            }
        }
        showCompanyMenu();
    }

    @Override
    public void createNewCompany() {
        currentMenuCode = "012";
        System.out.println("\nEnter the company name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        companyDao.addCompany(name);
        System.out.println("The company was created!");
    }

    @Override
    public void createNewCar() {
        currentMenuCode = "01112";
        System.out.println("\nEnter the car name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        carDao.addCar(name, companyId);
        System.out.println("The car was added!");
    }

    @Override
    public void createNewCustomer() {
        currentMenuCode = "03";
        System.out.println("\nEnter the customer name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        customerDao.createCustomer(name);
        System.out.println("The customer was added!");
    }

    @Override
    public void wrongInputValueMessage() {
        System.out.println("Wrong input value. Please, enter again");
    }

    @Override
    public void exit() {
        stopApp = true;
    }

}

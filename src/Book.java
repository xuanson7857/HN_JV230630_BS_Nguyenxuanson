import java.util.Scanner;

public class Book {
    private static int nextId = 0;
    private int bookId;
    private String bookName;
    private String author;
    private String description;
    private double importPrice;
    private double exportPrice;
    private float interest;
    private boolean bookStatus;

    public Book() {
        this.bookId = nextId++;
        this.bookStatus = true;

    }

    public Book(String bookName, String author, String description, double importPrice, double exportPrice, boolean bookStatus, float interest) {
        this.bookId = nextId++;

        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.bookStatus = bookStatus;
        this.interest = interest;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    public void inputData(Scanner scanner, int currentIndex) {
        boolean isExit = true;

        this.bookId = currentIndex;

        System.out.println("Nhập vào tên sách:");
        do {
            this.bookName = scanner.nextLine();
            if (!this.bookName.trim().isEmpty()) {
                break;
            } else {
                System.err.println("Tên sách không được để trống!!!");
            }
        } while (isExit);

        System.out.println("Nhập vào tên tác giả:");
        do {
            this.author = scanner.nextLine();
            if (!this.author.trim().isEmpty()) {
                break;
            } else {
                System.err.println("Tên tác giả không được để trống!!!");
            }
        } while (isExit);

        System.out.println("Nhập vào mô tả sách:");
        do {
            this.description = scanner.nextLine();
            if (!this.description.trim().isEmpty()) {
                if (this.description.trim().length() > 10) {
                    break;
                } else {
                    System.err.println("Phần mô tả sách không được ngắn hơn 10 ký tự");
                }
            } else {
                System.err.println("Phần mô tả sách không được để trống");
            }
        } while (isExit);

        System.out.println("Nhập vào giá nhập:");
        do {
            this.importPrice = Double.parseDouble(scanner.nextLine());
            if (this.importPrice > 0) {
                break;
            } else {
                System.err.println("Giá nhập phải lớn hơn 0");
            }
        } while (isExit);

        System.out.println("Nhập vào giá xuất:");
        do {
            this.exportPrice = Double.parseDouble(scanner.nextLine());
            if (this.exportPrice > this.importPrice * 1.2) {
                break;
            } else {
                System.err.println("Giá xuất phải lớn hơn 1.2 lần giá nhập");
            }
        } while (isExit);

        System.out.println("Nhập vào trạng thái:");
        do {
            String inputBookStatus = scanner.nextLine();
            if (inputBookStatus.trim().equals("true") || inputBookStatus.trim().equals("false")) {
                this.bookStatus = Boolean.parseBoolean(inputBookStatus.trim());
                break;
            } else {
                System.err.println("Vui lòng nhập 'true' hoặc 'false'");
            }
        } while (isExit);
        interest = (float) (exportPrice - importPrice);

    }

    public void displayData() {
        System.out.printf("Mã sách: %-3d  Tên sách: %-10s  Tác giả: %-10s\n", this.bookId, this.bookName, this.author);
        System.out.printf("Mô tả: %-50s\n", this.description);
        System.out.printf("Giá nhập: %-10.2f Giá xuất: %-10.2f Tình trạng: %b\n", this.importPrice, this.exportPrice, this.bookStatus);
    }
}


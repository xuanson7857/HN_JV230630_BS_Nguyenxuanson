

import java.util.Scanner;

public class Main {
    public static Book[] arrBook = new Book[100];
    public static int currentIndex = 0;


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice, numberOfBook, bookId, deleteIndex, changeIndex;
        String searchTerm;

        do {
            System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************");
            System.out.println("1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách");
            System.out.println("2. Hiển thị thông tin tất cả sách trong thư viện");
            System.out.println("3. Sắp xếp sách theo lợi nhuận tăng dần");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm tương đối theo tên sách hoặc mô tả");
            System.out.println("6. Thay đổi thông tin theo mã sách");
            System.out.println("7. Thoát");
            System.out.print("Nhập vào yêu cầu  của bạn: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Vui lòng nhập vào số lượng sách muốn thêm mới:");
                    numberOfBook = Integer.parseInt(scanner.nextLine());
                        for (int i = 0; i < numberOfBook; i++) {
                            addBook(scanner, currentIndex);
                            currentIndex++;

                        }

                    break;
                case 2:
                    for (int i = 0; i < currentIndex; i++) {
                        arrBook[i].displayData();
                    }
                    break;
                case 3:
                    if (currentIndex == 0) {
                    System.out.println("Thư viện sách rỗng.");
                } else {


                        for (int i = 0; i < currentIndex - 1; i++) {
                            for (int j = i + 1; j < currentIndex; j++) {
                                if (arrBook[i].getInterest() > arrBook[j].getInterest()) {
                                    Book book1 = arrBook[i];
                                    arrBook[i] = arrBook[j];
                                    arrBook[j] = book1;
                                }
                            }
                        }
                    }
                    for (int i = 0; i < currentIndex; i++) {
                        arrBook[i].displayData();
                        System.out.println("Lợi nhuận: " + arrBook[i].getInterest());
                    }
                    break;
                case 4:
                    System.out.println("Vui lòng nhập vào mã sách muốn xóa:");
                    bookId = Integer.parseInt(scanner.nextLine());
                    deleteIndex = getBookIndexById(bookId, arrBook, currentIndex);
                    if (deleteIndex != -1) {
                        deleteBookByIndex(deleteIndex, arrBook, currentIndex);
                        currentIndex--;
                    } else {
                        System.err.println("Mã sách không tồn tại!!!");
                    }
                    break;
                case 5:
                    System.out.println("Vui lòng nhập vào tên sách hoặc mô tả sách muốn tìm kiếm:");
                    searchTerm = scanner.nextLine();
                    searchBookByBookNameOrDescriptions(searchTerm, arrBook, currentIndex);
                    break;
                case 6:
                    System.out.println("Vui lòng nhập vào mã sách muốn thay đổi thông tin:");
                    bookId = Integer.parseInt(scanner.nextLine());
                    changeIndex = getBookIndexById(bookId, arrBook, currentIndex);
                    changeBookDataByIndex(scanner, changeIndex, arrBook, currentIndex);
                    break;
                case 7:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vui lòng nhập vào lựa chọn là số nguyên từ 1-7");
            }
        } while (true);
    }

    public static void addBook(Scanner scanner, int currentIndex) {
        Book book = new Book();
        arrBook[currentIndex] = book;
        arrBook[currentIndex].inputData(scanner, currentIndex);
    }

    public static int getBookIndexById(int bookId, Book[] arrBook, int currentIndex) {
        int bookIndex = -1;
        for (int i = 0; i < currentIndex; i++) {
            if (arrBook[i].getBookId() == bookId) {
                bookIndex = i;
            }
        }
        return bookIndex;
    }

    public static void deleteBookByIndex(int deleteIndex, Book[] arrBook, int currentIndex) {
        for (int i = deleteIndex; i < currentIndex - 1; i++) {
            arrBook[i] = arrBook[i + 1];
        }
        arrBook[currentIndex - 1] = null;
    }

    public static void searchBookByBookNameOrDescriptions(String searchTerm, Book[] arrBook, int currentIndex) {
        System.out.println();
        int searchResultsCount = 0;
        for (int i = 0; i < currentIndex; i++) {
            if (arrBook[i].getBookName().toLowerCase().contains(searchTerm.trim().toLowerCase()) || arrBook[i].getDescription().toLowerCase().contains(searchTerm.trim().toLowerCase())) {
                arrBook[i].displayData();
                searchResultsCount++;
            }
        }
        if (searchResultsCount == 0) {
            System.out.println("Không tìm thấy sách ");
        }
    }

    public static void changeBookDataByIndex(Scanner sc, int changeIndex, Book[] arrBook, int currentIndex) {
        boolean isExit = true;
        System.out.println("Nhập vào tên sách mới (Enter để bỏ qua):");
        do {
            String newBookName = sc.nextLine();
            if (newBookName.trim().isEmpty()) {
                break;
            } else {
                arrBook[changeIndex].setBookName(newBookName);
            }
        } while (isExit);
        System.out.println("Nhập vào tên tác giả mới (Enter để bỏ qua):");
        do {
            String newAuthor = sc.nextLine();
            if (newAuthor.trim().isEmpty()) {
                break;
            } else {
                arrBook[changeIndex].setAuthor(newAuthor);
            }
        } while (isExit);
        System.out.println("Nhập vào mô tả sách mới (Enter để bỏ qua):");
        do {
            String newDescription = sc.nextLine();
            if (newDescription.trim().isEmpty()) {
                break;
            } else {
                if (newDescription.trim().length() <= 10) {
                    System.err.println("Phần mô tả sách không được ngắn hơn 10 ký tự");
                } else {
                    arrBook[changeIndex].setDescription(newDescription);
                    break;
                }
            }
        } while (isExit);
        System.out.println("Nhập vào giá nhập mới (Enter để bỏ qua):");
        do {
            String newImportPrice = sc.nextLine();
            if (newImportPrice.trim().isEmpty()) {
                break;
            } else {
                if (Double.parseDouble(newImportPrice) > 0) {
                    arrBook[changeIndex].setImportPrice(Double.parseDouble(newImportPrice));
                    break;
                } else {
                    System.err.println("Giá nhập phải lớn hơn 0");
                }
            }
        } while (isExit);
        System.out.println("Nhập vào giá xuất mới (Enter để bỏ qua):");
        do {
            String newExportPrice = sc.nextLine();
            if (newExportPrice.trim().isEmpty()) {
                break;
            } else {
                if (Double.parseDouble(newExportPrice) > 1.2 * arrBook[changeIndex].getImportPrice()) {
                    arrBook[changeIndex].setExportPrice(Double.parseDouble(newExportPrice));
                    break;
                } else {
                    System.err.println("Giá xuất phải lớn hơn 1.2 lần giá nhập");
                }
            }
        } while (isExit);
        System.out.println("Nhập vào trạng thái mới (Enter để bỏ qua):");
        do {
            String newBookStatus = sc.nextLine();
            if (newBookStatus.trim().isEmpty()) {
                break;
            } else {
                if (newBookStatus.trim().equals("true") || newBookStatus.trim().equals("false")) {
                    arrBook[changeIndex].setBookStatus(Boolean.parseBoolean(newBookStatus));
                    break;
                } else {
                    System.out.println("Vui lòng nhập vào 'true' hoặc 'false'");                }
            }
        } while (isExit);
    }
}
